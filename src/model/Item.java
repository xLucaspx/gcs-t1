package model;

import java.util.ArrayList;
import java.util.List;

public class Item {
  private static int gerador;
  
	private int id;
	private String nome;
	private String descricao;
	private String categoria;
	private float preco;

	public Item(int id, String nome, String descricao, String categoria, float preco) {
		this.id = id;
		if(gerador < id) gerador = id;
    
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
	}

	public Item(String nome, String descricao, String categoria, float preco){
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
    
		this.id = ++gerador;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	/**
	 * @return As informações do item em formato de String.
	 */
	@Override
	public String toString() {
		return "------\nId: %d\nNome: %s\nDescrição: %s\nCategoria: %s\nPreco: R$ %.2f".formatted(id,
			nome,
			descricao,
			categoria,
			preco
		);
	}
}
