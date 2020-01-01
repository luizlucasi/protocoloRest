package com.telespazio.domino.web.rest;

import com.telespazio.domino.domain.Numeracao;
import com.telespazio.domino.service.NumeracaoService;
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
 * REST controller for managing {@link com.telespazio.domino.domain.Numeracao}.
 */
@RestController
@RequestMapping("/api")
public class NumeracaoResource {

    private final Logger log = LoggerFactory.getLogger(NumeracaoResource.class);

    private static final String ENTITY_NAME = "numeracao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NumeracaoService numeracaoService;

    public NumeracaoResource(NumeracaoService numeracaoService) {
        this.numeracaoService = numeracaoService;
    }

    /**
     * {@code POST  /numeracaos} : Create a new numeracao.
     *
     * @param numeracao the numeracao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new numeracao, or with status {@code 400 (Bad Request)} if the numeracao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/numeracaos")
    public ResponseEntity<Numeracao> createNumeracao(@Valid @RequestBody Numeracao numeracao) throws URISyntaxException {
        log.debug("REST request to save Numeracao : {}", numeracao);
        if (numeracao.getId() != null) {
            throw new BadRequestAlertException("A new numeracao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Numeracao result = numeracaoService.save(numeracao);
        return ResponseEntity.created(new URI("/api/numeracaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /numeracaos} : Updates an existing numeracao.
     *
     * @param numeracao the numeracao to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated numeracao,
     * or with status {@code 400 (Bad Request)} if the numeracao is not valid,
     * or with status {@code 500 (Internal Server Error)} if the numeracao couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/numeracaos")
    public ResponseEntity<Numeracao> updateNumeracao(@Valid @RequestBody Numeracao numeracao) throws URISyntaxException {
        log.debug("REST request to update Numeracao : {}", numeracao);
        if (numeracao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Numeracao result = numeracaoService.save(numeracao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, numeracao.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /numeracaos} : get all the numeracaos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of numeracaos in body.
     */
    @GetMapping("/numeracaos")
    public List<Numeracao> getAllNumeracaos() {
        log.debug("REST request to get all Numeracaos");
        return numeracaoService.findAll();
    }

    /**
     * {@code GET  /numeracaos/:id} : get the "id" numeracao.
     *
     * @param id the id of the numeracao to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the numeracao, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/numeracaos/{id}")
    public ResponseEntity<Numeracao> getNumeracao(@PathVariable Long id) {
        log.debug("REST request to get Numeracao : {}", id);
        Optional<Numeracao> numeracao = numeracaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(numeracao);
    }

    /**
     * {@code DELETE  /numeracaos/:id} : delete the "id" numeracao.
     *
     * @param id the id of the numeracao to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/numeracaos/{id}")
    public ResponseEntity<Void> deleteNumeracao(@PathVariable Long id) {
        log.debug("REST request to delete Numeracao : {}", id);
        numeracaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
