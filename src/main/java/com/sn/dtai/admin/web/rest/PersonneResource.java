package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.domain.Modules;
import com.sn.dtai.admin.domain.Pages;
import com.sn.dtai.admin.domain.Personne;
import com.sn.dtai.admin.repository.PagesRepository;
import com.sn.dtai.admin.repository.PersonneRepository;
import com.sn.dtai.admin.service.ActionsService;
import com.sn.dtai.admin.service.PagesService;
import com.sn.dtai.admin.service.dto.PersonneDTO;
import com.sn.dtai.admin.service.dto.RolesDTO;
import com.sn.dtai.admin.service.dto.PagesDTO;
import com.sn.dtai.admin.service.dto.ModulesDTO;
import com.sn.dtai.admin.service.dto.PageActionDTO;
import com.sn.dtai.admin.service.dto.PagesActionsDto;
import com.sn.dtai.admin.service.dto.ActionsDTO;
import com.sn.dtai.admin.service.dto.InfosUserDto;
import com.sn.dtai.admin.service.impl.PersonneServiceImpl;
import com.sn.dtai.admin.service.PersonneService;
import com.sn.dtai.admin.service.RolesService;
import com.sn.dtai.admin.service.PagesService;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

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
import java.time.Instant;


/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.Personne}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PersonneResource {

    private final Logger log = LoggerFactory.getLogger(PersonneResource.class);

    private static final String ENTITY_NAME = "adminPersonne";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    private final PersonneRepository personneRepository;
    private final PersonneService personneService;

    private final ActionsService actionsService;

    private final PagesService pagesService;

    private final PagesRepository pagesRepository;

    private final RolesService rolesService;

    public PersonneResource(PersonneRepository personneRepository,PersonneService personneService, PagesRepository pagesRepository, ActionsService actionsService, RolesService rolesService, PagesService pagesService) {
        this.personneRepository = personneRepository;
        this.personneService = personneService;
        this.pagesRepository = pagesRepository;
        this.actionsService = actionsService;
        this.rolesService = rolesService;
        this.pagesService = pagesService;
    }

    /**
     * {@code POST  /personnes} : Create a new personne.
     *
     * @param personne the personne to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personne, or with status {@code 400 (Bad Request)} if the personne has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/personnes")
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) throws URISyntaxException {
        log.debug("REST request to save Personne : {}", personne);
        if (personne.getId() != null) {
            throw new BadRequestAlertException("A new personne cannot already have an ID", ENTITY_NAME, "idexists");
        }
        personne.setPassword("$2a$10$sj7vb99t8eq7Co7uH4"+personne.getPassword()+"dP1uNlqMkocj/I.SEuWHJmRaQtB/gNXT5vu");


        Personne result = personneRepository.save(personne);
        return ResponseEntity
            .created(new URI("/api/personnes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /personnes/:id} : Updates an existing personne.
     *
     * @param id the id of the personne to save.
     * @param personne the personne to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personne,
     * or with status {@code 400 (Bad Request)} if the personne is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personne couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/personnes/{id}")
    public ResponseEntity<Personne> updatePersonne(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Personne personne
    ) throws URISyntaxException {
        Instant timestamp = Instant.now();
        personne.setLastDateUpdate(timestamp);
        personne.setDateDerniereConnexion(timestamp);
        Optional<PersonneDTO> personneDTO = personneService.getInfosPersonneByJhiUser(personne.getJhiUserId());
        personne.setDatePremiereConnexion(personneDTO.get().getDatePremiereConnexion());
        personne.setPassword("$2a$10$sj7vb99t8eq7Co7uH4"+personne.getPassword()+"dP1uNlqMkocj/ISEuWHJmRaQtB/gNXT5vu");

        log.debug("REST request to update Personne : {}, {}", id, personne);
        if (personne.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personne.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Personne result = personneRepository.save(personne);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personne.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /personnes/:id} : Partial updates given fields of an existing personne, field will ignore if it is null
     *
     * @param id the id of the personne to save.
     * @param personne the personne to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personne,
     * or with status {@code 400 (Bad Request)} if the personne is not valid,
     * or with status {@code 404 (Not Found)} if the personne is not found,
     * or with status {@code 500 (Internal Server Error)} if the personne couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/personnes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Personne> partialUpdatePersonne(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Personne personne
    ) throws URISyntaxException {
        log.debug("REST request to partial update Personne partially : {}, {}", id, personne);
        if (personne.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personne.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Personne> result = personneRepository
            .findById(personne.getId())
            .map(existingPersonne -> {
                if (personne.getTypePiece() != null) {
                    existingPersonne.setTypePiece(personne.getTypePiece());
                }
                if (personne.getNumeroPiece() != null) {
                    existingPersonne.setNumeroPiece(personne.getNumeroPiece());
                }
                if (personne.getDateNaissance() != null) {
                    existingPersonne.setDateNaissance(personne.getDateNaissance());
                }
                if (personne.getEmail() != null) {
                    existingPersonne.setEmail(personne.getEmail());
                }
                if (personne.getFonction() != null) {
                    existingPersonne.setFonction(personne.getFonction());
                }
                if (personne.getLieuNaissance() != null) {
                    existingPersonne.setLieuNaissance(personne.getLieuNaissance());
                }
                if (personne.getMatricule() != null) {
                    existingPersonne.setMatricule(personne.getMatricule());
                }
                if (personne.getNom() != null) {
                    existingPersonne.setNom(personne.getNom());
                }
                if (personne.getPrenom() != null) {
                    existingPersonne.setPrenom(personne.getPrenom());
                }
                if (personne.getSexe() != null) {
                    existingPersonne.setSexe(personne.getSexe());
                }
                if (personne.getTelephone() != null) {
                    existingPersonne.setTelephone(personne.getTelephone());
                }

                return existingPersonne;
            })
            .map(personneRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personne.getId().toString())
        );
    }

    /**
     * {@code GET  /personnes} : get all the personnes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnes in body.
     */
    @GetMapping("/personnes")
    public ResponseEntity<List<Personne>> getAllPersonnes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Personnes");
        Page<Personne> page = personneRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /personnes/:id} : get the "id" personne.
     *
     * @param id the id of the personne to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personne, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/personnes/{id}")
    public ResponseEntity<Personne> getPersonne(@PathVariable Long id) {
        log.debug("REST request to get Personne : {}", id);
        Optional<Personne> personne = personneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(personne);
    }

    /**
     * {@code DELETE  /personnes/:id} : delete the "id" personne.
     *
     * @param id the id of the personne to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/personnes/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        log.debug("REST request to delete Personne : {}", id);
        personneRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/personnes/user/{id}")
    public ResponseEntity<PersonneDTO> getPersonneProfilsModulesPagesActionsByJhiUser(@PathVariable Long id)
    {
        // PersonneDTO personneDTO = new PersonneDTO();
        Instant timestamp = Instant.now();
        log.debug("REST request to get PersonneProfilsModules by JhiUserId : {}", id);

        Optional<PersonneDTO> personneDTO = personneService.getInfosPersonneByJhiUser(id);
        if (!personneDTO.isPresent()) {
            throw new BadRequestAlertException("Personne Introuvable", ENTITY_NAME, "idnotfound");
        }

        /* List<Pages> pages = new ArrayList<>();
        for(Modules module: personneDTO.get().getProfils().getModules()) {
            for(Pages page: pagesRepository.findByModulesIdOrderByOrdre(module.getId())) {
                page.setActions(actionsService.findByPagesId(page.getId()));
                pages.add(page);
            }
        } */
        // personneDTO.get().setPages(pages);

        return ResponseEntity.ok().body(personneDTO.get());
    }

    @GetMapping("/personnes/user/infos/{id}")
    public ResponseEntity<PersonneDTO> getUserInfos(@PathVariable Long id)
    {
        // PersonneDTO personneDTO = new PersonneDTO();
        Instant timestamp = Instant.now();
        log.debug("REST request to get Infos User by JhiUserId : {}", id);


        Optional<PersonneDTO> personneDTO = personneService.getInfosPersonneByJhiUser(id);
        if (!personneDTO.isPresent()) {
            throw new BadRequestAlertException("Personne Introuvable", ENTITY_NAME, "idnotfound");
        }

        log.debug("personneDTO.get().getEtablissement().getId()::::::::::: {}", personneDTO.get().getEtablissement().getId());

        // Lister les roles de l'utilisateur en fonction du profil
        List<RolesDTO> rolesDTOs = rolesService.findByProfil(personneDTO.get().getProfils().getId());
        log.debug("rolesDTOs::::::::::: {}", rolesDTOs.size());

        Set<RolesDTO> customRolesDTOs = new HashSet<RolesDTO>();
        List<Long> rolesIds = new ArrayList<Long>();
        for(RolesDTO rolesDTO: rolesDTOs) {
            rolesIds.add(rolesDTO.getId());
            rolesDTO.setPagesActions(null);
            customRolesDTOs.add(rolesDTO);
        }

        // Lister les pages de l'utilisateur en fonction des roles
        List<RolesDTO> listRolesDTOs = new ArrayList<RolesDTO>(personneDTO.get().getProfils().getRoles());
        log.debug("listRolesDTOs.size()::::::::::: {}", listRolesDTOs.size());

        Set<ModulesDTO> modulesDTOs = new HashSet<ModulesDTO>();


        Set<PagesDTO> pagesDTOs = new HashSet<PagesDTO>();


        Set<ActionsDTO> actionsDTOs = new HashSet<ActionsDTO>();

        Set<PageActionDTO> pagesActionsDTOs = new HashSet<PageActionDTO>();

        for(RolesDTO rolesDTO: listRolesDTOs) {
            List<PageActionDTO> listPageActionDTOs = new ArrayList<PageActionDTO>(rolesDTO.getPagesActions());
            for(PageActionDTO pageActionDTO: listPageActionDTOs) {
                if (pageActionDTO.getPages().getActive()) {
                    log.debug("pageActionDTO.getId()::::::::::: {}", pageActionDTO.getId());
                    pageActionDTO.getPages().getModules().setApplications(null);
                    if (pageActionDTO.getPages().getModules().getRouterLink() != null) {
                        pageActionDTO.getPages().getModules().getRouterLink().setTabletypevaleur(null);
                    }
                    if (pageActionDTO.getPages().getModules().getIcon() != null) {
                        pageActionDTO.getPages().getModules().getIcon().setTabletypevaleur(null);
                    }

                    modulesDTOs.add(pageActionDTO.getPages().getModules());
                    pageActionDTO.getPages().setActions(null);
                    if (pageActionDTO.getPages().getRouterLink() != null) {
                        pageActionDTO.getPages().getRouterLink().setTabletypevaleur(null);
                    }


                    pagesDTOs.add(pageActionDTO.getPages());
                    // actionsDTOs.add(pageActionDTO.getActions());
                    pagesActionsDTOs.add(pageActionDTO);
                }
            }

        }

        List<ModulesDTO> listModulesDTOs = new ArrayList<ModulesDTO>(modulesDTOs);

        List<PagesDTO> listPagesDTOs = new ArrayList<PagesDTO>(pagesDTOs);

        List<ActionsDTO> listActionsDTOs = new ArrayList<ActionsDTO>(actionsDTOs);

        List<PageActionDTO> listPagesActionsDTOs = new ArrayList<PageActionDTO>(pagesActionsDTOs);

        personneDTO.get().setPassword(null);

        personneDTO.get().getProfils().setRoles(customRolesDTOs);
        personneDTO.get().setPages(listPagesDTOs);
        personneDTO.get().setModules(listModulesDTOs);
        personneDTO.get().setActions(listActionsDTOs);
        personneDTO.get().setPagesActions(listPagesActionsDTOs);


        return ResponseEntity.ok().body(personneDTO.get());
    }
