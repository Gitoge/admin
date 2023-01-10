package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.ParamBulletinsRepository;
import com.sn.dtai.admin.service.ParamBulletinsService;
import com.sn.dtai.admin.service.dto.ParamBulletinsDTO;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.ParamBulletins}.
 */
@RestController
@RequestMapping("/api")
public class ParamBulletinsResource {

    private final Logger log = LoggerFactory.getLogger(ParamBulletinsResource.class);

    private static final String ENTITY_NAME = "adminParamBulletins";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamBulletinsService paramBulletinsService;

    private final ParamBulletinsRepository paramBulletinsRepository;

    public ParamBulletinsResource(ParamBulletinsService paramBulletinsService, ParamBulletinsRepository paramBulletinsRepository) {
        this.paramBulletinsService = paramBulletinsService;
        this.paramBulletinsRepository = paramBulletinsRepository;
    }

    /**
     * {@code POST  /param-bulletins} : Create a new paramBulletins.
     *
     * @param paramBulletinsDTO the paramBulletinsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramBulletinsDTO, or with status {@code 400 (Bad Request)} if the paramBulletins has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-bulletins")
    public ResponseEntity<ParamBulletinsDTO> createParamBulletins(@Valid @RequestBody ParamBulletinsDTO paramBulletinsDTO)
        throws URISyntaxException {
        log.debug("REST request to save ParamBulletins : {}", paramBulletinsDTO);
        if (paramBulletinsDTO.getId() != null) {
            throw new BadRequestAlertException("A new paramBulletins cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamBulletinsDTO result = paramBulletinsService.save(paramBulletinsDTO);
        return ResponseEntity
            .created(new URI("/api/param-bulletins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-bulletins/:id} : Updates an existing paramBulletins.
     *
     * @param id the id of the paramBulletinsDTO to save.
     * @param paramBulletinsDTO the paramBulletinsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramBulletinsDTO,
     * or with status {@code 400 (Bad Request)} if the paramBulletinsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramBulletinsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-bulletins/{id}")
    public ResponseEntity<ParamBulletinsDTO> updateParamBulletins(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ParamBulletinsDTO paramBulletinsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParamBulletins : {}, {}", id, paramBulletinsDTO);
        if (paramBulletinsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramBulletinsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramBulletinsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ParamBulletinsDTO result = paramBulletinsService.update(paramBulletinsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramBulletinsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /param-bulletins/:id} : Partial updates given fields of an existing paramBulletins, field will ignore if it is null
     *
     * @param id the id of the paramBulletinsDTO to save.
     * @param paramBulletinsDTO the paramBulletinsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramBulletinsDTO,
     * or with status {@code 400 (Bad Request)} if the paramBulletinsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paramBulletinsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paramBulletinsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/param-bulletins/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ParamBulletinsDTO> partialUpdateParamBulletins(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ParamBulletinsDTO paramBulletinsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParamBulletins partially : {}, {}", id, paramBulletinsDTO);
        if (paramBulletinsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramBulletinsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramBulletinsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ParamBulletinsDTO> result = paramBulletinsService.partialUpdate(paramBulletinsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramBulletinsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /param-bulletins} : get all the paramBulletins.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramBulletins in body.
     */
    @GetMapping("/param-bulletins")
    public ResponseEntity<List<ParamBulletinsDTO>> getAllParamBulletins(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ParamBulletins");
        Page<ParamBulletinsDTO> page = paramBulletinsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /param-bulletins/:id} : get the "id" paramBulletins.
     *
     * @param id the id of the paramBulletinsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramBulletinsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-bulletins/{id}")
    public ResponseEntity<ParamBulletinsDTO> getParamBulletins(@PathVariable Long id) {
        log.debug("REST request to get ParamBulletins : {}", id);
        Optional<ParamBulletinsDTO> paramBulletinsDTO = paramBulletinsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paramBulletinsDTO);
    }

    /**
     * {@code DELETE  /param-bulletins/:id} : delete the "id" paramBulletins.
     *
     * @param id the id of the paramBulletinsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-bulletins/{id}")
    public ResponseEntity<Void> deleteParamBulletins(@PathVariable Long id) {
        log.debug("REST request to delete ParamBulletins : {}", id);
        paramBulletinsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
