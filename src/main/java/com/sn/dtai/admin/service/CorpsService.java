package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.CorpsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Corps}.
 */
public interface CorpsService {
    /**
     * Save a corps.
     *
     * @param corpsDTO the entity to save.
     * @return the persisted entity.
     */
    CorpsDTO save(CorpsDTO corpsDTO);

    /**
     * Updates a corps.
     *
     * @param corpsDTO the entity to update.
     * @return the persisted entity.
     */
    CorpsDTO update(CorpsDTO corpsDTO);

    /**
     * Partially updates a corps.
     *
     * @param corpsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CorpsDTO> partialUpdate(CorpsDTO corpsDTO);

    /**
     * Get all the corps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CorpsDTO> findAll(Pageable pageable);

    /**
     * Get all the corps with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CorpsDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" corps.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CorpsDTO> findOne(Long id);

    /**
     * Delete the "id" corps.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
