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

	/**
 * Retorna o número de propostas finalizadas pelo jogador.
 * Propostas são consideradas finalizadas se o status for "CONFIRMADA" ou "RECUSADA".
 *
 * @return Número de propostas finalizadas
 */
public int getNumeroPropostasFinalizadas() {
    int retorno = 0;
    for (Proposta p : propostas) {
        if (p.getStatus().equals("CONFIRMADA") || p.getStatus().equals("RECUSADA")) {
            retorno++;
        }
    }
    return retorno;
}

/**
 * Retorna o número de propostas abertas pelo jogador.
 * Propostas são consideradas abertas se o status for "ABERTA".
 *
 * @return Número de propostas abertas
 */
public int getNumeroPropostasAbertas() {
    int retorno = 0;
    for (Proposta p : propostas) {
        if (p.getStatus().equals("ABERTA")) {
            retorno++;
        }
    }
    return retorno;
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
