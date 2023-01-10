package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.HierarchieCategorieDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.HierarchieCategorie}.
 */
public interface HierarchieCategorieService {
    /**
     * Save a hierarchieCategorie.
     *
     * @param hierarchieCategorieDTO the entity to save.
     * @return the persisted entity.
     */
    HierarchieCategorieDTO save(HierarchieCategorieDTO hierarchieCategorieDTO);

    /**
     * Updates a hierarchieCategorie.
     *
     * @param hierarchieCategorieDTO the entity to update.
     * @return the persisted entity.
     */
    HierarchieCategorieDTO update(HierarchieCategorieDTO hierarchieCategorieDTO);

    /**
     * Partially updates a hierarchieCategorie.
     *
     * @param hierarchieCategorieDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HierarchieCategorieDTO> partialUpdate(HierarchieCategorieDTO hierarchieCategorieDTO);

    /**
     * Get all the hierarchieCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HierarchieCategorieDTO> findAll(Pageable pageable);

    /**
     * Get the "id" hierarchieCategorie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HierarchieCategorieDTO> findOne(Long id);

    /**
     * Delete the "id" hierarchieCategorie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void deleteAllByPage(Long hierarchieId);

    Page<HierarchieCategorieDTO> findAllHierarchieCategorie(Pageable pageable);

    /**
     * Get the "id" hierarchieCategorie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HierarchieCategorieDTO> findHierarchieByCategorie(Long categorieId);
}
