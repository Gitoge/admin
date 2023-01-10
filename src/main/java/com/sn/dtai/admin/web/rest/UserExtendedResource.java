package com.sn.dtai.admin.web.rest;

import com.sn.dtai.admin.domain.UserExtended;
import com.sn.dtai.admin.repository.UserExtendedRepository;
import com.sn.dtai.admin.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sn.dtai.admin.domain.UserExtended}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserExtendedResource {

    private final Logger log = LoggerFactory.getLogger(UserExtendedResource.class);

    private static final String ENTITY_NAME = "adminUserExtended";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserExtendedRepository userExtendedRepository;

    public UserExtendedResource(UserExtendedRepository userExtendedRepository) {
        this.userExtendedRepository = userExtendedRepository;
    }

    /**
     * {@code POST  /user-extendeds} : Create a new userExtended.
     *
     * @param userExtended the userExtended to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userExtended, or with status {@code 400 (Bad Request)} if the userExtended has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-extendeds")
    public ResponseEntity<UserExtended> createUserExtended(@RequestBody UserExtended userExtended) throws URISyntaxException {
        log.debug("REST request to save UserExtended : {}", userExtended);
        if (userExtended.getId() != null) {
            throw new BadRequestAlertException("A new userExtended cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserExtended result = userExtendedRepository.save(userExtended);
        return ResponseEntity
            .created(new URI("/api/user-extendeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-extendeds/:id} : Updates an existing userExtended.
     *
     * @param id the id of the userExtended to save.
     * @param userExtended the userExtended to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userExtended,
     * or with status {@code 400 (Bad Request)} if the userExtended is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userExtended couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-extendeds/{id}")
    public ResponseEntity<UserExtended> updateUserExtended(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserExtended userExtended
    ) throws URISyntaxException {
        log.debug("REST request to update UserExtended : {}, {}", id, userExtended);
        if (userExtended.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userExtended.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userExtendedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        // no save call needed as we have no fields that can be updated
        UserExtended result = userExtended;
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userExtended.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-extendeds/:id} : Partial updates given fields of an existing userExtended, field will ignore if it is null
     *
     * @param id the id of the userExtended to save.
     * @param userExtended the userExtended to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userExtended,
     * or with status {@code 400 (Bad Request)} if the userExtended is not valid,
     * or with status {@code 404 (Not Found)} if the userExtended is not found,
     * or with status {@code 500 (Internal Server Error)} if the userExtended couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-extendeds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserExtended> partialUpdateUserExtended(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserExtended userExtended
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserExtended partially : {}, {}", id, userExtended);
        if (userExtended.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userExtended.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userExtendedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserExtended> result = userExtendedRepository
            .findById(userExtended.getId())
            .map(existingUserExtended -> {
                return existingUserExtended;
            }); // .map(userExtendedRepository::save)

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userExtended.getId().toString())
        );
    }

    /**
     * {@code GET  /user-extendeds} : get all the userExtendeds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userExtendeds in body.
     */
    @GetMapping("/user-extendeds")
    public List<UserExtended> getAllUserExtendeds() {
        log.debug("REST request to get all UserExtendeds");
        return userExtendedRepository.findAll();
    }

    /**
     * {@code GET  /user-extendeds/:id} : get the "id" userExtended.
     *
     * @param id the id of the userExtended to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userExtended, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-extendeds/{id}")
    public ResponseEntity<UserExtended> getUserExtended(@PathVariable Long id) {
        log.debug("REST request to get UserExtended : {}", id);
        Optional<UserExtended> userExtended = userExtendedRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userExtended);
    }

    /**
     * {@code DELETE  /user-extendeds/:id} : delete the "id" userExtended.
     *
     * @param id the id of the userExtended to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-extendeds/{id}")
    public ResponseEntity<Void> deleteUserExtended(@PathVariable Long id) {
        log.debug("REST request to delete UserExtended : {}", id);
        userExtendedRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
