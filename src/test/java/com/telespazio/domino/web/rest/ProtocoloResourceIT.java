package com.telespazio.domino.web.rest;

import com.telespazio.domino.ProtocoloRestApp;
import com.telespazio.domino.domain.Protocolo;
import com.telespazio.domino.repository.ProtocoloRepository;
import com.telespazio.domino.service.ProtocoloService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.telespazio.domino.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProtocoloResource} REST controller.
 */
@SpringBootTest(classes = ProtocoloRestApp.class)
public class ProtocoloResourceIT {

    private static final String DEFAULT_SOLICITANTE = "AAAAAAAAAA";
    private static final String UPDATED_SOLICITANTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_SOLICITACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_SOLICITACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATA_ENVIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_ENVIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ENVIADO_POR = "AAAAAAAAAA";
    private static final String UPDATED_ENVIADO_POR = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_CRIACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_CRIACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_USUARIO_CRIACAO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_USUARIO_CRIACAO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LOCALIZACAO = "AAAAAAAAAA";
    private static final String UPDATED_LOCALIZACAO = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMENCLATURA = "AAAAAAAAAA";
    private static final String UPDATED_NOMENCLATURA = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATO = "AAAAAAAAAA";
    private static final String UPDATED_FORMATO = "BBBBBBBBBB";

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private ProtocoloService protocoloService;

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

    private MockMvc restProtocoloMockMvc;

