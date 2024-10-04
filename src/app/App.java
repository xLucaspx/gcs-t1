package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

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
 * @author Lucas da Paz
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

	private final PrintStream out = System.out;
	private Scanner in;

	/**
	 * Método que executa a aplicação.
	 */
	public void executar() {
		System.out.println("TODO: implementar métodos!");

		insereDados();
		int option = -1;

		while(option != 0){
			menu();
			option = Integer.parseInt(in.nextLine());
			switch(option){
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				default:
					break;
			}
		}
	}

	private void menu(){
		System.out.println(
				"""
				====================================================
				 1.		Cadastro
				 2.		Login
				 3.		Listar meus itens
				 4.		Listar todos os itens
				 5.		Buscar itens
				 6.		Fazer proposta
				 7.		Propostas realizadas
				 8.		Propostas recebidas
				 9.		Informações do sistema
				10.		Insere dados
				====================================================
				"""
		);
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
			System.out.printf("nome: %s, e-mail: %s, pin: %s%n", playerInfo[0], playerInfo[1], playerInfo[2]);
		}

		int qtdItens = Integer.parseInt(in.nextLine());
		System.out.printf("%d itens:%n", qtdItens);
		for (int i = 0; i < qtdItens; i++) {
			String[] itemInfo = in.nextLine().split(",");
			System.out.printf("id: %d, nome: %s, descrição, %s, categoria: %s, preço: R$ %.2f, e-mail jogador: %s%n",
				Integer.parseInt(itemInfo[0]),
				itemInfo[1],
				itemInfo[2],
				itemInfo[3],
				Float.parseFloat(itemInfo[4]),
				itemInfo[5]
			);
		}

		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		int qtdPropostas = Integer.parseInt(in.nextLine());
		System.out.printf("%d propostas:%n", qtdPropostas);
		for (int i = 0; i < qtdPropostas; i++) {
			String[] propostaInfo = in.nextLine().split(",");
			System.out.printf(
				"e-mail solicitante: %s, id item solicitante: %d, e-mail solicitado: %s, id item solicitado: %d, data: %s, status: %d%n",
				propostaInfo[0],
				Integer.parseInt(propostaInfo[1]),
				propostaInfo[2],
				Integer.parseInt(propostaInfo[3]),
				LocalDateTime.parse(propostaInfo[4], df).format(df),
				Integer.parseInt(propostaInfo[5])
			);
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
