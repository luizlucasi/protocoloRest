package com.telespazio.domino.service;

import com.telespazio.domino.domain.Versao;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Versao}.
 */
public interface VersaoService {

    /**
     * Save a versao.
     *
     * @param versao the entity to save.
     * @return the persisted entity.
     */
    Versao save(Versao versao);

    /**
     * Get all the versaos.
     *
     * @return the list of entities.
     */
    List<Versao> findAll();


    /**
     * Get the "id" versao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Versao> findOne(Long id);

    /**
     * Delete the "id" versao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
