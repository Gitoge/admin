package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.repository.PostesRepository;
import com.sn.dtai.admin.service.PostesService;
import com.sn.dtai.admin.service.dto.ListPostesDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import com.sn.dtai.admin.service.mapper.PostesMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Postes}.
 */
@RestController
@RequestMapping("/api")
public class PostesResource {
    private final Logger log = LoggerFactory.getLogger(PostesResource.class);

    private static final String ENTITY_NAME = "adminPostes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PostesService postesService;

    private final PostesRepository postesRepository;

    private final PostesMapper postesMapper;

    public PostesResource(PostesService postesService, PostesRepository postesRepository,PostesMapper postesMapper) {
        this.postesService = postesService;
        this.postesRepository = postesRepository;
        this.postesMapper = postesMapper;
    }

    /**
     * {@code POST  /postes} : Create a new postes.
     *
     * @param postesDTO the postesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postesDTO, or with status {@code 400 (Bad Request)} if the postes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/postes")
    public ResponseEntity<PostesDTO> createPostes(@Valid @RequestBody PostesDTO postesDTO) throws URISyntaxException {
        log.debug("REST request to save Postes : {}", postesDTO);
        if (postesDTO.getId() != null) {
            throw new BadRequestAlertException("A new postes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PostesDTO result = postesService.save(postesDTO);
        return ResponseEntity
            .created(new URI("/api/postes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /postes/:id} : Updates an existing postes.
     *
     * @param id the id of the postesDTO to save.
     * @param postesDTO the postesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postesDTO,
     * or with status {@code 400 (Bad Request)} if the postesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the postesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/postes/{id}")
    public ResponseEntity<PostesDTO> updatePostes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PostesDTO postesDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update Postes : {}, {}", id, postesDTO);
        if (postesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PostesDTO result = postesService.update(postesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /postes/:id} : Partial updates given fields of an existing postes, field will ignore if it is null
     *
     * @param id the id of the postesDTO to save.
     * @param postesDTO the postesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postesDTO,
     * or with status {@code 400 (Bad Request)} if the postesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the postesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the postesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/postes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PostesDTO> partialUpdatePostes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PostesDTO postesDTO
    )
        throws URISyntaxException {
        log.debug("REST request to partial update Postes partially : {}, {}", id, postesDTO);
        if (postesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PostesDTO> result = postesService.partialUpdate(postesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /postes} : get all the postes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postes in body.
     */
    @GetMapping("/postes")
    public ResponseEntity<List<PostesDTO>> getAllPostes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Postes");
        Page<PostesDTO> page = postesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /postes/:id} : get the "id" postes.
     *
     * @param id the id of the postesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postes/{id}")
    public ResponseEntity<PostesDTO> getPostes(@PathVariable Long id) {
        log.debug("REST request to get Postes : {}", id);
        Optional<PostesDTO> postesDTO = postesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(postesDTO);
    }

    /**
     * {@code DELETE  /postes/:id} : delete the "id" postes.
     *
     * @param id the id of the postesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/postes/{id}")
    public ResponseEntity<Void> deletePostes(@PathVariable Long id) {
        log.debug("REST request to delete Postes : {}", id);
        postesService.delete(id);
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
    @GetMapping("/postes/code-postes")
    public ResponseEntity<PostesDTO> getPosteByCode(@RequestParam(value = "code") String code) {
        log.debug("REST request to get postes by codes : {}", code);
        Optional<PostesDTO> codePostesDTO = postesService.findByCodePostes(code);
        return ResponseUtil.wrapOrNotFound(codePostesDTO);
    }

    /**
     * {@code GET  /postes} : get all the postes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postes in body.
     */
    @GetMapping("/listPostes")
    public List<PostesDTO> getAllPostesByList() {
        log.debug("REST request to get a page of Postes");
        List<PostesDTO> page = postesService.listPostes();
        return page;
    }

    /**
     * {@code GET  /postes} : get all the postes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of postes in body.
     */
    @GetMapping("/postes/all")
    public List<Postes> getAllPostes() {
        return postesRepository.listPostes();
    }

    /**
     * {@code GET  /postes} : get all the postes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of postes in body.
     */
    @GetMapping("/postes/grade/all/{gradeId}")
    public List<Postes> getAllPostesByGrade(@PathVariable Long gradeId) {
        return postesRepository.getPostesAssiettesByGrade(gradeId);
    }

    /**
     * {@code GET  /postes} : get all the postes by grade Id.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of postes in body.
     */
    @GetMapping("/postes/grade")
    public List<Postes> getPostesByGrade(@RequestParam(value = "gradeId") Long gradeId) {
        return postesRepository.getPostesByGrade(gradeId);
    }


/**
 * {@code GET  /postes} : get all the postes by grade Id.
 *
 * @param pageable the pagination information.
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
 *         of postes in body.
 */
@GetMapping("/postes/emplois")
public List<Postes> getPostesByEmplois(@RequestParam(value = "emploisId") Long emploisId) {
    return postesRepository.getPostesByEmplois(emploisId);
}

    /**
     * {@code GET  /postes} : get all the postes by grade Id.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of postes in body.
     */
    @GetMapping("/postes/assiettes")
    public List<Postes> getPostesByAssiette(@RequestParam(value = "assietteId") Long assietteId) {
        return postesRepository.getPostesByAssiette(assietteId);
    }

    /**
     * {@code GET  /postes/:id} : get the "id" postes.
     *
     * @param id the id of the postesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postes/gain/{id}")
    public ResponseEntity<PostesDTO> getPostesInf600(@PathVariable Long id) {
        log.debug("REST request to get Postes : {}", id);

        try {
        Optional<PostesDTO> postesDTO = postesRepository.getPostesInf600(id).map(postesMapper::toDto);;
        return ResponseUtil.wrapOrNotFound(postesDTO);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }


    /**
     * {@code GET  /postes/:id} : get the "id" postes.
     *
     * @param id the id of the postesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/postes/grade/gain/{gradeId}")
    public ListPostesDTO getPostesGainByGrade(@PathVariable Long gradeId) {
        log.debug("REST request to get Postes : {}", gradeId);

        try {

        List<PostesDTO> postesDTO = postesRepository.getPostesGainByGrade(gradeId).stream().map(postesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));;
        ListPostesDTO listPostesDTO = new ListPostesDTO();

        listPostesDTO.setPostes(postesDTO);
        return listPostesDTO;

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

      /**
     * {@code GET  /postes/rechercher} : get all the postes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of postes in body.
     */
    @GetMapping("/postes/recherche")
    public ResponseEntity<List<Postes>> getAllIndicesByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of postes");
        Page<Postes> page = postesRepository.recherchesPostesByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
