package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.Licencia;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.repository.LicenciaRepository;
import com.rrhh.client.service.LicenciaService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.LicenciaCriteria;
import com.rrhh.client.service.LicenciaQueryService;

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
 * Integration tests for the {@link LicenciaResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class LicenciaResourceIT {

    private static final LocalDate DEFAULT_FECHA_LICENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_LICENCIA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_LICENCIA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_REFERENCIAS = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIAS = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_DE_DIAS = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_DE_DIAS = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_USUARIOS_MOD = "AAAAAAAAAA";
    private static final String UPDATED_USUARIOS_MOD = "BBBBBBBBBB";

    @Autowired
    private LicenciaRepository licenciaRepository;

    @Autowired
    private LicenciaService licenciaService;

    @Autowired
    private LicenciaQueryService licenciaQueryService;

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

    private MockMvc restLicenciaMockMvc;

    private Licencia licencia;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LicenciaResource licenciaResource = new LicenciaResource(licenciaService, licenciaQueryService);
        this.restLicenciaMockMvc = MockMvcBuilders.standaloneSetup(licenciaResource)
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
    public static Licencia createEntity(EntityManager em) {
        Licencia licencia = new Licencia()
            .fechaLicencia(DEFAULT_FECHA_LICENCIA)
            .referencias(DEFAULT_REFERENCIAS)
            .numeroDeDias(DEFAULT_NUMERO_DE_DIAS)
            .observaciones(DEFAULT_OBSERVACIONES)
            .usuariosMod(DEFAULT_USUARIOS_MOD);
        return licencia;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Licencia createUpdatedEntity(EntityManager em) {
        Licencia licencia = new Licencia()
            .fechaLicencia(UPDATED_FECHA_LICENCIA)
            .referencias(UPDATED_REFERENCIAS)
            .numeroDeDias(UPDATED_NUMERO_DE_DIAS)
            .observaciones(UPDATED_OBSERVACIONES)
            .usuariosMod(UPDATED_USUARIOS_MOD);
        return licencia;
    }

    @BeforeEach
    public void initTest() {
        licencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createLicencia() throws Exception {
        int databaseSizeBeforeCreate = licenciaRepository.findAll().size();

        // Create the Licencia
        restLicenciaMockMvc.perform(post("/api/licencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(licencia)))
            .andExpect(status().isCreated());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Licencia testLicencia = licenciaList.get(licenciaList.size() - 1);
        assertThat(testLicencia.getFechaLicencia()).isEqualTo(DEFAULT_FECHA_LICENCIA);
        assertThat(testLicencia.getReferencias()).isEqualTo(DEFAULT_REFERENCIAS);
        assertThat(testLicencia.getNumeroDeDias()).isEqualTo(DEFAULT_NUMERO_DE_DIAS);
        assertThat(testLicencia.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testLicencia.getUsuariosMod()).isEqualTo(DEFAULT_USUARIOS_MOD);
    }

    @Test
    @Transactional
    public void createLicenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = licenciaRepository.findAll().size();

        // Create the Licencia with an existing ID
        licencia.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLicenciaMockMvc.perform(post("/api/licencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(licencia)))
            .andExpect(status().isBadRequest());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLicencias() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList
        restLicenciaMockMvc.perform(get("/api/licencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(licencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaLicencia").value(hasItem(DEFAULT_FECHA_LICENCIA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)))
            .andExpect(jsonPath("$.[*].numeroDeDias").value(hasItem(DEFAULT_NUMERO_DE_DIAS)))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].usuariosMod").value(hasItem(DEFAULT_USUARIOS_MOD)));
    }
    
    @Test
    @Transactional
    public void getLicencia() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get the licencia
        restLicenciaMockMvc.perform(get("/api/licencias/{id}", licencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(licencia.getId().intValue()))
            .andExpect(jsonPath("$.fechaLicencia").value(DEFAULT_FECHA_LICENCIA.toString()))
            .andExpect(jsonPath("$.referencias").value(DEFAULT_REFERENCIAS))
            .andExpect(jsonPath("$.numeroDeDias").value(DEFAULT_NUMERO_DE_DIAS))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES))
            .andExpect(jsonPath("$.usuariosMod").value(DEFAULT_USUARIOS_MOD));
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia equals to DEFAULT_FECHA_LICENCIA
        defaultLicenciaShouldBeFound("fechaLicencia.equals=" + DEFAULT_FECHA_LICENCIA);

        // Get all the licenciaList where fechaLicencia equals to UPDATED_FECHA_LICENCIA
        defaultLicenciaShouldNotBeFound("fechaLicencia.equals=" + UPDATED_FECHA_LICENCIA);
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia not equals to DEFAULT_FECHA_LICENCIA
        defaultLicenciaShouldNotBeFound("fechaLicencia.notEquals=" + DEFAULT_FECHA_LICENCIA);

        // Get all the licenciaList where fechaLicencia not equals to UPDATED_FECHA_LICENCIA
        defaultLicenciaShouldBeFound("fechaLicencia.notEquals=" + UPDATED_FECHA_LICENCIA);
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsInShouldWork() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia in DEFAULT_FECHA_LICENCIA or UPDATED_FECHA_LICENCIA
        defaultLicenciaShouldBeFound("fechaLicencia.in=" + DEFAULT_FECHA_LICENCIA + "," + UPDATED_FECHA_LICENCIA);

        // Get all the licenciaList where fechaLicencia equals to UPDATED_FECHA_LICENCIA
        defaultLicenciaShouldNotBeFound("fechaLicencia.in=" + UPDATED_FECHA_LICENCIA);
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsNullOrNotNull() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia is not null
        defaultLicenciaShouldBeFound("fechaLicencia.specified=true");

        // Get all the licenciaList where fechaLicencia is null
        defaultLicenciaShouldNotBeFound("fechaLicencia.specified=false");
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia is greater than or equal to DEFAULT_FECHA_LICENCIA
        defaultLicenciaShouldBeFound("fechaLicencia.greaterThanOrEqual=" + DEFAULT_FECHA_LICENCIA);

        // Get all the licenciaList where fechaLicencia is greater than or equal to UPDATED_FECHA_LICENCIA
        defaultLicenciaShouldNotBeFound("fechaLicencia.greaterThanOrEqual=" + UPDATED_FECHA_LICENCIA);
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia is less than or equal to DEFAULT_FECHA_LICENCIA
        defaultLicenciaShouldBeFound("fechaLicencia.lessThanOrEqual=" + DEFAULT_FECHA_LICENCIA);

        // Get all the licenciaList where fechaLicencia is less than or equal to SMALLER_FECHA_LICENCIA
        defaultLicenciaShouldNotBeFound("fechaLicencia.lessThanOrEqual=" + SMALLER_FECHA_LICENCIA);
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsLessThanSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia is less than DEFAULT_FECHA_LICENCIA
        defaultLicenciaShouldNotBeFound("fechaLicencia.lessThan=" + DEFAULT_FECHA_LICENCIA);

        // Get all the licenciaList where fechaLicencia is less than UPDATED_FECHA_LICENCIA
        defaultLicenciaShouldBeFound("fechaLicencia.lessThan=" + UPDATED_FECHA_LICENCIA);
    }

    @Test
    @Transactional
    public void getAllLicenciasByFechaLicenciaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where fechaLicencia is greater than DEFAULT_FECHA_LICENCIA
        defaultLicenciaShouldNotBeFound("fechaLicencia.greaterThan=" + DEFAULT_FECHA_LICENCIA);

        // Get all the licenciaList where fechaLicencia is greater than SMALLER_FECHA_LICENCIA
        defaultLicenciaShouldBeFound("fechaLicencia.greaterThan=" + SMALLER_FECHA_LICENCIA);
    }


    @Test
    @Transactional
    public void getAllLicenciasByReferenciasIsEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where referencias equals to DEFAULT_REFERENCIAS
        defaultLicenciaShouldBeFound("referencias.equals=" + DEFAULT_REFERENCIAS);

        // Get all the licenciaList where referencias equals to UPDATED_REFERENCIAS
        defaultLicenciaShouldNotBeFound("referencias.equals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByReferenciasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where referencias not equals to DEFAULT_REFERENCIAS
        defaultLicenciaShouldNotBeFound("referencias.notEquals=" + DEFAULT_REFERENCIAS);

        // Get all the licenciaList where referencias not equals to UPDATED_REFERENCIAS
        defaultLicenciaShouldBeFound("referencias.notEquals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByReferenciasIsInShouldWork() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where referencias in DEFAULT_REFERENCIAS or UPDATED_REFERENCIAS
        defaultLicenciaShouldBeFound("referencias.in=" + DEFAULT_REFERENCIAS + "," + UPDATED_REFERENCIAS);

        // Get all the licenciaList where referencias equals to UPDATED_REFERENCIAS
        defaultLicenciaShouldNotBeFound("referencias.in=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByReferenciasIsNullOrNotNull() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where referencias is not null
        defaultLicenciaShouldBeFound("referencias.specified=true");

        // Get all the licenciaList where referencias is null
        defaultLicenciaShouldNotBeFound("referencias.specified=false");
    }
                @Test
    @Transactional
    public void getAllLicenciasByReferenciasContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where referencias contains DEFAULT_REFERENCIAS
        defaultLicenciaShouldBeFound("referencias.contains=" + DEFAULT_REFERENCIAS);

        // Get all the licenciaList where referencias contains UPDATED_REFERENCIAS
        defaultLicenciaShouldNotBeFound("referencias.contains=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByReferenciasNotContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where referencias does not contain DEFAULT_REFERENCIAS
        defaultLicenciaShouldNotBeFound("referencias.doesNotContain=" + DEFAULT_REFERENCIAS);

        // Get all the licenciaList where referencias does not contain UPDATED_REFERENCIAS
        defaultLicenciaShouldBeFound("referencias.doesNotContain=" + UPDATED_REFERENCIAS);
    }


    @Test
    @Transactional
    public void getAllLicenciasByNumeroDeDiasIsEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where numeroDeDias equals to DEFAULT_NUMERO_DE_DIAS
        defaultLicenciaShouldBeFound("numeroDeDias.equals=" + DEFAULT_NUMERO_DE_DIAS);

        // Get all the licenciaList where numeroDeDias equals to UPDATED_NUMERO_DE_DIAS
        defaultLicenciaShouldNotBeFound("numeroDeDias.equals=" + UPDATED_NUMERO_DE_DIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByNumeroDeDiasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where numeroDeDias not equals to DEFAULT_NUMERO_DE_DIAS
        defaultLicenciaShouldNotBeFound("numeroDeDias.notEquals=" + DEFAULT_NUMERO_DE_DIAS);

        // Get all the licenciaList where numeroDeDias not equals to UPDATED_NUMERO_DE_DIAS
        defaultLicenciaShouldBeFound("numeroDeDias.notEquals=" + UPDATED_NUMERO_DE_DIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByNumeroDeDiasIsInShouldWork() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where numeroDeDias in DEFAULT_NUMERO_DE_DIAS or UPDATED_NUMERO_DE_DIAS
        defaultLicenciaShouldBeFound("numeroDeDias.in=" + DEFAULT_NUMERO_DE_DIAS + "," + UPDATED_NUMERO_DE_DIAS);

        // Get all the licenciaList where numeroDeDias equals to UPDATED_NUMERO_DE_DIAS
        defaultLicenciaShouldNotBeFound("numeroDeDias.in=" + UPDATED_NUMERO_DE_DIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByNumeroDeDiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where numeroDeDias is not null
        defaultLicenciaShouldBeFound("numeroDeDias.specified=true");

        // Get all the licenciaList where numeroDeDias is null
        defaultLicenciaShouldNotBeFound("numeroDeDias.specified=false");
    }
                @Test
    @Transactional
    public void getAllLicenciasByNumeroDeDiasContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where numeroDeDias contains DEFAULT_NUMERO_DE_DIAS
        defaultLicenciaShouldBeFound("numeroDeDias.contains=" + DEFAULT_NUMERO_DE_DIAS);

        // Get all the licenciaList where numeroDeDias contains UPDATED_NUMERO_DE_DIAS
        defaultLicenciaShouldNotBeFound("numeroDeDias.contains=" + UPDATED_NUMERO_DE_DIAS);
    }

    @Test
    @Transactional
    public void getAllLicenciasByNumeroDeDiasNotContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where numeroDeDias does not contain DEFAULT_NUMERO_DE_DIAS
        defaultLicenciaShouldNotBeFound("numeroDeDias.doesNotContain=" + DEFAULT_NUMERO_DE_DIAS);

        // Get all the licenciaList where numeroDeDias does not contain UPDATED_NUMERO_DE_DIAS
        defaultLicenciaShouldBeFound("numeroDeDias.doesNotContain=" + UPDATED_NUMERO_DE_DIAS);
    }


    @Test
    @Transactional
    public void getAllLicenciasByObservacionesIsEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where observaciones equals to DEFAULT_OBSERVACIONES
        defaultLicenciaShouldBeFound("observaciones.equals=" + DEFAULT_OBSERVACIONES);

        // Get all the licenciaList where observaciones equals to UPDATED_OBSERVACIONES
        defaultLicenciaShouldNotBeFound("observaciones.equals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllLicenciasByObservacionesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where observaciones not equals to DEFAULT_OBSERVACIONES
        defaultLicenciaShouldNotBeFound("observaciones.notEquals=" + DEFAULT_OBSERVACIONES);

        // Get all the licenciaList where observaciones not equals to UPDATED_OBSERVACIONES
        defaultLicenciaShouldBeFound("observaciones.notEquals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllLicenciasByObservacionesIsInShouldWork() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where observaciones in DEFAULT_OBSERVACIONES or UPDATED_OBSERVACIONES
        defaultLicenciaShouldBeFound("observaciones.in=" + DEFAULT_OBSERVACIONES + "," + UPDATED_OBSERVACIONES);

        // Get all the licenciaList where observaciones equals to UPDATED_OBSERVACIONES
        defaultLicenciaShouldNotBeFound("observaciones.in=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllLicenciasByObservacionesIsNullOrNotNull() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where observaciones is not null
        defaultLicenciaShouldBeFound("observaciones.specified=true");

        // Get all the licenciaList where observaciones is null
        defaultLicenciaShouldNotBeFound("observaciones.specified=false");
    }
                @Test
    @Transactional
    public void getAllLicenciasByObservacionesContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where observaciones contains DEFAULT_OBSERVACIONES
        defaultLicenciaShouldBeFound("observaciones.contains=" + DEFAULT_OBSERVACIONES);

        // Get all the licenciaList where observaciones contains UPDATED_OBSERVACIONES
        defaultLicenciaShouldNotBeFound("observaciones.contains=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllLicenciasByObservacionesNotContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where observaciones does not contain DEFAULT_OBSERVACIONES
        defaultLicenciaShouldNotBeFound("observaciones.doesNotContain=" + DEFAULT_OBSERVACIONES);

        // Get all the licenciaList where observaciones does not contain UPDATED_OBSERVACIONES
        defaultLicenciaShouldBeFound("observaciones.doesNotContain=" + UPDATED_OBSERVACIONES);
    }


    @Test
    @Transactional
    public void getAllLicenciasByUsuariosModIsEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where usuariosMod equals to DEFAULT_USUARIOS_MOD
        defaultLicenciaShouldBeFound("usuariosMod.equals=" + DEFAULT_USUARIOS_MOD);

        // Get all the licenciaList where usuariosMod equals to UPDATED_USUARIOS_MOD
        defaultLicenciaShouldNotBeFound("usuariosMod.equals=" + UPDATED_USUARIOS_MOD);
    }

    @Test
    @Transactional
    public void getAllLicenciasByUsuariosModIsNotEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where usuariosMod not equals to DEFAULT_USUARIOS_MOD
        defaultLicenciaShouldNotBeFound("usuariosMod.notEquals=" + DEFAULT_USUARIOS_MOD);

        // Get all the licenciaList where usuariosMod not equals to UPDATED_USUARIOS_MOD
        defaultLicenciaShouldBeFound("usuariosMod.notEquals=" + UPDATED_USUARIOS_MOD);
    }

    @Test
    @Transactional
    public void getAllLicenciasByUsuariosModIsInShouldWork() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where usuariosMod in DEFAULT_USUARIOS_MOD or UPDATED_USUARIOS_MOD
        defaultLicenciaShouldBeFound("usuariosMod.in=" + DEFAULT_USUARIOS_MOD + "," + UPDATED_USUARIOS_MOD);

        // Get all the licenciaList where usuariosMod equals to UPDATED_USUARIOS_MOD
        defaultLicenciaShouldNotBeFound("usuariosMod.in=" + UPDATED_USUARIOS_MOD);
    }

    @Test
    @Transactional
    public void getAllLicenciasByUsuariosModIsNullOrNotNull() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where usuariosMod is not null
        defaultLicenciaShouldBeFound("usuariosMod.specified=true");

        // Get all the licenciaList where usuariosMod is null
        defaultLicenciaShouldNotBeFound("usuariosMod.specified=false");
    }
                @Test
    @Transactional
    public void getAllLicenciasByUsuariosModContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where usuariosMod contains DEFAULT_USUARIOS_MOD
        defaultLicenciaShouldBeFound("usuariosMod.contains=" + DEFAULT_USUARIOS_MOD);

        // Get all the licenciaList where usuariosMod contains UPDATED_USUARIOS_MOD
        defaultLicenciaShouldNotBeFound("usuariosMod.contains=" + UPDATED_USUARIOS_MOD);
    }

    @Test
    @Transactional
    public void getAllLicenciasByUsuariosModNotContainsSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);

        // Get all the licenciaList where usuariosMod does not contain DEFAULT_USUARIOS_MOD
        defaultLicenciaShouldNotBeFound("usuariosMod.doesNotContain=" + DEFAULT_USUARIOS_MOD);

        // Get all the licenciaList where usuariosMod does not contain UPDATED_USUARIOS_MOD
        defaultLicenciaShouldBeFound("usuariosMod.doesNotContain=" + UPDATED_USUARIOS_MOD);
    }


    @Test
    @Transactional
    public void getAllLicenciasByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        licenciaRepository.saveAndFlush(licencia);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        licencia.setPersona(persona);
        licenciaRepository.saveAndFlush(licencia);
        Long personaId = persona.getId();

        // Get all the licenciaList where persona equals to personaId
        defaultLicenciaShouldBeFound("personaId.equals=" + personaId);

        // Get all the licenciaList where persona equals to personaId + 1
        defaultLicenciaShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLicenciaShouldBeFound(String filter) throws Exception {
        restLicenciaMockMvc.perform(get("/api/licencias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(licencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaLicencia").value(hasItem(DEFAULT_FECHA_LICENCIA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)))
            .andExpect(jsonPath("$.[*].numeroDeDias").value(hasItem(DEFAULT_NUMERO_DE_DIAS)))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].usuariosMod").value(hasItem(DEFAULT_USUARIOS_MOD)));

        // Check, that the count call also returns 1
        restLicenciaMockMvc.perform(get("/api/licencias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLicenciaShouldNotBeFound(String filter) throws Exception {
        restLicenciaMockMvc.perform(get("/api/licencias?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLicenciaMockMvc.perform(get("/api/licencias/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLicencia() throws Exception {
        // Get the licencia
        restLicenciaMockMvc.perform(get("/api/licencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLicencia() throws Exception {
        // Initialize the database
        licenciaService.save(licencia);

        int databaseSizeBeforeUpdate = licenciaRepository.findAll().size();

        // Update the licencia
        Licencia updatedLicencia = licenciaRepository.findById(licencia.getId()).get();
        // Disconnect from session so that the updates on updatedLicencia are not directly saved in db
        em.detach(updatedLicencia);
        updatedLicencia
            .fechaLicencia(UPDATED_FECHA_LICENCIA)
            .referencias(UPDATED_REFERENCIAS)
            .numeroDeDias(UPDATED_NUMERO_DE_DIAS)
            .observaciones(UPDATED_OBSERVACIONES)
            .usuariosMod(UPDATED_USUARIOS_MOD);

        restLicenciaMockMvc.perform(put("/api/licencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLicencia)))
            .andExpect(status().isOk());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeUpdate);
        Licencia testLicencia = licenciaList.get(licenciaList.size() - 1);
        assertThat(testLicencia.getFechaLicencia()).isEqualTo(UPDATED_FECHA_LICENCIA);
        assertThat(testLicencia.getReferencias()).isEqualTo(UPDATED_REFERENCIAS);
        assertThat(testLicencia.getNumeroDeDias()).isEqualTo(UPDATED_NUMERO_DE_DIAS);
        assertThat(testLicencia.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testLicencia.getUsuariosMod()).isEqualTo(UPDATED_USUARIOS_MOD);
    }

    @Test
    @Transactional
    public void updateNonExistingLicencia() throws Exception {
        int databaseSizeBeforeUpdate = licenciaRepository.findAll().size();

        // Create the Licencia

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLicenciaMockMvc.perform(put("/api/licencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(licencia)))
            .andExpect(status().isBadRequest());

        // Validate the Licencia in the database
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLicencia() throws Exception {
        // Initialize the database
        licenciaService.save(licencia);

        int databaseSizeBeforeDelete = licenciaRepository.findAll().size();

        // Delete the licencia
        restLicenciaMockMvc.perform(delete("/api/licencias/{id}", licencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Licencia> licenciaList = licenciaRepository.findAll();
        assertThat(licenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Licencia.class);
        Licencia licencia1 = new Licencia();
        licencia1.setId(1L);
        Licencia licencia2 = new Licencia();
        licencia2.setId(licencia1.getId());
        assertThat(licencia1).isEqualTo(licencia2);
        licencia2.setId(2L);
        assertThat(licencia1).isNotEqualTo(licencia2);
        licencia1.setId(null);
        assertThat(licencia1).isNotEqualTo(licencia2);
    }
}
