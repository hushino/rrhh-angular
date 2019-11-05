package com.rrhh.client.service.impl;

import com.rrhh.client.service.AltasAscensosBajasService;
import com.rrhh.client.domain.AltasAscensosBajas;
import com.rrhh.client.repository.AltasAscensosBajasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AltasAscensosBajas}.
 */
@Service
@Transactional
public class AltasAscensosBajasServiceImpl implements AltasAscensosBajasService {

    private final Logger log = LoggerFactory.getLogger(AltasAscensosBajasServiceImpl.class);

    private final AltasAscensosBajasRepository altasAscensosBajasRepository;

    public AltasAscensosBajasServiceImpl(AltasAscensosBajasRepository altasAscensosBajasRepository) {
        this.altasAscensosBajasRepository = altasAscensosBajasRepository;
    }

    /**
     * Save a altasAscensosBajas.
     *
     * @param altasAscensosBajas the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AltasAscensosBajas save(AltasAscensosBajas altasAscensosBajas) {
        log.debug("Request to save AltasAscensosBajas : {}", altasAscensosBajas);
        return altasAscensosBajasRepository.save(altasAscensosBajas);
    }

    /**
     * Get all the altasAscensosBajas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AltasAscensosBajas> findAll(Pageable pageable) {
        log.debug("Request to get all AltasAscensosBajas");
        return altasAscensosBajasRepository.findAll(pageable);
    }


    /**
     * Get one altasAscensosBajas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AltasAscensosBajas> findOne(Long id) {
        log.debug("Request to get AltasAscensosBajas : {}", id);
        return altasAscensosBajasRepository.findById(id);
    }

    /**
     * Delete the altasAscensosBajas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AltasAscensosBajas : {}", id);
        altasAscensosBajasRepository.deleteById(id);
    }
}
