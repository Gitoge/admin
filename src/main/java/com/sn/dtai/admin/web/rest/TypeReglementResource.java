package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.TypeReglement;
import com.sn.dtai.admin.repository.TypeReglementRepository;
import com.sn.dtai.admin.service.dto.TypeReglementDTO;
import com.sn.dtai.admin.service.mapper.TypeReglementMapper;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.TypeReglement}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TypeReglementResource {

    private final Logger log = LoggerFactory.getLogger(TypeReglementResource.class);

    private static final String ENTITY_NAME = "adminTypeReglement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeReglementRepository typeReglementRepository;

    private final TypeReglementMapper typeReglementMapper;

    public TypeReglementResource(TypeReglementRepository typeReglementRepository, TypeReglementMapper typeReglementMapper) {
        this.typeReglementRepository = typeReglementRepository;
        this.typeReglementMapper = typeReglementMapper;
    }

    /**
     * {@code POST  /type-reglements} : Create a new typeReglement.
     *
     * @param typeReglement the typeReglement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeReglement, or with status {@code 400 (Bad Request)} if the typeReglement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-reglements")
    public ResponseEntity<TypeReglement> createTypeReglement(@Valid @RequestBody TypeReglement typeReglement) throws URISyntaxException {
        log.debug("REST request to save TypeReglement : {}", typeReglement);
        if (typeReglement.getId() != null) {
            throw new BadRequestAlertException("A new typeReglement cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<TypeReglementDTO> destinatairesOP = typeReglementRepository.findByCode(typeReglement.getCode()).map(typeReglementMapper::toDto);;

        if(destinatairesOP.isPresent()){
            throw new CodeException();
        }

        TypeReglement result = typeReglementRepository.save(typeReglement);
        return ResponseEntity
            .created(new URI("/api/type-reglements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-reglements/:id} : Updates an existing typeReglement.
     *
     * @param id the id of the typeReglement to save.
     * @param typeReglement the typeReglement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeReglement,
     * or with status {@code 400 (Bad Request)} if the typeReglement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeReglement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-reglements/{id}")
    public ResponseEntity<TypeReglement> updateTypeReglement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TypeReglement typeReglement
    ) throws URISyntaxException {
        log.debug("REST request to update TypeReglement : {}, {}", id, typeReglement);
        if (typeReglement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeReglement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeReglementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TypeReglement result = typeReglementRepository.save(typeReglement);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeReglement.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /type-reglements/:id} : Partial updates given fields of an existing typeReglement, field will ignore if it is null
     *
     * @param id the id of the typeReglement to save.
     * @param typeReglement the typeReglement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeReglement,
     * or with status {@code 400 (Bad Request)} if the typeReglement is not valid,
     * or with status {@code 404 (Not Found)} if the typeReglement is not found,
     * or with status {@code 500 (Internal Server Error)} if the typeReglement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/type-reglements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypeReglement> partialUpdateTypeReglement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TypeReglement typeReglement
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypeReglement partially : {}, {}", id, typeReglement);
        if (typeReglement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeReglement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeReglementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypeReglement> result = typeReglementRepository
            .findById(typeReglement.getId())
            .map(existingTypeReglement -> {
                if (typeReglement.getCode() != null) {
                    existingTypeReglement.setCode(typeReglement.getCode());
                }
                if (typeReglement.getLibelle() != null) {
                    existingTypeReglement.setLibelle(typeReglement.getLibelle());
                }
                if (typeReglement.getDescription() != null) {
                    existingTypeReglement.setDescription(typeReglement.getDescription());
                }

                return existingTypeReglement;
            })
            .map(typeReglementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeReglement.getId().toString())
        );
    }

    /**
     * {@code GET  /type-reglements} : get all the typeReglements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeReglements in body.
     */
    @GetMapping("/type-reglements")
    public ResponseEntity<List<TypeReglement>> getAllTypeReglements(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TypeReglements");
        Page<TypeReglement> page = typeReglementRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-reglements/:id} : get the "id" typeReglement.
     *
     * @param id the id of the typeReglement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeReglement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-reglements/{id}")
    public ResponseEntity<TypeReglement> getTypeReglement(@PathVariable Long id) {
        log.debug("REST request to get TypeReglement : {}", id);
        Optional<TypeReglement> typeReglement = typeReglementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeReglement);
    }

    /**
     * {@code DELETE  /type-reglements/:id} : delete the "id" typeReglement.
     *
     * @param id the id of the typeReglement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-reglements/{id}")
    public ResponseEntity<Void> deleteTypeReglement(@PathVariable Long id) {
        log.debug("REST request to delete TypeReglement : {}", id);

        List<TypeReglementDTO> typeReglement= typeReglementRepository.getReglementsByTypeReglement(id).stream()
        .map(typeReglementMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (typeReglement.size()>0) {
            throw new BadRequestAlertException("Ce type de Rglement est utilisé dans Règlement.", ENTITY_NAME, "Ce type de Rglement est utilisé dans Règlement.");
        }
        typeReglementRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
