package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.AssiettesPostesRepository;
import com.sn.dtai.admin.service.AssiettesPostesService;
import com.sn.dtai.admin.service.dto.AssiettesPostesDTO;
import com.sn.dtai.admin.service.dto.ListAssiettesPostesDTO;
import com.sn.dtai.admin.service.mapper.AssiettesPostesMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
 * REST controller for managing {@link com.sn.dtai.admin.domain.AssiettesPostes}.
 */
@RestController
@RequestMapping("/api")
public class AssiettesPostesResource {

    private final Logger log = LoggerFactory.getLogger(AssiettesPostesResource.class);

    private static final String ENTITY_NAME = "adminAssiettesPostes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssiettesPostesService assiettesPostesService;

    private final AssiettesPostesRepository assiettesPostesRepository;

    private final AssiettesPostesMapper assiettesPostesMapper;

    public AssiettesPostesResource(AssiettesPostesService assiettesPostesService, AssiettesPostesRepository assiettesPostesRepository,  AssiettesPostesMapper assiettesPostesMapper) {
        this.assiettesPostesService = assiettesPostesService;
        this.assiettesPostesRepository = assiettesPostesRepository;
        this.assiettesPostesMapper = assiettesPostesMapper;
    }

    /**
     * {@code POST  /assiettes-postes} : Create a new assiettesPostes.
     *
     * @param assiettesPostesDTO the assiettesPostesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assiettesPostesDTO, or with status {@code 400 (Bad Request)} if the assiettesPostes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assiettes-postes")
    public ResponseEntity<AssiettesPostesDTO> createAssiettesPostes(@RequestBody AssiettesPostesDTO assiettesPostesDTO) throws URISyntaxException {
        log.debug("REST request to save AssiettesPostes : {}", assiettesPostesDTO);
        if (assiettesPostesDTO.getId() != null) {
            throw new BadRequestAlertException("A new assiettesPostes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssiettesPostesDTO result = assiettesPostesService.save(assiettesPostesDTO);
        return ResponseEntity
            .created(new URI("/api/assiettes-postes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assiettes-postes/:id} : Updates an existing assiettesPostes.
     *
     * @param id the id of the assiettesPostesDTO to save.
     * @param assiettesPostesDTO the assiettesPostesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assiettesPostesDTO,
     * or with status {@code 400 (Bad Request)} if the assiettesPostesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assiettesPostesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assiettes-postes/{id}")
    public ResponseEntity<AssiettesPostesDTO> updateAssiettesPostes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AssiettesPostesDTO assiettesPostesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AssiettesPostes : {}, {}", id, assiettesPostesDTO);
        if (assiettesPostesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assiettesPostesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assiettesPostesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AssiettesPostesDTO result = assiettesPostesService.update(assiettesPostesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assiettesPostesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /assiettes-postes/:id} : Partial updates given fields of an existing assiettesPostes, field will ignore if it is null
     *
     * @param id the id of the assiettesPostesDTO to save.
     * @param assiettesPostesDTO the assiettesPostesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assiettesPostesDTO,
     * or with status {@code 400 (Bad Request)} if the assiettesPostesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the assiettesPostesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the assiettesPostesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/assiettes-postes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AssiettesPostesDTO> partialUpdateAssiettesPostes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AssiettesPostesDTO assiettesPostesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AssiettesPostes partially : {}, {}", id, assiettesPostesDTO);
        if (assiettesPostesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assiettesPostesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assiettesPostesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AssiettesPostesDTO> result = assiettesPostesService.partialUpdate(assiettesPostesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assiettesPostesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /assiettes-postes} : get all the assiettesPostes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assiettesPostes in body.
     */
    @GetMapping("/assiettes-postes")
    public ResponseEntity<List<AssiettesPostesDTO>> getAllAssiettesPostess(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AssiettesPostess");
        Page<AssiettesPostesDTO> page = assiettesPostesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /assiettes-postes/all} : get all the assiettesPostes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assiettesPostes in body.
     */
    @GetMapping("/assiettes-postes/all")
    public ResponseEntity<List<AssiettesPostesDTO>> getAllAssiettesPostes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AssiettesPostess");
        // return assiettesPostesService.findAllAssiettesPostes();
        Page<AssiettesPostesDTO> page = assiettesPostesService.findAllAssiettesPostes(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /assiettes-postes/:id} : get the "id" assiettesPostes.
     *
     * @param id the id of the assiettesPostesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assiettesPostesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assiettes-postes/{id}")
    public ResponseEntity<AssiettesPostesDTO> getAssiettesPostes(@PathVariable Long id) {
        log.debug("REST request to get AssiettesPostes : {}", id);
        try{
        Optional<AssiettesPostesDTO> assiettesPostesDTO = assiettesPostesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assiettesPostesDTO);
        }
        catch(Exception e){
            throw new BadRequestAlertException("Assiette Introuvable", ENTITY_NAME,"Assiette Introuvable");
        }
    }

    /**
     * {@code DELETE  /assiettes-postes/:id} : delete the "id" assiettesPostes.
     *
     * @param id the id of the assiettesPostesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assiettes-postes/{id}")
    public ResponseEntity<Void> deleteAssiettesPostes(@PathVariable Long id) {
        log.debug("REST request to delete AssiettesPostes : {}", id);
        assiettesPostesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    
    /**
     * {@code GET  /assiettes-postes/:code} : get the "code" assiettesPostes.
     *
     * @param id the id of the assiettesPostesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assiettesPostesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assiettes-postes/assiettes/{assiettesId}")
    public ListAssiettesPostesDTO getAssiettesPostesByPostes(@PathVariable Long assiettesId) {
        log.debug("REST request to get AssiettesPostes : {}", assiettesId);
        List<AssiettesPostesDTO> assiettesPostesDTO = assiettesPostesService.findByAssiettes(assiettesId);
        
        ListAssiettesPostesDTO listeAssiettes = new ListAssiettesPostesDTO();

        listeAssiettes.setAssiettesPostes(assiettesPostesDTO);
        return  listeAssiettes;
    }


    /**
     * {@code GET  /assiettes-postes/:code} : get the "code" assiettesPostes.
     *
     * @param id the id of the assiettesPostesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assiettesPostesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assiettes-postes/assiettes/list/{assiettesId}")
    public List<AssiettesPostesDTO> getListAssiettesPostesByAssiette(@PathVariable Long assiettesId) {
        log.debug("REST request to get AssiettesPostes : {}", assiettesId);
        List<AssiettesPostesDTO> assiettesPostesDTO = assiettesPostesService.findByAssiettes(assiettesId);
       
        return  assiettesPostesDTO;
    }
}
