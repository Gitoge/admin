package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ApplicationsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Applications}.
 */
public interface ApplicationsService {
    /**
     * Save a applications.
     *
     * @param applicationsDTO the entity to save.
     * @return the persisted entity.
     */
    ApplicationsDTO save(ApplicationsDTO applicationsDTO);

    /**
     * Updates a applications.
     *
     * @param applicationsDTO the entity to update.
     * @return the persisted entity.
     */
    ApplicationsDTO update(ApplicationsDTO applicationsDTO);

    /**
     * Partially updates a applications.
     *
     * @param applicationsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApplicationsDTO> partialUpdate(ApplicationsDTO applicationsDTO);

    /**
     * Get all the applications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApplicationsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" applications.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApplicationsDTO> findOne(Long id);

    /**
     * Delete the "id" applications.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
