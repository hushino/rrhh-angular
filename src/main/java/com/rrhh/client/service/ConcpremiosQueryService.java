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

import com.rrhh.client.domain.Concpremios;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.ConcpremiosRepository;
import com.rrhh.client.service.dto.ConcpremiosCriteria;

/**
 * Service for executing complex queries for {@link Concpremios} entities in the database.
 * The main input is a {@link ConcpremiosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Concpremios} or a {@link Page} of {@link Concpremios} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ConcpremiosQueryService extends QueryService<Concpremios> {

    private final Logger log = LoggerFactory.getLogger(ConcpremiosQueryService.class);

    private final ConcpremiosRepository concpremiosRepository;

    public ConcpremiosQueryService(ConcpremiosRepository concpremiosRepository) {
        this.concpremiosRepository = concpremiosRepository;
    }

    /**
     * Return a {@link List} of {@link Concpremios} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Concpremios> findByCriteria(ConcpremiosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Concpremios> specification = createSpecification(criteria);
        return concpremiosRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Concpremios} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Concpremios> findByCriteria(ConcpremiosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Concpremios> specification = createSpecification(criteria);
        return concpremiosRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ConcpremiosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Concpremios> specification = createSpecification(criteria);
        return concpremiosRepository.count(specification);
    }

    /**
     * Function to convert {@link ConcpremiosCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Concpremios> createSpecification(ConcpremiosCriteria criteria) {
        Specification<Concpremios> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Concpremios_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), Concpremios_.fecha));
            }
            if (criteria.getReferencias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferencias(), Concpremios_.referencias));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(Concpremios_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
