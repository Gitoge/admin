package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.config.ApplicationProperties;
import com.sn.dtai.admin.domain.Etablissement;
import com.sn.dtai.admin.repository.EtablissementRepository;
import com.sn.dtai.admin.service.dto.ConventionDTO;
import com.sn.dtai.admin.service.impl.EtablissementServiceImpl;
import com.sn.dtai.admin.service.mapper.EtablissementMapper;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import io.micrometer.core.annotation.Timed;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Etablissement}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EtablissementResource {

    private final Logger log = LoggerFactory.getLogger(EtablissementResource.class);

    private static final String ENTITY_NAME = "adminEtablissement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtablissementRepository etablissementRepository;

    private final EtablissementServiceImpl etablissementServiceImpl;

    private final EtablissementMapper etablissementMapper;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ApplicationProperties applicationProperties;

    public EtablissementResource(
        EtablissementRepository etablissementRepository,
        EtablissementServiceImpl etablissementServiceImpl,
        EtablissementMapper etablissementMapper
    ) {
        this.etablissementRepository = etablissementRepository;
        this.etablissementServiceImpl = etablissementServiceImpl;
        this.etablissementMapper = etablissementMapper;
    }

    /**
     * {@code POST  /etablissements} : Create a new etablissement.
     *
     * @param etablissement the etablissement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new etablissement, or with status {@code 400 (Bad Request)}
     *         if the etablissement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etablissements")
    public ResponseEntity<Etablissement> createEtablissement(@Valid @RequestBody Etablissement etablissement) throws URISyntaxException {
        log.debug("REST request to save Etablissement : {}", etablissement);
        if (etablissement.getId() != null) {
            throw new BadRequestAlertException("A new etablissement cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code typeEtablissement
        if (etablissement.getCode() == null || etablissement.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code Etablissement
            Optional<Etablissement> verifCodeEtablissement = etablissementServiceImpl.findByCode(etablissement.getCode());
            if (verifCodeEtablissement.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + etablissement.getCode() + "' est déjà utilisé : '" + verifCodeEtablissement.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodeEtablissement.get().getCode() + "' est déjà utilisé  : '."
                );
            }
        }

        // Vérif Validité libelle Etablissement pour la structure connectée
        if (etablissement.getLibelleCourt() == null || etablissement.getLibelleCourt().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle typeLocalite
            Optional<Etablissement> verifLibelleEtablissement = etablissementServiceImpl.findByLibelle(etablissement.getLibelleCourt());
            if (verifLibelleEtablissement.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleEtablissement.get().getLibelleCourt() + "' est déjà utilisé'",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleEtablissement.get().getLibelleCourt() + "' est déjà utilisé . '"
                );
            }
        }
        if (etablissement.getLibelleConvention() != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(AUTHORIZATION_HEADER, bearerToken);
            HttpEntity<ConventionDTO> entityConventionDTO = new HttpEntity<ConventionDTO>(headers);

            ConventionDTO conventionDTO = new ConventionDTO();

            ResponseEntity<ConventionDTO> responseEntityConvention = restTemplate.exchange(
                this.applicationProperties.getRestUrlCarriere() + "/api/conventions/libelle/" + etablissement.getLibelleConvention(),
                HttpMethod.GET,
                entityConventionDTO,
                ConventionDTO.class
            );
            conventionDTO = responseEntityConvention.getBody();

            if (conventionDTO != null) {
                etablissement.setConventionId(conventionDTO.getId());
            }

            Etablissement result = etablissementRepository.save(etablissement);
            return ResponseEntity
                .created(new URI("/api/etablissements/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        } else {
            Etablissement result = etablissementRepository.save(etablissement);
            return ResponseEntity
                .created(new URI("/api/etablissements/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
    }

    /**
     * {@code PUT  /etablissements/:id} : Updates an existing etablissement.
     *
     * @param id            the id of the etablissement to save.
     * @param etablissement the etablissement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated etablissement,
     *         or with status {@code 400 (Bad Request)} if the etablissement is not
     *         valid,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         etablissement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etablissements/{id}")
    public ResponseEntity<Etablissement> updateEtablissement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Etablissement etablissement
    ) throws URISyntaxException {
        log.debug("REST request to update Etablissement : {}, {}", id, etablissement);
        if (etablissement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, etablissement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!etablissementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (etablissement.getLibelleConvention() != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(AUTHORIZATION_HEADER, bearerToken);
            HttpEntity<ConventionDTO> entityConventionDTO = new HttpEntity<ConventionDTO>(headers);

            ConventionDTO conventionDTO = new ConventionDTO();

            ResponseEntity<ConventionDTO> responseEntityConvention = restTemplate.exchange(
                this.applicationProperties.getRestUrlCarriere() + "/api/conventions/libelle/" + etablissement.getLibelleConvention(),
                HttpMethod.GET,
                entityConventionDTO,
                ConventionDTO.class
            );
            conventionDTO = responseEntityConvention.getBody();

            if (conventionDTO != null) {
                etablissement.setConventionId(conventionDTO.getId());
            }

            Etablissement result = etablissementRepository.save(etablissement);
            return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etablissement.getId().toString()))
                .body(result);
        } else {
            Etablissement result = etablissementRepository.save(etablissement);
            return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etablissement.getId().toString()))
                .body(result);
        }
    }

    /**
     * {@code PATCH  /etablissements/:id} : Partial updates given fields of an
     * existing etablissement, field will ignore if it is null
     *
     * @param id            the id of the etablissement to save.
     * @param etablissement the etablissement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated etablissement,
     *         or with status {@code 400 (Bad Request)} if the etablissement is not
     *         valid,
     *         or with status {@code 404 (Not Found)} if the etablissement is not
     *         found,
     *         or with status {@code 500 (Internal Server Error)} if the
     *         etablissement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/etablissements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Etablissement> partialUpdateEtablissement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Etablissement etablissement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Etablissement partially : {}, {}", id, etablissement);
        if (etablissement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, etablissement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!etablissementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Etablissement> result = etablissementRepository
            .findById(etablissement.getId())
            .map(existingEtablissement -> {
                if (etablissement.getCode() != null) {
                    existingEtablissement.setCode(etablissement.getCode());
                }
                if (etablissement.getSigle() != null) {
                    existingEtablissement.setSigle(etablissement.getSigle());
                }
                if (etablissement.getLibelleCourt() != null) {
                    existingEtablissement.setLibelleCourt(etablissement.getLibelleCourt());
                }
                if (etablissement.getLibelleLong() != null) {
                    existingEtablissement.setLibelleLong(etablissement.getLibelleLong());
                }
                if (etablissement.getAdresse() != null) {
                    existingEtablissement.setAdresse(etablissement.getAdresse());
                }
                if (etablissement.getNumTelephone() != null) {
                    existingEtablissement.setNumTelephone(etablissement.getNumTelephone());
                }
                if (etablissement.getFax() != null) {
                    existingEtablissement.setFax(etablissement.getFax());
                }
                if (etablissement.getEmail() != null) {
                    existingEtablissement.setEmail(etablissement.getEmail());
                }
                if (etablissement.getBp() != null) {
                    existingEtablissement.setBp(etablissement.getBp());
                }

                return existingEtablissement;
            })
            .map(etablissementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etablissement.getId().toString())
        );
    }

    /**
     * {@code GET  /etablissements} : get all the etablissements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of etablissements in body.
     */
    @GetMapping("/etablissements")
    public ResponseEntity<List<Etablissement>> getAllEtablissements(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Etablissements");
        Page<Etablissement> page = etablissementRepository.findAllEtablissementPage(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etablissements/:id} : get the "id" etablissement.
     *
     * @param id the id of the etablissement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the etablissement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etablissements/{id}")
    public ResponseEntity<Etablissement> getEtablissement(@PathVariable Long id) {
        log.debug("REST request to get Etablissement : {}", id);
        try {
            Optional<Etablissement> etablissement = etablissementRepository.findById(id);
            return ResponseUtil.wrapOrNotFound(etablissement);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * GET /localites/rattachement : localites by rattachement.
     */
    @GetMapping("/etablissements-localite/{localiteId}")
    @Timed
    public List<Etablissement> getEtablissementByLocalite(@PathVariable Long localiteId) {
        // log.debug("REST request Localites By localite : {}", localiteLocalite);
        log.debug("Request to get Localites : {}", localiteId);
        return etablissementRepository.findByLocalite(localiteId);
    }

    /**
     * {@code DELETE  /etablissements/:id} : delete the "id" etablissement.
     *
     * @param id the id of the etablissement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etablissements/{id}")
    public ResponseEntity<Void> deleteEtablissement(@PathVariable Long id) {
        log.debug("REST request to delete Etablissement : {}", id);
        etablissementRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /etablissements} : get all the etablissements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of etablissements in body.
     */
    @GetMapping("/etablissements/recherche")
    public ResponseEntity<List<Etablissement>> getAllEtablissementByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of etablissements");
        Page<Etablissement> page = etablissementRepository.recherchesEtablissement(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etablissements} : get all the etablissements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of etablissements in body.
     */
    @GetMapping("/etablissements/all")
    public List<Etablissement> getAllEtablissements() {
        return etablissementRepository.findAllEtablissement();
    }

    /**
     * GET /etablissementId/ : localites by rattachement.
     */
    @GetMapping("/etablissementId")
    @Timed
    public Optional<Etablissement> getEtablissementById(@RequestParam(value = "idEtablissement") Long idEtablissement) {
        log.debug("Request to get etablissementId : {}", idEtablissement);
        return etablissementRepository.findByIdEtablissement(idEtablissement);
    }

/**
 * GET /etablissements/etablissementId : Get etablissement by Id.
 */
@GetMapping("/etablissements/alll")
@Timed
public List<Etablissement> getEtablissementByEtablissementId(@RequestParam(value = "etablissementId") Long etablissementId) {
    // log.debug("REST request Localites By localite : {}", localiteLocalite);
    log.debug("Request to get etablissement by id: {}", etablissementId);
    return etablissementRepository.findByEtablissementId(etablissementId);
}

}
