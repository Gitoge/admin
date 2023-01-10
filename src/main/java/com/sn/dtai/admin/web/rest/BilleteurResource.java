package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Billeteur;
import com.sn.dtai.admin.repository.BilleteurRepository;
import com.sn.dtai.admin.service.BilleteurService;
import com.sn.dtai.admin.service.dto.BilleteurDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Billeteur}.
 */
@RestController
@RequestMapping("/api")
public class BilleteurResource {

    private final Logger log = LoggerFactory.getLogger(BilleteurResource.class);

    private static final String ENTITY_NAME = "carriereBilleteur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BilleteurService billeteurService;

    private final BilleteurRepository billeteurRepository;

    public BilleteurResource(BilleteurService billeteurService, BilleteurRepository billeteurRepository) {
        this.billeteurService = billeteurService;
        this.billeteurRepository = billeteurRepository;
    }

    /**
     * {@code POST  /billeteurs} : Create a new billeteur.
     *
     * @param billeteurDTO the billeteurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billeteurDTO, or with status {@code 400 (Bad Request)} if the billeteur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billeteurs")
    public ResponseEntity<BilleteurDTO> createBilleteur(@Valid @RequestBody BilleteurDTO billeteurDTO) throws URISyntaxException {
        log.debug("REST request to save Billeteur : {}", billeteurDTO);
        if (billeteurDTO.getId() != null) {
            throw new BadRequestAlertException("A new billeteur cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<BilleteurDTO> billeteurOP = billeteurService.findByCode(billeteurDTO.getCode());

        if(billeteurOP.isPresent()){
            throw new CodeException();
        }


        BilleteurDTO result = billeteurService.save(billeteurDTO);
        return ResponseEntity
            .created(new URI("/api/billeteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /billeteurs/:id} : Updates an existing billeteur.
     *
     * @param id the id of the billeteurDTO to save.
     * @param billeteurDTO the billeteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billeteurDTO,
     * or with status {@code 400 (Bad Request)} if the billeteurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billeteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billeteurs/{id}")
    public ResponseEntity<BilleteurDTO> updateBilleteur(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BilleteurDTO billeteurDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Billeteur : {}, {}", id, billeteurDTO);
        if (billeteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billeteurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billeteurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BilleteurDTO result = billeteurService.save(billeteurDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billeteurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /billeteurs/:id} : Partial updates given fields of an existing billeteur, field will ignore if it is null
     *
     * @param id the id of the billeteurDTO to save.
     * @param billeteurDTO the billeteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billeteurDTO,
     * or with status {@code 400 (Bad Request)} if the billeteurDTO is not valid,
     * or with status {@code 404 (Not Found)} if the billeteurDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the billeteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/billeteurs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BilleteurDTO> partialUpdateBilleteur(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BilleteurDTO billeteurDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Billeteur partially : {}, {}", id, billeteurDTO);
        if (billeteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billeteurDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billeteurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BilleteurDTO> result = billeteurService.partialUpdate(billeteurDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billeteurDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /billeteurs} : get all the billeteurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billeteurs in body.
     */
    @GetMapping("/billeteurs")
    public ResponseEntity<List<BilleteurDTO>> getAllBilleteurs(Pageable pageable) {
        log.debug("REST request to get a page of Billeteurs");
        Page<BilleteurDTO> page = billeteurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billeteurs/:id} : get the "id" billeteur.
     *
     * @param id the id of the billeteurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billeteurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billeteurs/{id}")
    public ResponseEntity<BilleteurDTO> getBilleteur(@PathVariable Long id) {
        log.debug("REST request to get Billeteur : {}", id);
        try{
        Optional<BilleteurDTO> billeteurDTO = billeteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billeteurDTO);
         }
        catch(Exception e){
            throw new BadRequestAlertException("Billeteur Introuvable", ENTITY_NAME,"Billeteur Introuvable");
        }
    }

    /**
     * {@code DELETE  /billeteurs/:id} : delete the "id" billeteur.
     *
     * @param id the id of the billeteurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billeteurs/{id}")
    public ResponseEntity<Void> deleteBilleteur(@PathVariable Long id) {
        log.debug("REST request to delete Billeteur : {}", id);
        billeteurService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    
    /**
     * {@code GET  /billeteurs} : get all the billeteurs.
     *
     * sans pagination
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of billeteurs in body.
     */
    @GetMapping("/billeteurs/all")
    public List<Billeteur> getAllBilleteurs() {
        return billeteurRepository.findAllBilleteurs();
    }
}
