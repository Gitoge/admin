package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.PageActionRepository;
import com.sn.dtai.admin.service.PageActionService;
import com.sn.dtai.admin.service.dto.PageActionDTO;
import com.sn.dtai.admin.service.dto.PagesActionsDto;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.PageAction}.
 */
@RestController
@RequestMapping("/api")
public class PageActionResource {

    private final Logger log = LoggerFactory.getLogger(PageActionResource.class);

    private static final String ENTITY_NAME = "adminPageAction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PageActionService pageActionService;

    private final PageActionRepository pageActionRepository;

    public PageActionResource(PageActionService pageActionService, PageActionRepository pageActionRepository) {
        this.pageActionService = pageActionService;
        this.pageActionRepository = pageActionRepository;
    }

    /**
     * {@code POST  /page-actions} : Create a new pageAction.
     *
     * @param pageActionDTO the pageActionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pageActionDTO, or with status {@code 400 (Bad Request)} if the pageAction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/page-actions")
    public ResponseEntity<PageActionDTO> createPageAction(@RequestBody PageActionDTO pageActionDTO) throws URISyntaxException {
        log.debug("REST request to save PageAction : {}", pageActionDTO);
        if (pageActionDTO.getId() != null) {
            throw new BadRequestAlertException("A new pageAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PageActionDTO result = pageActionService.save(pageActionDTO);
        return ResponseEntity
            .created(new URI("/api/page-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /page-actions/:id} : Updates an existing pageAction.
     *
     * @param id the id of the pageActionDTO to save.
     * @param pageActionDTO the pageActionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pageActionDTO,
     * or with status {@code 400 (Bad Request)} if the pageActionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pageActionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/page-actions/{id}")
    public ResponseEntity<PageActionDTO> updatePageAction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PageActionDTO pageActionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PageAction : {}, {}", id, pageActionDTO);
        if (pageActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pageActionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pageActionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PageActionDTO result = pageActionService.update(pageActionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pageActionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /page-actions/:id} : Partial updates given fields of an existing pageAction, field will ignore if it is null
     *
     * @param id the id of the pageActionDTO to save.
     * @param pageActionDTO the pageActionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pageActionDTO,
     * or with status {@code 400 (Bad Request)} if the pageActionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pageActionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pageActionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/page-actions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PageActionDTO> partialUpdatePageAction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PageActionDTO pageActionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PageAction partially : {}, {}", id, pageActionDTO);
        if (pageActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pageActionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pageActionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PageActionDTO> result = pageActionService.partialUpdate(pageActionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pageActionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /page-actions} : get all the pageActions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pageActions in body.
     */
    @GetMapping("/page-actions")
    public ResponseEntity<List<PageActionDTO>> getAllPageActions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PageActions");
        Page<PageActionDTO> page = pageActionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /page-actions/all} : get all the pageActions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pageActions in body.
     */
    @GetMapping("/page-actions/all")
    public ResponseEntity<List<PagesActionsDto>> getAllPagesActions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PageActions");
        // return pageActionService.findAllPageAction();
        Page<PagesActionsDto> page = pageActionService.findAllPageAction(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /page-actions/:id} : get the "id" pageAction.
     *
     * @param id the id of the pageActionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pageActionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/page-actions/{id}")
    public ResponseEntity<PageActionDTO> getPageAction(@PathVariable Long id) {
        log.debug("REST request to get PageAction : {}", id);
        Optional<PageActionDTO> pageActionDTO = pageActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pageActionDTO);
    }

    /**
     * {@code DELETE  /page-actions/:id} : delete the "id" pageAction.
     *
     * @param id the id of the pageActionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/page-actions/{id}")
    public ResponseEntity<Void> deletePageAction(@PathVariable Long id) {
        log.debug("REST request to delete PageAction : {}", id);
        pageActionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
