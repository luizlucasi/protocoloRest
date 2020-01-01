package com.telespazio.domino.service;

import com.telespazio.domino.domain.GrupoSolicitante;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link GrupoSolicitante}.
 */
public interface GrupoSolicitanteService {

    /**
     * Save a grupoSolicitante.
     *
     * @param grupoSolicitante the entity to save.
     * @return the persisted entity.
     */
    GrupoSolicitante save(GrupoSolicitante grupoSolicitante);

    /**
     * Get all the grupoSolicitantes.
     *
     * @return the list of entities.
     */
    List<GrupoSolicitante> findAll();


    /**
     * Get the "id" grupoSolicitante.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GrupoSolicitante> findOne(Long id);

    /**
     * Delete the "id" grupoSolicitante.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
