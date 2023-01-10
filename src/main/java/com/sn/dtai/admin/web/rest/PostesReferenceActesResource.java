package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.PostesReferenceActesRepository;
import com.sn.dtai.admin.service.PostesReferenceActesService;
import com.sn.dtai.admin.service.dto.PostesReferenceActesDTO;
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
 * REST controller for managing {@link com.sn.dtai.paie.domain.PostesReferenceActes}.
 */
@RestController
@RequestMapping("/api")
public class PostesReferenceActesResource {

    private final Logger log = LoggerFactory.getLogger(PostesReferenceActesResource.class);

    private static final String ENTITY_NAME = "paiePostesReferenceActes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PostesReferenceActesService postesReferenceActesService;

    private final PostesReferenceActesRepository postesReferenceActesRepository;

    public PostesReferenceActesResource(
        PostesReferenceActesService postesReferenceActesService,
        PostesReferenceActesRepository postesReferenceActesRepository
    ) {
        this.postesReferenceActesService = postesReferenceActesService;
        this.postesReferenceActesRepository = postesReferenceActesRepository;
    }

    /**
     * {@code POST  /postes-reference-actes} : Create a new postesReferenceActes.
     *
     * @param postesReferenceActesDTO the postesReferenceActesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postesReferenceActesDTO, or with status {@code 400 (Bad Request)} if the postesReferenceActes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/postes-reference-actes")
    public ResponseEntity<PostesReferenceActesDTO> createPostesReferenceActes(@RequestBody PostesReferenceActesDTO postesReferenceActesDTO)
        throws URISyntaxException {
        log.debug("REST request to save PostesReferenceActes : {}", postesReferenceActesDTO);
        if (postesReferenceActesDTO.getId() != null) {
            throw new BadRequestAlertException("A new postesReferenceActes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional postesId = postesReferenceActesService.findByPostesId(postesReferenceActesDTO.getPostes().getId());

        if (postesId.isPresent()) {
            throw new BadRequestAlertException("Ce poste existe déja", ENTITY_NAME, "Ce poste existe déja");
        }

        PostesReferenceActesDTO result = postesReferenceActesService.save(postesReferenceActesDTO);
        return ResponseEntity
            .created(new URI("/api/postes-reference-actes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /postes-reference-actes/:id} : Updates an existing postesReferenceActes.
     *
     * @param id the id of the postesReferenceActesDTO to save.
     * @param postesReferenceActesDTO the postesReferenceActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postesReferenceActesDTO,
     * or with status {@code 400 (Bad Request)} if the postesReferenceActesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the postesReferenceActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/postes-reference-actes/{id}")
    public ResponseEntity<PostesReferenceActesDTO> updatePostesReferenceActes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PostesReferenceActesDTO postesReferenceActesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PostesReferenceActes : {}, {}", id, postesReferenceActesDTO);
        if (postesReferenceActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postesReferenceActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postesReferenceActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PostesReferenceActesDTO result = postesReferenceActesService.save(postesReferenceActesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postesReferenceActesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /postes-reference-actes/:id} : Partial updates given fields of an existing postesReferenceActes, field will ignore if it is null
     *
     * @param id the id of the postesReferenceActesDTO to save.
     * @param postesReferenceActesDTO the postesReferenceActesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postesReferenceActesDTO,
     * or with status {@code 400 (Bad Request)} if the postesReferenceActesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the postesReferenceActesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the postesReferenceActesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/postes-reference-actes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PostesReferenceActesDTO> partialUpdatePostesReferenceActes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PostesReferenceActesDTO postesReferenceActesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PostesReferenceActes partially : {}, {}", id, postesReferenceActesDTO);
        if (postesReferenceActesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postesReferenceActesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postesReferenceActesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PostesReferenceActesDTO> result = postesReferenceActesService.partialUpdate(postesReferenceActesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postesReferenceActesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /postes-reference-actes} : get all the postesReferenceActes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postesReferenceActes in body.
     */
    @GetMapping("/postes-reference-actes")
    public ResponseEntity<List<PostesReferenceActesDTO>> getAllPostesReferenceActes(Pageable pageable) {
        log.debug("REST request to get a page of PostesReferenceActes");
        Page<PostesReferenceActesDTO> page = postesReferenceActesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /postes-reference-actes/:id} : get the "id" postesReferenceActes.
     *
     * @param id the id of the postesReferenceActesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postesReferenceActesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postes-reference-actes/{id}")
    public ResponseEntity<PostesReferenceActesDTO> getPostesReferenceActes(@PathVariable Long id) {
        log.debug("REST request to get PostesReferenceActes : {}", id);
        Optional<PostesReferenceActesDTO> postesReferenceActesDTO = postesReferenceActesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(postesReferenceActesDTO);
    }

    /**
     * {@code DELETE  /postes-reference-actes/:id} : delete the "id" postesReferenceActes.
     *
     * @param id the id of the postesReferenceActesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/postes-reference-actes/{id}")
    public ResponseEntity<Void> deletePostesReferenceActes(@PathVariable Long id) {
        log.debug("REST request to delete PostesReferenceActes : {}", id);
        postesReferenceActesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

/**
 * {@code GET  /postes/:code postes} : get the "postes " by code Postes.
 *
 * @param code Poste.
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postes, or with status {@code 404 (Not Found)}.
 */
@GetMapping("/postesReferenceActes/code-postes")
public List<PostesReferenceActesDTO> getPosteByCode(@RequestParam(value = "code") String code) {
    log.debug("REST request to get postes by codes : {}", code);
    List<PostesReferenceActesDTO> codePostesDTO = postesReferenceActesService.findByCodePostes(code);
    return codePostesDTO;
}
}
