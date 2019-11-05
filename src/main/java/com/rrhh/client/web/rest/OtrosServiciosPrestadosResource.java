package com.rrhh.client.web.rest;

import com.rrhh.client.domain.OtrosServiciosPrestados;
import com.rrhh.client.service.OtrosServiciosPrestadosService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.OtrosServiciosPrestadosCriteria;
import com.rrhh.client.service.OtrosServiciosPrestadosQueryService;

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
 * REST controller for managing {@link com.rrhh.client.domain.OtrosServiciosPrestados}.
 */
@RestController
@RequestMapping("/api")
public class OtrosServiciosPrestadosResource {

    private final Logger log = LoggerFactory.getLogger(OtrosServiciosPrestadosResource.class);

    private static final String ENTITY_NAME = "otrosServiciosPrestados";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OtrosServiciosPrestadosService otrosServiciosPrestadosService;

    private final OtrosServiciosPrestadosQueryService otrosServiciosPrestadosQueryService;

    public OtrosServiciosPrestadosResource(OtrosServiciosPrestadosService otrosServiciosPrestadosService, OtrosServiciosPrestadosQueryService otrosServiciosPrestadosQueryService) {
        this.otrosServiciosPrestadosService = otrosServiciosPrestadosService;
        this.otrosServiciosPrestadosQueryService = otrosServiciosPrestadosQueryService;
    }

    /**
     * {@code POST  /otros-servicios-prestados} : Create a new otrosServiciosPrestados.
     *
     * @param otrosServiciosPrestados the otrosServiciosPrestados to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new otrosServiciosPrestados, or with status {@code 400 (Bad Request)} if the otrosServiciosPrestados has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/otros-servicios-prestados")
    public ResponseEntity<OtrosServiciosPrestados> createOtrosServiciosPrestados(@RequestBody OtrosServiciosPrestados otrosServiciosPrestados) throws URISyntaxException {
        log.debug("REST request to save OtrosServiciosPrestados : {}", otrosServiciosPrestados);
        if (otrosServiciosPrestados.getId() != null) {
            throw new BadRequestAlertException("A new otrosServiciosPrestados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OtrosServiciosPrestados result = otrosServiciosPrestadosService.save(otrosServiciosPrestados);
        return ResponseEntity.created(new URI("/api/otros-servicios-prestados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /otros-servicios-prestados} : Updates an existing otrosServiciosPrestados.
     *
     * @param otrosServiciosPrestados the otrosServiciosPrestados to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated otrosServiciosPrestados,
     * or with status {@code 400 (Bad Request)} if the otrosServiciosPrestados is not valid,
     * or with status {@code 500 (Internal Server Error)} if the otrosServiciosPrestados couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/otros-servicios-prestados")
    public ResponseEntity<OtrosServiciosPrestados> updateOtrosServiciosPrestados(@RequestBody OtrosServiciosPrestados otrosServiciosPrestados) throws URISyntaxException {
        log.debug("REST request to update OtrosServiciosPrestados : {}", otrosServiciosPrestados);
        if (otrosServiciosPrestados.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OtrosServiciosPrestados result = otrosServiciosPrestadosService.save(otrosServiciosPrestados);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, otrosServiciosPrestados.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /otros-servicios-prestados} : get all the otrosServiciosPrestados.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of otrosServiciosPrestados in body.
     */
    @GetMapping("/otros-servicios-prestados")
    public ResponseEntity<List<OtrosServiciosPrestados>> getAllOtrosServiciosPrestados(OtrosServiciosPrestadosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OtrosServiciosPrestados by criteria: {}", criteria);
        Page<OtrosServiciosPrestados> page = otrosServiciosPrestadosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /otros-servicios-prestados/count} : count all the otrosServiciosPrestados.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/otros-servicios-prestados/count")
    public ResponseEntity<Long> countOtrosServiciosPrestados(OtrosServiciosPrestadosCriteria criteria) {
        log.debug("REST request to count OtrosServiciosPrestados by criteria: {}", criteria);
        return ResponseEntity.ok().body(otrosServiciosPrestadosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /otros-servicios-prestados/:id} : get the "id" otrosServiciosPrestados.
     *
     * @param id the id of the otrosServiciosPrestados to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the otrosServiciosPrestados, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/otros-servicios-prestados/{id}")
    public ResponseEntity<OtrosServiciosPrestados> getOtrosServiciosPrestados(@PathVariable Long id) {
        log.debug("REST request to get OtrosServiciosPrestados : {}", id);
        Optional<OtrosServiciosPrestados> otrosServiciosPrestados = otrosServiciosPrestadosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(otrosServiciosPrestados);
    }

    /**
     * {@code DELETE  /otros-servicios-prestados/:id} : delete the "id" otrosServiciosPrestados.
     *
     * @param id the id of the otrosServiciosPrestados to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/otros-servicios-prestados/{id}")
    public ResponseEntity<Void> deleteOtrosServiciosPrestados(@PathVariable Long id) {
        log.debug("REST request to delete OtrosServiciosPrestados : {}", id);
        otrosServiciosPrestadosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
