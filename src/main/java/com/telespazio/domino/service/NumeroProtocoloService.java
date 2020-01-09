package com.telespazio.domino.service;

import com.telespazio.domino.domain.NumeroProtocolo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link NumeroProtocolo}.
 */
public interface NumeroProtocoloService {

    /**
     * Save a numeroProtocolo.
     *
     * @param numeroProtocolo the entity to save.
     * @return the persisted entity.
     */
    NumeroProtocolo save(NumeroProtocolo numeroProtocolo);

    /**
     * Get all the numeroProtocolos.
     *
     * @return the list of entities.
     */
    List<NumeroProtocolo> findAll();


    /**
     * Get the "id" numeroProtocolo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NumeroProtocolo> findOne(Long id);

    /**
     * Delete the "id" numeroProtocolo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
