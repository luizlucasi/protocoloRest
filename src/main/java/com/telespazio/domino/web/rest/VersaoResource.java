package com.telespazio.domino.web.rest;

import com.telespazio.domino.domain.Versao;
import com.telespazio.domino.service.VersaoService;
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
 * REST controller for managing {@link com.telespazio.domino.domain.Versao}.
 */
@RestController
@RequestMapping("/api")
public class VersaoResource {

    private final Logger log = LoggerFactory.getLogger(VersaoResource.class);

    private static final String ENTITY_NAME = "versao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VersaoService versaoService;

    public VersaoResource(VersaoService versaoService) {
        this.versaoService = versaoService;
    }

    /**
     * {@code POST  /versaos} : Create a new versao.
     *
     * @param versao the versao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new versao, or with status {@code 400 (Bad Request)} if the versao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/versaos")
    public ResponseEntity<Versao> createVersao(@Valid @RequestBody Versao versao) throws URISyntaxException {
        log.debug("REST request to save Versao : {}", versao);
        if (versao.getId() != null) {
            throw new BadRequestAlertException("A new versao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Versao result = versaoService.save(versao);
        return ResponseEntity.created(new URI("/api/versaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /versaos} : Updates an existing versao.
     *
     * @param versao the versao to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated versao,
     * or with status {@code 400 (Bad Request)} if the versao is not valid,
     * or with status {@code 500 (Internal Server Error)} if the versao couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/versaos")
    public ResponseEntity<Versao> updateVersao(@Valid @RequestBody Versao versao) throws URISyntaxException {
        log.debug("REST request to update Versao : {}", versao);
        if (versao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Versao result = versaoService.save(versao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, versao.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /versaos} : get all the versaos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of versaos in body.
     */
    @GetMapping("/versaos")
    public List<Versao> getAllVersaos() {
        log.debug("REST request to get all Versaos");
        return versaoService.findAll();
    }

    /**
     * {@code GET  /versaos/:id} : get the "id" versao.
     *
     * @param id the id of the versao to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the versao, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/versaos/{id}")
    public ResponseEntity<Versao> getVersao(@PathVariable Long id) {
        log.debug("REST request to get Versao : {}", id);
        Optional<Versao> versao = versaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(versao);
    }

    /**
     * {@code DELETE  /versaos/:id} : delete the "id" versao.
     *
     * @param id the id of the versao to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/versaos/{id}")
    public ResponseEntity<Void> deleteVersao(@PathVariable Long id) {
        log.debug("REST request to delete Versao : {}", id);
        versaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
