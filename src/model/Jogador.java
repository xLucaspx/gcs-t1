package model;

import java.util.ArrayList;
import java.util.List;

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
 */

public class Jogador {
    private String nome;
    private String email;
    private String pin;
    private List<Item> itens;
    private List<Proposta> propostas;

    public Jogador(String nome, String email, String pin) {
        this.nome = nome;
        this.email = email;
        setPin(pin);
        this.itens = new ArrayList<>();
        this.propostas = new ArrayList<>();
    }

    /**
     * Verificação para o Pin apenas ser criado com 6 digitos numéricos
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
     * Adicionar itens na lista de itens
     *
     * @param item a ser adicionado
     */
    public boolean addItem(Item item) { //n contem esse parâmetro no diagrama
        return itens.add(item);
    }

    /**
     * Remover item da lista de itens
     *
     * @param item a ser removido
     */
    public void removeItem(Item item) { //n contem esse parâmetro no diagrama
        itens.remove(item);
    }

    /**
     * Retorna a lista de itens
     *
     * @return a lista com todos os itens
     */
    public List<Item> getItems() {
        return itens;
    }

    public List<Proposta> getPropostaRecebidas() {
        ArrayList<Proposta> recebidas = new ArrayList<>();
        for (Proposta p : propostas) {
            if (this.equals(p.getSolicitado().jogador())) {
                recebidas.add(p);
            }
        }
        return recebidas;
    }

    public List<Proposta> getPropostasRealizadas() {
        ArrayList<Proposta> realizadas = new ArrayList<Proposta>();
        for (Proposta p : propostas) {
            if (this.equals(p.getSolicitante().jogador())) {
                realizadas.add(p);
            }
        }
        return realizadas;
    }

    /**
     * @return as informações do jogador em formato de String.
     */
    @Override
    public String toString() {
        return "Nome: %s\nE-mail: %s".formatted(nome, email);
    }
}
