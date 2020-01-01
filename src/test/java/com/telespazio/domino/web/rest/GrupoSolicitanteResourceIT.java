package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.GrupoSolicitante;
import com.telespazio.domino.repository.GrupoSolicitanteRepository;
import com.telespazio.domino.service.GrupoSolicitanteService;
import com.telespazio.domino.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.telespazio.domino.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GrupoSolicitanteResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class GrupoSolicitanteResourceIT {

    private static final String DEFAULT_NOME_GRUPO_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOME_GRUPO_SOLICITANTE = "BBBBBBBBBB";

    @Autowired
    private GrupoSolicitanteRepository grupoSolicitanteRepository;

    @Autowired
    private GrupoSolicitanteService grupoSolicitanteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGrupoSolicitanteMockMvc;

    private GrupoSolicitante grupoSolicitante;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrupoSolicitanteResource grupoSolicitanteResource = new GrupoSolicitanteResource(grupoSolicitanteService);
        this.restGrupoSolicitanteMockMvc = MockMvcBuilders.standaloneSetup(grupoSolicitanteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrupoSolicitante createEntity(EntityManager em) {
        GrupoSolicitante grupoSolicitante = new GrupoSolicitante()
            .nomeGrupoSolicitante(DEFAULT_NOME_GRUPO_SOLICITANTE);
        return grupoSolicitante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrupoSolicitante createUpdatedEntity(EntityManager em) {
        GrupoSolicitante grupoSolicitante = new GrupoSolicitante()
            .nomeGrupoSolicitante(UPDATED_NOME_GRUPO_SOLICITANTE);
        return grupoSolicitante;
    }

    @BeforeEach
    public void initTest() {
        grupoSolicitante = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrupoSolicitante() throws Exception {
        int databaseSizeBeforeCreate = grupoSolicitanteRepository.findAll().size();

        // Create the GrupoSolicitante
        restGrupoSolicitanteMockMvc.perform(post("/api/grupo-solicitantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSolicitante)))
            .andExpect(status().isCreated());

        // Validate the GrupoSolicitante in the database
        List<GrupoSolicitante> grupoSolicitanteList = grupoSolicitanteRepository.findAll();
        assertThat(grupoSolicitanteList).hasSize(databaseSizeBeforeCreate + 1);
        GrupoSolicitante testGrupoSolicitante = grupoSolicitanteList.get(grupoSolicitanteList.size() - 1);
        assertThat(testGrupoSolicitante.getNomeGrupoSolicitante()).isEqualTo(DEFAULT_NOME_GRUPO_SOLICITANTE);
    }

    @Test
    @Transactional
    public void createGrupoSolicitanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grupoSolicitanteRepository.findAll().size();

        // Create the GrupoSolicitante with an existing ID
        grupoSolicitante.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrupoSolicitanteMockMvc.perform(post("/api/grupo-solicitantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSolicitante)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoSolicitante in the database
        List<GrupoSolicitante> grupoSolicitanteList = grupoSolicitanteRepository.findAll();
        assertThat(grupoSolicitanteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeGrupoSolicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = grupoSolicitanteRepository.findAll().size();
        // set the field null
        grupoSolicitante.setNomeGrupoSolicitante(null);

        // Create the GrupoSolicitante, which fails.

        restGrupoSolicitanteMockMvc.perform(post("/api/grupo-solicitantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSolicitante)))
            .andExpect(status().isBadRequest());

        List<GrupoSolicitante> grupoSolicitanteList = grupoSolicitanteRepository.findAll();
        assertThat(grupoSolicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGrupoSolicitantes() throws Exception {
        // Initialize the database
        grupoSolicitanteRepository.saveAndFlush(grupoSolicitante);

        // Get all the grupoSolicitanteList
        restGrupoSolicitanteMockMvc.perform(get("/api/grupo-solicitantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupoSolicitante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeGrupoSolicitante").value(hasItem(DEFAULT_NOME_GRUPO_SOLICITANTE)));
    }
    
    @Test
    @Transactional
    public void getGrupoSolicitante() throws Exception {
        // Initialize the database
        grupoSolicitanteRepository.saveAndFlush(grupoSolicitante);

        // Get the grupoSolicitante
        restGrupoSolicitanteMockMvc.perform(get("/api/grupo-solicitantes/{id}", grupoSolicitante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grupoSolicitante.getId().intValue()))
            .andExpect(jsonPath("$.nomeGrupoSolicitante").value(DEFAULT_NOME_GRUPO_SOLICITANTE));
    }

    @Test
    @Transactional
    public void getNonExistingGrupoSolicitante() throws Exception {
        // Get the grupoSolicitante
        restGrupoSolicitanteMockMvc.perform(get("/api/grupo-solicitantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrupoSolicitante() throws Exception {
        // Initialize the database
        grupoSolicitanteService.save(grupoSolicitante);

        int databaseSizeBeforeUpdate = grupoSolicitanteRepository.findAll().size();

        // Update the grupoSolicitante
        GrupoSolicitante updatedGrupoSolicitante = grupoSolicitanteRepository.findById(grupoSolicitante.getId()).get();
        // Disconnect from session so that the updates on updatedGrupoSolicitante are not directly saved in db
        em.detach(updatedGrupoSolicitante);
        updatedGrupoSolicitante
            .nomeGrupoSolicitante(UPDATED_NOME_GRUPO_SOLICITANTE);

        restGrupoSolicitanteMockMvc.perform(put("/api/grupo-solicitantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGrupoSolicitante)))
            .andExpect(status().isOk());

        // Validate the GrupoSolicitante in the database
        List<GrupoSolicitante> grupoSolicitanteList = grupoSolicitanteRepository.findAll();
        assertThat(grupoSolicitanteList).hasSize(databaseSizeBeforeUpdate);
        GrupoSolicitante testGrupoSolicitante = grupoSolicitanteList.get(grupoSolicitanteList.size() - 1);
        assertThat(testGrupoSolicitante.getNomeGrupoSolicitante()).isEqualTo(UPDATED_NOME_GRUPO_SOLICITANTE);
    }

    @Test
    @Transactional
    public void updateNonExistingGrupoSolicitante() throws Exception {
        int databaseSizeBeforeUpdate = grupoSolicitanteRepository.findAll().size();

        // Create the GrupoSolicitante

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrupoSolicitanteMockMvc.perform(put("/api/grupo-solicitantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoSolicitante)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoSolicitante in the database
        List<GrupoSolicitante> grupoSolicitanteList = grupoSolicitanteRepository.findAll();
        assertThat(grupoSolicitanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGrupoSolicitante() throws Exception {
        // Initialize the database
        grupoSolicitanteService.save(grupoSolicitante);

        int databaseSizeBeforeDelete = grupoSolicitanteRepository.findAll().size();

        // Delete the grupoSolicitante
        restGrupoSolicitanteMockMvc.perform(delete("/api/grupo-solicitantes/{id}", grupoSolicitante.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrupoSolicitante> grupoSolicitanteList = grupoSolicitanteRepository.findAll();
        assertThat(grupoSolicitanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
