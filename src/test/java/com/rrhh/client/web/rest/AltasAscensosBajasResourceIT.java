package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.AltasAscensosBajas;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.repository.AltasAscensosBajasRepository;
import com.rrhh.client.service.AltasAscensosBajasService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.AltasAscensosBajasCriteria;
import com.rrhh.client.service.AltasAscensosBajasQueryService;

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
 * Integration tests for the {@link AltasAscensosBajasResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class AltasAscensosBajasResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_CARGO = "AAAAAAAAAA";
    private static final String UPDATED_CARGO = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_EXPEDIENTE = "AAAAAAAAAA";
    private static final String UPDATED_EXPEDIENTE = "BBBBBBBBBB";

    private static final String DEFAULT_PRESTASERVICIOEN = "AAAAAAAAAA";
    private static final String UPDATED_PRESTASERVICIOEN = "BBBBBBBBBB";

    @Autowired
    private AltasAscensosBajasRepository altasAscensosBajasRepository;

    @Autowired
    private AltasAscensosBajasService altasAscensosBajasService;

    @Autowired
    private AltasAscensosBajasQueryService altasAscensosBajasQueryService;

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

    private MockMvc restAltasAscensosBajasMockMvc;

    private AltasAscensosBajas altasAscensosBajas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AltasAscensosBajasResource altasAscensosBajasResource = new AltasAscensosBajasResource(altasAscensosBajasService, altasAscensosBajasQueryService);
        this.restAltasAscensosBajasMockMvc = MockMvcBuilders.standaloneSetup(altasAscensosBajasResource)
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
    public static AltasAscensosBajas createEntity(EntityManager em) {
        AltasAscensosBajas altasAscensosBajas = new AltasAscensosBajas()
            .fecha(DEFAULT_FECHA)
            .cargo(DEFAULT_CARGO)
            .observaciones(DEFAULT_OBSERVACIONES)
            .expediente(DEFAULT_EXPEDIENTE)
            .prestaservicioen(DEFAULT_PRESTASERVICIOEN);
        return altasAscensosBajas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AltasAscensosBajas createUpdatedEntity(EntityManager em) {
        AltasAscensosBajas altasAscensosBajas = new AltasAscensosBajas()
            .fecha(UPDATED_FECHA)
            .cargo(UPDATED_CARGO)
            .observaciones(UPDATED_OBSERVACIONES)
            .expediente(UPDATED_EXPEDIENTE)
            .prestaservicioen(UPDATED_PRESTASERVICIOEN);
        return altasAscensosBajas;
    }

    @BeforeEach
    public void initTest() {
        altasAscensosBajas = createEntity(em);
    }

    @Test
    @Transactional
    public void createAltasAscensosBajas() throws Exception {
        int databaseSizeBeforeCreate = altasAscensosBajasRepository.findAll().size();

        // Create the AltasAscensosBajas
        restAltasAscensosBajasMockMvc.perform(post("/api/altas-ascensos-bajas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(altasAscensosBajas)))
            .andExpect(status().isCreated());

        // Validate the AltasAscensosBajas in the database
        List<AltasAscensosBajas> altasAscensosBajasList = altasAscensosBajasRepository.findAll();
        assertThat(altasAscensosBajasList).hasSize(databaseSizeBeforeCreate + 1);
        AltasAscensosBajas testAltasAscensosBajas = altasAscensosBajasList.get(altasAscensosBajasList.size() - 1);
        assertThat(testAltasAscensosBajas.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testAltasAscensosBajas.getCargo()).isEqualTo(DEFAULT_CARGO);
        assertThat(testAltasAscensosBajas.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testAltasAscensosBajas.getExpediente()).isEqualTo(DEFAULT_EXPEDIENTE);
        assertThat(testAltasAscensosBajas.getPrestaservicioen()).isEqualTo(DEFAULT_PRESTASERVICIOEN);
    }

    @Test
    @Transactional
    public void createAltasAscensosBajasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = altasAscensosBajasRepository.findAll().size();

        // Create the AltasAscensosBajas with an existing ID
        altasAscensosBajas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAltasAscensosBajasMockMvc.perform(post("/api/altas-ascensos-bajas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(altasAscensosBajas)))
            .andExpect(status().isBadRequest());

        // Validate the AltasAscensosBajas in the database
        List<AltasAscensosBajas> altasAscensosBajasList = altasAscensosBajasRepository.findAll();
        assertThat(altasAscensosBajasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAltasAscensosBajas() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList
        restAltasAscensosBajasMockMvc.perform(get("/api/altas-ascensos-bajas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(altasAscensosBajas.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].cargo").value(hasItem(DEFAULT_CARGO)))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].expediente").value(hasItem(DEFAULT_EXPEDIENTE)))
            .andExpect(jsonPath("$.[*].prestaservicioen").value(hasItem(DEFAULT_PRESTASERVICIOEN)));
    }
    
    @Test
    @Transactional
    public void getAltasAscensosBajas() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get the altasAscensosBajas
        restAltasAscensosBajasMockMvc.perform(get("/api/altas-ascensos-bajas/{id}", altasAscensosBajas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(altasAscensosBajas.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.cargo").value(DEFAULT_CARGO))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES))
            .andExpect(jsonPath("$.expediente").value(DEFAULT_EXPEDIENTE))
            .andExpect(jsonPath("$.prestaservicioen").value(DEFAULT_PRESTASERVICIOEN));
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha equals to DEFAULT_FECHA
        defaultAltasAscensosBajasShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the altasAscensosBajasList where fecha equals to UPDATED_FECHA
        defaultAltasAscensosBajasShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha not equals to DEFAULT_FECHA
        defaultAltasAscensosBajasShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the altasAscensosBajasList where fecha not equals to UPDATED_FECHA
        defaultAltasAscensosBajasShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultAltasAscensosBajasShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the altasAscensosBajasList where fecha equals to UPDATED_FECHA
        defaultAltasAscensosBajasShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha is not null
        defaultAltasAscensosBajasShouldBeFound("fecha.specified=true");

        // Get all the altasAscensosBajasList where fecha is null
        defaultAltasAscensosBajasShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha is greater than or equal to DEFAULT_FECHA
        defaultAltasAscensosBajasShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the altasAscensosBajasList where fecha is greater than or equal to UPDATED_FECHA
        defaultAltasAscensosBajasShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha is less than or equal to DEFAULT_FECHA
        defaultAltasAscensosBajasShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the altasAscensosBajasList where fecha is less than or equal to SMALLER_FECHA
        defaultAltasAscensosBajasShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha is less than DEFAULT_FECHA
        defaultAltasAscensosBajasShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the altasAscensosBajasList where fecha is less than UPDATED_FECHA
        defaultAltasAscensosBajasShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where fecha is greater than DEFAULT_FECHA
        defaultAltasAscensosBajasShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the altasAscensosBajasList where fecha is greater than SMALLER_FECHA
        defaultAltasAscensosBajasShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }


    @Test
    @Transactional
    public void getAllAltasAscensosBajasByCargoIsEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where cargo equals to DEFAULT_CARGO
        defaultAltasAscensosBajasShouldBeFound("cargo.equals=" + DEFAULT_CARGO);

        // Get all the altasAscensosBajasList where cargo equals to UPDATED_CARGO
        defaultAltasAscensosBajasShouldNotBeFound("cargo.equals=" + UPDATED_CARGO);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByCargoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where cargo not equals to DEFAULT_CARGO
        defaultAltasAscensosBajasShouldNotBeFound("cargo.notEquals=" + DEFAULT_CARGO);

        // Get all the altasAscensosBajasList where cargo not equals to UPDATED_CARGO
        defaultAltasAscensosBajasShouldBeFound("cargo.notEquals=" + UPDATED_CARGO);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByCargoIsInShouldWork() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where cargo in DEFAULT_CARGO or UPDATED_CARGO
        defaultAltasAscensosBajasShouldBeFound("cargo.in=" + DEFAULT_CARGO + "," + UPDATED_CARGO);

        // Get all the altasAscensosBajasList where cargo equals to UPDATED_CARGO
        defaultAltasAscensosBajasShouldNotBeFound("cargo.in=" + UPDATED_CARGO);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByCargoIsNullOrNotNull() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where cargo is not null
        defaultAltasAscensosBajasShouldBeFound("cargo.specified=true");

        // Get all the altasAscensosBajasList where cargo is null
        defaultAltasAscensosBajasShouldNotBeFound("cargo.specified=false");
    }
                @Test
    @Transactional
    public void getAllAltasAscensosBajasByCargoContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where cargo contains DEFAULT_CARGO
        defaultAltasAscensosBajasShouldBeFound("cargo.contains=" + DEFAULT_CARGO);

        // Get all the altasAscensosBajasList where cargo contains UPDATED_CARGO
        defaultAltasAscensosBajasShouldNotBeFound("cargo.contains=" + UPDATED_CARGO);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByCargoNotContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where cargo does not contain DEFAULT_CARGO
        defaultAltasAscensosBajasShouldNotBeFound("cargo.doesNotContain=" + DEFAULT_CARGO);

        // Get all the altasAscensosBajasList where cargo does not contain UPDATED_CARGO
        defaultAltasAscensosBajasShouldBeFound("cargo.doesNotContain=" + UPDATED_CARGO);
    }


    @Test
    @Transactional
    public void getAllAltasAscensosBajasByObservacionesIsEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where observaciones equals to DEFAULT_OBSERVACIONES
        defaultAltasAscensosBajasShouldBeFound("observaciones.equals=" + DEFAULT_OBSERVACIONES);

        // Get all the altasAscensosBajasList where observaciones equals to UPDATED_OBSERVACIONES
        defaultAltasAscensosBajasShouldNotBeFound("observaciones.equals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByObservacionesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where observaciones not equals to DEFAULT_OBSERVACIONES
        defaultAltasAscensosBajasShouldNotBeFound("observaciones.notEquals=" + DEFAULT_OBSERVACIONES);

        // Get all the altasAscensosBajasList where observaciones not equals to UPDATED_OBSERVACIONES
        defaultAltasAscensosBajasShouldBeFound("observaciones.notEquals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByObservacionesIsInShouldWork() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where observaciones in DEFAULT_OBSERVACIONES or UPDATED_OBSERVACIONES
        defaultAltasAscensosBajasShouldBeFound("observaciones.in=" + DEFAULT_OBSERVACIONES + "," + UPDATED_OBSERVACIONES);

        // Get all the altasAscensosBajasList where observaciones equals to UPDATED_OBSERVACIONES
        defaultAltasAscensosBajasShouldNotBeFound("observaciones.in=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByObservacionesIsNullOrNotNull() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where observaciones is not null
        defaultAltasAscensosBajasShouldBeFound("observaciones.specified=true");

        // Get all the altasAscensosBajasList where observaciones is null
        defaultAltasAscensosBajasShouldNotBeFound("observaciones.specified=false");
    }
                @Test
    @Transactional
    public void getAllAltasAscensosBajasByObservacionesContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where observaciones contains DEFAULT_OBSERVACIONES
        defaultAltasAscensosBajasShouldBeFound("observaciones.contains=" + DEFAULT_OBSERVACIONES);

        // Get all the altasAscensosBajasList where observaciones contains UPDATED_OBSERVACIONES
        defaultAltasAscensosBajasShouldNotBeFound("observaciones.contains=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByObservacionesNotContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where observaciones does not contain DEFAULT_OBSERVACIONES
        defaultAltasAscensosBajasShouldNotBeFound("observaciones.doesNotContain=" + DEFAULT_OBSERVACIONES);

        // Get all the altasAscensosBajasList where observaciones does not contain UPDATED_OBSERVACIONES
        defaultAltasAscensosBajasShouldBeFound("observaciones.doesNotContain=" + UPDATED_OBSERVACIONES);
    }


    @Test
    @Transactional
    public void getAllAltasAscensosBajasByExpedienteIsEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where expediente equals to DEFAULT_EXPEDIENTE
        defaultAltasAscensosBajasShouldBeFound("expediente.equals=" + DEFAULT_EXPEDIENTE);

        // Get all the altasAscensosBajasList where expediente equals to UPDATED_EXPEDIENTE
        defaultAltasAscensosBajasShouldNotBeFound("expediente.equals=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByExpedienteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where expediente not equals to DEFAULT_EXPEDIENTE
        defaultAltasAscensosBajasShouldNotBeFound("expediente.notEquals=" + DEFAULT_EXPEDIENTE);

        // Get all the altasAscensosBajasList where expediente not equals to UPDATED_EXPEDIENTE
        defaultAltasAscensosBajasShouldBeFound("expediente.notEquals=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByExpedienteIsInShouldWork() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where expediente in DEFAULT_EXPEDIENTE or UPDATED_EXPEDIENTE
        defaultAltasAscensosBajasShouldBeFound("expediente.in=" + DEFAULT_EXPEDIENTE + "," + UPDATED_EXPEDIENTE);

        // Get all the altasAscensosBajasList where expediente equals to UPDATED_EXPEDIENTE
        defaultAltasAscensosBajasShouldNotBeFound("expediente.in=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByExpedienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where expediente is not null
        defaultAltasAscensosBajasShouldBeFound("expediente.specified=true");

        // Get all the altasAscensosBajasList where expediente is null
        defaultAltasAscensosBajasShouldNotBeFound("expediente.specified=false");
    }
                @Test
    @Transactional
    public void getAllAltasAscensosBajasByExpedienteContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where expediente contains DEFAULT_EXPEDIENTE
        defaultAltasAscensosBajasShouldBeFound("expediente.contains=" + DEFAULT_EXPEDIENTE);

        // Get all the altasAscensosBajasList where expediente contains UPDATED_EXPEDIENTE
        defaultAltasAscensosBajasShouldNotBeFound("expediente.contains=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByExpedienteNotContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where expediente does not contain DEFAULT_EXPEDIENTE
        defaultAltasAscensosBajasShouldNotBeFound("expediente.doesNotContain=" + DEFAULT_EXPEDIENTE);

        // Get all the altasAscensosBajasList where expediente does not contain UPDATED_EXPEDIENTE
        defaultAltasAscensosBajasShouldBeFound("expediente.doesNotContain=" + UPDATED_EXPEDIENTE);
    }


    @Test
    @Transactional
    public void getAllAltasAscensosBajasByPrestaservicioenIsEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where prestaservicioen equals to DEFAULT_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldBeFound("prestaservicioen.equals=" + DEFAULT_PRESTASERVICIOEN);

        // Get all the altasAscensosBajasList where prestaservicioen equals to UPDATED_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldNotBeFound("prestaservicioen.equals=" + UPDATED_PRESTASERVICIOEN);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByPrestaservicioenIsNotEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where prestaservicioen not equals to DEFAULT_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldNotBeFound("prestaservicioen.notEquals=" + DEFAULT_PRESTASERVICIOEN);

        // Get all the altasAscensosBajasList where prestaservicioen not equals to UPDATED_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldBeFound("prestaservicioen.notEquals=" + UPDATED_PRESTASERVICIOEN);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByPrestaservicioenIsInShouldWork() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where prestaservicioen in DEFAULT_PRESTASERVICIOEN or UPDATED_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldBeFound("prestaservicioen.in=" + DEFAULT_PRESTASERVICIOEN + "," + UPDATED_PRESTASERVICIOEN);

        // Get all the altasAscensosBajasList where prestaservicioen equals to UPDATED_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldNotBeFound("prestaservicioen.in=" + UPDATED_PRESTASERVICIOEN);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByPrestaservicioenIsNullOrNotNull() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where prestaservicioen is not null
        defaultAltasAscensosBajasShouldBeFound("prestaservicioen.specified=true");

        // Get all the altasAscensosBajasList where prestaservicioen is null
        defaultAltasAscensosBajasShouldNotBeFound("prestaservicioen.specified=false");
    }
                @Test
    @Transactional
    public void getAllAltasAscensosBajasByPrestaservicioenContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where prestaservicioen contains DEFAULT_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldBeFound("prestaservicioen.contains=" + DEFAULT_PRESTASERVICIOEN);

        // Get all the altasAscensosBajasList where prestaservicioen contains UPDATED_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldNotBeFound("prestaservicioen.contains=" + UPDATED_PRESTASERVICIOEN);
    }

    @Test
    @Transactional
    public void getAllAltasAscensosBajasByPrestaservicioenNotContainsSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);

        // Get all the altasAscensosBajasList where prestaservicioen does not contain DEFAULT_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldNotBeFound("prestaservicioen.doesNotContain=" + DEFAULT_PRESTASERVICIOEN);

        // Get all the altasAscensosBajasList where prestaservicioen does not contain UPDATED_PRESTASERVICIOEN
        defaultAltasAscensosBajasShouldBeFound("prestaservicioen.doesNotContain=" + UPDATED_PRESTASERVICIOEN);
    }


    @Test
    @Transactional
    public void getAllAltasAscensosBajasByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        altasAscensosBajas.setPersona(persona);
        altasAscensosBajasRepository.saveAndFlush(altasAscensosBajas);
        Long personaId = persona.getId();

        // Get all the altasAscensosBajasList where persona equals to personaId
        defaultAltasAscensosBajasShouldBeFound("personaId.equals=" + personaId);

        // Get all the altasAscensosBajasList where persona equals to personaId + 1
        defaultAltasAscensosBajasShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAltasAscensosBajasShouldBeFound(String filter) throws Exception {
        restAltasAscensosBajasMockMvc.perform(get("/api/altas-ascensos-bajas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(altasAscensosBajas.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].cargo").value(hasItem(DEFAULT_CARGO)))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].expediente").value(hasItem(DEFAULT_EXPEDIENTE)))
            .andExpect(jsonPath("$.[*].prestaservicioen").value(hasItem(DEFAULT_PRESTASERVICIOEN)));

        // Check, that the count call also returns 1
        restAltasAscensosBajasMockMvc.perform(get("/api/altas-ascensos-bajas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAltasAscensosBajasShouldNotBeFound(String filter) throws Exception {
        restAltasAscensosBajasMockMvc.perform(get("/api/altas-ascensos-bajas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAltasAscensosBajasMockMvc.perform(get("/api/altas-ascensos-bajas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAltasAscensosBajas() throws Exception {
        // Get the altasAscensosBajas
        restAltasAscensosBajasMockMvc.perform(get("/api/altas-ascensos-bajas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAltasAscensosBajas() throws Exception {
        // Initialize the database
        altasAscensosBajasService.save(altasAscensosBajas);

        int databaseSizeBeforeUpdate = altasAscensosBajasRepository.findAll().size();

        // Update the altasAscensosBajas
        AltasAscensosBajas updatedAltasAscensosBajas = altasAscensosBajasRepository.findById(altasAscensosBajas.getId()).get();
        // Disconnect from session so that the updates on updatedAltasAscensosBajas are not directly saved in db
        em.detach(updatedAltasAscensosBajas);
        updatedAltasAscensosBajas
            .fecha(UPDATED_FECHA)
            .cargo(UPDATED_CARGO)
            .observaciones(UPDATED_OBSERVACIONES)
            .expediente(UPDATED_EXPEDIENTE)
            .prestaservicioen(UPDATED_PRESTASERVICIOEN);

        restAltasAscensosBajasMockMvc.perform(put("/api/altas-ascensos-bajas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAltasAscensosBajas)))
            .andExpect(status().isOk());

        // Validate the AltasAscensosBajas in the database
        List<AltasAscensosBajas> altasAscensosBajasList = altasAscensosBajasRepository.findAll();
        assertThat(altasAscensosBajasList).hasSize(databaseSizeBeforeUpdate);
        AltasAscensosBajas testAltasAscensosBajas = altasAscensosBajasList.get(altasAscensosBajasList.size() - 1);
        assertThat(testAltasAscensosBajas.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testAltasAscensosBajas.getCargo()).isEqualTo(UPDATED_CARGO);
        assertThat(testAltasAscensosBajas.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testAltasAscensosBajas.getExpediente()).isEqualTo(UPDATED_EXPEDIENTE);
        assertThat(testAltasAscensosBajas.getPrestaservicioen()).isEqualTo(UPDATED_PRESTASERVICIOEN);
    }

    @Test
    @Transactional
    public void updateNonExistingAltasAscensosBajas() throws Exception {
        int databaseSizeBeforeUpdate = altasAscensosBajasRepository.findAll().size();

        // Create the AltasAscensosBajas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAltasAscensosBajasMockMvc.perform(put("/api/altas-ascensos-bajas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(altasAscensosBajas)))
            .andExpect(status().isBadRequest());

        // Validate the AltasAscensosBajas in the database
        List<AltasAscensosBajas> altasAscensosBajasList = altasAscensosBajasRepository.findAll();
        assertThat(altasAscensosBajasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAltasAscensosBajas() throws Exception {
        // Initialize the database
        altasAscensosBajasService.save(altasAscensosBajas);

        int databaseSizeBeforeDelete = altasAscensosBajasRepository.findAll().size();

        // Delete the altasAscensosBajas
        restAltasAscensosBajasMockMvc.perform(delete("/api/altas-ascensos-bajas/{id}", altasAscensosBajas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AltasAscensosBajas> altasAscensosBajasList = altasAscensosBajasRepository.findAll();
        assertThat(altasAscensosBajasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AltasAscensosBajas.class);
        AltasAscensosBajas altasAscensosBajas1 = new AltasAscensosBajas();
        altasAscensosBajas1.setId(1L);
        AltasAscensosBajas altasAscensosBajas2 = new AltasAscensosBajas();
        altasAscensosBajas2.setId(altasAscensosBajas1.getId());
        assertThat(altasAscensosBajas1).isEqualTo(altasAscensosBajas2);
        altasAscensosBajas2.setId(2L);
        assertThat(altasAscensosBajas1).isNotEqualTo(altasAscensosBajas2);
        altasAscensosBajas1.setId(null);
        assertThat(altasAscensosBajas1).isNotEqualTo(altasAscensosBajas2);
    }
}
