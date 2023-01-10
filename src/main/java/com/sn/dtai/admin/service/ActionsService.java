package com.sn.dtai.admin.service;

import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.service.dto.ActionsDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Actions}.
 */
public interface ActionsService {
    /**
     * Save a actions.
     *
     * @param actionsDTO the entity to save.
     * @return the persisted entity.
     */
    ActionsDTO save(ActionsDTO actionsDTO);

    /**
     * Updates a actions.
     *
     * @param actionsDTO the entity to update.
     * @return the persisted entity.
     */
    ActionsDTO update(ActionsDTO actionsDTO);

    /**
     * Partially updates a actions.
     *
     * @param actionsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ActionsDTO> partialUpdate(ActionsDTO actionsDTO);

    /**
     * Get all the actions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActionsDTO> findAll(Pageable pageable);

    /**
     * Get all the actions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActionsDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" actions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActionsDTO> findOne(Long id);

    /**
     * Delete the "id" actions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Set<Actions> findByPagesId(Long id);

    List<ActionsDTO> findByPages(List<Long> pagesIds);

}
