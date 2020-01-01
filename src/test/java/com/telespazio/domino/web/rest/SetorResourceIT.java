package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.Setor;
import com.telespazio.domino.repository.SetorRepository;
import com.telespazio.domino.service.SetorService;
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
 * Integration tests for the {@link SetorResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class SetorResourceIT {

    private static final String DEFAULT_NOME_SETOR = "AAAAAAAAAA";
    private static final String UPDATED_NOME_SETOR = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_SETOR = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_SETOR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private SetorService setorService;

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

    private MockMvc restSetorMockMvc;

    private Setor setor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SetorResource setorResource = new SetorResource(setorService);
        this.restSetorMockMvc = MockMvcBuilders.standaloneSetup(setorResource)
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
    public static Setor createEntity(EntityManager em) {
        Setor setor = new Setor()
            .nomeSetor(DEFAULT_NOME_SETOR)
            .codigoSetor(DEFAULT_CODIGO_SETOR)
            .isActive(DEFAULT_IS_ACTIVE);
        return setor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Setor createUpdatedEntity(EntityManager em) {
        Setor setor = new Setor()
            .nomeSetor(UPDATED_NOME_SETOR)
            .codigoSetor(UPDATED_CODIGO_SETOR)
            .isActive(UPDATED_IS_ACTIVE);
        return setor;
    }

    @BeforeEach
    public void initTest() {
        setor = createEntity(em);
    }

    @Test
    @Transactional
    public void createSetor() throws Exception {
        int databaseSizeBeforeCreate = setorRepository.findAll().size();

        // Create the Setor
        restSetorMockMvc.perform(post("/api/setors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setor)))
            .andExpect(status().isCreated());

        // Validate the Setor in the database
        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeCreate + 1);
        Setor testSetor = setorList.get(setorList.size() - 1);
        assertThat(testSetor.getNomeSetor()).isEqualTo(DEFAULT_NOME_SETOR);
        assertThat(testSetor.getCodigoSetor()).isEqualTo(DEFAULT_CODIGO_SETOR);
        assertThat(testSetor.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void createSetorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = setorRepository.findAll().size();

        // Create the Setor with an existing ID
        setor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSetorMockMvc.perform(post("/api/setors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setor)))
            .andExpect(status().isBadRequest());

        // Validate the Setor in the database
        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeSetorIsRequired() throws Exception {
        int databaseSizeBeforeTest = setorRepository.findAll().size();
        // set the field null
        setor.setNomeSetor(null);

        // Create the Setor, which fails.

        restSetorMockMvc.perform(post("/api/setors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setor)))
            .andExpect(status().isBadRequest());

        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoSetorIsRequired() throws Exception {
        int databaseSizeBeforeTest = setorRepository.findAll().size();
        // set the field null
        setor.setCodigoSetor(null);

        // Create the Setor, which fails.

        restSetorMockMvc.perform(post("/api/setors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setor)))
            .andExpect(status().isBadRequest());

        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = setorRepository.findAll().size();
        // set the field null
        setor.setIsActive(null);

        // Create the Setor, which fails.

        restSetorMockMvc.perform(post("/api/setors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setor)))
            .andExpect(status().isBadRequest());

        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSetors() throws Exception {
        // Initialize the database
        setorRepository.saveAndFlush(setor);

        // Get all the setorList
        restSetorMockMvc.perform(get("/api/setors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(setor.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeSetor").value(hasItem(DEFAULT_NOME_SETOR)))
            .andExpect(jsonPath("$.[*].codigoSetor").value(hasItem(DEFAULT_CODIGO_SETOR)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSetor() throws Exception {
        // Initialize the database
        setorRepository.saveAndFlush(setor);

        // Get the setor
        restSetorMockMvc.perform(get("/api/setors/{id}", setor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(setor.getId().intValue()))
            .andExpect(jsonPath("$.nomeSetor").value(DEFAULT_NOME_SETOR))
            .andExpect(jsonPath("$.codigoSetor").value(DEFAULT_CODIGO_SETOR))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSetor() throws Exception {
        // Get the setor
        restSetorMockMvc.perform(get("/api/setors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSetor() throws Exception {
        // Initialize the database
        setorService.save(setor);

        int databaseSizeBeforeUpdate = setorRepository.findAll().size();

        // Update the setor
        Setor updatedSetor = setorRepository.findById(setor.getId()).get();
        // Disconnect from session so that the updates on updatedSetor are not directly saved in db
        em.detach(updatedSetor);
        updatedSetor
            .nomeSetor(UPDATED_NOME_SETOR)
            .codigoSetor(UPDATED_CODIGO_SETOR)
            .isActive(UPDATED_IS_ACTIVE);

        restSetorMockMvc.perform(put("/api/setors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSetor)))
            .andExpect(status().isOk());

        // Validate the Setor in the database
        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeUpdate);
        Setor testSetor = setorList.get(setorList.size() - 1);
        assertThat(testSetor.getNomeSetor()).isEqualTo(UPDATED_NOME_SETOR);
        assertThat(testSetor.getCodigoSetor()).isEqualTo(UPDATED_CODIGO_SETOR);
        assertThat(testSetor.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingSetor() throws Exception {
        int databaseSizeBeforeUpdate = setorRepository.findAll().size();

        // Create the Setor

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSetorMockMvc.perform(put("/api/setors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(setor)))
            .andExpect(status().isBadRequest());

        // Validate the Setor in the database
        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSetor() throws Exception {
        // Initialize the database
        setorService.save(setor);

        int databaseSizeBeforeDelete = setorRepository.findAll().size();

        // Delete the setor
        restSetorMockMvc.perform(delete("/api/setors/{id}", setor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Setor> setorList = setorRepository.findAll();
        assertThat(setorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
