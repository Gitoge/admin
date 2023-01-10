package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Classe;
import com.sn.dtai.admin.repository.ClasseRepository;
import com.sn.dtai.admin.service.impl.ClasseServiceImpl;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Classe}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ClasseResource {

    private final Logger log = LoggerFactory.getLogger(ClasseResource.class);

    private static final String ENTITY_NAME = "adminClasse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClasseRepository classeRepository;

    private final ClasseServiceImpl classeServiceImpl;

    public ClasseResource(ClasseRepository classeRepository, ClasseServiceImpl classeServiceImpl) {

        this.classeRepository = classeRepository;
        this.classeServiceImpl = classeServiceImpl;
    }

    /**
     * {@code POST  /classes} : Create a new classe.
     *
     * @param classe the classe to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new classe, or with status {@code 400 (Bad Request)} if the
     *         classe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classes")
    public ResponseEntity<Classe> createClasse(@Valid @RequestBody Classe classe) throws URISyntaxException {
        log.debug("REST request to save Classe : {}", classe);
        if (classe.getId() != null) {
            throw new BadRequestAlertException("A new classe cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code classe
        if (classe.getCode() == null || classe.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code classe
            Optional<Classe> verifCodeClasse = classeServiceImpl.findByCode(classe.getCode());
            if (verifCodeClasse.isPresent()) {
                throw new BadRequestAlertException(
                        "Le code '" + classe.getCode() + "' est déjà utilisé : '" + verifCodeClasse.get().getCode()
                                + "'",
                        ENTITY_NAME, "Le code '" + verifCodeClasse.get().getCode() + "' est déjà utilisé'.");
            }
        }

        // Vérif Validité code pages pour la structure connectée
        if (classe.getLibelle() == null || classe.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle roles
            Optional<Classe> verifLibelleClasse = classeServiceImpl.findByLibelle(classe.getLibelle());
            if (verifLibelleClasse.isPresent()) {
                throw new BadRequestAlertException(
                        "Le Libelle '" + verifLibelleClasse.get().getLibelle() + "' est déjà utilisé  '", ENTITY_NAME,
                        "Le libelle '" + verifLibelleClasse.get().getLibelle() + "' est déjà utilisé . '");
            }
        }

        Classe result = classeRepository.save(classe);
        return ResponseEntity
                .created(new URI("/api/classes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME,
                        result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /classes/:id} : Updates an existing classe.
     *
     * @param id     the id of the classe to save.
     * @param classe the classe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated classe,
     *         or with status {@code 400 (Bad Request)} if the classe is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the classe
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classes/{id}")
    public ResponseEntity<Classe> updateClasse(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody Classe classe) throws URISyntaxException {
        log.debug("REST request to update Classe : {}, {}", id, classe);
        if (classe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, classe.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!classeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Classe result = classeRepository.save(classe);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                        classe.getId().toString()))
                .body(result);
    }

    /**
     * {@code PATCH  /classes/:id} : Partial updates given fields of an existing
     * classe, field will ignore if it is null
     *
     * @param id     the id of the classe to save.
     * @param classe the classe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated classe,
     *         or with status {@code 400 (Bad Request)} if the classe is not valid,
     *         or with status {@code 404 (Not Found)} if the classe is not found,
     *         or with status {@code 500 (Internal Server Error)} if the classe
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/classes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Classe> partialUpdateClasse(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody Classe classe) throws URISyntaxException {
        log.debug("REST request to partial update Classe partially : {}, {}", id, classe);
        if (classe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, classe.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!classeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Classe> result = classeRepository
                .findById(classe.getId())
                .map(existingClasse -> {
                    if (classe.getCode() != null) {
                        existingClasse.setCode(classe.getCode());
                    }
                    if (classe.getLibelle() != null) {
                        existingClasse.setLibelle(classe.getLibelle());
                    }
                    if (classe.getDescription() != null) {
                        existingClasse.setDescription(classe.getDescription());
                    }

                    return existingClasse;
                })
                .map(classeRepository::save);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classe.getId().toString()));
    }

    /**
     * {@code GET  /classes} : get all the classes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of classes in body.
     */
    @GetMapping("/classes")
    public ResponseEntity<List<Classe>> getAllClasses(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Classes");
        Page<Classe> page = classeRepository.findAllClasse(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /classes/:id} : get the "id" classe.
     *
     * @param id the id of the classe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the classe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classes/{id}")
    public ResponseEntity<Classe> getClasse(@PathVariable Long id) {
        log.debug("REST request to get Classe : {}", id);
        Optional<Classe> classe = classeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(classe);
    }

    /**
     * {@code DELETE  /classes/:id} : delete the "id" classe.
     *
     * @param id the id of the classe to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        log.debug("REST request to delete Classe : {}", id);
        classeRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    /**
     * {@code GET  /classe} : get all the classe.
     *
     * sans pagination
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of classe in body.
     */
    @GetMapping("/classes/all")
    public List<Classe> getAllClasses() {
        return classeRepository.findAllClasse();
    }
}
