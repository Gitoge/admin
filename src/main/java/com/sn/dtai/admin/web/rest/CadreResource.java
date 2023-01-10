package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Cadre;
import com.sn.dtai.admin.repository.CadreRepository;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Cadre}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CadreResource {

    private final Logger log = LoggerFactory.getLogger(CadreResource.class);

    private static final String ENTITY_NAME = "adminCadre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CadreRepository cadreRepository;

    public CadreResource(CadreRepository cadreRepository) {
        this.cadreRepository = cadreRepository;
    }

    /**
     * {@code POST  /cadres} : Create a new cadre.
     *
     * @param cadre the cadre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cadre, or with status {@code 400 (Bad Request)} if the cadre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cadres")
    public ResponseEntity<Cadre> createCadre(@Valid @RequestBody Cadre cadre) throws URISyntaxException {
        log.debug("REST request to save Cadre : {}", cadre);
        if (cadre.getId() != null) {
            throw new BadRequestAlertException("A new cadre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cadre result = cadreRepository.save(cadre);
        return ResponseEntity
            .created(new URI("/api/cadres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cadres/:id} : Updates an existing cadre.
     *
     * @param id the id of the cadre to save.
     * @param cadre the cadre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cadre,
     * or with status {@code 400 (Bad Request)} if the cadre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cadre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cadres/{id}")
    public ResponseEntity<Cadre> updateCadre(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Cadre cadre)
        throws URISyntaxException {
        log.debug("REST request to update Cadre : {}, {}", id, cadre);
        if (cadre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cadre.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cadreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Cadre result = cadreRepository.save(cadre);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cadre.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cadres/:id} : Partial updates given fields of an existing cadre, field will ignore if it is null
     *
     * @param id the id of the cadre to save.
     * @param cadre the cadre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cadre,
     * or with status {@code 400 (Bad Request)} if the cadre is not valid,
     * or with status {@code 404 (Not Found)} if the cadre is not found,
     * or with status {@code 500 (Internal Server Error)} if the cadre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cadres/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cadre> partialUpdateCadre(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Cadre cadre
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cadre partially : {}, {}", id, cadre);
        if (cadre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cadre.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cadreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Cadre> result = cadreRepository
            .findById(cadre.getId())
            .map(existingCadre -> {
                if (cadre.getCode() != null) {
                    existingCadre.setCode(cadre.getCode());
                }
                if (cadre.getLibelle() != null) {
                    existingCadre.setLibelle(cadre.getLibelle());
                }
                if (cadre.getDescription() != null) {
                    existingCadre.setDescription(cadre.getDescription());
                }
                if (cadre.getTypeSalaire() != null) {
                    existingCadre.setTypeSalaire(cadre.getTypeSalaire());
                }

                return existingCadre;
            })
            .map(cadreRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cadre.getId().toString())
        );
    }

    /**
     * {@code GET  /cadres} : get all the cadres.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cadres in body.
     */
    @GetMapping("/cadres")
    public ResponseEntity<List<Cadre>> getAllCadres(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Cadres");
        Page<Cadre> page = cadreRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cadres/:id} : get the "id" cadre.
     *
     * @param id the id of the cadre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cadre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cadres/{id}")
    public ResponseEntity<Cadre> getCadre(@PathVariable Long id) {
        log.debug("REST request to get Cadre : {}", id);
        try{
        Optional<Cadre> cadre = cadreRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cadre);
         }
        catch(Exception e){
            throw new BadRequestAlertException("Cadre Introuvable", ENTITY_NAME,"Cadre Introuvable");
        }
    }

    /**
     * {@code DELETE  /cadres/:id} : delete the "id" cadre.
     *
     * @param id the id of the cadre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cadres/{id}")
    public ResponseEntity<Void> deleteCadre(@PathVariable Long id) {
        log.debug("REST request to delete Cadre : {}", id);
        cadreRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
