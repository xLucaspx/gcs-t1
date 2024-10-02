/**
 * @author Mateus Tieppo
 */

import model.Proposta;
import java.util.ArrayList;
import java.util.List;

public class PropostaHandler {
    private List<Proposta> propostas;

    public PropostaHandler() {
        propostas = new ArrayList<>();
    }

    // Método para cadastrar uma nova proposta
    public boolean cadastra(Proposta p) {
        if (p != null) {
            propostas.add(p);
            return true;
        }
        return false;
    }
}
