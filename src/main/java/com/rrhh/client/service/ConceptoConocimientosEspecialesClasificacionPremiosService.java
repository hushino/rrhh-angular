package com.rrhh.client.service;

import com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ConceptoConocimientosEspecialesClasificacionPremios}.
 */
public interface ConceptoConocimientosEspecialesClasificacionPremiosService {

    /**
     * Save a conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param conceptoConocimientosEspecialesClasificacionPremios the entity to save.
     * @return the persisted entity.
     */
    ConceptoConocimientosEspecialesClasificacionPremios save(ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios);

    /**
     * Get all the conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ConceptoConocimientosEspecialesClasificacionPremios> findAll(Pageable pageable);


    /**
     * Get the "id" conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConceptoConocimientosEspecialesClasificacionPremios> findOne(Long id);

    /**
     * Delete the "id" conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
