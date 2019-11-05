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

import com.rrhh.client.domain.Licencia;
import com.rrhh.client.domain.*; // for static metamodels
import com.rrhh.client.repository.LicenciaRepository;
import com.rrhh.client.service.dto.LicenciaCriteria;

/**
 * Service for executing complex queries for {@link Licencia} entities in the database.
 * The main input is a {@link LicenciaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Licencia} or a {@link Page} of {@link Licencia} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LicenciaQueryService extends QueryService<Licencia> {

    private final Logger log = LoggerFactory.getLogger(LicenciaQueryService.class);

    private final LicenciaRepository licenciaRepository;

    public LicenciaQueryService(LicenciaRepository licenciaRepository) {
        this.licenciaRepository = licenciaRepository;
    }

    /**
     * Return a {@link List} of {@link Licencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Licencia> findByCriteria(LicenciaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Licencia> specification = createSpecification(criteria);
        return licenciaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Licencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Licencia> findByCriteria(LicenciaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Licencia> specification = createSpecification(criteria);
        return licenciaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LicenciaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Licencia> specification = createSpecification(criteria);
        return licenciaRepository.count(specification);
    }

    /**
     * Function to convert {@link LicenciaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Licencia> createSpecification(LicenciaCriteria criteria) {
        Specification<Licencia> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Licencia_.id));
            }
            if (criteria.getFechaLicencia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaLicencia(), Licencia_.fechaLicencia));
            }
            if (criteria.getReferencias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferencias(), Licencia_.referencias));
            }
            if (criteria.getNumeroDeDias() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroDeDias(), Licencia_.numeroDeDias));
            }
            if (criteria.getObservaciones() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservaciones(), Licencia_.observaciones));
            }
            if (criteria.getUsuariosMod() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUsuariosMod(), Licencia_.usuariosMod));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(Licencia_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
