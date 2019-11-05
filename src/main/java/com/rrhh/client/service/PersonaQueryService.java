package com.rrhh.client.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.rrhh.client.domain.Persona;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.PersonaRepository;
import com.rrhh.client.service.dto.PersonaCriteria;

/**
 * Service for executing complex queries for {@link Persona} entities in the database.
 * The main input is a {@link PersonaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Persona} or a {@link Page} of {@link Persona} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PersonaQueryService extends QueryService<Persona> {

    private final Logger log = LoggerFactory.getLogger(PersonaQueryService.class);

    private final PersonaRepository personaRepository;

    public PersonaQueryService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    /**
     * Return a {@link List} of {@link Persona} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Persona> findByCriteria(PersonaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Persona> specification = createSpecification(criteria);
        return personaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Persona} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Persona> findByCriteria(PersonaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Persona> specification = createSpecification(criteria);
        return personaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PersonaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Persona> specification = createSpecification(criteria);
        return personaRepository.count(specification);
    }

    /**
     * Function to convert {@link PersonaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Persona> createSpecification(PersonaCriteria criteria) {
        Specification<Persona> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Persona_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), Persona_.nombre));
            }
            if (criteria.getApellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApellido(), Persona_.apellido));
            }
            if (criteria.getCuil() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCuil(), Persona_.cuil));
            }
            if (criteria.getDni() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDni(), Persona_.dni));
            }
            if (criteria.getLegajo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLegajo(), Persona_.legajo));
            }
            if (criteria.getApodo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApodo(), Persona_.apodo));
            }
            if (criteria.getSoltero() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSoltero(), Persona_.soltero));
            }
            if (criteria.getCasado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCasado(), Persona_.casado));
            }
            if (criteria.getConviviente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConviviente(), Persona_.conviviente));
            }
            if (criteria.getViudo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViudo(), Persona_.viudo));
            }
            if (criteria.getDomicilio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDomicilio(), Persona_.domicilio));
            }
            if (criteria.getLugar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLugar(), Persona_.lugar));
            }
            if (criteria.getCalle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCalle(), Persona_.calle));
            }
            if (criteria.getNumero() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumero(), Persona_.numero));
            }
            if (criteria.getTelefonofijo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefonofijo(), Persona_.telefonofijo));
            }
            if (criteria.getNumerodecelular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumerodecelular(), Persona_.numerodecelular));
            }
            if (criteria.getOficioprofecion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOficioprofecion(), Persona_.oficioprofecion));
            }
            if (criteria.getNiveldeestudios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNiveldeestudios(), Persona_.niveldeestudios));
            }
            if (criteria.getGruposanguineo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGruposanguineo(), Persona_.gruposanguineo));
            }
            if (criteria.getFactor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFactor(), Persona_.factor));
            }
            if (criteria.getDonante() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDonante(), Persona_.donante));
            }
            if (criteria.getDiabetes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiabetes(), Persona_.diabetes));
            }
            if (criteria.getHipertension() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHipertension(), Persona_.hipertension));
            }
            if (criteria.getAlergias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAlergias(), Persona_.alergias));
            }
            if (criteria.getAsma() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAsma(), Persona_.asma));
            }
            if (criteria.getOtros() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOtros(), Persona_.otros));
            }
            if (criteria.getFechadeingreso() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechadeingreso(), Persona_.fechadeingreso));
            }
            if (criteria.getInstrumentolegal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstrumentolegal(), Persona_.instrumentolegal));
            }
            if (criteria.getCategoria() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCategoria(), Persona_.categoria));
            }
            if (criteria.getItem() != null) {
                specification = specification.and(buildStringSpecification(criteria.getItem(), Persona_.item));
            }
            if (criteria.getPlanta() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlanta(), Persona_.planta));
            }
            if (criteria.getArea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArea(), Persona_.area));
            }
            if (criteria.getDireccion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccion(), Persona_.direccion));
            }
            if (criteria.getAnnos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnnos(), Persona_.annos));
            }
            if (criteria.getMeses() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeses(), Persona_.meses));
            }
            if (criteria.getDias() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDias(), Persona_.dias));
            }
            if (criteria.getRealizocomputodeservicios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRealizocomputodeservicios(), Persona_.realizocomputodeservicios));
            }
            if (criteria.getPoseeconocimientoenmaquinasviales() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPoseeconocimientoenmaquinasviales(), Persona_.poseeconocimientoenmaquinasviales));
            }
            if (criteria.getCasoemergenciacelular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCasoemergenciacelular(), Persona_.casoemergenciacelular));
            }
            if (criteria.getCasoemergenciafijo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCasoemergenciafijo(), Persona_.casoemergenciafijo));
            }
            if (criteria.getCasoemergencianombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCasoemergencianombre(), Persona_.casoemergencianombre));
            }
            if (criteria.getCasoemergenciacelular2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCasoemergenciacelular2(), Persona_.casoemergenciacelular2));
            }
            if (criteria.getCasoemergenciafijo2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCasoemergenciafijo2(), Persona_.casoemergenciafijo2));
            }
            if (criteria.getCasoemergencianombre2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCasoemergencianombre2(), Persona_.casoemergencianombre2));
            }
            if (criteria.getFamiliaracargonombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargonombre(), Persona_.familiaracargonombre));
            }
            if (criteria.getFamiliaracargonombre2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargonombre2(), Persona_.familiaracargonombre2));
            }
            if (criteria.getFamiliaracargonombre3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargonombre3(), Persona_.familiaracargonombre3));
            }
            if (criteria.getFamiliaracargonombre4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargonombre4(), Persona_.familiaracargonombre4));
            }
            if (criteria.getFamiliaracargonombre5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargonombre5(), Persona_.familiaracargonombre5));
            }
            if (criteria.getFamiliaracargodni() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargodni(), Persona_.familiaracargodni));
            }
            if (criteria.getFamiliaracargodni2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargodni2(), Persona_.familiaracargodni2));
            }
            if (criteria.getFamiliaracargodni3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargodni3(), Persona_.familiaracargodni3));
            }
            if (criteria.getFamiliaracargodni4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargodni4(), Persona_.familiaracargodni4));
            }
            if (criteria.getFamiliaracargodni5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargodni5(), Persona_.familiaracargodni5));
            }
            if (criteria.getFamiliaracargoedad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargoedad(), Persona_.familiaracargoedad));
            }
            if (criteria.getFamiliaracargoedad2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargoedad2(), Persona_.familiaracargoedad2));
            }
            if (criteria.getFamiliaracargoedad3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargoedad3(), Persona_.familiaracargoedad3));
            }
            if (criteria.getFamiliaracargoedad4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargoedad4(), Persona_.familiaracargoedad4));
            }
            if (criteria.getFamiliaracargoedad5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamiliaracargoedad5(), Persona_.familiaracargoedad5));
            }
            if (criteria.getAltura() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAltura(), Persona_.altura));
            }
            if (criteria.getBarrio() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBarrio(), Persona_.barrio));
            }
            if (criteria.getEstudiosincompletos() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstudiosincompletos(), Persona_.estudiosincompletos));
            }
            if (criteria.getConyugeapellido() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConyugeapellido(), Persona_.conyugeapellido));
            }
            if (criteria.getConyugenombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConyugenombre(), Persona_.conyugenombre));
            }
            if (criteria.getConyugedni() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConyugedni(), Persona_.conyugedni));
            }
            if (criteria.getConyugecuil() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConyugecuil(), Persona_.conyugecuil));
            }
            if (criteria.getGrupofamiliarapellidonombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre(), Persona_.grupofamiliarapellidonombre));
            }
            if (criteria.getGrupofamiliarapellidonombre2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre2(), Persona_.grupofamiliarapellidonombre2));
            }
            if (criteria.getGrupofamiliarapellidonombre3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre3(), Persona_.grupofamiliarapellidonombre3));
            }
            if (criteria.getGrupofamiliarapellidonombre4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre4(), Persona_.grupofamiliarapellidonombre4));
            }
            if (criteria.getGrupofamiliarapellidonombre5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre5(), Persona_.grupofamiliarapellidonombre5));
            }
            if (criteria.getGrupofamiliarapellidonombre6() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre6(), Persona_.grupofamiliarapellidonombre6));
            }
            if (criteria.getGrupofamiliarapellidonombre7() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre7(), Persona_.grupofamiliarapellidonombre7));
            }
            if (criteria.getGrupofamiliarapellidonombre8() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre8(), Persona_.grupofamiliarapellidonombre8));
            }
            if (criteria.getGrupofamiliarapellidonombre9() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre9(), Persona_.grupofamiliarapellidonombre9));
            }
            if (criteria.getGrupofamiliarapellidonombre10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre10(), Persona_.grupofamiliarapellidonombre10));
            }
            if (criteria.getGrupofamiliarapellidonombre11() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombre11(), Persona_.grupofamiliarapellidonombre11));
            }
            if (criteria.getGrupofamiliarapellidonombreedad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad(), Persona_.grupofamiliarapellidonombreedad));
            }
            if (criteria.getGrupofamiliarapellidonombreedad2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad2(), Persona_.grupofamiliarapellidonombreedad2));
            }
            if (criteria.getGrupofamiliarapellidonombreedad3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad3(), Persona_.grupofamiliarapellidonombreedad3));
            }
            if (criteria.getGrupofamiliarapellidonombreedad4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad4(), Persona_.grupofamiliarapellidonombreedad4));
            }
            if (criteria.getGrupofamiliarapellidonombreedad5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad5(), Persona_.grupofamiliarapellidonombreedad5));
            }
            if (criteria.getGrupofamiliarapellidonombreedad6() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad6(), Persona_.grupofamiliarapellidonombreedad6));
            }
            if (criteria.getGrupofamiliarapellidonombreedad7() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad7(), Persona_.grupofamiliarapellidonombreedad7));
            }
            if (criteria.getGrupofamiliarapellidonombreedad8() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad8(), Persona_.grupofamiliarapellidonombreedad8));
            }
            if (criteria.getGrupofamiliarapellidonombreedad9() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad9(), Persona_.grupofamiliarapellidonombreedad9));
            }
            if (criteria.getGrupofamiliarapellidonombreedad10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad10(), Persona_.grupofamiliarapellidonombreedad10));
            }
            if (criteria.getGrupofamiliarapellidonombreedad11() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombreedad11(), Persona_.grupofamiliarapellidonombreedad11));
            }
            if (criteria.getGrupofamiliarapellidonombredni() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni(), Persona_.grupofamiliarapellidonombredni));
            }
            if (criteria.getGrupofamiliarapellidonombredni2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni2(), Persona_.grupofamiliarapellidonombredni2));
            }
            if (criteria.getGrupofamiliarapellidonombredni3() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni3(), Persona_.grupofamiliarapellidonombredni3));
            }
            if (criteria.getGrupofamiliarapellidonombredni4() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni4(), Persona_.grupofamiliarapellidonombredni4));
            }
            if (criteria.getGrupofamiliarapellidonombredni5() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni5(), Persona_.grupofamiliarapellidonombredni5));
            }
            if (criteria.getGrupofamiliarapellidonombredni6() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni6(), Persona_.grupofamiliarapellidonombredni6));
            }
            if (criteria.getGrupofamiliarapellidonombredni7() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni7(), Persona_.grupofamiliarapellidonombredni7));
            }
            if (criteria.getGrupofamiliarapellidonombredni8() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni8(), Persona_.grupofamiliarapellidonombredni8));
            }
            if (criteria.getGrupofamiliarapellidonombredni9() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni9(), Persona_.grupofamiliarapellidonombredni9));
            }
            if (criteria.getGrupofamiliarapellidonombredni10() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni10(), Persona_.grupofamiliarapellidonombredni10));
            }
            if (criteria.getGrupofamiliarapellidonombredni11() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGrupofamiliarapellidonombredni11(), Persona_.grupofamiliarapellidonombredni11));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar(), Persona_.grupofamiliarapellidonombrefamiliar));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar2(), Persona_.grupofamiliarapellidonombrefamiliar2));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar4(), Persona_.grupofamiliarapellidonombrefamiliar4));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar3(), Persona_.grupofamiliarapellidonombrefamiliar3));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar5(), Persona_.grupofamiliarapellidonombrefamiliar5));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar6() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar6(), Persona_.grupofamiliarapellidonombrefamiliar6));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar7() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar7(), Persona_.grupofamiliarapellidonombrefamiliar7));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar8() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar8(), Persona_.grupofamiliarapellidonombrefamiliar8));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar9() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar9(), Persona_.grupofamiliarapellidonombrefamiliar9));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar10(), Persona_.grupofamiliarapellidonombrefamiliar10));
            }
            if (criteria.getGrupofamiliarapellidonombrefamiliar11() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrupofamiliarapellidonombrefamiliar11(), Persona_.grupofamiliarapellidonombrefamiliar11));
            }
            if (criteria.getLicenciaId() != null) {
                specification = specification.and(buildSpecification(criteria.getLicenciaId(),
                    root -> root.join(Persona_.licencias, JoinType.LEFT).get(Licencia_.id)));
            }
            if (criteria.getAltasAscensosBajasId() != null) {
                specification = specification.and(buildSpecification(criteria.getAltasAscensosBajasId(),
                    root -> root.join(Persona_.altasAscensosBajas, JoinType.LEFT).get(AltasAscensosBajas_.id)));
            }
            if (criteria.getConceptoConocimientosEspecialesClasificacionPremiosId() != null) {
                specification = specification.and(buildSpecification(criteria.getConceptoConocimientosEspecialesClasificacionPremiosId(),
                    root -> root.join(Persona_.conceptoConocimientosEspecialesClasificacionPremios, JoinType.LEFT).get(ConceptoConocimientosEspecialesClasificacionPremios_.id)));
            }
            if (criteria.getEmbargosId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmbargosId(),
                    root -> root.join(Persona_.embargos, JoinType.LEFT).get(Embargos_.id)));
            }
            if (criteria.getGarantiaId() != null) {
                specification = specification.and(buildSpecification(criteria.getGarantiaId(),
                    root -> root.join(Persona_.garantias, JoinType.LEFT).get(Garantia_.id)));
            }
            if (criteria.getOtrosServiciosPrestadosId() != null) {
                specification = specification.and(buildSpecification(criteria.getOtrosServiciosPrestadosId(),
                    root -> root.join(Persona_.otrosServiciosPrestados, JoinType.LEFT).get(OtrosServiciosPrestados_.id)));
            }
            if (criteria.getPenasDisciplinariasSufridasId() != null) {
                specification = specification.and(buildSpecification(criteria.getPenasDisciplinariasSufridasId(),
                    root -> root.join(Persona_.penasDisciplinariasSufridas, JoinType.LEFT).get(PenasDisciplinariasSufridas_.id)));
            }
        }
        return specification;
    }
}
