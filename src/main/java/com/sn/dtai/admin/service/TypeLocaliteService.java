package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TypeLocaliteDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TypeLocalite}.
 */
public interface TypeLocaliteService {
    /**
     * Save a typeLocalite.
     *
     * @param typeLocaliteDTO the entity to save.
     * @return the persisted entity.
     */
    TypeLocaliteDTO save(TypeLocaliteDTO typeLocaliteDTO);

    /**
     * Updates a typeLocalite.
     *
     * @param typeLocaliteDTO the entity to update.
     * @return the persisted entity.
     */
    TypeLocaliteDTO update(TypeLocaliteDTO typeLocaliteDTO);

    /**
     * Partially updates a typeLocalite.
     *
     * @param typeLocaliteDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeLocaliteDTO> partialUpdate(TypeLocaliteDTO typeLocaliteDTO);

    /**
     * Get all the typeLocalites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeLocaliteDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typeLocalite.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeLocaliteDTO> findOne(Long id);

    /**
     * Delete the "id" typeLocalite.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
