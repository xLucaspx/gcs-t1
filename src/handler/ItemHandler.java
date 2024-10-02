package handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Item;

/**
 * A classe ItemHandler é responsável por gerenciar uma lista de objetos do tipo
 * Item. Ela permite adicionar, remover e buscar itens na lista com base em
 * diferentes critérios, como ID, nome, descrição e categoria.
 *
 * Esta classe encapsula uma lista de itens e fornece métodos para operar sobre
 * ela, garantindo que a lista possa ser manipulada de maneira controlada e
 * segura.
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
     * @return true se o item foi adicionado com sucesso, false se o item é
     * nulo.
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
     * @return true se o item foi removido com sucesso, false se o item é nulo.
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
     * @return O item correspondente ao ID fornecido, ou null se não for
     * encontrado.
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
     * Busca todos os itens na lista que correspondem ao nome fornecido.
     *
     * @param nome O nome do item a ser buscado.
     * @return Lista de itens contendo o nome fornecido. Se nenhum item for
     * encontrado, retorna uma lista vazia.
     */
    public List<Item> buscaPorNome(String nome) {
        List<Item> itensEncontrados = new ArrayList<>();
        nome = nome.toLowerCase();
        for (Item i : itens) {
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
        List<Item> itensEncontrados = new ArrayList<>();
        descricao = descricao.toLowerCase();
        for (Item i : itens) {
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
        List<Item> itensEncontrados = new ArrayList<>();
        categoria = categoria.toLowerCase();
        for (Item i : itens) {
            if (i.getCategoria().toLowerCase().contains(categoria)) {
                itensEncontrados.add(i);
            }
        }
        return Collections.unmodifiableList(itensEncontrados);
    }
    public int size() {
		return itens.size();
	}

    public float precoTotal(){
        float total = 0;
        for(Item item : itens){
            total = total + item.getPreco();
        }
        return total;
    }
}
