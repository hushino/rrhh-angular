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

import com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.ConceptoConocimientosEspecialesClasificacionPremiosRepository;
import com.rrhh.client.service.dto.ConceptoConocimientosEspecialesClasificacionPremiosCriteria;

/**
 * Service for executing complex queries for {@link ConceptoConocimientosEspecialesClasificacionPremios} entities in the database.
 * The main input is a {@link ConceptoConocimientosEspecialesClasificacionPremiosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ConceptoConocimientosEspecialesClasificacionPremios} or a {@link Page} of {@link ConceptoConocimientosEspecialesClasificacionPremios} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ConceptoConocimientosEspecialesClasificacionPremiosQueryService extends QueryService<ConceptoConocimientosEspecialesClasificacionPremios> {

    private final Logger log = LoggerFactory.getLogger(ConceptoConocimientosEspecialesClasificacionPremiosQueryService.class);

    private final ConceptoConocimientosEspecialesClasificacionPremiosRepository conceptoConocimientosEspecialesClasificacionPremiosRepository;

    public ConceptoConocimientosEspecialesClasificacionPremiosQueryService(ConceptoConocimientosEspecialesClasificacionPremiosRepository conceptoConocimientosEspecialesClasificacionPremiosRepository) {
        this.conceptoConocimientosEspecialesClasificacionPremiosRepository = conceptoConocimientosEspecialesClasificacionPremiosRepository;
    }

    /**
     * Return a {@link List} of {@link ConceptoConocimientosEspecialesClasificacionPremios} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ConceptoConocimientosEspecialesClasificacionPremios> findByCriteria(ConceptoConocimientosEspecialesClasificacionPremiosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ConceptoConocimientosEspecialesClasificacionPremios> specification = createSpecification(criteria);
        return conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link ConceptoConocimientosEspecialesClasificacionPremios} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ConceptoConocimientosEspecialesClasificacionPremios> findByCriteria(ConceptoConocimientosEspecialesClasificacionPremiosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ConceptoConocimientosEspecialesClasificacionPremios> specification = createSpecification(criteria);
        return conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ConceptoConocimientosEspecialesClasificacionPremiosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ConceptoConocimientosEspecialesClasificacionPremios> specification = createSpecification(criteria);
        return conceptoConocimientosEspecialesClasificacionPremiosRepository.count(specification);
    }

    /**
     * Function to convert {@link ConceptoConocimientosEspecialesClasificacionPremiosCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ConceptoConocimientosEspecialesClasificacionPremios> createSpecification(ConceptoConocimientosEspecialesClasificacionPremiosCriteria criteria) {
        Specification<ConceptoConocimientosEspecialesClasificacionPremios> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ConceptoConocimientosEspecialesClasificacionPremios_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), ConceptoConocimientosEspecialesClasificacionPremios_.fecha));
            }
            if (criteria.getReferencias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferencias(), ConceptoConocimientosEspecialesClasificacionPremios_.referencias));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(ConceptoConocimientosEspecialesClasificacionPremios_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
