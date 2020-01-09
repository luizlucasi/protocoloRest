package com.telespazio.domino.web.rest;

import com.telespazio.domino.domain.NumeroProtocolo;
import com.telespazio.domino.service.NumeroProtocoloService;
import com.telespazio.domino.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.telespazio.domino.domain.NumeroProtocolo}.
 */
@RestController
@RequestMapping("/api")
public class NumeroProtocoloResource {

    private final Logger log = LoggerFactory.getLogger(NumeroProtocoloResource.class);

    private static final String ENTITY_NAME = "numeroProtocolo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NumeroProtocoloService numeroProtocoloService;

    public NumeroProtocoloResource(NumeroProtocoloService numeroProtocoloService) {
        this.numeroProtocoloService = numeroProtocoloService;
    }

    /**
     * {@code POST  /numero-protocolos} : Create a new numeroProtocolo.
     *
     * @param numeroProtocolo the numeroProtocolo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new numeroProtocolo, or with status {@code 400 (Bad Request)} if the numeroProtocolo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/numero-protocolos")
    public ResponseEntity<NumeroProtocolo> createNumeroProtocolo(@Valid @RequestBody NumeroProtocolo numeroProtocolo) throws URISyntaxException {
        log.debug("REST request to save NumeroProtocolo : {}", numeroProtocolo);
        if (numeroProtocolo.getId() != null) {
            throw new BadRequestAlertException("A new numeroProtocolo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NumeroProtocolo result = numeroProtocoloService.save(numeroProtocolo);
        return ResponseEntity.created(new URI("/api/numero-protocolos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /numero-protocolos} : Updates an existing numeroProtocolo.
     *
     * @param numeroProtocolo the numeroProtocolo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated numeroProtocolo,
     * or with status {@code 400 (Bad Request)} if the numeroProtocolo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the numeroProtocolo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/numero-protocolos")
    public ResponseEntity<NumeroProtocolo> updateNumeroProtocolo(@Valid @RequestBody NumeroProtocolo numeroProtocolo) throws URISyntaxException {
        log.debug("REST request to update NumeroProtocolo : {}", numeroProtocolo);
        if (numeroProtocolo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NumeroProtocolo result = numeroProtocoloService.save(numeroProtocolo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, numeroProtocolo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /numero-protocolos} : get all the numeroProtocolos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of numeroProtocolos in body.
     */
    @GetMapping("/numero-protocolos")
    public List<NumeroProtocolo> getAllNumeroProtocolos() {
        log.debug("REST request to get all NumeroProtocolos");
        return numeroProtocoloService.findAll();
    }

    /**
     * {@code GET  /numero-protocolos/:id} : get the "id" numeroProtocolo.
     *
     * @param id the id of the numeroProtocolo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the numeroProtocolo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/numero-protocolos/{id}")
    public ResponseEntity<NumeroProtocolo> getNumeroProtocolo(@PathVariable Long id) {
        log.debug("REST request to get NumeroProtocolo : {}", id);
        Optional<NumeroProtocolo> numeroProtocolo = numeroProtocoloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(numeroProtocolo);
    }

    /**
     * {@code DELETE  /numero-protocolos/:id} : delete the "id" numeroProtocolo.
     *
     * @param id the id of the numeroProtocolo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/numero-protocolos/{id}")
    public ResponseEntity<Void> deleteNumeroProtocolo(@PathVariable Long id) {
        log.debug("REST request to delete NumeroProtocolo : {}", id);
        numeroProtocoloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
