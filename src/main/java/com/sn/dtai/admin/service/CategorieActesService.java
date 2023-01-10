package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.CategorieActesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.CategorieActes}.
 */
public interface CategorieActesService {
    /**
     * Save a categorieActes.
     *
     * @param categorieActesDTO the entity to save.
     * @return the persisted entity.
     */
    CategorieActesDTO save(CategorieActesDTO categorieActesDTO);

    /**
     * Updates a categorieActes.
     *
     * @param categorieActesDTO the entity to update.
     * @return the persisted entity.
     */
    CategorieActesDTO update(CategorieActesDTO categorieActesDTO);

    /**
     * Partially updates a categorieActes.
     *
     * @param categorieActesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CategorieActesDTO> partialUpdate(CategorieActesDTO categorieActesDTO);

    /**
     * Get all the categorieActes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategorieActesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" categorieActes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategorieActesDTO> findOne(Long id);

    /**
     * Delete the "id" categorieActes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
