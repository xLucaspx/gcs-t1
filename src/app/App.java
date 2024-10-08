package app;

import handler.ItemHandler;
import handler.JogadorHandler;
import handler.PropostaHandler;
import model.DadosProposta;
import model.Item;
import model.Jogador;
import model.Proposta;
import model.StatusProposta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * <p>Classe da aplicação.</p>
 * <p><strong>Restrições</strong>:</p>
 * <ul>
 * 	<li>A entrada de dados para popular o sistema na inicialização deverá ocorrer por
 * 	leitura de arquivo de texto;</li>
 * 	<li>As interações do usuário com o sistema deverão ocorrer por meio do console;</li>
 * 	<li>Toda entrada e saida de dados deve ocorrer apenas na classe <code>App</code>.</li>
 * </ul>
 *
 * @author Caetano Kich Taffe, Daniella Moraes, Gabriel Domingues, Gabriel Paim,
 * Gustavo Fidélis, Lucas da Paz
 */
public class App {

	/**
	 * <p>Caminho para o arquivo que contém os dados que devem ser inseridos ao carregar o sistema.
	 * Deve estar em um formato válido, i.e.:</p>
	 * <ul>
	 * 	<li>Número <strong>x</strong> de jogadores que serão cadastrados;</li>
	 * 	<li><strong>x</strong> linhas com as informações de cada jogador no formato
	 * 		<code>nome,email,pin</code>;</li>
	 * 	<li>Número <strong>y</strong> de itens que serão cadastrados;</li>
	 * 	<li><strong>y</strong> linhas com as informações de cada item no formato
	 * 		<code>id,nome,descricao,categoria,preco,email-jogador</code>;</li>
	 * 	<li>Número <strong>z</strong> de propostas que serão cadastradas;</li>
	 * 	<li><strong>z</strong> linhas com as informações de cada proposta no formato
	 * 		<code>email-solicitante,id-item-solicitante,email-solicitado,id-item-solicitado,data,status</code>.
	 * 	</li>
	 * </ul>
	 */
	private static final String CAMINHO_ARQUIVO_SEEDER = "./resources/seeder.txt";

	private final JogadorHandler jogadorHandler;
	private final ItemHandler itemHandler;
	private final PropostaHandler propostaHandler;

	private Scanner in;
	private Jogador jogadorLogado;
	private boolean run;

	public App() {
		jogadorHandler = new JogadorHandler();
		itemHandler = new ItemHandler();
		propostaHandler = new PropostaHandler();
		in = new Scanner(System.in);
	}

	/**
	 * Método que executa a aplicação.
	 */
	public void executar() {
		insereDados();
		run = true;

		while (run) {
			if (jogadorLogado == null) {
				menuInicial();
			} else {
				menuPrincipal();
			}
		}
	}

	/**
	 * Imprime o cabeçalho do sistema.
	 */
	private void cabecalho() {
		System.out.println("\n---Sistema de Gerenciamento de Itens---\n");
	}

	/**
	 * Imprime as informações do jogador
	 */
	private void informacoesJogador() {
		if (jogadorLogado == null) return;

		System.out.println(jogadorLogado);
		long qtdPropostasRecebidasAbertas = jogadorLogado.getPropostasRecebidas().stream().filter(p -> p.getStatus()
			.equals(StatusProposta.ABERTA)).count();
		long qtdPropostasRealizadasAbertas = jogadorLogado.getPropostasRealizadas().stream().filter(p -> p.getStatus()
			.equals(StatusProposta.ABERTA)).count();

		System.out.printf("Notificações:%n- %d propostas recebidas em aberto%n- %d propostas realizadas em aberto%n%n",
			qtdPropostasRecebidasAbertas,
			qtdPropostasRealizadasAbertas
		);
	}

	/**
	 * Solicita que o usuário interaja com o sistema antes de continuar.
	 */
	private void aguardaUsuario() {
		System.out.print("\nPressione ENTER para continuar...");
		in.nextLine();
	}

