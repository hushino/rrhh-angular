package com.rrhh.client.service;

import com.rrhh.client.domain.Persona;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Persona}.
 */
public interface PersonaService {

    /**
     * Save a persona.
     *
     * @param persona the entity to save.
     * @return the persisted entity.
     */
    Persona save(Persona persona);

    /**
     * Get all the personas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Persona> findAll(Pageable pageable);


    /**
     * Get the "id" persona.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Persona> findOne(Long id);

    /**
     * Delete the "id" persona.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
