package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.TipoUsuarioService;
import com.telespazio.domino.domain.TipoUsuario;
import com.telespazio.domino.repository.TipoUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoUsuario}.
 */
@Service
@Transactional
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final Logger log = LoggerFactory.getLogger(TipoUsuarioServiceImpl.class);

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioServiceImpl(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    /**
     * Save a tipoUsuario.
     *
     * @param tipoUsuario the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoUsuario save(TipoUsuario tipoUsuario) {
        log.debug("Request to save TipoUsuario : {}", tipoUsuario);
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    /**
     * Get all the tipoUsuarios.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TipoUsuario> findAll() {
        log.debug("Request to get all TipoUsuarios");
        return tipoUsuarioRepository.findAll();
    }


    /**
     * Get one tipoUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoUsuario> findOne(Long id) {
        log.debug("Request to get TipoUsuario : {}", id);
        return tipoUsuarioRepository.findById(id);
    }

    /**
     * Delete the tipoUsuario by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoUsuario : {}", id);
        tipoUsuarioRepository.deleteById(id);
    }
}
