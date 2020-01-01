package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.Versao;
import com.telespazio.domino.repository.VersaoRepository;
import com.telespazio.domino.service.VersaoService;
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
 * Integration tests for the {@link VersaoResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class VersaoResourceIT {

    private static final String DEFAULT_NUMERO_VERSAO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_VERSAO = "BBBBBBBBBB";

    @Autowired
    private VersaoRepository versaoRepository;

    @Autowired
    private VersaoService versaoService;

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

    private MockMvc restVersaoMockMvc;

    private Versao versao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VersaoResource versaoResource = new VersaoResource(versaoService);
        this.restVersaoMockMvc = MockMvcBuilders.standaloneSetup(versaoResource)
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
    public static Versao createEntity(EntityManager em) {
        Versao versao = new Versao()
            .numeroVersao(DEFAULT_NUMERO_VERSAO);
        return versao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Versao createUpdatedEntity(EntityManager em) {
        Versao versao = new Versao()
            .numeroVersao(UPDATED_NUMERO_VERSAO);
        return versao;
    }

    @BeforeEach
    public void initTest() {
        versao = createEntity(em);
    }

    @Test
    @Transactional
    public void createVersao() throws Exception {
        int databaseSizeBeforeCreate = versaoRepository.findAll().size();

        // Create the Versao
        restVersaoMockMvc.perform(post("/api/versaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(versao)))
            .andExpect(status().isCreated());

        // Validate the Versao in the database
        List<Versao> versaoList = versaoRepository.findAll();
        assertThat(versaoList).hasSize(databaseSizeBeforeCreate + 1);
        Versao testVersao = versaoList.get(versaoList.size() - 1);
        assertThat(testVersao.getNumeroVersao()).isEqualTo(DEFAULT_NUMERO_VERSAO);
    }

    @Test
    @Transactional
    public void createVersaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = versaoRepository.findAll().size();

        // Create the Versao with an existing ID
        versao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVersaoMockMvc.perform(post("/api/versaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(versao)))
            .andExpect(status().isBadRequest());

        // Validate the Versao in the database
        List<Versao> versaoList = versaoRepository.findAll();
        assertThat(versaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumeroVersaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = versaoRepository.findAll().size();
        // set the field null
        versao.setNumeroVersao(null);

        // Create the Versao, which fails.

        restVersaoMockMvc.perform(post("/api/versaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(versao)))
            .andExpect(status().isBadRequest());

        List<Versao> versaoList = versaoRepository.findAll();
        assertThat(versaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVersaos() throws Exception {
        // Initialize the database
        versaoRepository.saveAndFlush(versao);

        // Get all the versaoList
        restVersaoMockMvc.perform(get("/api/versaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(versao.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroVersao").value(hasItem(DEFAULT_NUMERO_VERSAO)));
    }
    
    @Test
    @Transactional
    public void getVersao() throws Exception {
        // Initialize the database
        versaoRepository.saveAndFlush(versao);

        // Get the versao
        restVersaoMockMvc.perform(get("/api/versaos/{id}", versao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(versao.getId().intValue()))
            .andExpect(jsonPath("$.numeroVersao").value(DEFAULT_NUMERO_VERSAO));
    }

    @Test
    @Transactional
    public void getNonExistingVersao() throws Exception {
        // Get the versao
        restVersaoMockMvc.perform(get("/api/versaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVersao() throws Exception {
        // Initialize the database
        versaoService.save(versao);

        int databaseSizeBeforeUpdate = versaoRepository.findAll().size();

        // Update the versao
        Versao updatedVersao = versaoRepository.findById(versao.getId()).get();
        // Disconnect from session so that the updates on updatedVersao are not directly saved in db
        em.detach(updatedVersao);
        updatedVersao
            .numeroVersao(UPDATED_NUMERO_VERSAO);

        restVersaoMockMvc.perform(put("/api/versaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVersao)))
            .andExpect(status().isOk());

        // Validate the Versao in the database
        List<Versao> versaoList = versaoRepository.findAll();
        assertThat(versaoList).hasSize(databaseSizeBeforeUpdate);
        Versao testVersao = versaoList.get(versaoList.size() - 1);
        assertThat(testVersao.getNumeroVersao()).isEqualTo(UPDATED_NUMERO_VERSAO);
    }

    @Test
    @Transactional
    public void updateNonExistingVersao() throws Exception {
        int databaseSizeBeforeUpdate = versaoRepository.findAll().size();

        // Create the Versao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVersaoMockMvc.perform(put("/api/versaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(versao)))
            .andExpect(status().isBadRequest());

        // Validate the Versao in the database
        List<Versao> versaoList = versaoRepository.findAll();
        assertThat(versaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVersao() throws Exception {
        // Initialize the database
        versaoService.save(versao);

        int databaseSizeBeforeDelete = versaoRepository.findAll().size();

        // Delete the versao
        restVersaoMockMvc.perform(delete("/api/versaos/{id}", versao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Versao> versaoList = versaoRepository.findAll();
        assertThat(versaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
