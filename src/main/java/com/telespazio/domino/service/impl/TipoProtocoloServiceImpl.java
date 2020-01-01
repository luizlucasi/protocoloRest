package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.TipoProtocoloService;
import com.telespazio.domino.domain.TipoProtocolo;
import com.telespazio.domino.repository.TipoProtocoloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoProtocolo}.
 */
@Service
@Transactional
public class TipoProtocoloServiceImpl implements TipoProtocoloService {

    private final Logger log = LoggerFactory.getLogger(TipoProtocoloServiceImpl.class);

    private final TipoProtocoloRepository tipoProtocoloRepository;

    public TipoProtocoloServiceImpl(TipoProtocoloRepository tipoProtocoloRepository) {
        this.tipoProtocoloRepository = tipoProtocoloRepository;
    }

    /**
     * Save a tipoProtocolo.
     *
     * @param tipoProtocolo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoProtocolo save(TipoProtocolo tipoProtocolo) {
        log.debug("Request to save TipoProtocolo : {}", tipoProtocolo);
        return tipoProtocoloRepository.save(tipoProtocolo);
    }

    /**
     * Get all the tipoProtocolos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TipoProtocolo> findAll() {
        log.debug("Request to get all TipoProtocolos");
        return tipoProtocoloRepository.findAll();
    }


    /**
     * Get one tipoProtocolo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoProtocolo> findOne(Long id) {
        log.debug("Request to get TipoProtocolo : {}", id);
        return tipoProtocoloRepository.findById(id);
    }

    /**
     * Delete the tipoProtocolo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoProtocolo : {}", id);
        tipoProtocoloRepository.deleteById(id);
    }
}
