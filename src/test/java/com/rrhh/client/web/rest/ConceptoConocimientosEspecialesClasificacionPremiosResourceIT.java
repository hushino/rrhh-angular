package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.repository.ConceptoConocimientosEspecialesClasificacionPremiosRepository;
import com.rrhh.client.service.ConceptoConocimientosEspecialesClasificacionPremiosService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.ConceptoConocimientosEspecialesClasificacionPremiosCriteria;
import com.rrhh.client.service.ConceptoConocimientosEspecialesClasificacionPremiosQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.rrhh.client.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ConceptoConocimientosEspecialesClasificacionPremiosResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class ConceptoConocimientosEspecialesClasificacionPremiosResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_REFERENCIAS = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIAS = "BBBBBBBBBB";

    @Autowired
    private ConceptoConocimientosEspecialesClasificacionPremiosRepository conceptoConocimientosEspecialesClasificacionPremiosRepository;

    @Autowired
    private ConceptoConocimientosEspecialesClasificacionPremiosService conceptoConocimientosEspecialesClasificacionPremiosService;

    @Autowired
    private ConceptoConocimientosEspecialesClasificacionPremiosQueryService conceptoConocimientosEspecialesClasificacionPremiosQueryService;

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

    private MockMvc restConceptoConocimientosEspecialesClasificacionPremiosMockMvc;

    private ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConceptoConocimientosEspecialesClasificacionPremiosResource conceptoConocimientosEspecialesClasificacionPremiosResource = new ConceptoConocimientosEspecialesClasificacionPremiosResource(conceptoConocimientosEspecialesClasificacionPremiosService, conceptoConocimientosEspecialesClasificacionPremiosQueryService);
        this.restConceptoConocimientosEspecialesClasificacionPremiosMockMvc = MockMvcBuilders.standaloneSetup(conceptoConocimientosEspecialesClasificacionPremiosResource)
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
    public static ConceptoConocimientosEspecialesClasificacionPremios createEntity(EntityManager em) {
        ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios = new ConceptoConocimientosEspecialesClasificacionPremios()
            .fecha(DEFAULT_FECHA)
            .referencias(DEFAULT_REFERENCIAS);
        return conceptoConocimientosEspecialesClasificacionPremios;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConceptoConocimientosEspecialesClasificacionPremios createUpdatedEntity(EntityManager em) {
        ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios = new ConceptoConocimientosEspecialesClasificacionPremios()
            .fecha(UPDATED_FECHA)
            .referencias(UPDATED_REFERENCIAS);
        return conceptoConocimientosEspecialesClasificacionPremios;
    }

    @BeforeEach
    public void initTest() {
        conceptoConocimientosEspecialesClasificacionPremios = createEntity(em);
    }

    @Test
    @Transactional
    public void createConceptoConocimientosEspecialesClasificacionPremios() throws Exception {
        int databaseSizeBeforeCreate = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll().size();

        // Create the ConceptoConocimientosEspecialesClasificacionPremios
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(post("/api/concepto-conocimientos-especiales-clasificacion-premios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptoConocimientosEspecialesClasificacionPremios)))
            .andExpect(status().isCreated());

        // Validate the ConceptoConocimientosEspecialesClasificacionPremios in the database
        List<ConceptoConocimientosEspecialesClasificacionPremios> conceptoConocimientosEspecialesClasificacionPremiosList = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll();
        assertThat(conceptoConocimientosEspecialesClasificacionPremiosList).hasSize(databaseSizeBeforeCreate + 1);
        ConceptoConocimientosEspecialesClasificacionPremios testConceptoConocimientosEspecialesClasificacionPremios = conceptoConocimientosEspecialesClasificacionPremiosList.get(conceptoConocimientosEspecialesClasificacionPremiosList.size() - 1);
        assertThat(testConceptoConocimientosEspecialesClasificacionPremios.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testConceptoConocimientosEspecialesClasificacionPremios.getReferencias()).isEqualTo(DEFAULT_REFERENCIAS);
    }

    @Test
    @Transactional
    public void createConceptoConocimientosEspecialesClasificacionPremiosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll().size();

        // Create the ConceptoConocimientosEspecialesClasificacionPremios with an existing ID
        conceptoConocimientosEspecialesClasificacionPremios.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(post("/api/concepto-conocimientos-especiales-clasificacion-premios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptoConocimientosEspecialesClasificacionPremios)))
            .andExpect(status().isBadRequest());

        // Validate the ConceptoConocimientosEspecialesClasificacionPremios in the database
        List<ConceptoConocimientosEspecialesClasificacionPremios> conceptoConocimientosEspecialesClasificacionPremiosList = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll();
        assertThat(conceptoConocimientosEspecialesClasificacionPremiosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremios() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(get("/api/concepto-conocimientos-especiales-clasificacion-premios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conceptoConocimientosEspecialesClasificacionPremios.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));
    }
    
    @Test
    @Transactional
    public void getConceptoConocimientosEspecialesClasificacionPremios() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get the conceptoConocimientosEspecialesClasificacionPremios
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(get("/api/concepto-conocimientos-especiales-clasificacion-premios/{id}", conceptoConocimientosEspecialesClasificacionPremios.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conceptoConocimientosEspecialesClasificacionPremios.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.referencias").value(DEFAULT_REFERENCIAS));
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha equals to DEFAULT_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha equals to UPDATED_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha not equals to DEFAULT_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha not equals to UPDATED_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha equals to UPDATED_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is not null
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.specified=true");

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is null
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is greater than or equal to DEFAULT_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is greater than or equal to UPDATED_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is less than or equal to DEFAULT_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is less than or equal to SMALLER_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is less than DEFAULT_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is less than UPDATED_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is greater than DEFAULT_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where fecha is greater than SMALLER_FECHA
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }


    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByReferenciasIsEqualToSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias equals to DEFAULT_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("referencias.equals=" + DEFAULT_REFERENCIAS);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias equals to UPDATED_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("referencias.equals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByReferenciasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias not equals to DEFAULT_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("referencias.notEquals=" + DEFAULT_REFERENCIAS);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias not equals to UPDATED_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("referencias.notEquals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByReferenciasIsInShouldWork() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias in DEFAULT_REFERENCIAS or UPDATED_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("referencias.in=" + DEFAULT_REFERENCIAS + "," + UPDATED_REFERENCIAS);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias equals to UPDATED_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("referencias.in=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByReferenciasIsNullOrNotNull() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias is not null
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("referencias.specified=true");

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias is null
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("referencias.specified=false");
    }
                @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByReferenciasContainsSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias contains DEFAULT_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("referencias.contains=" + DEFAULT_REFERENCIAS);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias contains UPDATED_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("referencias.contains=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByReferenciasNotContainsSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias does not contain DEFAULT_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("referencias.doesNotContain=" + DEFAULT_REFERENCIAS);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where referencias does not contain UPDATED_REFERENCIAS
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("referencias.doesNotContain=" + UPDATED_REFERENCIAS);
    }


    @Test
    @Transactional
    public void getAllConceptoConocimientosEspecialesClasificacionPremiosByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        conceptoConocimientosEspecialesClasificacionPremios.setPersona(persona);
        conceptoConocimientosEspecialesClasificacionPremiosRepository.saveAndFlush(conceptoConocimientosEspecialesClasificacionPremios);
        Long personaId = persona.getId();

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where persona equals to personaId
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound("personaId.equals=" + personaId);

        // Get all the conceptoConocimientosEspecialesClasificacionPremiosList where persona equals to personaId + 1
        defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultConceptoConocimientosEspecialesClasificacionPremiosShouldBeFound(String filter) throws Exception {
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(get("/api/concepto-conocimientos-especiales-clasificacion-premios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conceptoConocimientosEspecialesClasificacionPremios.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));

        // Check, that the count call also returns 1
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(get("/api/concepto-conocimientos-especiales-clasificacion-premios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultConceptoConocimientosEspecialesClasificacionPremiosShouldNotBeFound(String filter) throws Exception {
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(get("/api/concepto-conocimientos-especiales-clasificacion-premios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(get("/api/concepto-conocimientos-especiales-clasificacion-premios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingConceptoConocimientosEspecialesClasificacionPremios() throws Exception {
        // Get the conceptoConocimientosEspecialesClasificacionPremios
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(get("/api/concepto-conocimientos-especiales-clasificacion-premios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConceptoConocimientosEspecialesClasificacionPremios() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosService.save(conceptoConocimientosEspecialesClasificacionPremios);

        int databaseSizeBeforeUpdate = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll().size();

        // Update the conceptoConocimientosEspecialesClasificacionPremios
        ConceptoConocimientosEspecialesClasificacionPremios updatedConceptoConocimientosEspecialesClasificacionPremios = conceptoConocimientosEspecialesClasificacionPremiosRepository.findById(conceptoConocimientosEspecialesClasificacionPremios.getId()).get();
        // Disconnect from session so that the updates on updatedConceptoConocimientosEspecialesClasificacionPremios are not directly saved in db
        em.detach(updatedConceptoConocimientosEspecialesClasificacionPremios);
        updatedConceptoConocimientosEspecialesClasificacionPremios
            .fecha(UPDATED_FECHA)
            .referencias(UPDATED_REFERENCIAS);

        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(put("/api/concepto-conocimientos-especiales-clasificacion-premios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConceptoConocimientosEspecialesClasificacionPremios)))
            .andExpect(status().isOk());

        // Validate the ConceptoConocimientosEspecialesClasificacionPremios in the database
        List<ConceptoConocimientosEspecialesClasificacionPremios> conceptoConocimientosEspecialesClasificacionPremiosList = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll();
        assertThat(conceptoConocimientosEspecialesClasificacionPremiosList).hasSize(databaseSizeBeforeUpdate);
        ConceptoConocimientosEspecialesClasificacionPremios testConceptoConocimientosEspecialesClasificacionPremios = conceptoConocimientosEspecialesClasificacionPremiosList.get(conceptoConocimientosEspecialesClasificacionPremiosList.size() - 1);
        assertThat(testConceptoConocimientosEspecialesClasificacionPremios.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testConceptoConocimientosEspecialesClasificacionPremios.getReferencias()).isEqualTo(UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void updateNonExistingConceptoConocimientosEspecialesClasificacionPremios() throws Exception {
        int databaseSizeBeforeUpdate = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll().size();

        // Create the ConceptoConocimientosEspecialesClasificacionPremios

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(put("/api/concepto-conocimientos-especiales-clasificacion-premios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptoConocimientosEspecialesClasificacionPremios)))
            .andExpect(status().isBadRequest());

        // Validate the ConceptoConocimientosEspecialesClasificacionPremios in the database
        List<ConceptoConocimientosEspecialesClasificacionPremios> conceptoConocimientosEspecialesClasificacionPremiosList = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll();
        assertThat(conceptoConocimientosEspecialesClasificacionPremiosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConceptoConocimientosEspecialesClasificacionPremios() throws Exception {
        // Initialize the database
        conceptoConocimientosEspecialesClasificacionPremiosService.save(conceptoConocimientosEspecialesClasificacionPremios);

        int databaseSizeBeforeDelete = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll().size();

        // Delete the conceptoConocimientosEspecialesClasificacionPremios
        restConceptoConocimientosEspecialesClasificacionPremiosMockMvc.perform(delete("/api/concepto-conocimientos-especiales-clasificacion-premios/{id}", conceptoConocimientosEspecialesClasificacionPremios.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConceptoConocimientosEspecialesClasificacionPremios> conceptoConocimientosEspecialesClasificacionPremiosList = conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll();
        assertThat(conceptoConocimientosEspecialesClasificacionPremiosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConceptoConocimientosEspecialesClasificacionPremios.class);
        ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios1 = new ConceptoConocimientosEspecialesClasificacionPremios();
        conceptoConocimientosEspecialesClasificacionPremios1.setId(1L);
        ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios2 = new ConceptoConocimientosEspecialesClasificacionPremios();
        conceptoConocimientosEspecialesClasificacionPremios2.setId(conceptoConocimientosEspecialesClasificacionPremios1.getId());
        assertThat(conceptoConocimientosEspecialesClasificacionPremios1).isEqualTo(conceptoConocimientosEspecialesClasificacionPremios2);
        conceptoConocimientosEspecialesClasificacionPremios2.setId(2L);
        assertThat(conceptoConocimientosEspecialesClasificacionPremios1).isNotEqualTo(conceptoConocimientosEspecialesClasificacionPremios2);
        conceptoConocimientosEspecialesClasificacionPremios1.setId(null);
        assertThat(conceptoConocimientosEspecialesClasificacionPremios1).isNotEqualTo(conceptoConocimientosEspecialesClasificacionPremios2);
    }
}
