package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ParamQuotiteCessibleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.ParamQuotiteCessible}.
 */
public interface ParamQuotiteCessibleService {
    /**
     * Save a paramQuotiteCessible.
     *
     * @param paramQuotiteCessibleDTO the entity to save.
     * @return the persisted entity.
     */
    ParamQuotiteCessibleDTO save(ParamQuotiteCessibleDTO paramQuotiteCessibleDTO);

    /**
     * Updates a paramQuotiteCessible.
     *
     * @param paramQuotiteCessibleDTO the entity to update.
     * @return the persisted entity.
     */
    ParamQuotiteCessibleDTO update(ParamQuotiteCessibleDTO paramQuotiteCessibleDTO);

    /**
     * Partially updates a paramQuotiteCessible.
     *
     * @param paramQuotiteCessibleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ParamQuotiteCessibleDTO> partialUpdate(ParamQuotiteCessibleDTO paramQuotiteCessibleDTO);

    /**
     * Get all the paramQuotiteCessibles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ParamQuotiteCessibleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" paramQuotiteCessible.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParamQuotiteCessibleDTO> findOne(Long id);

    /**
     * Delete the "id" paramQuotiteCessible.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
