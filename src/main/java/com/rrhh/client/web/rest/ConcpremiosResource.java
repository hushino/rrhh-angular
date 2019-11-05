package com.rrhh.client.web.rest;

import com.rrhh.client.domain.Concpremios;
import com.rrhh.client.service.ConcpremiosService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.ConcpremiosCriteria;
import com.rrhh.client.service.ConcpremiosQueryService;

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
 * REST controller for managing {@link com.rrhh.client.domain.Concpremios}.
 */
@RestController
@RequestMapping("/api")
public class ConcpremiosResource {

    private final Logger log = LoggerFactory.getLogger(ConcpremiosResource.class);

    private static final String ENTITY_NAME = "concpremios";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConcpremiosService concpremiosService;

    private final ConcpremiosQueryService concpremiosQueryService;

    public ConcpremiosResource(ConcpremiosService concpremiosService, ConcpremiosQueryService concpremiosQueryService) {
        this.concpremiosService = concpremiosService;
        this.concpremiosQueryService = concpremiosQueryService;
    }

    /**
     * {@code POST  /concpremios} : Create a new concpremios.
     *
     * @param concpremios the concpremios to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new concpremios, or with status {@code 400 (Bad Request)} if the concpremios has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/concpremios")
    public ResponseEntity<Concpremios> createConcpremios(@RequestBody Concpremios concpremios) throws URISyntaxException {
        log.debug("REST request to save Concpremios : {}", concpremios);
        if (concpremios.getId() != null) {
            throw new BadRequestAlertException("A new concpremios cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Concpremios result = concpremiosService.save(concpremios);
        return ResponseEntity.created(new URI("/api/concpremios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /concpremios} : Updates an existing concpremios.
     *
     * @param concpremios the concpremios to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated concpremios,
     * or with status {@code 400 (Bad Request)} if the concpremios is not valid,
     * or with status {@code 500 (Internal Server Error)} if the concpremios couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/concpremios")
    public ResponseEntity<Concpremios> updateConcpremios(@RequestBody Concpremios concpremios) throws URISyntaxException {
        log.debug("REST request to update Concpremios : {}", concpremios);
        if (concpremios.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Concpremios result = concpremiosService.save(concpremios);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, concpremios.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /concpremios} : get all the concpremios.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of concpremios in body.
     */
    @GetMapping("/concpremios")
    public ResponseEntity<List<Concpremios>> getAllConcpremios(ConcpremiosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Concpremios by criteria: {}", criteria);
        Page<Concpremios> page = concpremiosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /concpremios/count} : count all the concpremios.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/concpremios/count")
    public ResponseEntity<Long> countConcpremios(ConcpremiosCriteria criteria) {
        log.debug("REST request to count Concpremios by criteria: {}", criteria);
        return ResponseEntity.ok().body(concpremiosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /concpremios/:id} : get the "id" concpremios.
     *
     * @param id the id of the concpremios to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the concpremios, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/concpremios/{id}")
    public ResponseEntity<Concpremios> getConcpremios(@PathVariable Long id) {
        log.debug("REST request to get Concpremios : {}", id);
        Optional<Concpremios> concpremios = concpremiosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(concpremios);
    }

    /**
     * {@code DELETE  /concpremios/:id} : delete the "id" concpremios.
     *
     * @param id the id of the concpremios to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/concpremios/{id}")
    public ResponseEntity<Void> deleteConcpremios(@PathVariable Long id) {
        log.debug("REST request to delete Concpremios : {}", id);
        concpremiosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
