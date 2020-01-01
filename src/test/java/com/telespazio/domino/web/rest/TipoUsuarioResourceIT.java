package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.TipoUsuario;
import com.telespazio.domino.repository.TipoUsuarioRepository;
import com.telespazio.domino.service.TipoUsuarioService;
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
 * Integration tests for the {@link TipoUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class TipoUsuarioResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

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

    private MockMvc restTipoUsuarioMockMvc;

    private TipoUsuario tipoUsuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoUsuarioResource tipoUsuarioResource = new TipoUsuarioResource(tipoUsuarioService);
        this.restTipoUsuarioMockMvc = MockMvcBuilders.standaloneSetup(tipoUsuarioResource)
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
    public static TipoUsuario createEntity(EntityManager em) {
        TipoUsuario tipoUsuario = new TipoUsuario()
            .descricao(DEFAULT_DESCRICAO);
        return tipoUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoUsuario createUpdatedEntity(EntityManager em) {
        TipoUsuario tipoUsuario = new TipoUsuario()
            .descricao(UPDATED_DESCRICAO);
        return tipoUsuario;
    }

    @BeforeEach
    public void initTest() {
        tipoUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoUsuario() throws Exception {
        int databaseSizeBeforeCreate = tipoUsuarioRepository.findAll().size();

        // Create the TipoUsuario
        restTipoUsuarioMockMvc.perform(post("/api/tipo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuario)))
            .andExpect(status().isCreated());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        TipoUsuario testTipoUsuario = tipoUsuarioList.get(tipoUsuarioList.size() - 1);
        assertThat(testTipoUsuario.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createTipoUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoUsuarioRepository.findAll().size();

        // Create the TipoUsuario with an existing ID
        tipoUsuario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoUsuarioMockMvc.perform(post("/api/tipo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuario)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoUsuarioRepository.findAll().size();
        // set the field null
        tipoUsuario.setDescricao(null);

        // Create the TipoUsuario, which fails.

        restTipoUsuarioMockMvc.perform(post("/api/tipo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuario)))
            .andExpect(status().isBadRequest());

        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoUsuarios() throws Exception {
        // Initialize the database
        tipoUsuarioRepository.saveAndFlush(tipoUsuario);

        // Get all the tipoUsuarioList
        restTipoUsuarioMockMvc.perform(get("/api/tipo-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoUsuario.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getTipoUsuario() throws Exception {
        // Initialize the database
        tipoUsuarioRepository.saveAndFlush(tipoUsuario);

        // Get the tipoUsuario
        restTipoUsuarioMockMvc.perform(get("/api/tipo-usuarios/{id}", tipoUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoUsuario.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingTipoUsuario() throws Exception {
        // Get the tipoUsuario
        restTipoUsuarioMockMvc.perform(get("/api/tipo-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoUsuario() throws Exception {
        // Initialize the database
        tipoUsuarioService.save(tipoUsuario);

        int databaseSizeBeforeUpdate = tipoUsuarioRepository.findAll().size();

        // Update the tipoUsuario
        TipoUsuario updatedTipoUsuario = tipoUsuarioRepository.findById(tipoUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedTipoUsuario are not directly saved in db
        em.detach(updatedTipoUsuario);
        updatedTipoUsuario
            .descricao(UPDATED_DESCRICAO);

        restTipoUsuarioMockMvc.perform(put("/api/tipo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoUsuario)))
            .andExpect(status().isOk());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeUpdate);
        TipoUsuario testTipoUsuario = tipoUsuarioList.get(tipoUsuarioList.size() - 1);
        assertThat(testTipoUsuario.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoUsuario() throws Exception {
        int databaseSizeBeforeUpdate = tipoUsuarioRepository.findAll().size();

        // Create the TipoUsuario

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoUsuarioMockMvc.perform(put("/api/tipo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoUsuario)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUsuario in the database
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoUsuario() throws Exception {
        // Initialize the database
        tipoUsuarioService.save(tipoUsuario);

        int databaseSizeBeforeDelete = tipoUsuarioRepository.findAll().size();

        // Delete the tipoUsuario
        restTipoUsuarioMockMvc.perform(delete("/api/tipo-usuarios/{id}", tipoUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoUsuario> tipoUsuarioList = tipoUsuarioRepository.findAll();
        assertThat(tipoUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
