package model;

public enum StatusProposta {ABERTA(1,"Em processamento"), CONFIRMADA(2,"Realizado com Sucesso"), RECUSADA(3,"Proposta Recusada");
	//declaração de vairavel
	private final String status;
	private final int codigo;
	//contrutor
	private StatusProposta(int codigo, String status) {
		this.codigo = codigo;
		this.status = status;
	}
	public String mostrarStatus() {

		return status;
	}

}
