package com.rrhh.client.service.impl;

import com.rrhh.client.service.PenasDisciplinariasSufridasService;
import com.rrhh.client.domain.PenasDisciplinariasSufridas;
import com.rrhh.client.repository.PenasDisciplinariasSufridasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PenasDisciplinariasSufridas}.
 */
@Service
@Transactional
public class PenasDisciplinariasSufridasServiceImpl implements PenasDisciplinariasSufridasService {

    private final Logger log = LoggerFactory.getLogger(PenasDisciplinariasSufridasServiceImpl.class);

    private final PenasDisciplinariasSufridasRepository penasDisciplinariasSufridasRepository;

    public PenasDisciplinariasSufridasServiceImpl(PenasDisciplinariasSufridasRepository penasDisciplinariasSufridasRepository) {
        this.penasDisciplinariasSufridasRepository = penasDisciplinariasSufridasRepository;
    }

    /**
     * Save a penasDisciplinariasSufridas.
     *
     * @param penasDisciplinariasSufridas the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PenasDisciplinariasSufridas save(PenasDisciplinariasSufridas penasDisciplinariasSufridas) {
        log.debug("Request to save PenasDisciplinariasSufridas : {}", penasDisciplinariasSufridas);
        return penasDisciplinariasSufridasRepository.save(penasDisciplinariasSufridas);
    }

    /**
     * Get all the penasDisciplinariasSufridas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PenasDisciplinariasSufridas> findAll(Pageable pageable) {
        log.debug("Request to get all PenasDisciplinariasSufridas");
        return penasDisciplinariasSufridasRepository.findAll(pageable);
    }


    /**
     * Get one penasDisciplinariasSufridas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PenasDisciplinariasSufridas> findOne(Long id) {
        log.debug("Request to get PenasDisciplinariasSufridas : {}", id);
        return penasDisciplinariasSufridasRepository.findById(id);
    }

    /**
     * Delete the penasDisciplinariasSufridas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PenasDisciplinariasSufridas : {}", id);
        penasDisciplinariasSufridasRepository.deleteById(id);
    }
}
