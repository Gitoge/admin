package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TypeReglementDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TypeReglement}.
 */
public interface TypeReglementService {
    /**
     * Save a typeReglement.
     *
     * @param typeReglementDTO the entity to save.
     * @return the persisted entity.
     */
    TypeReglementDTO save(TypeReglementDTO typeReglementDTO);

    /**
     * Updates a typeReglement.
     *
     * @param typeReglementDTO the entity to update.
     * @return the persisted entity.
     */
    TypeReglementDTO update(TypeReglementDTO typeReglementDTO);

    /**
     * Partially updates a typeReglement.
     *
     * @param typeReglementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeReglementDTO> partialUpdate(TypeReglementDTO typeReglementDTO);

    /**
     * Get all the typeReglements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeReglementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typeReglement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeReglementDTO> findOne(Long id);

    /**
     * Delete the "id" typeReglement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
