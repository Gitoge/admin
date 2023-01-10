package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Destinataires;
import com.sn.dtai.admin.repository.DestinatairesRepository;
import com.sn.dtai.admin.service.dto.DestinatairesDTO;
import com.sn.dtai.admin.service.mapper.DestinatairesMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import com.sn.dtai.admin.web.rest.errors.CodeException;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Destinataires}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DestinatairesResource {

    private final Logger log = LoggerFactory.getLogger(DestinatairesResource.class);

    private static final String ENTITY_NAME = "adminDestinataires";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DestinatairesRepository destinatairesRepository;
    
    private final DestinatairesMapper destinatairesMapper;

    public DestinatairesResource(DestinatairesRepository destinatairesRepository, DestinatairesMapper destinatairesMapper) {
        this.destinatairesRepository = destinatairesRepository;
        this.destinatairesMapper = destinatairesMapper;
    }

    /**
     * {@code POST  /destinataires} : Create a new destinataires.
     *
     * @param destinataires the destinataires to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new destinataires, or with status {@code 400 (Bad Request)} if the destinataires has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/destinataires")
    public ResponseEntity<Destinataires> createDestinataires(@Valid @RequestBody Destinataires destinataires) throws URISyntaxException {
        log.debug("REST request to save Destinataires : {}", destinataires);
        if (destinataires.getId() != null) {
            throw new BadRequestAlertException("A new destinataires cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<DestinatairesDTO> destinatairesOP = destinatairesRepository.findByCode(destinataires.getCode()).map(destinatairesMapper::toDto);;

        if(destinatairesOP.isPresent()){
            throw new CodeException();
        }

        
        Destinataires result = destinatairesRepository.save(destinataires);
        return ResponseEntity
            .created(new URI("/api/destinataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /destinataires/:id} : Updates an existing destinataires.
     *
     * @param id the id of the destinataires to save.
     * @param destinataires the destinataires to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated destinataires,
     * or with status {@code 400 (Bad Request)} if the destinataires is not valid,
     * or with status {@code 500 (Internal Server Error)} if the destinataires couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/destinataires/{id}")
    public ResponseEntity<Destinataires> updateDestinataires(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Destinataires destinataires
    ) throws URISyntaxException {
        log.debug("REST request to update Destinataires : {}, {}", id, destinataires);
        if (destinataires.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, destinataires.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!destinatairesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Destinataires result = destinatairesRepository.save(destinataires);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, destinataires.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /destinataires/:id} : Partial updates given fields of an existing destinataires, field will ignore if it is null
     *
     * @param id the id of the destinataires to save.
     * @param destinataires the destinataires to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated destinataires,
     * or with status {@code 400 (Bad Request)} if the destinataires is not valid,
     * or with status {@code 404 (Not Found)} if the destinataires is not found,
     * or with status {@code 500 (Internal Server Error)} if the destinataires couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/destinataires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Destinataires> partialUpdateDestinataires(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Destinataires destinataires
    ) throws URISyntaxException {
        log.debug("REST request to partial update Destinataires partially : {}, {}", id, destinataires);
        if (destinataires.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, destinataires.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!destinatairesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Destinataires> result = destinatairesRepository
            .findById(destinataires.getId())
            .map(existingDestinataires -> {
                if (destinataires.getCode() != null) {
                    existingDestinataires.setCode(destinataires.getCode());
                }
                if (destinataires.getLibelle() != null) {
                    existingDestinataires.setLibelle(destinataires.getLibelle());
                }
                if (destinataires.getPrenom() != null) {
                    existingDestinataires.setPrenom(destinataires.getPrenom());
                }
                if (destinataires.getNom() != null) {
                    existingDestinataires.setNom(destinataires.getNom());
                }
                if (destinataires.getAdresse() != null) {
                    existingDestinataires.setAdresse(destinataires.getAdresse());
                }
                if (destinataires.getComptebancaire() != null) {
                    existingDestinataires.setComptebancaire(destinataires.getComptebancaire());
                }

                return existingDestinataires;
            })
            .map(destinatairesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, destinataires.getId().toString())
        );
    }

    /**
     * {@code GET  /destinataires} : get all the destinataires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of destinataires in body.
     */
    @GetMapping("/destinataires")
    public ResponseEntity<List<Destinataires>> getAllDestinataires(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Destinataires");
        Page<Destinataires> page = destinatairesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /destinataires/:id} : get the "id" destinataires.
     *
     * @param id the id of the destinataires to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the destinataires, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/destinataires/{id}")
    public ResponseEntity<Destinataires> getDestinataires(@PathVariable Long id) {
        log.debug("REST request to get Destinataires : {}", id);
        Optional<Destinataires> destinataires = destinatairesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(destinataires);
    }

    /**
     * {@code DELETE  /destinataires/:id} : delete the "id" destinataires.
     *
     * @param id the id of the destinataires to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/destinataires/{id}")
    public ResponseEntity<Void> deleteDestinataires(@PathVariable Long id) {
        log.debug("REST request to delete Destinataires : {}", id);
        destinatairesRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
