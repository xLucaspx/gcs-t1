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
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * <p>
 * Classe da aplicação.</p>
 * <p>
 * <strong>Restrições</strong>:</p>
 * <ul>
 * 	<li>A entrada de dados para popular o sistema na inicialização deverá ocorrer por
 * 	leitura de arquivo de texto;</li>
 * 	<li>As interações do usuário com o sistema deverão ocorrer por meio do console;</li>
 * 	<li>Toda entrada e saida de dados deve ocorrer apenas na classe <code>App</code>.</li>
 * </ul>
 *
 * @author Gabriel Domingues e Lucas da Paz
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
	private final PrintStream out = System.out;
	private Scanner in;

	public App() {
		jogadorHandler = new JogadorHandler();
		itemHandler = new ItemHandler();
		propostaHandler = new PropostaHandler();
	}

	/**
	 * Método que executa a aplicação.
	 */
	public void executar() {
		System.out.println("TODO: implementar métodos!");
		insereDados();
		listaItensJogador();
		buscaItensId();
		buscaItensNome();
		buscaItensDescricao();
		buscaItensCategoria();
		mostraInformacoesSistema();
	}

	/**
	 * Lista os itens de um jogador com base no email fornecido pelo usuário. O
	 * método solicita ao usuário que insira o email de um jogador. Caso o
	 * jogador seja encontrado, seus itens serão listados no console. Se o
	 * jogador não possuir itens ou se o email não corresponder a nenhum jogador
	 * cadastrado, uma mensagem adequada será exibida.
	 */
	private void listaItensJogador() {
		System.out.println("Digite o email do jogador o qual deseja buscar");
		String emailJogador = in.nextLine();
		Jogador jogador = jogadorHandler.buscaPorEmail(emailJogador);
		if (jogador == null) {
			System.out.println("Não existe nenhum jogador cadastrado com esse email.");
			return;
		}
		if (jogador.getItems().isEmpty()) {
			System.out.printf("O jogador com o email %s não possui itens.%n", emailJogador);
			return;
		}
		for (Item item : jogador.getItems()) {
			System.out.println(item);
		}
	}

	/**
	 * Método responsável por buscar um item pelo seu ID.
	 * <p>
	 * O método solicita ao usuário o ID do item que deseja buscar. Em seguida,
	 * realiza a busca no sistema utilizando o ID fornecido. Caso o item seja
	 * encontrado, suas informações serão exibidas. Caso contrário, uma mensagem
	 * informando que nenhum item foi encontrado é exibida.
	 */
	private void buscaItensId() {
		System.out.println("Digite o id do item o qual deseja buscar");
		int id = Integer.parseInt(in.nextLine());
		Item item = itemHandler.buscaPorId(id);
		if (item == null) {
			System.out.println("Não foi encontrado nenhum item com o id digitado.");
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
	private void buscaItensNome() {
		System.out.println("Digite o nome do item o qual deseja buscar");
		String nome = in.nextLine();
		List<Item> itensEncontrados = itemHandler.buscaPorNome(nome);
		if (itensEncontrados.isEmpty()) {
			System.out.println("Não foi encontrado nenhum item com o nome digitado.");
			return;
		}
		for (Item item : itensEncontrados) {
			System.out.println(item);
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
	private void buscaItensDescricao() {
		System.out.println("Digite a descrição do item o qual deseja buscar");
		String descricao = in.nextLine();
		List<Item> itensEncontrados = itemHandler.buscaPorDescricao(descricao);
		if (itensEncontrados.isEmpty()) {
			System.out.println("Não foi encontrado nenhum item com a descrição digitada.");
			return;
		}
		for (Item item : itensEncontrados) {
			System.out.println(item);
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
	private void buscaItensCategoria() {
		System.out.println("Digite a categoria do item o qual deseja buscar.");
		String categoria = in.nextLine();
		List<Item> itensEncontrados = itemHandler.buscaPorCategoria(categoria);
		if (itensEncontrados.isEmpty()) {
			System.out.println("Não foi encontrado nenhum item com a categoria digitada.");
			return;
		}
		for (Item item : itensEncontrados) {
			System.out.println(item);
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
		int totalUsuarios = jogadorHandler.size();
		int totalItens = itemHandler.size();
		int propostasFinalizadas = propostaHandler.getNumeroPropostasFinalizadas();
		int propostasEmAndamento = propostaHandler.getNumeroPropostasAbertas();
		float precoTotal = itemHandler.precoTotal();
		System.out.printf("O total de usuários é: %d%n", totalUsuarios);
		System.out.printf("O total de itens é: %d e a soma total de seus preços é: R$ %.2f%n", totalItens, precoTotal);
		System.out.printf("A quantidade de propostas de trocas aceitas/declinadas é: %d%n", propostasFinalizadas);
		System.out.printf("A quantidade total de propostas aguardando resposta é: %d%n", propostasEmAndamento);
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
			itemHandler.add(item);
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

	private void abreProposta(DadosProposta solicitante, DadosProposta solicitado){
		Proposta novaProposta = new Proposta(solicitante, solicitado);
		propostaHandler.getPropostas().add(novaProposta);

		System.out.println("A nova proposta com o jogador "+ novaProposta.getSolicitado()+" foi aberta com sucesso!");
		System.out.println("O jogador "+ novaProposta.getSolicitante()+ " enviou uma nova proposta de troca!");
	}
	private void propostaHandler(Proposta p){
		int count =0;
		boolean atualizaProposta = false;
		for(int i =0; i< propostaHandler.getPropostas().size(); i++){
			Proposta propostaExistente = propostaHandler.getPropostas().get(i);
			if(propostaExistente.getSolicitado().equals(p.getSolicitado())){
				propostaHandler.getPropostas().set(i,p);
				atualizaProposta = true;
				return;
			}
		}
		propostaHandler.getPropostas().add(p);


	}

}

