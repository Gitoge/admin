package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.TypeActesRepository;
import com.sn.dtai.admin.service.TypeActesService;
import com.sn.dtai.admin.service.dto.TypeActesDTO;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.TypeActes}.
 */
@RestController
@RequestMapping("/api")
public class TypeActesResource {

    private final Logger log = LoggerFactory.getLogger(TypeActesResource.class);

    private static final String ENTITY_NAME = "adminTypeActes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeActesService typeActesService;

    private final TypeActesRepository typeActesRepository;

    public TypeActesResource(TypeActesService typeActesService, TypeActesRepository typeActesRepository) {
        this.typeActesService = typeActesService;
        this.typeActesRepository = typeActesRepository;
    }

    /**
     * {@code POST  /type-actes} : Create a new typeActes.
     *
     * @param typeActesDTO the typeActesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeActesDTO, or with status {@code 400 (Bad Request)} if the typeActes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-actes")
    public ResponseEntity<TypeActesDTO> createTypeActes(@Valid @RequestBody TypeActesDTO typeActesDTO) throws URISyntaxException {
        log.debug("REST request to save TypeActes : {}", typeActesDTO);
        if (typeActesDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeActes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeActesDTO result = typeActesService.save(typeActesDTO);
        return ResponseEntity
            .created(new URI("/api/type-actes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-actes/:id} : Updates an existing typeActes.
     *
     * @param id the id of the typeActesDTO to save.
     * @param typeActesDTO the typeActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeActesDTO,
     * or with status {@code 400 (Bad Request)} if the typeActesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-actes/{id}")
    public ResponseEntity<TypeActesDTO> updateTypeActes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TypeActesDTO typeActesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TypeActes : {}, {}", id, typeActesDTO);
        if (typeActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TypeActesDTO result = typeActesService.update(typeActesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeActesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /type-actes/:id} : Partial updates given fields of an existing typeActes, field will ignore if it is null
     *
     * @param id the id of the typeActesDTO to save.
     * @param typeActesDTO the typeActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeActesDTO,
     * or with status {@code 400 (Bad Request)} if the typeActesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the typeActesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the typeActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/type-actes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypeActesDTO> partialUpdateTypeActes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TypeActesDTO typeActesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypeActes partially : {}, {}", id, typeActesDTO);
        if (typeActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypeActesDTO> result = typeActesService.partialUpdate(typeActesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeActesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /type-actes} : get all the typeActes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeActes in body.
     */
    @GetMapping("/type-actes")
    public ResponseEntity<List<TypeActesDTO>> getAllTypeActes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TypeActes");
        Page<TypeActesDTO> page = typeActesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-actes/:id} : get the "id" typeActes.
     *
     * @param id the id of the typeActesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeActesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-actes/{id}")
    public ResponseEntity<TypeActesDTO> getTypeActes(@PathVariable Long id) {
        log.debug("REST request to get TypeActes : {}", id);
        Optional<TypeActesDTO> typeActesDTO = typeActesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeActesDTO);
    }

    /**
     * {@code DELETE  /type-actes/:id} : delete the "id" typeActes.
     *
     * @param id the id of the typeActesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-actes/{id}")
    public ResponseEntity<Void> deleteTypeActes(@PathVariable Long id) {
        log.debug("REST request to delete TypeActes : {}", id);
        typeActesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
