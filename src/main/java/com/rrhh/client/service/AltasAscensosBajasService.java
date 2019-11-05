package com.rrhh.client.service;

import com.rrhh.client.domain.AltasAscensosBajas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link AltasAscensosBajas}.
 */
public interface AltasAscensosBajasService {

    /**
     * Save a altasAscensosBajas.
     *
     * @param altasAscensosBajas the entity to save.
     * @return the persisted entity.
     */
    AltasAscensosBajas save(AltasAscensosBajas altasAscensosBajas);

    /**
     * Get all the altasAscensosBajas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AltasAscensosBajas> findAll(Pageable pageable);


    /**
     * Get the "id" altasAscensosBajas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AltasAscensosBajas> findOne(Long id);

    /**
     * Delete the "id" altasAscensosBajas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
