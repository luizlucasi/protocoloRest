package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.TipoProtocolo;
import com.telespazio.domino.repository.TipoProtocoloRepository;
import com.telespazio.domino.service.TipoProtocoloService;
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
 * Integration tests for the {@link TipoProtocoloResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class TipoProtocoloResourceIT {

    private static final String DEFAULT_NOME_PROTOCOLO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_PROTOCOLO = "BBBBBBBBBB";

    private static final String DEFAULT_COD_PROTOCOLO = "AAAAAAAAAA";
    private static final String UPDATED_COD_PROTOCOLO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private TipoProtocoloRepository tipoProtocoloRepository;

    @Autowired
    private TipoProtocoloService tipoProtocoloService;

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

    private MockMvc restTipoProtocoloMockMvc;

    private TipoProtocolo tipoProtocolo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoProtocoloResource tipoProtocoloResource = new TipoProtocoloResource(tipoProtocoloService);
        this.restTipoProtocoloMockMvc = MockMvcBuilders.standaloneSetup(tipoProtocoloResource)
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
    public static TipoProtocolo createEntity(EntityManager em) {
        TipoProtocolo tipoProtocolo = new TipoProtocolo()
            .nomeProtocolo(DEFAULT_NOME_PROTOCOLO)
            .codProtocolo(DEFAULT_COD_PROTOCOLO)
            .isActive(DEFAULT_IS_ACTIVE);
        return tipoProtocolo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoProtocolo createUpdatedEntity(EntityManager em) {
        TipoProtocolo tipoProtocolo = new TipoProtocolo()
            .nomeProtocolo(UPDATED_NOME_PROTOCOLO)
            .codProtocolo(UPDATED_COD_PROTOCOLO)
            .isActive(UPDATED_IS_ACTIVE);
        return tipoProtocolo;
    }

    @BeforeEach
    public void initTest() {
        tipoProtocolo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoProtocolo() throws Exception {
        int databaseSizeBeforeCreate = tipoProtocoloRepository.findAll().size();

        // Create the TipoProtocolo
        restTipoProtocoloMockMvc.perform(post("/api/tipo-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoProtocolo)))
            .andExpect(status().isCreated());

        // Validate the TipoProtocolo in the database
        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeCreate + 1);
        TipoProtocolo testTipoProtocolo = tipoProtocoloList.get(tipoProtocoloList.size() - 1);
        assertThat(testTipoProtocolo.getNomeProtocolo()).isEqualTo(DEFAULT_NOME_PROTOCOLO);
        assertThat(testTipoProtocolo.getCodProtocolo()).isEqualTo(DEFAULT_COD_PROTOCOLO);
        assertThat(testTipoProtocolo.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createTipoProtocoloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoProtocoloRepository.findAll().size();

        // Create the TipoProtocolo with an existing ID
        tipoProtocolo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoProtocoloMockMvc.perform(post("/api/tipo-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoProtocolo)))
            .andExpect(status().isBadRequest());

        // Validate the TipoProtocolo in the database
        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeProtocoloIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoProtocoloRepository.findAll().size();
        // set the field null
        tipoProtocolo.setNomeProtocolo(null);

        // Create the TipoProtocolo, which fails.

        restTipoProtocoloMockMvc.perform(post("/api/tipo-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoProtocolo)))
            .andExpect(status().isBadRequest());

        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodProtocoloIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoProtocoloRepository.findAll().size();
        // set the field null
        tipoProtocolo.setCodProtocolo(null);

        // Create the TipoProtocolo, which fails.

        restTipoProtocoloMockMvc.perform(post("/api/tipo-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoProtocolo)))
            .andExpect(status().isBadRequest());

        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoProtocoloRepository.findAll().size();
        // set the field null
        tipoProtocolo.setIsActive(null);

        // Create the TipoProtocolo, which fails.

        restTipoProtocoloMockMvc.perform(post("/api/tipo-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoProtocolo)))
            .andExpect(status().isBadRequest());

        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoProtocolos() throws Exception {
        // Initialize the database
        tipoProtocoloRepository.saveAndFlush(tipoProtocolo);

        // Get all the tipoProtocoloList
        restTipoProtocoloMockMvc.perform(get("/api/tipo-protocolos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoProtocolo.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeProtocolo").value(hasItem(DEFAULT_NOME_PROTOCOLO)))
            .andExpect(jsonPath("$.[*].codProtocolo").value(hasItem(DEFAULT_COD_PROTOCOLO)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTipoProtocolo() throws Exception {
        // Initialize the database
        tipoProtocoloRepository.saveAndFlush(tipoProtocolo);

        // Get the tipoProtocolo
        restTipoProtocoloMockMvc.perform(get("/api/tipo-protocolos/{id}", tipoProtocolo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoProtocolo.getId().intValue()))
            .andExpect(jsonPath("$.nomeProtocolo").value(DEFAULT_NOME_PROTOCOLO))
            .andExpect(jsonPath("$.codProtocolo").value(DEFAULT_COD_PROTOCOLO))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoProtocolo() throws Exception {
        // Get the tipoProtocolo
        restTipoProtocoloMockMvc.perform(get("/api/tipo-protocolos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoProtocolo() throws Exception {
        // Initialize the database
        tipoProtocoloService.save(tipoProtocolo);

        int databaseSizeBeforeUpdate = tipoProtocoloRepository.findAll().size();

        // Update the tipoProtocolo
        TipoProtocolo updatedTipoProtocolo = tipoProtocoloRepository.findById(tipoProtocolo.getId()).get();
        // Disconnect from session so that the updates on updatedTipoProtocolo are not directly saved in db
        em.detach(updatedTipoProtocolo);
        updatedTipoProtocolo
            .nomeProtocolo(UPDATED_NOME_PROTOCOLO)
            .codProtocolo(UPDATED_COD_PROTOCOLO)
            .isActive(UPDATED_IS_ACTIVE);

        restTipoProtocoloMockMvc.perform(put("/api/tipo-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoProtocolo)))
            .andExpect(status().isOk());

        // Validate the TipoProtocolo in the database
        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeUpdate);
        TipoProtocolo testTipoProtocolo = tipoProtocoloList.get(tipoProtocoloList.size() - 1);
        assertThat(testTipoProtocolo.getNomeProtocolo()).isEqualTo(UPDATED_NOME_PROTOCOLO);
        assertThat(testTipoProtocolo.getCodProtocolo()).isEqualTo(UPDATED_COD_PROTOCOLO);
        assertThat(testTipoProtocolo.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoProtocolo() throws Exception {
        int databaseSizeBeforeUpdate = tipoProtocoloRepository.findAll().size();

        // Create the TipoProtocolo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoProtocoloMockMvc.perform(put("/api/tipo-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoProtocolo)))
            .andExpect(status().isBadRequest());

        // Validate the TipoProtocolo in the database
        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoProtocolo() throws Exception {
        // Initialize the database
        tipoProtocoloService.save(tipoProtocolo);

        int databaseSizeBeforeDelete = tipoProtocoloRepository.findAll().size();

        // Delete the tipoProtocolo
        restTipoProtocoloMockMvc.perform(delete("/api/tipo-protocolos/{id}", tipoProtocolo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoProtocolo> tipoProtocoloList = tipoProtocoloRepository.findAll();
        assertThat(tipoProtocoloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
