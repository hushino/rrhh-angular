package com.rrhh.client.service;

import com.rrhh.client.domain.PenasDisciplinariasSufridas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link PenasDisciplinariasSufridas}.
 */
public interface PenasDisciplinariasSufridasService {

    /**
     * Save a penasDisciplinariasSufridas.
     *
     * @param penasDisciplinariasSufridas the entity to save.
     * @return the persisted entity.
     */
    PenasDisciplinariasSufridas save(PenasDisciplinariasSufridas penasDisciplinariasSufridas);

    /**
     * Get all the penasDisciplinariasSufridas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PenasDisciplinariasSufridas> findAll(Pageable pageable);


    /**
     * Get the "id" penasDisciplinariasSufridas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PenasDisciplinariasSufridas> findOne(Long id);

    /**
     * Delete the "id" penasDisciplinariasSufridas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
