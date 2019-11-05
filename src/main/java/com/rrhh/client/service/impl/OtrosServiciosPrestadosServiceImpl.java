package com.rrhh.client.service.impl;

import com.rrhh.client.service.OtrosServiciosPrestadosService;
import com.rrhh.client.domain.OtrosServiciosPrestados;
import com.rrhh.client.repository.OtrosServiciosPrestadosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OtrosServiciosPrestados}.
 */
@Service
@Transactional
public class OtrosServiciosPrestadosServiceImpl implements OtrosServiciosPrestadosService {

    private final Logger log = LoggerFactory.getLogger(OtrosServiciosPrestadosServiceImpl.class);

    private final OtrosServiciosPrestadosRepository otrosServiciosPrestadosRepository;

    public OtrosServiciosPrestadosServiceImpl(OtrosServiciosPrestadosRepository otrosServiciosPrestadosRepository) {
        this.otrosServiciosPrestadosRepository = otrosServiciosPrestadosRepository;
    }

    /**
     * Save a otrosServiciosPrestados.
     *
     * @param otrosServiciosPrestados the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OtrosServiciosPrestados save(OtrosServiciosPrestados otrosServiciosPrestados) {
        log.debug("Request to save OtrosServiciosPrestados : {}", otrosServiciosPrestados);
        return otrosServiciosPrestadosRepository.save(otrosServiciosPrestados);
    }

    /**
     * Get all the otrosServiciosPrestados.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OtrosServiciosPrestados> findAll(Pageable pageable) {
        log.debug("Request to get all OtrosServiciosPrestados");
        return otrosServiciosPrestadosRepository.findAll(pageable);
    }


    /**
     * Get one otrosServiciosPrestados by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OtrosServiciosPrestados> findOne(Long id) {
        log.debug("Request to get OtrosServiciosPrestados : {}", id);
        return otrosServiciosPrestadosRepository.findById(id);
    }

    /**
     * Delete the otrosServiciosPrestados by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OtrosServiciosPrestados : {}", id);
        otrosServiciosPrestadosRepository.deleteById(id);
    }
}
