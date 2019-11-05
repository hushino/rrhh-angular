package com.rrhh.client.service;

import com.rrhh.client.domain.Garantia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Garantia}.
 */
public interface GarantiaService {

    /**
     * Save a garantia.
     *
     * @param garantia the entity to save.
     * @return the persisted entity.
     */
    Garantia save(Garantia garantia);

    /**
     * Get all the garantias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Garantia> findAll(Pageable pageable);


    /**
     * Get the "id" garantia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Garantia> findOne(Long id);

    /**
     * Delete the "id" garantia.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
