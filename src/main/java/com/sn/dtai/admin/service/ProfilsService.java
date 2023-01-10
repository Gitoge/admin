package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ProfilsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Profils}.
 */
public interface ProfilsService {
    /**
     * Save a profils.
     *
     * @param profilsDTO the entity to save.
     * @return the persisted entity.
     */
    ProfilsDTO save(ProfilsDTO profilsDTO);

    /**
     * Updates a profils.
     *
     * @param profilsDTO the entity to update.
     * @return the persisted entity.
     */
    ProfilsDTO update(ProfilsDTO profilsDTO);

    /**
     * Partially updates a profils.
     *
     * @param profilsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProfilsDTO> partialUpdate(ProfilsDTO profilsDTO);

    /**
     * Get all the profils.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfilsDTO> findAll(Pageable pageable);

    /**
     * Get all the profils with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfilsDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" profils.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfilsDTO> findOne(Long id);

    /**
     * Delete the "id" profils.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
