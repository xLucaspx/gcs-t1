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
  
  public boolean cadastra(Proposta p) {
		if (p == null) {
			return false;
		}
		return propostas.add(p);
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
	 * Retorna o número de propostas em aberto.
	 * Propostas são consideradas em aberto se o <em>status</em>
	 * for igual a <code>StatusProposta.ABERTA</code>.
	 *
	 * @return Número de propostas abertas
	 */
	public long getNumeroPropostasAbertas() {
		return propostas.stream().filter(p -> p.getStatus().equals(StatusProposta.ABERTA)).count();
	}
	public long getNumeroPropostasFinalizadas(){

		return getNumeroPropostasRecusadas()+getNumeroPropostasConfirmadas();
	}

	public List<Proposta> getPropostas() {
		return Collections.unmodifiableList(propostas);
	}
}
