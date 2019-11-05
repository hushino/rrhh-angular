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

import com.rrhh.client.domain.PenasDisciplinariasSufridas;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.PenasDisciplinariasSufridasRepository;
import com.rrhh.client.service.dto.PenasDisciplinariasSufridasCriteria;

/**
 * Service for executing complex queries for {@link PenasDisciplinariasSufridas} entities in the database.
 * The main input is a {@link PenasDisciplinariasSufridasCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PenasDisciplinariasSufridas} or a {@link Page} of {@link PenasDisciplinariasSufridas} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PenasDisciplinariasSufridasQueryService extends QueryService<PenasDisciplinariasSufridas> {

    private final Logger log = LoggerFactory.getLogger(PenasDisciplinariasSufridasQueryService.class);

    private final PenasDisciplinariasSufridasRepository penasDisciplinariasSufridasRepository;

    public PenasDisciplinariasSufridasQueryService(PenasDisciplinariasSufridasRepository penasDisciplinariasSufridasRepository) {
        this.penasDisciplinariasSufridasRepository = penasDisciplinariasSufridasRepository;
    }

    /**
     * Return a {@link List} of {@link PenasDisciplinariasSufridas} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PenasDisciplinariasSufridas> findByCriteria(PenasDisciplinariasSufridasCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PenasDisciplinariasSufridas> specification = createSpecification(criteria);
        return penasDisciplinariasSufridasRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link PenasDisciplinariasSufridas} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PenasDisciplinariasSufridas> findByCriteria(PenasDisciplinariasSufridasCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PenasDisciplinariasSufridas> specification = createSpecification(criteria);
        return penasDisciplinariasSufridasRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PenasDisciplinariasSufridasCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PenasDisciplinariasSufridas> specification = createSpecification(criteria);
        return penasDisciplinariasSufridasRepository.count(specification);
    }

    /**
     * Function to convert {@link PenasDisciplinariasSufridasCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PenasDisciplinariasSufridas> createSpecification(PenasDisciplinariasSufridasCriteria criteria) {
        Specification<PenasDisciplinariasSufridas> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PenasDisciplinariasSufridas_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), PenasDisciplinariasSufridas_.fecha));
            }
            if (criteria.getExpediente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExpediente(), PenasDisciplinariasSufridas_.expediente));
            }
            if (criteria.getReferencias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferencias(), PenasDisciplinariasSufridas_.referencias));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(PenasDisciplinariasSufridas_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
