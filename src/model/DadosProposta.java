package model;

/**
 * Record para armazenar dados de uma proposta.
 *
 * @param jogador <code>Jogador</code> envolvido na proposta.
 * @param item    <code>Item</code> do respectivo <code>Jogador</code>,
 *                também envolvido na proposta.
 * @author Rodrigo Miotto Slongo
 */
public record DadosProposta(Jogador jogador, Item item) {}