    private Protocolo protocolo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProtocoloResource protocoloResource = new ProtocoloResource(protocoloService);
        this.restProtocoloMockMvc = MockMvcBuilders.standaloneSetup(protocoloResource)
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
    public static Protocolo createEntity(EntityManager em) {
        Protocolo protocolo = new Protocolo()
            .solicitante(DEFAULT_SOLICITANTE)
            .dataSolicitacao(DEFAULT_DATA_SOLICITACAO)
            .dataEnvio(DEFAULT_DATA_ENVIO)
            .enviadoPor(DEFAULT_ENVIADO_POR)
            .dataCriacao(DEFAULT_DATA_CRIACAO)
            .usuarioCriacao(DEFAULT_USUARIO_CRIACAO)
            .localizacao(DEFAULT_LOCALIZACAO)
            .observacao(DEFAULT_OBSERVACAO)
            .nomenclatura(DEFAULT_NOMENCLATURA)
            .formato(DEFAULT_FORMATO);
        return protocolo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Protocolo createUpdatedEntity(EntityManager em) {
        Protocolo protocolo = new Protocolo()
            .solicitante(UPDATED_SOLICITANTE)
            .dataSolicitacao(UPDATED_DATA_SOLICITACAO)
            .dataEnvio(UPDATED_DATA_ENVIO)
            .enviadoPor(UPDATED_ENVIADO_POR)
            .dataCriacao(UPDATED_DATA_CRIACAO)
            .usuarioCriacao(UPDATED_USUARIO_CRIACAO)
            .localizacao(UPDATED_LOCALIZACAO)
            .observacao(UPDATED_OBSERVACAO)
            .nomenclatura(UPDATED_NOMENCLATURA)
            .formato(UPDATED_FORMATO);
        return protocolo;
    }

    @BeforeEach
    public void initTest() {
        protocolo = createEntity(em);
    }

    @Test
    @Transactional
    public void createProtocolo() throws Exception {
        int databaseSizeBeforeCreate = protocoloRepository.findAll().size();

        // Create the Protocolo
        restProtocoloMockMvc.perform(post("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(protocolo)))
            .andExpect(status().isCreated());

        // Validate the Protocolo in the database
        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeCreate + 1);
        Protocolo testProtocolo = protocoloList.get(protocoloList.size() - 1);
        assertThat(testProtocolo.getSolicitante()).isEqualTo(DEFAULT_SOLICITANTE);
        assertThat(testProtocolo.getDataSolicitacao()).isEqualTo(DEFAULT_DATA_SOLICITACAO);
        assertThat(testProtocolo.getDataEnvio()).isEqualTo(DEFAULT_DATA_ENVIO);
        assertThat(testProtocolo.getEnviadoPor()).isEqualTo(DEFAULT_ENVIADO_POR);
        assertThat(testProtocolo.getDataCriacao()).isEqualTo(DEFAULT_DATA_CRIACAO);
        assertThat(testProtocolo.getUsuarioCriacao()).isEqualTo(DEFAULT_USUARIO_CRIACAO);
        assertThat(testProtocolo.getLocalizacao()).isEqualTo(DEFAULT_LOCALIZACAO);
        assertThat(testProtocolo.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
        assertThat(testProtocolo.getNomenclatura()).isEqualTo(DEFAULT_NOMENCLATURA);
        assertThat(testProtocolo.getFormato()).isEqualTo(DEFAULT_FORMATO);
    }

    @Test
    @Transactional
    public void createProtocoloWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = protocoloRepository.findAll().size();

        // Create the Protocolo with an existing ID
        protocolo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProtocoloMockMvc.perform(post("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(protocolo)))
            .andExpect(status().isBadRequest());

        // Validate the Protocolo in the database
        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSolicitanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = protocoloRepository.findAll().size();
        // set the field null
        protocolo.setSolicitante(null);

        // Create the Protocolo, which fails.

        restProtocoloMockMvc.perform(post("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(protocolo)))
            .andExpect(status().isBadRequest());

        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataSolicitacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = protocoloRepository.findAll().size();
        // set the field null
        protocolo.setDataSolicitacao(null);

        // Create the Protocolo, which fails.

        restProtocoloMockMvc.perform(post("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(protocolo)))
            .andExpect(status().isBadRequest());

        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataCriacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = protocoloRepository.findAll().size();
        // set the field null
        protocolo.setDataCriacao(null);

        // Create the Protocolo, which fails.

        restProtocoloMockMvc.perform(post("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(protocolo)))
            .andExpect(status().isBadRequest());

        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsuarioCriacaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = protocoloRepository.findAll().size();
        // set the field null
        protocolo.setUsuarioCriacao(null);

        // Create the Protocolo, which fails.

        restProtocoloMockMvc.perform(post("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(protocolo)))
            .andExpect(status().isBadRequest());

        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProtocolos() throws Exception {
        // Initialize the database
        protocoloRepository.saveAndFlush(protocolo);

        // Get all the protocoloList
        restProtocoloMockMvc.perform(get("/api/protocolos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(protocolo.getId().intValue())))
            .andExpect(jsonPath("$.[*].solicitante").value(hasItem(DEFAULT_SOLICITANTE)))
            .andExpect(jsonPath("$.[*].dataSolicitacao").value(hasItem(DEFAULT_DATA_SOLICITACAO.toString())))
            .andExpect(jsonPath("$.[*].dataEnvio").value(hasItem(DEFAULT_DATA_ENVIO.toString())))
            .andExpect(jsonPath("$.[*].enviadoPor").value(hasItem(DEFAULT_ENVIADO_POR)))
            .andExpect(jsonPath("$.[*].dataCriacao").value(hasItem(DEFAULT_DATA_CRIACAO.toString())))
            .andExpect(jsonPath("$.[*].usuarioCriacao").value(hasItem(DEFAULT_USUARIO_CRIACAO.toString())))
            .andExpect(jsonPath("$.[*].localizacao").value(hasItem(DEFAULT_LOCALIZACAO)))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO)))
            .andExpect(jsonPath("$.[*].nomenclatura").value(hasItem(DEFAULT_NOMENCLATURA)))
            .andExpect(jsonPath("$.[*].formato").value(hasItem(DEFAULT_FORMATO)));
    }
    
    @Test
    @Transactional
    public void getProtocolo() throws Exception {
        // Initialize the database
        protocoloRepository.saveAndFlush(protocolo);

        // Get the protocolo
        restProtocoloMockMvc.perform(get("/api/protocolos/{id}", protocolo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(protocolo.getId().intValue()))
            .andExpect(jsonPath("$.solicitante").value(DEFAULT_SOLICITANTE))
            .andExpect(jsonPath("$.dataSolicitacao").value(DEFAULT_DATA_SOLICITACAO.toString()))
            .andExpect(jsonPath("$.dataEnvio").value(DEFAULT_DATA_ENVIO.toString()))
            .andExpect(jsonPath("$.enviadoPor").value(DEFAULT_ENVIADO_POR))
            .andExpect(jsonPath("$.dataCriacao").value(DEFAULT_DATA_CRIACAO.toString()))
            .andExpect(jsonPath("$.usuarioCriacao").value(DEFAULT_USUARIO_CRIACAO.toString()))
            .andExpect(jsonPath("$.localizacao").value(DEFAULT_LOCALIZACAO))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO))
            .andExpect(jsonPath("$.nomenclatura").value(DEFAULT_NOMENCLATURA))
            .andExpect(jsonPath("$.formato").value(DEFAULT_FORMATO));
    }

    @Test
    @Transactional
    public void getNonExistingProtocolo() throws Exception {
        // Get the protocolo
        restProtocoloMockMvc.perform(get("/api/protocolos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProtocolo() throws Exception {
        // Initialize the database
        protocoloService.save(protocolo);

        int databaseSizeBeforeUpdate = protocoloRepository.findAll().size();

        // Update the protocolo
        Protocolo updatedProtocolo = protocoloRepository.findById(protocolo.getId()).get();
        // Disconnect from session so that the updates on updatedProtocolo are not directly saved in db
        em.detach(updatedProtocolo);
        updatedProtocolo
            .solicitante(UPDATED_SOLICITANTE)
            .dataSolicitacao(UPDATED_DATA_SOLICITACAO)
            .dataEnvio(UPDATED_DATA_ENVIO)
            .enviadoPor(UPDATED_ENVIADO_POR)
            .dataCriacao(UPDATED_DATA_CRIACAO)
            .usuarioCriacao(UPDATED_USUARIO_CRIACAO)
            .localizacao(UPDATED_LOCALIZACAO)
            .observacao(UPDATED_OBSERVACAO)
            .nomenclatura(UPDATED_NOMENCLATURA)
            .formato(UPDATED_FORMATO);

        restProtocoloMockMvc.perform(put("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProtocolo)))
            .andExpect(status().isOk());

        // Validate the Protocolo in the database
        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeUpdate);
        Protocolo testProtocolo = protocoloList.get(protocoloList.size() - 1);
        assertThat(testProtocolo.getSolicitante()).isEqualTo(UPDATED_SOLICITANTE);
        assertThat(testProtocolo.getDataSolicitacao()).isEqualTo(UPDATED_DATA_SOLICITACAO);
        assertThat(testProtocolo.getDataEnvio()).isEqualTo(UPDATED_DATA_ENVIO);
        assertThat(testProtocolo.getEnviadoPor()).isEqualTo(UPDATED_ENVIADO_POR);
        assertThat(testProtocolo.getDataCriacao()).isEqualTo(UPDATED_DATA_CRIACAO);
        assertThat(testProtocolo.getUsuarioCriacao()).isEqualTo(UPDATED_USUARIO_CRIACAO);
        assertThat(testProtocolo.getLocalizacao()).isEqualTo(UPDATED_LOCALIZACAO);
        assertThat(testProtocolo.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
        assertThat(testProtocolo.getNomenclatura()).isEqualTo(UPDATED_NOMENCLATURA);
        assertThat(testProtocolo.getFormato()).isEqualTo(UPDATED_FORMATO);
    }

    @Test
    @Transactional
    public void updateNonExistingProtocolo() throws Exception {
        int databaseSizeBeforeUpdate = protocoloRepository.findAll().size();

        // Create the Protocolo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProtocoloMockMvc.perform(put("/api/protocolos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(protocolo)))
            .andExpect(status().isBadRequest());

        // Validate the Protocolo in the database
        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProtocolo() throws Exception {
        // Initialize the database
        protocoloService.save(protocolo);

        int databaseSizeBeforeDelete = protocoloRepository.findAll().size();

        // Delete the protocolo
        restProtocoloMockMvc.perform(delete("/api/protocolos/{id}", protocolo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Protocolo> protocoloList = protocoloRepository.findAll();
        assertThat(protocoloList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
