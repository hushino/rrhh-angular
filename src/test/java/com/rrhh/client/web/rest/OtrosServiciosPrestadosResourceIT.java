package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.OtrosServiciosPrestados;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.repository.OtrosServiciosPrestadosRepository;
import com.rrhh.client.service.OtrosServiciosPrestadosService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.OtrosServiciosPrestadosCriteria;
import com.rrhh.client.service.OtrosServiciosPrestadosQueryService;

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
 * Integration tests for the {@link OtrosServiciosPrestadosResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class OtrosServiciosPrestadosResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_REFERENCIAS = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIAS = "BBBBBBBBBB";

    @Autowired
    private OtrosServiciosPrestadosRepository otrosServiciosPrestadosRepository;

    @Autowired
    private OtrosServiciosPrestadosService otrosServiciosPrestadosService;

    @Autowired
    private OtrosServiciosPrestadosQueryService otrosServiciosPrestadosQueryService;

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

    private MockMvc restOtrosServiciosPrestadosMockMvc;

    private OtrosServiciosPrestados otrosServiciosPrestados;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OtrosServiciosPrestadosResource otrosServiciosPrestadosResource = new OtrosServiciosPrestadosResource(otrosServiciosPrestadosService, otrosServiciosPrestadosQueryService);
        this.restOtrosServiciosPrestadosMockMvc = MockMvcBuilders.standaloneSetup(otrosServiciosPrestadosResource)
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
    public static OtrosServiciosPrestados createEntity(EntityManager em) {
        OtrosServiciosPrestados otrosServiciosPrestados = new OtrosServiciosPrestados()
            .fecha(DEFAULT_FECHA)
            .referencias(DEFAULT_REFERENCIAS);
        return otrosServiciosPrestados;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OtrosServiciosPrestados createUpdatedEntity(EntityManager em) {
        OtrosServiciosPrestados otrosServiciosPrestados = new OtrosServiciosPrestados()
            .fecha(UPDATED_FECHA)
            .referencias(UPDATED_REFERENCIAS);
        return otrosServiciosPrestados;
    }

    @BeforeEach
    public void initTest() {
        otrosServiciosPrestados = createEntity(em);
    }

    @Test
    @Transactional
    public void createOtrosServiciosPrestados() throws Exception {
        int databaseSizeBeforeCreate = otrosServiciosPrestadosRepository.findAll().size();

        // Create the OtrosServiciosPrestados
        restOtrosServiciosPrestadosMockMvc.perform(post("/api/otros-servicios-prestados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otrosServiciosPrestados)))
            .andExpect(status().isCreated());

        // Validate the OtrosServiciosPrestados in the database
        List<OtrosServiciosPrestados> otrosServiciosPrestadosList = otrosServiciosPrestadosRepository.findAll();
        assertThat(otrosServiciosPrestadosList).hasSize(databaseSizeBeforeCreate + 1);
        OtrosServiciosPrestados testOtrosServiciosPrestados = otrosServiciosPrestadosList.get(otrosServiciosPrestadosList.size() - 1);
        assertThat(testOtrosServiciosPrestados.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testOtrosServiciosPrestados.getReferencias()).isEqualTo(DEFAULT_REFERENCIAS);
    }

    @Test
    @Transactional
    public void createOtrosServiciosPrestadosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = otrosServiciosPrestadosRepository.findAll().size();

        // Create the OtrosServiciosPrestados with an existing ID
        otrosServiciosPrestados.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOtrosServiciosPrestadosMockMvc.perform(post("/api/otros-servicios-prestados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otrosServiciosPrestados)))
            .andExpect(status().isBadRequest());

        // Validate the OtrosServiciosPrestados in the database
        List<OtrosServiciosPrestados> otrosServiciosPrestadosList = otrosServiciosPrestadosRepository.findAll();
        assertThat(otrosServiciosPrestadosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOtrosServiciosPrestados() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList
        restOtrosServiciosPrestadosMockMvc.perform(get("/api/otros-servicios-prestados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(otrosServiciosPrestados.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));
    }
    
    @Test
    @Transactional
    public void getOtrosServiciosPrestados() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get the otrosServiciosPrestados
        restOtrosServiciosPrestadosMockMvc.perform(get("/api/otros-servicios-prestados/{id}", otrosServiciosPrestados.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(otrosServiciosPrestados.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.referencias").value(DEFAULT_REFERENCIAS));
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha equals to DEFAULT_FECHA
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the otrosServiciosPrestadosList where fecha equals to UPDATED_FECHA
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha not equals to DEFAULT_FECHA
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the otrosServiciosPrestadosList where fecha not equals to UPDATED_FECHA
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the otrosServiciosPrestadosList where fecha equals to UPDATED_FECHA
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha is not null
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.specified=true");

        // Get all the otrosServiciosPrestadosList where fecha is null
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha is greater than or equal to DEFAULT_FECHA
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the otrosServiciosPrestadosList where fecha is greater than or equal to UPDATED_FECHA
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha is less than or equal to DEFAULT_FECHA
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the otrosServiciosPrestadosList where fecha is less than or equal to SMALLER_FECHA
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha is less than DEFAULT_FECHA
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the otrosServiciosPrestadosList where fecha is less than UPDATED_FECHA
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where fecha is greater than DEFAULT_FECHA
        defaultOtrosServiciosPrestadosShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the otrosServiciosPrestadosList where fecha is greater than SMALLER_FECHA
        defaultOtrosServiciosPrestadosShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }


    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByReferenciasIsEqualToSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where referencias equals to DEFAULT_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldBeFound("referencias.equals=" + DEFAULT_REFERENCIAS);

        // Get all the otrosServiciosPrestadosList where referencias equals to UPDATED_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldNotBeFound("referencias.equals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByReferenciasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where referencias not equals to DEFAULT_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldNotBeFound("referencias.notEquals=" + DEFAULT_REFERENCIAS);

        // Get all the otrosServiciosPrestadosList where referencias not equals to UPDATED_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldBeFound("referencias.notEquals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByReferenciasIsInShouldWork() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where referencias in DEFAULT_REFERENCIAS or UPDATED_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldBeFound("referencias.in=" + DEFAULT_REFERENCIAS + "," + UPDATED_REFERENCIAS);

        // Get all the otrosServiciosPrestadosList where referencias equals to UPDATED_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldNotBeFound("referencias.in=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByReferenciasIsNullOrNotNull() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where referencias is not null
        defaultOtrosServiciosPrestadosShouldBeFound("referencias.specified=true");

        // Get all the otrosServiciosPrestadosList where referencias is null
        defaultOtrosServiciosPrestadosShouldNotBeFound("referencias.specified=false");
    }
                @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByReferenciasContainsSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where referencias contains DEFAULT_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldBeFound("referencias.contains=" + DEFAULT_REFERENCIAS);

        // Get all the otrosServiciosPrestadosList where referencias contains UPDATED_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldNotBeFound("referencias.contains=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByReferenciasNotContainsSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);

        // Get all the otrosServiciosPrestadosList where referencias does not contain DEFAULT_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldNotBeFound("referencias.doesNotContain=" + DEFAULT_REFERENCIAS);

        // Get all the otrosServiciosPrestadosList where referencias does not contain UPDATED_REFERENCIAS
        defaultOtrosServiciosPrestadosShouldBeFound("referencias.doesNotContain=" + UPDATED_REFERENCIAS);
    }


    @Test
    @Transactional
    public void getAllOtrosServiciosPrestadosByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        otrosServiciosPrestados.setPersona(persona);
        otrosServiciosPrestadosRepository.saveAndFlush(otrosServiciosPrestados);
        Long personaId = persona.getId();

        // Get all the otrosServiciosPrestadosList where persona equals to personaId
        defaultOtrosServiciosPrestadosShouldBeFound("personaId.equals=" + personaId);

        // Get all the otrosServiciosPrestadosList where persona equals to personaId + 1
        defaultOtrosServiciosPrestadosShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultOtrosServiciosPrestadosShouldBeFound(String filter) throws Exception {
        restOtrosServiciosPrestadosMockMvc.perform(get("/api/otros-servicios-prestados?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(otrosServiciosPrestados.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));

        // Check, that the count call also returns 1
        restOtrosServiciosPrestadosMockMvc.perform(get("/api/otros-servicios-prestados/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultOtrosServiciosPrestadosShouldNotBeFound(String filter) throws Exception {
        restOtrosServiciosPrestadosMockMvc.perform(get("/api/otros-servicios-prestados?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restOtrosServiciosPrestadosMockMvc.perform(get("/api/otros-servicios-prestados/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingOtrosServiciosPrestados() throws Exception {
        // Get the otrosServiciosPrestados
        restOtrosServiciosPrestadosMockMvc.perform(get("/api/otros-servicios-prestados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOtrosServiciosPrestados() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosService.save(otrosServiciosPrestados);

        int databaseSizeBeforeUpdate = otrosServiciosPrestadosRepository.findAll().size();

        // Update the otrosServiciosPrestados
        OtrosServiciosPrestados updatedOtrosServiciosPrestados = otrosServiciosPrestadosRepository.findById(otrosServiciosPrestados.getId()).get();
        // Disconnect from session so that the updates on updatedOtrosServiciosPrestados are not directly saved in db
        em.detach(updatedOtrosServiciosPrestados);
        updatedOtrosServiciosPrestados
            .fecha(UPDATED_FECHA)
            .referencias(UPDATED_REFERENCIAS);

        restOtrosServiciosPrestadosMockMvc.perform(put("/api/otros-servicios-prestados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOtrosServiciosPrestados)))
            .andExpect(status().isOk());

        // Validate the OtrosServiciosPrestados in the database
        List<OtrosServiciosPrestados> otrosServiciosPrestadosList = otrosServiciosPrestadosRepository.findAll();
        assertThat(otrosServiciosPrestadosList).hasSize(databaseSizeBeforeUpdate);
        OtrosServiciosPrestados testOtrosServiciosPrestados = otrosServiciosPrestadosList.get(otrosServiciosPrestadosList.size() - 1);
        assertThat(testOtrosServiciosPrestados.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testOtrosServiciosPrestados.getReferencias()).isEqualTo(UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void updateNonExistingOtrosServiciosPrestados() throws Exception {
        int databaseSizeBeforeUpdate = otrosServiciosPrestadosRepository.findAll().size();

        // Create the OtrosServiciosPrestados

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOtrosServiciosPrestadosMockMvc.perform(put("/api/otros-servicios-prestados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otrosServiciosPrestados)))
            .andExpect(status().isBadRequest());

        // Validate the OtrosServiciosPrestados in the database
        List<OtrosServiciosPrestados> otrosServiciosPrestadosList = otrosServiciosPrestadosRepository.findAll();
        assertThat(otrosServiciosPrestadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOtrosServiciosPrestados() throws Exception {
        // Initialize the database
        otrosServiciosPrestadosService.save(otrosServiciosPrestados);

        int databaseSizeBeforeDelete = otrosServiciosPrestadosRepository.findAll().size();

        // Delete the otrosServiciosPrestados
        restOtrosServiciosPrestadosMockMvc.perform(delete("/api/otros-servicios-prestados/{id}", otrosServiciosPrestados.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OtrosServiciosPrestados> otrosServiciosPrestadosList = otrosServiciosPrestadosRepository.findAll();
        assertThat(otrosServiciosPrestadosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OtrosServiciosPrestados.class);
        OtrosServiciosPrestados otrosServiciosPrestados1 = new OtrosServiciosPrestados();
        otrosServiciosPrestados1.setId(1L);
        OtrosServiciosPrestados otrosServiciosPrestados2 = new OtrosServiciosPrestados();
        otrosServiciosPrestados2.setId(otrosServiciosPrestados1.getId());
        assertThat(otrosServiciosPrestados1).isEqualTo(otrosServiciosPrestados2);
        otrosServiciosPrestados2.setId(2L);
        assertThat(otrosServiciosPrestados1).isNotEqualTo(otrosServiciosPrestados2);
        otrosServiciosPrestados1.setId(null);
        assertThat(otrosServiciosPrestados1).isNotEqualTo(otrosServiciosPrestados2);
    }
}