	/**
	 * Menu que permite ao usuário se autenticar ou se cadastrar no sistema.
	 */
	private void menuInicial() {
		cabecalho();
		System.out.print("""
			[1] Login
			[2] Cadastro
			[3] Escolher usuário
			[0] Encerrar

			Escolha...\s""");

		int op = Integer.parseInt(in.nextLine());
		switch (op) {
			case 1 -> login();
			case 2 -> cadastro();
			case 3 -> escolheUsuario();
			case 0 -> encerrar();
			default -> System.out.println("\nOpção inválida");
		}
	}

	/**
	 * Menu que permite ao usuário autenticado utilizar funcionalidades do sistema.
	 */
	private void menuPrincipal() {
		if (!isAutenticado()) return;

		aguardaUsuario();
		cabecalho();
		informacoesJogador();

		System.out.print("""
			- Menu Principal -
			[1] Listar seus itens
			[2] Cadastrar novo item
			[3] Listar itens disponíveis
			[4] Pesquisar itens
			[5] Nova proposta de troca
			[6] Listar propostas de troca
			[7] Gerenciar propostas recebidas
			[8] Informações do sistema
			[9] Logout
			[0] Encerrar

			Escolha...\s""");

		int op = Integer.parseInt(in.nextLine());
		switch (op) {
			case 1 -> listaItensJogador();
			case 2 -> cadastraItem();
			case 3 -> listaItensDisponiveis();
			case 4 -> buscaItens();
			case 5 -> abrePropostaTroca();
			case 6 -> listaPropostas();
			case 7 -> handlePropostasRecebidas();
			case 8 -> mostraInformacoesSistema();
			case 9 -> logout();
			case 0 -> encerrar();
			default -> System.out.println("\nOpção inválida");
		}
	}

	/**
	 * Confirma a opção de encerrar do usuário e, caso afirmativa,
	 * define que o programa deve parar de executar.
	 */
	private void encerrar() {
		System.out.print("Tem certeza que deseja encerrar o sistema? Digite S para confirmar... ");
		String input = in.nextLine();

		if (!input.equalsIgnoreCase("S")) {
			System.out.println("Operação cancelada!");
			return;
		}

		System.out.println("\nSistema encerrado!\n");
		jogadorLogado = null;
		run = false;
	}

	/**
	 * Lê as informações de cadastro inseridas pelo usuário, valida
	 * conforme as regras da aplicação e realiza o cadastro do usuário.
	 * Em caso de erro, é exibida mensagem na tela.
	 */
	private void cadastro() {
		if (jogadorLogado != null) {
			System.out.println("\nVocê já possui cadastro!");
			return;
		}

		System.out.println("\n- Cadastro -");
		System.out.print("Digite seu nome: ");
		String nome = in.nextLine();

		if (nome.isBlank()) {
			System.out.println("O nome deve ser preenchido!");
			return;
		}

		System.out.print("Digite seu e-mail: ");
		String email = in.nextLine();

		if (email.isBlank() || !email.matches("^\\w+([.\\-]?\\w+)*@\\w+([.\\-]?\\w+)*(\\.\\w{2,3})+$")) {
			System.out.println("O e-mail inserido é inválido!");
			return;
		}

		if (jogadorHandler.buscaPorEmail(email) != null) {
			System.out.println("Este e-mail já está cadastrado no sistema!");
			return;
		}

		System.out.print("Digite seu pin (6 dígitos): ");
		String pin = in.nextLine();

		if (!pin.matches("\\d{6}")) {
			System.out.println("O pin deve ser preenchido com 6 dígitos!");
			return;
		}

		Jogador j = new Jogador(nome, email, pin);
		if (jogadorHandler.cadastra(j)) {
			System.out.println("Cadastro realizado com sucesso!");
			return;
		}

		System.out.println("Erro inesperado ao realizar o cadastro...");
	}

