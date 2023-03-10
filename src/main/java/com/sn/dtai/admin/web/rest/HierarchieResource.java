package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Hierarchie;
import com.sn.dtai.admin.repository.HierarchieRepository;
import com.sn.dtai.admin.service.dto.HierarchieDTO;
import com.sn.dtai.admin.service.mapper.HierarchieMapper;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Hierarchie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class HierarchieResource {

    private final Logger log = LoggerFactory.getLogger(HierarchieResource.class);

    private static final String ENTITY_NAME = "adminHierarchie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HierarchieRepository hierarchieRepository;
    
    private final HierarchieMapper hierarchieMapper;

    public HierarchieResource(HierarchieRepository hierarchieRepository, HierarchieMapper hierarchieMapper) {
        this.hierarchieRepository = hierarchieRepository;
        this.hierarchieMapper = hierarchieMapper;
    }

    /**
     * {@code POST  /hierarchies} : Create a new hierarchie.
     *
     * @param hierarchie the hierarchie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new hierarchie, or with status {@code 400 (Bad Request)} if
     *         the hierarchie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hierarchies")
    public ResponseEntity<Hierarchie> createHierarchie(@Valid @RequestBody Hierarchie hierarchie)
            throws URISyntaxException {
        log.debug("REST request to save Hierarchie : {}", hierarchie);
        if (hierarchie.getId() != null) {
            throw new BadRequestAlertException("A new hierarchie cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<HierarchieDTO> hierarchieOP = hierarchieRepository.findByCode(hierarchie.getCode()).map(hierarchieMapper::toDto);

        if(hierarchieOP.isPresent()){
            throw new CodeException();
        }

        Hierarchie result = hierarchieRepository.save(hierarchie);
        return ResponseEntity
                .created(new URI("/api/hierarchies/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /hierarchies/:id} : Updates an existing hierarchie.
     *
     * @param id         the id of the hierarchie to save.
     * @param hierarchie the hierarchie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated hierarchie,
     *         or with status {@code 400 (Bad Request)} if the hierarchie is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the hierarchie
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hierarchies/{id}")
    public ResponseEntity<Hierarchie> updateHierarchie(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody Hierarchie hierarchie) throws URISyntaxException {
        log.debug("REST request to update Hierarchie : {}, {}", id, hierarchie);
        if (hierarchie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hierarchie.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hierarchieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Hierarchie result = hierarchieRepository.save(hierarchie);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        hierarchie.getId().toString()))
                .body(result);
    }

    /**
     * {@code PATCH  /hierarchies/:id} : Partial updates given fields of an existing
     * hierarchie, field will ignore if it is null
     *
     * @param id         the id of the hierarchie to save.
     * @param hierarchie the hierarchie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated hierarchie,
     *         or with status {@code 400 (Bad Request)} if the hierarchie is not
     *         valid,
     *         or with status {@code 404 (Not Found)} if the hierarchie is not
     *         found,
     *         or with status {@code 500 (Internal Server Error)} if the hierarchie
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hierarchies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Hierarchie> partialUpdateHierarchie(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody Hierarchie hierarchie) throws URISyntaxException {
        log.debug("REST request to partial update Hierarchie partially : {}, {}", id, hierarchie);
        if (hierarchie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hierarchie.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hierarchieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Hierarchie> result = hierarchieRepository
                .findById(hierarchie.getId())
                .map(existingHierarchie -> {
                    if (hierarchie.getCode() != null) {
                        existingHierarchie.setCode(hierarchie.getCode());
                    }
                    if (hierarchie.getLibelle() != null) {
                        existingHierarchie.setLibelle(hierarchie.getLibelle());
                    }
                    if (hierarchie.getBorneInferieure() != null) {
                        existingHierarchie.setBorneInferieure(hierarchie.getBorneInferieure());
                    }
                    if (hierarchie.getBorneSuperieure() != null) {
                        existingHierarchie.setBorneSuperieure(hierarchie.getBorneSuperieure());
                    }
                    if (hierarchie.getCodEchelonIndiciare() != null) {
                        existingHierarchie.setCodEchelonIndiciare(hierarchie.getCodEchelonIndiciare());
                    }
                    if (hierarchie.getHcadre() != null) {
                        existingHierarchie.setHcadre(hierarchie.getHcadre());
                    }

                    return existingHierarchie;
                })
                .map(hierarchieRepository::save);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hierarchie.getId().toString()));
    }

    /**
     * {@code GET  /hierarchies} : get all the hierarchies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of hierarchies in body.
     */
    @GetMapping("/hierarchies")
    public ResponseEntity<List<Hierarchie>> getAllHierarchies(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Hierarchies");
        Page<Hierarchie> page = hierarchieRepository.findAllHierarchies(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hierarchies/:id} : get the "id" hierarchie.
     *
     * @param id the id of the hierarchie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the hierarchie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hierarchies/{id}")
    public ResponseEntity<Hierarchie> getHierarchie(@PathVariable Long id) {
        log.debug("REST request to get Hierarchie : {}", id);
        Optional<Hierarchie> hierarchie = hierarchieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hierarchie);
    }

    /**
     * {@code DELETE  /hierarchies/:id} : delete the "id" hierarchie.
     *
     * @param id the id of the hierarchie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hierarchies/{id}")
    public ResponseEntity<Void> deleteHierarchie(@PathVariable Long id) {
        log.debug("REST request to delete Hierarchie : {}", id);
        hierarchieRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    /**
     * {@code GET  /hierarchie} : get all the hierarchies.
     *
     * sans pagination
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of hierarchies in body.
     */
    @GetMapping("/hierarchies/all")
    public List<Hierarchie> getAllHierarchies() {
        return hierarchieRepository.findAllHierarchies();
    }

      /**
     * {@code GET  /hierarchie} : get all the hierarchies.
     *
     * sans pagination
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of hierarchies in body.
     */
    @GetMapping("/hierarchies/bornes/{valeur}")
    public Optional<Hierarchie> getHierarchieByBornes(@PathVariable String valeur) {
        return hierarchieRepository.findHierarchieByBornes(valeur);
    }
}
