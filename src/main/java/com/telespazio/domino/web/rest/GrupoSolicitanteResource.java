package com.telespazio.domino.web.rest;

import com.telespazio.domino.domain.GrupoSolicitante;
import com.telespazio.domino.service.GrupoSolicitanteService;
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
 * REST controller for managing {@link com.telespazio.domino.domain.GrupoSolicitante}.
 */
@RestController
@RequestMapping("/api")
public class GrupoSolicitanteResource {

    private final Logger log = LoggerFactory.getLogger(GrupoSolicitanteResource.class);

    private static final String ENTITY_NAME = "grupoSolicitante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GrupoSolicitanteService grupoSolicitanteService;

    public GrupoSolicitanteResource(GrupoSolicitanteService grupoSolicitanteService) {
        this.grupoSolicitanteService = grupoSolicitanteService;
    }

    /**
     * {@code POST  /grupo-solicitantes} : Create a new grupoSolicitante.
     *
     * @param grupoSolicitante the grupoSolicitante to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grupoSolicitante, or with status {@code 400 (Bad Request)} if the grupoSolicitante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grupo-solicitantes")
    public ResponseEntity<GrupoSolicitante> createGrupoSolicitante(@Valid @RequestBody GrupoSolicitante grupoSolicitante) throws URISyntaxException {
        log.debug("REST request to save GrupoSolicitante : {}", grupoSolicitante);
        if (grupoSolicitante.getId() != null) {
            throw new BadRequestAlertException("A new grupoSolicitante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrupoSolicitante result = grupoSolicitanteService.save(grupoSolicitante);
        return ResponseEntity.created(new URI("/api/grupo-solicitantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grupo-solicitantes} : Updates an existing grupoSolicitante.
     *
     * @param grupoSolicitante the grupoSolicitante to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grupoSolicitante,
     * or with status {@code 400 (Bad Request)} if the grupoSolicitante is not valid,
     * or with status {@code 500 (Internal Server Error)} if the grupoSolicitante couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grupo-solicitantes")
    public ResponseEntity<GrupoSolicitante> updateGrupoSolicitante(@Valid @RequestBody GrupoSolicitante grupoSolicitante) throws URISyntaxException {
        log.debug("REST request to update GrupoSolicitante : {}", grupoSolicitante);
        if (grupoSolicitante.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GrupoSolicitante result = grupoSolicitanteService.save(grupoSolicitante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, grupoSolicitante.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /grupo-solicitantes} : get all the grupoSolicitantes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grupoSolicitantes in body.
     */
    @GetMapping("/grupo-solicitantes")
    public List<GrupoSolicitante> getAllGrupoSolicitantes() {
        log.debug("REST request to get all GrupoSolicitantes");
        return grupoSolicitanteService.findAll();
    }

    /**
     * {@code GET  /grupo-solicitantes/:id} : get the "id" grupoSolicitante.
     *
     * @param id the id of the grupoSolicitante to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grupoSolicitante, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grupo-solicitantes/{id}")
    public ResponseEntity<GrupoSolicitante> getGrupoSolicitante(@PathVariable Long id) {
        log.debug("REST request to get GrupoSolicitante : {}", id);
        Optional<GrupoSolicitante> grupoSolicitante = grupoSolicitanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grupoSolicitante);
    }

    /**
     * {@code DELETE  /grupo-solicitantes/:id} : delete the "id" grupoSolicitante.
     *
     * @param id the id of the grupoSolicitante to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grupo-solicitantes/{id}")
    public ResponseEntity<Void> deleteGrupoSolicitante(@PathVariable Long id) {
        log.debug("REST request to delete GrupoSolicitante : {}", id);
        grupoSolicitanteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
