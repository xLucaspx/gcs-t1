package model;


public class Item {
	private int id;
	private String nome;
	private String descricao;
	private String categoria;
	private float preco;

	public Item(int id, String nome, String descricao, String categoria, float preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
	}

	/**
	 * Mostra as informações dos itens
	 * @return Retorna uma String com as informação dos item
	 */
	public String mostraInformacoes(){
		String info = "------\n" +
				"Id: " + id +
				"\nNome: " + nome +
				"\nDescrição: " +descricao +
				"\nCategoraia: " + categoria +
				"\nPreco: " + preco;
		return info;
	}

}
