package com.telespazio.domino.service;

import com.telespazio.domino.domain.Setor;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Setor}.
 */
public interface SetorService {

    /**
     * Save a setor.
     *
     * @param setor the entity to save.
     * @return the persisted entity.
     */
    Setor save(Setor setor);

    /**
     * Get all the setors.
     *
     * @return the list of entities.
     */
    List<Setor> findAll();


    /**
     * Get the "id" setor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Setor> findOne(Long id);

    /**
     * Delete the "id" setor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
