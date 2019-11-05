package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.PenasDisciplinariasSufridas;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.repository.PenasDisciplinariasSufridasRepository;
import com.rrhh.client.service.PenasDisciplinariasSufridasService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.PenasDisciplinariasSufridasCriteria;
import com.rrhh.client.service.PenasDisciplinariasSufridasQueryService;

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
 * Integration tests for the {@link PenasDisciplinariasSufridasResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class PenasDisciplinariasSufridasResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_EXPEDIENTE = "AAAAAAAAAA";
    private static final String UPDATED_EXPEDIENTE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCIAS = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIAS = "BBBBBBBBBB";

    @Autowired
    private PenasDisciplinariasSufridasRepository penasDisciplinariasSufridasRepository;

    @Autowired
    private PenasDisciplinariasSufridasService penasDisciplinariasSufridasService;

    @Autowired
    private PenasDisciplinariasSufridasQueryService penasDisciplinariasSufridasQueryService;

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

    private MockMvc restPenasDisciplinariasSufridasMockMvc;

    private PenasDisciplinariasSufridas penasDisciplinariasSufridas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PenasDisciplinariasSufridasResource penasDisciplinariasSufridasResource = new PenasDisciplinariasSufridasResource(penasDisciplinariasSufridasService, penasDisciplinariasSufridasQueryService);
        this.restPenasDisciplinariasSufridasMockMvc = MockMvcBuilders.standaloneSetup(penasDisciplinariasSufridasResource)
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
    public static PenasDisciplinariasSufridas createEntity(EntityManager em) {
        PenasDisciplinariasSufridas penasDisciplinariasSufridas = new PenasDisciplinariasSufridas()
            .fecha(DEFAULT_FECHA)
            .expediente(DEFAULT_EXPEDIENTE)
            .referencias(DEFAULT_REFERENCIAS);
        return penasDisciplinariasSufridas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PenasDisciplinariasSufridas createUpdatedEntity(EntityManager em) {
        PenasDisciplinariasSufridas penasDisciplinariasSufridas = new PenasDisciplinariasSufridas()
            .fecha(UPDATED_FECHA)
            .expediente(UPDATED_EXPEDIENTE)
            .referencias(UPDATED_REFERENCIAS);
        return penasDisciplinariasSufridas;
    }

    @BeforeEach
    public void initTest() {
        penasDisciplinariasSufridas = createEntity(em);
    }

    @Test
    @Transactional
    public void createPenasDisciplinariasSufridas() throws Exception {
        int databaseSizeBeforeCreate = penasDisciplinariasSufridasRepository.findAll().size();

        // Create the PenasDisciplinariasSufridas
        restPenasDisciplinariasSufridasMockMvc.perform(post("/api/penas-disciplinarias-sufridas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penasDisciplinariasSufridas)))
            .andExpect(status().isCreated());

        // Validate the PenasDisciplinariasSufridas in the database
        List<PenasDisciplinariasSufridas> penasDisciplinariasSufridasList = penasDisciplinariasSufridasRepository.findAll();
        assertThat(penasDisciplinariasSufridasList).hasSize(databaseSizeBeforeCreate + 1);
        PenasDisciplinariasSufridas testPenasDisciplinariasSufridas = penasDisciplinariasSufridasList.get(penasDisciplinariasSufridasList.size() - 1);
        assertThat(testPenasDisciplinariasSufridas.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testPenasDisciplinariasSufridas.getExpediente()).isEqualTo(DEFAULT_EXPEDIENTE);
        assertThat(testPenasDisciplinariasSufridas.getReferencias()).isEqualTo(DEFAULT_REFERENCIAS);
    }

    @Test
    @Transactional
    public void createPenasDisciplinariasSufridasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = penasDisciplinariasSufridasRepository.findAll().size();

        // Create the PenasDisciplinariasSufridas with an existing ID
        penasDisciplinariasSufridas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPenasDisciplinariasSufridasMockMvc.perform(post("/api/penas-disciplinarias-sufridas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penasDisciplinariasSufridas)))
            .andExpect(status().isBadRequest());

        // Validate the PenasDisciplinariasSufridas in the database
        List<PenasDisciplinariasSufridas> penasDisciplinariasSufridasList = penasDisciplinariasSufridasRepository.findAll();
        assertThat(penasDisciplinariasSufridasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridas() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList
        restPenasDisciplinariasSufridasMockMvc.perform(get("/api/penas-disciplinarias-sufridas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(penasDisciplinariasSufridas.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].expediente").value(hasItem(DEFAULT_EXPEDIENTE)))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));
    }
    
    @Test
    @Transactional
    public void getPenasDisciplinariasSufridas() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get the penasDisciplinariasSufridas
        restPenasDisciplinariasSufridasMockMvc.perform(get("/api/penas-disciplinarias-sufridas/{id}", penasDisciplinariasSufridas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(penasDisciplinariasSufridas.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.expediente").value(DEFAULT_EXPEDIENTE))
            .andExpect(jsonPath("$.referencias").value(DEFAULT_REFERENCIAS));
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha equals to DEFAULT_FECHA
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the penasDisciplinariasSufridasList where fecha equals to UPDATED_FECHA
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha not equals to DEFAULT_FECHA
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the penasDisciplinariasSufridasList where fecha not equals to UPDATED_FECHA
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the penasDisciplinariasSufridasList where fecha equals to UPDATED_FECHA
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha is not null
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.specified=true");

        // Get all the penasDisciplinariasSufridasList where fecha is null
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha is greater than or equal to DEFAULT_FECHA
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the penasDisciplinariasSufridasList where fecha is greater than or equal to UPDATED_FECHA
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha is less than or equal to DEFAULT_FECHA
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the penasDisciplinariasSufridasList where fecha is less than or equal to SMALLER_FECHA
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha is less than DEFAULT_FECHA
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the penasDisciplinariasSufridasList where fecha is less than UPDATED_FECHA
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where fecha is greater than DEFAULT_FECHA
        defaultPenasDisciplinariasSufridasShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the penasDisciplinariasSufridasList where fecha is greater than SMALLER_FECHA
        defaultPenasDisciplinariasSufridasShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }


    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByExpedienteIsEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where expediente equals to DEFAULT_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldBeFound("expediente.equals=" + DEFAULT_EXPEDIENTE);

        // Get all the penasDisciplinariasSufridasList where expediente equals to UPDATED_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldNotBeFound("expediente.equals=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByExpedienteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where expediente not equals to DEFAULT_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldNotBeFound("expediente.notEquals=" + DEFAULT_EXPEDIENTE);

        // Get all the penasDisciplinariasSufridasList where expediente not equals to UPDATED_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldBeFound("expediente.notEquals=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByExpedienteIsInShouldWork() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where expediente in DEFAULT_EXPEDIENTE or UPDATED_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldBeFound("expediente.in=" + DEFAULT_EXPEDIENTE + "," + UPDATED_EXPEDIENTE);

        // Get all the penasDisciplinariasSufridasList where expediente equals to UPDATED_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldNotBeFound("expediente.in=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByExpedienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where expediente is not null
        defaultPenasDisciplinariasSufridasShouldBeFound("expediente.specified=true");

        // Get all the penasDisciplinariasSufridasList where expediente is null
        defaultPenasDisciplinariasSufridasShouldNotBeFound("expediente.specified=false");
    }
                @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByExpedienteContainsSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where expediente contains DEFAULT_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldBeFound("expediente.contains=" + DEFAULT_EXPEDIENTE);

        // Get all the penasDisciplinariasSufridasList where expediente contains UPDATED_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldNotBeFound("expediente.contains=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByExpedienteNotContainsSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where expediente does not contain DEFAULT_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldNotBeFound("expediente.doesNotContain=" + DEFAULT_EXPEDIENTE);

        // Get all the penasDisciplinariasSufridasList where expediente does not contain UPDATED_EXPEDIENTE
        defaultPenasDisciplinariasSufridasShouldBeFound("expediente.doesNotContain=" + UPDATED_EXPEDIENTE);
    }


    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByReferenciasIsEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where referencias equals to DEFAULT_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldBeFound("referencias.equals=" + DEFAULT_REFERENCIAS);

        // Get all the penasDisciplinariasSufridasList where referencias equals to UPDATED_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldNotBeFound("referencias.equals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByReferenciasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where referencias not equals to DEFAULT_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldNotBeFound("referencias.notEquals=" + DEFAULT_REFERENCIAS);

        // Get all the penasDisciplinariasSufridasList where referencias not equals to UPDATED_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldBeFound("referencias.notEquals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByReferenciasIsInShouldWork() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where referencias in DEFAULT_REFERENCIAS or UPDATED_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldBeFound("referencias.in=" + DEFAULT_REFERENCIAS + "," + UPDATED_REFERENCIAS);

        // Get all the penasDisciplinariasSufridasList where referencias equals to UPDATED_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldNotBeFound("referencias.in=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByReferenciasIsNullOrNotNull() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where referencias is not null
        defaultPenasDisciplinariasSufridasShouldBeFound("referencias.specified=true");

        // Get all the penasDisciplinariasSufridasList where referencias is null
        defaultPenasDisciplinariasSufridasShouldNotBeFound("referencias.specified=false");
    }
                @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByReferenciasContainsSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where referencias contains DEFAULT_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldBeFound("referencias.contains=" + DEFAULT_REFERENCIAS);

        // Get all the penasDisciplinariasSufridasList where referencias contains UPDATED_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldNotBeFound("referencias.contains=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByReferenciasNotContainsSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);

        // Get all the penasDisciplinariasSufridasList where referencias does not contain DEFAULT_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldNotBeFound("referencias.doesNotContain=" + DEFAULT_REFERENCIAS);

        // Get all the penasDisciplinariasSufridasList where referencias does not contain UPDATED_REFERENCIAS
        defaultPenasDisciplinariasSufridasShouldBeFound("referencias.doesNotContain=" + UPDATED_REFERENCIAS);
    }


    @Test
    @Transactional
    public void getAllPenasDisciplinariasSufridasByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        penasDisciplinariasSufridas.setPersona(persona);
        penasDisciplinariasSufridasRepository.saveAndFlush(penasDisciplinariasSufridas);
        Long personaId = persona.getId();

        // Get all the penasDisciplinariasSufridasList where persona equals to personaId
        defaultPenasDisciplinariasSufridasShouldBeFound("personaId.equals=" + personaId);

        // Get all the penasDisciplinariasSufridasList where persona equals to personaId + 1
        defaultPenasDisciplinariasSufridasShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPenasDisciplinariasSufridasShouldBeFound(String filter) throws Exception {
        restPenasDisciplinariasSufridasMockMvc.perform(get("/api/penas-disciplinarias-sufridas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(penasDisciplinariasSufridas.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].expediente").value(hasItem(DEFAULT_EXPEDIENTE)))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));

        // Check, that the count call also returns 1
        restPenasDisciplinariasSufridasMockMvc.perform(get("/api/penas-disciplinarias-sufridas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPenasDisciplinariasSufridasShouldNotBeFound(String filter) throws Exception {
        restPenasDisciplinariasSufridasMockMvc.perform(get("/api/penas-disciplinarias-sufridas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPenasDisciplinariasSufridasMockMvc.perform(get("/api/penas-disciplinarias-sufridas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPenasDisciplinariasSufridas() throws Exception {
        // Get the penasDisciplinariasSufridas
        restPenasDisciplinariasSufridasMockMvc.perform(get("/api/penas-disciplinarias-sufridas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePenasDisciplinariasSufridas() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasService.save(penasDisciplinariasSufridas);

        int databaseSizeBeforeUpdate = penasDisciplinariasSufridasRepository.findAll().size();

        // Update the penasDisciplinariasSufridas
        PenasDisciplinariasSufridas updatedPenasDisciplinariasSufridas = penasDisciplinariasSufridasRepository.findById(penasDisciplinariasSufridas.getId()).get();
        // Disconnect from session so that the updates on updatedPenasDisciplinariasSufridas are not directly saved in db
        em.detach(updatedPenasDisciplinariasSufridas);
        updatedPenasDisciplinariasSufridas
            .fecha(UPDATED_FECHA)
            .expediente(UPDATED_EXPEDIENTE)
            .referencias(UPDATED_REFERENCIAS);

        restPenasDisciplinariasSufridasMockMvc.perform(put("/api/penas-disciplinarias-sufridas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPenasDisciplinariasSufridas)))
            .andExpect(status().isOk());

        // Validate the PenasDisciplinariasSufridas in the database
        List<PenasDisciplinariasSufridas> penasDisciplinariasSufridasList = penasDisciplinariasSufridasRepository.findAll();
        assertThat(penasDisciplinariasSufridasList).hasSize(databaseSizeBeforeUpdate);
        PenasDisciplinariasSufridas testPenasDisciplinariasSufridas = penasDisciplinariasSufridasList.get(penasDisciplinariasSufridasList.size() - 1);
        assertThat(testPenasDisciplinariasSufridas.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testPenasDisciplinariasSufridas.getExpediente()).isEqualTo(UPDATED_EXPEDIENTE);
        assertThat(testPenasDisciplinariasSufridas.getReferencias()).isEqualTo(UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void updateNonExistingPenasDisciplinariasSufridas() throws Exception {
        int databaseSizeBeforeUpdate = penasDisciplinariasSufridasRepository.findAll().size();

        // Create the PenasDisciplinariasSufridas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPenasDisciplinariasSufridasMockMvc.perform(put("/api/penas-disciplinarias-sufridas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(penasDisciplinariasSufridas)))
            .andExpect(status().isBadRequest());

        // Validate the PenasDisciplinariasSufridas in the database
        List<PenasDisciplinariasSufridas> penasDisciplinariasSufridasList = penasDisciplinariasSufridasRepository.findAll();
        assertThat(penasDisciplinariasSufridasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePenasDisciplinariasSufridas() throws Exception {
        // Initialize the database
        penasDisciplinariasSufridasService.save(penasDisciplinariasSufridas);

        int databaseSizeBeforeDelete = penasDisciplinariasSufridasRepository.findAll().size();

        // Delete the penasDisciplinariasSufridas
        restPenasDisciplinariasSufridasMockMvc.perform(delete("/api/penas-disciplinarias-sufridas/{id}", penasDisciplinariasSufridas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PenasDisciplinariasSufridas> penasDisciplinariasSufridasList = penasDisciplinariasSufridasRepository.findAll();
        assertThat(penasDisciplinariasSufridasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PenasDisciplinariasSufridas.class);
        PenasDisciplinariasSufridas penasDisciplinariasSufridas1 = new PenasDisciplinariasSufridas();
        penasDisciplinariasSufridas1.setId(1L);
        PenasDisciplinariasSufridas penasDisciplinariasSufridas2 = new PenasDisciplinariasSufridas();
        penasDisciplinariasSufridas2.setId(penasDisciplinariasSufridas1.getId());
        assertThat(penasDisciplinariasSufridas1).isEqualTo(penasDisciplinariasSufridas2);
        penasDisciplinariasSufridas2.setId(2L);
        assertThat(penasDisciplinariasSufridas1).isNotEqualTo(penasDisciplinariasSufridas2);
        penasDisciplinariasSufridas1.setId(null);
        assertThat(penasDisciplinariasSufridas1).isNotEqualTo(penasDisciplinariasSufridas2);
    }
}
