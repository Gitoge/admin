package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.TypeEtablissement;
import com.sn.dtai.admin.repository.TypeEtablissementRepository;
import com.sn.dtai.admin.service.dto.TypeEtablissementDTO;
import com.sn.dtai.admin.service.impl.TypeEtablissementServiceImpl;
import com.sn.dtai.admin.service.mapper.TypeEtablissementMapper;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.TypeEtablissement}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TypeEtablissementResource {

    private final Logger log = LoggerFactory.getLogger(TypeEtablissementResource.class);

    private static final String ENTITY_NAME = "adminTypeEtablissement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeEtablissementRepository typeEtablissementRepository;

    private final TypeEtablissementServiceImpl typeEtablissementServiceImpl;
    
    private final TypeEtablissementMapper typeEtablissementMapper;

    public TypeEtablissementResource(
        TypeEtablissementRepository typeEtablissementRepository,
        TypeEtablissementServiceImpl typeEtablissementServiceImpl,
        TypeEtablissementMapper typeEtablissementMapper
    ) {
        this.typeEtablissementRepository = typeEtablissementRepository;
        this.typeEtablissementServiceImpl = typeEtablissementServiceImpl;
        this.typeEtablissementMapper = typeEtablissementMapper;
    }

    /**
     * {@code POST  /type-etablissements} : Create a new typeEtablissement.
     *
     * @param typeEtablissement the typeEtablissement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeEtablissement, or with status {@code 400 (Bad Request)} if the typeEtablissement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-etablissements")
    public ResponseEntity<TypeEtablissement> createTypeEtablissement(@Valid @RequestBody TypeEtablissement typeEtablissement)
        throws URISyntaxException {
        log.debug("REST request to save TypeEtablissement : {}", typeEtablissement);
        if (typeEtablissement.getId() != null) {
            throw new BadRequestAlertException("A new typeEtablissement cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code typeEtablissement
        if (typeEtablissement.getCode() == null || typeEtablissement.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code type etablissement
            Optional<TypeEtablissement> verifCodeTypeEtablissement = typeEtablissementServiceImpl.findByCode(typeEtablissement.getCode());
            if (verifCodeTypeEtablissement.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + typeEtablissement.getCode() + "' est déjà utilisé : '" + verifCodeTypeEtablissement.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodeTypeEtablissement.get().getCode() + "' est déjà utilisé  : '."
                );
            }
        }

        // Vérif Validité code typeEtablissement pour la structure connectée
        if (typeEtablissement.getLibelle() == null || typeEtablissement.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle typeEtablissement
            Optional<TypeEtablissement> verifLibelleTypeEtablissement = typeEtablissementServiceImpl.findByLibelle(
                typeEtablissement.getLibelle()
            );
            if (verifLibelleTypeEtablissement.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleTypeEtablissement.get().getLibelle() + "' est déjà utilisé'",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleTypeEtablissement.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        TypeEtablissement result = typeEtablissementRepository.save(typeEtablissement);
        return ResponseEntity
            .created(new URI("/api/type-etablissements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-etablissements/:id} : Updates an existing typeEtablissement.
     *
     * @param id the id of the typeEtablissement to save.
     * @param typeEtablissement the typeEtablissement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeEtablissement,
     * or with status {@code 400 (Bad Request)} if the typeEtablissement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeEtablissement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-etablissements/{id}")
    public ResponseEntity<TypeEtablissement> updateTypeEtablissement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TypeEtablissement typeEtablissement
    ) throws URISyntaxException {
        log.debug("REST request to update TypeEtablissement : {}, {}", id, typeEtablissement);
        if (typeEtablissement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeEtablissement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeEtablissementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TypeEtablissement result = typeEtablissementRepository.save(typeEtablissement);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeEtablissement.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /type-etablissements/:id} : Partial updates given fields of an existing typeEtablissement, field will ignore if it is null
     *
     * @param id the id of the typeEtablissement to save.
     * @param typeEtablissement the typeEtablissement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeEtablissement,
     * or with status {@code 400 (Bad Request)} if the typeEtablissement is not valid,
     * or with status {@code 404 (Not Found)} if the typeEtablissement is not found,
     * or with status {@code 500 (Internal Server Error)} if the typeEtablissement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/type-etablissements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypeEtablissement> partialUpdateTypeEtablissement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TypeEtablissement typeEtablissement
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypeEtablissement partially : {}, {}", id, typeEtablissement);
        if (typeEtablissement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeEtablissement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeEtablissementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypeEtablissement> result = typeEtablissementRepository
            .findById(typeEtablissement.getId())
            .map(existingTypeEtablissement -> {
                if (typeEtablissement.getCode() != null) {
                    existingTypeEtablissement.setCode(typeEtablissement.getCode());
                }
                if (typeEtablissement.getLibelle() != null) {
                    existingTypeEtablissement.setLibelle(typeEtablissement.getLibelle());
                }
                if (typeEtablissement.getDescription() != null) {
                    existingTypeEtablissement.setDescription(typeEtablissement.getDescription());
                }

                return existingTypeEtablissement;
            })
            .map(typeEtablissementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeEtablissement.getId().toString())
        );
    }

    /**
     * {@code GET  /type-etablissements} : get all the typeEtablissements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeEtablissements in body.
     */
    @GetMapping("/type-etablissements")
    public ResponseEntity<List<TypeEtablissement>> getAllTypeEtablissements(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TypeEtablissements");
        Page<TypeEtablissement> page = typeEtablissementRepository.findAllTypeEtablissement(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /type-etablissements/:id} : get the "id" typeEtablissement.
     *
     * @param id the id of the typeEtablissement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeEtablissement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-etablissements/{id}")
    public ResponseEntity<TypeEtablissement> getTypeEtablissement(@PathVariable Long id) {
        log.debug("REST request to get TypeEtablissement : {}", id);
        Optional<TypeEtablissement> typeEtablissement = typeEtablissementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeEtablissement);
    }

    /**
     * {@code DELETE  /type-etablissements/:id} : delete the "id" typeEtablissement.
     *
     * @param id the id of the typeEtablissement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-etablissements/{id}")
    public ResponseEntity<Void> deleteTypeEtablissement(@PathVariable Long id) {
        log.debug("REST request to delete TypeEtablissement : {}", id);
        List<TypeEtablissementDTO> typeEtablissements= typeEtablissementRepository.getEtablissementsByTypeEtablissement(id).stream()
        .map(typeEtablissementMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (typeEtablissements.size()>0) {
            throw new BadRequestAlertException("Ce type d'établissement est utilisé par un établissement.", ENTITY_NAME, "Ce type d'établissement est utilisé par un établissement.");
        }
        typeEtablissementRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /type-etablissements/recherchemotsclés} : get the type-etablissements by mots clés.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of type-etablissements in body.
     */
    @GetMapping("/type-etablissements/recherchemotsclés")
    public ResponseEntity<List<TypeEtablissement>> getTypesEtablissementsByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of types établissemnts");
        Page<TypeEtablissement> page = typeEtablissementRepository.rechercheTypeEtablissementByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
