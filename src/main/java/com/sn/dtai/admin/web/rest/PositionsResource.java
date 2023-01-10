package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Positions;
import com.sn.dtai.admin.repository.PositionsRepository;
import com.sn.dtai.admin.service.dto.PositionsDTO;
import com.sn.dtai.admin.service.mapper.PositionsMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import com.sn.dtai.admin.web.rest.errors.CodeException;

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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Positions}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PositionsResource {

    private final Logger log = LoggerFactory.getLogger(PositionsResource.class);

    private static final String ENTITY_NAME = "adminPositions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PositionsRepository positionsRepository;
    
    private final PositionsMapper positionsMapper;

    public PositionsResource(PositionsRepository positionsRepository, PositionsMapper positionsMapper) {
        this.positionsRepository = positionsRepository;
        this.positionsMapper = positionsMapper;
    }

    /**
     * {@code POST  /positions} : Create a new positions.
     *
     * @param positions the positions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new positions, or with status {@code 400 (Bad Request)} if the positions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/positions")
    public ResponseEntity<Positions> createPositions(@Valid @RequestBody Positions positions) throws URISyntaxException {
        log.debug("REST request to save Positions : {}", positions);
        if (positions.getId() != null) {
            throw new BadRequestAlertException("A new positions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<PositionsDTO> positionOP = positionsRepository.findByCode(positions.getCode()).map(positionsMapper::toDto);

        if(positionOP.isPresent()){
            throw new CodeException();
        }

        Positions result = positionsRepository.save(positions);
        return ResponseEntity
            .created(new URI("/api/positions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /positions/:id} : Updates an existing positions.
     *
     * @param id the id of the positions to save.
     * @param positions the positions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated positions,
     * or with status {@code 400 (Bad Request)} if the positions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the positions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/positions/{id}")
    public ResponseEntity<Positions> updatePositions(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Positions positions
    ) throws URISyntaxException {
        log.debug("REST request to update Positions : {}, {}", id, positions);
        if (positions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, positions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!positionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Positions result = positionsRepository.save(positions);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, positions.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /positions/:id} : Partial updates given fields of an existing positions, field will ignore if it is null
     *
     * @param id the id of the positions to save.
     * @param positions the positions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated positions,
     * or with status {@code 400 (Bad Request)} if the positions is not valid,
     * or with status {@code 404 (Not Found)} if the positions is not found,
     * or with status {@code 500 (Internal Server Error)} if the positions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/positions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Positions> partialUpdatePositions(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Positions positions
    ) throws URISyntaxException {
        log.debug("REST request to partial update Positions partially : {}, {}", id, positions);
        if (positions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, positions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!positionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Positions> result = positionsRepository
            .findById(positions.getId())
            .map(existingPositions -> {
                if (positions.getCode() != null) {
                    existingPositions.setCode(positions.getCode());
                }
                if (positions.getLibelle() != null) {
                    existingPositions.setLibelle(positions.getLibelle());
                }
                if (positions.getDescription() != null) {
                    existingPositions.setDescription(positions.getDescription());
                }
                if (positions.getTypeAgent() != null) {
                    existingPositions.setTypeAgent(positions.getTypeAgent());
                }
                if (positions.getImpactSolde() != null) {
                    existingPositions.setImpactSolde(positions.getImpactSolde());
                }
                if (positions.getStatutPosition() != null) {
                    existingPositions.setStatutPosition(positions.getStatutPosition());
                }

                return existingPositions;
            })
            .map(positionsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, positions.getId().toString())
        );
    }

    /**
     * {@code GET  /positions} : get all the positions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of positions in body.
     */
    @GetMapping("/positions")
    public ResponseEntity<List<Positions>> getAllPositions(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Positions");
        Page<Positions> page = positionsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /positions/:id} : get the "id" positions.
     *
     * @param id the id of the positions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the positions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/positions/{id}")
    public ResponseEntity<Positions> getPositions(@PathVariable Long id) {
        log.debug("REST request to get Positions : {}", id);
        Optional<Positions> positions = positionsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(positions);
    }

    /**
     * {@code DELETE  /positions/:id} : delete the "id" positions.
     *
     * @param id the id of the positions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/positions/{id}")
    public ResponseEntity<Void> deletePositions(@PathVariable Long id) {
        log.debug("REST request to delete Positions : {}", id);
        positionsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

        /**
     * {@code GET  /grades} : get all the grades.
     *
     * sans pagination
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of grades in body.
     */
    @GetMapping("/positions/all")
    public List<Positions> getAllPositions() {
        return positionsRepository.findAllPositions();
    }

     /**
     * {@code GET  /positions/:id} : get the "id" positions.
     *
     * @param id the id of the positions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the positions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/positions-code/activite/")
    public ResponseEntity<PositionsDTO> getPositionsActivite() {
        log.debug("REST request to get Positions : ");
        Optional<PositionsDTO> positions = positionsRepository.enActivite().map(positionsMapper::toDto);
        return ResponseUtil.wrapOrNotFound(positions);
    }
}
