package com.rrhh.client.service;

import com.rrhh.client.domain.Concpremios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Concpremios}.
 */
public interface ConcpremiosService {

    /**
     * Save a concpremios.
     *
     * @param concpremios the entity to save.
     * @return the persisted entity.
     */
    Concpremios save(Concpremios concpremios);

    /**
     * Get all the concpremios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Concpremios> findAll(Pageable pageable);


    /**
     * Get the "id" concpremios.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Concpremios> findOne(Long id);

    /**
     * Delete the "id" concpremios.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
