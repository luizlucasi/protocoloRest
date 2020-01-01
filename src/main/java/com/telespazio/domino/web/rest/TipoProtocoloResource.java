package com.telespazio.domino.web.rest;

import com.telespazio.domino.domain.TipoProtocolo;
import com.telespazio.domino.service.TipoProtocoloService;
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
 * REST controller for managing {@link com.telespazio.domino.domain.TipoProtocolo}.
 */
@RestController
@RequestMapping("/api")
public class TipoProtocoloResource {

    private final Logger log = LoggerFactory.getLogger(TipoProtocoloResource.class);

    private static final String ENTITY_NAME = "tipoProtocolo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoProtocoloService tipoProtocoloService;

    public TipoProtocoloResource(TipoProtocoloService tipoProtocoloService) {
        this.tipoProtocoloService = tipoProtocoloService;
    }

    /**
     * {@code POST  /tipo-protocolos} : Create a new tipoProtocolo.
     *
     * @param tipoProtocolo the tipoProtocolo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoProtocolo, or with status {@code 400 (Bad Request)} if the tipoProtocolo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-protocolos")
    public ResponseEntity<TipoProtocolo> createTipoProtocolo(@Valid @RequestBody TipoProtocolo tipoProtocolo) throws URISyntaxException {
        log.debug("REST request to save TipoProtocolo : {}", tipoProtocolo);
        if (tipoProtocolo.getId() != null) {
            throw new BadRequestAlertException("A new tipoProtocolo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoProtocolo result = tipoProtocoloService.save(tipoProtocolo);
        return ResponseEntity.created(new URI("/api/tipo-protocolos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-protocolos} : Updates an existing tipoProtocolo.
     *
     * @param tipoProtocolo the tipoProtocolo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoProtocolo,
     * or with status {@code 400 (Bad Request)} if the tipoProtocolo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoProtocolo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-protocolos")
    public ResponseEntity<TipoProtocolo> updateTipoProtocolo(@Valid @RequestBody TipoProtocolo tipoProtocolo) throws URISyntaxException {
        log.debug("REST request to update TipoProtocolo : {}", tipoProtocolo);
        if (tipoProtocolo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoProtocolo result = tipoProtocoloService.save(tipoProtocolo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tipoProtocolo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-protocolos} : get all the tipoProtocolos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoProtocolos in body.
     */
    @GetMapping("/tipo-protocolos")
    public List<TipoProtocolo> getAllTipoProtocolos() {
        log.debug("REST request to get all TipoProtocolos");
        return tipoProtocoloService.findAll();
    }

    /**
     * {@code GET  /tipo-protocolos/:id} : get the "id" tipoProtocolo.
     *
     * @param id the id of the tipoProtocolo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoProtocolo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-protocolos/{id}")
    public ResponseEntity<TipoProtocolo> getTipoProtocolo(@PathVariable Long id) {
        log.debug("REST request to get TipoProtocolo : {}", id);
        Optional<TipoProtocolo> tipoProtocolo = tipoProtocoloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoProtocolo);
    }

    /**
     * {@code DELETE  /tipo-protocolos/:id} : delete the "id" tipoProtocolo.
     *
     * @param id the id of the tipoProtocolo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-protocolos/{id}")
    public ResponseEntity<Void> deleteTipoProtocolo(@PathVariable Long id) {
        log.debug("REST request to delete TipoProtocolo : {}", id);
        tipoProtocoloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
