package model;

import java.util.Collection;

public class Item {

	private int id;

	private String nome;

	private String descricao;

	private String categoria;

	private float preco;

	private Jogador jogador;

	private Proposta proposta;

	private DadosProposta dadosProposta;

	private Collection<Proposta> proposta;

	public Item(int id, String nome, String descricao, String categoria, float preco) {

	}

}
