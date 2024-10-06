package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Lucas da Paz, Rodrigo Miotto Slongo
 */
public class Proposta {
	private final DadosProposta solicitante;
	private final DadosProposta solicitado;
	private final LocalDateTime data;
	private StatusProposta status;

	public Proposta(DadosProposta solicitante, DadosProposta solicitado) {
		this(solicitante, solicitado, LocalDateTime.now(), StatusProposta.ABERTA);
	}

	public Proposta(DadosProposta solicitante, DadosProposta solicitado, LocalDateTime data, StatusProposta status) {
		this.solicitante = solicitante;
		this.solicitado = solicitado;
		this.data = data;
		this.status = status;

		solicitante.jogador().addProposta(this);
		solicitado.jogador().addProposta(this);
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
	 * @return O <em>status</em> da proposta.
	 */
	public StatusProposta getStatus() {
		return this.status;
	}

	/**
	 * Atualiza o <em>status</em> da <code>Proposta</code> para <code>CONFIRMADA</code>
	 * e realiza a troca dos itens entre os jogadores envolvidos.
	 */
	public void confirmar() {
		if (!this.status.equals(StatusProposta.ABERTA)) {
			return;
		}
		Jogador jSolicitante = solicitante.jogador();
		Item iSolicitante = solicitante.item();

		Jogador jSolicitado = solicitado.jogador();
		Item iSolicitado = solicitado.item();

		jSolicitante.removeItem(iSolicitante);
		jSolicitante.addItem(iSolicitado);

		jSolicitado.removeItem(iSolicitado);
		jSolicitado.addItem(iSolicitante);

		this.status = StatusProposta.CONFIRMADA;
	}

	/**
	 * Define o <em>status</em> da <code>Proposta</code>
	 * para <code>RECUSADA</code>.
	 */
	public void recusar() {
		if (!this.status.equals(StatusProposta.ABERTA)) {
			return;
		}

		this.status = StatusProposta.RECUSADA;
	}
	public StatusProposta getStatus(){
		return status;
	}
	public void aceitaProposta(){
		boolean aceitou = false;
		boolean aberta = true;
		if(this.status.equals(StatusProposta.ABERTA)){
			aceitou = true;
			aberta = false;
			System.out.println("Proposta aceita com sucesso!");
		}else{
			System.out.println("Não foi possível aceitar a proposta foi ela foi fechada");
		}
	}
	public void recusaProposta(){
		boolean aceitou = false;
		boolean aberta = true;
		if(this.status.equals(StatusProposta.ABERTA)){
			aceitou = false;
			aberta = false;
			System.out.println("Proposta recusada!");
		}else{
			System.out.println("Não foi possível recusar a proposta pois ela já foi fechada");
		}
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
