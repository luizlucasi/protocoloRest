package com.telespazio.domino.service;

import com.telespazio.domino.domain.Numeracao;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Numeracao}.
 */
public interface NumeracaoService {

    /**
     * Save a numeracao.
     *
     * @param numeracao the entity to save.
     * @return the persisted entity.
     */
    Numeracao save(Numeracao numeracao);

    /**
     * Get all the numeracaos.
     *
     * @return the list of entities.
     */
    List<Numeracao> findAll();


    /**
     * Get the "id" numeracao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Numeracao> findOne(Long id);

    /**
     * Delete the "id" numeracao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
