package model;

/**
 * Classe que representa os itens dos jogadores.
 *
 * @author Gustavo Fidélis, Lucas da Paz, Rodrigo Slongo
 */
public class Item {
	private static int geradorId;

	private int id;
	private String nome;
	private String descricao;
	private String categoria;
	private float preco;
	private Jogador jogador;

	public Item(int id, String nome, String descricao, String categoria, float preco, Jogador jogador) {
		this.id = id;
		if (geradorId < id) geradorId = id;

		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
		this.jogador = jogador;
	}

	public Item(String nome, String descricao, String categoria, float preco, Jogador jogador) {
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
		this.jogador = jogador;

		this.id = ++geradorId;
	}

	public int getId() {
		return id;
	}

	public float getPreco(){
		return preco;
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
	 * @return O <code>Jogador</code> dono do item.
	 */
	public Jogador getJogador() {
		return jogador;
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
