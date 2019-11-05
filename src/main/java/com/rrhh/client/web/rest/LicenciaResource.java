package com.rrhh.client.web.rest;

import com.rrhh.client.domain.Licencia;
import com.rrhh.client.service.LicenciaService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.LicenciaCriteria;
import com.rrhh.client.service.LicenciaQueryService;

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
 * REST controller for managing {@link com.rrhh.client.domain.Licencia}.
 */
@RestController
@RequestMapping("/api")
public class LicenciaResource {

    private final Logger log = LoggerFactory.getLogger(LicenciaResource.class);

    private static final String ENTITY_NAME = "licencia";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LicenciaService licenciaService;

    private final LicenciaQueryService licenciaQueryService;

    public LicenciaResource(LicenciaService licenciaService, LicenciaQueryService licenciaQueryService) {
        this.licenciaService = licenciaService;
        this.licenciaQueryService = licenciaQueryService;
    }

    /**
     * {@code POST  /licencias} : Create a new licencia.
     *
     * @param licencia the licencia to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new licencia, or with status {@code 400 (Bad Request)} if the licencia has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/licencias")
    public ResponseEntity<Licencia> createLicencia(@RequestBody Licencia licencia) throws URISyntaxException {
        log.debug("REST request to save Licencia : {}", licencia);
        if (licencia.getId() != null) {
            throw new BadRequestAlertException("A new licencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Licencia result = licenciaService.save(licencia);
        return ResponseEntity.created(new URI("/api/licencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /licencias} : Updates an existing licencia.
     *
     * @param licencia the licencia to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licencia,
     * or with status {@code 400 (Bad Request)} if the licencia is not valid,
     * or with status {@code 500 (Internal Server Error)} if the licencia couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/licencias")
    public ResponseEntity<Licencia> updateLicencia(@RequestBody Licencia licencia) throws URISyntaxException {
        log.debug("REST request to update Licencia : {}", licencia);
        if (licencia.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Licencia result = licenciaService.save(licencia);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licencia.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /licencias} : get all the licencias.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of licencias in body.
     */
    @GetMapping("/licencias")
    public ResponseEntity<List<Licencia>> getAllLicencias(LicenciaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Licencias by criteria: {}", criteria);
        Page<Licencia> page = licenciaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /licencias/count} : count all the licencias.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/licencias/count")
    public ResponseEntity<Long> countLicencias(LicenciaCriteria criteria) {
        log.debug("REST request to count Licencias by criteria: {}", criteria);
        return ResponseEntity.ok().body(licenciaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /licencias/:id} : get the "id" licencia.
     *
     * @param id the id of the licencia to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the licencia, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/licencias/{id}")
    public ResponseEntity<Licencia> getLicencia(@PathVariable Long id) {
        log.debug("REST request to get Licencia : {}", id);
        Optional<Licencia> licencia = licenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(licencia);
    }

    /**
     * {@code DELETE  /licencias/:id} : delete the "id" licencia.
     *
     * @param id the id of the licencia to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/licencias/{id}")
    public ResponseEntity<Void> deleteLicencia(@PathVariable Long id) {
        log.debug("REST request to delete Licencia : {}", id);
        licenciaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
