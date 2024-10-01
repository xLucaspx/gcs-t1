package handler;

import model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Classe responsável por gerenciar objetos do tipo <code>Item</code>.
 * Permite adicionar, remover e buscar itens com base em diferentes critérios,
 * como ID, nome, descrição e categoria.</p>
 * <p>Esta classe encapsula uma lista e fornece métodos para operar sobre ela,
 * garantindo que a mesma possa ser manipulada de maneira controlada e segura.</p>
 *
 * @author LucaWB
 */
public class ItemHandler {

	/**
	 * A lista de itens armazenada por esta classe. Todos os objetos do tipo
	 * Item são armazenados nesta lista.
	 */
	private final List<Item> itens;

	/**
	 * Construtor padrão que inicializa uma nova lista de itens.
	 */
	public ItemHandler() {
		this.itens = new ArrayList<>();
	}

	/**
	 * Adiciona um item à lista, se o item não for nulo.
	 *
	 * @param i O item a ser adicionado.
	 * @return <code>true</code> se o item foi adicionado com sucesso,
	 * <code>false</code> caso contrário.
	 */
	public boolean add(Item i) {
		if (i == null) {
			return false;
		}
		return itens.add(i);
	}

	/**
	 * Remove um item da lista, se o item não for nulo.
	 *
	 * @param i O item a ser removido.
	 * @return <code>true</code> se o item foi removido com sucesso,
	 * <code>false</code> caso contrário.
	 */
	public boolean remove(Item i) {
		if (i == null) {
			return false;
		}
		return itens.remove(i);
	}

	/**
	 * Busca um item na lista com base no ID fornecido.
	 *
	 * @param id O ID do item a ser buscado.
	 * @return O item correspondente ao ID fornecido, ou <code>null</code>
	 * se não for encontrado.
	 */
	public Item buscaPorId(int id) {
		for (Item i : itens) {
			if (i.getId() == id) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Busca um item na lista com base no nome fornecido.
	 *
	 * @param nome O nome do item a ser buscado.
	 * @return O item correspondente ao nome fornecido, ou <code>null</code>
	 * se não for encontrado.
	 */
	public Item buscaPorNome(String nome) {
		for (Item i : itens) {
			if (i.getNome().equals(nome)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Busca todos os itens na lista que correspondem à descrição fornecida.
	 *
	 * @param descricao A descrição dos itens a serem buscados.
	 * @return Uma lista de itens que possuem a descrição fornecida. Se nenhum
	 * item for encontrado, retorna uma lista vazia.
	 */
	public List<Item> buscaPorDescricao(String descricao) {
		List<Item> itensEncontrados = new ArrayList<>();
		for (Item i : itens) {
			if (i.getDescricao().equals(descricao)) {
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
		List<Item> itensEncontrados = new ArrayList<>();
		for (Item i : itens) {
			if (i.getCategoria().equals(categoria)) {
				itensEncontrados.add(i);
			}
		}
		return Collections.unmodifiableList(itensEncontrados);
	}

	public List<Item> getItens(){
		return Collections.unmodifiableList(itens);
	}
}
