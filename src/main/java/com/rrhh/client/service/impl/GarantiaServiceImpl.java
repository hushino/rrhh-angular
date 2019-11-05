package com.rrhh.client.service.impl;

import com.rrhh.client.service.GarantiaService;
import com.rrhh.client.domain.Garantia;
import com.rrhh.client.repository.GarantiaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Garantia}.
 */
@Service
@Transactional
public class GarantiaServiceImpl implements GarantiaService {

    private final Logger log = LoggerFactory.getLogger(GarantiaServiceImpl.class);

    private final GarantiaRepository garantiaRepository;

    public GarantiaServiceImpl(GarantiaRepository garantiaRepository) {
        this.garantiaRepository = garantiaRepository;
    }

    /**
     * Save a garantia.
     *
     * @param garantia the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Garantia save(Garantia garantia) {
        log.debug("Request to save Garantia : {}", garantia);
        return garantiaRepository.save(garantia);
    }

    /**
     * Get all the garantias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Garantia> findAll(Pageable pageable) {
        log.debug("Request to get all Garantias");
        return garantiaRepository.findAll(pageable);
    }


    /**
     * Get one garantia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Garantia> findOne(Long id) {
        log.debug("Request to get Garantia : {}", id);
        return garantiaRepository.findById(id);
    }

    /**
     * Delete the garantia by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Garantia : {}", id);
        garantiaRepository.deleteById(id);
    }
}
