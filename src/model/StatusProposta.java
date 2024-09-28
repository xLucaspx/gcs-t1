package model;

/**
 * @author Rodrigo Miotto Slongo
 */
public enum StatusProposta {
	ABERTA(1, "Em processamento"), CONFIRMADA(2, "Proposta aceita"), RECUSADA(3, "Proposta recusada");

	private final int codigo;
	private final String descricao;

	StatusProposta(int codigo, String status) {
		this.codigo = codigo;
		this.descricao = status;
	}

	public int getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
