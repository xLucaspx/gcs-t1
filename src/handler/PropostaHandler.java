package handler;

import model.Proposta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Domingues
 */
public class PropostaHandler {

	private final List<Proposta> propostas;

	public PropostaHandler() {
		this.propostas = new ArrayList<>();
	}

	public int getNumeroPropostasFinalizadas() {
		// TODO: implementar método
		return 0;
	}

	public int getNumeroPropostasAbertas() {
		// TODO: implementar método
		return 0;
	}

	public boolean cadastra(Proposta p) {
		if (p == null) {
			return false;
		}
		return propostas.add(p);
	}
	public List<Proposta> getPropostas() {
		return propostas;
	}
}
