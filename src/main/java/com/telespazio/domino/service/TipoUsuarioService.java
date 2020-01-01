package com.telespazio.domino.service;

import com.telespazio.domino.domain.TipoUsuario;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TipoUsuario}.
 */
public interface TipoUsuarioService {

    /**
     * Save a tipoUsuario.
     *
     * @param tipoUsuario the entity to save.
     * @return the persisted entity.
     */
    TipoUsuario save(TipoUsuario tipoUsuario);

    /**
     * Get all the tipoUsuarios.
     *
     * @return the list of entities.
     */
    List<TipoUsuario> findAll();


    /**
     * Get the "id" tipoUsuario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoUsuario> findOne(Long id);

    /**
     * Delete the "id" tipoUsuario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
