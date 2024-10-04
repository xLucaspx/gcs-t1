package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Rodrigo Miotto Slongo
 */
public class Proposta {
	private final DadosProposta solicitante;
	private final DadosProposta solicitado;
	private final LocalDateTime data;
	private StatusProposta status;

	public Proposta(DadosProposta solicitante, DadosProposta solicitado) {
		this.solicitante = solicitante;
		this.solicitado = solicitado;
		this.data = LocalDateTime.now();
		this.status = StatusProposta.ABERTA;
	}

	public Proposta(DadosProposta solicitante, DadosProposta solicitado, LocalDateTime data, StatusProposta status) {
		this.solicitante = solicitante;
		this.solicitado = solicitado;
		this.data = data;
		this.status = status;
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
	 * Pega o status atual da proposta
	 * @return o status atual em que esta a proposta
	 */
	public String getStatus(){
		return this.status.toString();
	}

	/**
	 * @return Os dados da proposta em formato de String.
	 */
	@Override
	public String toString() {
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
