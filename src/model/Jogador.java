package model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	private String nome;
	private String email;
	private String pin;
	private List<Item> itens;

	public Jogador(String nome, String email, String pin) {
		this.nome = nome;
		this.email = email;
		this.pin = pin;
		this.itens = new ArrayList<Item>();
	}

	/**
	 * @return as informações do jogador em formato de String.
	 */
	@Override
	public String toString() {
		return "Nome: %s\nE-mail: %s".formatted(nome, email);
	}
}
