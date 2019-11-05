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

import com.rrhh.client.domain.Garantia;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.GarantiaRepository;
import com.rrhh.client.service.dto.GarantiaCriteria;

/**
 * Service for executing complex queries for {@link Garantia} entities in the database.
 * The main input is a {@link GarantiaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Garantia} or a {@link Page} of {@link Garantia} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GarantiaQueryService extends QueryService<Garantia> {

    private final Logger log = LoggerFactory.getLogger(GarantiaQueryService.class);

    private final GarantiaRepository garantiaRepository;

    public GarantiaQueryService(GarantiaRepository garantiaRepository) {
        this.garantiaRepository = garantiaRepository;
    }

    /**
     * Return a {@link List} of {@link Garantia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Garantia> findByCriteria(GarantiaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Garantia> specification = createSpecification(criteria);
        return garantiaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Garantia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Garantia> findByCriteria(GarantiaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Garantia> specification = createSpecification(criteria);
        return garantiaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GarantiaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Garantia> specification = createSpecification(criteria);
        return garantiaRepository.count(specification);
    }

    /**
     * Function to convert {@link GarantiaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Garantia> createSpecification(GarantiaCriteria criteria) {
        Specification<Garantia> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Garantia_.id));
            }
            if (criteria.getPresentadaFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPresentadaFecha(), Garantia_.presentadaFecha));
            }
            if (criteria.getGarantia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGarantia(), Garantia_.garantia));
            }
            if (criteria.getObservaciones() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservaciones(), Garantia_.observaciones));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(Garantia_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
