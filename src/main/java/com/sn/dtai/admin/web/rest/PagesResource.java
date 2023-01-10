package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.domain.Pages;
import com.sn.dtai.admin.repository.PagesRepository;
import com.sn.dtai.admin.service.PageActionService;
import com.sn.dtai.admin.service.PagesService;
import com.sn.dtai.admin.service.dto.ActionsDTO;
import com.sn.dtai.admin.service.dto.PageActionDTO;
import com.sn.dtai.admin.service.dto.PagesDTO;
import com.sn.dtai.admin.service.impl.PagesServiceImpl;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Pages}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PagesResource {

    private final Logger log = LoggerFactory.getLogger(PagesResource.class);

    private static final String ENTITY_NAME = "adminPages";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PagesRepository pagesRepository;

    private final PagesServiceImpl pagesServiceImpl;

    private final PagesService pagesService;

    private final PageActionService pageActionService;

    public PagesResource(
        PagesRepository pagesRepository,
        PagesServiceImpl pagesServiceImpl,
        PagesService pagesService,
        PageActionService pageActionService
    ) {
        this.pagesRepository = pagesRepository;
        this.pagesServiceImpl = pagesServiceImpl;
        this.pageActionService = pageActionService;
        this.pagesService = pagesService;
    }

    /**
     * {@code POST  /pages} : Create a new pages.
     *
     * @param pages the pages to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pages, or with status {@code 400 (Bad Request)} if the pages has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pages")
    public ResponseEntity<Pages> createPages(@Valid @RequestBody Pages pages) throws URISyntaxException {
        log.debug("REST request to save Pages : {}", pages);
        if (pages.getId() != null) {
            throw new BadRequestAlertException("A new pages cannot already have an ID", ENTITY_NAME, "idexists");
        }
        // Vérif Validité code pages
        if (pages.getCode() == null || pages.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code moduless
            Optional<Pages> verifCodePages = pagesServiceImpl.findByCode(pages.getCode());
            if (verifCodePages.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + pages.getCode() + "' est déjà utilisé : '" + verifCodePages.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodePages.get().getCode() + "' est déjà utilisé'."
                );
            }
        }

        // Vérif Validité code pages pour la structure connectée
        if (pages.getLibelle() == null || pages.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle roles
            Optional<Pages> verifLibelleRoles = pagesServiceImpl.findByLibelle(pages.getLibelle());
            if (verifLibelleRoles.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleRoles.get().getLibelle() + "' est déjà utilisé  '",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleRoles.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        Pages result = pagesRepository.save(pages);
        return ResponseEntity
            .created(new URI("/api/pages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pages/:id} : Updates an existing pages.
     *
     * @param id the id of the pages to save.
     * @param pages the pages to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pages,
     * or with status {@code 400 (Bad Request)} if the pages is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pages couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pages/{id}")
    public ResponseEntity<Pages> updatePages(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Pages pages)
        throws URISyntaxException {
        log.debug("REST request to update Pages : {}, {}", id, pages);
        if (pages.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pages.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        //    // Vérif Validité code applicatoins
        //    if (pages.getCode() == null ||  pages.getCode().trim().equals("")) {
        //     throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        // } else {
        //      // Vérif Unicité code moduless
        //  Optional<Pages> verifCodePages = pagesServiceImpl.findByCode(pages.getCode());
        //  if (verifCodePages.isPresent()) {
        //      throw new BadRequestAlertException("Le code '"+pages.getCode()+"' est déjà utilisé : '"+verifCodePages.get().getCode()+"'", ENTITY_NAME, "Le code '"+verifCodePages.get().getCode()+"' est déjà utilisé'.");
        //  }
        // }

        // // Vérif Validité code pages pour la structure connectée
        // if (pages.getLibelle() == null || pages.getLibelle().trim().equals("")) {
        //     throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        // } else{
        //          // Vérif Unicité libelle roles
        //  Optional<Pages> verifLibelleRoles = pagesServiceImpl.findByLibelle(pages.getLibelle());
        //  if (verifLibelleRoles.isPresent()) {
        //      throw new BadRequestAlertException("Le Libelle '"+verifLibelleRoles.get().getLibelle()+"' est déjà utilisé  '", ENTITY_NAME, "Le libelle '"+verifLibelleRoles.get().getLibelle()+"' est déjà utilisé . '");
        //  }
        // }

        Pages result = pagesRepository.save(pages);

        //Suppression des anciennes enregistrement
        pageActionService.deleteAllByPage(pages.getId());
        // Enregistrement dans la table page action
        PageActionDTO pageActionDTO;
        ActionsDTO actionDTO;
        PagesDTO pageDTO;
        for (Actions actions : pages.getActions()) {
            pageActionDTO = new PageActionDTO();
            pageDTO = new PagesDTO();
            pageDTO.setId(pages.getId());
            pageActionDTO.setPages(pageDTO);
            actionDTO = new ActionsDTO();
            actionDTO.setId(actions.getId());
            pageActionDTO.setActions(actionDTO);
            pageActionDTO.setEtat(true);
            pageActionService.save(pageActionDTO);
        }
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pages.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /pages/:id} : Partial updates given fields of an existing pages, field will ignore if it is null
     *
     * @param id the id of the pages to save.
     * @param pages the pages to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pages,
     * or with status {@code 400 (Bad Request)} if the pages is not valid,
     * or with status {@code 404 (Not Found)} if the pages is not found,
     * or with status {@code 500 (Internal Server Error)} if the pages couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/pages/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Pages> partialUpdatePages(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Pages pages
    ) throws URISyntaxException {
        log.debug("REST request to partial update Pages partially : {}, {}", id, pages);
        if (pages.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pages.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Pages> result = pagesRepository
            .findById(pages.getId())
            .map(existingPages -> {
                if (pages.getCode() != null) {
                    existingPages.setCode(pages.getCode());
                }
                if (pages.getLibelle() != null) {
                    existingPages.setLibelle(pages.getLibelle());
                }
                if (pages.getDescription() != null) {
                    existingPages.setDescription(pages.getDescription());
                }

                return existingPages;
            })
            .map(pagesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pages.getId().toString())
        );
    }

    /**
     * {@code GET  /pages} : get all the pages.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pages in body.
     */
    @GetMapping("/pages")
    public ResponseEntity<List<Pages>> getAllPages(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Pages");
        Page<Pages> page;
        if (eagerload) {
            page = pagesRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = pagesRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pages/:id} : get the "id" pages.
     *
     * @param id the id of the pages to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pages, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pages/{id}")
    public ResponseEntity<Pages> getPages(@PathVariable Long id) {
        log.debug("REST request to get Pages : {}", id);
        Optional<Pages> pages = pagesRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(pages);
    }

    /**
     * {@code DELETE  /pages/:id} : delete the "id" pages.
     *
     * @param id the id of the pages to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pages/{id}")
    public ResponseEntity<Void> deletePages(@PathVariable Long id) {
        log.debug("REST request to delete Pages : {}", id);
        pagesRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/pages/modules/{id}")
    public ResponseEntity<List<Pages>> getPagesByModules(@PathVariable Long id) {
        log.debug("/pages/modules : {}", id);
        List<Pages> pages = pagesRepository.findByModulesIdOrderByOrdre(id);
        return ResponseEntity.ok().body(pages);
    }

    /**
     * {@code GET  /pages/recherchemotsclés} : get the pages by mots clés.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of pages in body.
     */
    @GetMapping("/pages/recherchemotsclés")
    public ResponseEntity<List<Pages>> getPagesByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of pages");
        Page<Pages> page = pagesRepository.recherchePagesByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
