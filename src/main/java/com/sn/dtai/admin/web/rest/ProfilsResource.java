package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Profils;
import com.sn.dtai.admin.repository.ProfilsRepository;
import com.sn.dtai.admin.service.impl.ProfilsServiceImpl;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Profils}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProfilsResource {

    private final Logger log = LoggerFactory.getLogger(ProfilsResource.class);

    private static final String ENTITY_NAME = "adminProfils";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfilsRepository profilsRepository;

    private final ProfilsServiceImpl profilsServiceImpl;

    public ProfilsResource(ProfilsRepository profilsRepository, ProfilsServiceImpl profilsServiceImpl) {
        this.profilsRepository = profilsRepository;
        this.profilsServiceImpl = profilsServiceImpl;
    }

    /**
     * {@code POST  /profils} : Create a new profils.
     *
     * @param profils the profils to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profils, or with status {@code 400 (Bad Request)} if the profils has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profils")
    public ResponseEntity<Profils> createProfils(@Valid @RequestBody Profils profils) throws URISyntaxException {
        log.debug("REST request to save Profils : {}", profils);
        if (profils.getId() != null) {
            throw new BadRequestAlertException("A new profils cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code applicatoins
        if (profils.getCode() == null || profils.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code applications
            Optional<Profils> verifCodeProfil = profilsServiceImpl.findByCode(profils.getCode());
            if (verifCodeProfil.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + profils.getCode() + "' est déjà utilisé : '" + verifCodeProfil.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" +
                    verifCodeProfil.get().getCode() +
                    "' est déjà utilisé par le profil  : '" +
                    verifCodeProfil.get().getLibelle() +
                    "'."
                );
            }
        }

        // Vérif Validité code forfait pour la structure connectée
        if (profils.getLibelle() == null || profils.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle forfait pour la structure connectée
            Optional<Profils> verifLibelleProfils = profilsServiceImpl.findByLibelle(profils.getLibelle());
            if (verifLibelleProfils.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Nom '" + verifLibelleProfils.get().getLibelle() + "' est déjà utilisé  par un profil '",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleProfils.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        Profils result = profilsRepository.save(profils);
        return ResponseEntity
            .created(new URI("/api/profils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profils/:id} : Updates an existing profils.
     *
     * @param id the id of the profils to save.
     * @param profils the profils to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profils,
     * or with status {@code 400 (Bad Request)} if the profils is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profils couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profils/{id}")
    public ResponseEntity<Profils> updateProfils(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Profils profils
    ) throws URISyntaxException {
        log.debug("REST request to update Profils : {}, {}", id, profils);
        if (profils.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, profils.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!profilsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        /*
         // Vérif Validité code applicatoins
         if (profils.getCode() == null || profils.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
             // Vérif Unicité code applications 
         Optional<Profils> verifCodeProfil = profilsServiceImpl.findByCode(profils.getCode());
         if (verifCodeProfil.isPresent()) {
             throw new BadRequestAlertException("Le code '"+profils.getCode()+"' est déjà utilisé : '"+verifCodeProfil.get().getCode()+"'", ENTITY_NAME, "Le code '"+verifCodeProfil.get().getCode()+"' est déjà utilisé par le profil  : '"+verifCodeProfil.get().getLibelle()+"'.");
         }
        }*/

        /* // Vérif Validité code forfait pour la structure connectée
        if (profils.getLibelle() == null || profils.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else{
                 // Vérif Unicité libelle forfait pour la structure connectée
         Optional<Profils> verifLibelleProfils = profilsServiceImpl.findByLibelle(profils.getLibelle());
         if (verifLibelleProfils.isPresent()) {
             throw new BadRequestAlertException("Le Nom '"+verifLibelleProfils.get().getLibelle()+"' est déjà utilisé  par un profil '", ENTITY_NAME, "Le libelle '"+verifLibelleProfils.get().getLibelle()+"' est déjà utilisé . '");
         }
        } */

        Profils result = profilsRepository.save(profils);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profils.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /profils/:id} : Partial updates given fields of an existing profils, field will ignore if it is null
     *
     * @param id the id of the profils to save.
     * @param profils the profils to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profils,
     * or with status {@code 400 (Bad Request)} if the profils is not valid,
     * or with status {@code 404 (Not Found)} if the profils is not found,
     * or with status {@code 500 (Internal Server Error)} if the profils couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/profils/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Profils> partialUpdateProfils(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Profils profils
    ) throws URISyntaxException {
        log.debug("REST request to partial update Profils partially : {}, {}", id, profils);
        if (profils.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, profils.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!profilsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Profils> result = profilsRepository
            .findById(profils.getId())
            .map(existingProfils -> {
                if (profils.getCode() != null) {
                    existingProfils.setCode(profils.getCode());
                }
                if (profils.getLibelle() != null) {
                    existingProfils.setLibelle(profils.getLibelle());
                }
                if (profils.getDescription() != null) {
                    existingProfils.setDescription(profils.getDescription());
                }

                return existingProfils;
            })
            .map(profilsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profils.getId().toString())
        );
    }

    /**
     * {@code GET  /profils} : get all the profils.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profils in body.
     */
    @GetMapping("/profils")
    public ResponseEntity<List<Profils>> getAllProfils(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Profils");
        Page<Profils> page;
        if (eagerload) {
            page = profilsRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = profilsRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /profils/:id} : get the "id" profils.
     *
     * @param id the id of the profils to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profils, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profils/{id}")
    public ResponseEntity<Profils> getProfils(@PathVariable Long id) {
        log.debug("REST request to get Profils : {}", id);
        Optional<Profils> profils = profilsRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(profils);
    }

    /**
     * {@code DELETE  /profils/:id} : delete the "id" profils.
     *
     * @param id the id of the profils to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profils/{id}")
    public ResponseEntity<Void> deleteProfils(@PathVariable Long id) {
        log.debug("REST request to delete Profils : {}", id);
        profilsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /profils/recherchemotsclés} : get the profils by mots clés.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of profils in body.
     */
    @GetMapping("/profils/recherchemotsclés")
    public ResponseEntity<List<Profils>> getProfilsByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of profils");
        Page<Profils> page = profilsRepository.rechercheProfilsByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
