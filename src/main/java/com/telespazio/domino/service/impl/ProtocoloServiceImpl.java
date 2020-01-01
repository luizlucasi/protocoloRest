package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.ProtocoloService;
import com.telespazio.domino.domain.Protocolo;
import com.telespazio.domino.repository.ProtocoloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Protocolo}.
 */
@Service
@Transactional
public class ProtocoloServiceImpl implements ProtocoloService {

    private final Logger log = LoggerFactory.getLogger(ProtocoloServiceImpl.class);

    private final ProtocoloRepository protocoloRepository;

    public ProtocoloServiceImpl(ProtocoloRepository protocoloRepository) {
        this.protocoloRepository = protocoloRepository;
    }

    /**
     * Save a protocolo.
     *
     * @param protocolo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Protocolo save(Protocolo protocolo) {
        log.debug("Request to save Protocolo : {}", protocolo);
        return protocoloRepository.save(protocolo);
    }

    /**
     * Get all the protocolos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Protocolo> findAll(Pageable pageable) {
        log.debug("Request to get all Protocolos");
        return protocoloRepository.findAll(pageable);
    }


    /**
     * Get one protocolo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Protocolo> findOne(Long id) {
        log.debug("Request to get Protocolo : {}", id);
        return protocoloRepository.findById(id);
    }

    /**
     * Delete the protocolo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Protocolo : {}", id);
        protocoloRepository.deleteById(id);
    }
}
