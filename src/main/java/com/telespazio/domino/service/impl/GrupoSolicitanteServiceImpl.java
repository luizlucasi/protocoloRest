package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.GrupoSolicitanteService;
import com.telespazio.domino.domain.GrupoSolicitante;
import com.telespazio.domino.repository.GrupoSolicitanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link GrupoSolicitante}.
 */
@Service
@Transactional
public class GrupoSolicitanteServiceImpl implements GrupoSolicitanteService {

    private final Logger log = LoggerFactory.getLogger(GrupoSolicitanteServiceImpl.class);

    private final GrupoSolicitanteRepository grupoSolicitanteRepository;

    public GrupoSolicitanteServiceImpl(GrupoSolicitanteRepository grupoSolicitanteRepository) {
        this.grupoSolicitanteRepository = grupoSolicitanteRepository;
    }

    /**
     * Save a grupoSolicitante.
     *
     * @param grupoSolicitante the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GrupoSolicitante save(GrupoSolicitante grupoSolicitante) {
        log.debug("Request to save GrupoSolicitante : {}", grupoSolicitante);
        return grupoSolicitanteRepository.save(grupoSolicitante);
    }

    /**
     * Get all the grupoSolicitantes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<GrupoSolicitante> findAll() {
        log.debug("Request to get all GrupoSolicitantes");
        return grupoSolicitanteRepository.findAll();
    }


    /**
     * Get one grupoSolicitante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GrupoSolicitante> findOne(Long id) {
        log.debug("Request to get GrupoSolicitante : {}", id);
        return grupoSolicitanteRepository.findById(id);
    }

    /**
     * Delete the grupoSolicitante by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GrupoSolicitante : {}", id);
        grupoSolicitanteRepository.deleteById(id);
    }
}
