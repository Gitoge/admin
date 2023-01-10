package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.PositionsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Positions}.
 */
public interface PositionsService {
    /**
     * Save a positions.
     *
     * @param positionsDTO the entity to save.
     * @return the persisted entity.
     */
    PositionsDTO save(PositionsDTO positionsDTO);

    /**
     * Updates a positions.
     *
     * @param positionsDTO the entity to update.
     * @return the persisted entity.
     */
    PositionsDTO update(PositionsDTO positionsDTO);

    /**
     * Partially updates a positions.
     *
     * @param positionsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PositionsDTO> partialUpdate(PositionsDTO positionsDTO);

    /**
     * Get all the positions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PositionsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" positions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PositionsDTO> findOne(Long id);

    /**
     * Delete the "id" positions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