	/**
	 * Lê as informações de <em>login</em> inseridas no sistema e,
	 * caso sejam válidas, permite que o usuário entre no sistema.
	 */
	private void login() {
		if (jogadorLogado != null) {
			System.out.println("\nVocê já está logado!");
			return;
		}

		System.out.println("\n- Login -");
		System.out.print("E-mail: ");
		String email = in.nextLine();
		Jogador j = jogadorHandler.buscaPorEmail(email);

		if (j == null) {
			System.out.println("E-mail não cadastrado!");
			return;
		}

		System.out.print("Pin: ");
		String pin = in.nextLine();

		if (!j.verificaPin(pin)) {
			System.out.println("Pin incorreto!");
			return;
		}

		jogadorLogado = j;
		System.out.printf("Seja bem-vindo(a), %s%n", j.getNome());
	}

	/**
	 * Permite escolher um usuário da lista de usuários cadastrados,
	 * evitando a necessidade de login. Para fins didáticos e de testes.
	 */
	private void escolheUsuario() {
		if (jogadorLogado != null) {
			System.out.printf("\nVocê já está autenticado como %s%n", jogadorLogado.getNome());
			return;
		}

		System.out.println("\n- Jogadores disponíveis -");
		List<Jogador> jogadores = jogadorHandler.getJogadores();
		int size = jogadores.size();
		for (int i = 0; i < size; i++) {
			Jogador j = jogadores.get(i);
			System.out.printf("[%2d] - %s <%s>%n", i + 1, j.getNome(), j.getEmail());
		}

		System.out.print("\nEscolha... ");
		int op = Integer.parseInt(in.nextLine());

		if (op < 1 || op > size) {
			System.out.println("Valor inválido inserido!");
			return;
		}

		jogadorLogado = jogadores.get(--op);
	}

	/**
	 * Confirma a opção de <em>logout</em> do usuário e, caso afirmativa,
	 * realiza a saída da aplicação.
	 */
	private void logout() {
		if (!isAutenticado()) return;

		System.out.print("Tem certeza que deseja sair? Digite S para confirmar... ");
		String input = in.nextLine();

		if (!input.equalsIgnoreCase("S")) {
			System.out.println("Operação cancelada!");
		}

		System.out.println("Saindo... Até mais!");
		jogadorLogado = null;
	}

	/**
	 * Verifica se existe um jogador autenticado no sistema.
	 *
	 * @return <code>true</code> caso exista um <code>Jogador</code> logado,
	 * <code>false</code> caso contrário.
	 */
	private boolean isAutenticado() {
		if (jogadorLogado == null) {
			System.out.println("\nÉ necessário realizar o login no sistema!");
			return false;
		}
		return true;
	}

	/**
	 * Permite que o usuário cadastre um novo <code>Item</code>.
	 */
	private void cadastraItem() {
		if (!isAutenticado()) return;

		System.out.println("\n- Cadastro de item -");
		System.out.print("Digite o nome do item: ");
		String nome = in.nextLine();

		if (nome.isBlank()) {
			System.out.println("O nome do item deve ser informado!");
			return;
		}

		System.out.print("Digite a descrição do item: ");
		String descricao = in.nextLine();

		if (descricao.isBlank()) {
			System.out.println("A descrição do item deve ser informada!");
			return;
		}

		System.out.print("Digite a categoria do item: ");
		String categoria = in.nextLine();

		if (categoria.isBlank()) {
			System.out.println("A categoria do item deve ser informada!");
			return;
		}

		System.out.print("Digite o preço do item (utilize ponto decimal): ");
		float preco = Float.parseFloat(in.nextLine());

		Item i = new Item(nome, descricao, categoria, preco);
		System.out.printf("%n%s%n%n", i);

		System.out.print("Confirma a criação do item? Digite S para confirmar... ");
		String input = in.nextLine();

		if (!input.equalsIgnoreCase("S")) {
			System.out.println("Operação cancelada!");
			return;
		}

		itemHandler.cadastra(i);
		jogadorLogado.addItem(i);
		System.out.println("Cadastro realizado com sucesso!");
	}

