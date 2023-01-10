package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.EtablissementBancaire;
import com.sn.dtai.admin.repository.EtablissementBancaireRepository;
import com.sn.dtai.admin.service.EtablissementBancaireService;
import com.sn.dtai.admin.service.dto.EtablissementBancaireDTO;
import com.sn.dtai.admin.service.mapper.EtablissementBancaireMapper;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.EtablissementBancaire}.
 */
@RestController
@RequestMapping("/api")
public class EtablissementBancaireResource {

    private final Logger log = LoggerFactory.getLogger(EtablissementBancaireResource.class);

    private static final String ENTITY_NAME = "carriereEtablissementBancaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtablissementBancaireService etablissementBancaireService;

    private final EtablissementBancaireRepository etablissementBancaireRepository;
    
    private final EtablissementBancaireMapper etablissementBancaireMapper;

    public EtablissementBancaireResource(
        EtablissementBancaireService etablissementBancaireService,
        EtablissementBancaireRepository etablissementBancaireRepository,
        EtablissementBancaireMapper etablissementBancaireMapper
    ) {
        this.etablissementBancaireService = etablissementBancaireService;
        this.etablissementBancaireRepository = etablissementBancaireRepository;
        this.etablissementBancaireMapper =  etablissementBancaireMapper;
    }

    /**
     * {@code POST  /etablissement-bancaires} : Create a new etablissementBancaire.
     *
     * @param etablissementBancaireDTO the etablissementBancaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etablissementBancaireDTO, or with status {@code 400 (Bad Request)} if the etablissementBancaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etablissement-bancaires")
    public ResponseEntity<EtablissementBancaireDTO> createEtablissementBancaire(
        @Valid @RequestBody EtablissementBancaireDTO etablissementBancaireDTO
    ) throws URISyntaxException {
        log.debug("REST request to save EtablissementBancaire : {}", etablissementBancaireDTO);
        if (etablissementBancaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new etablissementBancaire cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<EtablissementBancaireDTO> etablissementBancaireOP = etablissementBancaireService.findByCode(etablissementBancaireDTO.getCode());

        if(etablissementBancaireOP.isPresent()){
            throw new CodeException();
        }


        EtablissementBancaireDTO result = etablissementBancaireService.save(etablissementBancaireDTO);
        return ResponseEntity
            .created(new URI("/api/etablissement-bancaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etablissement-bancaires/:id} : Updates an existing etablissementBancaire.
     *
     * @param id the id of the etablissementBancaireDTO to save.
     * @param etablissementBancaireDTO the etablissementBancaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etablissementBancaireDTO,
     * or with status {@code 400 (Bad Request)} if the etablissementBancaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etablissementBancaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etablissement-bancaires/{id}")
    public ResponseEntity<EtablissementBancaireDTO> updateEtablissementBancaire(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EtablissementBancaireDTO etablissementBancaireDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EtablissementBancaire : {}, {}", id, etablissementBancaireDTO);
        if (etablissementBancaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, etablissementBancaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!etablissementBancaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EtablissementBancaireDTO result = etablissementBancaireService.save(etablissementBancaireDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etablissementBancaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /etablissement-bancaires/:id} : Partial updates given fields of an existing etablissementBancaire, field will ignore if it is null
     *
     * @param id the id of the etablissementBancaireDTO to save.
     * @param etablissementBancaireDTO the etablissementBancaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etablissementBancaireDTO,
     * or with status {@code 400 (Bad Request)} if the etablissementBancaireDTO is not valid,
     * or with status {@code 404 (Not Found)} if the etablissementBancaireDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the etablissementBancaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/etablissement-bancaires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EtablissementBancaireDTO> partialUpdateEtablissementBancaire(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EtablissementBancaireDTO etablissementBancaireDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EtablissementBancaire partially : {}, {}", id, etablissementBancaireDTO);
        if (etablissementBancaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, etablissementBancaireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!etablissementBancaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EtablissementBancaireDTO> result = etablissementBancaireService.partialUpdate(etablissementBancaireDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etablissementBancaireDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /etablissement-bancaires} : get all the etablissementBancaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etablissementBancaires in body.
     */
    @GetMapping("/etablissement-bancaires")
    public ResponseEntity<List<EtablissementBancaireDTO>> getAllEtablissementBancaires(Pageable pageable) {
        log.debug("REST request to get a page of EtablissementBancaires");
        Page<EtablissementBancaireDTO> page = etablissementBancaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etablissement-bancaires/:id} : get the "id" etablissementBancaire.
     *
     * @param id the id of the etablissementBancaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etablissementBancaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etablissement-bancaires/{id}")
    public ResponseEntity<EtablissementBancaireDTO> getEtablissementBancaire(@PathVariable Long id) {
        log.debug("REST request to get EtablissementBancaire : {}", id);
        Optional<EtablissementBancaireDTO> etablissementBancaireDTO = etablissementBancaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etablissementBancaireDTO);
    }

    /**
     * {@code DELETE  /etablissement-bancaires/:id} : delete the "id" etablissementBancaire.
     *
     * @param id the id of the etablissementBancaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etablissement-bancaires/{id}")
    public ResponseEntity<Void> deleteEtablissementBancaire(@PathVariable Long id) {
        log.debug("REST request to delete EtablissementBancaire : {}", id);
        List<EtablissementBancaireDTO> etablissements= etablissementBancaireRepository.getEtablissementByAgence(id).stream()
        .map(etablissementBancaireMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (etablissements.size()>0) {
            throw new BadRequestAlertException("Cet Etablissement Bancaire est déjà utiliséE dans une agence.", ENTITY_NAME, "Cet Etablissement Bancaire est déjà utiliséE dans une agence.");
        }
        etablissementBancaireService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

        /**
     * {@code GET  /etablissement-bancaires} : get all the etablissement-bancaires.
     *
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of etablissement-bancaires in body.
     */
    @GetMapping("/etablissement-bancaires/all")
    public List<EtablissementBancaire> getAllEtablissementBancaires() {
        return etablissementBancaireRepository.findAllEtablissementBancaires();
    }
}
