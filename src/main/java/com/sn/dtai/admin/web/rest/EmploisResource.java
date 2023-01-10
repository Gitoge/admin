package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Emplois;
import com.sn.dtai.admin.repository.EmploisRepository;
import com.sn.dtai.admin.service.EmploisService;
import com.sn.dtai.admin.service.dto.EmploisDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import com.sn.dtai.admin.service.impl.EmploisServiceImpl;
import com.sn.dtai.admin.service.mapper.EmploisMapper;
import com.sn.dtai.admin.service.mapper.PostesMapper;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Emplois}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EmploisResource {

    private final Logger log = LoggerFactory.getLogger(EmploisResource.class);

    private static final String ENTITY_NAME = "adminEmplois";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmploisService emploisService;
    private final EmploisServiceImpl emploisServiceImpl;
    private final EmploisRepository emploisRepository;
    
    private final EmploisMapper emploisMapper;
    private final PostesMapper postesMapper;

    public EmploisResource(EmploisService emploisService, EmploisRepository emploisRepository, EmploisServiceImpl emploisServiceImpl, EmploisMapper emploisMapper, PostesMapper postesMapper) {
        this.emploisService = emploisService;
        this.emploisRepository = emploisRepository;
        this.emploisServiceImpl = emploisServiceImpl;
        this.emploisMapper = emploisMapper;
        this.postesMapper = postesMapper;
    }

    /**
     * {@code POST  /emplois} : Create a new emplois.
     *
     * @param emplois the emplois to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new emplois, or with status {@code 400 (Bad Request)} if the
     *         emplois has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/emplois")
    public ResponseEntity<Emplois> createEmplois(@Valid @RequestBody Emplois emplois) throws URISyntaxException {
        log.debug("REST request to save Emplois : {}", emplois);
        if (emplois.getId() != null) {
            throw new BadRequestAlertException("A new emplois cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<EmploisDTO> emploisOP = emploisServiceImpl.findByCode(emplois.getCode());

        if(emploisOP.isPresent()){
            throw new CodeException();
        }
        Emplois result = emploisRepository.save(emplois);
        return ResponseEntity
            .created(new URI("/api/emplois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /emplois/:id} : Updates an existing emplois.
     *
     * @param id      the id of the emplois to save.
     * @param emplois the emplois to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated emplois,
     *         or with status {@code 400 (Bad Request)} if the emplois is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the emplois
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/emplois/{id}")
    public ResponseEntity<Emplois> updateEmplois(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Emplois emplois
    ) throws URISyntaxException {
        log.debug("REST request to update Emplois : {}, {}", id, emplois);
        if (emplois.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emplois.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emploisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Emplois result = emploisRepository.save(emplois);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emplois.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /emplois/:id} : Partial updates given fields of an existing
     * emplois, field will ignore if it is null
     *
     * @param id      the id of the emplois to save.
     * @param emplois the emplois to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated emplois,
     *         or with status {@code 400 (Bad Request)} if the emplois is not valid,
     *         or with status {@code 404 (Not Found)} if the emplois is not found,
     *         or with status {@code 500 (Internal Server Error)} if the emplois
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/emplois/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Emplois> partialUpdateEmplois(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Emplois emplois
    ) throws URISyntaxException {
        log.debug("REST request to partial update Emplois partially : {}, {}", id, emplois);
        if (emplois.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emplois.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emploisRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Emplois> result = emploisRepository
            .findById(emplois.getId())
            .map(existingEmplois -> {
                if (emplois.getCode() != null) {
                    existingEmplois.setCode(emplois.getCode());
                }
                if (emplois.getLibelle() != null) {
                    existingEmplois.setLibelle(emplois.getLibelle());
                }
                if (emplois.getDescription() != null) {
                    existingEmplois.setDescription(emplois.getDescription());
                }
                if (emplois.getTauxAt() != null) {
                    existingEmplois.setTauxAt(emplois.getTauxAt());
                }
                if (emplois.getPrimeLieEmploi() != null) {
                    existingEmplois.setPrimeLieEmploi(emplois.getPrimeLieEmploi());
                }

                return existingEmplois;
            })
            .map(emploisRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, emplois.getId().toString())
        );
    }

    /**
     * {@code GET  /emplois} : get all the emplois.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of emplois in body.
     */
    @GetMapping("/emplois")
    public ResponseEntity<List<EmploisDTO>> getAllEmplois(Pageable pageable) {
        log.debug("REST request to get a page of Emplois");
        Page<EmploisDTO> page = emploisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /emplois/:id} : get the "id" emplois.
     *
     * @param id the id of the emplois to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the emplois, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emplois/{id}")
    public ResponseEntity<Emplois> getEmplois(@PathVariable Long id) {
        log.debug("REST request to get Emplois : {}", id);
        Optional<Emplois> emplois = emploisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(emplois);
    }

    /**
     * {@code DELETE  /emplois/:id} : delete the "id" emplois.
     *
     * @param id the id of the emplois to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/emplois/{id}")
    public ResponseEntity<Void> deleteEmplois(@PathVariable Long id) {
        log.debug("REST request to delete Emplois : {}", id);
        emploisRepository.deleteById(id);

        List<PostesDTO> postesByEmplois= emploisRepository.getPostesByEmplois(id).stream()
        .map(postesMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (postesByEmplois.size()>0) {
            throw new BadRequestAlertException(" Cet Emploi est lié à des postes.", ENTITY_NAME, " Cet Emploi est lié à des postes.");
        }
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /emplois} : get all the emplois.
     *
     * sans pagination
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of emplois in body.
     */
    @GetMapping("/emplois/all")
    public List<Emplois> getAllEmplois() {
        return emploisRepository.findEmplois();
    }


    /**
     * {@code GET  /emplois/:id} : get the "id" emplois.
     *
     * @param id the id of the emplois to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the emplois, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/emploi/{id}")
    public ResponseEntity<EmploisDTO> getEmploi(@PathVariable Long id) {
        log.debug("REST request to get Emplois : {}", id);

        try {
            Optional<EmploisDTO> emplois = emploisServiceImpl.findOne(id);
            return ResponseUtil.wrapOrNotFound(emplois);
        } catch (Exception e) {
            throw new BadRequestAlertException("Emplois Introuvable", ENTITY_NAME, "Emplois Introuvable");
        }

    }
/**
 * {@code GET  /emplois} : get emploi by code mplois.
 *
 * sans pagination
 *
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
 *         of emplois in body.
 */
@GetMapping("/emplois-by-code")
public Optional<Emplois> getEmploisByCode(@RequestParam(value = "code") String code) {
    return emploisRepository.findEmploisByCode(code);
}

/**
 * {@code GET  /emplois/recherche} : get all the emplois.
 *
 * @param pageable the pagination information.
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
 *         of emplois in body.
 */
@GetMapping("/emplois/recherche")
public ResponseEntity<List<Emplois>> getEmploisRecherche(String motCle,Pageable pageable) {
    log.debug("REST request to get a page of Emplois");
    Page<Emplois> page = emploisRepository.rechercheEmplois(motCle,pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}

}
