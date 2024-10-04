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

	/**
	 * Pega o codigo do status atual
	 * @return o codigo do atual da proposta
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Pega a descricao do status atual
	 * @return a descrição atual da proposta
	 */
	public String getDescricao() {return descricao;}
	@Override
	public String toString() {
		return descricao;
	}
}
