package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.repository.ActionsRepository;
import com.sn.dtai.admin.service.ActionsService;
import com.sn.dtai.admin.service.impl.ActionsServiceImpl;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Actions}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ActionsResource {

    private final Logger log = LoggerFactory.getLogger(ActionsResource.class);

    private static final String ENTITY_NAME = "adminActions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActionsRepository actionsRepository;

    private final ActionsServiceImpl actionsServiceImpl;

    private final ActionsService actionsService;

    public ActionsResource(ActionsRepository actionsRepository, ActionsServiceImpl actionsServiceImpl, ActionsService actionsService) {
        this.actionsRepository = actionsRepository;

        this.actionsServiceImpl = actionsServiceImpl;

        this.actionsService = actionsService;
    }

    /**
     * {@code POST  /actions} : Create a new actions.
     *
     * @param actions the actions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new actions, or with status {@code 400 (Bad Request)} if the actions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/actions")
    public ResponseEntity<Actions> createActions(@Valid @RequestBody Actions actions) throws URISyntaxException {
        log.debug("REST request to save Actions : {}", actions);
        if (actions.getId() != null) {
            throw new BadRequestAlertException("A new actions cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // V??rif Validit?? code applicatoins
        if (actions.getCode() == null || actions.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // V??rif Unicit?? code moduless
            Optional<Actions> verifCodePages = actionsServiceImpl.findByCode(actions.getCode());
            if (verifCodePages.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + actions.getCode() + "' est d??j?? utilis?? : '" + verifCodePages.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodePages.get().getCode() + "' est d??j?? utilis??'."
                );
            }
        }

        // V??rif Validit?? code pages pour la structure connect??e
        if (actions.getLibelle() == null || actions.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // V??rif Unicit?? libelle roles
            Optional<Actions> verifLibelleRoles = actionsServiceImpl.findByLibelle(actions.getLibelle());
            if (verifLibelleRoles.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleRoles.get().getLibelle() + "' est d??j?? utilis??  '",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleRoles.get().getLibelle() + "' est d??j?? utilis?? . '"
                );
            }
        }

        Actions result = actionsRepository.save(actions);
        return ResponseEntity
            .created(new URI("/api/actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /actions/:id} : Updates an existing actions.
     *
     * @param id the id of the actions to save.
     * @param actions the actions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated actions,
     * or with status {@code 400 (Bad Request)} if the actions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the actions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/actions/{id}")
    public ResponseEntity<Actions> updateActions(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Actions actions
    ) throws URISyntaxException {
        log.debug("REST request to update Actions : {}, {}", id, actions);
        if (actions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, actions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!actionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        /*// V??rif Validit?? code applicatoins
         if (actions.getCode() == null ||  actions.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
             // V??rif Unicit?? code moduless
         Optional<Actions> verifCodePages = actionsServiceImpl.findByCode(actions.getCode());
         if (verifCodePages.isPresent()) {
             throw new BadRequestAlertException("Le code '"+actions.getCode()+"' est d??j?? utilis?? : '"+verifCodePages.get().getCode()+"'", ENTITY_NAME, "Le code '"+verifCodePages.get().getCode()+"' est d??j?? utilis??'.");
         }
        }

        // V??rif Validit?? code pages pour la structure connect??e
        if (actions.getLibelle() == null || actions.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else{
                 // V??rif Unicit?? libelle roles
         Optional<Actions> verifLibelleRoles = actionsServiceImpl.findByLibelle(actions.getLibelle());
         if (verifLibelleRoles.isPresent()) {
             throw new BadRequestAlertException("Le Libelle '"+verifLibelleRoles.get().getLibelle()+"' est d??j?? utilis??  '", ENTITY_NAME, "Le libelle '"+verifLibelleRoles.get().getLibelle()+"' est d??j?? utilis?? . '");
         }
        }*/

        Actions result = actionsRepository.save(actions);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, actions.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /actions/:id} : Partial updates given fields of an existing actions, field will ignore if it is null
     *
     * @param id the id of the actions to save.
     * @param actions the actions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated actions,
     * or with status {@code 400 (Bad Request)} if the actions is not valid,
     * or with status {@code 404 (Not Found)} if the actions is not found,
     * or with status {@code 500 (Internal Server Error)} if the actions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/actions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Actions> partialUpdateActions(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Actions actions
    ) throws URISyntaxException {
        log.debug("REST request to partial update Actions partially : {}, {}", id, actions);
        if (actions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, actions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!actionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Actions> result = actionsRepository
            .findById(actions.getId())
            .map(existingActions -> {
                if (actions.getCode() != null) {
                    existingActions.setCode(actions.getCode());
                }
                if (actions.getLibelle() != null) {
                    existingActions.setLibelle(actions.getLibelle());
                }
                if (actions.getDescription() != null) {
                    existingActions.setDescription(actions.getDescription());
                }

                return existingActions;
            })
            .map(actionsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, actions.getId().toString())
        );
    }

    /**
     * {@code GET  /actions} : get all the actions.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of actions in body.
     */
    @GetMapping("/actions")
    public ResponseEntity<List<Actions>> getAllActions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Actions");
        Page<Actions> page;
        if (eagerload) {
            page = actionsRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = actionsRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /actions/:id} : get the "id" actions.
     *
     * @param id the id of the actions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the actions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/actions/{id}")
    public ResponseEntity<Actions> getActions(@PathVariable Long id) {
        log.debug("REST request to get Actions : {}", id);
        try {
            Optional<Actions> actions = actionsRepository.findOneWithEagerRelationships(id);
            return ResponseUtil.wrapOrNotFound(actions);
        } catch (Exception e) {
            throw new BadRequestAlertException("Actions Introuvable", ENTITY_NAME, "Actions Introuvable");
        }
    }

    /**
     * {@code DELETE  /actions/:id} : delete the "id" actions.
     *
     * @param id the id of the actions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/actions/{id}")
    public ResponseEntity<Void> deleteActions(@PathVariable Long id) {
        log.debug("REST request to delete Actions : {}", id);
        actionsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/actions/pages/{id}")
    public ResponseEntity<Set<Actions>> findByPagesId(@PathVariable Long id) {
        log.debug("REST request to get Actions by PageId: {}", id);
        Set<Actions> actions = actionsService.findByPagesId(id);
        return ResponseEntity.ok().body(actions);
    }

    /**
     * {@code GET  /actions/recherchemotscl??s} : get the actions by mots cl??s.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of actions in body.
     */
    @GetMapping("/actions/recherchemotscl??s")
    public ResponseEntity<List<Actions>> getActionsByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of actions");
        Page<Actions> page = actionsRepository.rechercheActionsByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
