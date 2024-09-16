package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import handler.PropostaHandler;

public class Proposta {
	private DadosProposta solicitante;
	private DadosProposta solicitado;
	private LocalDateTime data;
	private Jogador jogador;
	private ArrayList<Jogador> jogador;
	private Item item;
	private StatusProposta statusProposta;
	private DadosProposta dadosProposta;
	private PropostaHandler propostaHandler;
	private ArrayList<Jogador> jogador;
	private ArrayList<Item> item;


	public void setStatus(StatusProposta status) {
	}

}