	/**
	 * Lista os itens do jogador logado, em ordem alfabética.
	 */
	private void listaItensJogador() {
		if (!isAutenticado()) return;

		System.out.println("\n- Seus itens -");
		List<Item> itens = new ArrayList<>(jogadorLogado.getItens());

		if (itens.isEmpty()) {
			System.out.println("Nenhum item encontrado!");
			return;
		}

		// ordena alfabeticamente
		itens.sort(Comparator.comparing(Item::getNome));

		for (Item i : itens) {
			System.out.printf("%s%n%n", i);
		}
	}

	/**
	 * Lista todos os itens cadastrados no sistema, exceto os do próprio jogador.
	 * Ordena os itens pelo preço.
	 */
	private void listaItensDisponiveis() {
		if (!isAutenticado()) return;

		System.out.println("\n- Itens disponíveis -");
		List<Item> itens = new ArrayList<>(itemHandler.getItens());

		if (itens.isEmpty()) {
			System.out.println("Nenhum item encontrado!");
			return;
		}

		// ordena por preço
		itens.sort(Comparator.comparing(Item::getPreco));

		for (Item i : itens) {
			if (i.getJogador().equals(jogadorLogado)) continue;
			System.out.printf("%s%n%n", i);
		}
	}

	/**
	 * Menu de busca de itens.
	 */
	private void buscaItens() {
		if (!isAutenticado()) return;

		System.out.print("""

			- Pesquisa de itens -
			[1] Buscar por ID
			[2] Buscar por nome
			[3] Buscar por descrição
			[4] Buscar por categoria
			[0] Voltar

			Escolha...\s""");

		int op = Integer.parseInt(in.nextLine());
		switch (op) {
			case 1 -> buscaItemPorId();
			case 2 -> buscaItensPorNome();
			case 3 -> buscaItensPorDescricao();
			case 4 -> buscaItensPorCategoria();
		}
	}

	/**
	 * <p>Método responsável por buscar um item pelo seu ID.</p>
	 * <p>O método solicita ao usuário o ID do item que deseja buscar. Em seguida,
	 * realiza a busca no sistema utilizando o ID fornecido. Caso o item seja
	 * encontrado, suas informações serão exibidas. Caso contrário, uma mensagem
	 * informando que nenhum item foi encontrado é exibida.</p>
	 */
	private void buscaItemPorId() {
		if (!isAutenticado()) return;

		System.out.print("Digite o ID do item: ");
		int id = Integer.parseInt(in.nextLine());

		Item item = itemHandler.buscaPorId(id);
		if (item == null) {
			System.out.println("Item não encontrado!");
			return;
		}
		System.out.println(item);
	}

	/**
	 * <p>Método responsável por buscar itens pelo nome.</p>
	 * <p>O método solicita ao usuário o nome (ou parte do nome) do item que deseja
	 * buscar. Em seguida, realiza a busca no sistema utilizando o nome
	 * fornecido. Caso itens sejam encontrados, suas informações serão exibidas.
	 * Caso contrário, uma mensagem informando que nenhum item foi encontrado é
	 * exibida.</p>
	 */
	private void buscaItensPorNome() {
		if (!isAutenticado()) return;

		System.out.print("Digite o nome para buscar: ");
		String nome = in.nextLine();

		List<Item> itensEncontrados = itemHandler.buscaPorNome(nome);
		if (itensEncontrados.isEmpty()) {
			System.out.printf("Nenhum item encontrado para a busca \"%s\"!%n", nome);
			return;
		}

		for (Item i : itensEncontrados) {
			System.out.printf("%s%n%n", i);
		}
	}

