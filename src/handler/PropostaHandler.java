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
	 * Retorna o número de propostas finalizadas pelo jogador.
	 * Propostas são consideradas finalizadas se o <em>status</em>
	 * for diferente de <code>ABERTA</code>.
	 *
	 * @return Número de propostas finalizadas.
	 */
	public int getNumeroPropostasFinalizadas() {
		int cont = 0;
		for (Proposta p : propostas) {
			if (!p.getStatus().equals(StatusProposta.ABERTA)) {
				cont++;
			}
		}
		return cont;
	}

	/**
	 * Retorna o número de propostas abertas pelo jogador.
	 * Propostas são consideradas abertas se o <em>status</em>
	 * for igual a <code>ABERTA</code>.
	 *
	 * @return Número de propostas abertas
	 */
	public int getNumeroPropostasAbertas() {
		int cont = 0;
		for (Proposta p : propostas) {
			if (p.getStatus().equals(StatusProposta.ABERTA)) {
				cont++;
			}
		}
		return cont;
	}

	public List<Proposta> getPropostas() {
		return Collections.unmodifiableList(propostas);
	}
}
