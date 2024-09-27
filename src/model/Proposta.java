package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Proposta {
	private DadosProposta solicitante;
	private DadosProposta solicitado;
	private LocalDateTime data;
	private StatusProposta status;

	public Proposta(DadosProposta solicitado, DadosProposta solicitante) {
		this.solicitado = solicitado;
		this.solicitante = solicitante;
		this.data = LocalDateTime.now();
		this.status = StatusProposta.ABERTA;
	}

	/**
	 * Metodo get para acessar o solicitado
	 * @return solicitado da proposta
	 * @author: Rodrigo Miotto Slongo
	 */
	public DadosProposta getSolicitado() {
		return solicitado;
	}

	/**
	 * Método get para acessar o solicitante
	 * @return solocitante da proposta
	 * @author: Rodrigo Miotto Slongo
	 */
	public DadosProposta getSolicitante() {
		return solicitante;
	}

	/**
	 * Atualiza o status da proposta.
	 *
	 * @param status O novo <code>StatusProposta</code>.
	 */
	public void setStatus(StatusProposta status) {
		if (this.status.equals(StatusProposta.ABERTA)) {
			this.status = status;
		}
	}

	/**
	 * @return Os dados da proposta em formato de String.
	 */
	public String mostraProposta() {
		return """
			Data: %s
			Status: %s
			===============Solicitante============
			Jogador: %s
			Item: %s
			===============Solicitado=============
			Jogador: %s
			Item: %s
			""".formatted(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
			status,
			solicitante.jogador(),
			solicitante.item(),
			solicitado.jogador(),
			solicitado.item()
		);
	}


}
