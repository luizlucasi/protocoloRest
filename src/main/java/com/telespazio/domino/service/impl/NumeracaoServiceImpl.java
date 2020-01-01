package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.NumeracaoService;
import com.telespazio.domino.domain.Numeracao;
import com.telespazio.domino.repository.NumeracaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Numeracao}.
 */
@Service
@Transactional
public class NumeracaoServiceImpl implements NumeracaoService {

    private final Logger log = LoggerFactory.getLogger(NumeracaoServiceImpl.class);

    private final NumeracaoRepository numeracaoRepository;

    public NumeracaoServiceImpl(NumeracaoRepository numeracaoRepository) {
        this.numeracaoRepository = numeracaoRepository;
    }

    /**
     * Save a numeracao.
     *
     * @param numeracao the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Numeracao save(Numeracao numeracao) {
        log.debug("Request to save Numeracao : {}", numeracao);
        return numeracaoRepository.save(numeracao);
    }

    /**
     * Get all the numeracaos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Numeracao> findAll() {
        log.debug("Request to get all Numeracaos");
        return numeracaoRepository.findAll();
    }


    /**
     * Get one numeracao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Numeracao> findOne(Long id) {
        log.debug("Request to get Numeracao : {}", id);
        return numeracaoRepository.findById(id);
    }

    /**
     * Delete the numeracao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Numeracao : {}", id);
        numeracaoRepository.deleteById(id);
    }
}
