package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import handler.JogadorHandler;

public class Jogador {

	private String nome;
	private String email;
	private String pin;
	private ArrayList<Item> itens;

	public Jogador(String nome, String email, String pin) {
		this.nome = nome;
		this.email = email;
		this.pin = pin;
	}

	public void addItem() {

	}

	public void removeItem() {

	}

	public List getItens() {
		return null;
	}

	public List getPropostasRecebidas() {
		return null;
	}

	public List getPropostasRealizadas() {
		return null;
	}

	/**
	 * Mostra as Informações do jogador
	 * @return Retorna uma String com as informações do jogador
	 */
	public String getInformacoes(){
		String informacoes = "Nome: " + this.nome +
				"\nEmail: " + this.email +
				"\nPIN: " + this.pin;
		return informacoes ;
	}
}
