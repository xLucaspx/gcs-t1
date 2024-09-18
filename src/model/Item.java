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
	 * @return as informações do item em formato de String.
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
