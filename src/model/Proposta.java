package model;

import java.time.LocalDateTime;

public class Proposta {
	private DadosProposta solicitante;
	private DadosProposta solicitado;
	private LocalDateTime data;
	private StatusProposta status;


	public Proposta(DadosProposta solicitado, DadosProposta solicitante) {
		this.solicitado = solicitado;
		this.solicitante = solicitante;
		this.data = LocalDateTime.now();

	}
	public void setStatus(StatusProposta status) {
		this.status = status;
	}

	/**
	 * Mostra dados da proposta para o usuárop
	 * @return Retorna uma String com as as propostaas
	 */
	public String mostraProposta(){
		String proposta;
		proposta =
				"===============Solicitante============\n"+
				"Hora: "+ data.toString()+
				"\n" + solicitante.mostrarDadosProposta()+
				"===============Solicidtado============\n"
				+ solicitado.mostrarDadosProposta();

		return proposta;
	}


}
