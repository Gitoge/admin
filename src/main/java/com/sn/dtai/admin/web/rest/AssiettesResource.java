package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Assiettes;
import com.sn.dtai.admin.repository.AssiettesRepository;
import com.sn.dtai.admin.service.AssiettesPostesService;
import com.sn.dtai.admin.service.AssiettesService;
import com.sn.dtai.admin.service.dto.AssiettesDTO;
import com.sn.dtai.admin.service.dto.AssiettesPostesDTO;
import com.sn.dtai.admin.service.dto.ListAssiettesPostesDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import com.sn.dtai.admin.service.mapper.AssiettesMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Assiettes}.
 */
@RestController
@RequestMapping("/api")
public class AssiettesResource {

    private final Logger log = LoggerFactory.getLogger(AssiettesResource.class);

    private static final String ENTITY_NAME = "adminAssiettes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssiettesService assiettesService;

    private final AssiettesRepository assiettesRepository;

    private final AssiettesPostesResource assiettesPosteResource;

    private final AssiettesPostesService assiettesPosteService;

    public AssiettesResource(AssiettesService assiettesService, AssiettesRepository assiettesRepository,
            AssiettesPostesResource assiettesPosteResource, AssiettesPostesService assiettesPosteService) {
        this.assiettesService = assiettesService;
        this.assiettesRepository = assiettesRepository;
        this.assiettesPosteResource = assiettesPosteResource;
        this.assiettesPosteService = assiettesPosteService;
    }