	/**
	 * <p>Método responsável por buscar itens pela descrição.</p>
	 * <p>O método solicita ao usuário a descrição (ou parte da descrição) do item
	 * que deseja buscar. Em seguida, realiza a busca no sistema utilizando a
	 * descrição fornecida. Caso itens sejam encontrados, suas informações serão
	 * exibidas. Caso contrário, uma mensagem informando que nenhum item foi
	 * encontrado é exibida.</p>
	 */
	private void buscaItensPorDescricao() {
		if (!isAutenticado()) return;

		System.out.print("Digite a descrição para buscar: ");
		String descricao = in.nextLine();

		List<Item> itensEncontrados = itemHandler.buscaPorDescricao(descricao);
		if (itensEncontrados.isEmpty()) {
			System.out.printf("Nenhum item encontrado para a busca \"%s\"!%n", descricao);
			return;
		}

		for (Item i : itensEncontrados) {
			System.out.printf("%s%n%n", i);
		}
	}

	/**
	 * <p>Método responsável por buscar itens pela categoria.</p>
	 * <p>O método solicita ao usuário a categoria do item que deseja buscar. Em
	 * seguida, realiza a busca no sistema utilizando a categoria fornecida.
	 * Caso itens sejam encontrados, suas informações serão exibidas. Caso
	 * contrário, uma mensagem informando que nenhum item foi encontrado é
	 * exibida.</p>
	 */
	private void buscaItensPorCategoria() {
		if (!isAutenticado()) return;

		System.out.print("Digite a categoria para buscar: ");
		String categoria = in.nextLine();

		List<Item> itensEncontrados = itemHandler.buscaPorCategoria(categoria);
		if (itensEncontrados.isEmpty()) {
			System.out.printf("Nenhum item encontrado para a busca \"%s\"!%n", categoria);
			return;
		}

		for (Item i : itensEncontrados) {
			System.out.printf("%s%n%n", i);
		}
	}

	private void abrePropostaTroca() {
		if (!isAutenticado()) return;

		System.out.println("\n- Nova proposta de troca -");
		System.out.print("Digite o ID do item que está oferecendo: ");
		int id = Integer.parseInt(in.nextLine());
		Item itemOferecido = jogadorLogado.getItem(id);

		if (itemOferecido == null) {
			System.out.println("Item não encontrado!");
			return;
		}

		System.out.print("Digite o ID do item solicitado: ");
		id = Integer.parseInt(in.nextLine());
		Item itemSolicitado = itemHandler.buscaPorId(id);

		if (itemSolicitado == null) {
			System.out.println("Item não encontrado!");
			return;
		}

		System.out.printf("""

			Remetente: %s
			Item oferecido: %s

			Destinatário: %s
			Item solicitado: %s

			""", jogadorLogado.getNome(), itemOferecido, itemSolicitado.getJogador().getNome(), itemSolicitado);
		System.out.print("Confirma a criação da proposta? Digite S para confirmar... ");
		String input = in.nextLine();

		if (!input.equalsIgnoreCase("S")) {
			System.out.println("Operação cancelada!");
			return;
		}

		DadosProposta solicitante = new DadosProposta(jogadorLogado, itemOferecido);
		DadosProposta solicitado = new DadosProposta(itemSolicitado.getJogador(), itemSolicitado);
		Proposta p = new Proposta(solicitante, solicitado);
		propostaHandler.cadastra(p);

		System.out.println("Proposta realizada com sucesso!");
	}

	private void listaPropostas() {
		if (!isAutenticado()) return;

		System.out.print("""

			- Listagem de propostas -
			[1] Propostas recebidas
			[2] Propostas realizadas
			[3] Propostas recebidas em aberto
			[4] Propostas realizadas em aberto
			[0] Voltar

			Escolha...\s""");

		int op = Integer.parseInt(in.nextLine());
		switch (op) {
			case 1 -> listaPropostasRecebidas();
			case 2 -> listaPropostasRealizadas();
			case 3 -> listaPropostasRecebidasAbertas();
			case 4 -> listaPropostasRealizadasAbertas();
		}
	}

	/**
	 * <p>Lista todas as propostas recebidas em aberto do jogador logado </p>
	 */
	private void listaPropostasRecebidasAbertas() {
		if (!isAutenticado()) return;

		List<Proposta> recebidas = jogadorLogado.getPropostasRecebidas().stream().filter(p -> p.getStatus()
			.equals(StatusProposta.ABERTA)).toList();
		System.out.printf("%n%d propostas recebidas em aberto:%n", recebidas.size());

		for (Proposta p : recebidas) {
			System.out.println(p);
		}
	}

