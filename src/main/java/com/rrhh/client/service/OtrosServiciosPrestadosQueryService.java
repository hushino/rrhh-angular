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

import com.rrhh.client.domain.OtrosServiciosPrestados;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.OtrosServiciosPrestadosRepository;
import com.rrhh.client.service.dto.OtrosServiciosPrestadosCriteria;

/**
 * Service for executing complex queries for {@link OtrosServiciosPrestados} entities in the database.
 * The main input is a {@link OtrosServiciosPrestadosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OtrosServiciosPrestados} or a {@link Page} of {@link OtrosServiciosPrestados} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OtrosServiciosPrestadosQueryService extends QueryService<OtrosServiciosPrestados> {

    private final Logger log = LoggerFactory.getLogger(OtrosServiciosPrestadosQueryService.class);

    private final OtrosServiciosPrestadosRepository otrosServiciosPrestadosRepository;

    public OtrosServiciosPrestadosQueryService(OtrosServiciosPrestadosRepository otrosServiciosPrestadosRepository) {
        this.otrosServiciosPrestadosRepository = otrosServiciosPrestadosRepository;
    }

    /**
     * Return a {@link List} of {@link OtrosServiciosPrestados} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OtrosServiciosPrestados> findByCriteria(OtrosServiciosPrestadosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OtrosServiciosPrestados> specification = createSpecification(criteria);
        return otrosServiciosPrestadosRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link OtrosServiciosPrestados} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OtrosServiciosPrestados> findByCriteria(OtrosServiciosPrestadosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OtrosServiciosPrestados> specification = createSpecification(criteria);
        return otrosServiciosPrestadosRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OtrosServiciosPrestadosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OtrosServiciosPrestados> specification = createSpecification(criteria);
        return otrosServiciosPrestadosRepository.count(specification);
    }

    /**
     * Function to convert {@link OtrosServiciosPrestadosCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OtrosServiciosPrestados> createSpecification(OtrosServiciosPrestadosCriteria criteria) {
        Specification<OtrosServiciosPrestados> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), OtrosServiciosPrestados_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), OtrosServiciosPrestados_.fecha));
            }
            if (criteria.getReferencias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferencias(), OtrosServiciosPrestados_.referencias));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(OtrosServiciosPrestados_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
