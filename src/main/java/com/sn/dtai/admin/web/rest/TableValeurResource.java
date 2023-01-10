package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.TableValeur;
import com.sn.dtai.admin.repository.TableValeurRepository;
import com.sn.dtai.admin.service.dto.TableValeurDTO;
import com.sn.dtai.admin.service.mapper.TableValeurMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;

import io.micrometer.core.annotation.Timed;

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
 * REST controller for managing {@link com.sn.dtai.admin.domain.TableValeur}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TableValeurResource {

    private final Logger log = LoggerFactory.getLogger(TableValeurResource.class);

    private static final String ENTITY_NAME = "adminTableValeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TableValeurRepository tableValeurRepository;

    private final TableValeurMapper tableValeurMapper;

    private static String CODE_PAYS="001";
    
    private static String CODE_STATUT_MARITAL="028";

    private static String CODE_REMUNERATION="REMUNERATION";
     
    private static String CODE_REGLEMENT="MODE_REGLEMENT";
   
    private static String CODE_TYPE_PIECE="TYPE_PIECE";

    private static String CODE_GENRE= "010";
    
    private static String CODE_LIEN_PARENTE= "LIEN_PARENTE";

    private static String CODE_ROUTER_LINK= "ROUTER_LINK";

    private static String CODE_ICONS= "ICONS";
    
    private static String CODE_NATURE_ACTES= "NATURE_ACTES";

    public TableValeurResource(TableValeurRepository tableValeurRepository, TableValeurMapper tableValeurMapper) {
        this.tableValeurRepository = tableValeurRepository;
        this.tableValeurMapper = tableValeurMapper;
    }

    /**
     * {@code POST  /table-valeurs} : Create a new tableValeur.
     *
     * @param tableValeur the tableValeur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableValeur, or with status {@code 400 (Bad Request)} if the tableValeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/table-valeurs")
    public ResponseEntity<TableValeur> createTableValeur(@Valid @RequestBody TableValeur tableValeur) throws URISyntaxException {
        log.debug("REST request to save TableValeur : {}", tableValeur);
        if (tableValeur.getId() != null) {
            throw new BadRequestAlertException("A new tableValeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TableValeur result = tableValeurRepository.save(tableValeur);
        return ResponseEntity
            .created(new URI("/api/table-valeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /table-valeurs/:id} : Updates an existing tableValeur.
     *
     * @param id the id of the tableValeur to save.
     * @param tableValeur the tableValeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableValeur,
     * or with status {@code 400 (Bad Request)} if the tableValeur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tableValeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/table-valeurs/{id}")
    public ResponseEntity<TableValeur> updateTableValeur(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TableValeur tableValeur
    ) throws URISyntaxException {
        log.debug("REST request to update TableValeur : {}, {}", id, tableValeur);
        if (tableValeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableValeur.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableValeurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TableValeur result = tableValeurRepository.save(tableValeur);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableValeur.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /table-valeurs/:id} : Partial updates given fields of an existing tableValeur, field will ignore if it is null
     *
     * @param id the id of the tableValeur to save.
     * @param tableValeur the tableValeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableValeur,
     * or with status {@code 400 (Bad Request)} if the tableValeur is not valid,
     * or with status {@code 404 (Not Found)} if the tableValeur is not found,
     * or with status {@code 500 (Internal Server Error)} if the tableValeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/table-valeurs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TableValeur> partialUpdateTableValeur(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TableValeur tableValeur
    ) throws URISyntaxException {
        log.debug("REST request to partial update TableValeur partially : {}, {}", id, tableValeur);
        if (tableValeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tableValeur.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tableValeurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TableValeur> result = tableValeurRepository
            .findById(tableValeur.getId())
            .map(existingTableValeur -> {
                if (tableValeur.getCode() != null) {
                    existingTableValeur.setCode(tableValeur.getCode());
                }
                if (tableValeur.getLibelle() != null) {
                    existingTableValeur.setLibelle(tableValeur.getLibelle());
                }
                if (tableValeur.getDescription() != null) {
                    existingTableValeur.setDescription(tableValeur.getDescription());
                }

                return existingTableValeur;
            })
            .map(tableValeurRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tableValeur.getId().toString())
        );
    }

    /**
     * {@code GET  /table-valeurs} : get all the tableValeurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableValeurs in body.
     */
    @GetMapping("/table-valeurs")
    public ResponseEntity<List<TableValeur>> getAllTableValeurs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TableValeurs");
        Page<TableValeur> page = tableValeurRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /table-valeurs/:id} : get the "id" tableValeur.
     *
     * @param id the id of the tableValeur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableValeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/table-valeurs/{id}")
    public ResponseEntity<TableValeur> getTableValeur(@PathVariable Long id) {
        log.debug("REST request to get TableValeur : {}", id);
        Optional<TableValeur> tableValeur = tableValeurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tableValeur);
    }

    /**
     * {@code DELETE  /table-valeurs/:id} : delete the "id" tableValeur.
     *
     * @param id the id of the tableValeur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/table-valeurs/{id}")
    public ResponseEntity<Void> deleteTableValeur(@PathVariable Long id) {
        log.debug("REST request to delete TableValeur : {}", id);
        tableValeurRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * GET  /table-valeurs/pays : get all the tableValeurs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/pays")
    @Timed
    public  List<TableValeurDTO> getAllPays() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_PAYS).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    /**
     * GET  /table-valeurs/remuneration : get all the tableValeurs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/remuneration")
    @Timed
    public  List<TableValeurDTO> getTypeRemuneration() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_REMUNERATION).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    /**
     * GET  /table-valeurs/type-piece : get all the tableValeurs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/type-piece")
    @Timed
    public  List<TableValeurDTO> getTypePiece() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_TYPE_PIECE).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

     /**
     * GET  /table-valeurs/statut-marital : get all the tableValeurs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/statut-marital")
    @Timed
    public  List<TableValeurDTO> getStatutMarital() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_STATUT_MARITAL).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

     /**
     * GET  /table-valeurs/genre : get all the tableValeurs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/genre")
    @Timed
    public  List<TableValeurDTO> getGenre() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_GENRE).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    /**
     * GET  /table-valeurs/lien-parente : get all the tableValeurs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/lien-parente")
    @Timed
    public  List<TableValeurDTO> getLienParente() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_LIEN_PARENTE).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    /**
     * GET  /table-valeurs/router-link : get all the tableValeurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/router-link")
    @Timed
    public  List<TableValeurDTO> getRouterLink() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_ROUTER_LINK).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    /**
     * GET  /table-valeurs/icons : get all the tableValeurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/icons")
    @Timed
    public  List<TableValeurDTO> getIcons() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_ICONS).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

    
    /**
     * GET  /table-valeurs/nature-actes : get all the tableValeurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/nature-actes")
    @Timed
    public  List<TableValeurDTO> getNatureActes() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_NATURE_ACTES).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }


     
    /**
     * GET  /table-valeurs/mode-reglement : get all the tableValeurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tableValeurs in body
     */
    @GetMapping("/table-valeurs/mode-reglement")
    @Timed
    public  List<TableValeurDTO> getModeReglement() {
      //  log.debug("Request to get TableValeur : {}");
        return tableValeurRepository.findTableValeurByType(CODE_REGLEMENT).stream()
                .map(tableValeurMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));

    }

}
