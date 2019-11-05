package com.rrhh.client.service.impl;

import com.rrhh.client.service.ConcpremiosService;
import com.rrhh.client.domain.Concpremios;
import com.rrhh.client.repository.ConcpremiosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Concpremios}.
 */
@Service
@Transactional
public class ConcpremiosServiceImpl implements ConcpremiosService {

    private final Logger log = LoggerFactory.getLogger(ConcpremiosServiceImpl.class);

    private final ConcpremiosRepository concpremiosRepository;

    public ConcpremiosServiceImpl(ConcpremiosRepository concpremiosRepository) {
        this.concpremiosRepository = concpremiosRepository;
    }

    /**
     * Save a concpremios.
     *
     * @param concpremios the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Concpremios save(Concpremios concpremios) {
        log.debug("Request to save Concpremios : {}", concpremios);
        return concpremiosRepository.save(concpremios);
    }

    /**
     * Get all the concpremios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Concpremios> findAll(Pageable pageable) {
        log.debug("Request to get all Concpremios");
        return concpremiosRepository.findAll(pageable);
    }


    /**
     * Get one concpremios by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Concpremios> findOne(Long id) {
        log.debug("Request to get Concpremios : {}", id);
        return concpremiosRepository.findById(id);
    }

    /**
     * Delete the concpremios by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Concpremios : {}", id);
        concpremiosRepository.deleteById(id);
    }
}