	/**
	 * <p>Lista todas as propostas recebidas do jogador logado </p>
	 */
	private void listaPropostasRecebidas() {
		if (!isAutenticado()) return;

		List<Proposta> recebidas = jogadorLogado.getPropostasRecebidas();
		System.out.printf("%n%d propostas recebidas:%n", recebidas.size());
		for (Proposta p : recebidas) {
			System.out.println(p);
		}
	}

	/**
	 * <p>Lista todas as propostas realizadas em aberto do jogador logado </p>
	 */
	private void listaPropostasRealizadasAbertas() {
		if (!isAutenticado()) return;

		List<Proposta> realizadas = jogadorLogado.getPropostasRealizadas().stream().filter(p -> p.getStatus()
			.equals(StatusProposta.ABERTA)).toList();
		System.out.printf("%n%d propostas realizadas em aberto:%n", realizadas.size());

		for (Proposta p : realizadas) {
			System.out.println(p);
		}
	}

	/**
	 * <p>Lista todas as propostas realizadas do jogador logado </p>
	 */
	private void listaPropostasRealizadas() {
		if (!isAutenticado()) return;

		List<Proposta> realizadas = jogadorLogado.getPropostasRealizadas();
		System.out.printf("%n%d propostas realizadas:%n", realizadas.size());

		for (Proposta p : realizadas) {
			System.out.println(p);
		}
	}

	/**
	 * Permite ao jogador visualizar suas propostas recebidas em aberto,
	 * selecionar uma proposta da lista e aceitá-la ou recusá-la.
	 */
	private void handlePropostasRecebidas() {
		System.out.println("\n- Gerenciar propostas recebidas -");
		List<Proposta> propostas = jogadorLogado.getPropostasRecebidas().stream().filter(p -> p.getStatus()
			.equals(StatusProposta.ABERTA)).toList();
		int size = propostas.size();

		if (size == 0) {
			System.out.println("Nenhuma proposta em aberto!");
			return;
		}

		for (int i = 0; i < size; i++) {
			System.out.printf("[%2d]:%n%s%n", i + 1, propostas.get(i));
		}

		System.out.print("Selecione a proposta: ");
		int pIndex = Integer.parseInt(in.nextLine());

		if (pIndex < 1 || pIndex > size) {
			System.out.println("Valor inválido inserido!");
			return;
		}

		Proposta p = propostas.get(--pIndex);
		System.out.printf("%nSeleção: %s%n", p);
		System.out.print("""
			[1] Aceitar
			[2] Rejeitar
			[0] Voltar

			Escolha...\s""");
		int op = Integer.parseInt(in.nextLine());

		if (op < 1 || op > 2) {
			System.out.println("Operação cancelada!");
			return;
		}

		System.out.print("Esta operação não pode ser desfeita! Digite S para continuar... ");
		String input = in.nextLine();

		if (!input.equalsIgnoreCase("S")) {
			System.out.println("Operação cancelada!");
			return;
		}

		switch (op) {
			case 1 -> p.confirmar();
			case 2 -> p.recusar();
		}
	}

