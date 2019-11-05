package com.rrhh.client.web.rest;

import com.rrhh.client.domain.AltasAscensosBajas;
import com.rrhh.client.service.AltasAscensosBajasService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.AltasAscensosBajasCriteria;
import com.rrhh.client.service.AltasAscensosBajasQueryService;

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
 * REST controller for managing {@link com.rrhh.client.domain.AltasAscensosBajas}.
 */
@RestController
@RequestMapping("/api")
public class AltasAscensosBajasResource {

    private final Logger log = LoggerFactory.getLogger(AltasAscensosBajasResource.class);

    private static final String ENTITY_NAME = "altasAscensosBajas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AltasAscensosBajasService altasAscensosBajasService;

    private final AltasAscensosBajasQueryService altasAscensosBajasQueryService;

    public AltasAscensosBajasResource(AltasAscensosBajasService altasAscensosBajasService, AltasAscensosBajasQueryService altasAscensosBajasQueryService) {
        this.altasAscensosBajasService = altasAscensosBajasService;
        this.altasAscensosBajasQueryService = altasAscensosBajasQueryService;
    }

    /**
     * {@code POST  /altas-ascensos-bajas} : Create a new altasAscensosBajas.
     *
     * @param altasAscensosBajas the altasAscensosBajas to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new altasAscensosBajas, or with status {@code 400 (Bad Request)} if the altasAscensosBajas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/altas-ascensos-bajas")
    public ResponseEntity<AltasAscensosBajas> createAltasAscensosBajas(@RequestBody AltasAscensosBajas altasAscensosBajas) throws URISyntaxException {
        log.debug("REST request to save AltasAscensosBajas : {}", altasAscensosBajas);
        if (altasAscensosBajas.getId() != null) {
            throw new BadRequestAlertException("A new altasAscensosBajas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AltasAscensosBajas result = altasAscensosBajasService.save(altasAscensosBajas);
        return ResponseEntity.created(new URI("/api/altas-ascensos-bajas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /altas-ascensos-bajas} : Updates an existing altasAscensosBajas.
     *
     * @param altasAscensosBajas the altasAscensosBajas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated altasAscensosBajas,
     * or with status {@code 400 (Bad Request)} if the altasAscensosBajas is not valid,
     * or with status {@code 500 (Internal Server Error)} if the altasAscensosBajas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/altas-ascensos-bajas")
    public ResponseEntity<AltasAscensosBajas> updateAltasAscensosBajas(@RequestBody AltasAscensosBajas altasAscensosBajas) throws URISyntaxException {
        log.debug("REST request to update AltasAscensosBajas : {}", altasAscensosBajas);
        if (altasAscensosBajas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AltasAscensosBajas result = altasAscensosBajasService.save(altasAscensosBajas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, altasAscensosBajas.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /altas-ascensos-bajas} : get all the altasAscensosBajas.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of altasAscensosBajas in body.
     */
    @GetMapping("/altas-ascensos-bajas")
    public ResponseEntity<List<AltasAscensosBajas>> getAllAltasAscensosBajas(AltasAscensosBajasCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AltasAscensosBajas by criteria: {}", criteria);
        Page<AltasAscensosBajas> page = altasAscensosBajasQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /altas-ascensos-bajas/count} : count all the altasAscensosBajas.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/altas-ascensos-bajas/count")
    public ResponseEntity<Long> countAltasAscensosBajas(AltasAscensosBajasCriteria criteria) {
        log.debug("REST request to count AltasAscensosBajas by criteria: {}", criteria);
        return ResponseEntity.ok().body(altasAscensosBajasQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /altas-ascensos-bajas/:id} : get the "id" altasAscensosBajas.
     *
     * @param id the id of the altasAscensosBajas to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the altasAscensosBajas, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/altas-ascensos-bajas/{id}")
    public ResponseEntity<AltasAscensosBajas> getAltasAscensosBajas(@PathVariable Long id) {
        log.debug("REST request to get AltasAscensosBajas : {}", id);
        Optional<AltasAscensosBajas> altasAscensosBajas = altasAscensosBajasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(altasAscensosBajas);
    }

    /**
     * {@code DELETE  /altas-ascensos-bajas/:id} : delete the "id" altasAscensosBajas.
     *
     * @param id the id of the altasAscensosBajas to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/altas-ascensos-bajas/{id}")
    public ResponseEntity<Void> deleteAltasAscensosBajas(@PathVariable Long id) {
        log.debug("REST request to delete AltasAscensosBajas : {}", id);
        altasAscensosBajasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
