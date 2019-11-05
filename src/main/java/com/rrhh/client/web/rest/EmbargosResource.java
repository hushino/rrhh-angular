package com.rrhh.client.web.rest;

import com.rrhh.client.domain.Embargos;
import com.rrhh.client.service.EmbargosService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.EmbargosCriteria;
import com.rrhh.client.service.EmbargosQueryService;

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
 * REST controller for managing {@link com.rrhh.client.domain.Embargos}.
 */
@RestController
@RequestMapping("/api")
public class EmbargosResource {

    private final Logger log = LoggerFactory.getLogger(EmbargosResource.class);

    private static final String ENTITY_NAME = "embargos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmbargosService embargosService;

    private final EmbargosQueryService embargosQueryService;

    public EmbargosResource(EmbargosService embargosService, EmbargosQueryService embargosQueryService) {
        this.embargosService = embargosService;
        this.embargosQueryService = embargosQueryService;
    }

    /**
     * {@code POST  /embargos} : Create a new embargos.
     *
     * @param embargos the embargos to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new embargos, or with status {@code 400 (Bad Request)} if the embargos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/embargos")
    public ResponseEntity<Embargos> createEmbargos(@RequestBody Embargos embargos) throws URISyntaxException {
        log.debug("REST request to save Embargos : {}", embargos);
        if (embargos.getId() != null) {
            throw new BadRequestAlertException("A new embargos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Embargos result = embargosService.save(embargos);
        return ResponseEntity.created(new URI("/api/embargos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /embargos} : Updates an existing embargos.
     *
     * @param embargos the embargos to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated embargos,
     * or with status {@code 400 (Bad Request)} if the embargos is not valid,
     * or with status {@code 500 (Internal Server Error)} if the embargos couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/embargos")
    public ResponseEntity<Embargos> updateEmbargos(@RequestBody Embargos embargos) throws URISyntaxException {
        log.debug("REST request to update Embargos : {}", embargos);
        if (embargos.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Embargos result = embargosService.save(embargos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, embargos.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /embargos} : get all the embargos.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of embargos in body.
     */
    @GetMapping("/embargos")
    public ResponseEntity<List<Embargos>> getAllEmbargos(EmbargosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Embargos by criteria: {}", criteria);
        Page<Embargos> page = embargosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /embargos/count} : count all the embargos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/embargos/count")
    public ResponseEntity<Long> countEmbargos(EmbargosCriteria criteria) {
        log.debug("REST request to count Embargos by criteria: {}", criteria);
        return ResponseEntity.ok().body(embargosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /embargos/:id} : get the "id" embargos.
     *
     * @param id the id of the embargos to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the embargos, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/embargos/{id}")
    public ResponseEntity<Embargos> getEmbargos(@PathVariable Long id) {
        log.debug("REST request to get Embargos : {}", id);
        Optional<Embargos> embargos = embargosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(embargos);
    }

    /**
     * {@code DELETE  /embargos/:id} : delete the "id" embargos.
     *
     * @param id the id of the embargos to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/embargos/{id}")
    public ResponseEntity<Void> deleteEmbargos(@PathVariable Long id) {
        log.debug("REST request to delete Embargos : {}", id);
        embargosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
