package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Rodrigo Miotto Slongo
 */
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
	 * @return Dados do solicitado na proposta contendo o <code>Item</code>
	 * solicitado e o <code>Jogador</code> dono deste item.
	 */
	public DadosProposta getSolicitado() {
		return solicitado;
	}

	/**
	 * @return Dados do solicitante da proposta contendo o <code>Jogador</code>
	 * solicitante e o <code>Item</code> oferecido.
	 */
	public DadosProposta getSolicitante() {
		return solicitante;
	}

	/**
	 * Atualiza o <em>status</em> da proposta; apenas propostas em
	 * aberto terão o <em>status</em> atualizado.
	 *
	 * @param status O novo <code>StatusProposta</code>.
	 */
	public void setStatus(StatusProposta status) {
		if (!this.status.equals(StatusProposta.ABERTA)) {
			return;
		}
		this.status = status;
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