    /**
     * {@code POST  /assiettes} : Create a new assiettes.
     *
     * @param assiettesDTO the assiettesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new assiettesDTO, or with status {@code 400 (Bad Request)}
     *         if the assiettes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assiettes")
    public ResponseEntity<AssiettesDTO> createAssiettes(@Valid @RequestBody AssiettesDTO assiettesDTO)
            throws URISyntaxException {
        log.debug("REST request to save Assiettes : {}", assiettesDTO);
        if (assiettesDTO.getId() != null) {
            throw new BadRequestAlertException("A new assiettes cannot already have an ID", ENTITY_NAME, "idexists");
        }

        AssiettesPostesDTO assiettesPostesDTO = new AssiettesPostesDTO();
        Optional code =assiettesService.findOne(assiettesDTO.getCode());
        if (code.isPresent()) {
            throw new BadRequestAlertException("Ce code existe déja", ENTITY_NAME, "Ce code existe déja");
        }
        AssiettesDTO result = assiettesService.save(assiettesDTO);

        assiettesPostesDTO.setAssiettes(result);
        assiettesPostesDTO.setOperateur(assiettesDTO.getOperateur());

        List<PostesDTO> listesPostes = new ArrayList<>(assiettesDTO.getPostes());

        for (int i = 0; i < listesPostes.size(); i++) {
            assiettesPostesDTO.setPostes(listesPostes.get(i));
            assiettesPosteResource.createAssiettesPostes(assiettesPostesDTO);
        }

        return ResponseEntity
                .created(new URI("/api/assiettes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /assiettes/:id} : Updates an existing assiettes.
     *
     * @param id           the id of the assiettesDTO to save.
     * @param assiettesDTO the assiettesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated assiettesDTO,
     *         or with status {@code 400 (Bad Request)} if the assiettesDTO is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         assiettesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assiettes/{id}")
    public ResponseEntity<AssiettesDTO> updateAssiettes(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody AssiettesDTO assiettesDTO) throws URISyntaxException {
        log.debug("REST request to update Assiettes : {}, {}", id, assiettesDTO);
        if (assiettesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assiettesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assiettesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AssiettesPostesDTO assiettesPostesDTO = new AssiettesPostesDTO();

        AssiettesDTO result = assiettesService.update(assiettesDTO);

        List<AssiettesPostesDTO> assiettePostesAUpdate = assiettesPosteResource
                .getListAssiettesPostesByAssiette(assiettesDTO.getId());

        List<PostesDTO> listesPostes = new ArrayList<>(assiettesDTO.getPostes());

        // ON veut conserver les mêmes id

        for (int x = 0; x < assiettePostesAUpdate.size(); x++) {
            assiettesPosteService.delete(assiettePostesAUpdate.get(x).getId());
        }
        for (int y = 0; y < listesPostes.size(); y++) {
            assiettesPostesDTO = new AssiettesPostesDTO();
            assiettesPostesDTO.setAssiettes(result);
            assiettesPostesDTO.setOperateur(assiettesDTO.getOperateur());
            assiettesPostesDTO.setPostes(listesPostes.get(y));
            assiettesPosteResource.createAssiettesPostes(assiettesPostesDTO);

        }
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        assiettesDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code PATCH  /assiettes/:id} : Partial updates given fields of an existing
     * assiettes, field will ignore if it is null
     *
     * @param id           the id of the assiettesDTO to save.
     * @param assiettesDTO the assiettesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated assiettesDTO,
     *         or with status {@code 400 (Bad Request)} if the assiettesDTO is not
     *         valid,
     *         or with status {@code 404 (Not Found)} if the assiettesDTO is not
     *         found,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         assiettesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/assiettes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AssiettesDTO> partialUpdateAssiettes(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody AssiettesDTO assiettesDTO) throws URISyntaxException {
        log.debug("REST request to partial update Assiettes partially : {}, {}", id, assiettesDTO);
        if (assiettesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assiettesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assiettesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AssiettesDTO> result = assiettesService.partialUpdate(assiettesDTO);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        assiettesDTO.getId().toString()));
    }

    /**
     * {@code GET  /assiettes} : get all the assiettes.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is
     *                  applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of assiettes in body.
     */
    @GetMapping("/assiettes")
    public ResponseEntity<List<AssiettesDTO>> getAllAssiettes(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable,
            @RequestParam(required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get a page of Assiettes");
        Page<AssiettesDTO> page;
        if (eagerload) {
            page = assiettesService.findAllWithEagerRelationships(pageable);
        } else {
            page = assiettesService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /assiettes/:id} : get the "id" assiettes.
     *
     * @param id the id of the assiettesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the assiettesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assiettes/{id}")
    public ResponseEntity<AssiettesDTO> getAssiettes(@PathVariable Long id) {
        log.debug("REST request to get Assiettes : {}", id);
        Optional<AssiettesDTO> assiettesDTO = assiettesService.findOne(id);
        List<AssiettesPostesDTO> assiettesPoste = assiettesPosteService.findByAssiettes(id);
        if(assiettesPoste.size()> 0){
            assiettesDTO.get().setOperateur(assiettesPoste.get(0).getOperateur());
        }
        return ResponseUtil.wrapOrNotFound(assiettesDTO);
    }

    /**
     * {@code DELETE  /assiettes/:id} : delete the "id" assiettes.
     *
     * @param id the id of the assiettesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assiettes/{id}")
    public ResponseEntity<Void> deleteAssiettes(@PathVariable Long id) {
        log.debug("REST request to delete Assiettes : {}", id);
        try {
            assiettesService.delete(id);

        } catch (Exception e) {
            throw new BadRequestAlertException("Assiette est toujours référencée dans la table Assiettes Poste", ENTITY_NAME, "Assiette est toujours référencée dans la table Assiettes Poste");
        }
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    /**
     * {@code GET  /assiettes/:id} : get the "id" assiettes.
     *
     * @param id the id of the assiettesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the assiettesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assiette/{id}")
    public ResponseEntity<Assiettes> getAssiette(@PathVariable Long id) {
        log.debug("REST request to get Assiettes : {}", id);
        try {
            Optional<Assiettes> assiettes = assiettesRepository.findById(id);
            return ResponseUtil.wrapOrNotFound(assiettes);
        } catch (Exception e) {
            throw new BadRequestAlertException("Assiette Introuvable", ENTITY_NAME, "Assiette Introuvable");
        }
    }

    /**
     * {@code GET  /assiettes/:id} : get the "id" assiettes.
     *
     * @param id the id of the assiettesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the assiettesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assiettes/code/{code}")
    public ResponseEntity<AssiettesDTO> getAssiette(@PathVariable String code) {
        log.debug("REST request to get Assiettes : {}", code);
        Optional<AssiettesDTO> assiettes = assiettesService.findOne(code);
        return ResponseUtil.wrapOrNotFound(assiettes);
    }
}
