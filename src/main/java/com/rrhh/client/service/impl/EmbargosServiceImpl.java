package com.rrhh.client.service.impl;

import com.rrhh.client.service.EmbargosService;
import com.rrhh.client.domain.Embargos;
import com.rrhh.client.repository.EmbargosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Embargos}.
 */
@Service
@Transactional
public class EmbargosServiceImpl implements EmbargosService {

    private final Logger log = LoggerFactory.getLogger(EmbargosServiceImpl.class);

    private final EmbargosRepository embargosRepository;

    public EmbargosServiceImpl(EmbargosRepository embargosRepository) {
        this.embargosRepository = embargosRepository;
    }

    /**
     * Save a embargos.
     *
     * @param embargos the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Embargos save(Embargos embargos) {
        log.debug("Request to save Embargos : {}", embargos);
        return embargosRepository.save(embargos);
    }

    /**
     * Get all the embargos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Embargos> findAll(Pageable pageable) {
        log.debug("Request to get all Embargos");
        return embargosRepository.findAll(pageable);
    }


    /**
     * Get one embargos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Embargos> findOne(Long id) {
        log.debug("Request to get Embargos : {}", id);
        return embargosRepository.findById(id);
    }

    /**
     * Delete the embargos by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Embargos : {}", id);
        embargosRepository.deleteById(id);
    }
}