/**
 * {@code GET  /personnes} : get all the personnes.
 *
 * @param pageable the pagination information.
 * @param userInsertId the user Connected .
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnes in body.
 */
@GetMapping("/personnes/recherches")
public ResponseEntity<List<Personne>> getAllPersonnesByUserConnected(@org.springdoc.api.annotations.ParameterObject Pageable pageable,String motCles,Long id) {
    log.debug("REST request to get a page of Personnes");
    Page<Personne> page = personneRepository.recherchesPersonne(motCles,id,pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}

/**
 * {@code GET  /personnes} : get all the personnes.
 *
 * @param pageable the pagination information.
 * @param userInsertId the user Connected .
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnes in body.
 */
@GetMapping("/personnes/recherches-admin")
public ResponseEntity<List<Personne>> getAllPersonnesByAdmin(@org.springdoc.api.annotations.ParameterObject Pageable pageable,String motCles,Long etablissementId) {
    log.debug("REST request to get a page of Personnes");
    Page<Personne> page = personneRepository.recherchesPersonneAdmin(motCles,etablissementId,pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


/**
 * {@code GET  /personnes} : get all the personnes by super Admin.
 *
 * @param pageable the pagination information.
 * @param userInsertId the user Connected .
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of personnes in body.
 */
@GetMapping("/personnes/superAdmin")
public ResponseEntity<List<Personne>> getAllPersonnesBySuperAdmin(@org.springdoc.api.annotations.ParameterObject Pageable pageable,String motCles) {
    log.debug("REST request to get a page of Personnes");
    Page<Personne> page = personneRepository.superAdmin(motCles,pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}



/**
 * {@code PUT  /personnes/:id} : Updates an existing personne.
 *
 * @param id the id of the personne to save.
 * @param personne the personne to update.
 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personne,
 * or with status {@code 400 (Bad Request)} if the personne is not valid,
 * or with status {@code 500 (Internal Server Error)} if the personne couldn't be updated.
 * @throws URISyntaxException if the Location URI syntax is incorrect.
 */
@PutMapping("/personnes-delete/{id}")
public ResponseEntity<Personne> updatePersonneDelete(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody Personne personne
) throws URISyntaxException {
    Instant timestamp = Instant.now();
    personne.setDateDerniereConnexion(timestamp);

    log.debug("REST request to update Personne : {}, {}", id, personne);
    if (personne.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, personne.getId())) {
        throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!personneRepository.existsById(id)) {
        throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Personne result = personneRepository.save(personne);
    return ResponseEntity
        .ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personne.getId().toString()))
        .body(result);
}

/**
 * PUT  /personnes/mise-a-jour/premiere-connexion : Updates an existing personne.
 *
 * @param id of personne to update
 * @return the ResponseEntity with status 200 (OK) and with body the updated personneDTO,
 * or with status 400 (Bad Request) if the personneDTO is not valid,
 * or with status 500 (Internal Server Error) if the personneDTO couldn't be updated
 * @throws URISyntaxException if the Location URI syntax is incorrect
 */
@PutMapping("/personnes/mise-a-jour/premiere-connexion")
public ResponseEntity<PersonneDTO> updatePremiereConnexion(@RequestBody Long id) throws URISyntaxException {
    log.debug("REST request to update Premiere Connexion : {}", id);
    Optional<PersonneDTO> personneDTO = personneService.findOne(id);
    if (!personneDTO.isPresent()) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    personneDTO.get().setDatePremiereConnexion(Instant.now());
    PersonneDTO result = personneService.save(personneDTO.get());


    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personneDTO.get().getId().toString()))
        .body(result);
}


/**
 * PUT  /personnes/mise-a-jour/derniere-connexion : Updates an existing personne.
 *
 * @param id of personne to update
 * @return the ResponseEntity with status 200 (OK) and with body the updated personneDTO,
 * or with status 400 (Bad Request) if the personneDTO is not valid,
 * or with status 500 (Internal Server Error) if the personneDTO couldn't be updated
 * @throws URISyntaxException if the Location URI syntax is incorrect
 */
@PutMapping("/personnes/mise-a-jour/derniere-connexion")
public ResponseEntity<PersonneDTO> updateDerniereConnexion(@RequestBody Long id) throws URISyntaxException {
    log.debug("REST request to update Premiere Connexion : {}", id);
    Optional<PersonneDTO> personneDTO = personneService.findOne(id);
    if (!personneDTO.isPresent()) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    personneDTO.get().setDateDerniereConnexion(Instant.now());
    PersonneDTO result = personneService.save(personneDTO.get());


    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, personneDTO.get().getId().toString()))
        .body(result);
}

}
