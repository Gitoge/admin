package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.ParamQuotiteCessibleRepository;
import com.sn.dtai.admin.service.ParamQuotiteCessibleService;
import com.sn.dtai.admin.service.dto.ParamQuotiteCessibleDTO;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.ParamQuotiteCessible}.
 */
@RestController
@RequestMapping("/api")
public class ParamQuotiteCessibleResource {

    private final Logger log = LoggerFactory.getLogger(ParamQuotiteCessibleResource.class);

    private static final String ENTITY_NAME = "adminParamQuotiteCessible";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamQuotiteCessibleService paramQuotiteCessibleService;

    private final ParamQuotiteCessibleRepository paramQuotiteCessibleRepository;

    public ParamQuotiteCessibleResource(
        ParamQuotiteCessibleService paramQuotiteCessibleService,
        ParamQuotiteCessibleRepository paramQuotiteCessibleRepository
    ) {
        this.paramQuotiteCessibleService = paramQuotiteCessibleService;
        this.paramQuotiteCessibleRepository = paramQuotiteCessibleRepository;
    }

    /**
     * {@code POST  /param-quotite-cessibles} : Create a new paramQuotiteCessible.
     *
     * @param paramQuotiteCessibleDTO the paramQuotiteCessibleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramQuotiteCessibleDTO, or with status {@code 400 (Bad Request)} if the paramQuotiteCessible has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-quotite-cessibles")
    public ResponseEntity<ParamQuotiteCessibleDTO> createParamQuotiteCessible(
        @Valid @RequestBody ParamQuotiteCessibleDTO paramQuotiteCessibleDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ParamQuotiteCessible : {}", paramQuotiteCessibleDTO);
        if (paramQuotiteCessibleDTO.getId() != null) {
            throw new BadRequestAlertException("A new paramQuotiteCessible cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamQuotiteCessibleDTO result = paramQuotiteCessibleService.save(paramQuotiteCessibleDTO);
        return ResponseEntity
            .created(new URI("/api/param-quotite-cessibles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-quotite-cessibles/:id} : Updates an existing paramQuotiteCessible.
     *
     * @param id the id of the paramQuotiteCessibleDTO to save.
     * @param paramQuotiteCessibleDTO the paramQuotiteCessibleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramQuotiteCessibleDTO,
     * or with status {@code 400 (Bad Request)} if the paramQuotiteCessibleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramQuotiteCessibleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-quotite-cessibles/{id}")
    public ResponseEntity<ParamQuotiteCessibleDTO> updateParamQuotiteCessible(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ParamQuotiteCessibleDTO paramQuotiteCessibleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParamQuotiteCessible : {}, {}", id, paramQuotiteCessibleDTO);
        if (paramQuotiteCessibleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramQuotiteCessibleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramQuotiteCessibleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ParamQuotiteCessibleDTO result = paramQuotiteCessibleService.update(paramQuotiteCessibleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramQuotiteCessibleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /param-quotite-cessibles/:id} : Partial updates given fields of an existing paramQuotiteCessible, field will ignore if it is null
     *
     * @param id the id of the paramQuotiteCessibleDTO to save.
     * @param paramQuotiteCessibleDTO the paramQuotiteCessibleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramQuotiteCessibleDTO,
     * or with status {@code 400 (Bad Request)} if the paramQuotiteCessibleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paramQuotiteCessibleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paramQuotiteCessibleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/param-quotite-cessibles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ParamQuotiteCessibleDTO> partialUpdateParamQuotiteCessible(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ParamQuotiteCessibleDTO paramQuotiteCessibleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParamQuotiteCessible partially : {}, {}", id, paramQuotiteCessibleDTO);
        if (paramQuotiteCessibleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramQuotiteCessibleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramQuotiteCessibleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ParamQuotiteCessibleDTO> result = paramQuotiteCessibleService.partialUpdate(paramQuotiteCessibleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramQuotiteCessibleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /param-quotite-cessibles} : get all the paramQuotiteCessibles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramQuotiteCessibles in body.
     */
    @GetMapping("/param-quotite-cessibles")
    public ResponseEntity<List<ParamQuotiteCessibleDTO>> getAllParamQuotiteCessibles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ParamQuotiteCessibles");
        Page<ParamQuotiteCessibleDTO> page = paramQuotiteCessibleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /param-quotite-cessibles/:id} : get the "id" paramQuotiteCessible.
     *
     * @param id the id of the paramQuotiteCessibleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramQuotiteCessibleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-quotite-cessibles/{id}")
    public ResponseEntity<ParamQuotiteCessibleDTO> getParamQuotiteCessible(@PathVariable Long id) {
        log.debug("REST request to get ParamQuotiteCessible : {}", id);
        Optional<ParamQuotiteCessibleDTO> paramQuotiteCessibleDTO = paramQuotiteCessibleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paramQuotiteCessibleDTO);
    }

    /**
     * {@code DELETE  /param-quotite-cessibles/:id} : delete the "id" paramQuotiteCessible.
     *
     * @param id the id of the paramQuotiteCessibleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-quotite-cessibles/{id}")
    public ResponseEntity<Void> deleteParamQuotiteCessible(@PathVariable Long id) {
        log.debug("REST request to delete ParamQuotiteCessible : {}", id);
        paramQuotiteCessibleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
