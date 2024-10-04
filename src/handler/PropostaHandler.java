package handler;

import java.util.ArrayList;
import java.util.List;
import model.Proposta;

/**
 * @author Gabriel Domingues, Luca WB.
 */
public class PropostaHandler {

	private final List<Proposta> propostas;

	public PropostaHandler() {
		this.propostas = new ArrayList<>();
	}

	public int getNumeroPropostasFinalizadas() {
		int retorno = 0;
		for (Proposta p : propostas) {
			if (p.getStatus().equals("CONFIRMADA") || p.getStatus().equals("RECUSADA")) {
				retorno++;
			}
		}
		return retorno;
	}

	public int getNumeroPropostasAbertas() {
		int retorno = 0;
		for (Proposta p : propostas) {
			if (p.getStatus().equals("ABERTA")) {
				retorno++;
			}
		}
		return 0;
	}

	public boolean cadastra(Proposta p) {
		if (p == null) {
			return false;
		}
		return propostas.add(p);
	}
}
