package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.TypeLocalite;
import com.sn.dtai.admin.repository.TypeLocaliteRepository;
import com.sn.dtai.admin.service.dto.TypeLocaliteDTO;
import com.sn.dtai.admin.service.impl.TypeLocaliteServiceImpl;
import com.sn.dtai.admin.service.mapper.TypeLocaliteMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.TypeLocalite}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TypeLocaliteResource {

    private final Logger log = LoggerFactory.getLogger(TypeLocaliteResource.class);

    private static final String ENTITY_NAME = "adminTypeLocalite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeLocaliteRepository typeLocaliteRepository;

    private final TypeLocaliteServiceImpl typeLocaliteServiceImpl;
    
    private final TypeLocaliteMapper typeLocaliteMapper;

    public TypeLocaliteResource(TypeLocaliteRepository typeLocaliteRepository, TypeLocaliteServiceImpl typeLocaliteServiceImpl, TypeLocaliteMapper typeLocaliteMapper) {
        this.typeLocaliteRepository = typeLocaliteRepository;
        this.typeLocaliteServiceImpl = typeLocaliteServiceImpl;
        this.typeLocaliteMapper = typeLocaliteMapper;
    }

    /**
     * {@code POST  /type-localites} : Create a new typeLocalite.
     *
     * @param typeLocalite the typeLocalite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeLocalite, or with status {@code 400 (Bad Request)} if the typeLocalite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-localites")
    public ResponseEntity<TypeLocalite> createTypeLocalite(@Valid @RequestBody TypeLocalite typeLocalite) throws URISyntaxException {
        log.debug("REST request to save TypeLocalite : {}", typeLocalite);
        if (typeLocalite.getId() != null) {
            throw new BadRequestAlertException("A new typeLocalite cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code applicatoins
        if (typeLocalite.getCode() == null || typeLocalite.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code moduless
            Optional<TypeLocalite> verifCodeTypeLocalite = typeLocaliteServiceImpl.findByCode(typeLocalite.getCode());
            if (verifCodeTypeLocalite.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + typeLocalite.getCode() + "' est déjà utilisé : '" + verifCodeTypeLocalite.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodeTypeLocalite.get().getCode() + "' est déjà utilisé  : '."
                );
            }
        }

        // Vérif Validité code typeLocalite pour la structure connectée
        if (typeLocalite.getLibelle() == null || typeLocalite.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle typeLocalite
            Optional<TypeLocalite> verifLibelleTyepLocalite = typeLocaliteServiceImpl.findByLibelle(typeLocalite.getLibelle());
            if (verifLibelleTyepLocalite.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleTyepLocalite.get().getLibelle() + "' est déjà utilisé'",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleTyepLocalite.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        TypeLocalite result = typeLocaliteRepository.save(typeLocalite);
        return ResponseEntity
            .created(new URI("/api/type-localites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-localites/:id} : Updates an existing typeLocalite.
     *
     * @param id the id of the typeLocalite to save.
     * @param typeLocalite the typeLocalite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeLocalite,
     * or with status {@code 400 (Bad Request)} if the typeLocalite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeLocalite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-localites/{id}")
    public ResponseEntity<TypeLocalite> updateTypeLocalite(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TypeLocalite typeLocalite
    ) throws URISyntaxException {
        log.debug("REST request to update TypeLocalite : {}, {}", id, typeLocalite);
        if (typeLocalite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeLocalite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeLocaliteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TypeLocalite result = typeLocaliteRepository.save(typeLocalite);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeLocalite.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /type-localites/:id} : Partial updates given fields of an existing typeLocalite, field will ignore if it is null
     *
     * @param id the id of the typeLocalite to save.
     * @param typeLocalite the typeLocalite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeLocalite,
     * or with status {@code 400 (Bad Request)} if the typeLocalite is not valid,
     * or with status {@code 404 (Not Found)} if the typeLocalite is not found,
     * or with status {@code 500 (Internal Server Error)} if the typeLocalite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/type-localites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypeLocalite> partialUpdateTypeLocalite(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TypeLocalite typeLocalite
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypeLocalite partially : {}, {}", id, typeLocalite);
        if (typeLocalite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeLocalite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeLocaliteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypeLocalite> result = typeLocaliteRepository
            .findById(typeLocalite.getId())
            .map(existingTypeLocalite -> {
                if (typeLocalite.getCode() != null) {
                    existingTypeLocalite.setCode(typeLocalite.getCode());
                }
                if (typeLocalite.getLibelle() != null) {
                    existingTypeLocalite.setLibelle(typeLocalite.getLibelle());
                }
                if (typeLocalite.getDescription() != null) {
                    existingTypeLocalite.setDescription(typeLocalite.getDescription());
                }

                return existingTypeLocalite;
            })
            .map(typeLocaliteRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeLocalite.getId().toString())
        );
    }

    /**
     * {@code GET  /type-localites} : get all the typeLocalites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeLocalites in body.
     */
    @GetMapping("/type-localites")
    public ResponseEntity<List<TypeLocalite>> getAllTypeLocalites(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TypeLocalites");
        Page<TypeLocalite> page = typeLocaliteRepository.findAllTypeLocalite(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-localites/:id} : get the "id" typeLocalite.
     *
     * @param id the id of the typeLocalite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeLocalite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-localites/{id}")
    public ResponseEntity<TypeLocalite> getTypeLocalite(@PathVariable Long id) {
        log.debug("REST request to get TypeLocalite : {}", id);
        Optional<TypeLocalite> typeLocalite = typeLocaliteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeLocalite);
    }

    /**
     * {@code DELETE  /type-localites/:id} : delete the "id" typeLocalite.
     *
     * @param id the id of the typeLocalite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-localites/{id}")
    public ResponseEntity<Void> deleteTypeLocalite(@PathVariable Long id) {
        log.debug("REST request to delete TypeLocalite : {}", id);

        List<TypeLocaliteDTO> TypeLocalites= typeLocaliteRepository.getLocalitesByTypeLocalite(id).stream()
        .map(typeLocaliteMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (TypeLocalites.size()>0) {
            throw new BadRequestAlertException("Ce type de Localite est utilisé par une Localité.", ENTITY_NAME, "Ce type de Localite est utilisé par une Localité.");
        }
        typeLocaliteRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /type-localites/recherchemotsclés} : get the type-localites by mots clés.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of type-localites in body.
     */
    @GetMapping("/type-localites/recherchemotsclés")
    public ResponseEntity<List<TypeLocalite>> getTypesLocalitesByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of types localités");
        Page<TypeLocalite> page = typeLocaliteRepository.rechercheTypeLocaliteByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
