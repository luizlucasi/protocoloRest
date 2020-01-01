package com.telespazio.domino.web.rest;

import com.telespazio.domino.domain.Protocolo;
import com.telespazio.domino.service.ProtocoloService;
import com.telespazio.domino.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.telespazio.domino.domain.Protocolo}.
 */
@RestController
@RequestMapping("/api")
public class ProtocoloResource {

    private final Logger log = LoggerFactory.getLogger(ProtocoloResource.class);

    private static final String ENTITY_NAME = "protocolo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProtocoloService protocoloService;

    public ProtocoloResource(ProtocoloService protocoloService) {
        this.protocoloService = protocoloService;
    }

    /**
     * {@code POST  /protocolos} : Create a new protocolo.
     *
     * @param protocolo the protocolo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new protocolo, or with status {@code 400 (Bad Request)} if the protocolo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/protocolos")
    public ResponseEntity<Protocolo> createProtocolo(@Valid @RequestBody Protocolo protocolo) throws URISyntaxException {
        log.debug("REST request to save Protocolo : {}", protocolo);
        if (protocolo.getId() != null) {
            throw new BadRequestAlertException("A new protocolo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Protocolo result = protocoloService.save(protocolo);
        return ResponseEntity.created(new URI("/api/protocolos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /protocolos} : Updates an existing protocolo.
     *
     * @param protocolo the protocolo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated protocolo,
     * or with status {@code 400 (Bad Request)} if the protocolo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the protocolo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/protocolos")
    public ResponseEntity<Protocolo> updateProtocolo(@Valid @RequestBody Protocolo protocolo) throws URISyntaxException {
        log.debug("REST request to update Protocolo : {}", protocolo);
        if (protocolo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Protocolo result = protocoloService.save(protocolo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, protocolo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /protocolos} : get all the protocolos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of protocolos in body.
     */
    @GetMapping("/protocolos")
    public ResponseEntity<List<Protocolo>> getAllProtocolos(Pageable pageable) {
        log.debug("REST request to get a page of Protocolos");
        Page<Protocolo> page = protocoloService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /protocolos/:id} : get the "id" protocolo.
     *
     * @param id the id of the protocolo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the protocolo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/protocolos/{id}")
    public ResponseEntity<Protocolo> getProtocolo(@PathVariable Long id) {
        log.debug("REST request to get Protocolo : {}", id);
        Optional<Protocolo> protocolo = protocoloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(protocolo);
    }

    /**
     * {@code DELETE  /protocolos/:id} : delete the "id" protocolo.
     *
     * @param id the id of the protocolo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/protocolos/{id}")
    public ResponseEntity<Void> deleteProtocolo(@PathVariable Long id) {
        log.debug("REST request to delete Protocolo : {}", id);
        protocoloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
