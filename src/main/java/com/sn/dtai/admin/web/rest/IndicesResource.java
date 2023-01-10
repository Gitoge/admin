package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Indices;
import com.sn.dtai.admin.repository.IndicesRepository;
import com.sn.dtai.admin.service.dto.IndicesDTO;
import com.sn.dtai.admin.service.impl.IndicesServiceImpl;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Indices}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class IndicesResource {

    private final Logger log = LoggerFactory.getLogger(IndicesResource.class);

    private static final String ENTITY_NAME = "adminIndices";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IndicesRepository indicesRepository;
   
    private final IndicesServiceImpl indicesServiceImpl;

    public IndicesResource(IndicesRepository indicesRepository, IndicesServiceImpl indicesServiceImpl) {
        this.indicesRepository = indicesRepository;
        this.indicesServiceImpl =  indicesServiceImpl;
    }

    /**
     * {@code POST  /indices} : Create a new indices.
     *
     * @param indices the indices to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new indices, or with status {@code 400 (Bad Request)} if the indices has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/indices")
    public ResponseEntity<Indices> createIndices(@Valid @RequestBody Indices indices) throws URISyntaxException {
        log.debug("REST request to save Indices : {}", indices);
        if (indices.getId() != null) {
            throw new BadRequestAlertException("A new indices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Indices result = indicesRepository.save(indices);
        return ResponseEntity
            .created(new URI("/api/indices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /indices/:id} : Updates an existing indices.
     *
     * @param id the id of the indices to save.
     * @param indices the indices to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated indices,
     * or with status {@code 400 (Bad Request)} if the indices is not valid,
     * or with status {@code 500 (Internal Server Error)} if the indices couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/indices/{id}")
    public ResponseEntity<Indices> updateIndices(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Indices indices
    ) throws URISyntaxException {
        log.debug("REST request to update Indices : {}, {}", id, indices);
        if (indices.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, indices.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!indicesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Indices result = indicesRepository.save(indices);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, indices.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /indices/:id} : Partial updates given fields of an existing indices, field will ignore if it is null
     *
     * @param id the id of the indices to save.
     * @param indices the indices to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated indices,
     * or with status {@code 400 (Bad Request)} if the indices is not valid,
     * or with status {@code 404 (Not Found)} if the indices is not found,
     * or with status {@code 500 (Internal Server Error)} if the indices couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/indices/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Indices> partialUpdateIndices(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Indices indices
    ) throws URISyntaxException {
        log.debug("REST request to partial update Indices partially : {}, {}", id, indices);
        if (indices.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, indices.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!indicesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Indices> result = indicesRepository
            .findById(indices.getId())
            .map(existingIndices -> {
                if (indices.getCode() != null) {
                    existingIndices.setCode(indices.getCode());
                }
                if (indices.getDescription() != null) {
                    existingIndices.setDescription(indices.getDescription());
                }
                if (indices.getMntSFTenf01() != null) {
                    existingIndices.setMntSFTenf01(indices.getMntSFTenf01());
                }
                if (indices.getMntSFTenf02() != null) {
                    existingIndices.setMntSFTenf02(indices.getMntSFTenf02());
                }
                if (indices.getSoldeIndiciaire() != null) {
                    existingIndices.setSoldeIndiciaire(indices.getSoldeIndiciaire());
                }

                return existingIndices;
            })
            .map(indicesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, indices.getId().toString())
        );
    }

    /**
     * {@code GET  /indices} : get all the indices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of indices in body.
     */
    @GetMapping("/indices")
    public ResponseEntity<List<Indices>> getAllIndices(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Indices");
        Page<Indices> page = indicesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /indices/:id} : get the "id" indices.
     *
     * @param id the id of the indices to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the indices, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/indices/{id}")
    public ResponseEntity<Indices> getIndices(@PathVariable Long id) {
        log.debug("REST request to get Indices : {}", id);
        Optional<Indices> indices = indicesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(indices);
    }

    /**
     * {@code DELETE  /indices/:id} : delete the "id" indices.
     *
     * @param id the id of the indices to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/indices/{id}")
    public ResponseEntity<Void> deleteIndices(@PathVariable Long id) {
        log.debug("REST request to delete Indices : {}", id);
        indicesRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

}
