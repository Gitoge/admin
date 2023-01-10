package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.DiplomesRepository;
import com.sn.dtai.admin.service.DiplomesService;
import com.sn.dtai.admin.service.dto.DiplomesDTO;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Diplomes}.
 */
@RestController
@RequestMapping("/api")
public class DiplomesResource {

    private final Logger log = LoggerFactory.getLogger(DiplomesResource.class);

    private static final String ENTITY_NAME = "adminDiplomes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DiplomesService diplomesService;

    private final DiplomesRepository diplomesRepository;

    public DiplomesResource(DiplomesService diplomesService, DiplomesRepository diplomesRepository) {
        this.diplomesService = diplomesService;
        this.diplomesRepository = diplomesRepository;
    }

    /**
     * {@code POST  /diplomes} : Create a new diplomes.
     *
     * @param diplomesDTO the diplomesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new diplomesDTO, or with status {@code 400 (Bad Request)} if the diplomes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/diplomes")
    public ResponseEntity<DiplomesDTO> createDiplomes(@Valid @RequestBody DiplomesDTO diplomesDTO) throws URISyntaxException {
        log.debug("REST request to save Diplomes : {}", diplomesDTO);
        if (diplomesDTO.getId() != null) {
            throw new BadRequestAlertException("A new diplomes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiplomesDTO result = diplomesService.save(diplomesDTO);
        return ResponseEntity
            .created(new URI("/api/diplomes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /diplomes/:id} : Updates an existing diplomes.
     *
     * @param id the id of the diplomesDTO to save.
     * @param diplomesDTO the diplomesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated diplomesDTO,
     * or with status {@code 400 (Bad Request)} if the diplomesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the diplomesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/diplomes/{id}")
    public ResponseEntity<DiplomesDTO> updateDiplomes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DiplomesDTO diplomesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Diplomes : {}, {}", id, diplomesDTO);
        if (diplomesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, diplomesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!diplomesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DiplomesDTO result = diplomesService.update(diplomesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, diplomesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /diplomes/:id} : Partial updates given fields of an existing diplomes, field will ignore if it is null
     *
     * @param id the id of the diplomesDTO to save.
     * @param diplomesDTO the diplomesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated diplomesDTO,
     * or with status {@code 400 (Bad Request)} if the diplomesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the diplomesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the diplomesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/diplomes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DiplomesDTO> partialUpdateDiplomes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DiplomesDTO diplomesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Diplomes partially : {}, {}", id, diplomesDTO);
        if (diplomesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, diplomesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!diplomesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DiplomesDTO> result = diplomesService.partialUpdate(diplomesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, diplomesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /diplomes} : get all the diplomes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of diplomes in body.
     */
    @GetMapping("/diplomes")
    public ResponseEntity<List<DiplomesDTO>> getAllDiplomes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Diplomes");
        Page<DiplomesDTO> page = diplomesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /diplomes/:id} : get the "id" diplomes.
     *
     * @param id the id of the diplomesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the diplomesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/diplomes/{id}")
    public ResponseEntity<DiplomesDTO> getDiplomes(@PathVariable Long id) {
        log.debug("REST request to get Diplomes : {}", id);
        Optional<DiplomesDTO> diplomesDTO = diplomesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diplomesDTO);
    }

    /**
     * {@code DELETE  /diplomes/:id} : delete the "id" diplomes.
     *
     * @param id the id of the diplomesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/diplomes/{id}")
    public ResponseEntity<Void> deleteDiplomes(@PathVariable Long id) {
        log.debug("REST request to delete Diplomes : {}", id);
        diplomesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
