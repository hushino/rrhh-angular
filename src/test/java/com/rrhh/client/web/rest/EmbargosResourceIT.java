package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.Embargos;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.repository.EmbargosRepository;
import com.rrhh.client.service.EmbargosService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.EmbargosCriteria;
import com.rrhh.client.service.EmbargosQueryService;

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
 * Integration tests for the {@link EmbargosResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class EmbargosResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_JUZGADO = "AAAAAAAAAA";
    private static final String UPDATED_JUZGADO = "BBBBBBBBBB";

    private static final String DEFAULT_ACREEDOR = "AAAAAAAAAA";
    private static final String UPDATED_ACREEDOR = "BBBBBBBBBB";

    private static final String DEFAULT_CANTIDAD = "AAAAAAAAAA";
    private static final String UPDATED_CANTIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_EXPEDIENTE = "AAAAAAAAAA";
    private static final String UPDATED_EXPEDIENTE = "BBBBBBBBBB";

    private static final String DEFAULT_FIANZA_O_DEUDA_PROPIA = "AAAAAAAAAA";
    private static final String UPDATED_FIANZA_O_DEUDA_PROPIA = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGEN_DE_LA_DEUDA = "AAAAAAAAAA";
    private static final String UPDATED_ORIGEN_DE_LA_DEUDA = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    private static final String DEFAULT_LEVANTADA = "AAAAAAAAAA";
    private static final String UPDATED_LEVANTADA = "BBBBBBBBBB";

    @Autowired
    private EmbargosRepository embargosRepository;

    @Autowired
    private EmbargosService embargosService;

    @Autowired
    private EmbargosQueryService embargosQueryService;

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

    private MockMvc restEmbargosMockMvc;

    private Embargos embargos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmbargosResource embargosResource = new EmbargosResource(embargosService, embargosQueryService);
        this.restEmbargosMockMvc = MockMvcBuilders.standaloneSetup(embargosResource)
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
    public static Embargos createEntity(EntityManager em) {
        Embargos embargos = new Embargos()
            .fecha(DEFAULT_FECHA)
            .juzgado(DEFAULT_JUZGADO)
            .acreedor(DEFAULT_ACREEDOR)
            .cantidad(DEFAULT_CANTIDAD)
            .expediente(DEFAULT_EXPEDIENTE)
            .fianzaODeudaPropia(DEFAULT_FIANZA_O_DEUDA_PROPIA)
            .origenDeLaDeuda(DEFAULT_ORIGEN_DE_LA_DEUDA)
            .observaciones(DEFAULT_OBSERVACIONES)
            .levantada(DEFAULT_LEVANTADA);
        return embargos;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Embargos createUpdatedEntity(EntityManager em) {
        Embargos embargos = new Embargos()
            .fecha(UPDATED_FECHA)
            .juzgado(UPDATED_JUZGADO)
            .acreedor(UPDATED_ACREEDOR)
            .cantidad(UPDATED_CANTIDAD)
            .expediente(UPDATED_EXPEDIENTE)
            .fianzaODeudaPropia(UPDATED_FIANZA_O_DEUDA_PROPIA)
            .origenDeLaDeuda(UPDATED_ORIGEN_DE_LA_DEUDA)
            .observaciones(UPDATED_OBSERVACIONES)
            .levantada(UPDATED_LEVANTADA);
        return embargos;
    }

    @BeforeEach
    public void initTest() {
        embargos = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmbargos() throws Exception {
        int databaseSizeBeforeCreate = embargosRepository.findAll().size();

        // Create the Embargos
        restEmbargosMockMvc.perform(post("/api/embargos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embargos)))
            .andExpect(status().isCreated());

        // Validate the Embargos in the database
        List<Embargos> embargosList = embargosRepository.findAll();
        assertThat(embargosList).hasSize(databaseSizeBeforeCreate + 1);
        Embargos testEmbargos = embargosList.get(embargosList.size() - 1);
        assertThat(testEmbargos.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testEmbargos.getJuzgado()).isEqualTo(DEFAULT_JUZGADO);
        assertThat(testEmbargos.getAcreedor()).isEqualTo(DEFAULT_ACREEDOR);
        assertThat(testEmbargos.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
        assertThat(testEmbargos.getExpediente()).isEqualTo(DEFAULT_EXPEDIENTE);
        assertThat(testEmbargos.getFianzaODeudaPropia()).isEqualTo(DEFAULT_FIANZA_O_DEUDA_PROPIA);
        assertThat(testEmbargos.getOrigenDeLaDeuda()).isEqualTo(DEFAULT_ORIGEN_DE_LA_DEUDA);
        assertThat(testEmbargos.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
        assertThat(testEmbargos.getLevantada()).isEqualTo(DEFAULT_LEVANTADA);
    }

    @Test
    @Transactional
    public void createEmbargosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = embargosRepository.findAll().size();

        // Create the Embargos with an existing ID
        embargos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmbargosMockMvc.perform(post("/api/embargos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embargos)))
            .andExpect(status().isBadRequest());

        // Validate the Embargos in the database
        List<Embargos> embargosList = embargosRepository.findAll();
        assertThat(embargosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmbargos() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList
        restEmbargosMockMvc.perform(get("/api/embargos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(embargos.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].juzgado").value(hasItem(DEFAULT_JUZGADO)))
            .andExpect(jsonPath("$.[*].acreedor").value(hasItem(DEFAULT_ACREEDOR)))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].expediente").value(hasItem(DEFAULT_EXPEDIENTE)))
            .andExpect(jsonPath("$.[*].fianzaODeudaPropia").value(hasItem(DEFAULT_FIANZA_O_DEUDA_PROPIA)))
            .andExpect(jsonPath("$.[*].origenDeLaDeuda").value(hasItem(DEFAULT_ORIGEN_DE_LA_DEUDA)))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].levantada").value(hasItem(DEFAULT_LEVANTADA)));
    }
    
    @Test
    @Transactional
    public void getEmbargos() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get the embargos
        restEmbargosMockMvc.perform(get("/api/embargos/{id}", embargos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(embargos.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.juzgado").value(DEFAULT_JUZGADO))
            .andExpect(jsonPath("$.acreedor").value(DEFAULT_ACREEDOR))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD))
            .andExpect(jsonPath("$.expediente").value(DEFAULT_EXPEDIENTE))
            .andExpect(jsonPath("$.fianzaODeudaPropia").value(DEFAULT_FIANZA_O_DEUDA_PROPIA))
            .andExpect(jsonPath("$.origenDeLaDeuda").value(DEFAULT_ORIGEN_DE_LA_DEUDA))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES))
            .andExpect(jsonPath("$.levantada").value(DEFAULT_LEVANTADA));
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha equals to DEFAULT_FECHA
        defaultEmbargosShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the embargosList where fecha equals to UPDATED_FECHA
        defaultEmbargosShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha not equals to DEFAULT_FECHA
        defaultEmbargosShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the embargosList where fecha not equals to UPDATED_FECHA
        defaultEmbargosShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultEmbargosShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the embargosList where fecha equals to UPDATED_FECHA
        defaultEmbargosShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha is not null
        defaultEmbargosShouldBeFound("fecha.specified=true");

        // Get all the embargosList where fecha is null
        defaultEmbargosShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha is greater than or equal to DEFAULT_FECHA
        defaultEmbargosShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the embargosList where fecha is greater than or equal to UPDATED_FECHA
        defaultEmbargosShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha is less than or equal to DEFAULT_FECHA
        defaultEmbargosShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the embargosList where fecha is less than or equal to SMALLER_FECHA
        defaultEmbargosShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha is less than DEFAULT_FECHA
        defaultEmbargosShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the embargosList where fecha is less than UPDATED_FECHA
        defaultEmbargosShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fecha is greater than DEFAULT_FECHA
        defaultEmbargosShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the embargosList where fecha is greater than SMALLER_FECHA
        defaultEmbargosShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }


    @Test
    @Transactional
    public void getAllEmbargosByJuzgadoIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where juzgado equals to DEFAULT_JUZGADO
        defaultEmbargosShouldBeFound("juzgado.equals=" + DEFAULT_JUZGADO);

        // Get all the embargosList where juzgado equals to UPDATED_JUZGADO
        defaultEmbargosShouldNotBeFound("juzgado.equals=" + UPDATED_JUZGADO);
    }

    @Test
    @Transactional
    public void getAllEmbargosByJuzgadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where juzgado not equals to DEFAULT_JUZGADO
        defaultEmbargosShouldNotBeFound("juzgado.notEquals=" + DEFAULT_JUZGADO);

        // Get all the embargosList where juzgado not equals to UPDATED_JUZGADO
        defaultEmbargosShouldBeFound("juzgado.notEquals=" + UPDATED_JUZGADO);
    }

    @Test
    @Transactional
    public void getAllEmbargosByJuzgadoIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where juzgado in DEFAULT_JUZGADO or UPDATED_JUZGADO
        defaultEmbargosShouldBeFound("juzgado.in=" + DEFAULT_JUZGADO + "," + UPDATED_JUZGADO);

        // Get all the embargosList where juzgado equals to UPDATED_JUZGADO
        defaultEmbargosShouldNotBeFound("juzgado.in=" + UPDATED_JUZGADO);
    }

    @Test
    @Transactional
    public void getAllEmbargosByJuzgadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where juzgado is not null
        defaultEmbargosShouldBeFound("juzgado.specified=true");

        // Get all the embargosList where juzgado is null
        defaultEmbargosShouldNotBeFound("juzgado.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByJuzgadoContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where juzgado contains DEFAULT_JUZGADO
        defaultEmbargosShouldBeFound("juzgado.contains=" + DEFAULT_JUZGADO);

        // Get all the embargosList where juzgado contains UPDATED_JUZGADO
        defaultEmbargosShouldNotBeFound("juzgado.contains=" + UPDATED_JUZGADO);
    }

    @Test
    @Transactional
    public void getAllEmbargosByJuzgadoNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where juzgado does not contain DEFAULT_JUZGADO
        defaultEmbargosShouldNotBeFound("juzgado.doesNotContain=" + DEFAULT_JUZGADO);

        // Get all the embargosList where juzgado does not contain UPDATED_JUZGADO
        defaultEmbargosShouldBeFound("juzgado.doesNotContain=" + UPDATED_JUZGADO);
    }


    @Test
    @Transactional
    public void getAllEmbargosByAcreedorIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where acreedor equals to DEFAULT_ACREEDOR
        defaultEmbargosShouldBeFound("acreedor.equals=" + DEFAULT_ACREEDOR);

        // Get all the embargosList where acreedor equals to UPDATED_ACREEDOR
        defaultEmbargosShouldNotBeFound("acreedor.equals=" + UPDATED_ACREEDOR);
    }

    @Test
    @Transactional
    public void getAllEmbargosByAcreedorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where acreedor not equals to DEFAULT_ACREEDOR
        defaultEmbargosShouldNotBeFound("acreedor.notEquals=" + DEFAULT_ACREEDOR);

        // Get all the embargosList where acreedor not equals to UPDATED_ACREEDOR
        defaultEmbargosShouldBeFound("acreedor.notEquals=" + UPDATED_ACREEDOR);
    }

    @Test
    @Transactional
    public void getAllEmbargosByAcreedorIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where acreedor in DEFAULT_ACREEDOR or UPDATED_ACREEDOR
        defaultEmbargosShouldBeFound("acreedor.in=" + DEFAULT_ACREEDOR + "," + UPDATED_ACREEDOR);

        // Get all the embargosList where acreedor equals to UPDATED_ACREEDOR
        defaultEmbargosShouldNotBeFound("acreedor.in=" + UPDATED_ACREEDOR);
    }

    @Test
    @Transactional
    public void getAllEmbargosByAcreedorIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where acreedor is not null
        defaultEmbargosShouldBeFound("acreedor.specified=true");

        // Get all the embargosList where acreedor is null
        defaultEmbargosShouldNotBeFound("acreedor.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByAcreedorContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where acreedor contains DEFAULT_ACREEDOR
        defaultEmbargosShouldBeFound("acreedor.contains=" + DEFAULT_ACREEDOR);

        // Get all the embargosList where acreedor contains UPDATED_ACREEDOR
        defaultEmbargosShouldNotBeFound("acreedor.contains=" + UPDATED_ACREEDOR);
    }

    @Test
    @Transactional
    public void getAllEmbargosByAcreedorNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where acreedor does not contain DEFAULT_ACREEDOR
        defaultEmbargosShouldNotBeFound("acreedor.doesNotContain=" + DEFAULT_ACREEDOR);

        // Get all the embargosList where acreedor does not contain UPDATED_ACREEDOR
        defaultEmbargosShouldBeFound("acreedor.doesNotContain=" + UPDATED_ACREEDOR);
    }


    @Test
    @Transactional
    public void getAllEmbargosByCantidadIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where cantidad equals to DEFAULT_CANTIDAD
        defaultEmbargosShouldBeFound("cantidad.equals=" + DEFAULT_CANTIDAD);

        // Get all the embargosList where cantidad equals to UPDATED_CANTIDAD
        defaultEmbargosShouldNotBeFound("cantidad.equals=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    public void getAllEmbargosByCantidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where cantidad not equals to DEFAULT_CANTIDAD
        defaultEmbargosShouldNotBeFound("cantidad.notEquals=" + DEFAULT_CANTIDAD);

        // Get all the embargosList where cantidad not equals to UPDATED_CANTIDAD
        defaultEmbargosShouldBeFound("cantidad.notEquals=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    public void getAllEmbargosByCantidadIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where cantidad in DEFAULT_CANTIDAD or UPDATED_CANTIDAD
        defaultEmbargosShouldBeFound("cantidad.in=" + DEFAULT_CANTIDAD + "," + UPDATED_CANTIDAD);

        // Get all the embargosList where cantidad equals to UPDATED_CANTIDAD
        defaultEmbargosShouldNotBeFound("cantidad.in=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    public void getAllEmbargosByCantidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where cantidad is not null
        defaultEmbargosShouldBeFound("cantidad.specified=true");

        // Get all the embargosList where cantidad is null
        defaultEmbargosShouldNotBeFound("cantidad.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByCantidadContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where cantidad contains DEFAULT_CANTIDAD
        defaultEmbargosShouldBeFound("cantidad.contains=" + DEFAULT_CANTIDAD);

        // Get all the embargosList where cantidad contains UPDATED_CANTIDAD
        defaultEmbargosShouldNotBeFound("cantidad.contains=" + UPDATED_CANTIDAD);
    }

    @Test
    @Transactional
    public void getAllEmbargosByCantidadNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where cantidad does not contain DEFAULT_CANTIDAD
        defaultEmbargosShouldNotBeFound("cantidad.doesNotContain=" + DEFAULT_CANTIDAD);

        // Get all the embargosList where cantidad does not contain UPDATED_CANTIDAD
        defaultEmbargosShouldBeFound("cantidad.doesNotContain=" + UPDATED_CANTIDAD);
    }


    @Test
    @Transactional
    public void getAllEmbargosByExpedienteIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where expediente equals to DEFAULT_EXPEDIENTE
        defaultEmbargosShouldBeFound("expediente.equals=" + DEFAULT_EXPEDIENTE);

        // Get all the embargosList where expediente equals to UPDATED_EXPEDIENTE
        defaultEmbargosShouldNotBeFound("expediente.equals=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllEmbargosByExpedienteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where expediente not equals to DEFAULT_EXPEDIENTE
        defaultEmbargosShouldNotBeFound("expediente.notEquals=" + DEFAULT_EXPEDIENTE);

        // Get all the embargosList where expediente not equals to UPDATED_EXPEDIENTE
        defaultEmbargosShouldBeFound("expediente.notEquals=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllEmbargosByExpedienteIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where expediente in DEFAULT_EXPEDIENTE or UPDATED_EXPEDIENTE
        defaultEmbargosShouldBeFound("expediente.in=" + DEFAULT_EXPEDIENTE + "," + UPDATED_EXPEDIENTE);

        // Get all the embargosList where expediente equals to UPDATED_EXPEDIENTE
        defaultEmbargosShouldNotBeFound("expediente.in=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllEmbargosByExpedienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where expediente is not null
        defaultEmbargosShouldBeFound("expediente.specified=true");

        // Get all the embargosList where expediente is null
        defaultEmbargosShouldNotBeFound("expediente.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByExpedienteContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where expediente contains DEFAULT_EXPEDIENTE
        defaultEmbargosShouldBeFound("expediente.contains=" + DEFAULT_EXPEDIENTE);

        // Get all the embargosList where expediente contains UPDATED_EXPEDIENTE
        defaultEmbargosShouldNotBeFound("expediente.contains=" + UPDATED_EXPEDIENTE);
    }

    @Test
    @Transactional
    public void getAllEmbargosByExpedienteNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where expediente does not contain DEFAULT_EXPEDIENTE
        defaultEmbargosShouldNotBeFound("expediente.doesNotContain=" + DEFAULT_EXPEDIENTE);

        // Get all the embargosList where expediente does not contain UPDATED_EXPEDIENTE
        defaultEmbargosShouldBeFound("expediente.doesNotContain=" + UPDATED_EXPEDIENTE);
    }


    @Test
    @Transactional
    public void getAllEmbargosByFianzaODeudaPropiaIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fianzaODeudaPropia equals to DEFAULT_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldBeFound("fianzaODeudaPropia.equals=" + DEFAULT_FIANZA_O_DEUDA_PROPIA);

        // Get all the embargosList where fianzaODeudaPropia equals to UPDATED_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldNotBeFound("fianzaODeudaPropia.equals=" + UPDATED_FIANZA_O_DEUDA_PROPIA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFianzaODeudaPropiaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fianzaODeudaPropia not equals to DEFAULT_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldNotBeFound("fianzaODeudaPropia.notEquals=" + DEFAULT_FIANZA_O_DEUDA_PROPIA);

        // Get all the embargosList where fianzaODeudaPropia not equals to UPDATED_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldBeFound("fianzaODeudaPropia.notEquals=" + UPDATED_FIANZA_O_DEUDA_PROPIA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFianzaODeudaPropiaIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fianzaODeudaPropia in DEFAULT_FIANZA_O_DEUDA_PROPIA or UPDATED_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldBeFound("fianzaODeudaPropia.in=" + DEFAULT_FIANZA_O_DEUDA_PROPIA + "," + UPDATED_FIANZA_O_DEUDA_PROPIA);

        // Get all the embargosList where fianzaODeudaPropia equals to UPDATED_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldNotBeFound("fianzaODeudaPropia.in=" + UPDATED_FIANZA_O_DEUDA_PROPIA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFianzaODeudaPropiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fianzaODeudaPropia is not null
        defaultEmbargosShouldBeFound("fianzaODeudaPropia.specified=true");

        // Get all the embargosList where fianzaODeudaPropia is null
        defaultEmbargosShouldNotBeFound("fianzaODeudaPropia.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByFianzaODeudaPropiaContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fianzaODeudaPropia contains DEFAULT_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldBeFound("fianzaODeudaPropia.contains=" + DEFAULT_FIANZA_O_DEUDA_PROPIA);

        // Get all the embargosList where fianzaODeudaPropia contains UPDATED_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldNotBeFound("fianzaODeudaPropia.contains=" + UPDATED_FIANZA_O_DEUDA_PROPIA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByFianzaODeudaPropiaNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where fianzaODeudaPropia does not contain DEFAULT_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldNotBeFound("fianzaODeudaPropia.doesNotContain=" + DEFAULT_FIANZA_O_DEUDA_PROPIA);

        // Get all the embargosList where fianzaODeudaPropia does not contain UPDATED_FIANZA_O_DEUDA_PROPIA
        defaultEmbargosShouldBeFound("fianzaODeudaPropia.doesNotContain=" + UPDATED_FIANZA_O_DEUDA_PROPIA);
    }


    @Test
    @Transactional
    public void getAllEmbargosByOrigenDeLaDeudaIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where origenDeLaDeuda equals to DEFAULT_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldBeFound("origenDeLaDeuda.equals=" + DEFAULT_ORIGEN_DE_LA_DEUDA);

        // Get all the embargosList where origenDeLaDeuda equals to UPDATED_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldNotBeFound("origenDeLaDeuda.equals=" + UPDATED_ORIGEN_DE_LA_DEUDA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByOrigenDeLaDeudaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where origenDeLaDeuda not equals to DEFAULT_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldNotBeFound("origenDeLaDeuda.notEquals=" + DEFAULT_ORIGEN_DE_LA_DEUDA);

        // Get all the embargosList where origenDeLaDeuda not equals to UPDATED_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldBeFound("origenDeLaDeuda.notEquals=" + UPDATED_ORIGEN_DE_LA_DEUDA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByOrigenDeLaDeudaIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where origenDeLaDeuda in DEFAULT_ORIGEN_DE_LA_DEUDA or UPDATED_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldBeFound("origenDeLaDeuda.in=" + DEFAULT_ORIGEN_DE_LA_DEUDA + "," + UPDATED_ORIGEN_DE_LA_DEUDA);

        // Get all the embargosList where origenDeLaDeuda equals to UPDATED_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldNotBeFound("origenDeLaDeuda.in=" + UPDATED_ORIGEN_DE_LA_DEUDA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByOrigenDeLaDeudaIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where origenDeLaDeuda is not null
        defaultEmbargosShouldBeFound("origenDeLaDeuda.specified=true");

        // Get all the embargosList where origenDeLaDeuda is null
        defaultEmbargosShouldNotBeFound("origenDeLaDeuda.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByOrigenDeLaDeudaContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where origenDeLaDeuda contains DEFAULT_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldBeFound("origenDeLaDeuda.contains=" + DEFAULT_ORIGEN_DE_LA_DEUDA);

        // Get all the embargosList where origenDeLaDeuda contains UPDATED_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldNotBeFound("origenDeLaDeuda.contains=" + UPDATED_ORIGEN_DE_LA_DEUDA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByOrigenDeLaDeudaNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where origenDeLaDeuda does not contain DEFAULT_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldNotBeFound("origenDeLaDeuda.doesNotContain=" + DEFAULT_ORIGEN_DE_LA_DEUDA);

        // Get all the embargosList where origenDeLaDeuda does not contain UPDATED_ORIGEN_DE_LA_DEUDA
        defaultEmbargosShouldBeFound("origenDeLaDeuda.doesNotContain=" + UPDATED_ORIGEN_DE_LA_DEUDA);
    }


    @Test
    @Transactional
    public void getAllEmbargosByObservacionesIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where observaciones equals to DEFAULT_OBSERVACIONES
        defaultEmbargosShouldBeFound("observaciones.equals=" + DEFAULT_OBSERVACIONES);

        // Get all the embargosList where observaciones equals to UPDATED_OBSERVACIONES
        defaultEmbargosShouldNotBeFound("observaciones.equals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllEmbargosByObservacionesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where observaciones not equals to DEFAULT_OBSERVACIONES
        defaultEmbargosShouldNotBeFound("observaciones.notEquals=" + DEFAULT_OBSERVACIONES);

        // Get all the embargosList where observaciones not equals to UPDATED_OBSERVACIONES
        defaultEmbargosShouldBeFound("observaciones.notEquals=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllEmbargosByObservacionesIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where observaciones in DEFAULT_OBSERVACIONES or UPDATED_OBSERVACIONES
        defaultEmbargosShouldBeFound("observaciones.in=" + DEFAULT_OBSERVACIONES + "," + UPDATED_OBSERVACIONES);

        // Get all the embargosList where observaciones equals to UPDATED_OBSERVACIONES
        defaultEmbargosShouldNotBeFound("observaciones.in=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllEmbargosByObservacionesIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where observaciones is not null
        defaultEmbargosShouldBeFound("observaciones.specified=true");

        // Get all the embargosList where observaciones is null
        defaultEmbargosShouldNotBeFound("observaciones.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByObservacionesContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where observaciones contains DEFAULT_OBSERVACIONES
        defaultEmbargosShouldBeFound("observaciones.contains=" + DEFAULT_OBSERVACIONES);

        // Get all the embargosList where observaciones contains UPDATED_OBSERVACIONES
        defaultEmbargosShouldNotBeFound("observaciones.contains=" + UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void getAllEmbargosByObservacionesNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where observaciones does not contain DEFAULT_OBSERVACIONES
        defaultEmbargosShouldNotBeFound("observaciones.doesNotContain=" + DEFAULT_OBSERVACIONES);

        // Get all the embargosList where observaciones does not contain UPDATED_OBSERVACIONES
        defaultEmbargosShouldBeFound("observaciones.doesNotContain=" + UPDATED_OBSERVACIONES);
    }


    @Test
    @Transactional
    public void getAllEmbargosByLevantadaIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where levantada equals to DEFAULT_LEVANTADA
        defaultEmbargosShouldBeFound("levantada.equals=" + DEFAULT_LEVANTADA);

        // Get all the embargosList where levantada equals to UPDATED_LEVANTADA
        defaultEmbargosShouldNotBeFound("levantada.equals=" + UPDATED_LEVANTADA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByLevantadaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where levantada not equals to DEFAULT_LEVANTADA
        defaultEmbargosShouldNotBeFound("levantada.notEquals=" + DEFAULT_LEVANTADA);

        // Get all the embargosList where levantada not equals to UPDATED_LEVANTADA
        defaultEmbargosShouldBeFound("levantada.notEquals=" + UPDATED_LEVANTADA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByLevantadaIsInShouldWork() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where levantada in DEFAULT_LEVANTADA or UPDATED_LEVANTADA
        defaultEmbargosShouldBeFound("levantada.in=" + DEFAULT_LEVANTADA + "," + UPDATED_LEVANTADA);

        // Get all the embargosList where levantada equals to UPDATED_LEVANTADA
        defaultEmbargosShouldNotBeFound("levantada.in=" + UPDATED_LEVANTADA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByLevantadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where levantada is not null
        defaultEmbargosShouldBeFound("levantada.specified=true");

        // Get all the embargosList where levantada is null
        defaultEmbargosShouldNotBeFound("levantada.specified=false");
    }
                @Test
    @Transactional
    public void getAllEmbargosByLevantadaContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where levantada contains DEFAULT_LEVANTADA
        defaultEmbargosShouldBeFound("levantada.contains=" + DEFAULT_LEVANTADA);

        // Get all the embargosList where levantada contains UPDATED_LEVANTADA
        defaultEmbargosShouldNotBeFound("levantada.contains=" + UPDATED_LEVANTADA);
    }

    @Test
    @Transactional
    public void getAllEmbargosByLevantadaNotContainsSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);

        // Get all the embargosList where levantada does not contain DEFAULT_LEVANTADA
        defaultEmbargosShouldNotBeFound("levantada.doesNotContain=" + DEFAULT_LEVANTADA);

        // Get all the embargosList where levantada does not contain UPDATED_LEVANTADA
        defaultEmbargosShouldBeFound("levantada.doesNotContain=" + UPDATED_LEVANTADA);
    }


    @Test
    @Transactional
    public void getAllEmbargosByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        embargosRepository.saveAndFlush(embargos);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        embargos.setPersona(persona);
        embargosRepository.saveAndFlush(embargos);
        Long personaId = persona.getId();

        // Get all the embargosList where persona equals to personaId
        defaultEmbargosShouldBeFound("personaId.equals=" + personaId);

        // Get all the embargosList where persona equals to personaId + 1
        defaultEmbargosShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEmbargosShouldBeFound(String filter) throws Exception {
        restEmbargosMockMvc.perform(get("/api/embargos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(embargos.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].juzgado").value(hasItem(DEFAULT_JUZGADO)))
            .andExpect(jsonPath("$.[*].acreedor").value(hasItem(DEFAULT_ACREEDOR)))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].expediente").value(hasItem(DEFAULT_EXPEDIENTE)))
            .andExpect(jsonPath("$.[*].fianzaODeudaPropia").value(hasItem(DEFAULT_FIANZA_O_DEUDA_PROPIA)))
            .andExpect(jsonPath("$.[*].origenDeLaDeuda").value(hasItem(DEFAULT_ORIGEN_DE_LA_DEUDA)))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES)))
            .andExpect(jsonPath("$.[*].levantada").value(hasItem(DEFAULT_LEVANTADA)));

        // Check, that the count call also returns 1
        restEmbargosMockMvc.perform(get("/api/embargos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEmbargosShouldNotBeFound(String filter) throws Exception {
        restEmbargosMockMvc.perform(get("/api/embargos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEmbargosMockMvc.perform(get("/api/embargos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingEmbargos() throws Exception {
        // Get the embargos
        restEmbargosMockMvc.perform(get("/api/embargos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmbargos() throws Exception {
        // Initialize the database
        embargosService.save(embargos);

        int databaseSizeBeforeUpdate = embargosRepository.findAll().size();

        // Update the embargos
        Embargos updatedEmbargos = embargosRepository.findById(embargos.getId()).get();
        // Disconnect from session so that the updates on updatedEmbargos are not directly saved in db
        em.detach(updatedEmbargos);
        updatedEmbargos
            .fecha(UPDATED_FECHA)
            .juzgado(UPDATED_JUZGADO)
            .acreedor(UPDATED_ACREEDOR)
            .cantidad(UPDATED_CANTIDAD)
            .expediente(UPDATED_EXPEDIENTE)
            .fianzaODeudaPropia(UPDATED_FIANZA_O_DEUDA_PROPIA)
            .origenDeLaDeuda(UPDATED_ORIGEN_DE_LA_DEUDA)
            .observaciones(UPDATED_OBSERVACIONES)
            .levantada(UPDATED_LEVANTADA);

        restEmbargosMockMvc.perform(put("/api/embargos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmbargos)))
            .andExpect(status().isOk());

        // Validate the Embargos in the database
        List<Embargos> embargosList = embargosRepository.findAll();
        assertThat(embargosList).hasSize(databaseSizeBeforeUpdate);
        Embargos testEmbargos = embargosList.get(embargosList.size() - 1);
        assertThat(testEmbargos.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testEmbargos.getJuzgado()).isEqualTo(UPDATED_JUZGADO);
        assertThat(testEmbargos.getAcreedor()).isEqualTo(UPDATED_ACREEDOR);
        assertThat(testEmbargos.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testEmbargos.getExpediente()).isEqualTo(UPDATED_EXPEDIENTE);
        assertThat(testEmbargos.getFianzaODeudaPropia()).isEqualTo(UPDATED_FIANZA_O_DEUDA_PROPIA);
        assertThat(testEmbargos.getOrigenDeLaDeuda()).isEqualTo(UPDATED_ORIGEN_DE_LA_DEUDA);
        assertThat(testEmbargos.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
        assertThat(testEmbargos.getLevantada()).isEqualTo(UPDATED_LEVANTADA);
    }

    @Test
    @Transactional
    public void updateNonExistingEmbargos() throws Exception {
        int databaseSizeBeforeUpdate = embargosRepository.findAll().size();

        // Create the Embargos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmbargosMockMvc.perform(put("/api/embargos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(embargos)))
            .andExpect(status().isBadRequest());

        // Validate the Embargos in the database
        List<Embargos> embargosList = embargosRepository.findAll();
        assertThat(embargosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmbargos() throws Exception {
        // Initialize the database
        embargosService.save(embargos);

        int databaseSizeBeforeDelete = embargosRepository.findAll().size();

        // Delete the embargos
        restEmbargosMockMvc.perform(delete("/api/embargos/{id}", embargos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Embargos> embargosList = embargosRepository.findAll();
        assertThat(embargosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Embargos.class);
        Embargos embargos1 = new Embargos();
        embargos1.setId(1L);
        Embargos embargos2 = new Embargos();
        embargos2.setId(embargos1.getId());
        assertThat(embargos1).isEqualTo(embargos2);
        embargos2.setId(2L);
        assertThat(embargos1).isNotEqualTo(embargos2);
        embargos1.setId(null);
        assertThat(embargos1).isNotEqualTo(embargos2);
    }
}
