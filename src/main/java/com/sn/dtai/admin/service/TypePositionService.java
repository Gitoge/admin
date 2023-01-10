package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TypePositionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TypePosition}.
 */
public interface TypePositionService {
    /**
     * Save a typePosition.
     *
     * @param typePositionDTO the entity to save.
     * @return the persisted entity.
     */
    TypePositionDTO save(TypePositionDTO typePositionDTO);

    /**
     * Updates a typePosition.
     *
     * @param typePositionDTO the entity to update.
     * @return the persisted entity.
     */
    TypePositionDTO update(TypePositionDTO typePositionDTO);

    /**
     * Partially updates a typePosition.
     *
     * @param typePositionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypePositionDTO> partialUpdate(TypePositionDTO typePositionDTO);

    /**
     * Get all the typePositions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypePositionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typePosition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypePositionDTO> findOne(Long id);

    /**
     * Delete the "id" typePosition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
