package com.rrhh.client.service;

import com.rrhh.client.domain.OtrosServiciosPrestados;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link OtrosServiciosPrestados}.
 */
public interface OtrosServiciosPrestadosService {

    /**
     * Save a otrosServiciosPrestados.
     *
     * @param otrosServiciosPrestados the entity to save.
     * @return the persisted entity.
     */
    OtrosServiciosPrestados save(OtrosServiciosPrestados otrosServiciosPrestados);

    /**
     * Get all the otrosServiciosPrestados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OtrosServiciosPrestados> findAll(Pageable pageable);


    /**
     * Get the "id" otrosServiciosPrestados.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OtrosServiciosPrestados> findOne(Long id);

    /**
     * Delete the "id" otrosServiciosPrestados.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
