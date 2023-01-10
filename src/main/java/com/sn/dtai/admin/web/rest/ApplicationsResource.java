package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Applications;
import com.sn.dtai.admin.repository.ApplicationsRepository;
import com.sn.dtai.admin.service.impl.ApplicationsServiceImpl;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Applications}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ApplicationsResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationsResource.class);

    private static final String ENTITY_NAME = "adminApplications";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApplicationsRepository applicationsRepository;

    private final ApplicationsServiceImpl applicationsServiceImpl;

    public ApplicationsResource(ApplicationsRepository applicationsRepository, ApplicationsServiceImpl applicationsServiceImpl) {
        this.applicationsRepository = applicationsRepository;
        this.applicationsServiceImpl = applicationsServiceImpl;
    }

    /**
     * {@code POST  /applications} : Create a new applications.
     *
     * @param applications the applications to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new applications, or with status {@code 400 (Bad Request)} if the applications has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/applications")
    public ResponseEntity<Applications> createApplications(@Valid @RequestBody Applications applications) throws URISyntaxException {
        log.debug("REST request to save Applications : {}", applications);
        if (applications.getId() != null) {
            throw new BadRequestAlertException("A new applications cannot already have an ID", ENTITY_NAME, "idexists");
        }
        // Vérif Validité code applicatoins
        if (applications.getCode() == null || applications.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code applications
            Optional<Applications> verifCodeApplication = applicationsServiceImpl.findByCode(applications.getCode());
            if (verifCodeApplication.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + applications.getCode() + "' est déjà utilisé : '" + verifCodeApplication.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" +
                    verifCodeApplication.get().getCode() +
                    "' est déjà utilisé par l'application  : '" +
                    verifCodeApplication.get().getNom() +
                    "'."
                );
            }
        }

        // Vérif Validité code forfait pour la structure connectée
        if (applications.getNom() == null || applications.getNom().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle forfait pour la structure connectée
            Optional<Applications> verifLibelleApplications = applicationsServiceImpl.findByNom(applications.getNom());
            if (verifLibelleApplications.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Nom '" + verifLibelleApplications.get().getNom() + "' est déjà utilisé  par une application '",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleApplications.get().getNom() + "' est déjà utilisé . '"
                );
            }
        }
        Applications result = applicationsRepository.save(applications);
        return ResponseEntity
            .created(new URI("/api/applications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /applications/:id} : Updates an existing applications.
     *
     * @param id the id of the applications to save.
     * @param applications the applications to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated applications,
     * or with status {@code 400 (Bad Request)} if the applications is not valid,
     * or with status {@code 500 (Internal Server Error)} if the applications couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/applications/{id}")
    public ResponseEntity<Applications> updateApplications(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Applications applications
    ) throws URISyntaxException {
        log.debug("REST request to update Applications : {}, {}", id, applications);
        if (applications.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, applications.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!applicationsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        /* // Vérif Validité code applicatoins
        if (applications.getCode() == null) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
             // Vérif Unicité code applications 
         Optional<Applications> verifCodeApplication = applicationsServiceImpl.findByCode(applications.getCode());
         if (verifCodeApplication.isPresent()) {
             throw new BadRequestAlertException("Le code '"+applications.getCode()+"' est déjà utilisé : '"+verifCodeApplication.get().getCode()+"'", ENTITY_NAME, "Le code '"+verifCodeApplication.get().getCode()+"' est déjà utilisé par l'application  : '"+verifCodeApplication.get().getNom()+"'.");
         }
        }

        // Vérif Validité code forfait pour la structure connectée
        if (applications.getNom() == null) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else{
                 // Vérif Unicité libelle forfait pour la structure connectée
         Optional<Applications> verifLibelleApplications = applicationsServiceImpl.findByNom(applications.getNom());
         if (verifLibelleApplications.isPresent()) {
             throw new BadRequestAlertException("Le Nom '"+verifLibelleApplications.get().getNom()+"' est déjà utilisé  par une application '", ENTITY_NAME, "Le libelle '"+verifLibelleApplications.get().getNom()+"' est déjà utilisé . '");
         }
        }
         */

        Applications result = applicationsRepository.save(applications);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applications.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /applications/:id} : Partial updates given fields of an existing applications, field will ignore if it is null
     *
     * @param id the id of the applications to save.
     * @param applications the applications to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated applications,
     * or with status {@code 400 (Bad Request)} if the applications is not valid,
     * or with status {@code 404 (Not Found)} if the applications is not found,
     * or with status {@code 500 (Internal Server Error)} if the applications couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/applications/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Applications> partialUpdateApplications(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Applications applications
    ) throws URISyntaxException {
        log.debug("REST request to partial update Applications partially : {}, {}", id, applications);
        if (applications.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, applications.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!applicationsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Applications> result = applicationsRepository
            .findById(applications.getId())
            .map(existingApplications -> {
                if (applications.getCode() != null) {
                    existingApplications.setCode(applications.getCode());
                }
                if (applications.getNom() != null) {
                    existingApplications.setNom(applications.getNom());
                }
                if (applications.getDescription() != null) {
                    existingApplications.setDescription(applications.getDescription());
                }

                return existingApplications;
            })
            .map(applicationsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applications.getId().toString())
        );
    }

    /**
     * {@code GET  /modules} : get all the applications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of applications in body.
     */
    @GetMapping("/applications")
    public ResponseEntity<List<Applications>> getAllApplications(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get all Applications");
        Page<Applications> page = applicationsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        // return applicationsRepository.findAll();
    }

    /**
     * {@code GET  /applications/:id} : get the "id" applications.
     *
     * @param id the id of the applications to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the applications, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/applications/{id}")
    public ResponseEntity<Applications> getApplications(@PathVariable Long id) {
        log.debug("REST request to get Applications : {}", id);
        try {
            Optional<Applications> applications = applicationsRepository.findById(id);
            return ResponseUtil.wrapOrNotFound(applications);
        } catch (Exception e) {
            throw new BadRequestAlertException("A Introuvable", ENTITY_NAME, "Affectation Introuvable");
        }
    }

    /**
     * {@code DELETE  /applications/:id} : delete the "id" applications.
     *
     * @param id the id of the applications to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplications(@PathVariable Long id) throws Exception {
        log.debug("REST request to delete Applications : {}", id);
        try {
            applicationsRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestAlertException(
                e.getMessage(),
                ENTITY_NAME,
                "Echec Suppression : Vous ne pouvez pas supprimer ce Application"
            );
        }

        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
