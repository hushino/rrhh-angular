package com.rrhh.client.service.impl;

import com.rrhh.client.service.ConceptoConocimientosEspecialesClasificacionPremiosService;
import com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios;
import com.rrhh.client.repository.ConceptoConocimientosEspecialesClasificacionPremiosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ConceptoConocimientosEspecialesClasificacionPremios}.
 */
@Service
@Transactional
public class ConceptoConocimientosEspecialesClasificacionPremiosServiceImpl implements ConceptoConocimientosEspecialesClasificacionPremiosService {

    private final Logger log = LoggerFactory.getLogger(ConceptoConocimientosEspecialesClasificacionPremiosServiceImpl.class);

    private final ConceptoConocimientosEspecialesClasificacionPremiosRepository conceptoConocimientosEspecialesClasificacionPremiosRepository;

    public ConceptoConocimientosEspecialesClasificacionPremiosServiceImpl(ConceptoConocimientosEspecialesClasificacionPremiosRepository conceptoConocimientosEspecialesClasificacionPremiosRepository) {
        this.conceptoConocimientosEspecialesClasificacionPremiosRepository = conceptoConocimientosEspecialesClasificacionPremiosRepository;
    }

    /**
     * Save a conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param conceptoConocimientosEspecialesClasificacionPremios the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ConceptoConocimientosEspecialesClasificacionPremios save(ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios) {
        log.debug("Request to save ConceptoConocimientosEspecialesClasificacionPremios : {}", conceptoConocimientosEspecialesClasificacionPremios);
        return conceptoConocimientosEspecialesClasificacionPremiosRepository.save(conceptoConocimientosEspecialesClasificacionPremios);
    }

    /**
     * Get all the conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConceptoConocimientosEspecialesClasificacionPremios> findAll(Pageable pageable) {
        log.debug("Request to get all ConceptoConocimientosEspecialesClasificacionPremios");
        return conceptoConocimientosEspecialesClasificacionPremiosRepository.findAll(pageable);
    }


    /**
     * Get one conceptoConocimientosEspecialesClasificacionPremios by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConceptoConocimientosEspecialesClasificacionPremios> findOne(Long id) {
        log.debug("Request to get ConceptoConocimientosEspecialesClasificacionPremios : {}", id);
        return conceptoConocimientosEspecialesClasificacionPremiosRepository.findById(id);
    }

    /**
     * Delete the conceptoConocimientosEspecialesClasificacionPremios by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConceptoConocimientosEspecialesClasificacionPremios : {}", id);
        conceptoConocimientosEspecialesClasificacionPremiosRepository.deleteById(id);
    }
}
