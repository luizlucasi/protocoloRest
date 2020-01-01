package com.telespazio.domino.web.rest;

import com.telespazio.domino.domain.TipoUsuario;
import com.telespazio.domino.service.TipoUsuarioService;
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
 * REST controller for managing {@link com.telespazio.domino.domain.TipoUsuario}.
 */
@RestController
@RequestMapping("/api")
public class TipoUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(TipoUsuarioResource.class);

    private static final String ENTITY_NAME = "tipoUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioResource(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    /**
     * {@code POST  /tipo-usuarios} : Create a new tipoUsuario.
     *
     * @param tipoUsuario the tipoUsuario to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoUsuario, or with status {@code 400 (Bad Request)} if the tipoUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-usuarios")
    public ResponseEntity<TipoUsuario> createTipoUsuario(@Valid @RequestBody TipoUsuario tipoUsuario) throws URISyntaxException {
        log.debug("REST request to save TipoUsuario : {}", tipoUsuario);
        if (tipoUsuario.getId() != null) {
            throw new BadRequestAlertException("A new tipoUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoUsuario result = tipoUsuarioService.save(tipoUsuario);
        return ResponseEntity.created(new URI("/api/tipo-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-usuarios} : Updates an existing tipoUsuario.
     *
     * @param tipoUsuario the tipoUsuario to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoUsuario,
     * or with status {@code 400 (Bad Request)} if the tipoUsuario is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoUsuario couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-usuarios")
    public ResponseEntity<TipoUsuario> updateTipoUsuario(@Valid @RequestBody TipoUsuario tipoUsuario) throws URISyntaxException {
        log.debug("REST request to update TipoUsuario : {}", tipoUsuario);
        if (tipoUsuario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoUsuario result = tipoUsuarioService.save(tipoUsuario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tipoUsuario.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-usuarios} : get all the tipoUsuarios.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoUsuarios in body.
     */
    @GetMapping("/tipo-usuarios")
    public List<TipoUsuario> getAllTipoUsuarios() {
        log.debug("REST request to get all TipoUsuarios");
        return tipoUsuarioService.findAll();
    }

    /**
     * {@code GET  /tipo-usuarios/:id} : get the "id" tipoUsuario.
     *
     * @param id the id of the tipoUsuario to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoUsuario, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-usuarios/{id}")
    public ResponseEntity<TipoUsuario> getTipoUsuario(@PathVariable Long id) {
        log.debug("REST request to get TipoUsuario : {}", id);
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoUsuario);
    }

    /**
     * {@code DELETE  /tipo-usuarios/:id} : delete the "id" tipoUsuario.
     *
     * @param id the id of the tipoUsuario to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-usuarios/{id}")
    public ResponseEntity<Void> deleteTipoUsuario(@PathVariable Long id) {
        log.debug("REST request to delete TipoUsuario : {}", id);
        tipoUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
