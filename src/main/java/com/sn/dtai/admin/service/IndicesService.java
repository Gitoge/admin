package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.IndicesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Indices}.
 */
public interface IndicesService {
    /**
     * Save a indices.
     *
     * @param indicesDTO the entity to save.
     * @return the persisted entity.
     */
    IndicesDTO save(IndicesDTO indicesDTO);

    /**
     * Updates a indices.
     *
     * @param indicesDTO the entity to update.
     * @return the persisted entity.
     */
    IndicesDTO update(IndicesDTO indicesDTO);

    /**
     * Partially updates a indices.
     *
     * @param indicesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IndicesDTO> partialUpdate(IndicesDTO indicesDTO);

    /**
     * Get all the indices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IndicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" indices.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IndicesDTO> findOne(Long id);

    /**
     * Delete the "id" indices.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
