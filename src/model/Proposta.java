package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Proposta {
	private DadosProposta solicitante;
	private DadosProposta solicitado;
	private LocalDateTime data;
	private StatusProposta status;
	private Item [] item;
	private Jogador[] jogador;

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
		item = new Item[2];
		this.item[0] = solicitante.item();
		this.item[1] = solicitado.item();
		jogador = new Jogador[2];
		jogador[0] = solicitante.jogador();
		jogador[1] = solicitado.jogador();
		proposta =
				"\n===============Solicitante============\n"+
				"Hora: "+ data.toString()+
				"\nJogador: " + jogador[0].mostraInformacoes() +
				"\n-------Item" + item[0].mostraInformacoes()+
				"\n===============Solicidtado============\n" +
				"\nJogador: " + jogador[1].mostraInformacoes() +
				"\n------Item" + item[1].mostraInformacoes()+
				"\n  ||||"+ status.mostrarStatus()+ "||||  \n";

		return proposta;
	}


}
