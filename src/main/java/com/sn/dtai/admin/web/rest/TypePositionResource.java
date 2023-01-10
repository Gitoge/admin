package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.TypePositionRepository;
import com.sn.dtai.admin.service.TypePositionService;
import com.sn.dtai.admin.service.dto.TypePositionDTO;
import com.sn.dtai.admin.service.mapper.TypePositionMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import com.sn.dtai.admin.web.rest.errors.CodeException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
 * REST controller for managing {@link com.sn.dtai.admin.domain.TypePosition}.
 */
@RestController
@RequestMapping("/api")
public class TypePositionResource {

    private final Logger log = LoggerFactory.getLogger(TypePositionResource.class);

    private static final String ENTITY_NAME = "adminTypePosition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypePositionService typePositionService;

    private final TypePositionRepository typePositionRepository;

    private final TypePositionMapper typePositionMapper;

    public TypePositionResource(TypePositionService typePositionService, TypePositionRepository typePositionRepository, TypePositionMapper typePositionMapper) {
        this.typePositionService = typePositionService;
        this.typePositionRepository = typePositionRepository;
        this.typePositionMapper = typePositionMapper;
    }

    /**
     * {@code POST  /type-positions} : Create a new typePosition.
     *
     * @param typePositionDTO the typePositionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typePositionDTO, or with status {@code 400 (Bad Request)} if the typePosition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-positions")
    public ResponseEntity<TypePositionDTO> createTypePosition(@Valid @RequestBody TypePositionDTO typePositionDTO)
        throws URISyntaxException {
        log.debug("REST request to save TypePosition : {}", typePositionDTO);
        if (typePositionDTO.getId() != null) {
            throw new BadRequestAlertException("A new typePosition cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<TypePositionDTO> typePositionOP = typePositionRepository.findByCode(typePositionDTO.getCode()).map(typePositionMapper::toDto);

        if(typePositionOP.isPresent()){
            throw new CodeException();
        }
        
        TypePositionDTO result = typePositionService.save(typePositionDTO);
        return ResponseEntity
            .created(new URI("/api/type-positions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-positions/:id} : Updates an existing typePosition.
     *
     * @param id the id of the typePositionDTO to save.
     * @param typePositionDTO the typePositionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePositionDTO,
     * or with status {@code 400 (Bad Request)} if the typePositionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typePositionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-positions/{id}")
    public ResponseEntity<TypePositionDTO> updateTypePosition(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TypePositionDTO typePositionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TypePosition : {}, {}", id, typePositionDTO);
        if (typePositionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typePositionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typePositionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TypePositionDTO result = typePositionService.update(typePositionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePositionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /type-positions/:id} : Partial updates given fields of an existing typePosition, field will ignore if it is null
     *
     * @param id the id of the typePositionDTO to save.
     * @param typePositionDTO the typePositionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePositionDTO,
     * or with status {@code 400 (Bad Request)} if the typePositionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the typePositionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the typePositionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/type-positions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypePositionDTO> partialUpdateTypePosition(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TypePositionDTO typePositionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypePosition partially : {}, {}", id, typePositionDTO);
        if (typePositionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typePositionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typePositionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypePositionDTO> result = typePositionService.partialUpdate(typePositionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePositionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /type-positions} : get all the typePositions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typePositions in body.
     */
    @GetMapping("/type-positions")
    public ResponseEntity<List<TypePositionDTO>> getAllTypePositions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TypePositions");
        Page<TypePositionDTO> page = typePositionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-positions/:id} : get the "id" typePosition.
     *
     * @param id the id of the typePositionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typePositionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-positions/{id}")
    public ResponseEntity<TypePositionDTO> getTypePosition(@PathVariable Long id) {
        log.debug("REST request to get TypePosition : {}", id);
        Optional<TypePositionDTO> typePositionDTO = typePositionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typePositionDTO);
    }

    /**
     * {@code DELETE  /type-positions/:id} : delete the "id" typePosition.
     *
     * @param id the id of the typePositionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-positions/{id}")
    public ResponseEntity<Void> deleteTypePosition(@PathVariable Long id) {
        log.debug("REST request to delete TypePosition : {}", id);
        List<TypePositionDTO> typePositions= typePositionRepository.getPositionsByTypePosition(id).stream()
        .map(typePositionMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (typePositions.size()>0) {
            throw new BadRequestAlertException("Ce type de position est utilisé dans position.", ENTITY_NAME, "Ce type de position est utilisé dans position.");
        }
        typePositionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
