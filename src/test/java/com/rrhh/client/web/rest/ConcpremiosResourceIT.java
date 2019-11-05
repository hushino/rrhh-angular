package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.Concpremios;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.repository.ConcpremiosRepository;
import com.rrhh.client.service.ConcpremiosService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.ConcpremiosCriteria;
import com.rrhh.client.service.ConcpremiosQueryService;

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
 * Integration tests for the {@link ConcpremiosResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class ConcpremiosResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_REFERENCIAS = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIAS = "BBBBBBBBBB";

    @Autowired
    private ConcpremiosRepository concpremiosRepository;

    @Autowired
    private ConcpremiosService concpremiosService;

    @Autowired
    private ConcpremiosQueryService concpremiosQueryService;

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

    private MockMvc restConcpremiosMockMvc;

    private Concpremios concpremios;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConcpremiosResource concpremiosResource = new ConcpremiosResource(concpremiosService, concpremiosQueryService);
        this.restConcpremiosMockMvc = MockMvcBuilders.standaloneSetup(concpremiosResource)
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
    public static Concpremios createEntity(EntityManager em) {
        Concpremios concpremios = new Concpremios()
            .fecha(DEFAULT_FECHA)
            .referencias(DEFAULT_REFERENCIAS);
        return concpremios;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Concpremios createUpdatedEntity(EntityManager em) {
        Concpremios concpremios = new Concpremios()
            .fecha(UPDATED_FECHA)
            .referencias(UPDATED_REFERENCIAS);
        return concpremios;
    }

    @BeforeEach
    public void initTest() {
        concpremios = createEntity(em);
    }

    @Test
    @Transactional
    public void createConcpremios() throws Exception {
        int databaseSizeBeforeCreate = concpremiosRepository.findAll().size();

        // Create the Concpremios
        restConcpremiosMockMvc.perform(post("/api/concpremios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concpremios)))
            .andExpect(status().isCreated());

        // Validate the Concpremios in the database
        List<Concpremios> concpremiosList = concpremiosRepository.findAll();
        assertThat(concpremiosList).hasSize(databaseSizeBeforeCreate + 1);
        Concpremios testConcpremios = concpremiosList.get(concpremiosList.size() - 1);
        assertThat(testConcpremios.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testConcpremios.getReferencias()).isEqualTo(DEFAULT_REFERENCIAS);
    }

    @Test
    @Transactional
    public void createConcpremiosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = concpremiosRepository.findAll().size();

        // Create the Concpremios with an existing ID
        concpremios.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConcpremiosMockMvc.perform(post("/api/concpremios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concpremios)))
            .andExpect(status().isBadRequest());

        // Validate the Concpremios in the database
        List<Concpremios> concpremiosList = concpremiosRepository.findAll();
        assertThat(concpremiosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConcpremios() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList
        restConcpremiosMockMvc.perform(get("/api/concpremios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(concpremios.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));
    }
    
    @Test
    @Transactional
    public void getConcpremios() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get the concpremios
        restConcpremiosMockMvc.perform(get("/api/concpremios/{id}", concpremios.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(concpremios.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.referencias").value(DEFAULT_REFERENCIAS));
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsEqualToSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha equals to DEFAULT_FECHA
        defaultConcpremiosShouldBeFound("fecha.equals=" + DEFAULT_FECHA);

        // Get all the concpremiosList where fecha equals to UPDATED_FECHA
        defaultConcpremiosShouldNotBeFound("fecha.equals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha not equals to DEFAULT_FECHA
        defaultConcpremiosShouldNotBeFound("fecha.notEquals=" + DEFAULT_FECHA);

        // Get all the concpremiosList where fecha not equals to UPDATED_FECHA
        defaultConcpremiosShouldBeFound("fecha.notEquals=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsInShouldWork() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha in DEFAULT_FECHA or UPDATED_FECHA
        defaultConcpremiosShouldBeFound("fecha.in=" + DEFAULT_FECHA + "," + UPDATED_FECHA);

        // Get all the concpremiosList where fecha equals to UPDATED_FECHA
        defaultConcpremiosShouldNotBeFound("fecha.in=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsNullOrNotNull() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha is not null
        defaultConcpremiosShouldBeFound("fecha.specified=true");

        // Get all the concpremiosList where fecha is null
        defaultConcpremiosShouldNotBeFound("fecha.specified=false");
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha is greater than or equal to DEFAULT_FECHA
        defaultConcpremiosShouldBeFound("fecha.greaterThanOrEqual=" + DEFAULT_FECHA);

        // Get all the concpremiosList where fecha is greater than or equal to UPDATED_FECHA
        defaultConcpremiosShouldNotBeFound("fecha.greaterThanOrEqual=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha is less than or equal to DEFAULT_FECHA
        defaultConcpremiosShouldBeFound("fecha.lessThanOrEqual=" + DEFAULT_FECHA);

        // Get all the concpremiosList where fecha is less than or equal to SMALLER_FECHA
        defaultConcpremiosShouldNotBeFound("fecha.lessThanOrEqual=" + SMALLER_FECHA);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsLessThanSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha is less than DEFAULT_FECHA
        defaultConcpremiosShouldNotBeFound("fecha.lessThan=" + DEFAULT_FECHA);

        // Get all the concpremiosList where fecha is less than UPDATED_FECHA
        defaultConcpremiosShouldBeFound("fecha.lessThan=" + UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByFechaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where fecha is greater than DEFAULT_FECHA
        defaultConcpremiosShouldNotBeFound("fecha.greaterThan=" + DEFAULT_FECHA);

        // Get all the concpremiosList where fecha is greater than SMALLER_FECHA
        defaultConcpremiosShouldBeFound("fecha.greaterThan=" + SMALLER_FECHA);
    }


    @Test
    @Transactional
    public void getAllConcpremiosByReferenciasIsEqualToSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where referencias equals to DEFAULT_REFERENCIAS
        defaultConcpremiosShouldBeFound("referencias.equals=" + DEFAULT_REFERENCIAS);

        // Get all the concpremiosList where referencias equals to UPDATED_REFERENCIAS
        defaultConcpremiosShouldNotBeFound("referencias.equals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByReferenciasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where referencias not equals to DEFAULT_REFERENCIAS
        defaultConcpremiosShouldNotBeFound("referencias.notEquals=" + DEFAULT_REFERENCIAS);

        // Get all the concpremiosList where referencias not equals to UPDATED_REFERENCIAS
        defaultConcpremiosShouldBeFound("referencias.notEquals=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByReferenciasIsInShouldWork() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where referencias in DEFAULT_REFERENCIAS or UPDATED_REFERENCIAS
        defaultConcpremiosShouldBeFound("referencias.in=" + DEFAULT_REFERENCIAS + "," + UPDATED_REFERENCIAS);

        // Get all the concpremiosList where referencias equals to UPDATED_REFERENCIAS
        defaultConcpremiosShouldNotBeFound("referencias.in=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByReferenciasIsNullOrNotNull() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where referencias is not null
        defaultConcpremiosShouldBeFound("referencias.specified=true");

        // Get all the concpremiosList where referencias is null
        defaultConcpremiosShouldNotBeFound("referencias.specified=false");
    }
                @Test
    @Transactional
    public void getAllConcpremiosByReferenciasContainsSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where referencias contains DEFAULT_REFERENCIAS
        defaultConcpremiosShouldBeFound("referencias.contains=" + DEFAULT_REFERENCIAS);

        // Get all the concpremiosList where referencias contains UPDATED_REFERENCIAS
        defaultConcpremiosShouldNotBeFound("referencias.contains=" + UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void getAllConcpremiosByReferenciasNotContainsSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);

        // Get all the concpremiosList where referencias does not contain DEFAULT_REFERENCIAS
        defaultConcpremiosShouldNotBeFound("referencias.doesNotContain=" + DEFAULT_REFERENCIAS);

        // Get all the concpremiosList where referencias does not contain UPDATED_REFERENCIAS
        defaultConcpremiosShouldBeFound("referencias.doesNotContain=" + UPDATED_REFERENCIAS);
    }


    @Test
    @Transactional
    public void getAllConcpremiosByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        concpremiosRepository.saveAndFlush(concpremios);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        concpremios.setPersona(persona);
        concpremiosRepository.saveAndFlush(concpremios);
        Long personaId = persona.getId();

        // Get all the concpremiosList where persona equals to personaId
        defaultConcpremiosShouldBeFound("personaId.equals=" + personaId);

        // Get all the concpremiosList where persona equals to personaId + 1
        defaultConcpremiosShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultConcpremiosShouldBeFound(String filter) throws Exception {
        restConcpremiosMockMvc.perform(get("/api/concpremios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(concpremios.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].referencias").value(hasItem(DEFAULT_REFERENCIAS)));

        // Check, that the count call also returns 1
        restConcpremiosMockMvc.perform(get("/api/concpremios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultConcpremiosShouldNotBeFound(String filter) throws Exception {
        restConcpremiosMockMvc.perform(get("/api/concpremios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restConcpremiosMockMvc.perform(get("/api/concpremios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingConcpremios() throws Exception {
        // Get the concpremios
        restConcpremiosMockMvc.perform(get("/api/concpremios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConcpremios() throws Exception {
        // Initialize the database
        concpremiosService.save(concpremios);

        int databaseSizeBeforeUpdate = concpremiosRepository.findAll().size();

        // Update the concpremios
        Concpremios updatedConcpremios = concpremiosRepository.findById(concpremios.getId()).get();
        // Disconnect from session so that the updates on updatedConcpremios are not directly saved in db
        em.detach(updatedConcpremios);
        updatedConcpremios
            .fecha(UPDATED_FECHA)
            .referencias(UPDATED_REFERENCIAS);

        restConcpremiosMockMvc.perform(put("/api/concpremios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConcpremios)))
            .andExpect(status().isOk());

        // Validate the Concpremios in the database
        List<Concpremios> concpremiosList = concpremiosRepository.findAll();
        assertThat(concpremiosList).hasSize(databaseSizeBeforeUpdate);
        Concpremios testConcpremios = concpremiosList.get(concpremiosList.size() - 1);
        assertThat(testConcpremios.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testConcpremios.getReferencias()).isEqualTo(UPDATED_REFERENCIAS);
    }

    @Test
    @Transactional
    public void updateNonExistingConcpremios() throws Exception {
        int databaseSizeBeforeUpdate = concpremiosRepository.findAll().size();

        // Create the Concpremios

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConcpremiosMockMvc.perform(put("/api/concpremios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(concpremios)))
            .andExpect(status().isBadRequest());

        // Validate the Concpremios in the database
        List<Concpremios> concpremiosList = concpremiosRepository.findAll();
        assertThat(concpremiosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConcpremios() throws Exception {
        // Initialize the database
        concpremiosService.save(concpremios);

        int databaseSizeBeforeDelete = concpremiosRepository.findAll().size();

        // Delete the concpremios
        restConcpremiosMockMvc.perform(delete("/api/concpremios/{id}", concpremios.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Concpremios> concpremiosList = concpremiosRepository.findAll();
        assertThat(concpremiosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Concpremios.class);
        Concpremios concpremios1 = new Concpremios();
        concpremios1.setId(1L);
        Concpremios concpremios2 = new Concpremios();
        concpremios2.setId(concpremios1.getId());
        assertThat(concpremios1).isEqualTo(concpremios2);
        concpremios2.setId(2L);
        assertThat(concpremios1).isNotEqualTo(concpremios2);
        concpremios1.setId(null);
        assertThat(concpremios1).isNotEqualTo(concpremios2);
    }
}
