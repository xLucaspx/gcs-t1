package model;

public class DadosProposta {
	private Jogador jogador;
	private Item item;
	private Proposta proposta;

	public DadosProposta(Jogador jogador, Item item, Proposta proposta) {
		this.jogador = jogador;
		this.item = item;
		this.proposta = proposta;
	}

	/**
	 * Mostra todos os dados das propostas do jogador e da carta em proposta
	 * @return Retorna uma String com os dados do jogador e o item
	 */
	public String mostrarDadosProposta() {
		String dados;
		dados = "-----------------------------------\nJogador:  " + jogador.getInformacoes() +
		"-----------------------------------\nItem:  " + item.mostraInformacoes();
		return dados;
	}
}
