package handler;

import model.Proposta;
import model.StatusProposta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gabriel Domingues, Luca WB, Lucas da Paz, Mateus Tieppo
 */
public class PropostaHandler {

	private final List<Proposta> propostas;

	public PropostaHandler() {
		this.propostas = new ArrayList<>();
	}

	/**
	 * Adiciona proposta a lista.
	 *
	 * @param p proposta a ser adicionada.
	 * @return verdadeiro se for executado com sucesso
	 */
	public boolean cadastra(Proposta p) {
		if (p == null) {
			return false;
		}
		return propostas.add(p);
	}

	/**
	 * Retorna o número de propostas em aberto.
	 * Propostas são consideradas em aberto se o <em>status</em>
	 * for igual a <code>StatusProposta.ABERTA</code>.
	 *
	 * @return Número de propostas abertas
	 */
	public long getNumeroPropostasAbertas() {
		return propostas.stream().filter(p -> p.getStatus().equals(StatusProposta.ABERTA)).count();
	}

	/**
	 * Retorna o número de propostas confirmadas pelos jogadores.
	 * Propostas são consideradas confirmadas se o <em>status</em>
	 * for igual a <code>StatusProposta.CONFIRMADA</code>.
	 *
	 * @return Número de propostas confirmadas.
	 */
	public long getNumeroPropostasConfirmadas() {
		return propostas.stream().filter(p -> p.getStatus().equals(StatusProposta.CONFIRMADA)).count();
	}

	/**
	 * Retorna o número de propostas recusadas pelos jogadores.
	 * Propostas são consideradas recusadas se o <em>status</em>
	 * for igual a <code>StatusProposta.RECUSADA</code>.
	 *
	 * @return Número de propostas recusadas.
	 */
	public long getNumeroPropostasRecusadas() {
		return propostas.stream().filter(p -> p.getStatus().equals(StatusProposta.RECUSADA)).count();
	}

	/**
	 * Retorna o número de propostas canceladas pelos jogadores.
	 * Propostas são consideradas canceladas se o <em>status</em>
	 * for igual a <code>StatusProposta.CANCELADA</code>.
	 *
	 * @return Número de propostas canceladas.
	 */
	public long getNumeroPropostasCanceladas() {
		return propostas.stream().filter(p -> p.getStatus().equals(StatusProposta.CANCELADA)).count();
	}

	/**
	 * Pega as propostas existentes na classe.
	 *
	 * @return lista de proposta da classe.
	 */
	public List<Proposta> getPropostas() {
		return Collections.unmodifiableList(propostas);
	}
}
