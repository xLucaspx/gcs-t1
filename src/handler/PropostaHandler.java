package handler;

import java.util.ArrayList;
import java.util.List;
import model.Proposta;

public class PropostaHandler {

    private final List<Proposta> propostas;

    public PropostaHandler() {
        this.propostas = new ArrayList<Proposta>();
    }

    public int getNumeroPrpostasFinalizadas(){
        return 0;
    }
    public int getNumeroPropostasAbertas(){
        return 0;
    }

    public boolean cadastra(Proposta p) {
		if (p == null) {
			return false;
		}
		return propostas.add(p);
	}
}
