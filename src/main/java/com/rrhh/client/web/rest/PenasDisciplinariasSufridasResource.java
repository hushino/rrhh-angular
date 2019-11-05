package com.rrhh.client.web.rest;

import com.rrhh.client.domain.PenasDisciplinariasSufridas;
import com.rrhh.client.service.PenasDisciplinariasSufridasService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.PenasDisciplinariasSufridasCriteria;
import com.rrhh.client.service.PenasDisciplinariasSufridasQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.rrhh.client.domain.PenasDisciplinariasSufridas}.
 */
@RestController
@RequestMapping("/api")
public class PenasDisciplinariasSufridasResource {

    private final Logger log = LoggerFactory.getLogger(PenasDisciplinariasSufridasResource.class);

    private static final String ENTITY_NAME = "penasDisciplinariasSufridas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PenasDisciplinariasSufridasService penasDisciplinariasSufridasService;

    private final PenasDisciplinariasSufridasQueryService penasDisciplinariasSufridasQueryService;

    public PenasDisciplinariasSufridasResource(PenasDisciplinariasSufridasService penasDisciplinariasSufridasService, PenasDisciplinariasSufridasQueryService penasDisciplinariasSufridasQueryService) {
        this.penasDisciplinariasSufridasService = penasDisciplinariasSufridasService;
        this.penasDisciplinariasSufridasQueryService = penasDisciplinariasSufridasQueryService;
    }

    /**
     * {@code POST  /penas-disciplinarias-sufridas} : Create a new penasDisciplinariasSufridas.
     *
     * @param penasDisciplinariasSufridas the penasDisciplinariasSufridas to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new penasDisciplinariasSufridas, or with status {@code 400 (Bad Request)} if the penasDisciplinariasSufridas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/penas-disciplinarias-sufridas")
    public ResponseEntity<PenasDisciplinariasSufridas> createPenasDisciplinariasSufridas(@RequestBody PenasDisciplinariasSufridas penasDisciplinariasSufridas) throws URISyntaxException {
        log.debug("REST request to save PenasDisciplinariasSufridas : {}", penasDisciplinariasSufridas);
        if (penasDisciplinariasSufridas.getId() != null) {
            throw new BadRequestAlertException("A new penasDisciplinariasSufridas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PenasDisciplinariasSufridas result = penasDisciplinariasSufridasService.save(penasDisciplinariasSufridas);
        return ResponseEntity.created(new URI("/api/penas-disciplinarias-sufridas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /penas-disciplinarias-sufridas} : Updates an existing penasDisciplinariasSufridas.
     *
     * @param penasDisciplinariasSufridas the penasDisciplinariasSufridas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated penasDisciplinariasSufridas,
     * or with status {@code 400 (Bad Request)} if the penasDisciplinariasSufridas is not valid,
     * or with status {@code 500 (Internal Server Error)} if the penasDisciplinariasSufridas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/penas-disciplinarias-sufridas")
    public ResponseEntity<PenasDisciplinariasSufridas> updatePenasDisciplinariasSufridas(@RequestBody PenasDisciplinariasSufridas penasDisciplinariasSufridas) throws URISyntaxException {
        log.debug("REST request to update PenasDisciplinariasSufridas : {}", penasDisciplinariasSufridas);
        if (penasDisciplinariasSufridas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PenasDisciplinariasSufridas result = penasDisciplinariasSufridasService.save(penasDisciplinariasSufridas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, penasDisciplinariasSufridas.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /penas-disciplinarias-sufridas} : get all the penasDisciplinariasSufridas.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of penasDisciplinariasSufridas in body.
     */
    @GetMapping("/penas-disciplinarias-sufridas")
    public ResponseEntity<List<PenasDisciplinariasSufridas>> getAllPenasDisciplinariasSufridas(PenasDisciplinariasSufridasCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PenasDisciplinariasSufridas by criteria: {}", criteria);
        Page<PenasDisciplinariasSufridas> page = penasDisciplinariasSufridasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /penas-disciplinarias-sufridas/count} : count all the penasDisciplinariasSufridas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/penas-disciplinarias-sufridas/count")
    public ResponseEntity<Long> countPenasDisciplinariasSufridas(PenasDisciplinariasSufridasCriteria criteria) {
        log.debug("REST request to count PenasDisciplinariasSufridas by criteria: {}", criteria);
        return ResponseEntity.ok().body(penasDisciplinariasSufridasQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /penas-disciplinarias-sufridas/:id} : get the "id" penasDisciplinariasSufridas.
     *
     * @param id the id of the penasDisciplinariasSufridas to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the penasDisciplinariasSufridas, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/penas-disciplinarias-sufridas/{id}")
    public ResponseEntity<PenasDisciplinariasSufridas> getPenasDisciplinariasSufridas(@PathVariable Long id) {
        log.debug("REST request to get PenasDisciplinariasSufridas : {}", id);
        Optional<PenasDisciplinariasSufridas> penasDisciplinariasSufridas = penasDisciplinariasSufridasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(penasDisciplinariasSufridas);
    }

    /**
     * {@code DELETE  /penas-disciplinarias-sufridas/:id} : delete the "id" penasDisciplinariasSufridas.
     *
     * @param id the id of the penasDisciplinariasSufridas to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/penas-disciplinarias-sufridas/{id}")
    public ResponseEntity<Void> deletePenasDisciplinariasSufridas(@PathVariable Long id) {
        log.debug("REST request to delete PenasDisciplinariasSufridas : {}", id);
        penasDisciplinariasSufridasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
