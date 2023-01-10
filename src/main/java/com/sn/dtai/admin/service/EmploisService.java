package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.EmploisDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Emplois}.
 */
public interface EmploisService {
    /**
     * Save a emplois.
     *
     * @param emploisDTO the entity to save.
     * @return the persisted entity.
     */
    EmploisDTO save(EmploisDTO emploisDTO);

    /**
     * Updates a emplois.
     *
     * @param emploisDTO the entity to update.
     * @return the persisted entity.
     */
    EmploisDTO update(EmploisDTO emploisDTO);

    /**
     * Partially updates a emplois.
     *
     * @param emploisDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EmploisDTO> partialUpdate(EmploisDTO emploisDTO);

    /**
     * Get all the emplois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmploisDTO> findAll(Pageable pageable);

    /**
     * Get all the emplois with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmploisDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" emplois.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmploisDTO> findOne(Long id);

    /**
     * Delete the "id" emplois.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
