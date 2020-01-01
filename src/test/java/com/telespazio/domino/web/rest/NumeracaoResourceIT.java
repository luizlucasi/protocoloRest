package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.Numeracao;
import com.telespazio.domino.repository.NumeracaoRepository;
import com.telespazio.domino.service.NumeracaoService;
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
 * Integration tests for the {@link NumeracaoResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class NumeracaoResourceIT {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    private static final Integer DEFAULT_ANO = 1;
    private static final Integer UPDATED_ANO = 2;

    @Autowired
    private NumeracaoRepository numeracaoRepository;

    @Autowired
    private NumeracaoService numeracaoService;

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

    private MockMvc restNumeracaoMockMvc;

    private Numeracao numeracao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NumeracaoResource numeracaoResource = new NumeracaoResource(numeracaoService);
        this.restNumeracaoMockMvc = MockMvcBuilders.standaloneSetup(numeracaoResource)
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
    public static Numeracao createEntity(EntityManager em) {
        Numeracao numeracao = new Numeracao()
            .numero(DEFAULT_NUMERO)
            .ano(DEFAULT_ANO);
        return numeracao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Numeracao createUpdatedEntity(EntityManager em) {
        Numeracao numeracao = new Numeracao()
            .numero(UPDATED_NUMERO)
            .ano(UPDATED_ANO);
        return numeracao;
    }

    @BeforeEach
    public void initTest() {
        numeracao = createEntity(em);
    }

    @Test
    @Transactional
    public void createNumeracao() throws Exception {
        int databaseSizeBeforeCreate = numeracaoRepository.findAll().size();

        // Create the Numeracao
        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isCreated());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeCreate + 1);
        Numeracao testNumeracao = numeracaoList.get(numeracaoList.size() - 1);
        assertThat(testNumeracao.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testNumeracao.getAno()).isEqualTo(DEFAULT_ANO);
    }

    @Test
    @Transactional
    public void createNumeracaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = numeracaoRepository.findAll().size();

        // Create the Numeracao with an existing ID
        numeracao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeracaoRepository.findAll().size();
        // set the field null
        numeracao.setNumero(null);

        // Create the Numeracao, which fails.

        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = numeracaoRepository.findAll().size();
        // set the field null
        numeracao.setAno(null);

        // Create the Numeracao, which fails.

        restNumeracaoMockMvc.perform(post("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNumeracaos() throws Exception {
        // Initialize the database
        numeracaoRepository.saveAndFlush(numeracao);

        // Get all the numeracaoList
        restNumeracaoMockMvc.perform(get("/api/numeracaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(numeracao.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].ano").value(hasItem(DEFAULT_ANO)));
    }
    
    @Test
    @Transactional
    public void getNumeracao() throws Exception {
        // Initialize the database
        numeracaoRepository.saveAndFlush(numeracao);

        // Get the numeracao
        restNumeracaoMockMvc.perform(get("/api/numeracaos/{id}", numeracao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(numeracao.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.ano").value(DEFAULT_ANO));
    }

    @Test
    @Transactional
    public void getNonExistingNumeracao() throws Exception {
        // Get the numeracao
        restNumeracaoMockMvc.perform(get("/api/numeracaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNumeracao() throws Exception {
        // Initialize the database
        numeracaoService.save(numeracao);

        int databaseSizeBeforeUpdate = numeracaoRepository.findAll().size();

        // Update the numeracao
        Numeracao updatedNumeracao = numeracaoRepository.findById(numeracao.getId()).get();
        // Disconnect from session so that the updates on updatedNumeracao are not directly saved in db
        em.detach(updatedNumeracao);
        updatedNumeracao
            .numero(UPDATED_NUMERO)
            .ano(UPDATED_ANO);

        restNumeracaoMockMvc.perform(put("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNumeracao)))
            .andExpect(status().isOk());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeUpdate);
        Numeracao testNumeracao = numeracaoList.get(numeracaoList.size() - 1);
        assertThat(testNumeracao.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testNumeracao.getAno()).isEqualTo(UPDATED_ANO);
    }

    @Test
    @Transactional
    public void updateNonExistingNumeracao() throws Exception {
        int databaseSizeBeforeUpdate = numeracaoRepository.findAll().size();

        // Create the Numeracao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNumeracaoMockMvc.perform(put("/api/numeracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(numeracao)))
            .andExpect(status().isBadRequest());

        // Validate the Numeracao in the database
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNumeracao() throws Exception {
        // Initialize the database
        numeracaoService.save(numeracao);

        int databaseSizeBeforeDelete = numeracaoRepository.findAll().size();

        // Delete the numeracao
        restNumeracaoMockMvc.perform(delete("/api/numeracaos/{id}", numeracao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Numeracao> numeracaoList = numeracaoRepository.findAll();
        assertThat(numeracaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
