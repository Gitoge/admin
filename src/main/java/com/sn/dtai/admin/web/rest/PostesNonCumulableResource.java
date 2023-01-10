package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.PostesNonCumulableRepository;
import com.sn.dtai.admin.service.PostesNonCumulableService;
import com.sn.dtai.admin.service.dto.ListPostesNonCumulableDTO;
import com.sn.dtai.admin.service.dto.PostesNonCumulableDTO;
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
 * REST controller for managing
 * {@link com.sn.dtai.admin.domain.PostesNonCumulable}.
 */
@RestController
@RequestMapping("/api")
public class PostesNonCumulableResource {

    private final Logger log = LoggerFactory.getLogger(PostesNonCumulableResource.class);

    private static final String ENTITY_NAME = "carrierePostesNonCumulable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PostesNonCumulableService postesNonCumulableService;

    private final PostesNonCumulableRepository postesNonCumulableRepository;

    public PostesNonCumulableResource(
            PostesNonCumulableService postesNonCumulableService,
            PostesNonCumulableRepository postesNonCumulableRepository) {
        this.postesNonCumulableService = postesNonCumulableService;
        this.postesNonCumulableRepository = postesNonCumulableRepository;
    }

    /**
     * {@code POST  /postes-non-cumulables} : Create a new postesNonCumulable.
     *
     * @param postesNonCumulableDTO the postesNonCumulableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new postesNonCumulableDTO, or with status
     *         {@code 400 (Bad Request)} if the postesNonCumulable has already an
     *         ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/postes-non-cumulables")
    public ResponseEntity<PostesNonCumulableDTO> createPostesNonCumulable(
            @RequestBody PostesNonCumulableDTO postesNonCumulableDTO)
            throws URISyntaxException {
        log.debug("REST request to save PostesNonCumulable : {}", postesNonCumulableDTO);
        if (postesNonCumulableDTO.getId() != null) {
            throw new BadRequestAlertException("A new postesNonCumulable cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        if (postesNonCumulableDTO.getCodePoste1() == postesNonCumulableDTO.getCodePoste2()) {
            throw new BadRequestAlertException("Les deux codes ne doivent pas etre identiques", ENTITY_NAME,
                    "Les deux codes ne doivent pas etre identiques");
        }

        PostesNonCumulableDTO result = postesNonCumulableService.save(postesNonCumulableDTO);
        return ResponseEntity
                .created(new URI("/api/postes-non-cumulables/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /postes-non-cumulables/:id} : Updates an existing
     * postesNonCumulable.
     *
     * @param id                    the id of the postesNonCumulableDTO to save.
     * @param postesNonCumulableDTO the postesNonCumulableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated postesNonCumulableDTO,
     *         or with status {@code 400 (Bad Request)} if the postesNonCumulableDTO
     *         is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         postesNonCumulableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/postes-non-cumulables/{id}")
    public ResponseEntity<PostesNonCumulableDTO> updatePostesNonCumulable(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody PostesNonCumulableDTO postesNonCumulableDTO) throws URISyntaxException {
        log.debug("REST request to update PostesNonCumulable : {}, {}", id, postesNonCumulableDTO);
        if (postesNonCumulableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postesNonCumulableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postesNonCumulableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PostesNonCumulableDTO result = postesNonCumulableService.save(postesNonCumulableDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        postesNonCumulableDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code PATCH  /postes-non-cumulables/:id} : Partial updates given fields of
     * an existing postesNonCumulable, field will ignore if it is null
     *
     * @param id                    the id of the postesNonCumulableDTO to save.
     * @param postesNonCumulableDTO the postesNonCumulableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated postesNonCumulableDTO,
     *         or with status {@code 400 (Bad Request)} if the postesNonCumulableDTO
     *         is not valid,
     *         or with status {@code 404 (Not Found)} if the postesNonCumulableDTO
     *         is not found,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         postesNonCumulableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/postes-non-cumulables/{id}", consumes = { "application/json",
            "application/merge-patch+json" })
    public ResponseEntity<PostesNonCumulableDTO> partialUpdatePostesNonCumulable(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody PostesNonCumulableDTO postesNonCumulableDTO) throws URISyntaxException {
        log.debug("REST request to partial update PostesNonCumulable partially : {}, {}", id, postesNonCumulableDTO);
        if (postesNonCumulableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postesNonCumulableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postesNonCumulableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PostesNonCumulableDTO> result = postesNonCumulableService.partialUpdate(postesNonCumulableDTO);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        postesNonCumulableDTO.getId().toString()));
    }

    /**
     * {@code GET  /postes-non-cumulables} : get all the postesNonCumulables.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of postesNonCumulables in body.
     */
    @GetMapping("/postes-non-cumulables")
    public ResponseEntity<List<PostesNonCumulableDTO>> getAllPostesNonCumulables(Pageable pageable) {
        log.debug("REST request to get a page of PostesNonCumulables");
        Page<PostesNonCumulableDTO> page = postesNonCumulableService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /postes-non-cumulables/:id} : get the "id" postesNonCumulable.
     *
     * @param id the id of the postesNonCumulableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the postesNonCumulableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postes-non-cumulables/{id}")
    public ResponseEntity<PostesNonCumulableDTO> getPostesNonCumulable(@PathVariable Long id) {
        log.debug("REST request to get PostesNonCumulable : {}", id);
        Optional<PostesNonCumulableDTO> postesNonCumulableDTO = postesNonCumulableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(postesNonCumulableDTO);
    }

    /**
     * {@code DELETE  /postes-non-cumulables/:id} : delete the "id"
     * postesNonCumulable.
     *
     * @param id the id of the postesNonCumulableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/postes-non-cumulables/{id}")
    public ResponseEntity<Void> deletePostesNonCumulable(@PathVariable Long id) {
        log.debug("REST request to delete PostesNonCumulable : {}", id);
        postesNonCumulableService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    // GET POSTE NON CUMULABLES :
    /**
     * {@code GET  /postes-non-cumulables/:id} : get the "id" postesNonCumulable.
     *
     * @param id the id of the postesNonCumulableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the postesNonCumulableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postes-non-cumulables/code-poste/{codePoste}")
    public ResponseEntity<ListPostesNonCumulableDTO> getPostesNonCumulableByCodePoste1(@PathVariable String codePoste) {
        log.debug("REST request to get PostesNonCumulable : {}", codePoste);
       
        try {
            ListPostesNonCumulableDTO listePostesNonCumulableDTO = new ListPostesNonCumulableDTO();
            List<PostesNonCumulableDTO> postesNonCumulableDTO = postesNonCumulableService
                    .findPosteNonCumulable(codePoste);

                    listePostesNonCumulableDTO.setPostesNonCumulable(postesNonCumulableDTO);    
            return ResponseEntity.ok().body(listePostesNonCumulableDTO);

        } catch (Exception e) {
            return null;
        }

    }
    
}
