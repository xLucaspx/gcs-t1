package handler;

import model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorHandler {
	List<Jogador> list;

	public JogadorHandler() {
		this.list = new ArrayList<>();
	}

	public boolean cadastra(Jogador j) {
		if (j == null) {
			return false;
		}
		return list.add(j);
	}

	public Jogador buscaPorEmail(String email) {
		for (Jogador j : list) {
			if (j.getEmail().equals(email)) {
				return j;
			}
		}
		return null;
	}
}
