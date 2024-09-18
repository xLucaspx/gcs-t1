package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Proposta {
	private DadosProposta solicitante;
	private DadosProposta solicitado;
	private LocalDateTime data;
	private StatusProposta status;

	public Proposta(DadosProposta solicitado, DadosProposta solicitante) {
		this.solicitado = solicitado;
		this.solicitante = solicitante;
		this.data = LocalDateTime.now();
		status = StatusProposta.ABERTA;


	}

	/**
	 * Atualiza o Status da proposta
	 * @param status Atualmente o status da proposta
	 */
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
				"\n===============Solicitante============\n"+
				"Hora: "+ data.toString()+
				"\nJogador: " + solicitante.jogador().mostraInformacoes() +
				"\n-------Item" + solicitante.item().mostraInformacoes()+
				"\n===============Solicitado=============\n" +
				"\nJogador: " + solicitado.jogador().mostraInformacoes() +
				"\n------Item" + solicitado.item().mostraInformacoes()+
				"\n  ||||"+ status.mostrarStatus()+ "||||  \n";

		return proposta;
	}


}
