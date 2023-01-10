package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TypeDestinatairesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TypeDestinataires}.
 */
public interface TypeDestinatairesService {
    /**
     * Save a typeDestinataires.
     *
     * @param typeDestinatairesDTO the entity to save.
     * @return the persisted entity.
     */
    TypeDestinatairesDTO save(TypeDestinatairesDTO typeDestinatairesDTO);

    /**
     * Updates a typeDestinataires.
     *
     * @param typeDestinatairesDTO the entity to update.
     * @return the persisted entity.
     */
    TypeDestinatairesDTO update(TypeDestinatairesDTO typeDestinatairesDTO);

    /**
     * Partially updates a typeDestinataires.
     *
     * @param typeDestinatairesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeDestinatairesDTO> partialUpdate(TypeDestinatairesDTO typeDestinatairesDTO);

    /**
     * Get all the typeDestinataires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeDestinatairesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typeDestinataires.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeDestinatairesDTO> findOne(Long id);

    /**
     * Delete the "id" typeDestinataires.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
