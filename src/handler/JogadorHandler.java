package handler;

import model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class JogadorHandler {
	private final List<Jogador> jogadores;

	public JogadorHandler() {
		this.jogadores = new ArrayList<>();
	}

	public boolean cadastra(Jogador j) {
		if (j == null) {
			return false;
		}
		return jogadores.add(j);
	}

	public Jogador buscaPorEmail(String email) {
		for (Jogador j : jogadores) {
			if (j.getEmail().equals(email)) {
				return j;
			}
		}
		return null;
	}

	public int size() {
		return jogadores.size();
	}
}
