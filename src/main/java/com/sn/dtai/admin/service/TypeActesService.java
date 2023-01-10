package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TypeActesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TypeActes}.
 */
public interface TypeActesService {
    /**
     * Save a typeActes.
     *
     * @param typeActesDTO the entity to save.
     * @return the persisted entity.
     */
    TypeActesDTO save(TypeActesDTO typeActesDTO);

    /**
     * Updates a typeActes.
     *
     * @param typeActesDTO the entity to update.
     * @return the persisted entity.
     */
    TypeActesDTO update(TypeActesDTO typeActesDTO);

    /**
     * Partially updates a typeActes.
     *
     * @param typeActesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeActesDTO> partialUpdate(TypeActesDTO typeActesDTO);

    /**
     * Get all the typeActes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeActesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typeActes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeActesDTO> findOne(Long id);

    /**
     * Delete the "id" typeActes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
