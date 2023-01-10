package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.TypeDestinataires;
import com.sn.dtai.admin.repository.TypeDestinatairesRepository;
import com.sn.dtai.admin.service.dto.TypeDestinatairesDTO;
import com.sn.dtai.admin.service.mapper.TypeDestinatairesMapper;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.TypeDestinataires}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TypeDestinatairesResource {

    private final Logger log = LoggerFactory.getLogger(TypeDestinatairesResource.class);

    private static final String ENTITY_NAME = "adminTypeDestinataires";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeDestinatairesRepository typeDestinatairesRepository;

    private final TypeDestinatairesMapper typeDestinatairesMapper;

    public TypeDestinatairesResource(TypeDestinatairesRepository typeDestinatairesRepository, TypeDestinatairesMapper typeDestinatairesMapper) {
        this.typeDestinatairesRepository = typeDestinatairesRepository;
        this.typeDestinatairesMapper = typeDestinatairesMapper;
    }

    /**
     * {@code POST  /type-destinataires} : Create a new typeDestinataires.
     *
     * @param typeDestinataires the typeDestinataires to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeDestinataires, or with status {@code 400 (Bad Request)} if the typeDestinataires has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-destinataires")
    public ResponseEntity<TypeDestinataires> createTypeDestinataires(@Valid @RequestBody TypeDestinataires typeDestinataires)
        throws URISyntaxException {
        log.debug("REST request to save TypeDestinataires : {}", typeDestinataires);
        if (typeDestinataires.getId() != null) {
            throw new BadRequestAlertException("A new typeDestinataires cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<TypeDestinatairesDTO> destinatairesOP = typeDestinatairesRepository.findByCode(typeDestinataires.getCode()).map(typeDestinatairesMapper::toDto);;

        if(destinatairesOP.isPresent()){
            throw new CodeException();
        }

        TypeDestinataires result = typeDestinatairesRepository.save(typeDestinataires);
        return ResponseEntity
            .created(new URI("/api/type-destinataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-destinataires/:id} : Updates an existing typeDestinataires.
     *
     * @param id the id of the typeDestinataires to save.
     * @param typeDestinataires the typeDestinataires to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeDestinataires,
     * or with status {@code 400 (Bad Request)} if the typeDestinataires is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeDestinataires couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-destinataires/{id}")
    public ResponseEntity<TypeDestinataires> updateTypeDestinataires(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TypeDestinataires typeDestinataires
    ) throws URISyntaxException {
        log.debug("REST request to update TypeDestinataires : {}, {}", id, typeDestinataires);
        if (typeDestinataires.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeDestinataires.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeDestinatairesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TypeDestinataires result = typeDestinatairesRepository.save(typeDestinataires);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeDestinataires.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /type-destinataires/:id} : Partial updates given fields of an existing typeDestinataires, field will ignore if it is null
     *
     * @param id the id of the typeDestinataires to save.
     * @param typeDestinataires the typeDestinataires to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeDestinataires,
     * or with status {@code 400 (Bad Request)} if the typeDestinataires is not valid,
     * or with status {@code 404 (Not Found)} if the typeDestinataires is not found,
     * or with status {@code 500 (Internal Server Error)} if the typeDestinataires couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/type-destinataires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypeDestinataires> partialUpdateTypeDestinataires(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TypeDestinataires typeDestinataires
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypeDestinataires partially : {}, {}", id, typeDestinataires);
        if (typeDestinataires.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeDestinataires.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeDestinatairesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypeDestinataires> result = typeDestinatairesRepository
            .findById(typeDestinataires.getId())
            .map(existingTypeDestinataires -> {
                if (typeDestinataires.getCode() != null) {
                    existingTypeDestinataires.setCode(typeDestinataires.getCode());
                }
                if (typeDestinataires.getLibelle() != null) {
                    existingTypeDestinataires.setLibelle(typeDestinataires.getLibelle());
                }
                if (typeDestinataires.getDescription() != null) {
                    existingTypeDestinataires.setDescription(typeDestinataires.getDescription());
                }

                return existingTypeDestinataires;
            })
            .map(typeDestinatairesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeDestinataires.getId().toString())
        );
    }

    /**
     * {@code GET  /type-destinataires} : get all the typeDestinataires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeDestinataires in body.
     */
    @GetMapping("/type-destinataires")
    public ResponseEntity<List<TypeDestinataires>> getAllTypeDestinataires(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TypeDestinataires");
        Page<TypeDestinataires> page = typeDestinatairesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-destinataires/:id} : get the "id" typeDestinataires.
     *
     * @param id the id of the typeDestinataires to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeDestinataires, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-destinataires/{id}")
    public ResponseEntity<TypeDestinataires> getTypeDestinataires(@PathVariable Long id) {
        log.debug("REST request to get TypeDestinataires : {}", id);
        Optional<TypeDestinataires> typeDestinataires = typeDestinatairesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeDestinataires);
    }

    /**
     * {@code DELETE  /type-destinataires/:id} : delete the "id" typeDestinataires.
     *
     * @param id the id of the typeDestinataires to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-destinataires/{id}")
    public ResponseEntity<Void> deleteTypeDestinataires(@PathVariable Long id) {
        log.debug("REST request to delete TypeDestinataires : {}", id);

        List<TypeDestinatairesDTO> typePositions= typeDestinatairesRepository.getDestinatairesByTypeDestinataires(id).stream()
        .map(typeDestinatairesMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (typePositions.size()>0) {
            throw new BadRequestAlertException("Ce type de destinataire est utilisé dans Destinataire.", ENTITY_NAME, "Ce type de destinataire est utilisé dans Destinataire.");
        }

        typeDestinatairesRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
