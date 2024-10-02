package app;

import handler.ItemHandler;
import handler.JogadorHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import model.DadosProposta;
import model.Item;
import model.Jogador;
import model.Proposta;
import model.StatusProposta;

/**
 * <p>Classe da aplicação.</p>
 * <p><strong>Restrições</strong>:</p>
 * <ul>
 *   <li>
 *     A entrada de dados para popular o sistema na inicialização deverá ocorrer por leitura de
 *     arquivo de texto;
 *   </li>
 *   <li>
 *     As interações do usuário com o sistema deverão ocorrer por meio do console;
 *   </li>
 *   <li>
 *     Toda entrada e saida de dados deve ocorrer apenas na classe <code>App</code>.
 *   </li>
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

	private final PrintStream out = System.out;
	private Scanner in;

	public App() {
		jogadorHandler = new JogadorHandler();
		itemHandler = new ItemHandler();
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
	}


	private void listaItensJogador(){
		String emailJogador;
		System.out.println("Digite o email do jogador o qual deseja buscar");
		emailJogador = in.nextLine();
		Jogador jogador = jogadorHandler.buscaPorEmail(emailJogador);
		if(jogador == null){
			System.out.println("Não existe nenhum jogador cadastrado com esse email.");
			return;
		}
		if(jogador.getItems().isEmpty()){
			System.out.println("O jogador com o email" + jogador.getEmail() + "não possui itens.");
		}else{
			for(Item item : jogador.getItems()){
				System.out.println(item.toString());
			}
		}

	}

	private void buscaItensId(){
		int id;
		System.out.println("Digite o id do item o qual deseja buscar.");
		id = Integer.parseInt(in.nextLine());
		Item item;
		item = itemHandler.buscaPorId(id);
		if(item == null){
			System.out.println("Não foi encontrado nenhum item com o id digitado.");
		}else{
			System.out.println(item.toString());
		}
	}

	private void buscaItensNome(){
		String nome;
		List<Item> itensEncontrados = new ArrayList<Item>();
		System.out.println("Digite o nome do item o qual deseja buscar.");
		nome = in.nextLine();
		itensEncontrados = itemHandler.buscaPorNome(nome);
		if(itensEncontrados.isEmpty()){
			System.out.println("Não foi encontrado nenhum item com o nome digitado.");
		}else{
			for(Item item : itensEncontrados){
				System.out.println(item.toString());
			}
		}
	}

	private void buscaItensDescricao(){
		String descricao;
		List<Item> itensEncontrados = new ArrayList<Item>();
		System.out.println("Digite a descrição do item o qual deseja buscar.");
		descricao = in.nextLine();
		itensEncontrados = itemHandler.buscaPorDescricao(descricao);
		if(itensEncontrados.isEmpty()){
			System.out.println("Não foi encontrado nenhum item com a descrição digitada.");
		}else{
			for(Item item : itensEncontrados){
				System.out.println(item.toString());
			}
		}
	}


	private void mostraInformacoesSistema(){

	}


	/**
	 * Método que deve ler arquivo contendo os dados que serão inseridos no sistema,
	 * instanciar os objetos e armazená-los corretamente para posterior uso na aplicação.
	 */
	private void insereDados() {
		redirecionaEntrada(CAMINHO_ARQUIVO_SEEDER);

		int qtdJogadores = Integer.parseInt(in.nextLine());
		System.out.printf("%d jogadores:%n", qtdJogadores);
		for (int i = 0; i < qtdJogadores; i++) {
			String[] playerInfo = in.nextLine().split(",");
			Jogador j = new Jogador(playerInfo[0], playerInfo[1], playerInfo[2]);
			jogadorHandler.cadastra(j);
			System.out.println(j);
		}

		int qtdItens = Integer.parseInt(in.nextLine());
		System.out.printf("%d itens:%n", qtdItens);
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
			System.out.println(item);
		}

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		int qtdPropostas = Integer.parseInt(in.nextLine());
		System.out.printf("%d propostas:%n", qtdPropostas);
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

			System.out.println(p);
		}

		restauraEntrada();
	}

	/**
	 * Redireciona a entrada de dados para o arquivo especificado. Ajusta o <code>Locale</code> para
	 * utilizar ponto decimal.
	 *
	 * @param arquivoEntrada Caminho do arquivo que será utilizado para a entrada.
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
