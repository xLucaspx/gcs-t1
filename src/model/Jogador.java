package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * O sistema deverá permitir que jogadores se inscrevam, cadastrem itens de jogos (físicos ou virtuais),
 * proponham e façam trocas de itens. Cada jogador se inscreve com seu email, nome completo e pin de 6
 * dígitos. Cada jogador poderá gerenciar seus itens (cadastrar ou excluir). Não será possível editar um item.
 * Cada item deverá ser composto de um id (gerado automaticamente pelo sistema), o nome do item, uma
 * descrição, um tipo (sejam criativos!) e um valor aproximado em Reais. Cada jogador poderá consultar
 * quaisquer itens dos demais jogadores. Qualquer jogador poderá abrir uma proposta de troca, indicando um
 * item seu e um item de algum outro jogador. Quando o outro jogador entrar no sistema, as propostas de
 * trocas que o envolvem devem aparecer, junto com opções de aceitar ou declinar a proposta. Aceitando, o
 * sistema automaticamente troca a propriedade dos dois itens.
 *
 * @author Lucas da Paz, Rodrigo Slongo, William Klein, Luca WB
 */

public class Jogador {
	private String nome;
	private String email;
	private String pin;
	private Map<Integer, Item> itens;
	private List<Proposta> propostas;

	public Jogador(String nome, String email, String pin) {
		this.nome = nome;
		setEmail(email);
		setPin(pin);
		this.itens = new HashMap<>();
		this.propostas = new ArrayList<>();
	}

	public boolean verificaPin(String pinDigitado) {
		return pinDigitado.equals(this.pin);
	}

	/**
	 * Verificação para o pin apenas ser criado com 6 dígitos numéricos
	 *
	 * @param pin a ser verificado
	 */
	private void setPin(String pin) {
		if (!pin.matches("\\d{6}")) {
			return;
		}
		this.pin = pin;
	}

	/**
	 * Adiciona um <code>Item</code> no <em>map</em> de itens. Garante que a
	 * instância de <code>Jogador</code> que chamou o método será
	 * atribuída ao respectivo atributo do <code>Item</code>.
	 *
	 * @param item O <code>Item</code> a ser adicionado.
	 */
	public void addItem(Item item) {
		item.setJogador(this);
		itens.put(item.getId(), item);
	}

	/**
	 * Remove um <code>Item</code> do <em>map</em> de itens.
	 * Define o atributo <code>Jogador</code> do <code>Item</code>
	 * para <code>null</code>.
	 *
	 * @param item O <code>Item</code> a ser removido.
	 */
	public void removeItem(Item item) {
		item.setJogador(null);

		// se o jogador não possui mais o item, as propostas que envolvem o item são canceladas
		DadosProposta d = new DadosProposta(this, item);
		for (Proposta p : propostas) {
			if (p.getSolicitante().equals(d) || p.getSolicitado().equals(d)) {
				p.recusar();
			}
		}

		itens.remove(item.getId());
	}

	/**
	 * Retorna a lista de itens do jogador.
	 *
	 * @return Lista com todos os itens.
	 */
	public List<Item> getItens() {
		return List.copyOf(itens.values());
	}

	/**
	 * Busca, na coleção do jogador, o <code>Item</code> com o ID
	 * passado como argumento.
	 *
	 * @param id O ID do <code>Item</code> buscado.
	 * @return O <code>Item</code> com ID correspondente, ou <code>null</code>
	 * caso não seja encontrado ID correspondente na coleção.
	 */
	public Item getItem(int id) {
		return itens.get(id);
	}

	public void addProposta(Proposta p) {
		propostas.add(p);
	}

	public List<Proposta> getPropostasRecebidas() {
		ArrayList<Proposta> recebidas = new ArrayList<>();
		for (Proposta p : propostas) {
			if (this.equals(p.getSolicitado().jogador())) {
				recebidas.add(p);
			}
		}
		return Collections.unmodifiableList(recebidas);
	}

	public List<Proposta> getPropostasRealizadas() {
		ArrayList<Proposta> realizadas = new ArrayList<>();
		for (Proposta p : propostas) {
			if (this.equals(p.getSolicitante().jogador())) {
				realizadas.add(p);
			}
		}
		return Collections.unmodifiableList(realizadas);
	}

	public String getEmail() {
		return email;
	}

	/**
	 * Verifica se o e-mail passado é válido e, caso positivo, define o atributo do jogador.
	 *
	 * @param email e-mail do jogador
	 */
	private void setEmail(String email) {
		if (!email.matches("^\\w+([.\\-]?\\w+)*@\\w+([.\\-]?\\w+)*(\\.\\w{2,3})+$")) {
			return;
		}
		this.email = email;
	}

	public String getNome() {
		return nome;
	}
	/**
	 * @return as informações do jogador em formato de String.
	 */
	@Override
	public String toString() {
		return "Usuário: %s\tE-mail: %s%n".formatted(nome, email);
	}
}
