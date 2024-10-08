package handler;

import model.Jogador;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>A classe JogadorHandler é responsável por gerenciar uma lista de objetos do
 * tipo Jogador. Ela permite cadastrar jogadores e buscar por email.</p>
 * <p>Esta classe encapsula uma lista de jogadores e fornece métodos para operar
 * sobre ela, garantindo que a lista possa ser manipulada de maneira controlada e
 * segura.</p>
 *
 * @author Daniella Santos
 */
public class JogadorHandler {
	private final List<Jogador> jogadores;

	public JogadorHandler() {
		this.jogadores = new ArrayList<>();
	}

	/**
	 * Cadastra um <code>Jogador</code>.
	 *
	 * @param j O <code>Jogador</code> a ser cadastrado.
	 * @return <code>true</code> em caso de sucesso, <code>caso contrário</code>.
	 */
	public boolean cadastra(Jogador j) {
		if (j == null) {
			return false;
		}
		return jogadores.add(j);
	}

	/**
	 * Busca um <code>Jogador</code> com base no e-mail fornecido.
	 *
	 * @param email O e-mail do <code>Jogador</code> buscado.
	 * @return O <code>Jogador</code> correspondente ao email fornecido,
	 * ou <code>null</code> se não for encontrado.
	 */
	public Jogador buscaPorEmail(String email) {
		for (Jogador j : jogadores) {
			if (j.getEmail().equals(email)) {
				return j;
			}
		}
		return null;
	}

	/**
	 * @return O total de jogadores cadastrados.
	 */
	public int totalJogadores() {
		return jogadores.size();
	}
}
