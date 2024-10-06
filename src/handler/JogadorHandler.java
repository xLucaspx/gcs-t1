package handler;

import model.Jogador;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A classe JogadorHandler é responsável por gerenciar uma lista de objetos do
 * tipo
 * Jogador. Ela permite cadastrar jogadores e buscar por email.
 * </p>
 * <p>
 * Esta classe encapsula uma lista de jogadores e fornece métodos para operar
 * sobre
 * ela, garantindo que a lista possa ser manipulada de maneira controlada e
 * segura.
 * </p>
 *
 * @author Daniella Santos
 */
public class JogadorHandler {
    private final List<Jogador> jogadores;

    public JogadorHandler() {
        this.jogadores = new ArrayList<>();
    }

	/**
	 * Cadastra um jogador na lista com base no jogador fornecido.
	 *
	 * @param j O objeto jogador a ser inserido.
	 * @return confirmacao booleana de sucesso ou nao ao cadastrar um novo jogador
	 */
    public boolean cadastra(Jogador j) {
        if (j == null) {
            return false;
        }
        return jogadores.add(j);
    }
    
	/**
	 * Busca um jogador na lista com base no email fornecido.
	 *
	 * @param email O email do jogador a ser buscado.
	 * @return O jogador correspondente ao email fornecido, ou null se não for
	 * encontrado.
	 */
    public Jogador buscaPorEmail(String email) {
        for (Jogador j : jogadores) {
            if (j.getEmail().equals(email)) {
                return j;
            }
        }
        return null;
    }

    public int size() {
        return jogadores.size();
    }
}
