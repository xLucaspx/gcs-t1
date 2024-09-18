package model;

public enum StatusProposta {
	ABERTA(1, "Em processamento"), CONFIRMADA(2, "Realizado com Sucesso"), RECUSADA(3, "Proposta Recusada");

	private final int codigo;
	private final String status;


	StatusProposta(int codigo, String status) {
		this.codigo = codigo;
		this.status = status;
	}

	public int getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return status;
	}
}
