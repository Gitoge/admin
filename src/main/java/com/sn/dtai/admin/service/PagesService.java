package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.PagesDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Pages}.
 */
public interface PagesService {
    /**
     * Save a pages.
     *
     * @param pagesDTO the entity to save.
     * @return the persisted entity.
     */
    PagesDTO save(PagesDTO pagesDTO);

    /**
     * Updates a pages.
     *
     * @param pagesDTO the entity to update.
     * @return the persisted entity.
     */
    PagesDTO update(PagesDTO pagesDTO);

    /**
     * Partially updates a pages.
     *
     * @param pagesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PagesDTO> partialUpdate(PagesDTO pagesDTO);

    /**
     * Get all the pages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PagesDTO> findAll(Pageable pageable);

     /**
     * Get all the pages with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PagesDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" pages.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PagesDTO> findOne(Long id);

    /**
     * Delete the "id" pages.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<PagesDTO> findByRoles(List<Long> rolesIds);
}
