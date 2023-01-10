package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.CategorieActesRepository;
import com.sn.dtai.admin.service.CategorieActesService;
import com.sn.dtai.admin.service.dto.CategorieActesDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.CategorieActes}.
 */
@RestController
@RequestMapping("/api")
public class CategorieActesResource {
    private final Logger log = LoggerFactory.getLogger(CategorieActesResource.class);

    private static final String ENTITY_NAME = "adminCategorieActes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieActesService categorieActesService;

    private final CategorieActesRepository categorieActesRepository;

    public CategorieActesResource(CategorieActesService categorieActesService, CategorieActesRepository categorieActesRepository) {
        this.categorieActesService = categorieActesService;
        this.categorieActesRepository = categorieActesRepository;
    }

    /**
     * {@code POST  /categorie-actes} : Create a new categorieActes.
     *
     * @param categorieActesDTO the categorieActesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieActesDTO, or with status {@code 400 (Bad Request)} if the categorieActes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-actes")
    public ResponseEntity<CategorieActesDTO> createCategorieActes(@Valid @RequestBody CategorieActesDTO categorieActesDTO)
        throws URISyntaxException {
        log.debug("REST request to save CategorieActes : {}", categorieActesDTO);
        if (categorieActesDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieActes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieActesDTO result = categorieActesService.save(categorieActesDTO);
        return ResponseEntity
            .created(new URI("/api/categorie-actes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-actes/:id} : Updates an existing categorieActes.
     *
     * @param id the id of the categorieActesDTO to save.
     * @param categorieActesDTO the categorieActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieActesDTO,
     * or with status {@code 400 (Bad Request)} if the categorieActesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-actes/{id}")
    public ResponseEntity<CategorieActesDTO> updateCategorieActes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CategorieActesDTO categorieActesDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update CategorieActes : {}, {}", id, categorieActesDTO);
        if (categorieActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categorieActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categorieActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CategorieActesDTO result = categorieActesService.update(categorieActesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieActesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /categorie-actes/:id} : Partial updates given fields of an existing categorieActes, field will ignore if it is null
     *
     * @param id the id of the categorieActesDTO to save.
     * @param categorieActesDTO the categorieActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieActesDTO,
     * or with status {@code 400 (Bad Request)} if the categorieActesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the categorieActesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the categorieActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/categorie-actes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CategorieActesDTO> partialUpdateCategorieActes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CategorieActesDTO categorieActesDTO
    )
        throws URISyntaxException {
        log.debug("REST request to partial update CategorieActes partially : {}, {}", id, categorieActesDTO);
        if (categorieActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categorieActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categorieActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategorieActesDTO> result = categorieActesService.partialUpdate(categorieActesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieActesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /categorie-actes} : get all the categorieActes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieActes in body.
     */
    @GetMapping("/categorie-actes")
    public ResponseEntity<List<CategorieActesDTO>> getAllCategorieActes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of CategorieActes");
        Page<CategorieActesDTO> page = categorieActesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-actes/:id} : get the "id" categorieActes.
     *
     * @param id the id of the categorieActesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieActesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-actes/{id}")
    public ResponseEntity<CategorieActesDTO> getCategorieActes(@PathVariable Long id) {
        log.debug("REST request to get CategorieActes : {}", id);
        try {
            Optional<CategorieActesDTO> categorieActesDTO = categorieActesService.findOne(id);
            return ResponseUtil.wrapOrNotFound(categorieActesDTO);
        } catch (Exception e) {
            throw new BadRequestAlertException("Catégorie acte Introuvable", ENTITY_NAME, "Catégorie Introuvable");
        }
    }

    /**
     * {@code DELETE  /categorie-actes/:id} : delete the "id" categorieActes.
     *
     * @param id the id of the categorieActesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-actes/{id}")
    public ResponseEntity<Void> deleteCategorieActes(@PathVariable Long id) {
        log.debug("REST request to delete CategorieActes : {}", id);
        categorieActesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
