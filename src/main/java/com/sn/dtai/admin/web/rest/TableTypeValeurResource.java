package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.TableTypeValeur;
import com.sn.dtai.admin.repository.TableTypeValeurRepository;
import com.sn.dtai.admin.service.dto.TableTypeValeurDTO;
import com.sn.dtai.admin.service.impl.TableTypeValeurServiceImpl;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;

import io.micrometer.core.annotation.Timed;

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
 * REST controller for managing {@link com.sn.dtai.admin.domain.TableTypeValeur}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TableTypeValeurResource {

    private final Logger log = LoggerFactory.getLogger(TableTypeValeurResource.class);

    private static final String ENTITY_NAME = "adminTableTypeValeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableTypeValeurRepository tableTypeValeurRepository;
    private final TableTypeValeurServiceImpl tableTypeValeurService;

    public TableTypeValeurResource(TableTypeValeurRepository tableTypeValeurRepository, TableTypeValeurServiceImpl tableTypeValeurService) {
        this.tableTypeValeurRepository = tableTypeValeurRepository;
        this.tableTypeValeurService = tableTypeValeurService;
    }

    /**
     * {@code POST  /table-type-valeurs} : Create a new tableTypeValeur.
     *
     * @param tableTypeValeur the tableTypeValeur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableTypeValeur, or with status {@code 400 (Bad Request)} if the tableTypeValeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-type-valeurs")
    public ResponseEntity<TableTypeValeur> createTableTypeValeur(@Valid @RequestBody TableTypeValeur tableTypeValeur)
        throws URISyntaxException {
        log.debug("REST request to save TableTypeValeur : {}", tableTypeValeur);
        if (tableTypeValeur.getId() != null) {
            throw new BadRequestAlertException("A new tableTypeValeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableTypeValeur result = tableTypeValeurRepository.save(tableTypeValeur);
        return ResponseEntity
            .created(new URI("/api/table-type-valeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-type-valeurs/:id} : Updates an existing tableTypeValeur.
     *
     * @param id the id of the tableTypeValeur to save.
     * @param tableTypeValeur the tableTypeValeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableTypeValeur,
     * or with status {@code 400 (Bad Request)} if the tableTypeValeur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableTypeValeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-type-valeurs/{id}")
    public ResponseEntity<TableTypeValeur> updateTableTypeValeur(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TableTypeValeur tableTypeValeur
    ) throws URISyntaxException {
        log.debug("REST request to update TableTypeValeur : {}, {}", id, tableTypeValeur);
        if (tableTypeValeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableTypeValeur.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableTypeValeurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TableTypeValeur result = tableTypeValeurRepository.save(tableTypeValeur);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableTypeValeur.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /table-type-valeurs/:id} : Partial updates given fields of an existing tableTypeValeur, field will ignore if it is null
     *
     * @param id the id of the tableTypeValeur to save.
     * @param tableTypeValeur the tableTypeValeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableTypeValeur,
     * or with status {@code 400 (Bad Request)} if the tableTypeValeur is not valid,
     * or with status {@code 404 (Not Found)} if the tableTypeValeur is not found,
     * or with status {@code 500 (Internal Server Error)} if the tableTypeValeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/table-type-valeurs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TableTypeValeur> partialUpdateTableTypeValeur(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TableTypeValeur tableTypeValeur
    ) throws URISyntaxException {
        log.debug("REST request to partial update TableTypeValeur partially : {}, {}", id, tableTypeValeur);
        if (tableTypeValeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableTypeValeur.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableTypeValeurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TableTypeValeur> result = tableTypeValeurRepository
            .findById(tableTypeValeur.getId())
            .map(existingTableTypeValeur -> {
                if (tableTypeValeur.getCode() != null) {
                    existingTableTypeValeur.setCode(tableTypeValeur.getCode());
                }
                if (tableTypeValeur.getLibelle() != null) {
                    existingTableTypeValeur.setLibelle(tableTypeValeur.getLibelle());
                }
                if (tableTypeValeur.getDescription() != null) {
                    existingTableTypeValeur.setDescription(tableTypeValeur.getDescription());
                }

                return existingTableTypeValeur;
            })
            .map(tableTypeValeurRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableTypeValeur.getId().toString())
        );
    }

    /**
     * {@code GET  /table-type-valeurs} : get all the tableTypeValeurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableTypeValeurs in body.
     */
    @GetMapping("/table-type-valeurs")
    public ResponseEntity<List<TableTypeValeur>> getAllTableTypeValeurs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TableTypeValeurs");
        Page<TableTypeValeur> page = tableTypeValeurRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /table-type-valeurs/:id} : get the "id" tableTypeValeur.
     *
     * @param id the id of the tableTypeValeur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableTypeValeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-type-valeurs/{id}")
    public ResponseEntity<TableTypeValeur> getTableTypeValeur(@PathVariable Long id) {
        log.debug("REST request to get TableTypeValeur : {}", id);
        Optional<TableTypeValeur> tableTypeValeur = tableTypeValeurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tableTypeValeur);
    }

    /**
     * {@code DELETE  /table-type-valeurs/:id} : delete the "id" tableTypeValeur.
     *
     * @param id the id of the tableTypeValeur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-type-valeurs/{id}")
    public ResponseEntity<Void> deleteTableTypeValeur(@PathVariable Long id) {
        log.debug("REST request to delete TableTypeValeur : {}", id);
        tableTypeValeurRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

     /**
     * GET  /table-type-valeurs : get all the tableTypeValeurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tableTypeValeurs in body
     */
    @GetMapping("/table-type-valeurs/list")
    @Timed
    public List<TableTypeValeurDTO> getListAllTableTypeValeurs() {
        log.debug("REST request to get a page of TableTypeValeurs");
       
        return tableTypeValeurService.findListTableTypeValeur();
    }
}