	/**
	 * <p>Método responsável por exibir informações gerais do sistema.</p>
	 * <p>Este método coleta e exibe no console diversas informações importantes
	 * sobre o sistema: - Total de usuários cadastrados. - Total de itens
	 * cadastrados e a soma de seus preços. - Quantidade de propostas de troca
	 * aceitas ou declinadas. - Quantidade de propostas de troca que estão
	 * aguardando uma resposta.</p>
	 */
	private void mostraInformacoesSistema() {
		int qtdUsuarios = jogadorHandler.totalJogadores();
		int qtdItens = itemHandler.totalItens();
		double precoTotalItens = itemHandler.precoTotal();
		double mediaPrecoItens = precoTotalItens / qtdItens;
		long propostasAbertas = propostaHandler.getNumeroPropostasAbertas();
		long propostasConfirmadas = propostaHandler.getNumeroPropostasConfirmadas();
		long propostasRecusadas = propostaHandler.getNumeroPropostasRecusadas();
		long totalPropostas = propostasAbertas + propostasConfirmadas + propostasRecusadas;


		System.out.printf("""

				- Informações do Sistema -
				> Quantidade de usuários cadastrados: %d
				> Quantidade de itens cadastrados: %d
				\t- Valor total dos itens: R$ %.2f
				\t- Média de preço dos itens: R$ %.2f
				> Quantidade de propostas no sistema: %d
				\t- Propostas em aberto: %d
				\t- Propostas fechadas: %d
				\t\t* Confirmadas: %d
				\t\t* Recusadas: %d%n""",
			qtdUsuarios,
			qtdItens,
			precoTotalItens,
			mediaPrecoItens,
			totalPropostas,
			propostasAbertas,
			propostasConfirmadas + propostasRecusadas,
			propostasConfirmadas,
			propostasRecusadas
		);
	}

	/**
	 * Método que deve ler arquivo contendo os dados que serão inseridos no
	 * sistema, instanciar os objetos e armazená-los corretamente para posterior
	 * uso na aplicação.
	 */
	private void insereDados() {
		redirecionaEntrada(CAMINHO_ARQUIVO_SEEDER);

		int qtdJogadores = Integer.parseInt(in.nextLine());
		for (int i = 0; i < qtdJogadores; i++) {
			String[] playerInfo = in.nextLine().split(",");
			Jogador j = new Jogador(playerInfo[0], playerInfo[1], playerInfo[2]);
			jogadorHandler.cadastra(j);
		}

		int qtdItens = Integer.parseInt(in.nextLine());
		for (int i = 0; i < qtdItens; i++) {
			String[] itemInfo = in.nextLine().split(",");
			int id = Integer.parseInt(itemInfo[0]);
			String nome = itemInfo[1];
			String descricao = itemInfo[2];
			String categoria = itemInfo[3];
			float preco = Float.parseFloat(itemInfo[4]);
			Jogador j = jogadorHandler.buscaPorEmail(itemInfo[5]);
			Item item = new Item(id, nome, descricao, categoria, preco, j);
			itemHandler.cadastra(item);
		}

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		int qtdPropostas = Integer.parseInt(in.nextLine());
		for (int i = 0; i < qtdPropostas; i++) {
			String[] propostaInfo = in.nextLine().split(",");
			Jogador solicitante = jogadorHandler.buscaPorEmail(propostaInfo[0]);
			Item itemSolicitante = itemHandler.buscaPorId(Integer.parseInt(propostaInfo[1]));

			Jogador solicitado = jogadorHandler.buscaPorEmail(propostaInfo[2]);
			Item itemSolicitado = itemHandler.buscaPorId(Integer.parseInt(propostaInfo[3]));

			LocalDateTime data = LocalDateTime.parse(propostaInfo[4], df);
			StatusProposta status = StatusProposta.valueOf(propostaInfo[5]);

			Proposta p = new Proposta(new DadosProposta(solicitante, itemSolicitante),
				new DadosProposta(solicitado, itemSolicitado),
				data,
				status
			);
			propostaHandler.cadastra(p);
		}

		restauraEntrada();
	}

	/**
	 * Redireciona a entrada de dados para o arquivo especificado. Ajusta o
	 * <code>Locale</code> para utilizar ponto decimal.
	 *
	 * @param arquivoEntrada Caminho do arquivo que será utilizado para a
	 *                       entrada.
	 */
	private void redirecionaEntrada(String arquivoEntrada) {
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader(arquivoEntrada));
			in = new Scanner(inputStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		Locale.setDefault(Locale.ENGLISH);
		in.useLocale(Locale.ENGLISH);
	}

	/**
	 * Restaura a entrada padrão para o teclado.
	 */
	private void restauraEntrada() {
		in = new Scanner(System.in);
	}
}
