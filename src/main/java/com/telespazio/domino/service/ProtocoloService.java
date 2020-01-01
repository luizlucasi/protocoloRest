package com.telespazio.domino.service;

import com.telespazio.domino.domain.Protocolo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Protocolo}.
 */
public interface ProtocoloService {

    /**
     * Save a protocolo.
     *
     * @param protocolo the entity to save.
     * @return the persisted entity.
     */
    Protocolo save(Protocolo protocolo);

    /**
     * Get all the protocolos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Protocolo> findAll(Pageable pageable);


    /**
     * Get the "id" protocolo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Protocolo> findOne(Long id);

    /**
     * Delete the "id" protocolo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
