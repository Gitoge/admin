package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.ParamBaremeMinimumFiscalRepository;
import com.sn.dtai.admin.service.ParamBaremeMinimumFiscalService;
import com.sn.dtai.admin.service.dto.ParamBaremeMinimumFiscalDTO;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.ParamBaremeMinimumFiscal}.
 */
@RestController
@RequestMapping("/api")
public class ParamBaremeMinimumFiscalResource {

    private final Logger log = LoggerFactory.getLogger(ParamBaremeMinimumFiscalResource.class);

    private static final String ENTITY_NAME = "adminParamBaremeMinimumFiscal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamBaremeMinimumFiscalService paramBaremeMinimumFiscalService;

    private final ParamBaremeMinimumFiscalRepository paramBaremeMinimumFiscalRepository;

    public ParamBaremeMinimumFiscalResource(
        ParamBaremeMinimumFiscalService paramBaremeMinimumFiscalService,
        ParamBaremeMinimumFiscalRepository paramBaremeMinimumFiscalRepository
    ) {
        this.paramBaremeMinimumFiscalService = paramBaremeMinimumFiscalService;
        this.paramBaremeMinimumFiscalRepository = paramBaremeMinimumFiscalRepository;
    }

    /**
     * {@code POST  /param-bareme-minimum-fiscals} : Create a new paramBaremeMinimumFiscal.
     *
     * @param paramBaremeMinimumFiscalDTO the paramBaremeMinimumFiscalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramBaremeMinimumFiscalDTO, or with status {@code 400 (Bad Request)} if the paramBaremeMinimumFiscal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-bareme-minimum-fiscals")
    public ResponseEntity<ParamBaremeMinimumFiscalDTO> createParamBaremeMinimumFiscal(
        @Valid @RequestBody ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ParamBaremeMinimumFiscal : {}", paramBaremeMinimumFiscalDTO);
        if (paramBaremeMinimumFiscalDTO.getId() != null) {
            throw new BadRequestAlertException("A new paramBaremeMinimumFiscal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamBaremeMinimumFiscalDTO result = paramBaremeMinimumFiscalService.save(paramBaremeMinimumFiscalDTO);
        return ResponseEntity
            .created(new URI("/api/param-bareme-minimum-fiscals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-bareme-minimum-fiscals/:id} : Updates an existing paramBaremeMinimumFiscal.
     *
     * @param id the id of the paramBaremeMinimumFiscalDTO to save.
     * @param paramBaremeMinimumFiscalDTO the paramBaremeMinimumFiscalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramBaremeMinimumFiscalDTO,
     * or with status {@code 400 (Bad Request)} if the paramBaremeMinimumFiscalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramBaremeMinimumFiscalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-bareme-minimum-fiscals/{id}")
    public ResponseEntity<ParamBaremeMinimumFiscalDTO> updateParamBaremeMinimumFiscal(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParamBaremeMinimumFiscal : {}, {}", id, paramBaremeMinimumFiscalDTO);
        if (paramBaremeMinimumFiscalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramBaremeMinimumFiscalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramBaremeMinimumFiscalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ParamBaremeMinimumFiscalDTO result = paramBaremeMinimumFiscalService.update(paramBaremeMinimumFiscalDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramBaremeMinimumFiscalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /param-bareme-minimum-fiscals/:id} : Partial updates given fields of an existing paramBaremeMinimumFiscal, field will ignore if it is null
     *
     * @param id the id of the paramBaremeMinimumFiscalDTO to save.
     * @param paramBaremeMinimumFiscalDTO the paramBaremeMinimumFiscalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramBaremeMinimumFiscalDTO,
     * or with status {@code 400 (Bad Request)} if the paramBaremeMinimumFiscalDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paramBaremeMinimumFiscalDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paramBaremeMinimumFiscalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/param-bareme-minimum-fiscals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ParamBaremeMinimumFiscalDTO> partialUpdateParamBaremeMinimumFiscal(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParamBaremeMinimumFiscal partially : {}, {}", id, paramBaremeMinimumFiscalDTO);
        if (paramBaremeMinimumFiscalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramBaremeMinimumFiscalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramBaremeMinimumFiscalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ParamBaremeMinimumFiscalDTO> result = paramBaremeMinimumFiscalService.partialUpdate(paramBaremeMinimumFiscalDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramBaremeMinimumFiscalDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /param-bareme-minimum-fiscals} : get all the paramBaremeMinimumFiscals.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramBaremeMinimumFiscals in body.
     */
    @GetMapping("/param-bareme-minimum-fiscals")
    public ResponseEntity<List<ParamBaremeMinimumFiscalDTO>> getAllParamBaremeMinimumFiscals(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ParamBaremeMinimumFiscals");
        Page<ParamBaremeMinimumFiscalDTO> page = paramBaremeMinimumFiscalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /param-bareme-minimum-fiscals/:id} : get the "id" paramBaremeMinimumFiscal.
     *
     * @param id the id of the paramBaremeMinimumFiscalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramBaremeMinimumFiscalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-bareme-minimum-fiscals/{id}")
    public ResponseEntity<ParamBaremeMinimumFiscalDTO> getParamBaremeMinimumFiscal(@PathVariable Long id) {
        log.debug("REST request to get ParamBaremeMinimumFiscal : {}", id);
        Optional<ParamBaremeMinimumFiscalDTO> paramBaremeMinimumFiscalDTO = paramBaremeMinimumFiscalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paramBaremeMinimumFiscalDTO);
    }

    /**
     * {@code DELETE  /param-bareme-minimum-fiscals/:id} : delete the "id" paramBaremeMinimumFiscal.
     *
     * @param id the id of the paramBaremeMinimumFiscalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-bareme-minimum-fiscals/{id}")
    public ResponseEntity<Void> deleteParamBaremeMinimumFiscal(@PathVariable Long id) {
        log.debug("REST request to delete ParamBaremeMinimumFiscal : {}", id);
        paramBaremeMinimumFiscalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
