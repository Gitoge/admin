package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.StructureAdmin;
import com.sn.dtai.admin.repository.StructureAdminRepository;
import com.sn.dtai.admin.service.impl.StructureAdminServiceImpl;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.StructureAdmin}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StructureAdminResource {

    private final Logger log = LoggerFactory.getLogger(StructureAdminResource.class);

    private static final String ENTITY_NAME = "adminStructureAdmin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StructureAdminRepository structureAdminRepository;

    private final StructureAdminServiceImpl structureAdminServiceImpl;

    public StructureAdminResource(StructureAdminRepository structureAdminRepository,StructureAdminServiceImpl structureAdminServiceImpl) {
        this.structureAdminRepository = structureAdminRepository;
        this.structureAdminServiceImpl = structureAdminServiceImpl;
    }

    /**
     * {@code POST  /structure-admins} : Create a new structureAdmin.
     *
     * @param structureAdmin the structureAdmin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new structureAdmin, or with status {@code 400 (Bad Request)} if the structureAdmin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/structure-admins")
    public ResponseEntity<StructureAdmin> createStructureAdmin(@RequestBody StructureAdmin structureAdmin) throws URISyntaxException {
        log.debug("REST request to save StructureAdmin : {}", structureAdmin);
        if (structureAdmin.getId() != null) {
            throw new BadRequestAlertException("A new structureAdmin cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code applicatoins
        if (structureAdmin.getCode() == null ||  structureAdmin.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
             // Vérif Unicité code moduless 
         Optional<StructureAdmin> verifCodestructureAdmin = structureAdminServiceImpl.findByCode(structureAdmin.getCode());
         if (verifCodestructureAdmin.isPresent()) {
             throw new BadRequestAlertException("Le code '"+structureAdmin.getCode()+"' est déjà utilisé : '"+verifCodestructureAdmin.get().getCode()+"'", ENTITY_NAME, "Le code '"+verifCodestructureAdmin.get().getCode()+"' est déjà utilisé  : '.");
         }
        }

        // Vérif Validité code typeLocalite pour la structure connectée
        if (structureAdmin.getLibelle() == null || structureAdmin.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else{
                 // Vérif Unicité libelle typeLocalite
         Optional<StructureAdmin> verifLibelleStructureAdmin = structureAdminServiceImpl.findByLibelle(structureAdmin.getLibelle());
         if (verifLibelleStructureAdmin.isPresent()) {
             throw new BadRequestAlertException("Le Libelle '"+verifLibelleStructureAdmin.get().getLibelle()+"' est déjà utilisé'", ENTITY_NAME, "Le libelle '"+verifLibelleStructureAdmin.get().getLibelle()+"' est déjà utilisé . '");
         }
        }

        StructureAdmin result = structureAdminRepository.save(structureAdmin);
        return ResponseEntity
            .created(new URI("/api/structure-admins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /structure-admins/:id} : Updates an existing structureAdmin.
     *
     * @param id the id of the structureAdmin to save.
     * @param structureAdmin the structureAdmin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structureAdmin,
     * or with status {@code 400 (Bad Request)} if the structureAdmin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the structureAdmin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/structure-admins/{id}")
    public ResponseEntity<StructureAdmin> updateStructureAdmin(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StructureAdmin structureAdmin
    ) throws URISyntaxException {
        log.debug("REST request to update StructureAdmin : {}, {}", id, structureAdmin);
        if (structureAdmin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structureAdmin.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structureAdminRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StructureAdmin result = structureAdminRepository.save(structureAdmin);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, structureAdmin.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /structure-admins/:id} : Partial updates given fields of an existing structureAdmin, field will ignore if it is null
     *
     * @param id the id of the structureAdmin to save.
     * @param structureAdmin the structureAdmin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structureAdmin,
     * or with status {@code 400 (Bad Request)} if the structureAdmin is not valid,
     * or with status {@code 404 (Not Found)} if the structureAdmin is not found,
     * or with status {@code 500 (Internal Server Error)} if the structureAdmin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/structure-admins/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StructureAdmin> partialUpdateStructureAdmin(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StructureAdmin structureAdmin
    ) throws URISyntaxException {
        log.debug("REST request to partial update StructureAdmin partially : {}, {}", id, structureAdmin);
        if (structureAdmin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, structureAdmin.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!structureAdminRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StructureAdmin> result = structureAdminRepository
            .findById(structureAdmin.getId())
            .map(existingStructureAdmin -> {
                if (structureAdmin.getCode() != null) {
                    existingStructureAdmin.setCode(structureAdmin.getCode());
                }
                if (structureAdmin.getLibelle() != null) {
                    existingStructureAdmin.setLibelle(structureAdmin.getLibelle());
                }
                if (structureAdmin.getDescription() != null) {
                    existingStructureAdmin.setDescription(structureAdmin.getDescription());
                }
                if (structureAdmin.getDirection() != null) {
                    existingStructureAdmin.setDirection(structureAdmin.getDirection());
                }
                if (structureAdmin.getDepartementService() != null) {
                    existingStructureAdmin.setDepartementService(structureAdmin.getDepartementService());
                }
                if (structureAdmin.getAdresse() != null) {
                    existingStructureAdmin.setAdresse(structureAdmin.getAdresse());
                }

                return existingStructureAdmin;
            })
            .map(structureAdminRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, structureAdmin.getId().toString())
        );
    }

    /**
     * {@code GET  /structure-admins} : get all the structureAdmins.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of structureAdmins in body.
     */
    @GetMapping("/structure-admins")
    public  ResponseEntity<List<StructureAdmin>> getAllStructureAdmins(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get all StructureAdmins");
        Page<StructureAdmin> page = structureAdminRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /structure-admins/:id} : get the "id" structureAdmin.
     *
     * @param id the id of the structureAdmin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the structureAdmin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/structure-admins/{id}")
    public ResponseEntity<StructureAdmin> getStructureAdmin(@PathVariable Long id) {
        log.debug("REST request to get StructureAdmin : {}", id);
        Optional<StructureAdmin> structureAdmin = structureAdminRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(structureAdmin);
    }

    /**
     * {@code DELETE  /structure-admins/:id} : delete the "id" structureAdmin.
     *
     * @param id the id of the structureAdmin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/structure-admins/{id}")
    public ResponseEntity<Void> deleteStructureAdmin(@PathVariable Long id) {
        log.debug("REST request to delete StructureAdmin : {}", id);
        structureAdminRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
