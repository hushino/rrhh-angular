package com.rrhh.client.service;

import com.rrhh.client.domain.Licencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Licencia}.
 */
public interface LicenciaService {

    /**
     * Save a licencia.
     *
     * @param licencia the entity to save.
     * @return the persisted entity.
     */
    Licencia save(Licencia licencia);

    /**
     * Get all the licencias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Licencia> findAll(Pageable pageable);


    /**
     * Get the "id" licencia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Licencia> findOne(Long id);

    /**
     * Delete the "id" licencia.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
