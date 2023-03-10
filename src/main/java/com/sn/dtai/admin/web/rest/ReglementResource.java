package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Reglement;
import com.sn.dtai.admin.repository.ReglementRepository;
import com.sn.dtai.admin.service.dto.ReglementDTO;
import com.sn.dtai.admin.service.mapper.ReglementMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import com.sn.dtai.admin.web.rest.errors.CodeException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Reglement}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ReglementResource {

    private final Logger log = LoggerFactory.getLogger(ReglementResource.class);

    private static final String ENTITY_NAME = "adminReglement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReglementRepository reglementRepository;
    
    private final ReglementMapper reglementMapper;

    public ReglementResource(ReglementRepository reglementRepository, ReglementMapper reglementMapper) {
        this.reglementRepository = reglementRepository;
        this.reglementMapper = reglementMapper;
    }

    /**
     * {@code POST  /reglements} : Create a new reglement.
     *
     * @param reglement the reglement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reglement, or with status {@code 400 (Bad Request)} if the reglement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reglements")
    public ResponseEntity<Reglement> createReglement(@Valid @RequestBody Reglement reglement) throws URISyntaxException {
        log.debug("REST request to save Reglement : {}", reglement);
        if (reglement.getId() != null) {
            throw new BadRequestAlertException("A new reglement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<ReglementDTO> reglementOP = reglementRepository.findByCode(reglement.getCode()).map(reglementMapper::toDto);

        if(reglementOP.isPresent()){
            throw new CodeException();
        }
        Reglement result = reglementRepository.save(reglement);
        return ResponseEntity
            .created(new URI("/api/reglements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reglements/:id} : Updates an existing reglement.
     *
     * @param id the id of the reglement to save.
     * @param reglement the reglement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reglement,
     * or with status {@code 400 (Bad Request)} if the reglement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reglement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reglements/{id}")
    public ResponseEntity<Reglement> updateReglement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Reglement reglement
    ) throws URISyntaxException {
        log.debug("REST request to update Reglement : {}, {}", id, reglement);
        if (reglement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reglement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reglementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Reglement result = reglementRepository.save(reglement);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reglement.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reglements/:id} : Partial updates given fields of an existing reglement, field will ignore if it is null
     *
     * @param id the id of the reglement to save.
     * @param reglement the reglement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reglement,
     * or with status {@code 400 (Bad Request)} if the reglement is not valid,
     * or with status {@code 404 (Not Found)} if the reglement is not found,
     * or with status {@code 500 (Internal Server Error)} if the reglement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reglements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Reglement> partialUpdateReglement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Reglement reglement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Reglement partially : {}, {}", id, reglement);
        if (reglement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reglement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reglementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Reglement> result = reglementRepository
            .findById(reglement.getId())
            .map(existingReglement -> {
                if (reglement.getCode() != null) {
                    existingReglement.setCode(reglement.getCode());
                }
                if (reglement.getLibelle() != null) {
                    existingReglement.setLibelle(reglement.getLibelle());
                }
                if (reglement.getComplementinfos() != null) {
                    existingReglement.setComplementinfos(reglement.getComplementinfos());
                }
                if (reglement.getCommentaire() != null) {
                    existingReglement.setCommentaire(reglement.getCommentaire());
                }

                return existingReglement;
            })
            .map(reglementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reglement.getId().toString())
        );
    }

    /**
     * {@code GET  /reglements} : get all the reglements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reglements in body.
     */
    @GetMapping("/reglements")
    public ResponseEntity<List<Reglement>> getAllReglements(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Reglements");
        Page<Reglement> page = reglementRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reglements/:id} : get the "id" reglement.
     *
     * @param id the id of the reglement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reglement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reglements/{id}")
    public ResponseEntity<Reglement> getReglement(@PathVariable Long id) {
        log.debug("REST request to get Reglement : {}", id);
        Optional<Reglement> reglement = reglementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(reglement);
    }

    /**
     * {@code DELETE  /reglements/:id} : delete the "id" reglement.
     *
     * @param id the id of the reglement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reglements/{id}")
    public ResponseEntity<Void> deleteReglement(@PathVariable Long id) {
        log.debug("REST request to delete Reglement : {}", id);
        reglementRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
