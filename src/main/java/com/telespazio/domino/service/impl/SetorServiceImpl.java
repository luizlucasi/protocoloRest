package com.telespazio.domino.service.impl;

import com.telespazio.domino.service.SetorService;
import com.telespazio.domino.domain.Setor;
import com.telespazio.domino.repository.SetorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Setor}.
 */
@Service
@Transactional
public class SetorServiceImpl implements SetorService {

    private final Logger log = LoggerFactory.getLogger(SetorServiceImpl.class);

    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    /**
     * Save a setor.
     *
     * @param setor the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Setor save(Setor setor) {
        log.debug("Request to save Setor : {}", setor);
        return setorRepository.save(setor);
    }

    /**
     * Get all the setors.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Setor> findAll() {
        log.debug("Request to get all Setors");
        return setorRepository.findAll();
    }


    /**
     * Get one setor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Setor> findOne(Long id) {
        log.debug("Request to get Setor : {}", id);
        return setorRepository.findById(id);
    }

    /**
     * Delete the setor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Setor : {}", id);
        setorRepository.deleteById(id);
    }
}
