package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.VersaoService;
import com.telespazio.domino.domain.Versao;
import com.telespazio.domino.repository.VersaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Versao}.
 */
@Service
@Transactional
public class VersaoServiceImpl implements VersaoService {

    private final Logger log = LoggerFactory.getLogger(VersaoServiceImpl.class);

    private final VersaoRepository versaoRepository;

    public VersaoServiceImpl(VersaoRepository versaoRepository) {
        this.versaoRepository = versaoRepository;
    }

    /**
     * Save a versao.
     *
     * @param versao the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Versao save(Versao versao) {
        log.debug("Request to save Versao : {}", versao);
        return versaoRepository.save(versao);
    }

    /**
     * Get all the versaos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Versao> findAll() {
        log.debug("Request to get all Versaos");
        return versaoRepository.findAll();
    }


    /**
     * Get one versao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Versao> findOne(Long id) {
        log.debug("Request to get Versao : {}", id);
        return versaoRepository.findById(id);
    }

    /**
     * Delete the versao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Versao : {}", id);
        versaoRepository.deleteById(id);
    }
}
