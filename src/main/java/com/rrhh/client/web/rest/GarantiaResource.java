package com.rrhh.client.web.rest;

import com.rrhh.client.domain.Garantia;
import com.rrhh.client.service.GarantiaService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.GarantiaCriteria;
import com.rrhh.client.service.GarantiaQueryService;

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
 * REST controller for managing {@link com.rrhh.client.domain.Garantia}.
 */
@RestController
@RequestMapping("/api")
public class GarantiaResource {

    private final Logger log = LoggerFactory.getLogger(GarantiaResource.class);

    private static final String ENTITY_NAME = "garantia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GarantiaService garantiaService;

    private final GarantiaQueryService garantiaQueryService;

    public GarantiaResource(GarantiaService garantiaService, GarantiaQueryService garantiaQueryService) {
        this.garantiaService = garantiaService;
        this.garantiaQueryService = garantiaQueryService;
    }

    /**
     * {@code POST  /garantias} : Create a new garantia.
     *
     * @param garantia the garantia to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new garantia, or with status {@code 400 (Bad Request)} if the garantia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/garantias")
    public ResponseEntity<Garantia> createGarantia(@RequestBody Garantia garantia) throws URISyntaxException {
        log.debug("REST request to save Garantia : {}", garantia);
        if (garantia.getId() != null) {
            throw new BadRequestAlertException("A new garantia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Garantia result = garantiaService.save(garantia);
        return ResponseEntity.created(new URI("/api/garantias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /garantias} : Updates an existing garantia.
     *
     * @param garantia the garantia to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated garantia,
     * or with status {@code 400 (Bad Request)} if the garantia is not valid,
     * or with status {@code 500 (Internal Server Error)} if the garantia couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/garantias")
    public ResponseEntity<Garantia> updateGarantia(@RequestBody Garantia garantia) throws URISyntaxException {
        log.debug("REST request to update Garantia : {}", garantia);
        if (garantia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Garantia result = garantiaService.save(garantia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, garantia.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /garantias} : get all the garantias.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of garantias in body.
     */
    @GetMapping("/garantias")
    public ResponseEntity<List<Garantia>> getAllGarantias(GarantiaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Garantias by criteria: {}", criteria);
        Page<Garantia> page = garantiaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /garantias/count} : count all the garantias.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/garantias/count")
    public ResponseEntity<Long> countGarantias(GarantiaCriteria criteria) {
        log.debug("REST request to count Garantias by criteria: {}", criteria);
        return ResponseEntity.ok().body(garantiaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /garantias/:id} : get the "id" garantia.
     *
     * @param id the id of the garantia to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the garantia, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/garantias/{id}")
    public ResponseEntity<Garantia> getGarantia(@PathVariable Long id) {
        log.debug("REST request to get Garantia : {}", id);
        Optional<Garantia> garantia = garantiaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(garantia);
    }

    /**
     * {@code DELETE  /garantias/:id} : delete the "id" garantia.
     *
     * @param id the id of the garantia to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/garantias/{id}")
    public ResponseEntity<Void> deleteGarantia(@PathVariable Long id) {
        log.debug("REST request to delete Garantia : {}", id);
        garantiaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
