package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.repository.ParamBaremeImposableRepository;
import com.sn.dtai.admin.service.ParamBaremeImposableService;
import com.sn.dtai.admin.service.dto.ParamBaremeImposableDTO;
import com.sn.dtai.admin.service.impl.ParamBaremeImposableServiceImpl;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.ParamBaremeImposable}.
 */
@RestController
@RequestMapping("/api")
public class ParamBaremeImposableResource {

    private final Logger log = LoggerFactory.getLogger(ParamBaremeImposableResource.class);

    private static final String ENTITY_NAME = "adminParamBaremeImposable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParamBaremeImposableService paramBaremeImposableService;
   
    private final ParamBaremeImposableServiceImpl paramBaremeImposableServiceImpl;

    private final ParamBaremeImposableRepository paramBaremeImposableRepository;

    public ParamBaremeImposableResource(
        ParamBaremeImposableService paramBaremeImposableService,
        ParamBaremeImposableRepository paramBaremeImposableRepository,
        ParamBaremeImposableServiceImpl paramBaremeImposableServiceImpl
    ) {
        this.paramBaremeImposableService = paramBaremeImposableService;
        this.paramBaremeImposableRepository = paramBaremeImposableRepository;
        this.paramBaremeImposableServiceImpl = paramBaremeImposableServiceImpl;
    }

    /**
     * {@code POST  /param-bareme-imposables} : Create a new paramBaremeImposable.
     *
     * @param paramBaremeImposableDTO the paramBaremeImposableDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paramBaremeImposableDTO, or with status {@code 400 (Bad Request)} if the paramBaremeImposable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/param-bareme-imposables")
    public ResponseEntity<ParamBaremeImposableDTO> createParamBaremeImposable(
        @Valid @RequestBody ParamBaremeImposableDTO paramBaremeImposableDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ParamBaremeImposable : {}", paramBaremeImposableDTO);
        if (paramBaremeImposableDTO.getId() != null) {
            throw new BadRequestAlertException("A new paramBaremeImposable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParamBaremeImposableDTO result = paramBaremeImposableService.save(paramBaremeImposableDTO);
        return ResponseEntity
            .created(new URI("/api/param-bareme-imposables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /param-bareme-imposables/:id} : Updates an existing paramBaremeImposable.
     *
     * @param id the id of the paramBaremeImposableDTO to save.
     * @param paramBaremeImposableDTO the paramBaremeImposableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramBaremeImposableDTO,
     * or with status {@code 400 (Bad Request)} if the paramBaremeImposableDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paramBaremeImposableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/param-bareme-imposables/{id}")
    public ResponseEntity<ParamBaremeImposableDTO> updateParamBaremeImposable(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ParamBaremeImposableDTO paramBaremeImposableDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParamBaremeImposable : {}, {}", id, paramBaremeImposableDTO);
        if (paramBaremeImposableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramBaremeImposableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramBaremeImposableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ParamBaremeImposableDTO result = paramBaremeImposableService.update(paramBaremeImposableDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramBaremeImposableDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /param-bareme-imposables/:id} : Partial updates given fields of an existing paramBaremeImposable, field will ignore if it is null
     *
     * @param id the id of the paramBaremeImposableDTO to save.
     * @param paramBaremeImposableDTO the paramBaremeImposableDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paramBaremeImposableDTO,
     * or with status {@code 400 (Bad Request)} if the paramBaremeImposableDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paramBaremeImposableDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paramBaremeImposableDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/param-bareme-imposables/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ParamBaremeImposableDTO> partialUpdateParamBaremeImposable(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ParamBaremeImposableDTO paramBaremeImposableDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParamBaremeImposable partially : {}, {}", id, paramBaremeImposableDTO);
        if (paramBaremeImposableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paramBaremeImposableDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paramBaremeImposableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ParamBaremeImposableDTO> result = paramBaremeImposableService.partialUpdate(paramBaremeImposableDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paramBaremeImposableDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /param-bareme-imposables} : get all the paramBaremeImposables.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paramBaremeImposables in body.
     */
    @GetMapping("/param-bareme-imposables")
    public ResponseEntity<List<ParamBaremeImposableDTO>> getAllParamBaremeImposables(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ParamBaremeImposables");
        Page<ParamBaremeImposableDTO> page = paramBaremeImposableService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /param-bareme-imposables/:id} : get the "id" paramBaremeImposable.
     *
     * @param id the id of the paramBaremeImposableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramBaremeImposableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-bareme-imposables/{id}")
    public ResponseEntity<ParamBaremeImposableDTO> getParamBaremeImposable(@PathVariable Long id) {
        log.debug("REST request to get ParamBaremeImposable : {}", id);
        Optional<ParamBaremeImposableDTO> paramBaremeImposableDTO = paramBaremeImposableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paramBaremeImposableDTO);
    }

    /**
     * {@code DELETE  /param-bareme-imposables/:id} : delete the "id" paramBaremeImposable.
     *
     * @param id the id of the paramBaremeImposableDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/param-bareme-imposables/{id}")
    public ResponseEntity<Void> deleteParamBaremeImposable(@PathVariable Long id) {
        log.debug("REST request to delete ParamBaremeImposable : {}", id);
        paramBaremeImposableService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }


    
    /**
     * {@code GET  /param-bareme-imposables/:id} : get the "id" paramBaremeImposable.
     *
     * @param id the id of the paramBaremeImposableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramBaremeImposableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/param-bareme-imposables/poste/{codePoste}/somme/{somme}")
    public ResponseEntity<Integer> getParamBaremeImposableBySomme(@PathVariable String codePoste, @PathVariable Integer somme) {
        log.debug("REST request to get ParamBaremeImposable : {}", somme);
        try{
        Optional<Integer> paramBaremeImposableDTO = paramBaremeImposableServiceImpl.findBySommeandPoste(codePoste, somme);

            return ResponseUtil.wrapOrNotFound(paramBaremeImposableDTO);

        }catch(Exception e){
            return null;
        }
    }

    /**
     * {@code GET  /param-bareme-imposables/:id} : get the "id" paramBaremeImposable.
     *
     * @param id the id of the paramBaremeImposableDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paramBaremeImposableDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/somme-progressive/poste/{codePoste}/somme/{somme}")
    public ResponseEntity<Integer> getSommeProgressive(@PathVariable String codePoste, @PathVariable Integer somme) {
        log.debug("REST request to get ParamBaremeImposable : {}", somme);
        try{
            Optional<Integer> montant1 = paramBaremeImposableServiceImpl.findBySommeAndPoste(codePoste, somme);

            log.debug("MONTANT 1 : {} ", montant1);

            Optional<Integer> montant2 = paramBaremeImposableServiceImpl.sommeProgressive(codePoste, somme);

            log.debug("MONTANT 2 : {} ", montant2);
            return ResponseEntity.ok().body(montant1.get() + montant2.get());
        }catch(Exception e){
            return null;
        }
    }
}
