package handler;

import model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>A classe ItemHandler é responsável por gerenciar de objetos do tipo
 * Item. Ela permite adicionar, remover e buscar itens com base em
 * diferentes critérios, como ID, nome, descrição e categoria.</p>
 * <p>Esta classe encapsula uma coleção de itens e fornece métodos para operar sobre
 * ela, garantindo que possa ser manipulada de maneira controlada e segura.</p>
 *
 * @author Gabriel Domingues, LucaWB
 */
public class ItemHandler {

	/**
	 * A coleção de itens armazenada por esta classe.
	 */
	private final Map<Integer, Item> itens;

	/**
	 * Construtor padrão que inicializa uma nova lista de itens.
	 */
	public ItemHandler() {
		this.itens = new HashMap<>();
	}

	/**
	 * Adiciona um item à lista, se o item não for nulo.
	 *
	 * @param i O item a ser adicionado.
	 */
	public void add(Item i) {
		if (i == null) {
			return;
		}
		itens.put(i.getId(), i);
	}

	/**
	 * Remove um item da lista, se o item não for nulo.
	 *
	 * @param i O item a ser removido.
	 */
	public void remove(Item i) {
		if (i == null) {
			return;
		}
		itens.remove(i.getId());
	}

	/**
	 * @return Lista contendo todos os itens cadastrados no sistema.
	 */
	public List<Item> getItens() {
		return List.copyOf(itens.values());
	}

	/**
	 * Busca um item com base no ID fornecido.
	 *
	 * @param id O ID do item a ser buscado.
	 * @return O <code>Item</code> correspondente ao ID fornecido, ou
	 * <code>null</code> se não for encontrado.
	 */
	public Item buscaPorId(int id) {
		return itens.get(id);
	}

	/**
	 * Busca todos os itens na lista que correspondem ao nome fornecido.
	 *
	 * @param nome O nome do item a ser buscado.
	 * @return Lista de itens contendo o nome fornecido. Se nenhum item for
	 * encontrado, retorna uma lista vazia.
	 */
	public List<Item> buscaPorNome(String nome) {
		nome = nome.toLowerCase();
		List<Item> itensEncontrados = new ArrayList<>();
		Collection<Item> list = itens.values();

		for (Item i : list) {
			if (i.getNome().toLowerCase().contains(nome)) {
				itensEncontrados.add(i);
			}
		}
		return Collections.unmodifiableList(itensEncontrados);
	}

	/**
	 * Busca todos os itens na lista que correspondem à descrição fornecida.
	 *
	 * @param descricao A descrição dos itens a serem buscados.
	 * @return Uma lista de itens que possuem a descrição fornecida. Se nenhum
	 * item for encontrado, retorna uma lista vazia.
	 */
	public List<Item> buscaPorDescricao(String descricao) {
		descricao = descricao.toLowerCase();
		List<Item> itensEncontrados = new ArrayList<>();
		Collection<Item> list = itens.values();

		for (Item i : list) {
			if (i.getDescricao().toLowerCase().contains(descricao)) {
				itensEncontrados.add(i);
			}
		}
		return Collections.unmodifiableList(itensEncontrados);
	}

	/**
	 * Busca todos os itens na lista que correspondem à categoria fornecida.
	 *
	 * @param categoria A categoria dos itens a serem buscados.
	 * @return Uma lista de itens que possuem a categoria fornecida. Se nenhum
	 * item for encontrado, retorna uma lista vazia.
	 */
	public List<Item> buscaPorCategoria(String categoria) {
		categoria = categoria.toLowerCase();
		List<Item> itensEncontrados = new ArrayList<>();
		Collection<Item> list = itens.values();

		for (Item i : list) {
			if (i.getCategoria().toLowerCase().contains(categoria)) {
				itensEncontrados.add(i);
			}
		}
		return Collections.unmodifiableList(itensEncontrados);
	}

	/**
	 * @return O total de itens cadastrados
	 */
	public int totalItens() {
		return itens.size();
	}

	public double precoTotal() {
		return itens.values().stream().mapToDouble(Item::getPreco).sum();
	}
}
