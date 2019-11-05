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

import com.rrhh.client.domain.AltasAscensosBajas;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.AltasAscensosBajasRepository;
import com.rrhh.client.service.dto.AltasAscensosBajasCriteria;

/**
 * Service for executing complex queries for {@link AltasAscensosBajas} entities in the database.
 * The main input is a {@link AltasAscensosBajasCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AltasAscensosBajas} or a {@link Page} of {@link AltasAscensosBajas} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AltasAscensosBajasQueryService extends QueryService<AltasAscensosBajas> {

    private final Logger log = LoggerFactory.getLogger(AltasAscensosBajasQueryService.class);

    private final AltasAscensosBajasRepository altasAscensosBajasRepository;

    public AltasAscensosBajasQueryService(AltasAscensosBajasRepository altasAscensosBajasRepository) {
        this.altasAscensosBajasRepository = altasAscensosBajasRepository;
    }

    /**
     * Return a {@link List} of {@link AltasAscensosBajas} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AltasAscensosBajas> findByCriteria(AltasAscensosBajasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AltasAscensosBajas> specification = createSpecification(criteria);
        return altasAscensosBajasRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link AltasAscensosBajas} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AltasAscensosBajas> findByCriteria(AltasAscensosBajasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AltasAscensosBajas> specification = createSpecification(criteria);
        return altasAscensosBajasRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AltasAscensosBajasCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AltasAscensosBajas> specification = createSpecification(criteria);
        return altasAscensosBajasRepository.count(specification);
    }

    /**
     * Function to convert {@link AltasAscensosBajasCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AltasAscensosBajas> createSpecification(AltasAscensosBajasCriteria criteria) {
        Specification<AltasAscensosBajas> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AltasAscensosBajas_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), AltasAscensosBajas_.fecha));
            }
            if (criteria.getCargo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCargo(), AltasAscensosBajas_.cargo));
            }
            if (criteria.getObservaciones() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservaciones(), AltasAscensosBajas_.observaciones));
            }
            if (criteria.getExpediente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExpediente(), AltasAscensosBajas_.expediente));
            }
            if (criteria.getPrestaservicioen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrestaservicioen(), AltasAscensosBajas_.prestaservicioen));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(AltasAscensosBajas_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
