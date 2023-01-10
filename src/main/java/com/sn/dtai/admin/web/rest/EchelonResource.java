package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Echelon;
import com.sn.dtai.admin.repository.EchelonRepository;
import com.sn.dtai.admin.service.dto.EchelonDTO;
import com.sn.dtai.admin.service.impl.EchelonServiceImpl;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Echelon}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EchelonResource {

    private final Logger log = LoggerFactory.getLogger(EchelonResource.class);

    private static final String ENTITY_NAME = "adminEchelon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EchelonRepository echelonRepository;
    
    private final EchelonServiceImpl echelonServiceImpl;

    public EchelonResource(EchelonRepository echelonRepository, EchelonServiceImpl echelonServiceImpl) {
        this.echelonRepository = echelonRepository;
        this.echelonServiceImpl = echelonServiceImpl;
    }

    /**
     * {@code POST  /echelons} : Create a new echelon.
     *
     * @param echelon the echelon to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new echelon, or with status {@code 400 (Bad Request)} if the echelon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/echelons")
    public ResponseEntity<Echelon> createEchelon(@Valid @RequestBody Echelon echelon) throws URISyntaxException {
        log.debug("REST request to save Echelon : {}", echelon);
        if (echelon.getId() != null) {
            throw new BadRequestAlertException("A new echelon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Echelon result = echelonRepository.save(echelon);
        return ResponseEntity
            .created(new URI("/api/echelons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /echelons/:id} : Updates an existing echelon.
     *
     * @param id the id of the echelon to save.
     * @param echelon the echelon to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated echelon,
     * or with status {@code 400 (Bad Request)} if the echelon is not valid,
     * or with status {@code 500 (Internal Server Error)} if the echelon couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/echelons/{id}")
    public ResponseEntity<Echelon> updateEchelon(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Echelon echelon
    ) throws URISyntaxException {
        log.debug("REST request to update Echelon : {}, {}", id, echelon);
        if (echelon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, echelon.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!echelonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Echelon result = echelonRepository.save(echelon);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, echelon.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /echelons/:id} : Partial updates given fields of an existing echelon, field will ignore if it is null
     *
     * @param id the id of the echelon to save.
     * @param echelon the echelon to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated echelon,
     * or with status {@code 400 (Bad Request)} if the echelon is not valid,
     * or with status {@code 404 (Not Found)} if the echelon is not found,
     * or with status {@code 500 (Internal Server Error)} if the echelon couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/echelons/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Echelon> partialUpdateEchelon(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Echelon echelon
    ) throws URISyntaxException {
        log.debug("REST request to partial update Echelon partially : {}, {}", id, echelon);
        if (echelon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, echelon.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!echelonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Echelon> result = echelonRepository
            .findById(echelon.getId())
            .map(existingEchelon -> {
                if (echelon.getCode() != null) {
                    existingEchelon.setCode(echelon.getCode());
                }
                if (echelon.getLibelle() != null) {
                    existingEchelon.setLibelle(echelon.getLibelle());
                }
                if (echelon.getDescription() != null) {
                    existingEchelon.setDescription(echelon.getDescription());
                }

                return existingEchelon;
            })
            .map(echelonRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, echelon.getId().toString())
        );
    }

    /**
     * {@code GET  /echelons} : get all the echelons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of echelons in body.
     */
    @GetMapping("/echelons")
    public ResponseEntity<List<Echelon>> getAllEchelons(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Echelons");
        Page<Echelon> page = echelonRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /echelons/:id} : get the "id" echelon.
     *
     * @param id the id of the echelon to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the echelon, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/echelons/{id}")
    public ResponseEntity<Echelon> getEchelon(@PathVariable Long id) {
        log.debug("REST request to get Echelon : {}", id);
        Optional<Echelon> echelon = echelonRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(echelon);
    }

    /**
     * {@code DELETE  /echelons/:id} : delete the "id" echelon.
     *
     * @param id the id of the echelon to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/echelons/{id}")
    public ResponseEntity<Void> deleteEchelon(@PathVariable Long id) {
        log.debug("REST request to delete Echelon : {}", id);
        echelonRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

     /**
     * {@code GET  /echelons} : get all the echelons.
     *
     * sans pagination
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of echelons in body.
     */
    @GetMapping("/echelons/all")
    public List<Echelon> getAllEchelons() {
        return echelonRepository.findAllEchelon();
    }



/**
 * {@code GET  /echelonsById} : get the "id" echelon.
 *
 * @param id the id of the echelon to retrieve.
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the echelon, or with status {@code 404 (Not Found)}.
 */
@GetMapping("/echelonsById")
public ResponseEntity<Echelon> getEchelonById(@RequestParam(value="echelonId") Long echelonId) {
    log.debug("REST request to get Echelon : {}", echelonId);
    Optional<Echelon> echelon = echelonRepository.findEchelonById(echelonId);
    return ResponseUtil.wrapOrNotFound(echelon);
}

/**
 * {@code GET  /emplois} : get emploi by code mplois.
 *
 * sans pagination
 *
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
 *         of emplois in body.
 */
@GetMapping("/echelons/code-echelon")
public Optional<EchelonDTO> getEchelonByCode(@RequestParam(value = "code") String code) {
    return echelonServiceImpl.findByCode(code);
}
}
