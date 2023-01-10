package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Direction;
import com.sn.dtai.admin.repository.DirectionRepository;
import com.sn.dtai.admin.service.DirectionService;
import com.sn.dtai.admin.service.dto.DirectionDTO;
import com.sn.dtai.admin.service.impl.DirectionServiceImpl;
import com.sn.dtai.admin.service.mapper.DirectionMapper;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Direction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DirectionResource {

    private final Logger log = LoggerFactory.getLogger(DirectionResource.class);

    private static final String ENTITY_NAME = "adminDirection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DirectionRepository directionRepository;

    private final DirectionServiceImpl directionServiceImpl;

    private final DirectionMapper directionMapper;

    public DirectionResource(
        DirectionRepository directionRepository,
        DirectionServiceImpl directionServiceImpl,
        DirectionMapper directionMapper
    ) {
        this.directionRepository = directionRepository;
        this.directionServiceImpl = directionServiceImpl;
        this.directionMapper = directionMapper;
    }

    /**
     * {@code POST  /directions} : Create a new direction.
     *
     * @param direction the direction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new direction, or with status {@code 400 (Bad Request)} if the direction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/directions")
    public ResponseEntity<Direction> createDirection(@Valid @RequestBody Direction direction) throws URISyntaxException {
        log.debug("REST request to save Direction : {}", direction);
        if (direction.getId() != null) {
            throw new BadRequestAlertException("A new direction cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code Direction
        if (direction.getCode() == null || direction.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code Direction
            Optional<Direction> verifCodeDirection = directionServiceImpl.findByCode(direction.getCode());
            if (verifCodeDirection.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + direction.getCode() + "' est déjà utilisé : '" + verifCodeDirection.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodeDirection.get().getCode() + "' est déjà utilisé  : '."
                );
            }
        }

        // Vérif Validité libelle Direction pour la structure connectée
        if (direction.getLibelle() == null || direction.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle Direction
            Optional<Direction> verifLibelleDirection = directionServiceImpl.findByLibelle(direction.getLibelle());
            if (verifLibelleDirection.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleDirection.get().getLibelle() + "' est déjà utilisé'",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleDirection.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        Direction result = directionRepository.save(direction);
        return ResponseEntity
            .created(new URI("/api/directions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /directions/:id} : Updates an existing direction.
     *
     * @param id the id of the direction to save.
     * @param direction the direction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated direction,
     * or with status {@code 400 (Bad Request)} if the direction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the direction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/directions/{id}")
    public ResponseEntity<Direction> updateDirection(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Direction direction
    ) throws URISyntaxException {
        log.debug("REST request to update Direction : {}, {}", id, direction);
        if (direction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, direction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!directionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Direction result = directionRepository.save(direction);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, direction.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /directions/:id} : Partial updates given fields of an existing direction, field will ignore if it is null
     *
     * @param id the id of the direction to save.
     * @param direction the direction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated direction,
     * or with status {@code 400 (Bad Request)} if the direction is not valid,
     * or with status {@code 404 (Not Found)} if the direction is not found,
     * or with status {@code 500 (Internal Server Error)} if the direction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/directions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Direction> partialUpdateDirection(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Direction direction
    ) throws URISyntaxException {
        log.debug("REST request to partial update Direction partially : {}, {}", id, direction);
        if (direction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, direction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!directionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Direction> result = directionRepository
            .findById(direction.getId())
            .map(existingDirection -> {
                if (direction.getCode() != null) {
                    existingDirection.setCode(direction.getCode());
                }
                if (direction.getLibelle() != null) {
                    existingDirection.setLibelle(direction.getLibelle());
                }
                if (direction.getAdresse() != null) {
                    existingDirection.setAdresse(direction.getAdresse());
                }
                if (direction.getNumTelephone() != null) {
                    existingDirection.setNumTelephone(direction.getNumTelephone());
                }
                if (direction.getFax() != null) {
                    existingDirection.setFax(direction.getFax());
                }
                if (direction.getEmail() != null) {
                    existingDirection.setEmail(direction.getEmail());
                }
                if (direction.getContact() != null) {
                    existingDirection.setContact(direction.getContact());
                }

                return existingDirection;
            })
            .map(directionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, direction.getId().toString())
        );
    }

    /**
     * {@code GET  /directions} : get all the directions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directions in body.
     */
    @GetMapping("/directions")
    public ResponseEntity<List<Direction>> getAllDirections(Pageable pageable) {
        log.debug("REST request to get a page of Directions");
        Page<Direction> page = directionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /directions/:id} : get the "id" direction.
     *
     * @param id the id of the direction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the direction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/directions/{id}")
    public ResponseEntity<Direction> getDirection(@PathVariable Long id) {
        log.debug("REST request to get Direction : {}", id);
        Optional<Direction> direction = directionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(direction);
    }

    /**
     * {@code DELETE  /directions/:id} : delete the "id" direction.
     *
     * @param id the id of the direction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/directions/{id}")
    public ResponseEntity<Void> deleteDirection(@PathVariable Long id) {
        log.debug("REST request to delete Direction : {}", id);


        List<DirectionDTO> directions= directionRepository.getDirectionsByService(id).stream()
        .map(directionMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));;
        if (directions.size()>0) {
            throw new BadRequestAlertException("Cette Direction est déjà utiliséE dans un service.", ENTITY_NAME, "Cette Direction est déjà utilisée dans un service.");
        }
        directionRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /directions} : get all the directions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of directions in body.
     */
    @GetMapping("/directions/all")
    public List<Direction> getAllDirection() {
        return directionRepository.findAllDirections();
    }

    /**
     * {@code GET  /directions/recherchemotsclés} : get the directions by mots clés.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of directions in body.
     */
    @GetMapping("/directions/recherchemotsclés")
    public ResponseEntity<List<Direction>> getDirectionsByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of directions");
        Page<Direction> page = directionRepository.rechercheDirectionByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
