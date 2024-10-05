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
		this.email = email;
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
	 * Adiciona um item no <em>map</em> de itens.
	 *
	 * @param item O <code>Item</code> a ser adicionado.
	 */
	public void addItem(Item item) {
		itens.put(item.getId(), item);
	}

	/**
	 * Remove um item do <em>map</em> de itens.
	 *
	 * @param item O <code>Item</code> a ser removido.
	 */
	public void removeItem(Item item) {
		itens.remove(item.getId());
	}

	/**
	 * Retorna a lista de itens do jogador.
	 *
	 * @return Lista com todos os itens.
	 */
	public List<Item> getItems() {
		return List.copyOf(itens.values());
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
	 * @return as informações do jogador em formato de String.
	 */
	@Override
	public String toString() {
		return "Nome: %s\nE-mail: %s".formatted(nome, email);
	}
}
