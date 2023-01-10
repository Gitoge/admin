package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.NatureActesRepository;
import com.sn.dtai.admin.service.NatureActesService;
import com.sn.dtai.admin.service.dto.NatureActesDTO;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.NatureActes}.
 */
@RestController
@RequestMapping("/api")
public class NatureActesResource {

    private final Logger log = LoggerFactory.getLogger(NatureActesResource.class);

    private static final String ENTITY_NAME = "adminNatureActes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureActesService natureActesService;

    private final NatureActesRepository natureActesRepository;

    public NatureActesResource(NatureActesService natureActesService, NatureActesRepository natureActesRepository) {
        this.natureActesService = natureActesService;
        this.natureActesRepository = natureActesRepository;
    }

    /**
     * {@code POST  /nature-actes} : Create a new natureActes.
     *
     * @param natureActesDTO the natureActesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new natureActesDTO, or with status {@code 400 (Bad Request)} if the natureActes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-actes")
    public ResponseEntity<NatureActesDTO> createNatureActes(@Valid @RequestBody NatureActesDTO natureActesDTO) throws URISyntaxException {
        log.debug("REST request to save NatureActes : {}", natureActesDTO);
        if (natureActesDTO.getId() != null) {
            throw new BadRequestAlertException("A new natureActes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureActesDTO result = natureActesService.save(natureActesDTO);
        return ResponseEntity
            .created(new URI("/api/nature-actes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nature-actes/:id} : Updates an existing natureActes.
     *
     * @param id the id of the natureActesDTO to save.
     * @param natureActesDTO the natureActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureActesDTO,
     * or with status {@code 400 (Bad Request)} if the natureActesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the natureActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-actes/{id}")
    public ResponseEntity<NatureActesDTO> updateNatureActes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NatureActesDTO natureActesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NatureActes : {}, {}", id, natureActesDTO);
        if (natureActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, natureActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!natureActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NatureActesDTO result = natureActesService.update(natureActesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, natureActesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /nature-actes/:id} : Partial updates given fields of an existing natureActes, field will ignore if it is null
     *
     * @param id the id of the natureActesDTO to save.
     * @param natureActesDTO the natureActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureActesDTO,
     * or with status {@code 400 (Bad Request)} if the natureActesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the natureActesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the natureActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/nature-actes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NatureActesDTO> partialUpdateNatureActes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NatureActesDTO natureActesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NatureActes partially : {}, {}", id, natureActesDTO);
        if (natureActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, natureActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!natureActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NatureActesDTO> result = natureActesService.partialUpdate(natureActesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, natureActesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /nature-actes} : get all the natureActes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of natureActes in body.
     */
    @GetMapping("/nature-actes")
    public ResponseEntity<List<NatureActesDTO>> getAllNatureActes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of NatureActes");
        Page<NatureActesDTO> page = natureActesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nature-actes/:id} : get the "id" natureActes.
     *
     * @param id the id of the natureActesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the natureActesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-actes/{id}")
    public ResponseEntity<NatureActesDTO> getNatureActes(@PathVariable Long id) {
        log.debug("REST request to get NatureActes : {}", id);
        Optional<NatureActesDTO> natureActesDTO = natureActesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureActesDTO);
    }

    /**
     * {@code DELETE  /nature-actes/:id} : delete the "id" natureActes.
     *
     * @param id the id of the natureActesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-actes/{id}")
    public ResponseEntity<Void> deleteNatureActes(@PathVariable Long id) {
        log.debug("REST request to delete NatureActes : {}", id);
        natureActesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
