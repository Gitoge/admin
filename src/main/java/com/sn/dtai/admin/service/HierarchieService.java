package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.HierarchieDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Hierarchie}.
 */
public interface HierarchieService {
    /**
     * Save a hierarchie.
     *
     * @param hierarchieDTO the entity to save.
     * @return the persisted entity.
     */
    HierarchieDTO save(HierarchieDTO hierarchieDTO);

    /**
     * Updates a hierarchie.
     *
     * @param hierarchieDTO the entity to update.
     * @return the persisted entity.
     */
    HierarchieDTO update(HierarchieDTO hierarchieDTO);

    /**
     * Partially updates a hierarchie.
     *
     * @param hierarchieDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HierarchieDTO> partialUpdate(HierarchieDTO hierarchieDTO);

    /**
     * Get all the hierarchies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HierarchieDTO> findAll(Pageable pageable);

    /**
     * Get all the hierarchies with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HierarchieDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" hierarchie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HierarchieDTO> findOne(Long id);

    /**
     * Delete the "id" hierarchie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
