package com.rrhh.client.service;

import com.rrhh.client.domain.Embargos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Embargos}.
 */
public interface EmbargosService {

    /**
     * Save a embargos.
     *
     * @param embargos the entity to save.
     * @return the persisted entity.
     */
    Embargos save(Embargos embargos);

    /**
     * Get all the embargos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Embargos> findAll(Pageable pageable);


    /**
     * Get the "id" embargos.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Embargos> findOne(Long id);

    /**
     * Delete the "id" embargos.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
