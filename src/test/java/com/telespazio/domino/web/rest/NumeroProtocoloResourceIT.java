package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.NumeroProtocolo;
import com.telespazio.domino.repository.NumeroProtocoloRepository;
import com.telespazio.domino.service.NumeroProtocoloService;
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
 * Integration tests for the {@link NumeroProtocoloResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class NumeroProtocoloResourceIT {

    private static final String DEFAULT_ANO = "AAAAAAAAAA";
    private static final String UPDATED_ANO = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    @Autowired
    private NumeroProtocoloRepository numeroProtocoloRepository;

    @Autowired
    private NumeroProtocoloService numeroProtocoloService;

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

    private MockMvc restNumeroProtocoloMockMvc;

    private NumeroProtocolo numeroProtocolo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NumeroProtocoloResource numeroProtocoloResource = new NumeroProtocoloResource(numeroProtocoloService);
        this.restNumeroProtocoloMockMvc = MockMvcBuilders.standaloneSetup(numeroProtocoloResource)
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
    public static NumeroProtocolo createEntity(EntityManager em) {
        NumeroProtocolo numeroProtocolo = new NumeroProtocolo()
            .ano(DEFAULT_ANO)
            .numero(DEFAULT_NUMERO);
        return numeroProtocolo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NumeroProtocolo createUpdatedEntity(EntityManager em) {
        NumeroProtocolo numeroProtocolo = new NumeroProtocolo()
            .ano(UPDATED_ANO)
            .numero(UPDATED_NUMERO);
        return numeroProtocolo;
    }

    @BeforeEach
    public void initTest() {
        numeroProtocolo = createEntity(em);
    }

    @Test
    @Transactional
    public void createNumeroProtocolo() throws Exception {
        int databaseSizeBeforeCreate = numeroProtocoloRepository.findAll().size();

        // Create the NumeroProtocolo
        restNumeroProtocoloMockMvc.perform(post("/api/numero-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroProtocolo)))
            .andExpect(status().isCreated());

        // Validate the NumeroProtocolo in the database
        List<NumeroProtocolo> numeroProtocoloList = numeroProtocoloRepository.findAll();
        assertThat(numeroProtocoloList).hasSize(databaseSizeBeforeCreate + 1);
        NumeroProtocolo testNumeroProtocolo = numeroProtocoloList.get(numeroProtocoloList.size() - 1);
        assertThat(testNumeroProtocolo.getAno()).isEqualTo(DEFAULT_ANO);
        assertThat(testNumeroProtocolo.getNumero()).isEqualTo(DEFAULT_NUMERO);
    }

    @Test
    @Transactional
    public void createNumeroProtocoloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = numeroProtocoloRepository.findAll().size();

        // Create the NumeroProtocolo with an existing ID
        numeroProtocolo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNumeroProtocoloMockMvc.perform(post("/api/numero-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroProtocolo)))
            .andExpect(status().isBadRequest());

        // Validate the NumeroProtocolo in the database
        List<NumeroProtocolo> numeroProtocoloList = numeroProtocoloRepository.findAll();
        assertThat(numeroProtocoloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeroProtocoloRepository.findAll().size();
        // set the field null
        numeroProtocolo.setAno(null);

        // Create the NumeroProtocolo, which fails.

        restNumeroProtocoloMockMvc.perform(post("/api/numero-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroProtocolo)))
            .andExpect(status().isBadRequest());

        List<NumeroProtocolo> numeroProtocoloList = numeroProtocoloRepository.findAll();
        assertThat(numeroProtocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeroProtocoloRepository.findAll().size();
        // set the field null
        numeroProtocolo.setNumero(null);

        // Create the NumeroProtocolo, which fails.

        restNumeroProtocoloMockMvc.perform(post("/api/numero-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroProtocolo)))
            .andExpect(status().isBadRequest());

        List<NumeroProtocolo> numeroProtocoloList = numeroProtocoloRepository.findAll();
        assertThat(numeroProtocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNumeroProtocolos() throws Exception {
        // Initialize the database
        numeroProtocoloRepository.saveAndFlush(numeroProtocolo);

        // Get all the numeroProtocoloList
        restNumeroProtocoloMockMvc.perform(get("/api/numero-protocolos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(numeroProtocolo.getId().intValue())))
            .andExpect(jsonPath("$.[*].ano").value(hasItem(DEFAULT_ANO)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())));
    }
    
    @Test
    @Transactional
    public void getNumeroProtocolo() throws Exception {
        // Initialize the database
        numeroProtocoloRepository.saveAndFlush(numeroProtocolo);

        // Get the numeroProtocolo
        restNumeroProtocoloMockMvc.perform(get("/api/numero-protocolos/{id}", numeroProtocolo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(numeroProtocolo.getId().intValue()))
            .andExpect(jsonPath("$.ano").value(DEFAULT_ANO))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNumeroProtocolo() throws Exception {
        // Get the numeroProtocolo
        restNumeroProtocoloMockMvc.perform(get("/api/numero-protocolos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNumeroProtocolo() throws Exception {
        // Initialize the database
        numeroProtocoloService.save(numeroProtocolo);

        int databaseSizeBeforeUpdate = numeroProtocoloRepository.findAll().size();

        // Update the numeroProtocolo
        NumeroProtocolo updatedNumeroProtocolo = numeroProtocoloRepository.findById(numeroProtocolo.getId()).get();
        // Disconnect from session so that the updates on updatedNumeroProtocolo are not directly saved in db
        em.detach(updatedNumeroProtocolo);
        updatedNumeroProtocolo
            .ano(UPDATED_ANO)
            .numero(UPDATED_NUMERO);

        restNumeroProtocoloMockMvc.perform(put("/api/numero-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNumeroProtocolo)))
            .andExpect(status().isOk());

        // Validate the NumeroProtocolo in the database
        List<NumeroProtocolo> numeroProtocoloList = numeroProtocoloRepository.findAll();
        assertThat(numeroProtocoloList).hasSize(databaseSizeBeforeUpdate);
        NumeroProtocolo testNumeroProtocolo = numeroProtocoloList.get(numeroProtocoloList.size() - 1);
        assertThat(testNumeroProtocolo.getAno()).isEqualTo(UPDATED_ANO);
        assertThat(testNumeroProtocolo.getNumero()).isEqualTo(UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void updateNonExistingNumeroProtocolo() throws Exception {
        int databaseSizeBeforeUpdate = numeroProtocoloRepository.findAll().size();

        // Create the NumeroProtocolo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNumeroProtocoloMockMvc.perform(put("/api/numero-protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeroProtocolo)))
            .andExpect(status().isBadRequest());

        // Validate the NumeroProtocolo in the database
        List<NumeroProtocolo> numeroProtocoloList = numeroProtocoloRepository.findAll();
        assertThat(numeroProtocoloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNumeroProtocolo() throws Exception {
        // Initialize the database
        numeroProtocoloService.save(numeroProtocolo);

        int databaseSizeBeforeDelete = numeroProtocoloRepository.findAll().size();

        // Delete the numeroProtocolo
        restNumeroProtocoloMockMvc.perform(delete("/api/numero-protocolos/{id}", numeroProtocolo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NumeroProtocolo> numeroProtocoloList = numeroProtocoloRepository.findAll();
        assertThat(numeroProtocoloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
