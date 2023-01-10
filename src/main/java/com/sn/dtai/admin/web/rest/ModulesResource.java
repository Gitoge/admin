package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Modules;
import com.sn.dtai.admin.repository.ModulesRepository;
import com.sn.dtai.admin.service.impl.ModulesServiceImpl;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Modules}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ModulesResource {

    private final Logger log = LoggerFactory.getLogger(ModulesResource.class);

    private static final String ENTITY_NAME = "adminModules";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModulesRepository modulesRepository;

    private final ModulesServiceImpl modulesServiceImpl;

    /*private final ModulesService modulesService;*/

    public ModulesResource(ModulesRepository modulesRepository, ModulesServiceImpl modulesServiceImpl) {
        this.modulesRepository = modulesRepository;
        this.modulesServiceImpl = modulesServiceImpl;
    }

    /**
     * {@code POST  /modules} : Create a new modules.
     *
     * @param modules the modules to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modules, or with status {@code 400 (Bad Request)} if the modules has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modules")
    public ResponseEntity<Modules> createModules(@Valid @RequestBody Modules modules) throws URISyntaxException {
        log.debug("REST request to save Modules : {}", modules);
        if (modules.getId() != null) {
            throw new BadRequestAlertException("A new modules cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code applicatoins
        if (modules.getCode() == null || modules.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code moduless
            Optional<Modules> verifCodeModules = modulesServiceImpl.findByCode(modules.getCode());
            if (verifCodeModules.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + modules.getCode() + "' est déjà utilisé : '" + verifCodeModules.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" +
                    verifCodeModules.get().getCode() +
                    "' est déjà utilisé par l'modules  : '" +
                    verifCodeModules.get().getLibelle() +
                    "'."
                );
            }
        }

        // Vérif Validité code forfait pour la structure connectée
        if (modules.getLibelle() == null || modules.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle forfait pour la structure connectée
            Optional<Modules> verifLibelleModules = modulesServiceImpl.findByLibelle(modules.getLibelle());
            if (verifLibelleModules.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleModules.get().getLibelle() + "' est déjà utilisé  par une module '",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleModules.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        Modules result = modulesRepository.save(modules);
        return ResponseEntity
            .created(new URI("/api/modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modules/:id} : Updates an existing modules.
     *
     * @param id the id of the modules to save.
     * @param modules the modules to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modules,
     * or with status {@code 400 (Bad Request)} if the modules is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modules couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modules/{id}")
    public ResponseEntity<Modules> updateModules(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Modules modules
    ) throws URISyntaxException {
        log.debug("REST request to update Modules : {}, {}", id, modules);
        if (modules.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, modules.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!modulesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Modules result = modulesRepository.save(modules);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modules.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /modules/:id} : Partial updates given fields of an existing modules, field will ignore if it is null
     *
     * @param id the id of the modules to save.
     * @param modules the modules to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modules,
     * or with status {@code 400 (Bad Request)} if the modules is not valid,
     * or with status {@code 404 (Not Found)} if the modules is not found,
     * or with status {@code 500 (Internal Server Error)} if the modules couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/modules/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Modules> partialUpdateModules(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Modules modules
    ) throws URISyntaxException {
        log.debug("REST request to partial update Modules partially : {}, {}", id, modules);
        if (modules.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, modules.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!modulesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Modules> result = modulesRepository
            .findById(modules.getId())
            .map(existingModules -> {
                if (modules.getCode() != null) {
                    existingModules.setCode(modules.getCode());
                }
                if (modules.getLibelle() != null) {
                    existingModules.setLibelle(modules.getLibelle());
                }
                if (modules.getDescription() != null) {
                    existingModules.setDescription(modules.getDescription());
                }
                if (modules.getIcon() != null) {
                    existingModules.setIcon(modules.getIcon());
                }
                if (modules.getOrdre() != null) {
                    existingModules.setOrdre(modules.getOrdre());
                }
                if (modules.getRouterLink() != null) {
                    existingModules.setRouterLink(modules.getRouterLink());
                }
                if (modules.getType() != null) {
                    existingModules.setType(modules.getType());
                }
                if (modules.getActive() != null) {
                    existingModules.setActive(modules.getActive());
                }

                return existingModules;
            })
            .map(modulesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modules.getId().toString())
        );
    }

    /**
     * {@code GET  /modules} : get all the modules.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modules in body.
     */
    @GetMapping("/modules")
    public ResponseEntity<List<Modules>> getAllModules(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Modules");
        Page<Modules> page = modulesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /modules/actifs} : get all the modules actifs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modules in body.
     */
    @GetMapping("/modules/actifs")
    public ResponseEntity<List<Modules>> getAllModulesActifs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Modules");
        Page<Modules> page = modulesRepository.findAllActifs(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /modules/:id} : get the "id" modules.
     *
     * @param id the id of the modules to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modules, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modules/{id}")
    public ResponseEntity<Modules> getModules(@PathVariable Long id) {
        log.debug("REST request to get Modules : {}", id);
        Optional<Modules> modules = modulesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(modules);
    }

    /**
     * {@code DELETE  /modules/:id} : delete the "id" modules.
     *
     * @param id the id of the modules to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Void> deleteModules(@PathVariable Long id) {
        log.debug("REST request to delete Modules : {}", id);
        modulesRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /modules/recherchemotsclés} : get the modules by mots clés.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of modules in body.
     */
    @GetMapping("/modules/recherchemotsclés")
    public ResponseEntity<List<Modules>> getModulesByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of modules");
        Page<Modules> page = modulesRepository.rechercheModulesByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
