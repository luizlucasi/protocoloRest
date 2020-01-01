package com.telespazio.domino.service;

import com.telespazio.domino.domain.TipoProtocolo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TipoProtocolo}.
 */
public interface TipoProtocoloService {

    /**
     * Save a tipoProtocolo.
     *
     * @param tipoProtocolo the entity to save.
     * @return the persisted entity.
     */
    TipoProtocolo save(TipoProtocolo tipoProtocolo);

    /**
     * Get all the tipoProtocolos.
     *
     * @return the list of entities.
     */
    List<TipoProtocolo> findAll();


    /**
     * Get the "id" tipoProtocolo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoProtocolo> findOne(Long id);

    /**
     * Delete the "id" tipoProtocolo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
