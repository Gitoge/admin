package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.HierarchieCategorieRepository;
import com.sn.dtai.admin.service.HierarchieCategorieService;
import com.sn.dtai.admin.service.dto.HierarchieCategorieDTO;
import com.sn.dtai.admin.service.impl.HierarchieCategorieServiceImpl;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.HierarchieCategorie}.
 */
@RestController
@RequestMapping("/api")
public class HierarchieCategorieResource {

    private final Logger log = LoggerFactory.getLogger(HierarchieCategorieResource.class);

    private static final String ENTITY_NAME = "adminHierarchieCategorie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HierarchieCategorieService hierarchieCategoriesService;
    
    private final HierarchieCategorieServiceImpl hierarchieCategoriesServiceImpl;

    private final HierarchieCategorieRepository hierarchieCategoriesRepository;

    public HierarchieCategorieResource(HierarchieCategorieService hierarchieCategoriesService, HierarchieCategorieRepository hierarchieCategoriesRepository, HierarchieCategorieServiceImpl hierarchieCategoriesServiceImpl) {
        this.hierarchieCategoriesService = hierarchieCategoriesService;
        this.hierarchieCategoriesRepository = hierarchieCategoriesRepository;
        this.hierarchieCategoriesServiceImpl = hierarchieCategoriesServiceImpl;
    }

    /**
     * {@code POST  /hierarchie-categories} : Create a new hierarchieCategories.
     *
     * @param hierarchieCategoriesDTO the hierarchieCategoriesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hierarchieCategoriesDTO, or with status {@code 400 (Bad Request)} if the hierarchieCategories has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hierarchie-categories")
    public ResponseEntity<HierarchieCategorieDTO> createHierarchieCategorie(@RequestBody HierarchieCategorieDTO hierarchieCategoriesDTO) throws URISyntaxException {
        log.debug("REST request to save HierarchieCategorie : {}", hierarchieCategoriesDTO);
        if (hierarchieCategoriesDTO.getId() != null) {
            throw new BadRequestAlertException("A new hierarchieCategories cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HierarchieCategorieDTO result = hierarchieCategoriesService.save(hierarchieCategoriesDTO);
        return ResponseEntity
            .created(new URI("/api/hierarchie-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hierarchie-categories/:id} : Updates an existing hierarchieCategories.
     *
     * @param id the id of the hierarchieCategoriesDTO to save.
     * @param hierarchieCategoriesDTO the hierarchieCategoriesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hierarchieCategoriesDTO,
     * or with status {@code 400 (Bad Request)} if the hierarchieCategoriesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hierarchieCategoriesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hierarchie-categories/{id}")
    public ResponseEntity<HierarchieCategorieDTO> updateHierarchieCategorie(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HierarchieCategorieDTO hierarchieCategoriesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update HierarchieCategorie : {}, {}", id, hierarchieCategoriesDTO);
        if (hierarchieCategoriesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hierarchieCategoriesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hierarchieCategoriesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HierarchieCategorieDTO result = hierarchieCategoriesService.update(hierarchieCategoriesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hierarchieCategoriesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /hierarchie-categories/:id} : Partial updates given fields of an existing hierarchieCategories, field will ignore if it is null
     *
     * @param id the id of the hierarchieCategoriesDTO to save.
     * @param hierarchieCategoriesDTO the hierarchieCategoriesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hierarchieCategoriesDTO,
     * or with status {@code 400 (Bad Request)} if the hierarchieCategoriesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hierarchieCategoriesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hierarchieCategoriesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hierarchie-categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HierarchieCategorieDTO> partialUpdateHierarchieCategorie(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HierarchieCategorieDTO hierarchieCategoriesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update HierarchieCategorie partially : {}, {}", id, hierarchieCategoriesDTO);
        if (hierarchieCategoriesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hierarchieCategoriesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hierarchieCategoriesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HierarchieCategorieDTO> result = hierarchieCategoriesService.partialUpdate(hierarchieCategoriesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hierarchieCategoriesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hierarchie-categories} : get all the hierarchieCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hierarchieCategories in body.
     */
    @GetMapping("/hierarchie-categories")
    public ResponseEntity<List<HierarchieCategorieDTO>> getAllHierarchieCategories(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of HierarchieCategories");
        Page<HierarchieCategorieDTO> page = hierarchieCategoriesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hierarchie-categories/all} : get all the hierarchieCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hierarchieCategories in body.
     */
    @GetMapping("/hierarchie-categories/all")
    public ResponseEntity<List<HierarchieCategorieDTO>> getAllHierarchieCategorie(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of HierarchieCategories");
        // return hierarchieCategoriesService.findAllHierarchieCategorie();
        Page<HierarchieCategorieDTO> page = hierarchieCategoriesService.findAllHierarchieCategorie(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * {@code GET  /hierarchie-categories/:id} : get the "id" hierarchieCategories.
     *
     * @param id the id of the hierarchieCategoriesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hierarchieCategoriesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hierarchie-categories/{id}")
    public ResponseEntity<HierarchieCategorieDTO> getHierarchieCategorie(@PathVariable Long id) {
        log.debug("REST request to get HierarchieCategorie : {}", id);
        Optional<HierarchieCategorieDTO> hierarchieCategoriesDTO = hierarchieCategoriesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hierarchieCategoriesDTO);
    }

    /**
     * {@code DELETE  /hierarchie-categories/:id} : delete the "id" hierarchieCategories.
     *
     * @param id the id of the hierarchieCategoriesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hierarchie-categories/{id}")
    public ResponseEntity<Void> deleteHierarchieCategorie(@PathVariable Long id) {
        log.debug("REST request to delete HierarchieCategorie : {}", id);
        hierarchieCategoriesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


        /**
     * {@code GET  /hierarchie-categories/:categorieId} : get the "id" hierarchieCategories.
     *
     * @param id the id of the hierarchieCategoriesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hierarchieCategoriesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hierarchie-categories/categorie/{categorieId}")
    public ResponseEntity<HierarchieCategorieDTO> getHierarchieByCategorie(@PathVariable Long categorieId) {
        log.debug("REST request to get HierarchieCategorie : {}", categorieId);
        try{
            Optional<HierarchieCategorieDTO> hierarchieCategoriesDTO = hierarchieCategoriesServiceImpl.findHierarchieByCategorie(categorieId);
            return ResponseUtil.wrapOrNotFound(hierarchieCategoriesDTO);
        }catch(Exception e)
        {
            return null;
        }
      
    }
}
