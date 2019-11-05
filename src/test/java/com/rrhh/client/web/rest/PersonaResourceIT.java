package com.rrhh.client.web.rest;

import com.rrhh.client.RrhhApp;
import com.rrhh.client.domain.Persona;
import com.rrhh.client.domain.Licencia;
import com.rrhh.client.domain.AltasAscensosBajas;
import com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios;
import com.rrhh.client.domain.Embargos;
import com.rrhh.client.domain.Garantia;
import com.rrhh.client.domain.OtrosServiciosPrestados;
import com.rrhh.client.domain.PenasDisciplinariasSufridas;
import com.rrhh.client.repository.PersonaRepository;
import com.rrhh.client.service.PersonaService;
import com.rrhh.client.web.rest.errors.ExceptionTranslator;
import com.rrhh.client.service.dto.PersonaCriteria;
import com.rrhh.client.service.PersonaQueryService;

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
import org.springframework.util.Base64Utils;
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
 * Integration tests for the {@link PersonaResource} REST controller.
 */
@SpringBootTest(classes = RrhhApp.class)
public class PersonaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_CUIL = "AAAAAAAAAA";
    private static final String UPDATED_CUIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_DNI = 1;
    private static final Integer UPDATED_DNI = 2;
    private static final Integer SMALLER_DNI = 1 - 1;

    private static final Integer DEFAULT_LEGAJO = 1;
    private static final Integer UPDATED_LEGAJO = 2;
    private static final Integer SMALLER_LEGAJO = 1 - 1;

    private static final String DEFAULT_APODO = "AAAAAAAAAA";
    private static final String UPDATED_APODO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SOLTERO = "AAAAAAAAAA";
    private static final String UPDATED_SOLTERO = "BBBBBBBBBB";

    private static final String DEFAULT_CASADO = "AAAAAAAAAA";
    private static final String UPDATED_CASADO = "BBBBBBBBBB";

    private static final String DEFAULT_CONVIVIENTE = "AAAAAAAAAA";
    private static final String UPDATED_CONVIVIENTE = "BBBBBBBBBB";

    private static final String DEFAULT_VIUDO = "AAAAAAAAAA";
    private static final String UPDATED_VIUDO = "BBBBBBBBBB";

    private static final String DEFAULT_DOMICILIO = "AAAAAAAAAA";
    private static final String UPDATED_DOMICILIO = "BBBBBBBBBB";

    private static final String DEFAULT_LUGAR = "AAAAAAAAAA";
    private static final String UPDATED_LUGAR = "BBBBBBBBBB";

    private static final String DEFAULT_CALLE = "AAAAAAAAAA";
    private static final String UPDATED_CALLE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONOFIJO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONOFIJO = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERODECELULAR = "AAAAAAAAAA";
    private static final String UPDATED_NUMERODECELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_OFICIOPROFECION = "AAAAAAAAAA";
    private static final String UPDATED_OFICIOPROFECION = "BBBBBBBBBB";

    private static final String DEFAULT_NIVELDEESTUDIOS = "AAAAAAAAAA";
    private static final String UPDATED_NIVELDEESTUDIOS = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOSANGUINEO = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOSANGUINEO = "BBBBBBBBBB";

    private static final String DEFAULT_FACTOR = "AAAAAAAAAA";
    private static final String UPDATED_FACTOR = "BBBBBBBBBB";

    private static final String DEFAULT_DONANTE = "AAAAAAAAAA";
    private static final String UPDATED_DONANTE = "BBBBBBBBBB";

    private static final String DEFAULT_DIABETES = "AAAAAAAAAA";
    private static final String UPDATED_DIABETES = "BBBBBBBBBB";

    private static final String DEFAULT_HIPERTENSION = "AAAAAAAAAA";
    private static final String UPDATED_HIPERTENSION = "BBBBBBBBBB";

    private static final String DEFAULT_ALERGIAS = "AAAAAAAAAA";
    private static final String UPDATED_ALERGIAS = "BBBBBBBBBB";

    private static final String DEFAULT_ASMA = "AAAAAAAAAA";
    private static final String UPDATED_ASMA = "BBBBBBBBBB";

    private static final String DEFAULT_OTROS = "AAAAAAAAAA";
    private static final String UPDATED_OTROS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHADEINGRESO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHADEINGRESO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHADEINGRESO = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_INSTRUMENTOLEGAL = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENTOLEGAL = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_PLANTA = "AAAAAAAAAA";
    private static final String UPDATED_PLANTA = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNOS = 1;
    private static final Integer UPDATED_ANNOS = 2;
    private static final Integer SMALLER_ANNOS = 1 - 1;

    private static final Integer DEFAULT_MESES = 1;
    private static final Integer UPDATED_MESES = 2;
    private static final Integer SMALLER_MESES = 1 - 1;

    private static final Integer DEFAULT_DIAS = 1;
    private static final Integer UPDATED_DIAS = 2;
    private static final Integer SMALLER_DIAS = 1 - 1;

    private static final String DEFAULT_REALIZOCOMPUTODESERVICIOS = "AAAAAAAAAA";
    private static final String UPDATED_REALIZOCOMPUTODESERVICIOS = "BBBBBBBBBB";

    private static final String DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES = "AAAAAAAAAA";
    private static final String UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES = "BBBBBBBBBB";

    private static final String DEFAULT_CASOEMERGENCIACELULAR = "AAAAAAAAAA";
    private static final String UPDATED_CASOEMERGENCIACELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_CASOEMERGENCIAFIJO = "AAAAAAAAAA";
    private static final String UPDATED_CASOEMERGENCIAFIJO = "BBBBBBBBBB";

    private static final String DEFAULT_CASOEMERGENCIANOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_CASOEMERGENCIANOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_CASOEMERGENCIACELULAR_2 = "AAAAAAAAAA";
    private static final String UPDATED_CASOEMERGENCIACELULAR_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CASOEMERGENCIAFIJO_2 = "AAAAAAAAAA";
    private static final String UPDATED_CASOEMERGENCIAFIJO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CASOEMERGENCIANOMBRE_2 = "AAAAAAAAAA";
    private static final String UPDATED_CASOEMERGENCIANOMBRE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGONOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGONOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGONOMBRE_2 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGONOMBRE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGONOMBRE_3 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGONOMBRE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGONOMBRE_4 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGONOMBRE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGONOMBRE_5 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGONOMBRE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGODNI = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGODNI = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGODNI_2 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGODNI_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGODNI_3 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGODNI_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGODNI_4 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGODNI_4 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGODNI_5 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGODNI_5 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGOEDAD = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGOEDAD = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGOEDAD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGOEDAD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGOEDAD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGOEDAD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGOEDAD_4 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGOEDAD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILIARACARGOEDAD_5 = "AAAAAAAAAA";
    private static final String UPDATED_FAMILIARACARGOEDAD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ALTURA = "AAAAAAAAAA";
    private static final String UPDATED_ALTURA = "BBBBBBBBBB";

    private static final String DEFAULT_BARRIO = "AAAAAAAAAA";
    private static final String UPDATED_BARRIO = "BBBBBBBBBB";

    private static final String DEFAULT_ESTUDIOSINCOMPLETOS = "AAAAAAAAAA";
    private static final String UPDATED_ESTUDIOSINCOMPLETOS = "BBBBBBBBBB";

    private static final String DEFAULT_CONYUGEAPELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_CONYUGEAPELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_CONYUGENOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_CONYUGENOMBRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONYUGEDNI = 1;
    private static final Integer UPDATED_CONYUGEDNI = 2;
    private static final Integer SMALLER_CONYUGEDNI = 1 - 1;

    private static final String DEFAULT_CONYUGECUIL = "AAAAAAAAAA";
    private static final String UPDATED_CONYUGECUIL = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11 = "BBBBBBBBBB";

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_2 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_3 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_4 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_5 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_6 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_7 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_8 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_9 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_10 = 1 - 1;

    private static final Integer DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11 = 1;
    private static final Integer UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11 = 2;
    private static final Integer SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_11 = 1 - 1;

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10 = "BBBBBBBBBB";

    private static final String DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11 = "AAAAAAAAAA";
    private static final String UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11 = "BBBBBBBBBB";

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaQueryService personaQueryService;

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

    private MockMvc restPersonaMockMvc;

    private Persona persona;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PersonaResource personaResource = new PersonaResource(personaService, personaQueryService);
        this.restPersonaMockMvc = MockMvcBuilders.standaloneSetup(personaResource)
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
    public static Persona createEntity(EntityManager em) {
        Persona persona = new Persona()
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .cuil(DEFAULT_CUIL)
            .dni(DEFAULT_DNI)
            .legajo(DEFAULT_LEGAJO)
            .apodo(DEFAULT_APODO)
            .foto(DEFAULT_FOTO)
            .fotoContentType(DEFAULT_FOTO_CONTENT_TYPE)
            .soltero(DEFAULT_SOLTERO)
            .casado(DEFAULT_CASADO)
            .conviviente(DEFAULT_CONVIVIENTE)
            .viudo(DEFAULT_VIUDO)
            .domicilio(DEFAULT_DOMICILIO)
            .lugar(DEFAULT_LUGAR)
            .calle(DEFAULT_CALLE)
            .numero(DEFAULT_NUMERO)
            .telefonofijo(DEFAULT_TELEFONOFIJO)
            .numerodecelular(DEFAULT_NUMERODECELULAR)
            .oficioprofecion(DEFAULT_OFICIOPROFECION)
            .niveldeestudios(DEFAULT_NIVELDEESTUDIOS)
            .gruposanguineo(DEFAULT_GRUPOSANGUINEO)
            .factor(DEFAULT_FACTOR)
            .donante(DEFAULT_DONANTE)
            .diabetes(DEFAULT_DIABETES)
            .hipertension(DEFAULT_HIPERTENSION)
            .alergias(DEFAULT_ALERGIAS)
            .asma(DEFAULT_ASMA)
            .otros(DEFAULT_OTROS)
            .fechadeingreso(DEFAULT_FECHADEINGRESO)
            .instrumentolegal(DEFAULT_INSTRUMENTOLEGAL)
            .categoria(DEFAULT_CATEGORIA)
            .item(DEFAULT_ITEM)
            .planta(DEFAULT_PLANTA)
            .area(DEFAULT_AREA)
            .direccion(DEFAULT_DIRECCION)
            .annos(DEFAULT_ANNOS)
            .meses(DEFAULT_MESES)
            .dias(DEFAULT_DIAS)
            .realizocomputodeservicios(DEFAULT_REALIZOCOMPUTODESERVICIOS)
            .poseeconocimientoenmaquinasviales(DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES)
            .casoemergenciacelular(DEFAULT_CASOEMERGENCIACELULAR)
            .casoemergenciafijo(DEFAULT_CASOEMERGENCIAFIJO)
            .casoemergencianombre(DEFAULT_CASOEMERGENCIANOMBRE)
            .casoemergenciacelular2(DEFAULT_CASOEMERGENCIACELULAR_2)
            .casoemergenciafijo2(DEFAULT_CASOEMERGENCIAFIJO_2)
            .casoemergencianombre2(DEFAULT_CASOEMERGENCIANOMBRE_2)
            .familiaracargonombre(DEFAULT_FAMILIARACARGONOMBRE)
            .familiaracargonombre2(DEFAULT_FAMILIARACARGONOMBRE_2)
            .familiaracargonombre3(DEFAULT_FAMILIARACARGONOMBRE_3)
            .familiaracargonombre4(DEFAULT_FAMILIARACARGONOMBRE_4)
            .familiaracargonombre5(DEFAULT_FAMILIARACARGONOMBRE_5)
            .familiaracargodni(DEFAULT_FAMILIARACARGODNI)
            .familiaracargodni2(DEFAULT_FAMILIARACARGODNI_2)
            .familiaracargodni3(DEFAULT_FAMILIARACARGODNI_3)
            .familiaracargodni4(DEFAULT_FAMILIARACARGODNI_4)
            .familiaracargodni5(DEFAULT_FAMILIARACARGODNI_5)
            .familiaracargoedad(DEFAULT_FAMILIARACARGOEDAD)
            .familiaracargoedad2(DEFAULT_FAMILIARACARGOEDAD_2)
            .familiaracargoedad3(DEFAULT_FAMILIARACARGOEDAD_3)
            .familiaracargoedad4(DEFAULT_FAMILIARACARGOEDAD_4)
            .familiaracargoedad5(DEFAULT_FAMILIARACARGOEDAD_5)
            .altura(DEFAULT_ALTURA)
            .barrio(DEFAULT_BARRIO)
            .estudiosincompletos(DEFAULT_ESTUDIOSINCOMPLETOS)
            .conyugeapellido(DEFAULT_CONYUGEAPELLIDO)
            .conyugenombre(DEFAULT_CONYUGENOMBRE)
            .conyugedni(DEFAULT_CONYUGEDNI)
            .conyugecuil(DEFAULT_CONYUGECUIL)
            .grupofamiliarapellidonombre(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE)
            .grupofamiliarapellidonombre2(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2)
            .grupofamiliarapellidonombre3(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3)
            .grupofamiliarapellidonombre4(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4)
            .grupofamiliarapellidonombre5(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5)
            .grupofamiliarapellidonombre6(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6)
            .grupofamiliarapellidonombre7(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7)
            .grupofamiliarapellidonombre8(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8)
            .grupofamiliarapellidonombre9(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9)
            .grupofamiliarapellidonombre10(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10)
            .grupofamiliarapellidonombre11(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11)
            .grupofamiliarapellidonombreedad(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD)
            .grupofamiliarapellidonombreedad2(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2)
            .grupofamiliarapellidonombreedad3(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3)
            .grupofamiliarapellidonombreedad4(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4)
            .grupofamiliarapellidonombreedad5(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5)
            .grupofamiliarapellidonombreedad6(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6)
            .grupofamiliarapellidonombreedad7(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7)
            .grupofamiliarapellidonombreedad8(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8)
            .grupofamiliarapellidonombreedad9(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9)
            .grupofamiliarapellidonombreedad10(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10)
            .grupofamiliarapellidonombreedad11(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11)
            .grupofamiliarapellidonombredni(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI)
            .grupofamiliarapellidonombredni2(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2)
            .grupofamiliarapellidonombredni3(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3)
            .grupofamiliarapellidonombredni4(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4)
            .grupofamiliarapellidonombredni5(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5)
            .grupofamiliarapellidonombredni6(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6)
            .grupofamiliarapellidonombredni7(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7)
            .grupofamiliarapellidonombredni8(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8)
            .grupofamiliarapellidonombredni9(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9)
            .grupofamiliarapellidonombredni10(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10)
            .grupofamiliarapellidonombredni11(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11)
            .grupofamiliarapellidonombrefamiliar(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR)
            .grupofamiliarapellidonombrefamiliar2(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2)
            .grupofamiliarapellidonombrefamiliar4(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4)
            .grupofamiliarapellidonombrefamiliar3(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3)
            .grupofamiliarapellidonombrefamiliar5(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5)
            .grupofamiliarapellidonombrefamiliar6(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6)
            .grupofamiliarapellidonombrefamiliar7(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7)
            .grupofamiliarapellidonombrefamiliar8(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8)
            .grupofamiliarapellidonombrefamiliar9(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9)
            .grupofamiliarapellidonombrefamiliar10(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10)
            .grupofamiliarapellidonombrefamiliar11(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
        return persona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persona createUpdatedEntity(EntityManager em) {
        Persona persona = new Persona()
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .cuil(UPDATED_CUIL)
            .dni(UPDATED_DNI)
            .legajo(UPDATED_LEGAJO)
            .apodo(UPDATED_APODO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .soltero(UPDATED_SOLTERO)
            .casado(UPDATED_CASADO)
            .conviviente(UPDATED_CONVIVIENTE)
            .viudo(UPDATED_VIUDO)
            .domicilio(UPDATED_DOMICILIO)
            .lugar(UPDATED_LUGAR)
            .calle(UPDATED_CALLE)
            .numero(UPDATED_NUMERO)
            .telefonofijo(UPDATED_TELEFONOFIJO)
            .numerodecelular(UPDATED_NUMERODECELULAR)
            .oficioprofecion(UPDATED_OFICIOPROFECION)
            .niveldeestudios(UPDATED_NIVELDEESTUDIOS)
            .gruposanguineo(UPDATED_GRUPOSANGUINEO)
            .factor(UPDATED_FACTOR)
            .donante(UPDATED_DONANTE)
            .diabetes(UPDATED_DIABETES)
            .hipertension(UPDATED_HIPERTENSION)
            .alergias(UPDATED_ALERGIAS)
            .asma(UPDATED_ASMA)
            .otros(UPDATED_OTROS)
            .fechadeingreso(UPDATED_FECHADEINGRESO)
            .instrumentolegal(UPDATED_INSTRUMENTOLEGAL)
            .categoria(UPDATED_CATEGORIA)
            .item(UPDATED_ITEM)
            .planta(UPDATED_PLANTA)
            .area(UPDATED_AREA)
            .direccion(UPDATED_DIRECCION)
            .annos(UPDATED_ANNOS)
            .meses(UPDATED_MESES)
            .dias(UPDATED_DIAS)
            .realizocomputodeservicios(UPDATED_REALIZOCOMPUTODESERVICIOS)
            .poseeconocimientoenmaquinasviales(UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES)
            .casoemergenciacelular(UPDATED_CASOEMERGENCIACELULAR)
            .casoemergenciafijo(UPDATED_CASOEMERGENCIAFIJO)
            .casoemergencianombre(UPDATED_CASOEMERGENCIANOMBRE)
            .casoemergenciacelular2(UPDATED_CASOEMERGENCIACELULAR_2)
            .casoemergenciafijo2(UPDATED_CASOEMERGENCIAFIJO_2)
            .casoemergencianombre2(UPDATED_CASOEMERGENCIANOMBRE_2)
            .familiaracargonombre(UPDATED_FAMILIARACARGONOMBRE)
            .familiaracargonombre2(UPDATED_FAMILIARACARGONOMBRE_2)
            .familiaracargonombre3(UPDATED_FAMILIARACARGONOMBRE_3)
            .familiaracargonombre4(UPDATED_FAMILIARACARGONOMBRE_4)
            .familiaracargonombre5(UPDATED_FAMILIARACARGONOMBRE_5)
            .familiaracargodni(UPDATED_FAMILIARACARGODNI)
            .familiaracargodni2(UPDATED_FAMILIARACARGODNI_2)
            .familiaracargodni3(UPDATED_FAMILIARACARGODNI_3)
            .familiaracargodni4(UPDATED_FAMILIARACARGODNI_4)
            .familiaracargodni5(UPDATED_FAMILIARACARGODNI_5)
            .familiaracargoedad(UPDATED_FAMILIARACARGOEDAD)
            .familiaracargoedad2(UPDATED_FAMILIARACARGOEDAD_2)
            .familiaracargoedad3(UPDATED_FAMILIARACARGOEDAD_3)
            .familiaracargoedad4(UPDATED_FAMILIARACARGOEDAD_4)
            .familiaracargoedad5(UPDATED_FAMILIARACARGOEDAD_5)
            .altura(UPDATED_ALTURA)
            .barrio(UPDATED_BARRIO)
            .estudiosincompletos(UPDATED_ESTUDIOSINCOMPLETOS)
            .conyugeapellido(UPDATED_CONYUGEAPELLIDO)
            .conyugenombre(UPDATED_CONYUGENOMBRE)
            .conyugedni(UPDATED_CONYUGEDNI)
            .conyugecuil(UPDATED_CONYUGECUIL)
            .grupofamiliarapellidonombre(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE)
            .grupofamiliarapellidonombre2(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2)
            .grupofamiliarapellidonombre3(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3)
            .grupofamiliarapellidonombre4(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4)
            .grupofamiliarapellidonombre5(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5)
            .grupofamiliarapellidonombre6(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6)
            .grupofamiliarapellidonombre7(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7)
            .grupofamiliarapellidonombre8(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8)
            .grupofamiliarapellidonombre9(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9)
            .grupofamiliarapellidonombre10(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10)
            .grupofamiliarapellidonombre11(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11)
            .grupofamiliarapellidonombreedad(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD)
            .grupofamiliarapellidonombreedad2(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2)
            .grupofamiliarapellidonombreedad3(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3)
            .grupofamiliarapellidonombreedad4(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4)
            .grupofamiliarapellidonombreedad5(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5)
            .grupofamiliarapellidonombreedad6(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6)
            .grupofamiliarapellidonombreedad7(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7)
            .grupofamiliarapellidonombreedad8(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8)
            .grupofamiliarapellidonombreedad9(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9)
            .grupofamiliarapellidonombreedad10(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10)
            .grupofamiliarapellidonombreedad11(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11)
            .grupofamiliarapellidonombredni(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI)
            .grupofamiliarapellidonombredni2(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2)
            .grupofamiliarapellidonombredni3(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3)
            .grupofamiliarapellidonombredni4(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4)
            .grupofamiliarapellidonombredni5(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5)
            .grupofamiliarapellidonombredni6(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6)
            .grupofamiliarapellidonombredni7(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7)
            .grupofamiliarapellidonombredni8(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8)
            .grupofamiliarapellidonombredni9(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9)
            .grupofamiliarapellidonombredni10(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10)
            .grupofamiliarapellidonombredni11(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11)
            .grupofamiliarapellidonombrefamiliar(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR)
            .grupofamiliarapellidonombrefamiliar2(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2)
            .grupofamiliarapellidonombrefamiliar4(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4)
            .grupofamiliarapellidonombrefamiliar3(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3)
            .grupofamiliarapellidonombrefamiliar5(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5)
            .grupofamiliarapellidonombrefamiliar6(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6)
            .grupofamiliarapellidonombrefamiliar7(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7)
            .grupofamiliarapellidonombrefamiliar8(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8)
            .grupofamiliarapellidonombrefamiliar9(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9)
            .grupofamiliarapellidonombrefamiliar10(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10)
            .grupofamiliarapellidonombrefamiliar11(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
        return persona;
    }

    @BeforeEach
    public void initTest() {
        persona = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersona() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();

        // Create the Persona
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isCreated());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate + 1);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPersona.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testPersona.getCuil()).isEqualTo(DEFAULT_CUIL);
        assertThat(testPersona.getDni()).isEqualTo(DEFAULT_DNI);
        assertThat(testPersona.getLegajo()).isEqualTo(DEFAULT_LEGAJO);
        assertThat(testPersona.getApodo()).isEqualTo(DEFAULT_APODO);
        assertThat(testPersona.getFoto()).isEqualTo(DEFAULT_FOTO);
        assertThat(testPersona.getFotoContentType()).isEqualTo(DEFAULT_FOTO_CONTENT_TYPE);
        assertThat(testPersona.getSoltero()).isEqualTo(DEFAULT_SOLTERO);
        assertThat(testPersona.getCasado()).isEqualTo(DEFAULT_CASADO);
        assertThat(testPersona.getConviviente()).isEqualTo(DEFAULT_CONVIVIENTE);
        assertThat(testPersona.getViudo()).isEqualTo(DEFAULT_VIUDO);
        assertThat(testPersona.getDomicilio()).isEqualTo(DEFAULT_DOMICILIO);
        assertThat(testPersona.getLugar()).isEqualTo(DEFAULT_LUGAR);
        assertThat(testPersona.getCalle()).isEqualTo(DEFAULT_CALLE);
        assertThat(testPersona.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testPersona.getTelefonofijo()).isEqualTo(DEFAULT_TELEFONOFIJO);
        assertThat(testPersona.getNumerodecelular()).isEqualTo(DEFAULT_NUMERODECELULAR);
        assertThat(testPersona.getOficioprofecion()).isEqualTo(DEFAULT_OFICIOPROFECION);
        assertThat(testPersona.getNiveldeestudios()).isEqualTo(DEFAULT_NIVELDEESTUDIOS);
        assertThat(testPersona.getGruposanguineo()).isEqualTo(DEFAULT_GRUPOSANGUINEO);
        assertThat(testPersona.getFactor()).isEqualTo(DEFAULT_FACTOR);
        assertThat(testPersona.getDonante()).isEqualTo(DEFAULT_DONANTE);
        assertThat(testPersona.getDiabetes()).isEqualTo(DEFAULT_DIABETES);
        assertThat(testPersona.getHipertension()).isEqualTo(DEFAULT_HIPERTENSION);
        assertThat(testPersona.getAlergias()).isEqualTo(DEFAULT_ALERGIAS);
        assertThat(testPersona.getAsma()).isEqualTo(DEFAULT_ASMA);
        assertThat(testPersona.getOtros()).isEqualTo(DEFAULT_OTROS);
        assertThat(testPersona.getFechadeingreso()).isEqualTo(DEFAULT_FECHADEINGRESO);
        assertThat(testPersona.getInstrumentolegal()).isEqualTo(DEFAULT_INSTRUMENTOLEGAL);
        assertThat(testPersona.getCategoria()).isEqualTo(DEFAULT_CATEGORIA);
        assertThat(testPersona.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testPersona.getPlanta()).isEqualTo(DEFAULT_PLANTA);
        assertThat(testPersona.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testPersona.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testPersona.getAnnos()).isEqualTo(DEFAULT_ANNOS);
        assertThat(testPersona.getMeses()).isEqualTo(DEFAULT_MESES);
        assertThat(testPersona.getDias()).isEqualTo(DEFAULT_DIAS);
        assertThat(testPersona.getRealizocomputodeservicios()).isEqualTo(DEFAULT_REALIZOCOMPUTODESERVICIOS);
        assertThat(testPersona.getPoseeconocimientoenmaquinasviales()).isEqualTo(DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES);
        assertThat(testPersona.getCasoemergenciacelular()).isEqualTo(DEFAULT_CASOEMERGENCIACELULAR);
        assertThat(testPersona.getCasoemergenciafijo()).isEqualTo(DEFAULT_CASOEMERGENCIAFIJO);
        assertThat(testPersona.getCasoemergencianombre()).isEqualTo(DEFAULT_CASOEMERGENCIANOMBRE);
        assertThat(testPersona.getCasoemergenciacelular2()).isEqualTo(DEFAULT_CASOEMERGENCIACELULAR_2);
        assertThat(testPersona.getCasoemergenciafijo2()).isEqualTo(DEFAULT_CASOEMERGENCIAFIJO_2);
        assertThat(testPersona.getCasoemergencianombre2()).isEqualTo(DEFAULT_CASOEMERGENCIANOMBRE_2);
        assertThat(testPersona.getFamiliaracargonombre()).isEqualTo(DEFAULT_FAMILIARACARGONOMBRE);
        assertThat(testPersona.getFamiliaracargonombre2()).isEqualTo(DEFAULT_FAMILIARACARGONOMBRE_2);
        assertThat(testPersona.getFamiliaracargonombre3()).isEqualTo(DEFAULT_FAMILIARACARGONOMBRE_3);
        assertThat(testPersona.getFamiliaracargonombre4()).isEqualTo(DEFAULT_FAMILIARACARGONOMBRE_4);
        assertThat(testPersona.getFamiliaracargonombre5()).isEqualTo(DEFAULT_FAMILIARACARGONOMBRE_5);
        assertThat(testPersona.getFamiliaracargodni()).isEqualTo(DEFAULT_FAMILIARACARGODNI);
        assertThat(testPersona.getFamiliaracargodni2()).isEqualTo(DEFAULT_FAMILIARACARGODNI_2);
        assertThat(testPersona.getFamiliaracargodni3()).isEqualTo(DEFAULT_FAMILIARACARGODNI_3);
        assertThat(testPersona.getFamiliaracargodni4()).isEqualTo(DEFAULT_FAMILIARACARGODNI_4);
        assertThat(testPersona.getFamiliaracargodni5()).isEqualTo(DEFAULT_FAMILIARACARGODNI_5);
        assertThat(testPersona.getFamiliaracargoedad()).isEqualTo(DEFAULT_FAMILIARACARGOEDAD);
        assertThat(testPersona.getFamiliaracargoedad2()).isEqualTo(DEFAULT_FAMILIARACARGOEDAD_2);
        assertThat(testPersona.getFamiliaracargoedad3()).isEqualTo(DEFAULT_FAMILIARACARGOEDAD_3);
        assertThat(testPersona.getFamiliaracargoedad4()).isEqualTo(DEFAULT_FAMILIARACARGOEDAD_4);
        assertThat(testPersona.getFamiliaracargoedad5()).isEqualTo(DEFAULT_FAMILIARACARGOEDAD_5);
        assertThat(testPersona.getAltura()).isEqualTo(DEFAULT_ALTURA);
        assertThat(testPersona.getBarrio()).isEqualTo(DEFAULT_BARRIO);
        assertThat(testPersona.getEstudiosincompletos()).isEqualTo(DEFAULT_ESTUDIOSINCOMPLETOS);
        assertThat(testPersona.getConyugeapellido()).isEqualTo(DEFAULT_CONYUGEAPELLIDO);
        assertThat(testPersona.getConyugenombre()).isEqualTo(DEFAULT_CONYUGENOMBRE);
        assertThat(testPersona.getConyugedni()).isEqualTo(DEFAULT_CONYUGEDNI);
        assertThat(testPersona.getConyugecuil()).isEqualTo(DEFAULT_CONYUGECUIL);
        assertThat(testPersona.getGrupofamiliarapellidonombre()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE);
        assertThat(testPersona.getGrupofamiliarapellidonombre2()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2);
        assertThat(testPersona.getGrupofamiliarapellidonombre3()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3);
        assertThat(testPersona.getGrupofamiliarapellidonombre4()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4);
        assertThat(testPersona.getGrupofamiliarapellidonombre5()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5);
        assertThat(testPersona.getGrupofamiliarapellidonombre6()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6);
        assertThat(testPersona.getGrupofamiliarapellidonombre7()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7);
        assertThat(testPersona.getGrupofamiliarapellidonombre8()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8);
        assertThat(testPersona.getGrupofamiliarapellidonombre9()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9);
        assertThat(testPersona.getGrupofamiliarapellidonombre10()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10);
        assertThat(testPersona.getGrupofamiliarapellidonombre11()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad2()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad3()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad4()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad5()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad6()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad7()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad8()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad9()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad10()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad11()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);
        assertThat(testPersona.getGrupofamiliarapellidonombredni()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI);
        assertThat(testPersona.getGrupofamiliarapellidonombredni2()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
        assertThat(testPersona.getGrupofamiliarapellidonombredni3()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
        assertThat(testPersona.getGrupofamiliarapellidonombredni4()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
        assertThat(testPersona.getGrupofamiliarapellidonombredni5()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
        assertThat(testPersona.getGrupofamiliarapellidonombredni6()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
        assertThat(testPersona.getGrupofamiliarapellidonombredni7()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
        assertThat(testPersona.getGrupofamiliarapellidonombredni8()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
        assertThat(testPersona.getGrupofamiliarapellidonombredni9()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
        assertThat(testPersona.getGrupofamiliarapellidonombredni10()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
        assertThat(testPersona.getGrupofamiliarapellidonombredni11()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar2()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar4()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar3()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar5()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar6()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar7()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar8()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar9()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar10()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar11()).isEqualTo(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
    }

    @Test
    @Transactional
    public void createPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();

        // Create the Persona with an existing ID
        persona.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setNombre(null);

        // Create the Persona, which fails.

        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApellidoIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setApellido(null);

        // Create the Persona, which fails.

        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCuilIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setCuil(null);

        // Create the Persona, which fails.

        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDniIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setDni(null);

        // Create the Persona, which fails.

        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLegajoIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setLegajo(null);

        // Create the Persona, which fails.

        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonas() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persona.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].cuil").value(hasItem(DEFAULT_CUIL)))
            .andExpect(jsonPath("$.[*].dni").value(hasItem(DEFAULT_DNI)))
            .andExpect(jsonPath("$.[*].legajo").value(hasItem(DEFAULT_LEGAJO)))
            .andExpect(jsonPath("$.[*].apodo").value(hasItem(DEFAULT_APODO)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].soltero").value(hasItem(DEFAULT_SOLTERO)))
            .andExpect(jsonPath("$.[*].casado").value(hasItem(DEFAULT_CASADO)))
            .andExpect(jsonPath("$.[*].conviviente").value(hasItem(DEFAULT_CONVIVIENTE)))
            .andExpect(jsonPath("$.[*].viudo").value(hasItem(DEFAULT_VIUDO)))
            .andExpect(jsonPath("$.[*].domicilio").value(hasItem(DEFAULT_DOMICILIO)))
            .andExpect(jsonPath("$.[*].lugar").value(hasItem(DEFAULT_LUGAR)))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].telefonofijo").value(hasItem(DEFAULT_TELEFONOFIJO)))
            .andExpect(jsonPath("$.[*].numerodecelular").value(hasItem(DEFAULT_NUMERODECELULAR)))
            .andExpect(jsonPath("$.[*].oficioprofecion").value(hasItem(DEFAULT_OFICIOPROFECION)))
            .andExpect(jsonPath("$.[*].niveldeestudios").value(hasItem(DEFAULT_NIVELDEESTUDIOS)))
            .andExpect(jsonPath("$.[*].gruposanguineo").value(hasItem(DEFAULT_GRUPOSANGUINEO)))
            .andExpect(jsonPath("$.[*].factor").value(hasItem(DEFAULT_FACTOR)))
            .andExpect(jsonPath("$.[*].donante").value(hasItem(DEFAULT_DONANTE)))
            .andExpect(jsonPath("$.[*].diabetes").value(hasItem(DEFAULT_DIABETES)))
            .andExpect(jsonPath("$.[*].hipertension").value(hasItem(DEFAULT_HIPERTENSION)))
            .andExpect(jsonPath("$.[*].alergias").value(hasItem(DEFAULT_ALERGIAS)))
            .andExpect(jsonPath("$.[*].asma").value(hasItem(DEFAULT_ASMA)))
            .andExpect(jsonPath("$.[*].otros").value(hasItem(DEFAULT_OTROS)))
            .andExpect(jsonPath("$.[*].fechadeingreso").value(hasItem(DEFAULT_FECHADEINGRESO.toString())))
            .andExpect(jsonPath("$.[*].instrumentolegal").value(hasItem(DEFAULT_INSTRUMENTOLEGAL)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].planta").value(hasItem(DEFAULT_PLANTA)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].annos").value(hasItem(DEFAULT_ANNOS)))
            .andExpect(jsonPath("$.[*].meses").value(hasItem(DEFAULT_MESES)))
            .andExpect(jsonPath("$.[*].dias").value(hasItem(DEFAULT_DIAS)))
            .andExpect(jsonPath("$.[*].realizocomputodeservicios").value(hasItem(DEFAULT_REALIZOCOMPUTODESERVICIOS)))
            .andExpect(jsonPath("$.[*].poseeconocimientoenmaquinasviales").value(hasItem(DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES)))
            .andExpect(jsonPath("$.[*].casoemergenciacelular").value(hasItem(DEFAULT_CASOEMERGENCIACELULAR)))
            .andExpect(jsonPath("$.[*].casoemergenciafijo").value(hasItem(DEFAULT_CASOEMERGENCIAFIJO)))
            .andExpect(jsonPath("$.[*].casoemergencianombre").value(hasItem(DEFAULT_CASOEMERGENCIANOMBRE)))
            .andExpect(jsonPath("$.[*].casoemergenciacelular2").value(hasItem(DEFAULT_CASOEMERGENCIACELULAR_2)))
            .andExpect(jsonPath("$.[*].casoemergenciafijo2").value(hasItem(DEFAULT_CASOEMERGENCIAFIJO_2)))
            .andExpect(jsonPath("$.[*].casoemergencianombre2").value(hasItem(DEFAULT_CASOEMERGENCIANOMBRE_2)))
            .andExpect(jsonPath("$.[*].familiaracargonombre").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE)))
            .andExpect(jsonPath("$.[*].familiaracargonombre2").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_2)))
            .andExpect(jsonPath("$.[*].familiaracargonombre3").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_3)))
            .andExpect(jsonPath("$.[*].familiaracargonombre4").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_4)))
            .andExpect(jsonPath("$.[*].familiaracargonombre5").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_5)))
            .andExpect(jsonPath("$.[*].familiaracargodni").value(hasItem(DEFAULT_FAMILIARACARGODNI)))
            .andExpect(jsonPath("$.[*].familiaracargodni2").value(hasItem(DEFAULT_FAMILIARACARGODNI_2)))
            .andExpect(jsonPath("$.[*].familiaracargodni3").value(hasItem(DEFAULT_FAMILIARACARGODNI_3)))
            .andExpect(jsonPath("$.[*].familiaracargodni4").value(hasItem(DEFAULT_FAMILIARACARGODNI_4)))
            .andExpect(jsonPath("$.[*].familiaracargodni5").value(hasItem(DEFAULT_FAMILIARACARGODNI_5)))
            .andExpect(jsonPath("$.[*].familiaracargoedad").value(hasItem(DEFAULT_FAMILIARACARGOEDAD)))
            .andExpect(jsonPath("$.[*].familiaracargoedad2").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_2)))
            .andExpect(jsonPath("$.[*].familiaracargoedad3").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_3)))
            .andExpect(jsonPath("$.[*].familiaracargoedad4").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_4)))
            .andExpect(jsonPath("$.[*].familiaracargoedad5").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_5)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA)))
            .andExpect(jsonPath("$.[*].barrio").value(hasItem(DEFAULT_BARRIO)))
            .andExpect(jsonPath("$.[*].estudiosincompletos").value(hasItem(DEFAULT_ESTUDIOSINCOMPLETOS)))
            .andExpect(jsonPath("$.[*].conyugeapellido").value(hasItem(DEFAULT_CONYUGEAPELLIDO)))
            .andExpect(jsonPath("$.[*].conyugenombre").value(hasItem(DEFAULT_CONYUGENOMBRE)))
            .andExpect(jsonPath("$.[*].conyugedni").value(hasItem(DEFAULT_CONYUGEDNI)))
            .andExpect(jsonPath("$.[*].conyugecuil").value(hasItem(DEFAULT_CONYUGECUIL)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11)));
    }
    
    @Test
    @Transactional
    public void getPersona() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", persona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(persona.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO))
            .andExpect(jsonPath("$.cuil").value(DEFAULT_CUIL))
            .andExpect(jsonPath("$.dni").value(DEFAULT_DNI))
            .andExpect(jsonPath("$.legajo").value(DEFAULT_LEGAJO))
            .andExpect(jsonPath("$.apodo").value(DEFAULT_APODO))
            .andExpect(jsonPath("$.fotoContentType").value(DEFAULT_FOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.foto").value(Base64Utils.encodeToString(DEFAULT_FOTO)))
            .andExpect(jsonPath("$.soltero").value(DEFAULT_SOLTERO))
            .andExpect(jsonPath("$.casado").value(DEFAULT_CASADO))
            .andExpect(jsonPath("$.conviviente").value(DEFAULT_CONVIVIENTE))
            .andExpect(jsonPath("$.viudo").value(DEFAULT_VIUDO))
            .andExpect(jsonPath("$.domicilio").value(DEFAULT_DOMICILIO))
            .andExpect(jsonPath("$.lugar").value(DEFAULT_LUGAR))
            .andExpect(jsonPath("$.calle").value(DEFAULT_CALLE))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.telefonofijo").value(DEFAULT_TELEFONOFIJO))
            .andExpect(jsonPath("$.numerodecelular").value(DEFAULT_NUMERODECELULAR))
            .andExpect(jsonPath("$.oficioprofecion").value(DEFAULT_OFICIOPROFECION))
            .andExpect(jsonPath("$.niveldeestudios").value(DEFAULT_NIVELDEESTUDIOS))
            .andExpect(jsonPath("$.gruposanguineo").value(DEFAULT_GRUPOSANGUINEO))
            .andExpect(jsonPath("$.factor").value(DEFAULT_FACTOR))
            .andExpect(jsonPath("$.donante").value(DEFAULT_DONANTE))
            .andExpect(jsonPath("$.diabetes").value(DEFAULT_DIABETES))
            .andExpect(jsonPath("$.hipertension").value(DEFAULT_HIPERTENSION))
            .andExpect(jsonPath("$.alergias").value(DEFAULT_ALERGIAS))
            .andExpect(jsonPath("$.asma").value(DEFAULT_ASMA))
            .andExpect(jsonPath("$.otros").value(DEFAULT_OTROS))
            .andExpect(jsonPath("$.fechadeingreso").value(DEFAULT_FECHADEINGRESO.toString()))
            .andExpect(jsonPath("$.instrumentolegal").value(DEFAULT_INSTRUMENTOLEGAL))
            .andExpect(jsonPath("$.categoria").value(DEFAULT_CATEGORIA))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.planta").value(DEFAULT_PLANTA))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.annos").value(DEFAULT_ANNOS))
            .andExpect(jsonPath("$.meses").value(DEFAULT_MESES))
            .andExpect(jsonPath("$.dias").value(DEFAULT_DIAS))
            .andExpect(jsonPath("$.realizocomputodeservicios").value(DEFAULT_REALIZOCOMPUTODESERVICIOS))
            .andExpect(jsonPath("$.poseeconocimientoenmaquinasviales").value(DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES))
            .andExpect(jsonPath("$.casoemergenciacelular").value(DEFAULT_CASOEMERGENCIACELULAR))
            .andExpect(jsonPath("$.casoemergenciafijo").value(DEFAULT_CASOEMERGENCIAFIJO))
            .andExpect(jsonPath("$.casoemergencianombre").value(DEFAULT_CASOEMERGENCIANOMBRE))
            .andExpect(jsonPath("$.casoemergenciacelular2").value(DEFAULT_CASOEMERGENCIACELULAR_2))
            .andExpect(jsonPath("$.casoemergenciafijo2").value(DEFAULT_CASOEMERGENCIAFIJO_2))
            .andExpect(jsonPath("$.casoemergencianombre2").value(DEFAULT_CASOEMERGENCIANOMBRE_2))
            .andExpect(jsonPath("$.familiaracargonombre").value(DEFAULT_FAMILIARACARGONOMBRE))
            .andExpect(jsonPath("$.familiaracargonombre2").value(DEFAULT_FAMILIARACARGONOMBRE_2))
            .andExpect(jsonPath("$.familiaracargonombre3").value(DEFAULT_FAMILIARACARGONOMBRE_3))
            .andExpect(jsonPath("$.familiaracargonombre4").value(DEFAULT_FAMILIARACARGONOMBRE_4))
            .andExpect(jsonPath("$.familiaracargonombre5").value(DEFAULT_FAMILIARACARGONOMBRE_5))
            .andExpect(jsonPath("$.familiaracargodni").value(DEFAULT_FAMILIARACARGODNI))
            .andExpect(jsonPath("$.familiaracargodni2").value(DEFAULT_FAMILIARACARGODNI_2))
            .andExpect(jsonPath("$.familiaracargodni3").value(DEFAULT_FAMILIARACARGODNI_3))
            .andExpect(jsonPath("$.familiaracargodni4").value(DEFAULT_FAMILIARACARGODNI_4))
            .andExpect(jsonPath("$.familiaracargodni5").value(DEFAULT_FAMILIARACARGODNI_5))
            .andExpect(jsonPath("$.familiaracargoedad").value(DEFAULT_FAMILIARACARGOEDAD))
            .andExpect(jsonPath("$.familiaracargoedad2").value(DEFAULT_FAMILIARACARGOEDAD_2))
            .andExpect(jsonPath("$.familiaracargoedad3").value(DEFAULT_FAMILIARACARGOEDAD_3))
            .andExpect(jsonPath("$.familiaracargoedad4").value(DEFAULT_FAMILIARACARGOEDAD_4))
            .andExpect(jsonPath("$.familiaracargoedad5").value(DEFAULT_FAMILIARACARGOEDAD_5))
            .andExpect(jsonPath("$.altura").value(DEFAULT_ALTURA))
            .andExpect(jsonPath("$.barrio").value(DEFAULT_BARRIO))
            .andExpect(jsonPath("$.estudiosincompletos").value(DEFAULT_ESTUDIOSINCOMPLETOS))
            .andExpect(jsonPath("$.conyugeapellido").value(DEFAULT_CONYUGEAPELLIDO))
            .andExpect(jsonPath("$.conyugenombre").value(DEFAULT_CONYUGENOMBRE))
            .andExpect(jsonPath("$.conyugedni").value(DEFAULT_CONYUGEDNI))
            .andExpect(jsonPath("$.conyugecuil").value(DEFAULT_CONYUGECUIL))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre2").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre3").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre4").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre5").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre6").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre7").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre8").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre9").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre10").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10))
            .andExpect(jsonPath("$.grupofamiliarapellidonombre11").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad2").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad3").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad4").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad5").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad6").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad7").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad8").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad9").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad10").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10))
            .andExpect(jsonPath("$.grupofamiliarapellidonombreedad11").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni2").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni3").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni4").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni5").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni6").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni7").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni8").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni9").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni10").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10))
            .andExpect(jsonPath("$.grupofamiliarapellidonombredni11").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar2").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar4").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar3").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar5").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar6").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar7").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar8").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar9").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar10").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10))
            .andExpect(jsonPath("$.grupofamiliarapellidonombrefamiliar11").value(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11));
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre equals to DEFAULT_NOMBRE
        defaultPersonaShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre equals to UPDATED_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre not equals to DEFAULT_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre not equals to UPDATED_NOMBRE
        defaultPersonaShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultPersonaShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the personaList where nombre equals to UPDATED_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre is not null
        defaultPersonaShouldBeFound("nombre.specified=true");

        // Get all the personaList where nombre is null
        defaultPersonaShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByNombreContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre contains DEFAULT_NOMBRE
        defaultPersonaShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre contains UPDATED_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre does not contain DEFAULT_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre does not contain UPDATED_NOMBRE
        defaultPersonaShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }


    @Test
    @Transactional
    public void getAllPersonasByApellidoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellido equals to DEFAULT_APELLIDO
        defaultPersonaShouldBeFound("apellido.equals=" + DEFAULT_APELLIDO);

        // Get all the personaList where apellido equals to UPDATED_APELLIDO
        defaultPersonaShouldNotBeFound("apellido.equals=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellido not equals to DEFAULT_APELLIDO
        defaultPersonaShouldNotBeFound("apellido.notEquals=" + DEFAULT_APELLIDO);

        // Get all the personaList where apellido not equals to UPDATED_APELLIDO
        defaultPersonaShouldBeFound("apellido.notEquals=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellido in DEFAULT_APELLIDO or UPDATED_APELLIDO
        defaultPersonaShouldBeFound("apellido.in=" + DEFAULT_APELLIDO + "," + UPDATED_APELLIDO);

        // Get all the personaList where apellido equals to UPDATED_APELLIDO
        defaultPersonaShouldNotBeFound("apellido.in=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellido is not null
        defaultPersonaShouldBeFound("apellido.specified=true");

        // Get all the personaList where apellido is null
        defaultPersonaShouldNotBeFound("apellido.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByApellidoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellido contains DEFAULT_APELLIDO
        defaultPersonaShouldBeFound("apellido.contains=" + DEFAULT_APELLIDO);

        // Get all the personaList where apellido contains UPDATED_APELLIDO
        defaultPersonaShouldNotBeFound("apellido.contains=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellido does not contain DEFAULT_APELLIDO
        defaultPersonaShouldNotBeFound("apellido.doesNotContain=" + DEFAULT_APELLIDO);

        // Get all the personaList where apellido does not contain UPDATED_APELLIDO
        defaultPersonaShouldBeFound("apellido.doesNotContain=" + UPDATED_APELLIDO);
    }


    @Test
    @Transactional
    public void getAllPersonasByCuilIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where cuil equals to DEFAULT_CUIL
        defaultPersonaShouldBeFound("cuil.equals=" + DEFAULT_CUIL);

        // Get all the personaList where cuil equals to UPDATED_CUIL
        defaultPersonaShouldNotBeFound("cuil.equals=" + UPDATED_CUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByCuilIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where cuil not equals to DEFAULT_CUIL
        defaultPersonaShouldNotBeFound("cuil.notEquals=" + DEFAULT_CUIL);

        // Get all the personaList where cuil not equals to UPDATED_CUIL
        defaultPersonaShouldBeFound("cuil.notEquals=" + UPDATED_CUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByCuilIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where cuil in DEFAULT_CUIL or UPDATED_CUIL
        defaultPersonaShouldBeFound("cuil.in=" + DEFAULT_CUIL + "," + UPDATED_CUIL);

        // Get all the personaList where cuil equals to UPDATED_CUIL
        defaultPersonaShouldNotBeFound("cuil.in=" + UPDATED_CUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByCuilIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where cuil is not null
        defaultPersonaShouldBeFound("cuil.specified=true");

        // Get all the personaList where cuil is null
        defaultPersonaShouldNotBeFound("cuil.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCuilContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where cuil contains DEFAULT_CUIL
        defaultPersonaShouldBeFound("cuil.contains=" + DEFAULT_CUIL);

        // Get all the personaList where cuil contains UPDATED_CUIL
        defaultPersonaShouldNotBeFound("cuil.contains=" + UPDATED_CUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByCuilNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where cuil does not contain DEFAULT_CUIL
        defaultPersonaShouldNotBeFound("cuil.doesNotContain=" + DEFAULT_CUIL);

        // Get all the personaList where cuil does not contain UPDATED_CUIL
        defaultPersonaShouldBeFound("cuil.doesNotContain=" + UPDATED_CUIL);
    }


    @Test
    @Transactional
    public void getAllPersonasByDniIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni equals to DEFAULT_DNI
        defaultPersonaShouldBeFound("dni.equals=" + DEFAULT_DNI);

        // Get all the personaList where dni equals to UPDATED_DNI
        defaultPersonaShouldNotBeFound("dni.equals=" + UPDATED_DNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByDniIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni not equals to DEFAULT_DNI
        defaultPersonaShouldNotBeFound("dni.notEquals=" + DEFAULT_DNI);

        // Get all the personaList where dni not equals to UPDATED_DNI
        defaultPersonaShouldBeFound("dni.notEquals=" + UPDATED_DNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByDniIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni in DEFAULT_DNI or UPDATED_DNI
        defaultPersonaShouldBeFound("dni.in=" + DEFAULT_DNI + "," + UPDATED_DNI);

        // Get all the personaList where dni equals to UPDATED_DNI
        defaultPersonaShouldNotBeFound("dni.in=" + UPDATED_DNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByDniIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni is not null
        defaultPersonaShouldBeFound("dni.specified=true");

        // Get all the personaList where dni is null
        defaultPersonaShouldNotBeFound("dni.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByDniIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni is greater than or equal to DEFAULT_DNI
        defaultPersonaShouldBeFound("dni.greaterThanOrEqual=" + DEFAULT_DNI);

        // Get all the personaList where dni is greater than or equal to UPDATED_DNI
        defaultPersonaShouldNotBeFound("dni.greaterThanOrEqual=" + UPDATED_DNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByDniIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni is less than or equal to DEFAULT_DNI
        defaultPersonaShouldBeFound("dni.lessThanOrEqual=" + DEFAULT_DNI);

        // Get all the personaList where dni is less than or equal to SMALLER_DNI
        defaultPersonaShouldNotBeFound("dni.lessThanOrEqual=" + SMALLER_DNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByDniIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni is less than DEFAULT_DNI
        defaultPersonaShouldNotBeFound("dni.lessThan=" + DEFAULT_DNI);

        // Get all the personaList where dni is less than UPDATED_DNI
        defaultPersonaShouldBeFound("dni.lessThan=" + UPDATED_DNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByDniIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dni is greater than DEFAULT_DNI
        defaultPersonaShouldNotBeFound("dni.greaterThan=" + DEFAULT_DNI);

        // Get all the personaList where dni is greater than SMALLER_DNI
        defaultPersonaShouldBeFound("dni.greaterThan=" + SMALLER_DNI);
    }


    @Test
    @Transactional
    public void getAllPersonasByLegajoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo equals to DEFAULT_LEGAJO
        defaultPersonaShouldBeFound("legajo.equals=" + DEFAULT_LEGAJO);

        // Get all the personaList where legajo equals to UPDATED_LEGAJO
        defaultPersonaShouldNotBeFound("legajo.equals=" + UPDATED_LEGAJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByLegajoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo not equals to DEFAULT_LEGAJO
        defaultPersonaShouldNotBeFound("legajo.notEquals=" + DEFAULT_LEGAJO);

        // Get all the personaList where legajo not equals to UPDATED_LEGAJO
        defaultPersonaShouldBeFound("legajo.notEquals=" + UPDATED_LEGAJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByLegajoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo in DEFAULT_LEGAJO or UPDATED_LEGAJO
        defaultPersonaShouldBeFound("legajo.in=" + DEFAULT_LEGAJO + "," + UPDATED_LEGAJO);

        // Get all the personaList where legajo equals to UPDATED_LEGAJO
        defaultPersonaShouldNotBeFound("legajo.in=" + UPDATED_LEGAJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByLegajoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo is not null
        defaultPersonaShouldBeFound("legajo.specified=true");

        // Get all the personaList where legajo is null
        defaultPersonaShouldNotBeFound("legajo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByLegajoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo is greater than or equal to DEFAULT_LEGAJO
        defaultPersonaShouldBeFound("legajo.greaterThanOrEqual=" + DEFAULT_LEGAJO);

        // Get all the personaList where legajo is greater than or equal to UPDATED_LEGAJO
        defaultPersonaShouldNotBeFound("legajo.greaterThanOrEqual=" + UPDATED_LEGAJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByLegajoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo is less than or equal to DEFAULT_LEGAJO
        defaultPersonaShouldBeFound("legajo.lessThanOrEqual=" + DEFAULT_LEGAJO);

        // Get all the personaList where legajo is less than or equal to SMALLER_LEGAJO
        defaultPersonaShouldNotBeFound("legajo.lessThanOrEqual=" + SMALLER_LEGAJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByLegajoIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo is less than DEFAULT_LEGAJO
        defaultPersonaShouldNotBeFound("legajo.lessThan=" + DEFAULT_LEGAJO);

        // Get all the personaList where legajo is less than UPDATED_LEGAJO
        defaultPersonaShouldBeFound("legajo.lessThan=" + UPDATED_LEGAJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByLegajoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where legajo is greater than DEFAULT_LEGAJO
        defaultPersonaShouldNotBeFound("legajo.greaterThan=" + DEFAULT_LEGAJO);

        // Get all the personaList where legajo is greater than SMALLER_LEGAJO
        defaultPersonaShouldBeFound("legajo.greaterThan=" + SMALLER_LEGAJO);
    }


    @Test
    @Transactional
    public void getAllPersonasByApodoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apodo equals to DEFAULT_APODO
        defaultPersonaShouldBeFound("apodo.equals=" + DEFAULT_APODO);

        // Get all the personaList where apodo equals to UPDATED_APODO
        defaultPersonaShouldNotBeFound("apodo.equals=" + UPDATED_APODO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApodoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apodo not equals to DEFAULT_APODO
        defaultPersonaShouldNotBeFound("apodo.notEquals=" + DEFAULT_APODO);

        // Get all the personaList where apodo not equals to UPDATED_APODO
        defaultPersonaShouldBeFound("apodo.notEquals=" + UPDATED_APODO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApodoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apodo in DEFAULT_APODO or UPDATED_APODO
        defaultPersonaShouldBeFound("apodo.in=" + DEFAULT_APODO + "," + UPDATED_APODO);

        // Get all the personaList where apodo equals to UPDATED_APODO
        defaultPersonaShouldNotBeFound("apodo.in=" + UPDATED_APODO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApodoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apodo is not null
        defaultPersonaShouldBeFound("apodo.specified=true");

        // Get all the personaList where apodo is null
        defaultPersonaShouldNotBeFound("apodo.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByApodoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apodo contains DEFAULT_APODO
        defaultPersonaShouldBeFound("apodo.contains=" + DEFAULT_APODO);

        // Get all the personaList where apodo contains UPDATED_APODO
        defaultPersonaShouldNotBeFound("apodo.contains=" + UPDATED_APODO);
    }

    @Test
    @Transactional
    public void getAllPersonasByApodoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apodo does not contain DEFAULT_APODO
        defaultPersonaShouldNotBeFound("apodo.doesNotContain=" + DEFAULT_APODO);

        // Get all the personaList where apodo does not contain UPDATED_APODO
        defaultPersonaShouldBeFound("apodo.doesNotContain=" + UPDATED_APODO);
    }


    @Test
    @Transactional
    public void getAllPersonasBySolteroIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where soltero equals to DEFAULT_SOLTERO
        defaultPersonaShouldBeFound("soltero.equals=" + DEFAULT_SOLTERO);

        // Get all the personaList where soltero equals to UPDATED_SOLTERO
        defaultPersonaShouldNotBeFound("soltero.equals=" + UPDATED_SOLTERO);
    }

    @Test
    @Transactional
    public void getAllPersonasBySolteroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where soltero not equals to DEFAULT_SOLTERO
        defaultPersonaShouldNotBeFound("soltero.notEquals=" + DEFAULT_SOLTERO);

        // Get all the personaList where soltero not equals to UPDATED_SOLTERO
        defaultPersonaShouldBeFound("soltero.notEquals=" + UPDATED_SOLTERO);
    }

    @Test
    @Transactional
    public void getAllPersonasBySolteroIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where soltero in DEFAULT_SOLTERO or UPDATED_SOLTERO
        defaultPersonaShouldBeFound("soltero.in=" + DEFAULT_SOLTERO + "," + UPDATED_SOLTERO);

        // Get all the personaList where soltero equals to UPDATED_SOLTERO
        defaultPersonaShouldNotBeFound("soltero.in=" + UPDATED_SOLTERO);
    }

    @Test
    @Transactional
    public void getAllPersonasBySolteroIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where soltero is not null
        defaultPersonaShouldBeFound("soltero.specified=true");

        // Get all the personaList where soltero is null
        defaultPersonaShouldNotBeFound("soltero.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasBySolteroContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where soltero contains DEFAULT_SOLTERO
        defaultPersonaShouldBeFound("soltero.contains=" + DEFAULT_SOLTERO);

        // Get all the personaList where soltero contains UPDATED_SOLTERO
        defaultPersonaShouldNotBeFound("soltero.contains=" + UPDATED_SOLTERO);
    }

    @Test
    @Transactional
    public void getAllPersonasBySolteroNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where soltero does not contain DEFAULT_SOLTERO
        defaultPersonaShouldNotBeFound("soltero.doesNotContain=" + DEFAULT_SOLTERO);

        // Get all the personaList where soltero does not contain UPDATED_SOLTERO
        defaultPersonaShouldBeFound("soltero.doesNotContain=" + UPDATED_SOLTERO);
    }


    @Test
    @Transactional
    public void getAllPersonasByCasadoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casado equals to DEFAULT_CASADO
        defaultPersonaShouldBeFound("casado.equals=" + DEFAULT_CASADO);

        // Get all the personaList where casado equals to UPDATED_CASADO
        defaultPersonaShouldNotBeFound("casado.equals=" + UPDATED_CASADO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casado not equals to DEFAULT_CASADO
        defaultPersonaShouldNotBeFound("casado.notEquals=" + DEFAULT_CASADO);

        // Get all the personaList where casado not equals to UPDATED_CASADO
        defaultPersonaShouldBeFound("casado.notEquals=" + UPDATED_CASADO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasadoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casado in DEFAULT_CASADO or UPDATED_CASADO
        defaultPersonaShouldBeFound("casado.in=" + DEFAULT_CASADO + "," + UPDATED_CASADO);

        // Get all the personaList where casado equals to UPDATED_CASADO
        defaultPersonaShouldNotBeFound("casado.in=" + UPDATED_CASADO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casado is not null
        defaultPersonaShouldBeFound("casado.specified=true");

        // Get all the personaList where casado is null
        defaultPersonaShouldNotBeFound("casado.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCasadoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casado contains DEFAULT_CASADO
        defaultPersonaShouldBeFound("casado.contains=" + DEFAULT_CASADO);

        // Get all the personaList where casado contains UPDATED_CASADO
        defaultPersonaShouldNotBeFound("casado.contains=" + UPDATED_CASADO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasadoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casado does not contain DEFAULT_CASADO
        defaultPersonaShouldNotBeFound("casado.doesNotContain=" + DEFAULT_CASADO);

        // Get all the personaList where casado does not contain UPDATED_CASADO
        defaultPersonaShouldBeFound("casado.doesNotContain=" + UPDATED_CASADO);
    }


    @Test
    @Transactional
    public void getAllPersonasByConvivienteIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conviviente equals to DEFAULT_CONVIVIENTE
        defaultPersonaShouldBeFound("conviviente.equals=" + DEFAULT_CONVIVIENTE);

        // Get all the personaList where conviviente equals to UPDATED_CONVIVIENTE
        defaultPersonaShouldNotBeFound("conviviente.equals=" + UPDATED_CONVIVIENTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConvivienteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conviviente not equals to DEFAULT_CONVIVIENTE
        defaultPersonaShouldNotBeFound("conviviente.notEquals=" + DEFAULT_CONVIVIENTE);

        // Get all the personaList where conviviente not equals to UPDATED_CONVIVIENTE
        defaultPersonaShouldBeFound("conviviente.notEquals=" + UPDATED_CONVIVIENTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConvivienteIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conviviente in DEFAULT_CONVIVIENTE or UPDATED_CONVIVIENTE
        defaultPersonaShouldBeFound("conviviente.in=" + DEFAULT_CONVIVIENTE + "," + UPDATED_CONVIVIENTE);

        // Get all the personaList where conviviente equals to UPDATED_CONVIVIENTE
        defaultPersonaShouldNotBeFound("conviviente.in=" + UPDATED_CONVIVIENTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConvivienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conviviente is not null
        defaultPersonaShouldBeFound("conviviente.specified=true");

        // Get all the personaList where conviviente is null
        defaultPersonaShouldNotBeFound("conviviente.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByConvivienteContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conviviente contains DEFAULT_CONVIVIENTE
        defaultPersonaShouldBeFound("conviviente.contains=" + DEFAULT_CONVIVIENTE);

        // Get all the personaList where conviviente contains UPDATED_CONVIVIENTE
        defaultPersonaShouldNotBeFound("conviviente.contains=" + UPDATED_CONVIVIENTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConvivienteNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conviviente does not contain DEFAULT_CONVIVIENTE
        defaultPersonaShouldNotBeFound("conviviente.doesNotContain=" + DEFAULT_CONVIVIENTE);

        // Get all the personaList where conviviente does not contain UPDATED_CONVIVIENTE
        defaultPersonaShouldBeFound("conviviente.doesNotContain=" + UPDATED_CONVIVIENTE);
    }


    @Test
    @Transactional
    public void getAllPersonasByViudoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where viudo equals to DEFAULT_VIUDO
        defaultPersonaShouldBeFound("viudo.equals=" + DEFAULT_VIUDO);

        // Get all the personaList where viudo equals to UPDATED_VIUDO
        defaultPersonaShouldNotBeFound("viudo.equals=" + UPDATED_VIUDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByViudoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where viudo not equals to DEFAULT_VIUDO
        defaultPersonaShouldNotBeFound("viudo.notEquals=" + DEFAULT_VIUDO);

        // Get all the personaList where viudo not equals to UPDATED_VIUDO
        defaultPersonaShouldBeFound("viudo.notEquals=" + UPDATED_VIUDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByViudoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where viudo in DEFAULT_VIUDO or UPDATED_VIUDO
        defaultPersonaShouldBeFound("viudo.in=" + DEFAULT_VIUDO + "," + UPDATED_VIUDO);

        // Get all the personaList where viudo equals to UPDATED_VIUDO
        defaultPersonaShouldNotBeFound("viudo.in=" + UPDATED_VIUDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByViudoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where viudo is not null
        defaultPersonaShouldBeFound("viudo.specified=true");

        // Get all the personaList where viudo is null
        defaultPersonaShouldNotBeFound("viudo.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByViudoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where viudo contains DEFAULT_VIUDO
        defaultPersonaShouldBeFound("viudo.contains=" + DEFAULT_VIUDO);

        // Get all the personaList where viudo contains UPDATED_VIUDO
        defaultPersonaShouldNotBeFound("viudo.contains=" + UPDATED_VIUDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByViudoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where viudo does not contain DEFAULT_VIUDO
        defaultPersonaShouldNotBeFound("viudo.doesNotContain=" + DEFAULT_VIUDO);

        // Get all the personaList where viudo does not contain UPDATED_VIUDO
        defaultPersonaShouldBeFound("viudo.doesNotContain=" + UPDATED_VIUDO);
    }


    @Test
    @Transactional
    public void getAllPersonasByDomicilioIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where domicilio equals to DEFAULT_DOMICILIO
        defaultPersonaShouldBeFound("domicilio.equals=" + DEFAULT_DOMICILIO);

        // Get all the personaList where domicilio equals to UPDATED_DOMICILIO
        defaultPersonaShouldNotBeFound("domicilio.equals=" + UPDATED_DOMICILIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByDomicilioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where domicilio not equals to DEFAULT_DOMICILIO
        defaultPersonaShouldNotBeFound("domicilio.notEquals=" + DEFAULT_DOMICILIO);

        // Get all the personaList where domicilio not equals to UPDATED_DOMICILIO
        defaultPersonaShouldBeFound("domicilio.notEquals=" + UPDATED_DOMICILIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByDomicilioIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where domicilio in DEFAULT_DOMICILIO or UPDATED_DOMICILIO
        defaultPersonaShouldBeFound("domicilio.in=" + DEFAULT_DOMICILIO + "," + UPDATED_DOMICILIO);

        // Get all the personaList where domicilio equals to UPDATED_DOMICILIO
        defaultPersonaShouldNotBeFound("domicilio.in=" + UPDATED_DOMICILIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByDomicilioIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where domicilio is not null
        defaultPersonaShouldBeFound("domicilio.specified=true");

        // Get all the personaList where domicilio is null
        defaultPersonaShouldNotBeFound("domicilio.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByDomicilioContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where domicilio contains DEFAULT_DOMICILIO
        defaultPersonaShouldBeFound("domicilio.contains=" + DEFAULT_DOMICILIO);

        // Get all the personaList where domicilio contains UPDATED_DOMICILIO
        defaultPersonaShouldNotBeFound("domicilio.contains=" + UPDATED_DOMICILIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByDomicilioNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where domicilio does not contain DEFAULT_DOMICILIO
        defaultPersonaShouldNotBeFound("domicilio.doesNotContain=" + DEFAULT_DOMICILIO);

        // Get all the personaList where domicilio does not contain UPDATED_DOMICILIO
        defaultPersonaShouldBeFound("domicilio.doesNotContain=" + UPDATED_DOMICILIO);
    }


    @Test
    @Transactional
    public void getAllPersonasByLugarIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where lugar equals to DEFAULT_LUGAR
        defaultPersonaShouldBeFound("lugar.equals=" + DEFAULT_LUGAR);

        // Get all the personaList where lugar equals to UPDATED_LUGAR
        defaultPersonaShouldNotBeFound("lugar.equals=" + UPDATED_LUGAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByLugarIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where lugar not equals to DEFAULT_LUGAR
        defaultPersonaShouldNotBeFound("lugar.notEquals=" + DEFAULT_LUGAR);

        // Get all the personaList where lugar not equals to UPDATED_LUGAR
        defaultPersonaShouldBeFound("lugar.notEquals=" + UPDATED_LUGAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByLugarIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where lugar in DEFAULT_LUGAR or UPDATED_LUGAR
        defaultPersonaShouldBeFound("lugar.in=" + DEFAULT_LUGAR + "," + UPDATED_LUGAR);

        // Get all the personaList where lugar equals to UPDATED_LUGAR
        defaultPersonaShouldNotBeFound("lugar.in=" + UPDATED_LUGAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByLugarIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where lugar is not null
        defaultPersonaShouldBeFound("lugar.specified=true");

        // Get all the personaList where lugar is null
        defaultPersonaShouldNotBeFound("lugar.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByLugarContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where lugar contains DEFAULT_LUGAR
        defaultPersonaShouldBeFound("lugar.contains=" + DEFAULT_LUGAR);

        // Get all the personaList where lugar contains UPDATED_LUGAR
        defaultPersonaShouldNotBeFound("lugar.contains=" + UPDATED_LUGAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByLugarNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where lugar does not contain DEFAULT_LUGAR
        defaultPersonaShouldNotBeFound("lugar.doesNotContain=" + DEFAULT_LUGAR);

        // Get all the personaList where lugar does not contain UPDATED_LUGAR
        defaultPersonaShouldBeFound("lugar.doesNotContain=" + UPDATED_LUGAR);
    }


    @Test
    @Transactional
    public void getAllPersonasByCalleIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where calle equals to DEFAULT_CALLE
        defaultPersonaShouldBeFound("calle.equals=" + DEFAULT_CALLE);

        // Get all the personaList where calle equals to UPDATED_CALLE
        defaultPersonaShouldNotBeFound("calle.equals=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCalleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where calle not equals to DEFAULT_CALLE
        defaultPersonaShouldNotBeFound("calle.notEquals=" + DEFAULT_CALLE);

        // Get all the personaList where calle not equals to UPDATED_CALLE
        defaultPersonaShouldBeFound("calle.notEquals=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCalleIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where calle in DEFAULT_CALLE or UPDATED_CALLE
        defaultPersonaShouldBeFound("calle.in=" + DEFAULT_CALLE + "," + UPDATED_CALLE);

        // Get all the personaList where calle equals to UPDATED_CALLE
        defaultPersonaShouldNotBeFound("calle.in=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCalleIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where calle is not null
        defaultPersonaShouldBeFound("calle.specified=true");

        // Get all the personaList where calle is null
        defaultPersonaShouldNotBeFound("calle.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCalleContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where calle contains DEFAULT_CALLE
        defaultPersonaShouldBeFound("calle.contains=" + DEFAULT_CALLE);

        // Get all the personaList where calle contains UPDATED_CALLE
        defaultPersonaShouldNotBeFound("calle.contains=" + UPDATED_CALLE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCalleNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where calle does not contain DEFAULT_CALLE
        defaultPersonaShouldNotBeFound("calle.doesNotContain=" + DEFAULT_CALLE);

        // Get all the personaList where calle does not contain UPDATED_CALLE
        defaultPersonaShouldBeFound("calle.doesNotContain=" + UPDATED_CALLE);
    }


    @Test
    @Transactional
    public void getAllPersonasByNumeroIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numero equals to DEFAULT_NUMERO
        defaultPersonaShouldBeFound("numero.equals=" + DEFAULT_NUMERO);

        // Get all the personaList where numero equals to UPDATED_NUMERO
        defaultPersonaShouldNotBeFound("numero.equals=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumeroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numero not equals to DEFAULT_NUMERO
        defaultPersonaShouldNotBeFound("numero.notEquals=" + DEFAULT_NUMERO);

        // Get all the personaList where numero not equals to UPDATED_NUMERO
        defaultPersonaShouldBeFound("numero.notEquals=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumeroIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numero in DEFAULT_NUMERO or UPDATED_NUMERO
        defaultPersonaShouldBeFound("numero.in=" + DEFAULT_NUMERO + "," + UPDATED_NUMERO);

        // Get all the personaList where numero equals to UPDATED_NUMERO
        defaultPersonaShouldNotBeFound("numero.in=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumeroIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numero is not null
        defaultPersonaShouldBeFound("numero.specified=true");

        // Get all the personaList where numero is null
        defaultPersonaShouldNotBeFound("numero.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByNumeroContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numero contains DEFAULT_NUMERO
        defaultPersonaShouldBeFound("numero.contains=" + DEFAULT_NUMERO);

        // Get all the personaList where numero contains UPDATED_NUMERO
        defaultPersonaShouldNotBeFound("numero.contains=" + UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumeroNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numero does not contain DEFAULT_NUMERO
        defaultPersonaShouldNotBeFound("numero.doesNotContain=" + DEFAULT_NUMERO);

        // Get all the personaList where numero does not contain UPDATED_NUMERO
        defaultPersonaShouldBeFound("numero.doesNotContain=" + UPDATED_NUMERO);
    }


    @Test
    @Transactional
    public void getAllPersonasByTelefonofijoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefonofijo equals to DEFAULT_TELEFONOFIJO
        defaultPersonaShouldBeFound("telefonofijo.equals=" + DEFAULT_TELEFONOFIJO);

        // Get all the personaList where telefonofijo equals to UPDATED_TELEFONOFIJO
        defaultPersonaShouldNotBeFound("telefonofijo.equals=" + UPDATED_TELEFONOFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonofijoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefonofijo not equals to DEFAULT_TELEFONOFIJO
        defaultPersonaShouldNotBeFound("telefonofijo.notEquals=" + DEFAULT_TELEFONOFIJO);

        // Get all the personaList where telefonofijo not equals to UPDATED_TELEFONOFIJO
        defaultPersonaShouldBeFound("telefonofijo.notEquals=" + UPDATED_TELEFONOFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonofijoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefonofijo in DEFAULT_TELEFONOFIJO or UPDATED_TELEFONOFIJO
        defaultPersonaShouldBeFound("telefonofijo.in=" + DEFAULT_TELEFONOFIJO + "," + UPDATED_TELEFONOFIJO);

        // Get all the personaList where telefonofijo equals to UPDATED_TELEFONOFIJO
        defaultPersonaShouldNotBeFound("telefonofijo.in=" + UPDATED_TELEFONOFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonofijoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefonofijo is not null
        defaultPersonaShouldBeFound("telefonofijo.specified=true");

        // Get all the personaList where telefonofijo is null
        defaultPersonaShouldNotBeFound("telefonofijo.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByTelefonofijoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefonofijo contains DEFAULT_TELEFONOFIJO
        defaultPersonaShouldBeFound("telefonofijo.contains=" + DEFAULT_TELEFONOFIJO);

        // Get all the personaList where telefonofijo contains UPDATED_TELEFONOFIJO
        defaultPersonaShouldNotBeFound("telefonofijo.contains=" + UPDATED_TELEFONOFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonofijoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefonofijo does not contain DEFAULT_TELEFONOFIJO
        defaultPersonaShouldNotBeFound("telefonofijo.doesNotContain=" + DEFAULT_TELEFONOFIJO);

        // Get all the personaList where telefonofijo does not contain UPDATED_TELEFONOFIJO
        defaultPersonaShouldBeFound("telefonofijo.doesNotContain=" + UPDATED_TELEFONOFIJO);
    }


    @Test
    @Transactional
    public void getAllPersonasByNumerodecelularIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numerodecelular equals to DEFAULT_NUMERODECELULAR
        defaultPersonaShouldBeFound("numerodecelular.equals=" + DEFAULT_NUMERODECELULAR);

        // Get all the personaList where numerodecelular equals to UPDATED_NUMERODECELULAR
        defaultPersonaShouldNotBeFound("numerodecelular.equals=" + UPDATED_NUMERODECELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumerodecelularIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numerodecelular not equals to DEFAULT_NUMERODECELULAR
        defaultPersonaShouldNotBeFound("numerodecelular.notEquals=" + DEFAULT_NUMERODECELULAR);

        // Get all the personaList where numerodecelular not equals to UPDATED_NUMERODECELULAR
        defaultPersonaShouldBeFound("numerodecelular.notEquals=" + UPDATED_NUMERODECELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumerodecelularIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numerodecelular in DEFAULT_NUMERODECELULAR or UPDATED_NUMERODECELULAR
        defaultPersonaShouldBeFound("numerodecelular.in=" + DEFAULT_NUMERODECELULAR + "," + UPDATED_NUMERODECELULAR);

        // Get all the personaList where numerodecelular equals to UPDATED_NUMERODECELULAR
        defaultPersonaShouldNotBeFound("numerodecelular.in=" + UPDATED_NUMERODECELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumerodecelularIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numerodecelular is not null
        defaultPersonaShouldBeFound("numerodecelular.specified=true");

        // Get all the personaList where numerodecelular is null
        defaultPersonaShouldNotBeFound("numerodecelular.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByNumerodecelularContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numerodecelular contains DEFAULT_NUMERODECELULAR
        defaultPersonaShouldBeFound("numerodecelular.contains=" + DEFAULT_NUMERODECELULAR);

        // Get all the personaList where numerodecelular contains UPDATED_NUMERODECELULAR
        defaultPersonaShouldNotBeFound("numerodecelular.contains=" + UPDATED_NUMERODECELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByNumerodecelularNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where numerodecelular does not contain DEFAULT_NUMERODECELULAR
        defaultPersonaShouldNotBeFound("numerodecelular.doesNotContain=" + DEFAULT_NUMERODECELULAR);

        // Get all the personaList where numerodecelular does not contain UPDATED_NUMERODECELULAR
        defaultPersonaShouldBeFound("numerodecelular.doesNotContain=" + UPDATED_NUMERODECELULAR);
    }


    @Test
    @Transactional
    public void getAllPersonasByOficioprofecionIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where oficioprofecion equals to DEFAULT_OFICIOPROFECION
        defaultPersonaShouldBeFound("oficioprofecion.equals=" + DEFAULT_OFICIOPROFECION);

        // Get all the personaList where oficioprofecion equals to UPDATED_OFICIOPROFECION
        defaultPersonaShouldNotBeFound("oficioprofecion.equals=" + UPDATED_OFICIOPROFECION);
    }

    @Test
    @Transactional
    public void getAllPersonasByOficioprofecionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where oficioprofecion not equals to DEFAULT_OFICIOPROFECION
        defaultPersonaShouldNotBeFound("oficioprofecion.notEquals=" + DEFAULT_OFICIOPROFECION);

        // Get all the personaList where oficioprofecion not equals to UPDATED_OFICIOPROFECION
        defaultPersonaShouldBeFound("oficioprofecion.notEquals=" + UPDATED_OFICIOPROFECION);
    }

    @Test
    @Transactional
    public void getAllPersonasByOficioprofecionIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where oficioprofecion in DEFAULT_OFICIOPROFECION or UPDATED_OFICIOPROFECION
        defaultPersonaShouldBeFound("oficioprofecion.in=" + DEFAULT_OFICIOPROFECION + "," + UPDATED_OFICIOPROFECION);

        // Get all the personaList where oficioprofecion equals to UPDATED_OFICIOPROFECION
        defaultPersonaShouldNotBeFound("oficioprofecion.in=" + UPDATED_OFICIOPROFECION);
    }

    @Test
    @Transactional
    public void getAllPersonasByOficioprofecionIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where oficioprofecion is not null
        defaultPersonaShouldBeFound("oficioprofecion.specified=true");

        // Get all the personaList where oficioprofecion is null
        defaultPersonaShouldNotBeFound("oficioprofecion.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByOficioprofecionContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where oficioprofecion contains DEFAULT_OFICIOPROFECION
        defaultPersonaShouldBeFound("oficioprofecion.contains=" + DEFAULT_OFICIOPROFECION);

        // Get all the personaList where oficioprofecion contains UPDATED_OFICIOPROFECION
        defaultPersonaShouldNotBeFound("oficioprofecion.contains=" + UPDATED_OFICIOPROFECION);
    }

    @Test
    @Transactional
    public void getAllPersonasByOficioprofecionNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where oficioprofecion does not contain DEFAULT_OFICIOPROFECION
        defaultPersonaShouldNotBeFound("oficioprofecion.doesNotContain=" + DEFAULT_OFICIOPROFECION);

        // Get all the personaList where oficioprofecion does not contain UPDATED_OFICIOPROFECION
        defaultPersonaShouldBeFound("oficioprofecion.doesNotContain=" + UPDATED_OFICIOPROFECION);
    }


    @Test
    @Transactional
    public void getAllPersonasByNiveldeestudiosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where niveldeestudios equals to DEFAULT_NIVELDEESTUDIOS
        defaultPersonaShouldBeFound("niveldeestudios.equals=" + DEFAULT_NIVELDEESTUDIOS);

        // Get all the personaList where niveldeestudios equals to UPDATED_NIVELDEESTUDIOS
        defaultPersonaShouldNotBeFound("niveldeestudios.equals=" + UPDATED_NIVELDEESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByNiveldeestudiosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where niveldeestudios not equals to DEFAULT_NIVELDEESTUDIOS
        defaultPersonaShouldNotBeFound("niveldeestudios.notEquals=" + DEFAULT_NIVELDEESTUDIOS);

        // Get all the personaList where niveldeestudios not equals to UPDATED_NIVELDEESTUDIOS
        defaultPersonaShouldBeFound("niveldeestudios.notEquals=" + UPDATED_NIVELDEESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByNiveldeestudiosIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where niveldeestudios in DEFAULT_NIVELDEESTUDIOS or UPDATED_NIVELDEESTUDIOS
        defaultPersonaShouldBeFound("niveldeestudios.in=" + DEFAULT_NIVELDEESTUDIOS + "," + UPDATED_NIVELDEESTUDIOS);

        // Get all the personaList where niveldeestudios equals to UPDATED_NIVELDEESTUDIOS
        defaultPersonaShouldNotBeFound("niveldeestudios.in=" + UPDATED_NIVELDEESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByNiveldeestudiosIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where niveldeestudios is not null
        defaultPersonaShouldBeFound("niveldeestudios.specified=true");

        // Get all the personaList where niveldeestudios is null
        defaultPersonaShouldNotBeFound("niveldeestudios.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByNiveldeestudiosContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where niveldeestudios contains DEFAULT_NIVELDEESTUDIOS
        defaultPersonaShouldBeFound("niveldeestudios.contains=" + DEFAULT_NIVELDEESTUDIOS);

        // Get all the personaList where niveldeestudios contains UPDATED_NIVELDEESTUDIOS
        defaultPersonaShouldNotBeFound("niveldeestudios.contains=" + UPDATED_NIVELDEESTUDIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByNiveldeestudiosNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where niveldeestudios does not contain DEFAULT_NIVELDEESTUDIOS
        defaultPersonaShouldNotBeFound("niveldeestudios.doesNotContain=" + DEFAULT_NIVELDEESTUDIOS);

        // Get all the personaList where niveldeestudios does not contain UPDATED_NIVELDEESTUDIOS
        defaultPersonaShouldBeFound("niveldeestudios.doesNotContain=" + UPDATED_NIVELDEESTUDIOS);
    }


    @Test
    @Transactional
    public void getAllPersonasByGruposanguineoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where gruposanguineo equals to DEFAULT_GRUPOSANGUINEO
        defaultPersonaShouldBeFound("gruposanguineo.equals=" + DEFAULT_GRUPOSANGUINEO);

        // Get all the personaList where gruposanguineo equals to UPDATED_GRUPOSANGUINEO
        defaultPersonaShouldNotBeFound("gruposanguineo.equals=" + UPDATED_GRUPOSANGUINEO);
    }

    @Test
    @Transactional
    public void getAllPersonasByGruposanguineoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where gruposanguineo not equals to DEFAULT_GRUPOSANGUINEO
        defaultPersonaShouldNotBeFound("gruposanguineo.notEquals=" + DEFAULT_GRUPOSANGUINEO);

        // Get all the personaList where gruposanguineo not equals to UPDATED_GRUPOSANGUINEO
        defaultPersonaShouldBeFound("gruposanguineo.notEquals=" + UPDATED_GRUPOSANGUINEO);
    }

    @Test
    @Transactional
    public void getAllPersonasByGruposanguineoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where gruposanguineo in DEFAULT_GRUPOSANGUINEO or UPDATED_GRUPOSANGUINEO
        defaultPersonaShouldBeFound("gruposanguineo.in=" + DEFAULT_GRUPOSANGUINEO + "," + UPDATED_GRUPOSANGUINEO);

        // Get all the personaList where gruposanguineo equals to UPDATED_GRUPOSANGUINEO
        defaultPersonaShouldNotBeFound("gruposanguineo.in=" + UPDATED_GRUPOSANGUINEO);
    }

    @Test
    @Transactional
    public void getAllPersonasByGruposanguineoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where gruposanguineo is not null
        defaultPersonaShouldBeFound("gruposanguineo.specified=true");

        // Get all the personaList where gruposanguineo is null
        defaultPersonaShouldNotBeFound("gruposanguineo.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGruposanguineoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where gruposanguineo contains DEFAULT_GRUPOSANGUINEO
        defaultPersonaShouldBeFound("gruposanguineo.contains=" + DEFAULT_GRUPOSANGUINEO);

        // Get all the personaList where gruposanguineo contains UPDATED_GRUPOSANGUINEO
        defaultPersonaShouldNotBeFound("gruposanguineo.contains=" + UPDATED_GRUPOSANGUINEO);
    }

    @Test
    @Transactional
    public void getAllPersonasByGruposanguineoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where gruposanguineo does not contain DEFAULT_GRUPOSANGUINEO
        defaultPersonaShouldNotBeFound("gruposanguineo.doesNotContain=" + DEFAULT_GRUPOSANGUINEO);

        // Get all the personaList where gruposanguineo does not contain UPDATED_GRUPOSANGUINEO
        defaultPersonaShouldBeFound("gruposanguineo.doesNotContain=" + UPDATED_GRUPOSANGUINEO);
    }


    @Test
    @Transactional
    public void getAllPersonasByFactorIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where factor equals to DEFAULT_FACTOR
        defaultPersonaShouldBeFound("factor.equals=" + DEFAULT_FACTOR);

        // Get all the personaList where factor equals to UPDATED_FACTOR
        defaultPersonaShouldNotBeFound("factor.equals=" + UPDATED_FACTOR);
    }

    @Test
    @Transactional
    public void getAllPersonasByFactorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where factor not equals to DEFAULT_FACTOR
        defaultPersonaShouldNotBeFound("factor.notEquals=" + DEFAULT_FACTOR);

        // Get all the personaList where factor not equals to UPDATED_FACTOR
        defaultPersonaShouldBeFound("factor.notEquals=" + UPDATED_FACTOR);
    }

    @Test
    @Transactional
    public void getAllPersonasByFactorIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where factor in DEFAULT_FACTOR or UPDATED_FACTOR
        defaultPersonaShouldBeFound("factor.in=" + DEFAULT_FACTOR + "," + UPDATED_FACTOR);

        // Get all the personaList where factor equals to UPDATED_FACTOR
        defaultPersonaShouldNotBeFound("factor.in=" + UPDATED_FACTOR);
    }

    @Test
    @Transactional
    public void getAllPersonasByFactorIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where factor is not null
        defaultPersonaShouldBeFound("factor.specified=true");

        // Get all the personaList where factor is null
        defaultPersonaShouldNotBeFound("factor.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFactorContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where factor contains DEFAULT_FACTOR
        defaultPersonaShouldBeFound("factor.contains=" + DEFAULT_FACTOR);

        // Get all the personaList where factor contains UPDATED_FACTOR
        defaultPersonaShouldNotBeFound("factor.contains=" + UPDATED_FACTOR);
    }

    @Test
    @Transactional
    public void getAllPersonasByFactorNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where factor does not contain DEFAULT_FACTOR
        defaultPersonaShouldNotBeFound("factor.doesNotContain=" + DEFAULT_FACTOR);

        // Get all the personaList where factor does not contain UPDATED_FACTOR
        defaultPersonaShouldBeFound("factor.doesNotContain=" + UPDATED_FACTOR);
    }


    @Test
    @Transactional
    public void getAllPersonasByDonanteIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where donante equals to DEFAULT_DONANTE
        defaultPersonaShouldBeFound("donante.equals=" + DEFAULT_DONANTE);

        // Get all the personaList where donante equals to UPDATED_DONANTE
        defaultPersonaShouldNotBeFound("donante.equals=" + UPDATED_DONANTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByDonanteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where donante not equals to DEFAULT_DONANTE
        defaultPersonaShouldNotBeFound("donante.notEquals=" + DEFAULT_DONANTE);

        // Get all the personaList where donante not equals to UPDATED_DONANTE
        defaultPersonaShouldBeFound("donante.notEquals=" + UPDATED_DONANTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByDonanteIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where donante in DEFAULT_DONANTE or UPDATED_DONANTE
        defaultPersonaShouldBeFound("donante.in=" + DEFAULT_DONANTE + "," + UPDATED_DONANTE);

        // Get all the personaList where donante equals to UPDATED_DONANTE
        defaultPersonaShouldNotBeFound("donante.in=" + UPDATED_DONANTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByDonanteIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where donante is not null
        defaultPersonaShouldBeFound("donante.specified=true");

        // Get all the personaList where donante is null
        defaultPersonaShouldNotBeFound("donante.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByDonanteContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where donante contains DEFAULT_DONANTE
        defaultPersonaShouldBeFound("donante.contains=" + DEFAULT_DONANTE);

        // Get all the personaList where donante contains UPDATED_DONANTE
        defaultPersonaShouldNotBeFound("donante.contains=" + UPDATED_DONANTE);
    }

    @Test
    @Transactional
    public void getAllPersonasByDonanteNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where donante does not contain DEFAULT_DONANTE
        defaultPersonaShouldNotBeFound("donante.doesNotContain=" + DEFAULT_DONANTE);

        // Get all the personaList where donante does not contain UPDATED_DONANTE
        defaultPersonaShouldBeFound("donante.doesNotContain=" + UPDATED_DONANTE);
    }


    @Test
    @Transactional
    public void getAllPersonasByDiabetesIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where diabetes equals to DEFAULT_DIABETES
        defaultPersonaShouldBeFound("diabetes.equals=" + DEFAULT_DIABETES);

        // Get all the personaList where diabetes equals to UPDATED_DIABETES
        defaultPersonaShouldNotBeFound("diabetes.equals=" + UPDATED_DIABETES);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiabetesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where diabetes not equals to DEFAULT_DIABETES
        defaultPersonaShouldNotBeFound("diabetes.notEquals=" + DEFAULT_DIABETES);

        // Get all the personaList where diabetes not equals to UPDATED_DIABETES
        defaultPersonaShouldBeFound("diabetes.notEquals=" + UPDATED_DIABETES);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiabetesIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where diabetes in DEFAULT_DIABETES or UPDATED_DIABETES
        defaultPersonaShouldBeFound("diabetes.in=" + DEFAULT_DIABETES + "," + UPDATED_DIABETES);

        // Get all the personaList where diabetes equals to UPDATED_DIABETES
        defaultPersonaShouldNotBeFound("diabetes.in=" + UPDATED_DIABETES);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiabetesIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where diabetes is not null
        defaultPersonaShouldBeFound("diabetes.specified=true");

        // Get all the personaList where diabetes is null
        defaultPersonaShouldNotBeFound("diabetes.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByDiabetesContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where diabetes contains DEFAULT_DIABETES
        defaultPersonaShouldBeFound("diabetes.contains=" + DEFAULT_DIABETES);

        // Get all the personaList where diabetes contains UPDATED_DIABETES
        defaultPersonaShouldNotBeFound("diabetes.contains=" + UPDATED_DIABETES);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiabetesNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where diabetes does not contain DEFAULT_DIABETES
        defaultPersonaShouldNotBeFound("diabetes.doesNotContain=" + DEFAULT_DIABETES);

        // Get all the personaList where diabetes does not contain UPDATED_DIABETES
        defaultPersonaShouldBeFound("diabetes.doesNotContain=" + UPDATED_DIABETES);
    }


    @Test
    @Transactional
    public void getAllPersonasByHipertensionIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where hipertension equals to DEFAULT_HIPERTENSION
        defaultPersonaShouldBeFound("hipertension.equals=" + DEFAULT_HIPERTENSION);

        // Get all the personaList where hipertension equals to UPDATED_HIPERTENSION
        defaultPersonaShouldNotBeFound("hipertension.equals=" + UPDATED_HIPERTENSION);
    }

    @Test
    @Transactional
    public void getAllPersonasByHipertensionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where hipertension not equals to DEFAULT_HIPERTENSION
        defaultPersonaShouldNotBeFound("hipertension.notEquals=" + DEFAULT_HIPERTENSION);

        // Get all the personaList where hipertension not equals to UPDATED_HIPERTENSION
        defaultPersonaShouldBeFound("hipertension.notEquals=" + UPDATED_HIPERTENSION);
    }

    @Test
    @Transactional
    public void getAllPersonasByHipertensionIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where hipertension in DEFAULT_HIPERTENSION or UPDATED_HIPERTENSION
        defaultPersonaShouldBeFound("hipertension.in=" + DEFAULT_HIPERTENSION + "," + UPDATED_HIPERTENSION);

        // Get all the personaList where hipertension equals to UPDATED_HIPERTENSION
        defaultPersonaShouldNotBeFound("hipertension.in=" + UPDATED_HIPERTENSION);
    }

    @Test
    @Transactional
    public void getAllPersonasByHipertensionIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where hipertension is not null
        defaultPersonaShouldBeFound("hipertension.specified=true");

        // Get all the personaList where hipertension is null
        defaultPersonaShouldNotBeFound("hipertension.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByHipertensionContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where hipertension contains DEFAULT_HIPERTENSION
        defaultPersonaShouldBeFound("hipertension.contains=" + DEFAULT_HIPERTENSION);

        // Get all the personaList where hipertension contains UPDATED_HIPERTENSION
        defaultPersonaShouldNotBeFound("hipertension.contains=" + UPDATED_HIPERTENSION);
    }

    @Test
    @Transactional
    public void getAllPersonasByHipertensionNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where hipertension does not contain DEFAULT_HIPERTENSION
        defaultPersonaShouldNotBeFound("hipertension.doesNotContain=" + DEFAULT_HIPERTENSION);

        // Get all the personaList where hipertension does not contain UPDATED_HIPERTENSION
        defaultPersonaShouldBeFound("hipertension.doesNotContain=" + UPDATED_HIPERTENSION);
    }


    @Test
    @Transactional
    public void getAllPersonasByAlergiasIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where alergias equals to DEFAULT_ALERGIAS
        defaultPersonaShouldBeFound("alergias.equals=" + DEFAULT_ALERGIAS);

        // Get all the personaList where alergias equals to UPDATED_ALERGIAS
        defaultPersonaShouldNotBeFound("alergias.equals=" + UPDATED_ALERGIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlergiasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where alergias not equals to DEFAULT_ALERGIAS
        defaultPersonaShouldNotBeFound("alergias.notEquals=" + DEFAULT_ALERGIAS);

        // Get all the personaList where alergias not equals to UPDATED_ALERGIAS
        defaultPersonaShouldBeFound("alergias.notEquals=" + UPDATED_ALERGIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlergiasIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where alergias in DEFAULT_ALERGIAS or UPDATED_ALERGIAS
        defaultPersonaShouldBeFound("alergias.in=" + DEFAULT_ALERGIAS + "," + UPDATED_ALERGIAS);

        // Get all the personaList where alergias equals to UPDATED_ALERGIAS
        defaultPersonaShouldNotBeFound("alergias.in=" + UPDATED_ALERGIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlergiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where alergias is not null
        defaultPersonaShouldBeFound("alergias.specified=true");

        // Get all the personaList where alergias is null
        defaultPersonaShouldNotBeFound("alergias.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByAlergiasContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where alergias contains DEFAULT_ALERGIAS
        defaultPersonaShouldBeFound("alergias.contains=" + DEFAULT_ALERGIAS);

        // Get all the personaList where alergias contains UPDATED_ALERGIAS
        defaultPersonaShouldNotBeFound("alergias.contains=" + UPDATED_ALERGIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlergiasNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where alergias does not contain DEFAULT_ALERGIAS
        defaultPersonaShouldNotBeFound("alergias.doesNotContain=" + DEFAULT_ALERGIAS);

        // Get all the personaList where alergias does not contain UPDATED_ALERGIAS
        defaultPersonaShouldBeFound("alergias.doesNotContain=" + UPDATED_ALERGIAS);
    }


    @Test
    @Transactional
    public void getAllPersonasByAsmaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where asma equals to DEFAULT_ASMA
        defaultPersonaShouldBeFound("asma.equals=" + DEFAULT_ASMA);

        // Get all the personaList where asma equals to UPDATED_ASMA
        defaultPersonaShouldNotBeFound("asma.equals=" + UPDATED_ASMA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAsmaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where asma not equals to DEFAULT_ASMA
        defaultPersonaShouldNotBeFound("asma.notEquals=" + DEFAULT_ASMA);

        // Get all the personaList where asma not equals to UPDATED_ASMA
        defaultPersonaShouldBeFound("asma.notEquals=" + UPDATED_ASMA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAsmaIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where asma in DEFAULT_ASMA or UPDATED_ASMA
        defaultPersonaShouldBeFound("asma.in=" + DEFAULT_ASMA + "," + UPDATED_ASMA);

        // Get all the personaList where asma equals to UPDATED_ASMA
        defaultPersonaShouldNotBeFound("asma.in=" + UPDATED_ASMA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAsmaIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where asma is not null
        defaultPersonaShouldBeFound("asma.specified=true");

        // Get all the personaList where asma is null
        defaultPersonaShouldNotBeFound("asma.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByAsmaContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where asma contains DEFAULT_ASMA
        defaultPersonaShouldBeFound("asma.contains=" + DEFAULT_ASMA);

        // Get all the personaList where asma contains UPDATED_ASMA
        defaultPersonaShouldNotBeFound("asma.contains=" + UPDATED_ASMA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAsmaNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where asma does not contain DEFAULT_ASMA
        defaultPersonaShouldNotBeFound("asma.doesNotContain=" + DEFAULT_ASMA);

        // Get all the personaList where asma does not contain UPDATED_ASMA
        defaultPersonaShouldBeFound("asma.doesNotContain=" + UPDATED_ASMA);
    }


    @Test
    @Transactional
    public void getAllPersonasByOtrosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where otros equals to DEFAULT_OTROS
        defaultPersonaShouldBeFound("otros.equals=" + DEFAULT_OTROS);

        // Get all the personaList where otros equals to UPDATED_OTROS
        defaultPersonaShouldNotBeFound("otros.equals=" + UPDATED_OTROS);
    }

    @Test
    @Transactional
    public void getAllPersonasByOtrosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where otros not equals to DEFAULT_OTROS
        defaultPersonaShouldNotBeFound("otros.notEquals=" + DEFAULT_OTROS);

        // Get all the personaList where otros not equals to UPDATED_OTROS
        defaultPersonaShouldBeFound("otros.notEquals=" + UPDATED_OTROS);
    }

    @Test
    @Transactional
    public void getAllPersonasByOtrosIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where otros in DEFAULT_OTROS or UPDATED_OTROS
        defaultPersonaShouldBeFound("otros.in=" + DEFAULT_OTROS + "," + UPDATED_OTROS);

        // Get all the personaList where otros equals to UPDATED_OTROS
        defaultPersonaShouldNotBeFound("otros.in=" + UPDATED_OTROS);
    }

    @Test
    @Transactional
    public void getAllPersonasByOtrosIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where otros is not null
        defaultPersonaShouldBeFound("otros.specified=true");

        // Get all the personaList where otros is null
        defaultPersonaShouldNotBeFound("otros.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByOtrosContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where otros contains DEFAULT_OTROS
        defaultPersonaShouldBeFound("otros.contains=" + DEFAULT_OTROS);

        // Get all the personaList where otros contains UPDATED_OTROS
        defaultPersonaShouldNotBeFound("otros.contains=" + UPDATED_OTROS);
    }

    @Test
    @Transactional
    public void getAllPersonasByOtrosNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where otros does not contain DEFAULT_OTROS
        defaultPersonaShouldNotBeFound("otros.doesNotContain=" + DEFAULT_OTROS);

        // Get all the personaList where otros does not contain UPDATED_OTROS
        defaultPersonaShouldBeFound("otros.doesNotContain=" + UPDATED_OTROS);
    }


    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso equals to DEFAULT_FECHADEINGRESO
        defaultPersonaShouldBeFound("fechadeingreso.equals=" + DEFAULT_FECHADEINGRESO);

        // Get all the personaList where fechadeingreso equals to UPDATED_FECHADEINGRESO
        defaultPersonaShouldNotBeFound("fechadeingreso.equals=" + UPDATED_FECHADEINGRESO);
    }

    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso not equals to DEFAULT_FECHADEINGRESO
        defaultPersonaShouldNotBeFound("fechadeingreso.notEquals=" + DEFAULT_FECHADEINGRESO);

        // Get all the personaList where fechadeingreso not equals to UPDATED_FECHADEINGRESO
        defaultPersonaShouldBeFound("fechadeingreso.notEquals=" + UPDATED_FECHADEINGRESO);
    }

    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso in DEFAULT_FECHADEINGRESO or UPDATED_FECHADEINGRESO
        defaultPersonaShouldBeFound("fechadeingreso.in=" + DEFAULT_FECHADEINGRESO + "," + UPDATED_FECHADEINGRESO);

        // Get all the personaList where fechadeingreso equals to UPDATED_FECHADEINGRESO
        defaultPersonaShouldNotBeFound("fechadeingreso.in=" + UPDATED_FECHADEINGRESO);
    }

    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso is not null
        defaultPersonaShouldBeFound("fechadeingreso.specified=true");

        // Get all the personaList where fechadeingreso is null
        defaultPersonaShouldNotBeFound("fechadeingreso.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso is greater than or equal to DEFAULT_FECHADEINGRESO
        defaultPersonaShouldBeFound("fechadeingreso.greaterThanOrEqual=" + DEFAULT_FECHADEINGRESO);

        // Get all the personaList where fechadeingreso is greater than or equal to UPDATED_FECHADEINGRESO
        defaultPersonaShouldNotBeFound("fechadeingreso.greaterThanOrEqual=" + UPDATED_FECHADEINGRESO);
    }

    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso is less than or equal to DEFAULT_FECHADEINGRESO
        defaultPersonaShouldBeFound("fechadeingreso.lessThanOrEqual=" + DEFAULT_FECHADEINGRESO);

        // Get all the personaList where fechadeingreso is less than or equal to SMALLER_FECHADEINGRESO
        defaultPersonaShouldNotBeFound("fechadeingreso.lessThanOrEqual=" + SMALLER_FECHADEINGRESO);
    }

    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso is less than DEFAULT_FECHADEINGRESO
        defaultPersonaShouldNotBeFound("fechadeingreso.lessThan=" + DEFAULT_FECHADEINGRESO);

        // Get all the personaList where fechadeingreso is less than UPDATED_FECHADEINGRESO
        defaultPersonaShouldBeFound("fechadeingreso.lessThan=" + UPDATED_FECHADEINGRESO);
    }

    @Test
    @Transactional
    public void getAllPersonasByFechadeingresoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where fechadeingreso is greater than DEFAULT_FECHADEINGRESO
        defaultPersonaShouldNotBeFound("fechadeingreso.greaterThan=" + DEFAULT_FECHADEINGRESO);

        // Get all the personaList where fechadeingreso is greater than SMALLER_FECHADEINGRESO
        defaultPersonaShouldBeFound("fechadeingreso.greaterThan=" + SMALLER_FECHADEINGRESO);
    }


    @Test
    @Transactional
    public void getAllPersonasByInstrumentolegalIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where instrumentolegal equals to DEFAULT_INSTRUMENTOLEGAL
        defaultPersonaShouldBeFound("instrumentolegal.equals=" + DEFAULT_INSTRUMENTOLEGAL);

        // Get all the personaList where instrumentolegal equals to UPDATED_INSTRUMENTOLEGAL
        defaultPersonaShouldNotBeFound("instrumentolegal.equals=" + UPDATED_INSTRUMENTOLEGAL);
    }

    @Test
    @Transactional
    public void getAllPersonasByInstrumentolegalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where instrumentolegal not equals to DEFAULT_INSTRUMENTOLEGAL
        defaultPersonaShouldNotBeFound("instrumentolegal.notEquals=" + DEFAULT_INSTRUMENTOLEGAL);

        // Get all the personaList where instrumentolegal not equals to UPDATED_INSTRUMENTOLEGAL
        defaultPersonaShouldBeFound("instrumentolegal.notEquals=" + UPDATED_INSTRUMENTOLEGAL);
    }

    @Test
    @Transactional
    public void getAllPersonasByInstrumentolegalIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where instrumentolegal in DEFAULT_INSTRUMENTOLEGAL or UPDATED_INSTRUMENTOLEGAL
        defaultPersonaShouldBeFound("instrumentolegal.in=" + DEFAULT_INSTRUMENTOLEGAL + "," + UPDATED_INSTRUMENTOLEGAL);

        // Get all the personaList where instrumentolegal equals to UPDATED_INSTRUMENTOLEGAL
        defaultPersonaShouldNotBeFound("instrumentolegal.in=" + UPDATED_INSTRUMENTOLEGAL);
    }

    @Test
    @Transactional
    public void getAllPersonasByInstrumentolegalIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where instrumentolegal is not null
        defaultPersonaShouldBeFound("instrumentolegal.specified=true");

        // Get all the personaList where instrumentolegal is null
        defaultPersonaShouldNotBeFound("instrumentolegal.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByInstrumentolegalContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where instrumentolegal contains DEFAULT_INSTRUMENTOLEGAL
        defaultPersonaShouldBeFound("instrumentolegal.contains=" + DEFAULT_INSTRUMENTOLEGAL);

        // Get all the personaList where instrumentolegal contains UPDATED_INSTRUMENTOLEGAL
        defaultPersonaShouldNotBeFound("instrumentolegal.contains=" + UPDATED_INSTRUMENTOLEGAL);
    }

    @Test
    @Transactional
    public void getAllPersonasByInstrumentolegalNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where instrumentolegal does not contain DEFAULT_INSTRUMENTOLEGAL
        defaultPersonaShouldNotBeFound("instrumentolegal.doesNotContain=" + DEFAULT_INSTRUMENTOLEGAL);

        // Get all the personaList where instrumentolegal does not contain UPDATED_INSTRUMENTOLEGAL
        defaultPersonaShouldBeFound("instrumentolegal.doesNotContain=" + UPDATED_INSTRUMENTOLEGAL);
    }


    @Test
    @Transactional
    public void getAllPersonasByCategoriaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where categoria equals to DEFAULT_CATEGORIA
        defaultPersonaShouldBeFound("categoria.equals=" + DEFAULT_CATEGORIA);

        // Get all the personaList where categoria equals to UPDATED_CATEGORIA
        defaultPersonaShouldNotBeFound("categoria.equals=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllPersonasByCategoriaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where categoria not equals to DEFAULT_CATEGORIA
        defaultPersonaShouldNotBeFound("categoria.notEquals=" + DEFAULT_CATEGORIA);

        // Get all the personaList where categoria not equals to UPDATED_CATEGORIA
        defaultPersonaShouldBeFound("categoria.notEquals=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllPersonasByCategoriaIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where categoria in DEFAULT_CATEGORIA or UPDATED_CATEGORIA
        defaultPersonaShouldBeFound("categoria.in=" + DEFAULT_CATEGORIA + "," + UPDATED_CATEGORIA);

        // Get all the personaList where categoria equals to UPDATED_CATEGORIA
        defaultPersonaShouldNotBeFound("categoria.in=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllPersonasByCategoriaIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where categoria is not null
        defaultPersonaShouldBeFound("categoria.specified=true");

        // Get all the personaList where categoria is null
        defaultPersonaShouldNotBeFound("categoria.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCategoriaContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where categoria contains DEFAULT_CATEGORIA
        defaultPersonaShouldBeFound("categoria.contains=" + DEFAULT_CATEGORIA);

        // Get all the personaList where categoria contains UPDATED_CATEGORIA
        defaultPersonaShouldNotBeFound("categoria.contains=" + UPDATED_CATEGORIA);
    }

    @Test
    @Transactional
    public void getAllPersonasByCategoriaNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where categoria does not contain DEFAULT_CATEGORIA
        defaultPersonaShouldNotBeFound("categoria.doesNotContain=" + DEFAULT_CATEGORIA);

        // Get all the personaList where categoria does not contain UPDATED_CATEGORIA
        defaultPersonaShouldBeFound("categoria.doesNotContain=" + UPDATED_CATEGORIA);
    }


    @Test
    @Transactional
    public void getAllPersonasByItemIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where item equals to DEFAULT_ITEM
        defaultPersonaShouldBeFound("item.equals=" + DEFAULT_ITEM);

        // Get all the personaList where item equals to UPDATED_ITEM
        defaultPersonaShouldNotBeFound("item.equals=" + UPDATED_ITEM);
    }

    @Test
    @Transactional
    public void getAllPersonasByItemIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where item not equals to DEFAULT_ITEM
        defaultPersonaShouldNotBeFound("item.notEquals=" + DEFAULT_ITEM);

        // Get all the personaList where item not equals to UPDATED_ITEM
        defaultPersonaShouldBeFound("item.notEquals=" + UPDATED_ITEM);
    }

    @Test
    @Transactional
    public void getAllPersonasByItemIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where item in DEFAULT_ITEM or UPDATED_ITEM
        defaultPersonaShouldBeFound("item.in=" + DEFAULT_ITEM + "," + UPDATED_ITEM);

        // Get all the personaList where item equals to UPDATED_ITEM
        defaultPersonaShouldNotBeFound("item.in=" + UPDATED_ITEM);
    }

    @Test
    @Transactional
    public void getAllPersonasByItemIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where item is not null
        defaultPersonaShouldBeFound("item.specified=true");

        // Get all the personaList where item is null
        defaultPersonaShouldNotBeFound("item.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByItemContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where item contains DEFAULT_ITEM
        defaultPersonaShouldBeFound("item.contains=" + DEFAULT_ITEM);

        // Get all the personaList where item contains UPDATED_ITEM
        defaultPersonaShouldNotBeFound("item.contains=" + UPDATED_ITEM);
    }

    @Test
    @Transactional
    public void getAllPersonasByItemNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where item does not contain DEFAULT_ITEM
        defaultPersonaShouldNotBeFound("item.doesNotContain=" + DEFAULT_ITEM);

        // Get all the personaList where item does not contain UPDATED_ITEM
        defaultPersonaShouldBeFound("item.doesNotContain=" + UPDATED_ITEM);
    }


    @Test
    @Transactional
    public void getAllPersonasByPlantaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where planta equals to DEFAULT_PLANTA
        defaultPersonaShouldBeFound("planta.equals=" + DEFAULT_PLANTA);

        // Get all the personaList where planta equals to UPDATED_PLANTA
        defaultPersonaShouldNotBeFound("planta.equals=" + UPDATED_PLANTA);
    }

    @Test
    @Transactional
    public void getAllPersonasByPlantaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where planta not equals to DEFAULT_PLANTA
        defaultPersonaShouldNotBeFound("planta.notEquals=" + DEFAULT_PLANTA);

        // Get all the personaList where planta not equals to UPDATED_PLANTA
        defaultPersonaShouldBeFound("planta.notEquals=" + UPDATED_PLANTA);
    }

    @Test
    @Transactional
    public void getAllPersonasByPlantaIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where planta in DEFAULT_PLANTA or UPDATED_PLANTA
        defaultPersonaShouldBeFound("planta.in=" + DEFAULT_PLANTA + "," + UPDATED_PLANTA);

        // Get all the personaList where planta equals to UPDATED_PLANTA
        defaultPersonaShouldNotBeFound("planta.in=" + UPDATED_PLANTA);
    }

    @Test
    @Transactional
    public void getAllPersonasByPlantaIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where planta is not null
        defaultPersonaShouldBeFound("planta.specified=true");

        // Get all the personaList where planta is null
        defaultPersonaShouldNotBeFound("planta.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByPlantaContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where planta contains DEFAULT_PLANTA
        defaultPersonaShouldBeFound("planta.contains=" + DEFAULT_PLANTA);

        // Get all the personaList where planta contains UPDATED_PLANTA
        defaultPersonaShouldNotBeFound("planta.contains=" + UPDATED_PLANTA);
    }

    @Test
    @Transactional
    public void getAllPersonasByPlantaNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where planta does not contain DEFAULT_PLANTA
        defaultPersonaShouldNotBeFound("planta.doesNotContain=" + DEFAULT_PLANTA);

        // Get all the personaList where planta does not contain UPDATED_PLANTA
        defaultPersonaShouldBeFound("planta.doesNotContain=" + UPDATED_PLANTA);
    }


    @Test
    @Transactional
    public void getAllPersonasByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where area equals to DEFAULT_AREA
        defaultPersonaShouldBeFound("area.equals=" + DEFAULT_AREA);

        // Get all the personaList where area equals to UPDATED_AREA
        defaultPersonaShouldNotBeFound("area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAreaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where area not equals to DEFAULT_AREA
        defaultPersonaShouldNotBeFound("area.notEquals=" + DEFAULT_AREA);

        // Get all the personaList where area not equals to UPDATED_AREA
        defaultPersonaShouldBeFound("area.notEquals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where area in DEFAULT_AREA or UPDATED_AREA
        defaultPersonaShouldBeFound("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA);

        // Get all the personaList where area equals to UPDATED_AREA
        defaultPersonaShouldNotBeFound("area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where area is not null
        defaultPersonaShouldBeFound("area.specified=true");

        // Get all the personaList where area is null
        defaultPersonaShouldNotBeFound("area.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByAreaContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where area contains DEFAULT_AREA
        defaultPersonaShouldBeFound("area.contains=" + DEFAULT_AREA);

        // Get all the personaList where area contains UPDATED_AREA
        defaultPersonaShouldNotBeFound("area.contains=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAreaNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where area does not contain DEFAULT_AREA
        defaultPersonaShouldNotBeFound("area.doesNotContain=" + DEFAULT_AREA);

        // Get all the personaList where area does not contain UPDATED_AREA
        defaultPersonaShouldBeFound("area.doesNotContain=" + UPDATED_AREA);
    }


    @Test
    @Transactional
    public void getAllPersonasByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where direccion equals to DEFAULT_DIRECCION
        defaultPersonaShouldBeFound("direccion.equals=" + DEFAULT_DIRECCION);

        // Get all the personaList where direccion equals to UPDATED_DIRECCION
        defaultPersonaShouldNotBeFound("direccion.equals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllPersonasByDireccionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where direccion not equals to DEFAULT_DIRECCION
        defaultPersonaShouldNotBeFound("direccion.notEquals=" + DEFAULT_DIRECCION);

        // Get all the personaList where direccion not equals to UPDATED_DIRECCION
        defaultPersonaShouldBeFound("direccion.notEquals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllPersonasByDireccionIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where direccion in DEFAULT_DIRECCION or UPDATED_DIRECCION
        defaultPersonaShouldBeFound("direccion.in=" + DEFAULT_DIRECCION + "," + UPDATED_DIRECCION);

        // Get all the personaList where direccion equals to UPDATED_DIRECCION
        defaultPersonaShouldNotBeFound("direccion.in=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllPersonasByDireccionIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where direccion is not null
        defaultPersonaShouldBeFound("direccion.specified=true");

        // Get all the personaList where direccion is null
        defaultPersonaShouldNotBeFound("direccion.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByDireccionContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where direccion contains DEFAULT_DIRECCION
        defaultPersonaShouldBeFound("direccion.contains=" + DEFAULT_DIRECCION);

        // Get all the personaList where direccion contains UPDATED_DIRECCION
        defaultPersonaShouldNotBeFound("direccion.contains=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllPersonasByDireccionNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where direccion does not contain DEFAULT_DIRECCION
        defaultPersonaShouldNotBeFound("direccion.doesNotContain=" + DEFAULT_DIRECCION);

        // Get all the personaList where direccion does not contain UPDATED_DIRECCION
        defaultPersonaShouldBeFound("direccion.doesNotContain=" + UPDATED_DIRECCION);
    }


    @Test
    @Transactional
    public void getAllPersonasByAnnosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos equals to DEFAULT_ANNOS
        defaultPersonaShouldBeFound("annos.equals=" + DEFAULT_ANNOS);

        // Get all the personaList where annos equals to UPDATED_ANNOS
        defaultPersonaShouldNotBeFound("annos.equals=" + UPDATED_ANNOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAnnosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos not equals to DEFAULT_ANNOS
        defaultPersonaShouldNotBeFound("annos.notEquals=" + DEFAULT_ANNOS);

        // Get all the personaList where annos not equals to UPDATED_ANNOS
        defaultPersonaShouldBeFound("annos.notEquals=" + UPDATED_ANNOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAnnosIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos in DEFAULT_ANNOS or UPDATED_ANNOS
        defaultPersonaShouldBeFound("annos.in=" + DEFAULT_ANNOS + "," + UPDATED_ANNOS);

        // Get all the personaList where annos equals to UPDATED_ANNOS
        defaultPersonaShouldNotBeFound("annos.in=" + UPDATED_ANNOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAnnosIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos is not null
        defaultPersonaShouldBeFound("annos.specified=true");

        // Get all the personaList where annos is null
        defaultPersonaShouldNotBeFound("annos.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByAnnosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos is greater than or equal to DEFAULT_ANNOS
        defaultPersonaShouldBeFound("annos.greaterThanOrEqual=" + DEFAULT_ANNOS);

        // Get all the personaList where annos is greater than or equal to UPDATED_ANNOS
        defaultPersonaShouldNotBeFound("annos.greaterThanOrEqual=" + UPDATED_ANNOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAnnosIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos is less than or equal to DEFAULT_ANNOS
        defaultPersonaShouldBeFound("annos.lessThanOrEqual=" + DEFAULT_ANNOS);

        // Get all the personaList where annos is less than or equal to SMALLER_ANNOS
        defaultPersonaShouldNotBeFound("annos.lessThanOrEqual=" + SMALLER_ANNOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAnnosIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos is less than DEFAULT_ANNOS
        defaultPersonaShouldNotBeFound("annos.lessThan=" + DEFAULT_ANNOS);

        // Get all the personaList where annos is less than UPDATED_ANNOS
        defaultPersonaShouldBeFound("annos.lessThan=" + UPDATED_ANNOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByAnnosIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where annos is greater than DEFAULT_ANNOS
        defaultPersonaShouldNotBeFound("annos.greaterThan=" + DEFAULT_ANNOS);

        // Get all the personaList where annos is greater than SMALLER_ANNOS
        defaultPersonaShouldBeFound("annos.greaterThan=" + SMALLER_ANNOS);
    }


    @Test
    @Transactional
    public void getAllPersonasByMesesIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses equals to DEFAULT_MESES
        defaultPersonaShouldBeFound("meses.equals=" + DEFAULT_MESES);

        // Get all the personaList where meses equals to UPDATED_MESES
        defaultPersonaShouldNotBeFound("meses.equals=" + UPDATED_MESES);
    }

    @Test
    @Transactional
    public void getAllPersonasByMesesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses not equals to DEFAULT_MESES
        defaultPersonaShouldNotBeFound("meses.notEquals=" + DEFAULT_MESES);

        // Get all the personaList where meses not equals to UPDATED_MESES
        defaultPersonaShouldBeFound("meses.notEquals=" + UPDATED_MESES);
    }

    @Test
    @Transactional
    public void getAllPersonasByMesesIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses in DEFAULT_MESES or UPDATED_MESES
        defaultPersonaShouldBeFound("meses.in=" + DEFAULT_MESES + "," + UPDATED_MESES);

        // Get all the personaList where meses equals to UPDATED_MESES
        defaultPersonaShouldNotBeFound("meses.in=" + UPDATED_MESES);
    }

    @Test
    @Transactional
    public void getAllPersonasByMesesIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses is not null
        defaultPersonaShouldBeFound("meses.specified=true");

        // Get all the personaList where meses is null
        defaultPersonaShouldNotBeFound("meses.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByMesesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses is greater than or equal to DEFAULT_MESES
        defaultPersonaShouldBeFound("meses.greaterThanOrEqual=" + DEFAULT_MESES);

        // Get all the personaList where meses is greater than or equal to UPDATED_MESES
        defaultPersonaShouldNotBeFound("meses.greaterThanOrEqual=" + UPDATED_MESES);
    }

    @Test
    @Transactional
    public void getAllPersonasByMesesIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses is less than or equal to DEFAULT_MESES
        defaultPersonaShouldBeFound("meses.lessThanOrEqual=" + DEFAULT_MESES);

        // Get all the personaList where meses is less than or equal to SMALLER_MESES
        defaultPersonaShouldNotBeFound("meses.lessThanOrEqual=" + SMALLER_MESES);
    }

    @Test
    @Transactional
    public void getAllPersonasByMesesIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses is less than DEFAULT_MESES
        defaultPersonaShouldNotBeFound("meses.lessThan=" + DEFAULT_MESES);

        // Get all the personaList where meses is less than UPDATED_MESES
        defaultPersonaShouldBeFound("meses.lessThan=" + UPDATED_MESES);
    }

    @Test
    @Transactional
    public void getAllPersonasByMesesIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where meses is greater than DEFAULT_MESES
        defaultPersonaShouldNotBeFound("meses.greaterThan=" + DEFAULT_MESES);

        // Get all the personaList where meses is greater than SMALLER_MESES
        defaultPersonaShouldBeFound("meses.greaterThan=" + SMALLER_MESES);
    }


    @Test
    @Transactional
    public void getAllPersonasByDiasIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias equals to DEFAULT_DIAS
        defaultPersonaShouldBeFound("dias.equals=" + DEFAULT_DIAS);

        // Get all the personaList where dias equals to UPDATED_DIAS
        defaultPersonaShouldNotBeFound("dias.equals=" + UPDATED_DIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias not equals to DEFAULT_DIAS
        defaultPersonaShouldNotBeFound("dias.notEquals=" + DEFAULT_DIAS);

        // Get all the personaList where dias not equals to UPDATED_DIAS
        defaultPersonaShouldBeFound("dias.notEquals=" + UPDATED_DIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiasIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias in DEFAULT_DIAS or UPDATED_DIAS
        defaultPersonaShouldBeFound("dias.in=" + DEFAULT_DIAS + "," + UPDATED_DIAS);

        // Get all the personaList where dias equals to UPDATED_DIAS
        defaultPersonaShouldNotBeFound("dias.in=" + UPDATED_DIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiasIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias is not null
        defaultPersonaShouldBeFound("dias.specified=true");

        // Get all the personaList where dias is null
        defaultPersonaShouldNotBeFound("dias.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByDiasIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias is greater than or equal to DEFAULT_DIAS
        defaultPersonaShouldBeFound("dias.greaterThanOrEqual=" + DEFAULT_DIAS);

        // Get all the personaList where dias is greater than or equal to UPDATED_DIAS
        defaultPersonaShouldNotBeFound("dias.greaterThanOrEqual=" + UPDATED_DIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiasIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias is less than or equal to DEFAULT_DIAS
        defaultPersonaShouldBeFound("dias.lessThanOrEqual=" + DEFAULT_DIAS);

        // Get all the personaList where dias is less than or equal to SMALLER_DIAS
        defaultPersonaShouldNotBeFound("dias.lessThanOrEqual=" + SMALLER_DIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiasIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias is less than DEFAULT_DIAS
        defaultPersonaShouldNotBeFound("dias.lessThan=" + DEFAULT_DIAS);

        // Get all the personaList where dias is less than UPDATED_DIAS
        defaultPersonaShouldBeFound("dias.lessThan=" + UPDATED_DIAS);
    }

    @Test
    @Transactional
    public void getAllPersonasByDiasIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where dias is greater than DEFAULT_DIAS
        defaultPersonaShouldNotBeFound("dias.greaterThan=" + DEFAULT_DIAS);

        // Get all the personaList where dias is greater than SMALLER_DIAS
        defaultPersonaShouldBeFound("dias.greaterThan=" + SMALLER_DIAS);
    }


    @Test
    @Transactional
    public void getAllPersonasByRealizocomputodeserviciosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where realizocomputodeservicios equals to DEFAULT_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldBeFound("realizocomputodeservicios.equals=" + DEFAULT_REALIZOCOMPUTODESERVICIOS);

        // Get all the personaList where realizocomputodeservicios equals to UPDATED_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldNotBeFound("realizocomputodeservicios.equals=" + UPDATED_REALIZOCOMPUTODESERVICIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByRealizocomputodeserviciosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where realizocomputodeservicios not equals to DEFAULT_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldNotBeFound("realizocomputodeservicios.notEquals=" + DEFAULT_REALIZOCOMPUTODESERVICIOS);

        // Get all the personaList where realizocomputodeservicios not equals to UPDATED_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldBeFound("realizocomputodeservicios.notEquals=" + UPDATED_REALIZOCOMPUTODESERVICIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByRealizocomputodeserviciosIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where realizocomputodeservicios in DEFAULT_REALIZOCOMPUTODESERVICIOS or UPDATED_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldBeFound("realizocomputodeservicios.in=" + DEFAULT_REALIZOCOMPUTODESERVICIOS + "," + UPDATED_REALIZOCOMPUTODESERVICIOS);

        // Get all the personaList where realizocomputodeservicios equals to UPDATED_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldNotBeFound("realizocomputodeservicios.in=" + UPDATED_REALIZOCOMPUTODESERVICIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByRealizocomputodeserviciosIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where realizocomputodeservicios is not null
        defaultPersonaShouldBeFound("realizocomputodeservicios.specified=true");

        // Get all the personaList where realizocomputodeservicios is null
        defaultPersonaShouldNotBeFound("realizocomputodeservicios.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByRealizocomputodeserviciosContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where realizocomputodeservicios contains DEFAULT_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldBeFound("realizocomputodeservicios.contains=" + DEFAULT_REALIZOCOMPUTODESERVICIOS);

        // Get all the personaList where realizocomputodeservicios contains UPDATED_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldNotBeFound("realizocomputodeservicios.contains=" + UPDATED_REALIZOCOMPUTODESERVICIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByRealizocomputodeserviciosNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where realizocomputodeservicios does not contain DEFAULT_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldNotBeFound("realizocomputodeservicios.doesNotContain=" + DEFAULT_REALIZOCOMPUTODESERVICIOS);

        // Get all the personaList where realizocomputodeservicios does not contain UPDATED_REALIZOCOMPUTODESERVICIOS
        defaultPersonaShouldBeFound("realizocomputodeservicios.doesNotContain=" + UPDATED_REALIZOCOMPUTODESERVICIOS);
    }


    @Test
    @Transactional
    public void getAllPersonasByPoseeconocimientoenmaquinasvialesIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where poseeconocimientoenmaquinasviales equals to DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldBeFound("poseeconocimientoenmaquinasviales.equals=" + DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES);

        // Get all the personaList where poseeconocimientoenmaquinasviales equals to UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldNotBeFound("poseeconocimientoenmaquinasviales.equals=" + UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES);
    }

    @Test
    @Transactional
    public void getAllPersonasByPoseeconocimientoenmaquinasvialesIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where poseeconocimientoenmaquinasviales not equals to DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldNotBeFound("poseeconocimientoenmaquinasviales.notEquals=" + DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES);

        // Get all the personaList where poseeconocimientoenmaquinasviales not equals to UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldBeFound("poseeconocimientoenmaquinasviales.notEquals=" + UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES);
    }

    @Test
    @Transactional
    public void getAllPersonasByPoseeconocimientoenmaquinasvialesIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where poseeconocimientoenmaquinasviales in DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES or UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldBeFound("poseeconocimientoenmaquinasviales.in=" + DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES + "," + UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES);

        // Get all the personaList where poseeconocimientoenmaquinasviales equals to UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldNotBeFound("poseeconocimientoenmaquinasviales.in=" + UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES);
    }

    @Test
    @Transactional
    public void getAllPersonasByPoseeconocimientoenmaquinasvialesIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where poseeconocimientoenmaquinasviales is not null
        defaultPersonaShouldBeFound("poseeconocimientoenmaquinasviales.specified=true");

        // Get all the personaList where poseeconocimientoenmaquinasviales is null
        defaultPersonaShouldNotBeFound("poseeconocimientoenmaquinasviales.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByPoseeconocimientoenmaquinasvialesContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where poseeconocimientoenmaquinasviales contains DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldBeFound("poseeconocimientoenmaquinasviales.contains=" + DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES);

        // Get all the personaList where poseeconocimientoenmaquinasviales contains UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldNotBeFound("poseeconocimientoenmaquinasviales.contains=" + UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES);
    }

    @Test
    @Transactional
    public void getAllPersonasByPoseeconocimientoenmaquinasvialesNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where poseeconocimientoenmaquinasviales does not contain DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldNotBeFound("poseeconocimientoenmaquinasviales.doesNotContain=" + DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES);

        // Get all the personaList where poseeconocimientoenmaquinasviales does not contain UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES
        defaultPersonaShouldBeFound("poseeconocimientoenmaquinasviales.doesNotContain=" + UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES);
    }


    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelularIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular equals to DEFAULT_CASOEMERGENCIACELULAR
        defaultPersonaShouldBeFound("casoemergenciacelular.equals=" + DEFAULT_CASOEMERGENCIACELULAR);

        // Get all the personaList where casoemergenciacelular equals to UPDATED_CASOEMERGENCIACELULAR
        defaultPersonaShouldNotBeFound("casoemergenciacelular.equals=" + UPDATED_CASOEMERGENCIACELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelularIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular not equals to DEFAULT_CASOEMERGENCIACELULAR
        defaultPersonaShouldNotBeFound("casoemergenciacelular.notEquals=" + DEFAULT_CASOEMERGENCIACELULAR);

        // Get all the personaList where casoemergenciacelular not equals to UPDATED_CASOEMERGENCIACELULAR
        defaultPersonaShouldBeFound("casoemergenciacelular.notEquals=" + UPDATED_CASOEMERGENCIACELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelularIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular in DEFAULT_CASOEMERGENCIACELULAR or UPDATED_CASOEMERGENCIACELULAR
        defaultPersonaShouldBeFound("casoemergenciacelular.in=" + DEFAULT_CASOEMERGENCIACELULAR + "," + UPDATED_CASOEMERGENCIACELULAR);

        // Get all the personaList where casoemergenciacelular equals to UPDATED_CASOEMERGENCIACELULAR
        defaultPersonaShouldNotBeFound("casoemergenciacelular.in=" + UPDATED_CASOEMERGENCIACELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelularIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular is not null
        defaultPersonaShouldBeFound("casoemergenciacelular.specified=true");

        // Get all the personaList where casoemergenciacelular is null
        defaultPersonaShouldNotBeFound("casoemergenciacelular.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelularContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular contains DEFAULT_CASOEMERGENCIACELULAR
        defaultPersonaShouldBeFound("casoemergenciacelular.contains=" + DEFAULT_CASOEMERGENCIACELULAR);

        // Get all the personaList where casoemergenciacelular contains UPDATED_CASOEMERGENCIACELULAR
        defaultPersonaShouldNotBeFound("casoemergenciacelular.contains=" + UPDATED_CASOEMERGENCIACELULAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelularNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular does not contain DEFAULT_CASOEMERGENCIACELULAR
        defaultPersonaShouldNotBeFound("casoemergenciacelular.doesNotContain=" + DEFAULT_CASOEMERGENCIACELULAR);

        // Get all the personaList where casoemergenciacelular does not contain UPDATED_CASOEMERGENCIACELULAR
        defaultPersonaShouldBeFound("casoemergenciacelular.doesNotContain=" + UPDATED_CASOEMERGENCIACELULAR);
    }


    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo equals to DEFAULT_CASOEMERGENCIAFIJO
        defaultPersonaShouldBeFound("casoemergenciafijo.equals=" + DEFAULT_CASOEMERGENCIAFIJO);

        // Get all the personaList where casoemergenciafijo equals to UPDATED_CASOEMERGENCIAFIJO
        defaultPersonaShouldNotBeFound("casoemergenciafijo.equals=" + UPDATED_CASOEMERGENCIAFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo not equals to DEFAULT_CASOEMERGENCIAFIJO
        defaultPersonaShouldNotBeFound("casoemergenciafijo.notEquals=" + DEFAULT_CASOEMERGENCIAFIJO);

        // Get all the personaList where casoemergenciafijo not equals to UPDATED_CASOEMERGENCIAFIJO
        defaultPersonaShouldBeFound("casoemergenciafijo.notEquals=" + UPDATED_CASOEMERGENCIAFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo in DEFAULT_CASOEMERGENCIAFIJO or UPDATED_CASOEMERGENCIAFIJO
        defaultPersonaShouldBeFound("casoemergenciafijo.in=" + DEFAULT_CASOEMERGENCIAFIJO + "," + UPDATED_CASOEMERGENCIAFIJO);

        // Get all the personaList where casoemergenciafijo equals to UPDATED_CASOEMERGENCIAFIJO
        defaultPersonaShouldNotBeFound("casoemergenciafijo.in=" + UPDATED_CASOEMERGENCIAFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo is not null
        defaultPersonaShouldBeFound("casoemergenciafijo.specified=true");

        // Get all the personaList where casoemergenciafijo is null
        defaultPersonaShouldNotBeFound("casoemergenciafijo.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo contains DEFAULT_CASOEMERGENCIAFIJO
        defaultPersonaShouldBeFound("casoemergenciafijo.contains=" + DEFAULT_CASOEMERGENCIAFIJO);

        // Get all the personaList where casoemergenciafijo contains UPDATED_CASOEMERGENCIAFIJO
        defaultPersonaShouldNotBeFound("casoemergenciafijo.contains=" + UPDATED_CASOEMERGENCIAFIJO);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo does not contain DEFAULT_CASOEMERGENCIAFIJO
        defaultPersonaShouldNotBeFound("casoemergenciafijo.doesNotContain=" + DEFAULT_CASOEMERGENCIAFIJO);

        // Get all the personaList where casoemergenciafijo does not contain UPDATED_CASOEMERGENCIAFIJO
        defaultPersonaShouldBeFound("casoemergenciafijo.doesNotContain=" + UPDATED_CASOEMERGENCIAFIJO);
    }


    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombreIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre equals to DEFAULT_CASOEMERGENCIANOMBRE
        defaultPersonaShouldBeFound("casoemergencianombre.equals=" + DEFAULT_CASOEMERGENCIANOMBRE);

        // Get all the personaList where casoemergencianombre equals to UPDATED_CASOEMERGENCIANOMBRE
        defaultPersonaShouldNotBeFound("casoemergencianombre.equals=" + UPDATED_CASOEMERGENCIANOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre not equals to DEFAULT_CASOEMERGENCIANOMBRE
        defaultPersonaShouldNotBeFound("casoemergencianombre.notEquals=" + DEFAULT_CASOEMERGENCIANOMBRE);

        // Get all the personaList where casoemergencianombre not equals to UPDATED_CASOEMERGENCIANOMBRE
        defaultPersonaShouldBeFound("casoemergencianombre.notEquals=" + UPDATED_CASOEMERGENCIANOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombreIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre in DEFAULT_CASOEMERGENCIANOMBRE or UPDATED_CASOEMERGENCIANOMBRE
        defaultPersonaShouldBeFound("casoemergencianombre.in=" + DEFAULT_CASOEMERGENCIANOMBRE + "," + UPDATED_CASOEMERGENCIANOMBRE);

        // Get all the personaList where casoemergencianombre equals to UPDATED_CASOEMERGENCIANOMBRE
        defaultPersonaShouldNotBeFound("casoemergencianombre.in=" + UPDATED_CASOEMERGENCIANOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre is not null
        defaultPersonaShouldBeFound("casoemergencianombre.specified=true");

        // Get all the personaList where casoemergencianombre is null
        defaultPersonaShouldNotBeFound("casoemergencianombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombreContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre contains DEFAULT_CASOEMERGENCIANOMBRE
        defaultPersonaShouldBeFound("casoemergencianombre.contains=" + DEFAULT_CASOEMERGENCIANOMBRE);

        // Get all the personaList where casoemergencianombre contains UPDATED_CASOEMERGENCIANOMBRE
        defaultPersonaShouldNotBeFound("casoemergencianombre.contains=" + UPDATED_CASOEMERGENCIANOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombreNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre does not contain DEFAULT_CASOEMERGENCIANOMBRE
        defaultPersonaShouldNotBeFound("casoemergencianombre.doesNotContain=" + DEFAULT_CASOEMERGENCIANOMBRE);

        // Get all the personaList where casoemergencianombre does not contain UPDATED_CASOEMERGENCIANOMBRE
        defaultPersonaShouldBeFound("casoemergencianombre.doesNotContain=" + UPDATED_CASOEMERGENCIANOMBRE);
    }


    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelular2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular2 equals to DEFAULT_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldBeFound("casoemergenciacelular2.equals=" + DEFAULT_CASOEMERGENCIACELULAR_2);

        // Get all the personaList where casoemergenciacelular2 equals to UPDATED_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldNotBeFound("casoemergenciacelular2.equals=" + UPDATED_CASOEMERGENCIACELULAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelular2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular2 not equals to DEFAULT_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldNotBeFound("casoemergenciacelular2.notEquals=" + DEFAULT_CASOEMERGENCIACELULAR_2);

        // Get all the personaList where casoemergenciacelular2 not equals to UPDATED_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldBeFound("casoemergenciacelular2.notEquals=" + UPDATED_CASOEMERGENCIACELULAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelular2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular2 in DEFAULT_CASOEMERGENCIACELULAR_2 or UPDATED_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldBeFound("casoemergenciacelular2.in=" + DEFAULT_CASOEMERGENCIACELULAR_2 + "," + UPDATED_CASOEMERGENCIACELULAR_2);

        // Get all the personaList where casoemergenciacelular2 equals to UPDATED_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldNotBeFound("casoemergenciacelular2.in=" + UPDATED_CASOEMERGENCIACELULAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelular2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular2 is not null
        defaultPersonaShouldBeFound("casoemergenciacelular2.specified=true");

        // Get all the personaList where casoemergenciacelular2 is null
        defaultPersonaShouldNotBeFound("casoemergenciacelular2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelular2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular2 contains DEFAULT_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldBeFound("casoemergenciacelular2.contains=" + DEFAULT_CASOEMERGENCIACELULAR_2);

        // Get all the personaList where casoemergenciacelular2 contains UPDATED_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldNotBeFound("casoemergenciacelular2.contains=" + UPDATED_CASOEMERGENCIACELULAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciacelular2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciacelular2 does not contain DEFAULT_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldNotBeFound("casoemergenciacelular2.doesNotContain=" + DEFAULT_CASOEMERGENCIACELULAR_2);

        // Get all the personaList where casoemergenciacelular2 does not contain UPDATED_CASOEMERGENCIACELULAR_2
        defaultPersonaShouldBeFound("casoemergenciacelular2.doesNotContain=" + UPDATED_CASOEMERGENCIACELULAR_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijo2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo2 equals to DEFAULT_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldBeFound("casoemergenciafijo2.equals=" + DEFAULT_CASOEMERGENCIAFIJO_2);

        // Get all the personaList where casoemergenciafijo2 equals to UPDATED_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldNotBeFound("casoemergenciafijo2.equals=" + UPDATED_CASOEMERGENCIAFIJO_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijo2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo2 not equals to DEFAULT_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldNotBeFound("casoemergenciafijo2.notEquals=" + DEFAULT_CASOEMERGENCIAFIJO_2);

        // Get all the personaList where casoemergenciafijo2 not equals to UPDATED_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldBeFound("casoemergenciafijo2.notEquals=" + UPDATED_CASOEMERGENCIAFIJO_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijo2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo2 in DEFAULT_CASOEMERGENCIAFIJO_2 or UPDATED_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldBeFound("casoemergenciafijo2.in=" + DEFAULT_CASOEMERGENCIAFIJO_2 + "," + UPDATED_CASOEMERGENCIAFIJO_2);

        // Get all the personaList where casoemergenciafijo2 equals to UPDATED_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldNotBeFound("casoemergenciafijo2.in=" + UPDATED_CASOEMERGENCIAFIJO_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijo2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo2 is not null
        defaultPersonaShouldBeFound("casoemergenciafijo2.specified=true");

        // Get all the personaList where casoemergenciafijo2 is null
        defaultPersonaShouldNotBeFound("casoemergenciafijo2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijo2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo2 contains DEFAULT_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldBeFound("casoemergenciafijo2.contains=" + DEFAULT_CASOEMERGENCIAFIJO_2);

        // Get all the personaList where casoemergenciafijo2 contains UPDATED_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldNotBeFound("casoemergenciafijo2.contains=" + UPDATED_CASOEMERGENCIAFIJO_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergenciafijo2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergenciafijo2 does not contain DEFAULT_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldNotBeFound("casoemergenciafijo2.doesNotContain=" + DEFAULT_CASOEMERGENCIAFIJO_2);

        // Get all the personaList where casoemergenciafijo2 does not contain UPDATED_CASOEMERGENCIAFIJO_2
        defaultPersonaShouldBeFound("casoemergenciafijo2.doesNotContain=" + UPDATED_CASOEMERGENCIAFIJO_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombre2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre2 equals to DEFAULT_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldBeFound("casoemergencianombre2.equals=" + DEFAULT_CASOEMERGENCIANOMBRE_2);

        // Get all the personaList where casoemergencianombre2 equals to UPDATED_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldNotBeFound("casoemergencianombre2.equals=" + UPDATED_CASOEMERGENCIANOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombre2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre2 not equals to DEFAULT_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldNotBeFound("casoemergencianombre2.notEquals=" + DEFAULT_CASOEMERGENCIANOMBRE_2);

        // Get all the personaList where casoemergencianombre2 not equals to UPDATED_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldBeFound("casoemergencianombre2.notEquals=" + UPDATED_CASOEMERGENCIANOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombre2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre2 in DEFAULT_CASOEMERGENCIANOMBRE_2 or UPDATED_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldBeFound("casoemergencianombre2.in=" + DEFAULT_CASOEMERGENCIANOMBRE_2 + "," + UPDATED_CASOEMERGENCIANOMBRE_2);

        // Get all the personaList where casoemergencianombre2 equals to UPDATED_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldNotBeFound("casoemergencianombre2.in=" + UPDATED_CASOEMERGENCIANOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombre2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre2 is not null
        defaultPersonaShouldBeFound("casoemergencianombre2.specified=true");

        // Get all the personaList where casoemergencianombre2 is null
        defaultPersonaShouldNotBeFound("casoemergencianombre2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombre2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre2 contains DEFAULT_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldBeFound("casoemergencianombre2.contains=" + DEFAULT_CASOEMERGENCIANOMBRE_2);

        // Get all the personaList where casoemergencianombre2 contains UPDATED_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldNotBeFound("casoemergencianombre2.contains=" + UPDATED_CASOEMERGENCIANOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByCasoemergencianombre2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where casoemergencianombre2 does not contain DEFAULT_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldNotBeFound("casoemergencianombre2.doesNotContain=" + DEFAULT_CASOEMERGENCIANOMBRE_2);

        // Get all the personaList where casoemergencianombre2 does not contain UPDATED_CASOEMERGENCIANOMBRE_2
        defaultPersonaShouldBeFound("casoemergencianombre2.doesNotContain=" + UPDATED_CASOEMERGENCIANOMBRE_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombreIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre equals to DEFAULT_FAMILIARACARGONOMBRE
        defaultPersonaShouldBeFound("familiaracargonombre.equals=" + DEFAULT_FAMILIARACARGONOMBRE);

        // Get all the personaList where familiaracargonombre equals to UPDATED_FAMILIARACARGONOMBRE
        defaultPersonaShouldNotBeFound("familiaracargonombre.equals=" + UPDATED_FAMILIARACARGONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre not equals to DEFAULT_FAMILIARACARGONOMBRE
        defaultPersonaShouldNotBeFound("familiaracargonombre.notEquals=" + DEFAULT_FAMILIARACARGONOMBRE);

        // Get all the personaList where familiaracargonombre not equals to UPDATED_FAMILIARACARGONOMBRE
        defaultPersonaShouldBeFound("familiaracargonombre.notEquals=" + UPDATED_FAMILIARACARGONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombreIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre in DEFAULT_FAMILIARACARGONOMBRE or UPDATED_FAMILIARACARGONOMBRE
        defaultPersonaShouldBeFound("familiaracargonombre.in=" + DEFAULT_FAMILIARACARGONOMBRE + "," + UPDATED_FAMILIARACARGONOMBRE);

        // Get all the personaList where familiaracargonombre equals to UPDATED_FAMILIARACARGONOMBRE
        defaultPersonaShouldNotBeFound("familiaracargonombre.in=" + UPDATED_FAMILIARACARGONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre is not null
        defaultPersonaShouldBeFound("familiaracargonombre.specified=true");

        // Get all the personaList where familiaracargonombre is null
        defaultPersonaShouldNotBeFound("familiaracargonombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombreContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre contains DEFAULT_FAMILIARACARGONOMBRE
        defaultPersonaShouldBeFound("familiaracargonombre.contains=" + DEFAULT_FAMILIARACARGONOMBRE);

        // Get all the personaList where familiaracargonombre contains UPDATED_FAMILIARACARGONOMBRE
        defaultPersonaShouldNotBeFound("familiaracargonombre.contains=" + UPDATED_FAMILIARACARGONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombreNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre does not contain DEFAULT_FAMILIARACARGONOMBRE
        defaultPersonaShouldNotBeFound("familiaracargonombre.doesNotContain=" + DEFAULT_FAMILIARACARGONOMBRE);

        // Get all the personaList where familiaracargonombre does not contain UPDATED_FAMILIARACARGONOMBRE
        defaultPersonaShouldBeFound("familiaracargonombre.doesNotContain=" + UPDATED_FAMILIARACARGONOMBRE);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre2 equals to DEFAULT_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldBeFound("familiaracargonombre2.equals=" + DEFAULT_FAMILIARACARGONOMBRE_2);

        // Get all the personaList where familiaracargonombre2 equals to UPDATED_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldNotBeFound("familiaracargonombre2.equals=" + UPDATED_FAMILIARACARGONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre2 not equals to DEFAULT_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldNotBeFound("familiaracargonombre2.notEquals=" + DEFAULT_FAMILIARACARGONOMBRE_2);

        // Get all the personaList where familiaracargonombre2 not equals to UPDATED_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldBeFound("familiaracargonombre2.notEquals=" + UPDATED_FAMILIARACARGONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre2 in DEFAULT_FAMILIARACARGONOMBRE_2 or UPDATED_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldBeFound("familiaracargonombre2.in=" + DEFAULT_FAMILIARACARGONOMBRE_2 + "," + UPDATED_FAMILIARACARGONOMBRE_2);

        // Get all the personaList where familiaracargonombre2 equals to UPDATED_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldNotBeFound("familiaracargonombre2.in=" + UPDATED_FAMILIARACARGONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre2 is not null
        defaultPersonaShouldBeFound("familiaracargonombre2.specified=true");

        // Get all the personaList where familiaracargonombre2 is null
        defaultPersonaShouldNotBeFound("familiaracargonombre2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre2 contains DEFAULT_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldBeFound("familiaracargonombre2.contains=" + DEFAULT_FAMILIARACARGONOMBRE_2);

        // Get all the personaList where familiaracargonombre2 contains UPDATED_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldNotBeFound("familiaracargonombre2.contains=" + UPDATED_FAMILIARACARGONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre2 does not contain DEFAULT_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldNotBeFound("familiaracargonombre2.doesNotContain=" + DEFAULT_FAMILIARACARGONOMBRE_2);

        // Get all the personaList where familiaracargonombre2 does not contain UPDATED_FAMILIARACARGONOMBRE_2
        defaultPersonaShouldBeFound("familiaracargonombre2.doesNotContain=" + UPDATED_FAMILIARACARGONOMBRE_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre3IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre3 equals to DEFAULT_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldBeFound("familiaracargonombre3.equals=" + DEFAULT_FAMILIARACARGONOMBRE_3);

        // Get all the personaList where familiaracargonombre3 equals to UPDATED_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldNotBeFound("familiaracargonombre3.equals=" + UPDATED_FAMILIARACARGONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre3 not equals to DEFAULT_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldNotBeFound("familiaracargonombre3.notEquals=" + DEFAULT_FAMILIARACARGONOMBRE_3);

        // Get all the personaList where familiaracargonombre3 not equals to UPDATED_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldBeFound("familiaracargonombre3.notEquals=" + UPDATED_FAMILIARACARGONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre3IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre3 in DEFAULT_FAMILIARACARGONOMBRE_3 or UPDATED_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldBeFound("familiaracargonombre3.in=" + DEFAULT_FAMILIARACARGONOMBRE_3 + "," + UPDATED_FAMILIARACARGONOMBRE_3);

        // Get all the personaList where familiaracargonombre3 equals to UPDATED_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldNotBeFound("familiaracargonombre3.in=" + UPDATED_FAMILIARACARGONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre3IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre3 is not null
        defaultPersonaShouldBeFound("familiaracargonombre3.specified=true");

        // Get all the personaList where familiaracargonombre3 is null
        defaultPersonaShouldNotBeFound("familiaracargonombre3.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre3ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre3 contains DEFAULT_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldBeFound("familiaracargonombre3.contains=" + DEFAULT_FAMILIARACARGONOMBRE_3);

        // Get all the personaList where familiaracargonombre3 contains UPDATED_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldNotBeFound("familiaracargonombre3.contains=" + UPDATED_FAMILIARACARGONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre3NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre3 does not contain DEFAULT_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldNotBeFound("familiaracargonombre3.doesNotContain=" + DEFAULT_FAMILIARACARGONOMBRE_3);

        // Get all the personaList where familiaracargonombre3 does not contain UPDATED_FAMILIARACARGONOMBRE_3
        defaultPersonaShouldBeFound("familiaracargonombre3.doesNotContain=" + UPDATED_FAMILIARACARGONOMBRE_3);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre4IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre4 equals to DEFAULT_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldBeFound("familiaracargonombre4.equals=" + DEFAULT_FAMILIARACARGONOMBRE_4);

        // Get all the personaList where familiaracargonombre4 equals to UPDATED_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldNotBeFound("familiaracargonombre4.equals=" + UPDATED_FAMILIARACARGONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre4 not equals to DEFAULT_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldNotBeFound("familiaracargonombre4.notEquals=" + DEFAULT_FAMILIARACARGONOMBRE_4);

        // Get all the personaList where familiaracargonombre4 not equals to UPDATED_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldBeFound("familiaracargonombre4.notEquals=" + UPDATED_FAMILIARACARGONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre4IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre4 in DEFAULT_FAMILIARACARGONOMBRE_4 or UPDATED_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldBeFound("familiaracargonombre4.in=" + DEFAULT_FAMILIARACARGONOMBRE_4 + "," + UPDATED_FAMILIARACARGONOMBRE_4);

        // Get all the personaList where familiaracargonombre4 equals to UPDATED_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldNotBeFound("familiaracargonombre4.in=" + UPDATED_FAMILIARACARGONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre4IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre4 is not null
        defaultPersonaShouldBeFound("familiaracargonombre4.specified=true");

        // Get all the personaList where familiaracargonombre4 is null
        defaultPersonaShouldNotBeFound("familiaracargonombre4.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre4ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre4 contains DEFAULT_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldBeFound("familiaracargonombre4.contains=" + DEFAULT_FAMILIARACARGONOMBRE_4);

        // Get all the personaList where familiaracargonombre4 contains UPDATED_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldNotBeFound("familiaracargonombre4.contains=" + UPDATED_FAMILIARACARGONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre4NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre4 does not contain DEFAULT_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldNotBeFound("familiaracargonombre4.doesNotContain=" + DEFAULT_FAMILIARACARGONOMBRE_4);

        // Get all the personaList where familiaracargonombre4 does not contain UPDATED_FAMILIARACARGONOMBRE_4
        defaultPersonaShouldBeFound("familiaracargonombre4.doesNotContain=" + UPDATED_FAMILIARACARGONOMBRE_4);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre5IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre5 equals to DEFAULT_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldBeFound("familiaracargonombre5.equals=" + DEFAULT_FAMILIARACARGONOMBRE_5);

        // Get all the personaList where familiaracargonombre5 equals to UPDATED_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldNotBeFound("familiaracargonombre5.equals=" + UPDATED_FAMILIARACARGONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre5 not equals to DEFAULT_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldNotBeFound("familiaracargonombre5.notEquals=" + DEFAULT_FAMILIARACARGONOMBRE_5);

        // Get all the personaList where familiaracargonombre5 not equals to UPDATED_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldBeFound("familiaracargonombre5.notEquals=" + UPDATED_FAMILIARACARGONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre5IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre5 in DEFAULT_FAMILIARACARGONOMBRE_5 or UPDATED_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldBeFound("familiaracargonombre5.in=" + DEFAULT_FAMILIARACARGONOMBRE_5 + "," + UPDATED_FAMILIARACARGONOMBRE_5);

        // Get all the personaList where familiaracargonombre5 equals to UPDATED_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldNotBeFound("familiaracargonombre5.in=" + UPDATED_FAMILIARACARGONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre5IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre5 is not null
        defaultPersonaShouldBeFound("familiaracargonombre5.specified=true");

        // Get all the personaList where familiaracargonombre5 is null
        defaultPersonaShouldNotBeFound("familiaracargonombre5.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre5ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre5 contains DEFAULT_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldBeFound("familiaracargonombre5.contains=" + DEFAULT_FAMILIARACARGONOMBRE_5);

        // Get all the personaList where familiaracargonombre5 contains UPDATED_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldNotBeFound("familiaracargonombre5.contains=" + UPDATED_FAMILIARACARGONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargonombre5NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargonombre5 does not contain DEFAULT_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldNotBeFound("familiaracargonombre5.doesNotContain=" + DEFAULT_FAMILIARACARGONOMBRE_5);

        // Get all the personaList where familiaracargonombre5 does not contain UPDATED_FAMILIARACARGONOMBRE_5
        defaultPersonaShouldBeFound("familiaracargonombre5.doesNotContain=" + UPDATED_FAMILIARACARGONOMBRE_5);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodniIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni equals to DEFAULT_FAMILIARACARGODNI
        defaultPersonaShouldBeFound("familiaracargodni.equals=" + DEFAULT_FAMILIARACARGODNI);

        // Get all the personaList where familiaracargodni equals to UPDATED_FAMILIARACARGODNI
        defaultPersonaShouldNotBeFound("familiaracargodni.equals=" + UPDATED_FAMILIARACARGODNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodniIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni not equals to DEFAULT_FAMILIARACARGODNI
        defaultPersonaShouldNotBeFound("familiaracargodni.notEquals=" + DEFAULT_FAMILIARACARGODNI);

        // Get all the personaList where familiaracargodni not equals to UPDATED_FAMILIARACARGODNI
        defaultPersonaShouldBeFound("familiaracargodni.notEquals=" + UPDATED_FAMILIARACARGODNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodniIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni in DEFAULT_FAMILIARACARGODNI or UPDATED_FAMILIARACARGODNI
        defaultPersonaShouldBeFound("familiaracargodni.in=" + DEFAULT_FAMILIARACARGODNI + "," + UPDATED_FAMILIARACARGODNI);

        // Get all the personaList where familiaracargodni equals to UPDATED_FAMILIARACARGODNI
        defaultPersonaShouldNotBeFound("familiaracargodni.in=" + UPDATED_FAMILIARACARGODNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodniIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni is not null
        defaultPersonaShouldBeFound("familiaracargodni.specified=true");

        // Get all the personaList where familiaracargodni is null
        defaultPersonaShouldNotBeFound("familiaracargodni.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodniContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni contains DEFAULT_FAMILIARACARGODNI
        defaultPersonaShouldBeFound("familiaracargodni.contains=" + DEFAULT_FAMILIARACARGODNI);

        // Get all the personaList where familiaracargodni contains UPDATED_FAMILIARACARGODNI
        defaultPersonaShouldNotBeFound("familiaracargodni.contains=" + UPDATED_FAMILIARACARGODNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodniNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni does not contain DEFAULT_FAMILIARACARGODNI
        defaultPersonaShouldNotBeFound("familiaracargodni.doesNotContain=" + DEFAULT_FAMILIARACARGODNI);

        // Get all the personaList where familiaracargodni does not contain UPDATED_FAMILIARACARGODNI
        defaultPersonaShouldBeFound("familiaracargodni.doesNotContain=" + UPDATED_FAMILIARACARGODNI);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni2 equals to DEFAULT_FAMILIARACARGODNI_2
        defaultPersonaShouldBeFound("familiaracargodni2.equals=" + DEFAULT_FAMILIARACARGODNI_2);

        // Get all the personaList where familiaracargodni2 equals to UPDATED_FAMILIARACARGODNI_2
        defaultPersonaShouldNotBeFound("familiaracargodni2.equals=" + UPDATED_FAMILIARACARGODNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni2 not equals to DEFAULT_FAMILIARACARGODNI_2
        defaultPersonaShouldNotBeFound("familiaracargodni2.notEquals=" + DEFAULT_FAMILIARACARGODNI_2);

        // Get all the personaList where familiaracargodni2 not equals to UPDATED_FAMILIARACARGODNI_2
        defaultPersonaShouldBeFound("familiaracargodni2.notEquals=" + UPDATED_FAMILIARACARGODNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni2 in DEFAULT_FAMILIARACARGODNI_2 or UPDATED_FAMILIARACARGODNI_2
        defaultPersonaShouldBeFound("familiaracargodni2.in=" + DEFAULT_FAMILIARACARGODNI_2 + "," + UPDATED_FAMILIARACARGODNI_2);

        // Get all the personaList where familiaracargodni2 equals to UPDATED_FAMILIARACARGODNI_2
        defaultPersonaShouldNotBeFound("familiaracargodni2.in=" + UPDATED_FAMILIARACARGODNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni2 is not null
        defaultPersonaShouldBeFound("familiaracargodni2.specified=true");

        // Get all the personaList where familiaracargodni2 is null
        defaultPersonaShouldNotBeFound("familiaracargodni2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni2 contains DEFAULT_FAMILIARACARGODNI_2
        defaultPersonaShouldBeFound("familiaracargodni2.contains=" + DEFAULT_FAMILIARACARGODNI_2);

        // Get all the personaList where familiaracargodni2 contains UPDATED_FAMILIARACARGODNI_2
        defaultPersonaShouldNotBeFound("familiaracargodni2.contains=" + UPDATED_FAMILIARACARGODNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni2 does not contain DEFAULT_FAMILIARACARGODNI_2
        defaultPersonaShouldNotBeFound("familiaracargodni2.doesNotContain=" + DEFAULT_FAMILIARACARGODNI_2);

        // Get all the personaList where familiaracargodni2 does not contain UPDATED_FAMILIARACARGODNI_2
        defaultPersonaShouldBeFound("familiaracargodni2.doesNotContain=" + UPDATED_FAMILIARACARGODNI_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni3IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni3 equals to DEFAULT_FAMILIARACARGODNI_3
        defaultPersonaShouldBeFound("familiaracargodni3.equals=" + DEFAULT_FAMILIARACARGODNI_3);

        // Get all the personaList where familiaracargodni3 equals to UPDATED_FAMILIARACARGODNI_3
        defaultPersonaShouldNotBeFound("familiaracargodni3.equals=" + UPDATED_FAMILIARACARGODNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni3 not equals to DEFAULT_FAMILIARACARGODNI_3
        defaultPersonaShouldNotBeFound("familiaracargodni3.notEquals=" + DEFAULT_FAMILIARACARGODNI_3);

        // Get all the personaList where familiaracargodni3 not equals to UPDATED_FAMILIARACARGODNI_3
        defaultPersonaShouldBeFound("familiaracargodni3.notEquals=" + UPDATED_FAMILIARACARGODNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni3IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni3 in DEFAULT_FAMILIARACARGODNI_3 or UPDATED_FAMILIARACARGODNI_3
        defaultPersonaShouldBeFound("familiaracargodni3.in=" + DEFAULT_FAMILIARACARGODNI_3 + "," + UPDATED_FAMILIARACARGODNI_3);

        // Get all the personaList where familiaracargodni3 equals to UPDATED_FAMILIARACARGODNI_3
        defaultPersonaShouldNotBeFound("familiaracargodni3.in=" + UPDATED_FAMILIARACARGODNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni3IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni3 is not null
        defaultPersonaShouldBeFound("familiaracargodni3.specified=true");

        // Get all the personaList where familiaracargodni3 is null
        defaultPersonaShouldNotBeFound("familiaracargodni3.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni3ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni3 contains DEFAULT_FAMILIARACARGODNI_3
        defaultPersonaShouldBeFound("familiaracargodni3.contains=" + DEFAULT_FAMILIARACARGODNI_3);

        // Get all the personaList where familiaracargodni3 contains UPDATED_FAMILIARACARGODNI_3
        defaultPersonaShouldNotBeFound("familiaracargodni3.contains=" + UPDATED_FAMILIARACARGODNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni3NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni3 does not contain DEFAULT_FAMILIARACARGODNI_3
        defaultPersonaShouldNotBeFound("familiaracargodni3.doesNotContain=" + DEFAULT_FAMILIARACARGODNI_3);

        // Get all the personaList where familiaracargodni3 does not contain UPDATED_FAMILIARACARGODNI_3
        defaultPersonaShouldBeFound("familiaracargodni3.doesNotContain=" + UPDATED_FAMILIARACARGODNI_3);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni4IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni4 equals to DEFAULT_FAMILIARACARGODNI_4
        defaultPersonaShouldBeFound("familiaracargodni4.equals=" + DEFAULT_FAMILIARACARGODNI_4);

        // Get all the personaList where familiaracargodni4 equals to UPDATED_FAMILIARACARGODNI_4
        defaultPersonaShouldNotBeFound("familiaracargodni4.equals=" + UPDATED_FAMILIARACARGODNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni4 not equals to DEFAULT_FAMILIARACARGODNI_4
        defaultPersonaShouldNotBeFound("familiaracargodni4.notEquals=" + DEFAULT_FAMILIARACARGODNI_4);

        // Get all the personaList where familiaracargodni4 not equals to UPDATED_FAMILIARACARGODNI_4
        defaultPersonaShouldBeFound("familiaracargodni4.notEquals=" + UPDATED_FAMILIARACARGODNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni4IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni4 in DEFAULT_FAMILIARACARGODNI_4 or UPDATED_FAMILIARACARGODNI_4
        defaultPersonaShouldBeFound("familiaracargodni4.in=" + DEFAULT_FAMILIARACARGODNI_4 + "," + UPDATED_FAMILIARACARGODNI_4);

        // Get all the personaList where familiaracargodni4 equals to UPDATED_FAMILIARACARGODNI_4
        defaultPersonaShouldNotBeFound("familiaracargodni4.in=" + UPDATED_FAMILIARACARGODNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni4IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni4 is not null
        defaultPersonaShouldBeFound("familiaracargodni4.specified=true");

        // Get all the personaList where familiaracargodni4 is null
        defaultPersonaShouldNotBeFound("familiaracargodni4.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni4ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni4 contains DEFAULT_FAMILIARACARGODNI_4
        defaultPersonaShouldBeFound("familiaracargodni4.contains=" + DEFAULT_FAMILIARACARGODNI_4);

        // Get all the personaList where familiaracargodni4 contains UPDATED_FAMILIARACARGODNI_4
        defaultPersonaShouldNotBeFound("familiaracargodni4.contains=" + UPDATED_FAMILIARACARGODNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni4NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni4 does not contain DEFAULT_FAMILIARACARGODNI_4
        defaultPersonaShouldNotBeFound("familiaracargodni4.doesNotContain=" + DEFAULT_FAMILIARACARGODNI_4);

        // Get all the personaList where familiaracargodni4 does not contain UPDATED_FAMILIARACARGODNI_4
        defaultPersonaShouldBeFound("familiaracargodni4.doesNotContain=" + UPDATED_FAMILIARACARGODNI_4);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni5IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni5 equals to DEFAULT_FAMILIARACARGODNI_5
        defaultPersonaShouldBeFound("familiaracargodni5.equals=" + DEFAULT_FAMILIARACARGODNI_5);

        // Get all the personaList where familiaracargodni5 equals to UPDATED_FAMILIARACARGODNI_5
        defaultPersonaShouldNotBeFound("familiaracargodni5.equals=" + UPDATED_FAMILIARACARGODNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni5 not equals to DEFAULT_FAMILIARACARGODNI_5
        defaultPersonaShouldNotBeFound("familiaracargodni5.notEquals=" + DEFAULT_FAMILIARACARGODNI_5);

        // Get all the personaList where familiaracargodni5 not equals to UPDATED_FAMILIARACARGODNI_5
        defaultPersonaShouldBeFound("familiaracargodni5.notEquals=" + UPDATED_FAMILIARACARGODNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni5IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni5 in DEFAULT_FAMILIARACARGODNI_5 or UPDATED_FAMILIARACARGODNI_5
        defaultPersonaShouldBeFound("familiaracargodni5.in=" + DEFAULT_FAMILIARACARGODNI_5 + "," + UPDATED_FAMILIARACARGODNI_5);

        // Get all the personaList where familiaracargodni5 equals to UPDATED_FAMILIARACARGODNI_5
        defaultPersonaShouldNotBeFound("familiaracargodni5.in=" + UPDATED_FAMILIARACARGODNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni5IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni5 is not null
        defaultPersonaShouldBeFound("familiaracargodni5.specified=true");

        // Get all the personaList where familiaracargodni5 is null
        defaultPersonaShouldNotBeFound("familiaracargodni5.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni5ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni5 contains DEFAULT_FAMILIARACARGODNI_5
        defaultPersonaShouldBeFound("familiaracargodni5.contains=" + DEFAULT_FAMILIARACARGODNI_5);

        // Get all the personaList where familiaracargodni5 contains UPDATED_FAMILIARACARGODNI_5
        defaultPersonaShouldNotBeFound("familiaracargodni5.contains=" + UPDATED_FAMILIARACARGODNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargodni5NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargodni5 does not contain DEFAULT_FAMILIARACARGODNI_5
        defaultPersonaShouldNotBeFound("familiaracargodni5.doesNotContain=" + DEFAULT_FAMILIARACARGODNI_5);

        // Get all the personaList where familiaracargodni5 does not contain UPDATED_FAMILIARACARGODNI_5
        defaultPersonaShouldBeFound("familiaracargodni5.doesNotContain=" + UPDATED_FAMILIARACARGODNI_5);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedadIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad equals to DEFAULT_FAMILIARACARGOEDAD
        defaultPersonaShouldBeFound("familiaracargoedad.equals=" + DEFAULT_FAMILIARACARGOEDAD);

        // Get all the personaList where familiaracargoedad equals to UPDATED_FAMILIARACARGOEDAD
        defaultPersonaShouldNotBeFound("familiaracargoedad.equals=" + UPDATED_FAMILIARACARGOEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad not equals to DEFAULT_FAMILIARACARGOEDAD
        defaultPersonaShouldNotBeFound("familiaracargoedad.notEquals=" + DEFAULT_FAMILIARACARGOEDAD);

        // Get all the personaList where familiaracargoedad not equals to UPDATED_FAMILIARACARGOEDAD
        defaultPersonaShouldBeFound("familiaracargoedad.notEquals=" + UPDATED_FAMILIARACARGOEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedadIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad in DEFAULT_FAMILIARACARGOEDAD or UPDATED_FAMILIARACARGOEDAD
        defaultPersonaShouldBeFound("familiaracargoedad.in=" + DEFAULT_FAMILIARACARGOEDAD + "," + UPDATED_FAMILIARACARGOEDAD);

        // Get all the personaList where familiaracargoedad equals to UPDATED_FAMILIARACARGOEDAD
        defaultPersonaShouldNotBeFound("familiaracargoedad.in=" + UPDATED_FAMILIARACARGOEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedadIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad is not null
        defaultPersonaShouldBeFound("familiaracargoedad.specified=true");

        // Get all the personaList where familiaracargoedad is null
        defaultPersonaShouldNotBeFound("familiaracargoedad.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedadContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad contains DEFAULT_FAMILIARACARGOEDAD
        defaultPersonaShouldBeFound("familiaracargoedad.contains=" + DEFAULT_FAMILIARACARGOEDAD);

        // Get all the personaList where familiaracargoedad contains UPDATED_FAMILIARACARGOEDAD
        defaultPersonaShouldNotBeFound("familiaracargoedad.contains=" + UPDATED_FAMILIARACARGOEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedadNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad does not contain DEFAULT_FAMILIARACARGOEDAD
        defaultPersonaShouldNotBeFound("familiaracargoedad.doesNotContain=" + DEFAULT_FAMILIARACARGOEDAD);

        // Get all the personaList where familiaracargoedad does not contain UPDATED_FAMILIARACARGOEDAD
        defaultPersonaShouldBeFound("familiaracargoedad.doesNotContain=" + UPDATED_FAMILIARACARGOEDAD);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad2 equals to DEFAULT_FAMILIARACARGOEDAD_2
        defaultPersonaShouldBeFound("familiaracargoedad2.equals=" + DEFAULT_FAMILIARACARGOEDAD_2);

        // Get all the personaList where familiaracargoedad2 equals to UPDATED_FAMILIARACARGOEDAD_2
        defaultPersonaShouldNotBeFound("familiaracargoedad2.equals=" + UPDATED_FAMILIARACARGOEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad2 not equals to DEFAULT_FAMILIARACARGOEDAD_2
        defaultPersonaShouldNotBeFound("familiaracargoedad2.notEquals=" + DEFAULT_FAMILIARACARGOEDAD_2);

        // Get all the personaList where familiaracargoedad2 not equals to UPDATED_FAMILIARACARGOEDAD_2
        defaultPersonaShouldBeFound("familiaracargoedad2.notEquals=" + UPDATED_FAMILIARACARGOEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad2 in DEFAULT_FAMILIARACARGOEDAD_2 or UPDATED_FAMILIARACARGOEDAD_2
        defaultPersonaShouldBeFound("familiaracargoedad2.in=" + DEFAULT_FAMILIARACARGOEDAD_2 + "," + UPDATED_FAMILIARACARGOEDAD_2);

        // Get all the personaList where familiaracargoedad2 equals to UPDATED_FAMILIARACARGOEDAD_2
        defaultPersonaShouldNotBeFound("familiaracargoedad2.in=" + UPDATED_FAMILIARACARGOEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad2 is not null
        defaultPersonaShouldBeFound("familiaracargoedad2.specified=true");

        // Get all the personaList where familiaracargoedad2 is null
        defaultPersonaShouldNotBeFound("familiaracargoedad2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad2 contains DEFAULT_FAMILIARACARGOEDAD_2
        defaultPersonaShouldBeFound("familiaracargoedad2.contains=" + DEFAULT_FAMILIARACARGOEDAD_2);

        // Get all the personaList where familiaracargoedad2 contains UPDATED_FAMILIARACARGOEDAD_2
        defaultPersonaShouldNotBeFound("familiaracargoedad2.contains=" + UPDATED_FAMILIARACARGOEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad2 does not contain DEFAULT_FAMILIARACARGOEDAD_2
        defaultPersonaShouldNotBeFound("familiaracargoedad2.doesNotContain=" + DEFAULT_FAMILIARACARGOEDAD_2);

        // Get all the personaList where familiaracargoedad2 does not contain UPDATED_FAMILIARACARGOEDAD_2
        defaultPersonaShouldBeFound("familiaracargoedad2.doesNotContain=" + UPDATED_FAMILIARACARGOEDAD_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad3IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad3 equals to DEFAULT_FAMILIARACARGOEDAD_3
        defaultPersonaShouldBeFound("familiaracargoedad3.equals=" + DEFAULT_FAMILIARACARGOEDAD_3);

        // Get all the personaList where familiaracargoedad3 equals to UPDATED_FAMILIARACARGOEDAD_3
        defaultPersonaShouldNotBeFound("familiaracargoedad3.equals=" + UPDATED_FAMILIARACARGOEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad3 not equals to DEFAULT_FAMILIARACARGOEDAD_3
        defaultPersonaShouldNotBeFound("familiaracargoedad3.notEquals=" + DEFAULT_FAMILIARACARGOEDAD_3);

        // Get all the personaList where familiaracargoedad3 not equals to UPDATED_FAMILIARACARGOEDAD_3
        defaultPersonaShouldBeFound("familiaracargoedad3.notEquals=" + UPDATED_FAMILIARACARGOEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad3IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad3 in DEFAULT_FAMILIARACARGOEDAD_3 or UPDATED_FAMILIARACARGOEDAD_3
        defaultPersonaShouldBeFound("familiaracargoedad3.in=" + DEFAULT_FAMILIARACARGOEDAD_3 + "," + UPDATED_FAMILIARACARGOEDAD_3);

        // Get all the personaList where familiaracargoedad3 equals to UPDATED_FAMILIARACARGOEDAD_3
        defaultPersonaShouldNotBeFound("familiaracargoedad3.in=" + UPDATED_FAMILIARACARGOEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad3IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad3 is not null
        defaultPersonaShouldBeFound("familiaracargoedad3.specified=true");

        // Get all the personaList where familiaracargoedad3 is null
        defaultPersonaShouldNotBeFound("familiaracargoedad3.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad3ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad3 contains DEFAULT_FAMILIARACARGOEDAD_3
        defaultPersonaShouldBeFound("familiaracargoedad3.contains=" + DEFAULT_FAMILIARACARGOEDAD_3);

        // Get all the personaList where familiaracargoedad3 contains UPDATED_FAMILIARACARGOEDAD_3
        defaultPersonaShouldNotBeFound("familiaracargoedad3.contains=" + UPDATED_FAMILIARACARGOEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad3NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad3 does not contain DEFAULT_FAMILIARACARGOEDAD_3
        defaultPersonaShouldNotBeFound("familiaracargoedad3.doesNotContain=" + DEFAULT_FAMILIARACARGOEDAD_3);

        // Get all the personaList where familiaracargoedad3 does not contain UPDATED_FAMILIARACARGOEDAD_3
        defaultPersonaShouldBeFound("familiaracargoedad3.doesNotContain=" + UPDATED_FAMILIARACARGOEDAD_3);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad4IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad4 equals to DEFAULT_FAMILIARACARGOEDAD_4
        defaultPersonaShouldBeFound("familiaracargoedad4.equals=" + DEFAULT_FAMILIARACARGOEDAD_4);

        // Get all the personaList where familiaracargoedad4 equals to UPDATED_FAMILIARACARGOEDAD_4
        defaultPersonaShouldNotBeFound("familiaracargoedad4.equals=" + UPDATED_FAMILIARACARGOEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad4 not equals to DEFAULT_FAMILIARACARGOEDAD_4
        defaultPersonaShouldNotBeFound("familiaracargoedad4.notEquals=" + DEFAULT_FAMILIARACARGOEDAD_4);

        // Get all the personaList where familiaracargoedad4 not equals to UPDATED_FAMILIARACARGOEDAD_4
        defaultPersonaShouldBeFound("familiaracargoedad4.notEquals=" + UPDATED_FAMILIARACARGOEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad4IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad4 in DEFAULT_FAMILIARACARGOEDAD_4 or UPDATED_FAMILIARACARGOEDAD_4
        defaultPersonaShouldBeFound("familiaracargoedad4.in=" + DEFAULT_FAMILIARACARGOEDAD_4 + "," + UPDATED_FAMILIARACARGOEDAD_4);

        // Get all the personaList where familiaracargoedad4 equals to UPDATED_FAMILIARACARGOEDAD_4
        defaultPersonaShouldNotBeFound("familiaracargoedad4.in=" + UPDATED_FAMILIARACARGOEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad4IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad4 is not null
        defaultPersonaShouldBeFound("familiaracargoedad4.specified=true");

        // Get all the personaList where familiaracargoedad4 is null
        defaultPersonaShouldNotBeFound("familiaracargoedad4.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad4ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad4 contains DEFAULT_FAMILIARACARGOEDAD_4
        defaultPersonaShouldBeFound("familiaracargoedad4.contains=" + DEFAULT_FAMILIARACARGOEDAD_4);

        // Get all the personaList where familiaracargoedad4 contains UPDATED_FAMILIARACARGOEDAD_4
        defaultPersonaShouldNotBeFound("familiaracargoedad4.contains=" + UPDATED_FAMILIARACARGOEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad4NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad4 does not contain DEFAULT_FAMILIARACARGOEDAD_4
        defaultPersonaShouldNotBeFound("familiaracargoedad4.doesNotContain=" + DEFAULT_FAMILIARACARGOEDAD_4);

        // Get all the personaList where familiaracargoedad4 does not contain UPDATED_FAMILIARACARGOEDAD_4
        defaultPersonaShouldBeFound("familiaracargoedad4.doesNotContain=" + UPDATED_FAMILIARACARGOEDAD_4);
    }


    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad5IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad5 equals to DEFAULT_FAMILIARACARGOEDAD_5
        defaultPersonaShouldBeFound("familiaracargoedad5.equals=" + DEFAULT_FAMILIARACARGOEDAD_5);

        // Get all the personaList where familiaracargoedad5 equals to UPDATED_FAMILIARACARGOEDAD_5
        defaultPersonaShouldNotBeFound("familiaracargoedad5.equals=" + UPDATED_FAMILIARACARGOEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad5 not equals to DEFAULT_FAMILIARACARGOEDAD_5
        defaultPersonaShouldNotBeFound("familiaracargoedad5.notEquals=" + DEFAULT_FAMILIARACARGOEDAD_5);

        // Get all the personaList where familiaracargoedad5 not equals to UPDATED_FAMILIARACARGOEDAD_5
        defaultPersonaShouldBeFound("familiaracargoedad5.notEquals=" + UPDATED_FAMILIARACARGOEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad5IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad5 in DEFAULT_FAMILIARACARGOEDAD_5 or UPDATED_FAMILIARACARGOEDAD_5
        defaultPersonaShouldBeFound("familiaracargoedad5.in=" + DEFAULT_FAMILIARACARGOEDAD_5 + "," + UPDATED_FAMILIARACARGOEDAD_5);

        // Get all the personaList where familiaracargoedad5 equals to UPDATED_FAMILIARACARGOEDAD_5
        defaultPersonaShouldNotBeFound("familiaracargoedad5.in=" + UPDATED_FAMILIARACARGOEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad5IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad5 is not null
        defaultPersonaShouldBeFound("familiaracargoedad5.specified=true");

        // Get all the personaList where familiaracargoedad5 is null
        defaultPersonaShouldNotBeFound("familiaracargoedad5.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad5ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad5 contains DEFAULT_FAMILIARACARGOEDAD_5
        defaultPersonaShouldBeFound("familiaracargoedad5.contains=" + DEFAULT_FAMILIARACARGOEDAD_5);

        // Get all the personaList where familiaracargoedad5 contains UPDATED_FAMILIARACARGOEDAD_5
        defaultPersonaShouldNotBeFound("familiaracargoedad5.contains=" + UPDATED_FAMILIARACARGOEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByFamiliaracargoedad5NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where familiaracargoedad5 does not contain DEFAULT_FAMILIARACARGOEDAD_5
        defaultPersonaShouldNotBeFound("familiaracargoedad5.doesNotContain=" + DEFAULT_FAMILIARACARGOEDAD_5);

        // Get all the personaList where familiaracargoedad5 does not contain UPDATED_FAMILIARACARGOEDAD_5
        defaultPersonaShouldBeFound("familiaracargoedad5.doesNotContain=" + UPDATED_FAMILIARACARGOEDAD_5);
    }


    @Test
    @Transactional
    public void getAllPersonasByAlturaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where altura equals to DEFAULT_ALTURA
        defaultPersonaShouldBeFound("altura.equals=" + DEFAULT_ALTURA);

        // Get all the personaList where altura equals to UPDATED_ALTURA
        defaultPersonaShouldNotBeFound("altura.equals=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlturaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where altura not equals to DEFAULT_ALTURA
        defaultPersonaShouldNotBeFound("altura.notEquals=" + DEFAULT_ALTURA);

        // Get all the personaList where altura not equals to UPDATED_ALTURA
        defaultPersonaShouldBeFound("altura.notEquals=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlturaIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where altura in DEFAULT_ALTURA or UPDATED_ALTURA
        defaultPersonaShouldBeFound("altura.in=" + DEFAULT_ALTURA + "," + UPDATED_ALTURA);

        // Get all the personaList where altura equals to UPDATED_ALTURA
        defaultPersonaShouldNotBeFound("altura.in=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlturaIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where altura is not null
        defaultPersonaShouldBeFound("altura.specified=true");

        // Get all the personaList where altura is null
        defaultPersonaShouldNotBeFound("altura.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByAlturaContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where altura contains DEFAULT_ALTURA
        defaultPersonaShouldBeFound("altura.contains=" + DEFAULT_ALTURA);

        // Get all the personaList where altura contains UPDATED_ALTURA
        defaultPersonaShouldNotBeFound("altura.contains=" + UPDATED_ALTURA);
    }

    @Test
    @Transactional
    public void getAllPersonasByAlturaNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where altura does not contain DEFAULT_ALTURA
        defaultPersonaShouldNotBeFound("altura.doesNotContain=" + DEFAULT_ALTURA);

        // Get all the personaList where altura does not contain UPDATED_ALTURA
        defaultPersonaShouldBeFound("altura.doesNotContain=" + UPDATED_ALTURA);
    }


    @Test
    @Transactional
    public void getAllPersonasByBarrioIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where barrio equals to DEFAULT_BARRIO
        defaultPersonaShouldBeFound("barrio.equals=" + DEFAULT_BARRIO);

        // Get all the personaList where barrio equals to UPDATED_BARRIO
        defaultPersonaShouldNotBeFound("barrio.equals=" + UPDATED_BARRIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByBarrioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where barrio not equals to DEFAULT_BARRIO
        defaultPersonaShouldNotBeFound("barrio.notEquals=" + DEFAULT_BARRIO);

        // Get all the personaList where barrio not equals to UPDATED_BARRIO
        defaultPersonaShouldBeFound("barrio.notEquals=" + UPDATED_BARRIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByBarrioIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where barrio in DEFAULT_BARRIO or UPDATED_BARRIO
        defaultPersonaShouldBeFound("barrio.in=" + DEFAULT_BARRIO + "," + UPDATED_BARRIO);

        // Get all the personaList where barrio equals to UPDATED_BARRIO
        defaultPersonaShouldNotBeFound("barrio.in=" + UPDATED_BARRIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByBarrioIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where barrio is not null
        defaultPersonaShouldBeFound("barrio.specified=true");

        // Get all the personaList where barrio is null
        defaultPersonaShouldNotBeFound("barrio.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByBarrioContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where barrio contains DEFAULT_BARRIO
        defaultPersonaShouldBeFound("barrio.contains=" + DEFAULT_BARRIO);

        // Get all the personaList where barrio contains UPDATED_BARRIO
        defaultPersonaShouldNotBeFound("barrio.contains=" + UPDATED_BARRIO);
    }

    @Test
    @Transactional
    public void getAllPersonasByBarrioNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where barrio does not contain DEFAULT_BARRIO
        defaultPersonaShouldNotBeFound("barrio.doesNotContain=" + DEFAULT_BARRIO);

        // Get all the personaList where barrio does not contain UPDATED_BARRIO
        defaultPersonaShouldBeFound("barrio.doesNotContain=" + UPDATED_BARRIO);
    }


    @Test
    @Transactional
    public void getAllPersonasByEstudiosincompletosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where estudiosincompletos equals to DEFAULT_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldBeFound("estudiosincompletos.equals=" + DEFAULT_ESTUDIOSINCOMPLETOS);

        // Get all the personaList where estudiosincompletos equals to UPDATED_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldNotBeFound("estudiosincompletos.equals=" + UPDATED_ESTUDIOSINCOMPLETOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByEstudiosincompletosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where estudiosincompletos not equals to DEFAULT_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldNotBeFound("estudiosincompletos.notEquals=" + DEFAULT_ESTUDIOSINCOMPLETOS);

        // Get all the personaList where estudiosincompletos not equals to UPDATED_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldBeFound("estudiosincompletos.notEquals=" + UPDATED_ESTUDIOSINCOMPLETOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByEstudiosincompletosIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where estudiosincompletos in DEFAULT_ESTUDIOSINCOMPLETOS or UPDATED_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldBeFound("estudiosincompletos.in=" + DEFAULT_ESTUDIOSINCOMPLETOS + "," + UPDATED_ESTUDIOSINCOMPLETOS);

        // Get all the personaList where estudiosincompletos equals to UPDATED_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldNotBeFound("estudiosincompletos.in=" + UPDATED_ESTUDIOSINCOMPLETOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByEstudiosincompletosIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where estudiosincompletos is not null
        defaultPersonaShouldBeFound("estudiosincompletos.specified=true");

        // Get all the personaList where estudiosincompletos is null
        defaultPersonaShouldNotBeFound("estudiosincompletos.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByEstudiosincompletosContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where estudiosincompletos contains DEFAULT_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldBeFound("estudiosincompletos.contains=" + DEFAULT_ESTUDIOSINCOMPLETOS);

        // Get all the personaList where estudiosincompletos contains UPDATED_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldNotBeFound("estudiosincompletos.contains=" + UPDATED_ESTUDIOSINCOMPLETOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByEstudiosincompletosNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where estudiosincompletos does not contain DEFAULT_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldNotBeFound("estudiosincompletos.doesNotContain=" + DEFAULT_ESTUDIOSINCOMPLETOS);

        // Get all the personaList where estudiosincompletos does not contain UPDATED_ESTUDIOSINCOMPLETOS
        defaultPersonaShouldBeFound("estudiosincompletos.doesNotContain=" + UPDATED_ESTUDIOSINCOMPLETOS);
    }


    @Test
    @Transactional
    public void getAllPersonasByConyugeapellidoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugeapellido equals to DEFAULT_CONYUGEAPELLIDO
        defaultPersonaShouldBeFound("conyugeapellido.equals=" + DEFAULT_CONYUGEAPELLIDO);

        // Get all the personaList where conyugeapellido equals to UPDATED_CONYUGEAPELLIDO
        defaultPersonaShouldNotBeFound("conyugeapellido.equals=" + UPDATED_CONYUGEAPELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugeapellidoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugeapellido not equals to DEFAULT_CONYUGEAPELLIDO
        defaultPersonaShouldNotBeFound("conyugeapellido.notEquals=" + DEFAULT_CONYUGEAPELLIDO);

        // Get all the personaList where conyugeapellido not equals to UPDATED_CONYUGEAPELLIDO
        defaultPersonaShouldBeFound("conyugeapellido.notEquals=" + UPDATED_CONYUGEAPELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugeapellidoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugeapellido in DEFAULT_CONYUGEAPELLIDO or UPDATED_CONYUGEAPELLIDO
        defaultPersonaShouldBeFound("conyugeapellido.in=" + DEFAULT_CONYUGEAPELLIDO + "," + UPDATED_CONYUGEAPELLIDO);

        // Get all the personaList where conyugeapellido equals to UPDATED_CONYUGEAPELLIDO
        defaultPersonaShouldNotBeFound("conyugeapellido.in=" + UPDATED_CONYUGEAPELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugeapellidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugeapellido is not null
        defaultPersonaShouldBeFound("conyugeapellido.specified=true");

        // Get all the personaList where conyugeapellido is null
        defaultPersonaShouldNotBeFound("conyugeapellido.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByConyugeapellidoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugeapellido contains DEFAULT_CONYUGEAPELLIDO
        defaultPersonaShouldBeFound("conyugeapellido.contains=" + DEFAULT_CONYUGEAPELLIDO);

        // Get all the personaList where conyugeapellido contains UPDATED_CONYUGEAPELLIDO
        defaultPersonaShouldNotBeFound("conyugeapellido.contains=" + UPDATED_CONYUGEAPELLIDO);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugeapellidoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugeapellido does not contain DEFAULT_CONYUGEAPELLIDO
        defaultPersonaShouldNotBeFound("conyugeapellido.doesNotContain=" + DEFAULT_CONYUGEAPELLIDO);

        // Get all the personaList where conyugeapellido does not contain UPDATED_CONYUGEAPELLIDO
        defaultPersonaShouldBeFound("conyugeapellido.doesNotContain=" + UPDATED_CONYUGEAPELLIDO);
    }


    @Test
    @Transactional
    public void getAllPersonasByConyugenombreIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugenombre equals to DEFAULT_CONYUGENOMBRE
        defaultPersonaShouldBeFound("conyugenombre.equals=" + DEFAULT_CONYUGENOMBRE);

        // Get all the personaList where conyugenombre equals to UPDATED_CONYUGENOMBRE
        defaultPersonaShouldNotBeFound("conyugenombre.equals=" + UPDATED_CONYUGENOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugenombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugenombre not equals to DEFAULT_CONYUGENOMBRE
        defaultPersonaShouldNotBeFound("conyugenombre.notEquals=" + DEFAULT_CONYUGENOMBRE);

        // Get all the personaList where conyugenombre not equals to UPDATED_CONYUGENOMBRE
        defaultPersonaShouldBeFound("conyugenombre.notEquals=" + UPDATED_CONYUGENOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugenombreIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugenombre in DEFAULT_CONYUGENOMBRE or UPDATED_CONYUGENOMBRE
        defaultPersonaShouldBeFound("conyugenombre.in=" + DEFAULT_CONYUGENOMBRE + "," + UPDATED_CONYUGENOMBRE);

        // Get all the personaList where conyugenombre equals to UPDATED_CONYUGENOMBRE
        defaultPersonaShouldNotBeFound("conyugenombre.in=" + UPDATED_CONYUGENOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugenombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugenombre is not null
        defaultPersonaShouldBeFound("conyugenombre.specified=true");

        // Get all the personaList where conyugenombre is null
        defaultPersonaShouldNotBeFound("conyugenombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByConyugenombreContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugenombre contains DEFAULT_CONYUGENOMBRE
        defaultPersonaShouldBeFound("conyugenombre.contains=" + DEFAULT_CONYUGENOMBRE);

        // Get all the personaList where conyugenombre contains UPDATED_CONYUGENOMBRE
        defaultPersonaShouldNotBeFound("conyugenombre.contains=" + UPDATED_CONYUGENOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugenombreNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugenombre does not contain DEFAULT_CONYUGENOMBRE
        defaultPersonaShouldNotBeFound("conyugenombre.doesNotContain=" + DEFAULT_CONYUGENOMBRE);

        // Get all the personaList where conyugenombre does not contain UPDATED_CONYUGENOMBRE
        defaultPersonaShouldBeFound("conyugenombre.doesNotContain=" + UPDATED_CONYUGENOMBRE);
    }


    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni equals to DEFAULT_CONYUGEDNI
        defaultPersonaShouldBeFound("conyugedni.equals=" + DEFAULT_CONYUGEDNI);

        // Get all the personaList where conyugedni equals to UPDATED_CONYUGEDNI
        defaultPersonaShouldNotBeFound("conyugedni.equals=" + UPDATED_CONYUGEDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni not equals to DEFAULT_CONYUGEDNI
        defaultPersonaShouldNotBeFound("conyugedni.notEquals=" + DEFAULT_CONYUGEDNI);

        // Get all the personaList where conyugedni not equals to UPDATED_CONYUGEDNI
        defaultPersonaShouldBeFound("conyugedni.notEquals=" + UPDATED_CONYUGEDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni in DEFAULT_CONYUGEDNI or UPDATED_CONYUGEDNI
        defaultPersonaShouldBeFound("conyugedni.in=" + DEFAULT_CONYUGEDNI + "," + UPDATED_CONYUGEDNI);

        // Get all the personaList where conyugedni equals to UPDATED_CONYUGEDNI
        defaultPersonaShouldNotBeFound("conyugedni.in=" + UPDATED_CONYUGEDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni is not null
        defaultPersonaShouldBeFound("conyugedni.specified=true");

        // Get all the personaList where conyugedni is null
        defaultPersonaShouldNotBeFound("conyugedni.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni is greater than or equal to DEFAULT_CONYUGEDNI
        defaultPersonaShouldBeFound("conyugedni.greaterThanOrEqual=" + DEFAULT_CONYUGEDNI);

        // Get all the personaList where conyugedni is greater than or equal to UPDATED_CONYUGEDNI
        defaultPersonaShouldNotBeFound("conyugedni.greaterThanOrEqual=" + UPDATED_CONYUGEDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni is less than or equal to DEFAULT_CONYUGEDNI
        defaultPersonaShouldBeFound("conyugedni.lessThanOrEqual=" + DEFAULT_CONYUGEDNI);

        // Get all the personaList where conyugedni is less than or equal to SMALLER_CONYUGEDNI
        defaultPersonaShouldNotBeFound("conyugedni.lessThanOrEqual=" + SMALLER_CONYUGEDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni is less than DEFAULT_CONYUGEDNI
        defaultPersonaShouldNotBeFound("conyugedni.lessThan=" + DEFAULT_CONYUGEDNI);

        // Get all the personaList where conyugedni is less than UPDATED_CONYUGEDNI
        defaultPersonaShouldBeFound("conyugedni.lessThan=" + UPDATED_CONYUGEDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugedniIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugedni is greater than DEFAULT_CONYUGEDNI
        defaultPersonaShouldNotBeFound("conyugedni.greaterThan=" + DEFAULT_CONYUGEDNI);

        // Get all the personaList where conyugedni is greater than SMALLER_CONYUGEDNI
        defaultPersonaShouldBeFound("conyugedni.greaterThan=" + SMALLER_CONYUGEDNI);
    }


    @Test
    @Transactional
    public void getAllPersonasByConyugecuilIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugecuil equals to DEFAULT_CONYUGECUIL
        defaultPersonaShouldBeFound("conyugecuil.equals=" + DEFAULT_CONYUGECUIL);

        // Get all the personaList where conyugecuil equals to UPDATED_CONYUGECUIL
        defaultPersonaShouldNotBeFound("conyugecuil.equals=" + UPDATED_CONYUGECUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugecuilIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugecuil not equals to DEFAULT_CONYUGECUIL
        defaultPersonaShouldNotBeFound("conyugecuil.notEquals=" + DEFAULT_CONYUGECUIL);

        // Get all the personaList where conyugecuil not equals to UPDATED_CONYUGECUIL
        defaultPersonaShouldBeFound("conyugecuil.notEquals=" + UPDATED_CONYUGECUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugecuilIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugecuil in DEFAULT_CONYUGECUIL or UPDATED_CONYUGECUIL
        defaultPersonaShouldBeFound("conyugecuil.in=" + DEFAULT_CONYUGECUIL + "," + UPDATED_CONYUGECUIL);

        // Get all the personaList where conyugecuil equals to UPDATED_CONYUGECUIL
        defaultPersonaShouldNotBeFound("conyugecuil.in=" + UPDATED_CONYUGECUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugecuilIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugecuil is not null
        defaultPersonaShouldBeFound("conyugecuil.specified=true");

        // Get all the personaList where conyugecuil is null
        defaultPersonaShouldNotBeFound("conyugecuil.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByConyugecuilContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugecuil contains DEFAULT_CONYUGECUIL
        defaultPersonaShouldBeFound("conyugecuil.contains=" + DEFAULT_CONYUGECUIL);

        // Get all the personaList where conyugecuil contains UPDATED_CONYUGECUIL
        defaultPersonaShouldNotBeFound("conyugecuil.contains=" + UPDATED_CONYUGECUIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByConyugecuilNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where conyugecuil does not contain DEFAULT_CONYUGECUIL
        defaultPersonaShouldNotBeFound("conyugecuil.doesNotContain=" + DEFAULT_CONYUGECUIL);

        // Get all the personaList where conyugecuil does not contain UPDATED_CONYUGECUIL
        defaultPersonaShouldBeFound("conyugecuil.doesNotContain=" + UPDATED_CONYUGECUIL);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE);

        // Get all the personaList where grupofamiliarapellidonombre equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE);

        // Get all the personaList where grupofamiliarapellidonombre not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE);

        // Get all the personaList where grupofamiliarapellidonombre equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE);

        // Get all the personaList where grupofamiliarapellidonombre contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE);

        // Get all the personaList where grupofamiliarapellidonombre does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre2 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre2.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2);

        // Get all the personaList where grupofamiliarapellidonombre2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre2.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre2 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre2.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2);

        // Get all the personaList where grupofamiliarapellidonombre2 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre2.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre2 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre2.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2);

        // Get all the personaList where grupofamiliarapellidonombre2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre2.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre2 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre2.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre2 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre2 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre2.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2);

        // Get all the personaList where grupofamiliarapellidonombre2 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre2.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre2 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre2.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2);

        // Get all the personaList where grupofamiliarapellidonombre2 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre2.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre3IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre3 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre3.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3);

        // Get all the personaList where grupofamiliarapellidonombre3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre3.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre3 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre3.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3);

        // Get all the personaList where grupofamiliarapellidonombre3 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre3.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre3IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre3 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre3.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3);

        // Get all the personaList where grupofamiliarapellidonombre3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre3.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre3IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre3 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre3.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre3 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre3.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre3ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre3 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre3.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3);

        // Get all the personaList where grupofamiliarapellidonombre3 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre3.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre3NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre3 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre3.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3);

        // Get all the personaList where grupofamiliarapellidonombre3 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre3.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre4IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre4 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre4.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4);

        // Get all the personaList where grupofamiliarapellidonombre4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre4.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre4 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre4.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4);

        // Get all the personaList where grupofamiliarapellidonombre4 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre4.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre4IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre4 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre4.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4);

        // Get all the personaList where grupofamiliarapellidonombre4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre4.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre4IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre4 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre4.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre4 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre4.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre4ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre4 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre4.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4);

        // Get all the personaList where grupofamiliarapellidonombre4 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre4.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre4NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre4 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre4.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4);

        // Get all the personaList where grupofamiliarapellidonombre4 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre4.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre5IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre5 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre5.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5);

        // Get all the personaList where grupofamiliarapellidonombre5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre5.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre5 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre5.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5);

        // Get all the personaList where grupofamiliarapellidonombre5 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre5.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre5IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre5 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre5.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5);

        // Get all the personaList where grupofamiliarapellidonombre5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre5.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre5IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre5 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre5.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre5 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre5.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre5ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre5 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre5.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5);

        // Get all the personaList where grupofamiliarapellidonombre5 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre5.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre5NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre5 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre5.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5);

        // Get all the personaList where grupofamiliarapellidonombre5 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre5.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre6IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre6 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre6.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6);

        // Get all the personaList where grupofamiliarapellidonombre6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre6.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre6IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre6 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre6.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6);

        // Get all the personaList where grupofamiliarapellidonombre6 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre6.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre6IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre6 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre6.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6);

        // Get all the personaList where grupofamiliarapellidonombre6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre6.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre6IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre6 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre6.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre6 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre6.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre6ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre6 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre6.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6);

        // Get all the personaList where grupofamiliarapellidonombre6 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre6.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre6NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre6 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre6.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6);

        // Get all the personaList where grupofamiliarapellidonombre6 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre6.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre7IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre7 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre7.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7);

        // Get all the personaList where grupofamiliarapellidonombre7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre7.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre7IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre7 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre7.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7);

        // Get all the personaList where grupofamiliarapellidonombre7 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre7.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre7IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre7 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre7.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7);

        // Get all the personaList where grupofamiliarapellidonombre7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre7.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre7IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre7 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre7.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre7 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre7.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre7ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre7 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre7.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7);

        // Get all the personaList where grupofamiliarapellidonombre7 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre7.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre7NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre7 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre7.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7);

        // Get all the personaList where grupofamiliarapellidonombre7 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre7.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre8IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre8 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre8.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8);

        // Get all the personaList where grupofamiliarapellidonombre8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre8.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre8IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre8 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre8.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8);

        // Get all the personaList where grupofamiliarapellidonombre8 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre8.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre8IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre8 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre8.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8);

        // Get all the personaList where grupofamiliarapellidonombre8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre8.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre8IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre8 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre8.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre8 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre8.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre8ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre8 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre8.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8);

        // Get all the personaList where grupofamiliarapellidonombre8 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre8.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre8NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre8 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre8.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8);

        // Get all the personaList where grupofamiliarapellidonombre8 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre8.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre9IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre9 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre9.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9);

        // Get all the personaList where grupofamiliarapellidonombre9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre9.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre9IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre9 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre9.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9);

        // Get all the personaList where grupofamiliarapellidonombre9 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre9.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre9IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre9 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre9.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9);

        // Get all the personaList where grupofamiliarapellidonombre9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre9.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre9IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre9 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre9.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre9 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre9.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre9ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre9 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre9.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9);

        // Get all the personaList where grupofamiliarapellidonombre9 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre9.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre9NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre9 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre9.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9);

        // Get all the personaList where grupofamiliarapellidonombre9 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre9.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre10IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre10 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre10.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10);

        // Get all the personaList where grupofamiliarapellidonombre10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre10.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre10 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre10.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10);

        // Get all the personaList where grupofamiliarapellidonombre10 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre10.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre10IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre10 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre10.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10);

        // Get all the personaList where grupofamiliarapellidonombre10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre10.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre10IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre10 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre10.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre10 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre10.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre10ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre10 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre10.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10);

        // Get all the personaList where grupofamiliarapellidonombre10 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre10.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre10NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre10 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre10.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10);

        // Get all the personaList where grupofamiliarapellidonombre10 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre10.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre11IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre11 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre11.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11);

        // Get all the personaList where grupofamiliarapellidonombre11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre11.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre11IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre11 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre11.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11);

        // Get all the personaList where grupofamiliarapellidonombre11 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre11.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre11IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre11 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11 or UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre11.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11);

        // Get all the personaList where grupofamiliarapellidonombre11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre11.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre11IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre11 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre11.specified=true");

        // Get all the personaList where grupofamiliarapellidonombre11 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre11.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre11ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre11 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre11.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11);

        // Get all the personaList where grupofamiliarapellidonombre11 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre11.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombre11NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombre11 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombre11.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11);

        // Get all the personaList where grupofamiliarapellidonombre11 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombre11.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedadIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD);

        // Get all the personaList where grupofamiliarapellidonombreedad equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD);

        // Get all the personaList where grupofamiliarapellidonombreedad not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedadIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD);

        // Get all the personaList where grupofamiliarapellidonombreedad equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedadIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedadContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD);

        // Get all the personaList where grupofamiliarapellidonombreedad contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedadNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD);

        // Get all the personaList where grupofamiliarapellidonombreedad does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad2 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad2.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);

        // Get all the personaList where grupofamiliarapellidonombreedad2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad2.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad2 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad2.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);

        // Get all the personaList where grupofamiliarapellidonombreedad2 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad2.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad2 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad2.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);

        // Get all the personaList where grupofamiliarapellidonombreedad2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad2.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad2 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad2.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad2 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad2 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad2.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);

        // Get all the personaList where grupofamiliarapellidonombreedad2 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad2.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad2 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad2.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);

        // Get all the personaList where grupofamiliarapellidonombreedad2 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad2.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad3IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad3 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad3.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);

        // Get all the personaList where grupofamiliarapellidonombreedad3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad3.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad3 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad3.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);

        // Get all the personaList where grupofamiliarapellidonombreedad3 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad3.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad3IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad3 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad3.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);

        // Get all the personaList where grupofamiliarapellidonombreedad3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad3.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad3IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad3 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad3.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad3 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad3.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad3ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad3 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad3.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);

        // Get all the personaList where grupofamiliarapellidonombreedad3 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad3.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad3NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad3 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad3.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);

        // Get all the personaList where grupofamiliarapellidonombreedad3 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad3.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad4IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad4 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad4.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);

        // Get all the personaList where grupofamiliarapellidonombreedad4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad4.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad4 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad4.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);

        // Get all the personaList where grupofamiliarapellidonombreedad4 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad4.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad4IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad4 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad4.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);

        // Get all the personaList where grupofamiliarapellidonombreedad4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad4.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad4IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad4 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad4.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad4 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad4.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad4ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad4 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad4.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);

        // Get all the personaList where grupofamiliarapellidonombreedad4 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad4.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad4NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad4 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad4.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);

        // Get all the personaList where grupofamiliarapellidonombreedad4 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad4.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad5IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad5 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad5.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);

        // Get all the personaList where grupofamiliarapellidonombreedad5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad5.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad5 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad5.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);

        // Get all the personaList where grupofamiliarapellidonombreedad5 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad5.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad5IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad5 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad5.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);

        // Get all the personaList where grupofamiliarapellidonombreedad5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad5.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad5IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad5 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad5.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad5 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad5.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad5ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad5 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad5.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);

        // Get all the personaList where grupofamiliarapellidonombreedad5 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad5.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad5NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad5 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad5.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);

        // Get all the personaList where grupofamiliarapellidonombreedad5 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad5.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad6IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad6 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad6.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);

        // Get all the personaList where grupofamiliarapellidonombreedad6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad6.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad6IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad6 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad6.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);

        // Get all the personaList where grupofamiliarapellidonombreedad6 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad6.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad6IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad6 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad6.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);

        // Get all the personaList where grupofamiliarapellidonombreedad6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad6.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad6IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad6 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad6.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad6 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad6.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad6ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad6 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad6.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);

        // Get all the personaList where grupofamiliarapellidonombreedad6 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad6.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad6NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad6 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad6.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);

        // Get all the personaList where grupofamiliarapellidonombreedad6 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad6.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad7IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad7 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad7.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);

        // Get all the personaList where grupofamiliarapellidonombreedad7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad7.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad7IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad7 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad7.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);

        // Get all the personaList where grupofamiliarapellidonombreedad7 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad7.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad7IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad7 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad7.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);

        // Get all the personaList where grupofamiliarapellidonombreedad7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad7.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad7IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad7 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad7.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad7 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad7.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad7ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad7 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad7.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);

        // Get all the personaList where grupofamiliarapellidonombreedad7 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad7.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad7NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad7 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad7.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);

        // Get all the personaList where grupofamiliarapellidonombreedad7 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad7.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad8IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad8 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad8.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);

        // Get all the personaList where grupofamiliarapellidonombreedad8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad8.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad8IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad8 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad8.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);

        // Get all the personaList where grupofamiliarapellidonombreedad8 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad8.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad8IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad8 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad8.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);

        // Get all the personaList where grupofamiliarapellidonombreedad8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad8.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad8IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad8 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad8.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad8 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad8.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad8ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad8 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad8.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);

        // Get all the personaList where grupofamiliarapellidonombreedad8 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad8.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad8NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad8 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad8.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);

        // Get all the personaList where grupofamiliarapellidonombreedad8 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad8.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad9IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad9 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad9.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);

        // Get all the personaList where grupofamiliarapellidonombreedad9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad9.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad9IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad9 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad9.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);

        // Get all the personaList where grupofamiliarapellidonombreedad9 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad9.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad9IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad9 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad9.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);

        // Get all the personaList where grupofamiliarapellidonombreedad9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad9.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad9IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad9 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad9.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad9 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad9.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad9ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad9 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad9.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);

        // Get all the personaList where grupofamiliarapellidonombreedad9 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad9.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad9NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad9 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad9.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);

        // Get all the personaList where grupofamiliarapellidonombreedad9 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad9.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad10IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad10 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad10.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);

        // Get all the personaList where grupofamiliarapellidonombreedad10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad10.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad10 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad10.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);

        // Get all the personaList where grupofamiliarapellidonombreedad10 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad10.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad10IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad10 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad10.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);

        // Get all the personaList where grupofamiliarapellidonombreedad10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad10.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad10IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad10 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad10.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad10 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad10.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad10ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad10 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad10.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);

        // Get all the personaList where grupofamiliarapellidonombreedad10 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad10.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad10NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad10 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad10.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);

        // Get all the personaList where grupofamiliarapellidonombreedad10 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad10.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad11IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad11 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad11.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);

        // Get all the personaList where grupofamiliarapellidonombreedad11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad11.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad11IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad11 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad11.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);

        // Get all the personaList where grupofamiliarapellidonombreedad11 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad11.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad11IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad11 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad11.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);

        // Get all the personaList where grupofamiliarapellidonombreedad11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad11.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad11IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad11 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad11.specified=true");

        // Get all the personaList where grupofamiliarapellidonombreedad11 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad11.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad11ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad11 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad11.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);

        // Get all the personaList where grupofamiliarapellidonombreedad11 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad11.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombreedad11NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombreedad11 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombreedad11.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);

        // Get all the personaList where grupofamiliarapellidonombreedad11 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombreedad11.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI);

        // Get all the personaList where grupofamiliarapellidonombredni equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI);

        // Get all the personaList where grupofamiliarapellidonombredni not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI);

        // Get all the personaList where grupofamiliarapellidonombredni equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI);

        // Get all the personaList where grupofamiliarapellidonombredni is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI);

        // Get all the personaList where grupofamiliarapellidonombredni is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI);

        // Get all the personaList where grupofamiliarapellidonombredni is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredniIsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI);

        // Get all the personaList where grupofamiliarapellidonombredni is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2);

        // Get all the personaList where grupofamiliarapellidonombredni2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2);

        // Get all the personaList where grupofamiliarapellidonombredni2 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2);

        // Get all the personaList where grupofamiliarapellidonombredni2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni2 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2);

        // Get all the personaList where grupofamiliarapellidonombredni2 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2);

        // Get all the personaList where grupofamiliarapellidonombredni2 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2);

        // Get all the personaList where grupofamiliarapellidonombredni2 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni2IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni2 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni2.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2);

        // Get all the personaList where grupofamiliarapellidonombredni2 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni2.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3);

        // Get all the personaList where grupofamiliarapellidonombredni3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3);

        // Get all the personaList where grupofamiliarapellidonombredni3 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3);

        // Get all the personaList where grupofamiliarapellidonombredni3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni3 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3);

        // Get all the personaList where grupofamiliarapellidonombredni3 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3);

        // Get all the personaList where grupofamiliarapellidonombredni3 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3);

        // Get all the personaList where grupofamiliarapellidonombredni3 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni3IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni3 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni3.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3);

        // Get all the personaList where grupofamiliarapellidonombredni3 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni3.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4);

        // Get all the personaList where grupofamiliarapellidonombredni4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4);

        // Get all the personaList where grupofamiliarapellidonombredni4 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4);

        // Get all the personaList where grupofamiliarapellidonombredni4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni4 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4);

        // Get all the personaList where grupofamiliarapellidonombredni4 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4);

        // Get all the personaList where grupofamiliarapellidonombredni4 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4);

        // Get all the personaList where grupofamiliarapellidonombredni4 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni4IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni4 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni4.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4);

        // Get all the personaList where grupofamiliarapellidonombredni4 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni4.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5);

        // Get all the personaList where grupofamiliarapellidonombredni5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5);

        // Get all the personaList where grupofamiliarapellidonombredni5 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5);

        // Get all the personaList where grupofamiliarapellidonombredni5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni5 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5);

        // Get all the personaList where grupofamiliarapellidonombredni5 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5);

        // Get all the personaList where grupofamiliarapellidonombredni5 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5);

        // Get all the personaList where grupofamiliarapellidonombredni5 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni5IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni5 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni5.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5);

        // Get all the personaList where grupofamiliarapellidonombredni5 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni5.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6);

        // Get all the personaList where grupofamiliarapellidonombredni6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6);

        // Get all the personaList where grupofamiliarapellidonombredni6 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6);

        // Get all the personaList where grupofamiliarapellidonombredni6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni6 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6);

        // Get all the personaList where grupofamiliarapellidonombredni6 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6);

        // Get all the personaList where grupofamiliarapellidonombredni6 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6);

        // Get all the personaList where grupofamiliarapellidonombredni6 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni6IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni6 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni6.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6);

        // Get all the personaList where grupofamiliarapellidonombredni6 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni6.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7);

        // Get all the personaList where grupofamiliarapellidonombredni7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7);

        // Get all the personaList where grupofamiliarapellidonombredni7 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7);

        // Get all the personaList where grupofamiliarapellidonombredni7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni7 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7);

        // Get all the personaList where grupofamiliarapellidonombredni7 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7);

        // Get all the personaList where grupofamiliarapellidonombredni7 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7);

        // Get all the personaList where grupofamiliarapellidonombredni7 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni7IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni7 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni7.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7);

        // Get all the personaList where grupofamiliarapellidonombredni7 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni7.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8);

        // Get all the personaList where grupofamiliarapellidonombredni8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8);

        // Get all the personaList where grupofamiliarapellidonombredni8 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8);

        // Get all the personaList where grupofamiliarapellidonombredni8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni8 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8);

        // Get all the personaList where grupofamiliarapellidonombredni8 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8);

        // Get all the personaList where grupofamiliarapellidonombredni8 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8);

        // Get all the personaList where grupofamiliarapellidonombredni8 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni8IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni8 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni8.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8);

        // Get all the personaList where grupofamiliarapellidonombredni8 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni8.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9);

        // Get all the personaList where grupofamiliarapellidonombredni9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9);

        // Get all the personaList where grupofamiliarapellidonombredni9 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9);

        // Get all the personaList where grupofamiliarapellidonombredni9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni9 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9);

        // Get all the personaList where grupofamiliarapellidonombredni9 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9);

        // Get all the personaList where grupofamiliarapellidonombredni9 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9);

        // Get all the personaList where grupofamiliarapellidonombredni9 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni9IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni9 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni9.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9);

        // Get all the personaList where grupofamiliarapellidonombredni9 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni9.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10);

        // Get all the personaList where grupofamiliarapellidonombredni10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10);

        // Get all the personaList where grupofamiliarapellidonombredni10 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10);

        // Get all the personaList where grupofamiliarapellidonombredni10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni10 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10);

        // Get all the personaList where grupofamiliarapellidonombredni10 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10);

        // Get all the personaList where grupofamiliarapellidonombredni10 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10);

        // Get all the personaList where grupofamiliarapellidonombredni10 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni10IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni10 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni10.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10);

        // Get all the personaList where grupofamiliarapellidonombredni10 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni10.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11);

        // Get all the personaList where grupofamiliarapellidonombredni11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11);

        // Get all the personaList where grupofamiliarapellidonombredni11 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11);

        // Get all the personaList where grupofamiliarapellidonombredni11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.specified=true");

        // Get all the personaList where grupofamiliarapellidonombredni11 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.specified=false");
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 is greater than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.greaterThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11);

        // Get all the personaList where grupofamiliarapellidonombredni11 is greater than or equal to UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.greaterThanOrEqual=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 is less than or equal to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.lessThanOrEqual=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11);

        // Get all the personaList where grupofamiliarapellidonombredni11 is less than or equal to SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.lessThanOrEqual=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsLessThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 is less than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.lessThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11);

        // Get all the personaList where grupofamiliarapellidonombredni11 is less than UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.lessThan=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombredni11IsGreaterThanSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombredni11 is greater than DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombredni11.greaterThan=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11);

        // Get all the personaList where grupofamiliarapellidonombredni11 is greater than SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombredni11.greaterThan=" + SMALLER_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliarIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliarIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliarIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliarIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliarContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliarNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar2IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar2.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar2.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar2.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar2.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar2IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar2.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar2.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar2IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar2.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar2.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar2ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar2.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar2.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar2NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar2.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar2 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar2.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar4IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar4.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar4.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar4IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar4.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar4.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar4IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar4.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar4.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar4IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar4.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar4.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar4ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar4.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar4.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar4NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar4.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar4 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar4.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar3IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar3.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar3.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar3.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar3.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar3IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar3.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar3.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar3IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar3.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar3.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar3ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar3.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar3.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar3NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar3.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar3 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar3.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar5IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar5.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar5.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar5IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar5.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar5.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar5IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar5.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar5.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar5IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar5.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar5.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar5ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar5.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar5.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar5NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar5.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar5 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar5.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar6IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar6.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar6.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar6IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar6.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar6.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar6IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar6.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar6.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar6IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar6.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar6.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar6ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar6.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar6.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar6NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar6.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar6 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar6.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar7IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar7.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar7.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar7IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar7.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar7.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar7IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar7.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar7.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar7IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar7.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar7.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar7ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar7.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar7.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar7NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar7.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar7 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar7.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar8IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar8.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar8.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar8IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar8.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar8.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar8IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar8.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar8.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar8IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar8.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar8.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar8ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar8.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar8.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar8NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar8.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar8 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar8.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar9IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar9.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar9.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar9IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar9.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar9.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar9IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar9.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar9.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar9IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar9.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar9.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar9ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar9.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar9.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar9NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar9.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar9 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar9.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar10IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar10.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar10.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar10IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar10.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar10.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar10IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar10.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar10.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar10IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar10.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar10.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar10ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar10.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar10.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar10NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar10.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar10 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar10.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);
    }


    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar11IsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar11.equals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar11.equals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar11IsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 not equals to DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar11.notEquals=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 not equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar11.notEquals=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar11IsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 in DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11 or UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar11.in=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11 + "," + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 equals to UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar11.in=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar11IsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 is not null
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar11.specified=true");

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 is null
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar11.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar11ContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 contains DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar11.contains=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 contains UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar11.contains=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
    }

    @Test
    @Transactional
    public void getAllPersonasByGrupofamiliarapellidonombrefamiliar11NotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 does not contain DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldNotBeFound("grupofamiliarapellidonombrefamiliar11.doesNotContain=" + DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);

        // Get all the personaList where grupofamiliarapellidonombrefamiliar11 does not contain UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11
        defaultPersonaShouldBeFound("grupofamiliarapellidonombrefamiliar11.doesNotContain=" + UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
    }


    @Test
    @Transactional
    public void getAllPersonasByLicenciaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        Licencia licencia = LicenciaResourceIT.createEntity(em);
        em.persist(licencia);
        em.flush();
        persona.addLicencia(licencia);
        personaRepository.saveAndFlush(persona);
        Long licenciaId = licencia.getId();

        // Get all the personaList where licencia equals to licenciaId
        defaultPersonaShouldBeFound("licenciaId.equals=" + licenciaId);

        // Get all the personaList where licencia equals to licenciaId + 1
        defaultPersonaShouldNotBeFound("licenciaId.equals=" + (licenciaId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonasByAltasAscensosBajasIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        AltasAscensosBajas altasAscensosBajas = AltasAscensosBajasResourceIT.createEntity(em);
        em.persist(altasAscensosBajas);
        em.flush();
        persona.addAltasAscensosBajas(altasAscensosBajas);
        personaRepository.saveAndFlush(persona);
        Long altasAscensosBajasId = altasAscensosBajas.getId();

        // Get all the personaList where altasAscensosBajas equals to altasAscensosBajasId
        defaultPersonaShouldBeFound("altasAscensosBajasId.equals=" + altasAscensosBajasId);

        // Get all the personaList where altasAscensosBajas equals to altasAscensosBajasId + 1
        defaultPersonaShouldNotBeFound("altasAscensosBajasId.equals=" + (altasAscensosBajasId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonasByConceptoConocimientosEspecialesClasificacionPremiosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios = ConceptoConocimientosEspecialesClasificacionPremiosResourceIT.createEntity(em);
        em.persist(conceptoConocimientosEspecialesClasificacionPremios);
        em.flush();
        persona.addConceptoConocimientosEspecialesClasificacionPremios(conceptoConocimientosEspecialesClasificacionPremios);
        personaRepository.saveAndFlush(persona);
        Long conceptoConocimientosEspecialesClasificacionPremiosId = conceptoConocimientosEspecialesClasificacionPremios.getId();

        // Get all the personaList where conceptoConocimientosEspecialesClasificacionPremios equals to conceptoConocimientosEspecialesClasificacionPremiosId
        defaultPersonaShouldBeFound("conceptoConocimientosEspecialesClasificacionPremiosId.equals=" + conceptoConocimientosEspecialesClasificacionPremiosId);

        // Get all the personaList where conceptoConocimientosEspecialesClasificacionPremios equals to conceptoConocimientosEspecialesClasificacionPremiosId + 1
        defaultPersonaShouldNotBeFound("conceptoConocimientosEspecialesClasificacionPremiosId.equals=" + (conceptoConocimientosEspecialesClasificacionPremiosId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonasByEmbargosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        Embargos embargos = EmbargosResourceIT.createEntity(em);
        em.persist(embargos);
        em.flush();
        persona.addEmbargos(embargos);
        personaRepository.saveAndFlush(persona);
        Long embargosId = embargos.getId();

        // Get all the personaList where embargos equals to embargosId
        defaultPersonaShouldBeFound("embargosId.equals=" + embargosId);

        // Get all the personaList where embargos equals to embargosId + 1
        defaultPersonaShouldNotBeFound("embargosId.equals=" + (embargosId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonasByGarantiaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        Garantia garantia = GarantiaResourceIT.createEntity(em);
        em.persist(garantia);
        em.flush();
        persona.addGarantia(garantia);
        personaRepository.saveAndFlush(persona);
        Long garantiaId = garantia.getId();

        // Get all the personaList where garantia equals to garantiaId
        defaultPersonaShouldBeFound("garantiaId.equals=" + garantiaId);

        // Get all the personaList where garantia equals to garantiaId + 1
        defaultPersonaShouldNotBeFound("garantiaId.equals=" + (garantiaId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonasByOtrosServiciosPrestadosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        OtrosServiciosPrestados otrosServiciosPrestados = OtrosServiciosPrestadosResourceIT.createEntity(em);
        em.persist(otrosServiciosPrestados);
        em.flush();
        persona.addOtrosServiciosPrestados(otrosServiciosPrestados);
        personaRepository.saveAndFlush(persona);
        Long otrosServiciosPrestadosId = otrosServiciosPrestados.getId();

        // Get all the personaList where otrosServiciosPrestados equals to otrosServiciosPrestadosId
        defaultPersonaShouldBeFound("otrosServiciosPrestadosId.equals=" + otrosServiciosPrestadosId);

        // Get all the personaList where otrosServiciosPrestados equals to otrosServiciosPrestadosId + 1
        defaultPersonaShouldNotBeFound("otrosServiciosPrestadosId.equals=" + (otrosServiciosPrestadosId + 1));
    }


    @Test
    @Transactional
    public void getAllPersonasByPenasDisciplinariasSufridasIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        PenasDisciplinariasSufridas penasDisciplinariasSufridas = PenasDisciplinariasSufridasResourceIT.createEntity(em);
        em.persist(penasDisciplinariasSufridas);
        em.flush();
        persona.addPenasDisciplinariasSufridas(penasDisciplinariasSufridas);
        personaRepository.saveAndFlush(persona);
        Long penasDisciplinariasSufridasId = penasDisciplinariasSufridas.getId();

        // Get all the personaList where penasDisciplinariasSufridas equals to penasDisciplinariasSufridasId
        defaultPersonaShouldBeFound("penasDisciplinariasSufridasId.equals=" + penasDisciplinariasSufridasId);

        // Get all the personaList where penasDisciplinariasSufridas equals to penasDisciplinariasSufridasId + 1
        defaultPersonaShouldNotBeFound("penasDisciplinariasSufridasId.equals=" + (penasDisciplinariasSufridasId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPersonaShouldBeFound(String filter) throws Exception {
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persona.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].cuil").value(hasItem(DEFAULT_CUIL)))
            .andExpect(jsonPath("$.[*].dni").value(hasItem(DEFAULT_DNI)))
            .andExpect(jsonPath("$.[*].legajo").value(hasItem(DEFAULT_LEGAJO)))
            .andExpect(jsonPath("$.[*].apodo").value(hasItem(DEFAULT_APODO)))
            .andExpect(jsonPath("$.[*].fotoContentType").value(hasItem(DEFAULT_FOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].foto").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO))))
            .andExpect(jsonPath("$.[*].soltero").value(hasItem(DEFAULT_SOLTERO)))
            .andExpect(jsonPath("$.[*].casado").value(hasItem(DEFAULT_CASADO)))
            .andExpect(jsonPath("$.[*].conviviente").value(hasItem(DEFAULT_CONVIVIENTE)))
            .andExpect(jsonPath("$.[*].viudo").value(hasItem(DEFAULT_VIUDO)))
            .andExpect(jsonPath("$.[*].domicilio").value(hasItem(DEFAULT_DOMICILIO)))
            .andExpect(jsonPath("$.[*].lugar").value(hasItem(DEFAULT_LUGAR)))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].telefonofijo").value(hasItem(DEFAULT_TELEFONOFIJO)))
            .andExpect(jsonPath("$.[*].numerodecelular").value(hasItem(DEFAULT_NUMERODECELULAR)))
            .andExpect(jsonPath("$.[*].oficioprofecion").value(hasItem(DEFAULT_OFICIOPROFECION)))
            .andExpect(jsonPath("$.[*].niveldeestudios").value(hasItem(DEFAULT_NIVELDEESTUDIOS)))
            .andExpect(jsonPath("$.[*].gruposanguineo").value(hasItem(DEFAULT_GRUPOSANGUINEO)))
            .andExpect(jsonPath("$.[*].factor").value(hasItem(DEFAULT_FACTOR)))
            .andExpect(jsonPath("$.[*].donante").value(hasItem(DEFAULT_DONANTE)))
            .andExpect(jsonPath("$.[*].diabetes").value(hasItem(DEFAULT_DIABETES)))
            .andExpect(jsonPath("$.[*].hipertension").value(hasItem(DEFAULT_HIPERTENSION)))
            .andExpect(jsonPath("$.[*].alergias").value(hasItem(DEFAULT_ALERGIAS)))
            .andExpect(jsonPath("$.[*].asma").value(hasItem(DEFAULT_ASMA)))
            .andExpect(jsonPath("$.[*].otros").value(hasItem(DEFAULT_OTROS)))
            .andExpect(jsonPath("$.[*].fechadeingreso").value(hasItem(DEFAULT_FECHADEINGRESO.toString())))
            .andExpect(jsonPath("$.[*].instrumentolegal").value(hasItem(DEFAULT_INSTRUMENTOLEGAL)))
            .andExpect(jsonPath("$.[*].categoria").value(hasItem(DEFAULT_CATEGORIA)))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].planta").value(hasItem(DEFAULT_PLANTA)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].annos").value(hasItem(DEFAULT_ANNOS)))
            .andExpect(jsonPath("$.[*].meses").value(hasItem(DEFAULT_MESES)))
            .andExpect(jsonPath("$.[*].dias").value(hasItem(DEFAULT_DIAS)))
            .andExpect(jsonPath("$.[*].realizocomputodeservicios").value(hasItem(DEFAULT_REALIZOCOMPUTODESERVICIOS)))
            .andExpect(jsonPath("$.[*].poseeconocimientoenmaquinasviales").value(hasItem(DEFAULT_POSEECONOCIMIENTOENMAQUINASVIALES)))
            .andExpect(jsonPath("$.[*].casoemergenciacelular").value(hasItem(DEFAULT_CASOEMERGENCIACELULAR)))
            .andExpect(jsonPath("$.[*].casoemergenciafijo").value(hasItem(DEFAULT_CASOEMERGENCIAFIJO)))
            .andExpect(jsonPath("$.[*].casoemergencianombre").value(hasItem(DEFAULT_CASOEMERGENCIANOMBRE)))
            .andExpect(jsonPath("$.[*].casoemergenciacelular2").value(hasItem(DEFAULT_CASOEMERGENCIACELULAR_2)))
            .andExpect(jsonPath("$.[*].casoemergenciafijo2").value(hasItem(DEFAULT_CASOEMERGENCIAFIJO_2)))
            .andExpect(jsonPath("$.[*].casoemergencianombre2").value(hasItem(DEFAULT_CASOEMERGENCIANOMBRE_2)))
            .andExpect(jsonPath("$.[*].familiaracargonombre").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE)))
            .andExpect(jsonPath("$.[*].familiaracargonombre2").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_2)))
            .andExpect(jsonPath("$.[*].familiaracargonombre3").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_3)))
            .andExpect(jsonPath("$.[*].familiaracargonombre4").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_4)))
            .andExpect(jsonPath("$.[*].familiaracargonombre5").value(hasItem(DEFAULT_FAMILIARACARGONOMBRE_5)))
            .andExpect(jsonPath("$.[*].familiaracargodni").value(hasItem(DEFAULT_FAMILIARACARGODNI)))
            .andExpect(jsonPath("$.[*].familiaracargodni2").value(hasItem(DEFAULT_FAMILIARACARGODNI_2)))
            .andExpect(jsonPath("$.[*].familiaracargodni3").value(hasItem(DEFAULT_FAMILIARACARGODNI_3)))
            .andExpect(jsonPath("$.[*].familiaracargodni4").value(hasItem(DEFAULT_FAMILIARACARGODNI_4)))
            .andExpect(jsonPath("$.[*].familiaracargodni5").value(hasItem(DEFAULT_FAMILIARACARGODNI_5)))
            .andExpect(jsonPath("$.[*].familiaracargoedad").value(hasItem(DEFAULT_FAMILIARACARGOEDAD)))
            .andExpect(jsonPath("$.[*].familiaracargoedad2").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_2)))
            .andExpect(jsonPath("$.[*].familiaracargoedad3").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_3)))
            .andExpect(jsonPath("$.[*].familiaracargoedad4").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_4)))
            .andExpect(jsonPath("$.[*].familiaracargoedad5").value(hasItem(DEFAULT_FAMILIARACARGOEDAD_5)))
            .andExpect(jsonPath("$.[*].altura").value(hasItem(DEFAULT_ALTURA)))
            .andExpect(jsonPath("$.[*].barrio").value(hasItem(DEFAULT_BARRIO)))
            .andExpect(jsonPath("$.[*].estudiosincompletos").value(hasItem(DEFAULT_ESTUDIOSINCOMPLETOS)))
            .andExpect(jsonPath("$.[*].conyugeapellido").value(hasItem(DEFAULT_CONYUGEAPELLIDO)))
            .andExpect(jsonPath("$.[*].conyugenombre").value(hasItem(DEFAULT_CONYUGENOMBRE)))
            .andExpect(jsonPath("$.[*].conyugedni").value(hasItem(DEFAULT_CONYUGEDNI)))
            .andExpect(jsonPath("$.[*].conyugecuil").value(hasItem(DEFAULT_CONYUGECUIL)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombre11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBRE_11)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombreedad11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREEDAD_11)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombredni11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREDNI_11)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar2").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar4").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar3").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar5").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar6").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar7").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar8").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar9").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar10").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10)))
            .andExpect(jsonPath("$.[*].grupofamiliarapellidonombrefamiliar11").value(hasItem(DEFAULT_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11)));

        // Check, that the count call also returns 1
        restPersonaMockMvc.perform(get("/api/personas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPersonaShouldNotBeFound(String filter) throws Exception {
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPersonaMockMvc.perform(get("/api/personas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPersona() throws Exception {
        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersona() throws Exception {
        // Initialize the database
        personaService.save(persona);

        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Update the persona
        Persona updatedPersona = personaRepository.findById(persona.getId()).get();
        // Disconnect from session so that the updates on updatedPersona are not directly saved in db
        em.detach(updatedPersona);
        updatedPersona
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .cuil(UPDATED_CUIL)
            .dni(UPDATED_DNI)
            .legajo(UPDATED_LEGAJO)
            .apodo(UPDATED_APODO)
            .foto(UPDATED_FOTO)
            .fotoContentType(UPDATED_FOTO_CONTENT_TYPE)
            .soltero(UPDATED_SOLTERO)
            .casado(UPDATED_CASADO)
            .conviviente(UPDATED_CONVIVIENTE)
            .viudo(UPDATED_VIUDO)
            .domicilio(UPDATED_DOMICILIO)
            .lugar(UPDATED_LUGAR)
            .calle(UPDATED_CALLE)
            .numero(UPDATED_NUMERO)
            .telefonofijo(UPDATED_TELEFONOFIJO)
            .numerodecelular(UPDATED_NUMERODECELULAR)
            .oficioprofecion(UPDATED_OFICIOPROFECION)
            .niveldeestudios(UPDATED_NIVELDEESTUDIOS)
            .gruposanguineo(UPDATED_GRUPOSANGUINEO)
            .factor(UPDATED_FACTOR)
            .donante(UPDATED_DONANTE)
            .diabetes(UPDATED_DIABETES)
            .hipertension(UPDATED_HIPERTENSION)
            .alergias(UPDATED_ALERGIAS)
            .asma(UPDATED_ASMA)
            .otros(UPDATED_OTROS)
            .fechadeingreso(UPDATED_FECHADEINGRESO)
            .instrumentolegal(UPDATED_INSTRUMENTOLEGAL)
            .categoria(UPDATED_CATEGORIA)
            .item(UPDATED_ITEM)
            .planta(UPDATED_PLANTA)
            .area(UPDATED_AREA)
            .direccion(UPDATED_DIRECCION)
            .annos(UPDATED_ANNOS)
            .meses(UPDATED_MESES)
            .dias(UPDATED_DIAS)
            .realizocomputodeservicios(UPDATED_REALIZOCOMPUTODESERVICIOS)
            .poseeconocimientoenmaquinasviales(UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES)
            .casoemergenciacelular(UPDATED_CASOEMERGENCIACELULAR)
            .casoemergenciafijo(UPDATED_CASOEMERGENCIAFIJO)
            .casoemergencianombre(UPDATED_CASOEMERGENCIANOMBRE)
            .casoemergenciacelular2(UPDATED_CASOEMERGENCIACELULAR_2)
            .casoemergenciafijo2(UPDATED_CASOEMERGENCIAFIJO_2)
            .casoemergencianombre2(UPDATED_CASOEMERGENCIANOMBRE_2)
            .familiaracargonombre(UPDATED_FAMILIARACARGONOMBRE)
            .familiaracargonombre2(UPDATED_FAMILIARACARGONOMBRE_2)
            .familiaracargonombre3(UPDATED_FAMILIARACARGONOMBRE_3)
            .familiaracargonombre4(UPDATED_FAMILIARACARGONOMBRE_4)
            .familiaracargonombre5(UPDATED_FAMILIARACARGONOMBRE_5)
            .familiaracargodni(UPDATED_FAMILIARACARGODNI)
            .familiaracargodni2(UPDATED_FAMILIARACARGODNI_2)
            .familiaracargodni3(UPDATED_FAMILIARACARGODNI_3)
            .familiaracargodni4(UPDATED_FAMILIARACARGODNI_4)
            .familiaracargodni5(UPDATED_FAMILIARACARGODNI_5)
            .familiaracargoedad(UPDATED_FAMILIARACARGOEDAD)
            .familiaracargoedad2(UPDATED_FAMILIARACARGOEDAD_2)
            .familiaracargoedad3(UPDATED_FAMILIARACARGOEDAD_3)
            .familiaracargoedad4(UPDATED_FAMILIARACARGOEDAD_4)
            .familiaracargoedad5(UPDATED_FAMILIARACARGOEDAD_5)
            .altura(UPDATED_ALTURA)
            .barrio(UPDATED_BARRIO)
            .estudiosincompletos(UPDATED_ESTUDIOSINCOMPLETOS)
            .conyugeapellido(UPDATED_CONYUGEAPELLIDO)
            .conyugenombre(UPDATED_CONYUGENOMBRE)
            .conyugedni(UPDATED_CONYUGEDNI)
            .conyugecuil(UPDATED_CONYUGECUIL)
            .grupofamiliarapellidonombre(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE)
            .grupofamiliarapellidonombre2(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2)
            .grupofamiliarapellidonombre3(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3)
            .grupofamiliarapellidonombre4(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4)
            .grupofamiliarapellidonombre5(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5)
            .grupofamiliarapellidonombre6(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6)
            .grupofamiliarapellidonombre7(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7)
            .grupofamiliarapellidonombre8(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8)
            .grupofamiliarapellidonombre9(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9)
            .grupofamiliarapellidonombre10(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10)
            .grupofamiliarapellidonombre11(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11)
            .grupofamiliarapellidonombreedad(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD)
            .grupofamiliarapellidonombreedad2(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2)
            .grupofamiliarapellidonombreedad3(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3)
            .grupofamiliarapellidonombreedad4(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4)
            .grupofamiliarapellidonombreedad5(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5)
            .grupofamiliarapellidonombreedad6(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6)
            .grupofamiliarapellidonombreedad7(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7)
            .grupofamiliarapellidonombreedad8(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8)
            .grupofamiliarapellidonombreedad9(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9)
            .grupofamiliarapellidonombreedad10(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10)
            .grupofamiliarapellidonombreedad11(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11)
            .grupofamiliarapellidonombredni(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI)
            .grupofamiliarapellidonombredni2(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2)
            .grupofamiliarapellidonombredni3(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3)
            .grupofamiliarapellidonombredni4(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4)
            .grupofamiliarapellidonombredni5(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5)
            .grupofamiliarapellidonombredni6(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6)
            .grupofamiliarapellidonombredni7(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7)
            .grupofamiliarapellidonombredni8(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8)
            .grupofamiliarapellidonombredni9(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9)
            .grupofamiliarapellidonombredni10(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10)
            .grupofamiliarapellidonombredni11(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11)
            .grupofamiliarapellidonombrefamiliar(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR)
            .grupofamiliarapellidonombrefamiliar2(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2)
            .grupofamiliarapellidonombrefamiliar4(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4)
            .grupofamiliarapellidonombrefamiliar3(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3)
            .grupofamiliarapellidonombrefamiliar5(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5)
            .grupofamiliarapellidonombrefamiliar6(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6)
            .grupofamiliarapellidonombrefamiliar7(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7)
            .grupofamiliarapellidonombrefamiliar8(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8)
            .grupofamiliarapellidonombrefamiliar9(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9)
            .grupofamiliarapellidonombrefamiliar10(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10)
            .grupofamiliarapellidonombrefamiliar11(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);

        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersona)))
            .andExpect(status().isOk());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPersona.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testPersona.getCuil()).isEqualTo(UPDATED_CUIL);
        assertThat(testPersona.getDni()).isEqualTo(UPDATED_DNI);
        assertThat(testPersona.getLegajo()).isEqualTo(UPDATED_LEGAJO);
        assertThat(testPersona.getApodo()).isEqualTo(UPDATED_APODO);
        assertThat(testPersona.getFoto()).isEqualTo(UPDATED_FOTO);
        assertThat(testPersona.getFotoContentType()).isEqualTo(UPDATED_FOTO_CONTENT_TYPE);
        assertThat(testPersona.getSoltero()).isEqualTo(UPDATED_SOLTERO);
        assertThat(testPersona.getCasado()).isEqualTo(UPDATED_CASADO);
        assertThat(testPersona.getConviviente()).isEqualTo(UPDATED_CONVIVIENTE);
        assertThat(testPersona.getViudo()).isEqualTo(UPDATED_VIUDO);
        assertThat(testPersona.getDomicilio()).isEqualTo(UPDATED_DOMICILIO);
        assertThat(testPersona.getLugar()).isEqualTo(UPDATED_LUGAR);
        assertThat(testPersona.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testPersona.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testPersona.getTelefonofijo()).isEqualTo(UPDATED_TELEFONOFIJO);
        assertThat(testPersona.getNumerodecelular()).isEqualTo(UPDATED_NUMERODECELULAR);
        assertThat(testPersona.getOficioprofecion()).isEqualTo(UPDATED_OFICIOPROFECION);
        assertThat(testPersona.getNiveldeestudios()).isEqualTo(UPDATED_NIVELDEESTUDIOS);
        assertThat(testPersona.getGruposanguineo()).isEqualTo(UPDATED_GRUPOSANGUINEO);
        assertThat(testPersona.getFactor()).isEqualTo(UPDATED_FACTOR);
        assertThat(testPersona.getDonante()).isEqualTo(UPDATED_DONANTE);
        assertThat(testPersona.getDiabetes()).isEqualTo(UPDATED_DIABETES);
        assertThat(testPersona.getHipertension()).isEqualTo(UPDATED_HIPERTENSION);
        assertThat(testPersona.getAlergias()).isEqualTo(UPDATED_ALERGIAS);
        assertThat(testPersona.getAsma()).isEqualTo(UPDATED_ASMA);
        assertThat(testPersona.getOtros()).isEqualTo(UPDATED_OTROS);
        assertThat(testPersona.getFechadeingreso()).isEqualTo(UPDATED_FECHADEINGRESO);
        assertThat(testPersona.getInstrumentolegal()).isEqualTo(UPDATED_INSTRUMENTOLEGAL);
        assertThat(testPersona.getCategoria()).isEqualTo(UPDATED_CATEGORIA);
        assertThat(testPersona.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testPersona.getPlanta()).isEqualTo(UPDATED_PLANTA);
        assertThat(testPersona.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testPersona.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testPersona.getAnnos()).isEqualTo(UPDATED_ANNOS);
        assertThat(testPersona.getMeses()).isEqualTo(UPDATED_MESES);
        assertThat(testPersona.getDias()).isEqualTo(UPDATED_DIAS);
        assertThat(testPersona.getRealizocomputodeservicios()).isEqualTo(UPDATED_REALIZOCOMPUTODESERVICIOS);
        assertThat(testPersona.getPoseeconocimientoenmaquinasviales()).isEqualTo(UPDATED_POSEECONOCIMIENTOENMAQUINASVIALES);
        assertThat(testPersona.getCasoemergenciacelular()).isEqualTo(UPDATED_CASOEMERGENCIACELULAR);
        assertThat(testPersona.getCasoemergenciafijo()).isEqualTo(UPDATED_CASOEMERGENCIAFIJO);
        assertThat(testPersona.getCasoemergencianombre()).isEqualTo(UPDATED_CASOEMERGENCIANOMBRE);
        assertThat(testPersona.getCasoemergenciacelular2()).isEqualTo(UPDATED_CASOEMERGENCIACELULAR_2);
        assertThat(testPersona.getCasoemergenciafijo2()).isEqualTo(UPDATED_CASOEMERGENCIAFIJO_2);
        assertThat(testPersona.getCasoemergencianombre2()).isEqualTo(UPDATED_CASOEMERGENCIANOMBRE_2);
        assertThat(testPersona.getFamiliaracargonombre()).isEqualTo(UPDATED_FAMILIARACARGONOMBRE);
        assertThat(testPersona.getFamiliaracargonombre2()).isEqualTo(UPDATED_FAMILIARACARGONOMBRE_2);
        assertThat(testPersona.getFamiliaracargonombre3()).isEqualTo(UPDATED_FAMILIARACARGONOMBRE_3);
        assertThat(testPersona.getFamiliaracargonombre4()).isEqualTo(UPDATED_FAMILIARACARGONOMBRE_4);
        assertThat(testPersona.getFamiliaracargonombre5()).isEqualTo(UPDATED_FAMILIARACARGONOMBRE_5);
        assertThat(testPersona.getFamiliaracargodni()).isEqualTo(UPDATED_FAMILIARACARGODNI);
        assertThat(testPersona.getFamiliaracargodni2()).isEqualTo(UPDATED_FAMILIARACARGODNI_2);
        assertThat(testPersona.getFamiliaracargodni3()).isEqualTo(UPDATED_FAMILIARACARGODNI_3);
        assertThat(testPersona.getFamiliaracargodni4()).isEqualTo(UPDATED_FAMILIARACARGODNI_4);
        assertThat(testPersona.getFamiliaracargodni5()).isEqualTo(UPDATED_FAMILIARACARGODNI_5);
        assertThat(testPersona.getFamiliaracargoedad()).isEqualTo(UPDATED_FAMILIARACARGOEDAD);
        assertThat(testPersona.getFamiliaracargoedad2()).isEqualTo(UPDATED_FAMILIARACARGOEDAD_2);
        assertThat(testPersona.getFamiliaracargoedad3()).isEqualTo(UPDATED_FAMILIARACARGOEDAD_3);
        assertThat(testPersona.getFamiliaracargoedad4()).isEqualTo(UPDATED_FAMILIARACARGOEDAD_4);
        assertThat(testPersona.getFamiliaracargoedad5()).isEqualTo(UPDATED_FAMILIARACARGOEDAD_5);
        assertThat(testPersona.getAltura()).isEqualTo(UPDATED_ALTURA);
        assertThat(testPersona.getBarrio()).isEqualTo(UPDATED_BARRIO);
        assertThat(testPersona.getEstudiosincompletos()).isEqualTo(UPDATED_ESTUDIOSINCOMPLETOS);
        assertThat(testPersona.getConyugeapellido()).isEqualTo(UPDATED_CONYUGEAPELLIDO);
        assertThat(testPersona.getConyugenombre()).isEqualTo(UPDATED_CONYUGENOMBRE);
        assertThat(testPersona.getConyugedni()).isEqualTo(UPDATED_CONYUGEDNI);
        assertThat(testPersona.getConyugecuil()).isEqualTo(UPDATED_CONYUGECUIL);
        assertThat(testPersona.getGrupofamiliarapellidonombre()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE);
        assertThat(testPersona.getGrupofamiliarapellidonombre2()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_2);
        assertThat(testPersona.getGrupofamiliarapellidonombre3()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_3);
        assertThat(testPersona.getGrupofamiliarapellidonombre4()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_4);
        assertThat(testPersona.getGrupofamiliarapellidonombre5()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_5);
        assertThat(testPersona.getGrupofamiliarapellidonombre6()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_6);
        assertThat(testPersona.getGrupofamiliarapellidonombre7()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_7);
        assertThat(testPersona.getGrupofamiliarapellidonombre8()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_8);
        assertThat(testPersona.getGrupofamiliarapellidonombre9()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_9);
        assertThat(testPersona.getGrupofamiliarapellidonombre10()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_10);
        assertThat(testPersona.getGrupofamiliarapellidonombre11()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBRE_11);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad2()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_2);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad3()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_3);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad4()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_4);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad5()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_5);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad6()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_6);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad7()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_7);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad8()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_8);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad9()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_9);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad10()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_10);
        assertThat(testPersona.getGrupofamiliarapellidonombreedad11()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREEDAD_11);
        assertThat(testPersona.getGrupofamiliarapellidonombredni()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI);
        assertThat(testPersona.getGrupofamiliarapellidonombredni2()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_2);
        assertThat(testPersona.getGrupofamiliarapellidonombredni3()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_3);
        assertThat(testPersona.getGrupofamiliarapellidonombredni4()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_4);
        assertThat(testPersona.getGrupofamiliarapellidonombredni5()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_5);
        assertThat(testPersona.getGrupofamiliarapellidonombredni6()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_6);
        assertThat(testPersona.getGrupofamiliarapellidonombredni7()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_7);
        assertThat(testPersona.getGrupofamiliarapellidonombredni8()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_8);
        assertThat(testPersona.getGrupofamiliarapellidonombredni9()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_9);
        assertThat(testPersona.getGrupofamiliarapellidonombredni10()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_10);
        assertThat(testPersona.getGrupofamiliarapellidonombredni11()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREDNI_11);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar2()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_2);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar4()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_4);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar3()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_3);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar5()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_5);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar6()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_6);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar7()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_7);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar8()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_8);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar9()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_9);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar10()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_10);
        assertThat(testPersona.getGrupofamiliarapellidonombrefamiliar11()).isEqualTo(UPDATED_GRUPOFAMILIARAPELLIDONOMBREFAMILIAR_11);
    }

    @Test
    @Transactional
    public void updateNonExistingPersona() throws Exception {
        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Create the Persona

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(persona)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersona() throws Exception {
        // Initialize the database
        personaService.save(persona);

        int databaseSizeBeforeDelete = personaRepository.findAll().size();

        // Delete the persona
        restPersonaMockMvc.perform(delete("/api/personas/{id}", persona.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Persona.class);
        Persona persona1 = new Persona();
        persona1.setId(1L);
        Persona persona2 = new Persona();
        persona2.setId(persona1.getId());
        assertThat(persona1).isEqualTo(persona2);
        persona2.setId(2L);
        assertThat(persona1).isNotEqualTo(persona2);
        persona1.setId(null);
        assertThat(persona1).isNotEqualTo(persona2);
    }
}
