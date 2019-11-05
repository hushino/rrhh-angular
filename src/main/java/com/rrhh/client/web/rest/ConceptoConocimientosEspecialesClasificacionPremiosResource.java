package com.rrhh.client.web.rest;

import com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios;
import com.rrhh.client.service.ConceptoConocimientosEspecialesClasificacionPremiosService;
import com.rrhh.client.web.rest.errors.BadRequestAlertException;
import com.rrhh.client.service.dto.ConceptoConocimientosEspecialesClasificacionPremiosCriteria;
import com.rrhh.client.service.ConceptoConocimientosEspecialesClasificacionPremiosQueryService;

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
 * REST controller for managing {@link com.rrhh.client.domain.ConceptoConocimientosEspecialesClasificacionPremios}.
 */
@RestController
@RequestMapping("/api")
public class ConceptoConocimientosEspecialesClasificacionPremiosResource {

    private final Logger log = LoggerFactory.getLogger(ConceptoConocimientosEspecialesClasificacionPremiosResource.class);

    private static final String ENTITY_NAME = "conceptoConocimientosEspecialesClasificacionPremios";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConceptoConocimientosEspecialesClasificacionPremiosService conceptoConocimientosEspecialesClasificacionPremiosService;

    private final ConceptoConocimientosEspecialesClasificacionPremiosQueryService conceptoConocimientosEspecialesClasificacionPremiosQueryService;

    public ConceptoConocimientosEspecialesClasificacionPremiosResource(ConceptoConocimientosEspecialesClasificacionPremiosService conceptoConocimientosEspecialesClasificacionPremiosService, ConceptoConocimientosEspecialesClasificacionPremiosQueryService conceptoConocimientosEspecialesClasificacionPremiosQueryService) {
        this.conceptoConocimientosEspecialesClasificacionPremiosService = conceptoConocimientosEspecialesClasificacionPremiosService;
        this.conceptoConocimientosEspecialesClasificacionPremiosQueryService = conceptoConocimientosEspecialesClasificacionPremiosQueryService;
    }

    /**
     * {@code POST  /concepto-conocimientos-especiales-clasificacion-premios} : Create a new conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param conceptoConocimientosEspecialesClasificacionPremios the conceptoConocimientosEspecialesClasificacionPremios to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conceptoConocimientosEspecialesClasificacionPremios, or with status {@code 400 (Bad Request)} if the conceptoConocimientosEspecialesClasificacionPremios has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/concepto-conocimientos-especiales-clasificacion-premios")
    public ResponseEntity<ConceptoConocimientosEspecialesClasificacionPremios> createConceptoConocimientosEspecialesClasificacionPremios(@RequestBody ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios) throws URISyntaxException {
        log.debug("REST request to save ConceptoConocimientosEspecialesClasificacionPremios : {}", conceptoConocimientosEspecialesClasificacionPremios);
        if (conceptoConocimientosEspecialesClasificacionPremios.getId() != null) {
            throw new BadRequestAlertException("A new conceptoConocimientosEspecialesClasificacionPremios cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConceptoConocimientosEspecialesClasificacionPremios result = conceptoConocimientosEspecialesClasificacionPremiosService.save(conceptoConocimientosEspecialesClasificacionPremios);
        return ResponseEntity.created(new URI("/api/concepto-conocimientos-especiales-clasificacion-premios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /concepto-conocimientos-especiales-clasificacion-premios} : Updates an existing conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param conceptoConocimientosEspecialesClasificacionPremios the conceptoConocimientosEspecialesClasificacionPremios to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conceptoConocimientosEspecialesClasificacionPremios,
     * or with status {@code 400 (Bad Request)} if the conceptoConocimientosEspecialesClasificacionPremios is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conceptoConocimientosEspecialesClasificacionPremios couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/concepto-conocimientos-especiales-clasificacion-premios")
    public ResponseEntity<ConceptoConocimientosEspecialesClasificacionPremios> updateConceptoConocimientosEspecialesClasificacionPremios(@RequestBody ConceptoConocimientosEspecialesClasificacionPremios conceptoConocimientosEspecialesClasificacionPremios) throws URISyntaxException {
        log.debug("REST request to update ConceptoConocimientosEspecialesClasificacionPremios : {}", conceptoConocimientosEspecialesClasificacionPremios);
        if (conceptoConocimientosEspecialesClasificacionPremios.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConceptoConocimientosEspecialesClasificacionPremios result = conceptoConocimientosEspecialesClasificacionPremiosService.save(conceptoConocimientosEspecialesClasificacionPremios);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, conceptoConocimientosEspecialesClasificacionPremios.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /concepto-conocimientos-especiales-clasificacion-premios} : get all the conceptoConocimientosEspecialesClasificacionPremios.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conceptoConocimientosEspecialesClasificacionPremios in body.
     */
    @GetMapping("/concepto-conocimientos-especiales-clasificacion-premios")
    public ResponseEntity<List<ConceptoConocimientosEspecialesClasificacionPremios>> getAllConceptoConocimientosEspecialesClasificacionPremios(ConceptoConocimientosEspecialesClasificacionPremiosCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ConceptoConocimientosEspecialesClasificacionPremios by criteria: {}", criteria);
        Page<ConceptoConocimientosEspecialesClasificacionPremios> page = conceptoConocimientosEspecialesClasificacionPremiosQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /concepto-conocimientos-especiales-clasificacion-premios/count} : count all the conceptoConocimientosEspecialesClasificacionPremios.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/concepto-conocimientos-especiales-clasificacion-premios/count")
    public ResponseEntity<Long> countConceptoConocimientosEspecialesClasificacionPremios(ConceptoConocimientosEspecialesClasificacionPremiosCriteria criteria) {
        log.debug("REST request to count ConceptoConocimientosEspecialesClasificacionPremios by criteria: {}", criteria);
        return ResponseEntity.ok().body(conceptoConocimientosEspecialesClasificacionPremiosQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /concepto-conocimientos-especiales-clasificacion-premios/:id} : get the "id" conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param id the id of the conceptoConocimientosEspecialesClasificacionPremios to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conceptoConocimientosEspecialesClasificacionPremios, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/concepto-conocimientos-especiales-clasificacion-premios/{id}")
    public ResponseEntity<ConceptoConocimientosEspecialesClasificacionPremios> getConceptoConocimientosEspecialesClasificacionPremios(@PathVariable Long id) {
        log.debug("REST request to get ConceptoConocimientosEspecialesClasificacionPremios : {}", id);
        Optional<ConceptoConocimientosEspecialesClasificacionPremios> conceptoConocimientosEspecialesClasificacionPremios = conceptoConocimientosEspecialesClasificacionPremiosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conceptoConocimientosEspecialesClasificacionPremios);
    }

    /**
     * {@code DELETE  /concepto-conocimientos-especiales-clasificacion-premios/:id} : delete the "id" conceptoConocimientosEspecialesClasificacionPremios.
     *
     * @param id the id of the conceptoConocimientosEspecialesClasificacionPremios to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/concepto-conocimientos-especiales-clasificacion-premios/{id}")
    public ResponseEntity<Void> deleteConceptoConocimientosEspecialesClasificacionPremios(@PathVariable Long id) {
        log.debug("REST request to delete ConceptoConocimientosEspecialesClasificacionPremios : {}", id);
        conceptoConocimientosEspecialesClasificacionPremiosService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
