package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Grade;
import com.sn.dtai.admin.repository.GradeRepository;
import com.sn.dtai.admin.service.dto.GradeDTO;
import com.sn.dtai.admin.service.impl.GradeServiceImpl;
import com.sn.dtai.admin.service.mapper.GradeMapper;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Grade}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GradeResource {

    private final Logger log = LoggerFactory.getLogger(GradeResource.class);

    private static final String ENTITY_NAME = "adminGrade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GradeRepository gradeRepository;
    
    private final GradeMapper gradeMapper;

    private final GradeServiceImpl gradeServiceImpl;

    public GradeResource(GradeRepository gradeRepository, GradeServiceImpl gradeServiceImpl, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeServiceImpl = gradeServiceImpl;
        this.gradeMapper = gradeMapper ;
    }

    /**
     * {@code POST  /grades} : Create a new grade.
     *
     * @param grade the grade to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new grade, or with status {@code 400 (Bad Request)} if the
     *         grade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grades")
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody Grade grade) throws URISyntaxException {
        log.debug("REST request to save Grade : {}", grade);
        if (grade.getId() != null) {
            throw new BadRequestAlertException("A new grade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<GradeDTO> gradeOP = gradeRepository.findByCode(grade.getCode()).map(gradeMapper::toDto);;

        if(gradeOP.isPresent()){
            throw new CodeException();
        }

        Grade result = gradeRepository.save(grade);
        return ResponseEntity
            .created(new URI("/api/grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grades/:id} : Updates an existing grade.
     *
     * @param id    the id of the grade to save.
     * @param grade the grade to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated grade,
     *         or with status {@code 400 (Bad Request)} if the grade is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the grade
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grades/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Grade grade)
        throws URISyntaxException {
        log.debug("REST request to update Grade : {}, {}", id, grade);
        if (grade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grade.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gradeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Grade result = gradeRepository.save(grade);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grade.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /grades/:id} : Partial updates given fields of an existing
     * grade, field will ignore if it is null
     *
     * @param id    the id of the grade to save.
     * @param grade the grade to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated grade,
     *         or with status {@code 400 (Bad Request)} if the grade is not valid,
     *         or with status {@code 404 (Not Found)} if the grade is not found,
     *         or with status {@code 500 (Internal Server Error)} if the grade
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/grades/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Grade> partialUpdateGrade(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Grade grade
    ) throws URISyntaxException {
        log.debug("REST request to partial update Grade partially : {}, {}", id, grade);
        if (grade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, grade.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!gradeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Grade> result = gradeRepository
            .findById(grade.getId())
            .map(existingGrade -> {
                if (grade.getCode() != null) {
                    existingGrade.setCode(grade.getCode());
                }
                if (grade.getLibelle() != null) {
                    existingGrade.setLibelle(grade.getLibelle());
                }
                if (grade.getDescription() != null) {
                    existingGrade.setDescription(grade.getDescription());
                }
                if (grade.getAncEchClasse() != null) {
                    existingGrade.setAncEchClasse(grade.getAncEchClasse());
                }
                if (grade.getNbreEchelon() != null) {
                    existingGrade.setNbreEchelon(grade.getNbreEchelon());
                }

                return existingGrade;
            })
            .map(gradeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grade.getId().toString())
        );
    }

    /**
     * {@code GET  /grades} : get all the grades.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of grades in body.
     */
    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> getAllGrades(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Grades");
        Page<Grade> page = gradeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /grades/:id} : get the "id" grade.
     *
     * @param id the id of the grade to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the grade, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grades/{id}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long id) {
        log.debug("REST request to get Grade : {}", id);
        Optional<Grade> grade = gradeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(grade);
    }

    /**
     * {@code GET  /grades/:id} : get the "id" grade.
     *
     * @param id the id of the grade to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the grade, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grade/{id}")
    public ResponseEntity<GradeDTO> getGradeDTO(@PathVariable Long id) {
        log.debug("REST request to get Grade : {}", id);
        Optional<GradeDTO> grade = gradeServiceImpl.findOne(id);
        return ResponseUtil.wrapOrNotFound(grade);
    }

    /**
     * {@code DELETE  /grades/:id} : delete the "id" grade.
     *
     * @param id the id of the grade to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grades/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        log.debug("REST request to delete Grade : {}", id);
        gradeRepository.deleteById(id);
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
    @GetMapping("/grades/all")
    public List<Grade> getAllGrades() {
        return gradeRepository.findAllGrades();
    }

/**
 * {@code GET  grades/recherche} : get all the grades.
 *
 * sans pagination
 *
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
 *         of grades in body.
 */
@GetMapping("/grades/recherches")
public ResponseEntity<List<Grade>> rechercheGrades(String motCle,Pageable pageable) {
    log.debug("REST request to get a page of grade {}");
    Page<Grade> page = gradeRepository.rechercheGrades(motCle,pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}

 /**
     * {@code GET  /grades/:code grade} : get the "grades " by code Grade.
     *
     * @param code Poste.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grades, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grades/code-grade")
    public ResponseEntity<GradeDTO> getGradeByCode(@RequestParam(value = "code") String code) {
        log.debug("REST request to get grades by codes : {}", code);
       try{
        Optional<GradeDTO> codeGradeDTO = gradeServiceImpl.findByCode(code).map(gradeMapper::toDto);
        return ResponseEntity.ok().body(codeGradeDTO.get());
       } catch(Exception e){
        throw new BadRequestAlertException("Ce grade n'est pas présent en base", ENTITY_NAME, "Ce grade n'existe pas !");
       }
    }
}
