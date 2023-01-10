package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Services;
import com.sn.dtai.admin.repository.ServicesRepository;
import com.sn.dtai.admin.service.dto.ServicesDTO;
import com.sn.dtai.admin.service.impl.ServicesServiceImpl;
import com.sn.dtai.admin.service.mapper.ServicesMapper;
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
 * REST controller for managing {@link com.sn.dtai.admin.domain.Service}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ServicesResource {

    private final Logger log = LoggerFactory.getLogger(ServicesResource.class);

    private static final String ENTITY_NAME = "adminService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServicesRepository serviceRepository;

    private final ServicesServiceImpl serviceServiceImpl;

    private final ServicesMapper serviceMapper;

    public ServicesResource(ServicesRepository serviceRepository, ServicesServiceImpl serviceServiceImpl, ServicesMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceServiceImpl = serviceServiceImpl;
        this.serviceMapper = serviceMapper;
    }

    /**
     * {@code POST  /services} : Create a new service.
     *
     * @param service the service to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new service, or with status {@code 400 (Bad Request)} if the
     *         service has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/services")
    public ResponseEntity<Services> createService(@Valid @RequestBody Services service) throws URISyntaxException {
        log.debug("REST request to save Service : {}", service);
        if (service.getId() != null) {
            throw new BadRequestAlertException("A new service cannot already have an ID", ENTITY_NAME, "idexists");
        }

        // Vérif Validité code Service
        if (service.getCode() == null || service.getCode().trim().equals("")) {
            throw new BadRequestAlertException("Code Invalid", ENTITY_NAME, "Veuillez entrer un code valide !");
        } else {
            // Vérif Unicité code Service
            Optional<Services> verifCodeService = serviceServiceImpl.findByCode(service.getCode());
            if (verifCodeService.isPresent()) {
                throw new BadRequestAlertException(
                    "Le code '" + service.getCode() + "' est déjà utilisé : '" + verifCodeService.get().getCode() + "'",
                    ENTITY_NAME,
                    "Le code '" + verifCodeService.get().getCode() + "' est déjà utilisé  : '."
                );
            }
        }

        // Vérif Validité libelle Service pour la structure connectée
        if (service.getLibelle() == null || service.getLibelle().trim().equals("")) {
            throw new BadRequestAlertException("Libelle Invalid", ENTITY_NAME, "Veuillez entrer un Libelle valide !");
        } else {
            // Vérif Unicité libelle service
            Optional<Services> verifLibelleService = serviceServiceImpl.findByLibelle(service.getLibelle());
            if (verifLibelleService.isPresent()) {
                throw new BadRequestAlertException(
                    "Le Libelle '" + verifLibelleService.get().getLibelle() + "' est déjà utilisé'",
                    ENTITY_NAME,
                    "Le libelle '" + verifLibelleService.get().getLibelle() + "' est déjà utilisé . '"
                );
            }
        }

        Services result = serviceRepository.save(service);
        return ResponseEntity
            .created(new URI("/api/services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /services/:id} : Updates an existing service.
     *
     * @param id      the id of the service to save.
     * @param service the service to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated service,
     *         or with status {@code 400 (Bad Request)} if the service is not valid,
     *         or with status {@code 500 (Internal Server Error)} if the service
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/services/{id}")
    public ResponseEntity<Services> updateService(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Services service
    ) throws URISyntaxException {
        log.debug("REST request to update Service : {}, {}", id, service);
        if (service.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, service.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Services result = serviceRepository.save(service);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, service.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /services/:id} : Partial updates given fields of an existing
     * service, field will ignore if it is null
     *
     * @param id      the id of the service to save.
     * @param service the service to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated service,
     *         or with status {@code 400 (Bad Request)} if the service is not valid,
     *         or with status {@code 404 (Not Found)} if the service is not found,
     *         or with status {@code 500 (Internal Server Error)} if the service
     *         couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/services/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Services> partialUpdateService(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Services service
    ) throws URISyntaxException {
        log.debug("REST request to partial update Service partially : {}, {}", id, service);
        if (service.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, service.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Services> result = serviceRepository
            .findById(service.getId())
            .map(existingService -> {
                if (service.getCode() != null) {
                    existingService.setCode(service.getCode());
                }
                if (service.getLibelle() != null) {
                    existingService.setLibelle(service.getLibelle());
                }
                if (service.getAdresse() != null) {
                    existingService.setAdresse(service.getAdresse());
                }
                if (service.getNumTelephone() != null) {
                    existingService.setNumTelephone(service.getNumTelephone());
                }
                if (service.getFax() != null) {
                    existingService.setFax(service.getFax());
                }
                if (service.getEmail() != null) {
                    existingService.setEmail(service.getEmail());
                }
                if (service.getContact() != null) {
                    existingService.setContact(service.getContact());
                }

                return existingService;
            })
            .map(serviceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, service.getId().toString())
        );
    }

    /**
     * {@code GET  /services} : get all the services.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of services in body.
     */
    @GetMapping("/services")
    public ResponseEntity<List<Services>> getAllServices(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Services");
        Page<Services> page = serviceRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /services/:id} : get the "id" service.
     *
     * @param id the id of the service to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the service, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/services/{id}")
    public ResponseEntity<Services> getService(@PathVariable Long id) {
        log.debug("REST request to get Service : {}", id);
        Optional<Services> service = serviceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(service);
    }

    /**
     * {@code DELETE  /services/:id} : delete the "id" service.
     *
     * @param id the id of the service to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        log.debug("REST request to delete Service : {}", id);
        serviceRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /services/:id} : get the "id" service.
     *
     * @param id the id of the service to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the service, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/services/etablissement")
    public List<ServicesDTO> getServiceByEtablissement(@RequestParam(value = "etablissementId") Long etablissementId) {
        log.debug("REST request to get Service : {}", etablissementId);
        return serviceRepository
            .findByEtablissement(etablissementId)
            .stream()
            .map(serviceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * {@code GET  /services} : get all the services.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of services in body.
     */
    @GetMapping("/services/all")
    public List<Services> getAllServices() {
        return serviceRepository.findAllServices();
    }

    /**
     * {@code GET  /services/recherchemotsclés} : get the services by mots clés.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of services in body.
     */
    @GetMapping("/services/recherchemotsclés")
    public ResponseEntity<List<Services>> getServicesByMotsCles(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        String motCles
    ) {
        log.debug("REST request to get a page of services");
        Page<Services> page = serviceRepository.rechercheServicesByMotsCles(motCles, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /services/:code service} : get the "services " by code Service.
     *
     * @param code Poste.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the services, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/services/etablissement/code-service")
    public ResponseEntity<ServicesDTO> getOneServiceByEtablissementCode(@RequestParam(value = "etablissementId") Long etablissementId, @RequestParam(value = "code") String code) {
        log.debug("REST request to get services by codes : {}", code);
       try{
        Optional<ServicesDTO> codeServiceDTO = serviceServiceImpl.findBOneServiceyEtablissement(etablissementId, code);
        return ResponseEntity.ok().body(codeServiceDTO.get());
       } catch(Exception e){
        throw new BadRequestAlertException("Ce service n'est pas présent dans cet etablissement", ENTITY_NAME, "Ce service n'existe pas dans cet etablissement !");
       }
    }
}
