package handler;

import java.util.ArrayList;
import java.util.List;
import model.Jogador;

public class JogadorHandler {
    List<Jogador> list;

    public JogadorHandler() {
        this.list = new ArrayList<>();
    }

    public boolean cadastra(Jogador j) {
        if (j == null) {
            return false;
        }
        list.add(j);
        return true;
    }

    public Jogador buscaPorEmail(String email) {
        for (Jogador j : list) {
            if( j.getEmail.equals(email)) {
                return j;
            }
        }
        return null;
    }
}
