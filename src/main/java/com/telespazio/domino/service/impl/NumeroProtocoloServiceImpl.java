package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.NumeroProtocoloService;
import com.telespazio.domino.domain.NumeroProtocolo;
import com.telespazio.domino.repository.NumeroProtocoloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link NumeroProtocolo}.
 */
@Service
@Transactional
public class NumeroProtocoloServiceImpl implements NumeroProtocoloService {

    private final Logger log = LoggerFactory.getLogger(NumeroProtocoloServiceImpl.class);

    private final NumeroProtocoloRepository numeroProtocoloRepository;

    public NumeroProtocoloServiceImpl(NumeroProtocoloRepository numeroProtocoloRepository) {
        this.numeroProtocoloRepository = numeroProtocoloRepository;
    }

    /**
     * Save a numeroProtocolo.
     *
     * @param numeroProtocolo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NumeroProtocolo save(NumeroProtocolo numeroProtocolo) {
        log.debug("Request to save NumeroProtocolo : {}", numeroProtocolo);
        return numeroProtocoloRepository.save(numeroProtocolo);
    }

    /**
     * Get all the numeroProtocolos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NumeroProtocolo> findAll() {
        log.debug("Request to get all NumeroProtocolos");
        return numeroProtocoloRepository.findAll();
    }


    /**
     * Get one numeroProtocolo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NumeroProtocolo> findOne(Long id) {
        log.debug("Request to get NumeroProtocolo : {}", id);
        return numeroProtocoloRepository.findById(id);
    }

    /**
     * Delete the numeroProtocolo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NumeroProtocolo : {}", id);
        numeroProtocoloRepository.deleteById(id);
    }
}
