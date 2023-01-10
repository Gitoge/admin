package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Corps;
import com.sn.dtai.admin.repository.CorpsRepository;
import com.sn.dtai.admin.service.impl.CorpsServiceImpl;
import com.sn.dtai.admin.service.mapper.CorpsMapperImpl;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Corps}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CorpsResource {

    private final Logger log = LoggerFactory.getLogger(CorpsResource.class);

    private static final String ENTITY_NAME = "adminCorps";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CorpsRepository corpsRepository;

    private final CorpsServiceImpl corpsServiceImpl;

    public CorpsResource(CorpsRepository corpsRepository, CorpsServiceImpl corpsServiceImpl) {
        this.corpsRepository = corpsRepository;
        this.corpsServiceImpl = corpsServiceImpl;
    }

    /**
     * {@code POST  /corps} : Create a new corps.
     *
     * @param corps the corps to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new corps, or with status {@code 400 (Bad Request)} if the
     *         corps has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/corps")
    public ResponseEntity<Corps> createCorps(@Valid @RequestBody Corps corps) throws URISyntaxException {
        log.debug("REST request to save Corps : {}", corps);
        if (corps.getId() != null) {
            throw new BadRequestAlertException("A new corps cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code classe
        if (corps.getCode() == null || corps.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code corps
            Optional<Corps> verifCodeCorps = corpsServiceImpl.findByCode(corps.getCode());
            if (verifCodeCorps.isPresent()) {
                throw new BadRequestAlertException(
                        "Le code '" + corps.getCode() + "' est déjà utilisé : '" + verifCodeCorps.get().getCode() + "'",
                        ENTITY_NAME, "Le code '" + verifCodeCorps.get().getCode() + "' est déjà utilisé'.");
            }
        }

        // Vérif Validité code pages pour la structure connectée
        if (corps.getLibelle() == null || corps.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle roles
            Optional<Corps> verifLibelleCoprs = corpsServiceImpl.findByLibelle(corps.getLibelle());
            if (verifLibelleCoprs.isPresent()) {
                throw new BadRequestAlertException(
                        "Le Libelle '" + verifLibelleCoprs.get().getLibelle() + "' est déjà utilisé  '", ENTITY_NAME,
                        "Le libelle '" + verifLibelleCoprs.get().getLibelle() + "' est déjà utilisé . '");
            }
        }
        Corps result = corpsRepository.save(corps);
        return ResponseEntity
                .created(new URI("/api/corps/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /corps/:id} : Updates an existing corps.
     *
     * @param id    the id of the corps to save.
     * @param corps the corps to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated corps,
     *         or with status {@code 400 (Bad Request)} if the corps is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the corps
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/corps/{id}")
    public ResponseEntity<Corps> updateCorps(@PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody Corps corps)
            throws URISyntaxException {
        log.debug("REST request to update Corps : {}, {}", id, corps);
        if (corps.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, corps.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!corpsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Corps result = corpsRepository.save(corps);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        corps.getId().toString()))
                .body(result);
    }

    /**
     * {@code PATCH  /corps/:id} : Partial updates given fields of an existing
     * corps, field will ignore if it is null
     *
     * @param id    the id of the corps to save.
     * @param corps the corps to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated corps,
     *         or with status {@code 400 (Bad Request)} if the corps is not valid,
     *         or with status {@code 404 (Not Found)} if the corps is not found,
     *         or with status {@code 500 (Internal Server Error)} if the corps
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/corps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Corps> partialUpdateCorps(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody Corps corps) throws URISyntaxException {
        log.debug("REST request to partial update Corps partially : {}, {}", id, corps);
        if (corps.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, corps.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!corpsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Corps> result = corpsRepository
                .findById(corps.getId())
                .map(existingCorps -> {
                    if (corps.getCode() != null) {
                        existingCorps.setCode(corps.getCode());
                    }
                    if (corps.getLibelle() != null) {
                        existingCorps.setLibelle(corps.getLibelle());
                    }
                    if (corps.getDescription() != null) {
                        existingCorps.setDescription(corps.getDescription());
                    }
                    if (corps.getCodHierarchie() != null) {
                        existingCorps.setCodHierarchie(corps.getCodHierarchie());
                    }
                    if (corps.getAgeRetraite() != null) {
                        existingCorps.setAgeRetraite(corps.getAgeRetraite());
                    }
                    if (corps.getClassification() != null) {
                        existingCorps.setClassification(corps.getClassification());
                    }

                    return existingCorps;
                })
                .map(corpsRepository::save);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, corps.getId().toString()));
    }

    /**
     * {@code GET  /corps} : get all the corps.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of corps in body.
     */
    @GetMapping("/corps")
    public ResponseEntity<List<Corps>> getAllCorps(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Corps");
        Page<Corps> page = corpsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /corps/:id} : get the "id" corps.
     *
     * @param id the id of the corps to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the corps, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/corps/{id}")
    public ResponseEntity<Corps> getCorps(@PathVariable Long id) {
        log.debug("REST request to get Corps : {}", id);
        Optional<Corps> corps = corpsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(corps);
    }

    /**
     * {@code DELETE  /corps/:id} : delete the "id" corps.
     *
     * @param id the id of the corps to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/corps/{id}")
    public ResponseEntity<Void> deleteCorps(@PathVariable Long id) {
        log.debug("REST request to delete Corps : {}", id);
        corpsRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

     /**
     * {@code GET  /grades} : get all the grades.
     *
     * sans pagination
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of grades in body.
     */
    @GetMapping("/corps/all")
    public List<Corps> getAllCorps() {
        return corpsRepository.findAllCorps();
    }

}
