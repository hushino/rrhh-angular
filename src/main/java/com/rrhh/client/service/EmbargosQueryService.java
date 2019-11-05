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

import com.rrhh.client.domain.Embargos;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.EmbargosRepository;
import com.rrhh.client.service.dto.EmbargosCriteria;

/**
 * Service for executing complex queries for {@link Embargos} entities in the database.
 * The main input is a {@link EmbargosCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Embargos} or a {@link Page} of {@link Embargos} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmbargosQueryService extends QueryService<Embargos> {

    private final Logger log = LoggerFactory.getLogger(EmbargosQueryService.class);

    private final EmbargosRepository embargosRepository;

    public EmbargosQueryService(EmbargosRepository embargosRepository) {
        this.embargosRepository = embargosRepository;
    }

    /**
     * Return a {@link List} of {@link Embargos} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Embargos> findByCriteria(EmbargosCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Embargos> specification = createSpecification(criteria);
        return embargosRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Embargos} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Embargos> findByCriteria(EmbargosCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Embargos> specification = createSpecification(criteria);
        return embargosRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmbargosCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Embargos> specification = createSpecification(criteria);
        return embargosRepository.count(specification);
    }

    /**
     * Function to convert {@link EmbargosCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Embargos> createSpecification(EmbargosCriteria criteria) {
        Specification<Embargos> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Embargos_.id));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), Embargos_.fecha));
            }
            if (criteria.getJuzgado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJuzgado(), Embargos_.juzgado));
            }
            if (criteria.getAcreedor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcreedor(), Embargos_.acreedor));
            }
            if (criteria.getCantidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCantidad(), Embargos_.cantidad));
            }
            if (criteria.getExpediente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExpediente(), Embargos_.expediente));
            }
            if (criteria.getFianzaODeudaPropia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFianzaODeudaPropia(), Embargos_.fianzaODeudaPropia));
            }
            if (criteria.getOrigenDeLaDeuda() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOrigenDeLaDeuda(), Embargos_.origenDeLaDeuda));
            }
            if (criteria.getObservaciones() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservaciones(), Embargos_.observaciones));
            }
            if (criteria.getLevantada() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLevantada(), Embargos_.levantada));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(Embargos_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
