package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ModulesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Modules}.
 */
public interface ModulesService {
    /**
     * Save a modules.
     *
     * @param modulesDTO the entity to save.
     * @return the persisted entity.
     */
    ModulesDTO save(ModulesDTO modulesDTO);

    /**
     * Updates a modules.
     *
     * @param modulesDTO the entity to update.
     * @return the persisted entity.
     */
    ModulesDTO update(ModulesDTO modulesDTO);

    /**
     * Partially updates a modules.
     *
     * @param modulesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ModulesDTO> partialUpdate(ModulesDTO modulesDTO);

    /**
     * Get all the modules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ModulesDTO> findAll(Pageable pageable);

    // /**
    //  * Get all the modules with eager load of many-to-many relationships.
    //  *
    //  * @param pageable the pagination information.
    //  * @return the list of entities.
    //  */
    // Page<ModulesDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" modules.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ModulesDTO> findOne(Long id);

    /**
     * Delete the "id" modules.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
