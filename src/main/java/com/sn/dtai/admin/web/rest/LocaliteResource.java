package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Localite;
import com.sn.dtai.admin.repository.LocaliteRepository;
import com.sn.dtai.admin.service.dto.LocaliteDTO;
import com.sn.dtai.admin.service.impl.LocaliteServiceImpl;
import com.sn.dtai.admin.service.mapper.LocaliteMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import io.micrometer.core.annotation.Timed;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Localite}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LocaliteResource {

    private final Logger log = LoggerFactory.getLogger(LocaliteResource.class);

    private static final String ENTITY_NAME = "adminLocalite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocaliteRepository localiteRepository;

    private final LocaliteServiceImpl localiteServiceImpl;

    private final LocaliteMapper localiteMapper;

    public LocaliteResource(LocaliteRepository localiteRepository, LocaliteMapper localiteMapper, LocaliteServiceImpl localiteServiceImpl) {
        this.localiteRepository = localiteRepository;
        this.localiteMapper = localiteMapper;
        this.localiteServiceImpl = localiteServiceImpl;
    }

    /**
     * {@code POST  /localites} : Create a new localite.
     *
     * @param localite the localite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new localite, or with status {@code 400 (Bad Request)} if the localite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/localites")
    public ResponseEntity<Localite> createLocalite(@RequestBody Localite localite) throws URISyntaxException {
        log.debug("REST request to save Localite : {}", localite);
        if (localite.getId() != null) {
            throw new BadRequestAlertException("A new localite cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code applicatoins
        if (localite.getCode() == null || localite.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code moduless
            Optional<Localite> verifCodeLocalite = localiteServiceImpl.findByCode(localite.getCode());
            if (verifCodeLocalite.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + localite.getCode() + "' est déjà utilisé : '" + verifCodeLocalite.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodeLocalite.get().getCode() + "' est déjà utilisé  : '."
                );
            }
        }

        // Vérif Validité code typeLocalite pour la structure connectée
        if (localite.getLibelle() == null || localite.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle Localite
            Optional<Localite> verifLibelleLocalite = localiteServiceImpl.findByLibelle(localite.getLibelle());
            if (verifLibelleLocalite.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleLocalite.get().getLibelle() + "' est déjà utilisé'",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleLocalite.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        LocalDate dateCurrent = LocalDate.now();
        localite.setInsertDate(dateCurrent);
        Localite result = localiteRepository.save(localite);
        return ResponseEntity
            .created(new URI("/api/localites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /localites/:id} : Updates an existing localite.
     *
     * @param id the id of the localite to save.
     * @param localite the localite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localite,
     * or with status {@code 400 (Bad Request)} if the localite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the localite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/localites/{id}")
    public ResponseEntity<Localite> updateLocalite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Localite localite
    ) throws URISyntaxException {
        log.debug("REST request to update Localite : {}, {}", id, localite);
        if (localite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, localite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!localiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Localite result = localiteRepository.save(localite);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localite.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /localites/:id} : Partial updates given fields of an existing localite, field will ignore if it is null
     *
     * @param id the id of the localite to save.
     * @param localite the localite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localite,
     * or with status {@code 400 (Bad Request)} if the localite is not valid,
     * or with status {@code 404 (Not Found)} if the localite is not found,
     * or with status {@code 500 (Internal Server Error)} if the localite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/localites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Localite> partialUpdateLocalite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Localite localite
    ) throws URISyntaxException {
        log.debug("REST request to partial update Localite partially : {}, {}", id, localite);
        if (localite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, localite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!localiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Localite> result = localiteRepository
            .findById(localite.getId())
            .map(existingLocalite -> {
                if (localite.getCode() != null) {
                    existingLocalite.setCode(localite.getCode());
                }
                if (localite.getEstParDefaut() != null) {
                    existingLocalite.setEstParDefaut(localite.getEstParDefaut());
                }
                if (localite.getLibelle() != null) {
                    existingLocalite.setLibelle(localite.getLibelle());
                }
                if (localite.getNiveau() != null) {
                    existingLocalite.setNiveau(localite.getNiveau());
                }
                if (localite.getOrdre() != null) {
                    existingLocalite.setOrdre(localite.getOrdre());
                }
                if (localite.getVersion() != null) {
                    existingLocalite.setVersion(localite.getVersion());
                }
                if (localite.getPays() != null) {
                    existingLocalite.setPays(localite.getPays());
                }
                if (localite.getInsertUserId() != null) {
                    existingLocalite.setInsertUserId(localite.getInsertUserId());
                }
                if (localite.getInsertDate() != null) {
                    existingLocalite.setInsertDate(localite.getInsertDate());
                }

                return existingLocalite;
            })
            .map(localiteRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localite.getId().toString())
        );
    }

    /**
     * {@code GET  /localites} : get all the localites.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localites in body.
     */
    @GetMapping("/localites")
    public ResponseEntity<List<Localite>> getAllLocalites(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("localite-is-null".equals(filter)) {
            log.debug("REST request to get all Localites where localite is null");
            return new ResponseEntity<>(
                StreamSupport
                    .stream(localiteRepository.findAll().spliterator(), false)
                    .filter(localite -> localite.getLocalites() == null)
                    .collect(Collectors.toList()),
                HttpStatus.OK
            );
        }
        log.debug("REST request to get a page of Localites");
        Page<Localite> page = localiteRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /localites/:id} : get the "id" localite.
     *
     * @param id the id of the localite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the localite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/localites/{id}")
    public ResponseEntity<Localite> getLocalite(@PathVariable Long id) {
        log.debug("REST request to get Localite : {}", id);
        Optional<Localite> localite = localiteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(localite);
    }

    /**
     * {@code DELETE  /localites/:id} : delete the "id" localite.
     *
     * @param id the id of the localite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/localites/{id}")
    public ResponseEntity<Void> deleteLocalite(@PathVariable Long id) {
        log.debug("REST request to delete Localite : {}", id);
        List<LocaliteDTO> localitesByEtablissement= localiteRepository.getLocalitesByEtEtablissement(id).stream()
        .map(localiteMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (localitesByEtablissement.size()>0) {
            throw new BadRequestAlertException(" Cette Localité est utilisée dans établissement.", ENTITY_NAME, " Cette Localité est utilisée dans établissement.");
        }


        List<LocaliteDTO> localitesByLocalite= localiteRepository.getLocalitesByLocalite(id).stream()
        .map(localiteMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (localitesByLocalite.size() ==  0) {
            throw new BadRequestAlertException("Impossible de supprimer cette Localité car utilisée dans établissement.", ENTITY_NAME, " Impossible de supprimer cette Localité car utilisée dans établissement..");
        }

        localiteRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * GET  /localites/type :   localites by type.
     */
    @GetMapping("/localites/type")
    @Timed
    public List<LocaliteDTO> getLocalitesBycodeTypeLocalite(@RequestParam(value = "codeTypeLocalite") String codeTypeLocalite) {
        //log.debug("REST request Localites By TypeCodeLocalite : {}", codeTypeLocalite);
        log.debug("Request to get Localites : {}", codeTypeLocalite);
        return localiteRepository
            .findAllByTypeLocalite(codeTypeLocalite)
            .stream()
            .map(localiteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * GET  /localites/rattachement :   localites by rattachement.
     */
    @GetMapping("/localites/rattachement/{localite}")
    @Timed
    public List<LocaliteDTO> getLocalitesByRattachement(@PathVariable Long localite) {
        //log.debug("REST request Localites By localite : {}", localiteLocalite);
        log.debug("Request to get Localites : {}", localite);
        return localiteRepository
            .findAllByRattachement(localite)
            .stream()
            .map(localiteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

        /**
     * {@code GET  /localites} : get all the localites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of etablissements in body.
     */
    @GetMapping("/localites/recherche")
    public ResponseEntity<List<Localite>> getAllLocalitesByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of LOCALITES");
        Page<Localite> page = localiteRepository.rechercheLocaliteByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
