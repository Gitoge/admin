package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ServicesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Service}.
 */
public interface ServicesService {
    /**
     * Save a service.
     *
     * @param serviceDTO the entity to save.
     * @return the persisted entity.
     */
    ServicesDTO save(ServicesDTO serviceDTO);

    /**
     * Updates a service.
     *
     * @param serviceDTO the entity to update.
     * @return the persisted entity.
     */
    ServicesDTO update(ServicesDTO serviceDTO);

    /**
     * Partially updates a service.
     *
     * @param serviceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ServicesDTO> partialUpdate(ServicesDTO serviceDTO);

    /**
     * Get all the services.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServicesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" service.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServicesDTO> findOne(Long id);

    /**
     * Delete the "id" service.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
