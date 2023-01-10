package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.RolesDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Roles}.
 */
public interface RolesService {
    /**
     * Save a roles.
     *
     * @param rolesDTO the entity to save.
     * @return the persisted entity.
     */
    RolesDTO save(RolesDTO rolesDTO);

    /**
     * Updates a roles.
     *
     * @param rolesDTO the entity to update.
     * @return the persisted entity.
     */
    RolesDTO update(RolesDTO rolesDTO);

    /**
     * Partially updates a roles.
     *
     * @param rolesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RolesDTO> partialUpdate(RolesDTO rolesDTO);

    /**
     * Get all the roles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RolesDTO> findAll(Pageable pageable);

    /**
     * Get all the roles with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RolesDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" roles.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RolesDTO> findOne(Long id);

    /**
     * Delete the "id" roles.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<RolesDTO> findByProfil(Long profilId);
}
