package com.rrhh.client.service.impl;

import com.rrhh.client.service.LicenciaService;
import com.rrhh.client.domain.Licencia;
import com.rrhh.client.repository.LicenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Licencia}.
 */
@Service
@Transactional
public class LicenciaServiceImpl implements LicenciaService {

    private final Logger log = LoggerFactory.getLogger(LicenciaServiceImpl.class);

    private final LicenciaRepository licenciaRepository;

    public LicenciaServiceImpl(LicenciaRepository licenciaRepository) {
        this.licenciaRepository = licenciaRepository;
    }

    /**
     * Save a licencia.
     *
     * @param licencia the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Licencia save(Licencia licencia) {
        log.debug("Request to save Licencia : {}", licencia);
        return licenciaRepository.save(licencia);
    }

    /**
     * Get all the licencias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Licencia> findAll(Pageable pageable) {
        log.debug("Request to get all Licencias");
        return licenciaRepository.findAll(pageable);
    }


    /**
     * Get one licencia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Licencia> findOne(Long id) {
        log.debug("Request to get Licencia : {}", id);
        return licenciaRepository.findById(id);
    }

    /**
     * Delete the licencia by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Licencia : {}", id);
        licenciaRepository.deleteById(id);
    }
}
