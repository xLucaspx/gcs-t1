package model;

/**
 * @author Lucas da Paz, Rodrigo Miotto Slongo
 */
public enum StatusProposta {
	ABERTA(1, "Em processamento"), CONFIRMADA(2, "Proposta aceita"), RECUSADA(3, "Proposta recusada"),
	CANCELADA(4, "Proposta cancelada");

	private final int codigo;
	private final String descricao;

	StatusProposta(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return O código numérico que representa o <em>status</em>.
	 */
	public int getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
