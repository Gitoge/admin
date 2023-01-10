package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TypeEtablissementDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TypeEtablissement}.
 */
public interface TypeEtablissementService {
    /**
     * Save a typeEtablissement.
     *
     * @param typeEtablissementDTO the entity to save.
     * @return the persisted entity.
     */
    TypeEtablissementDTO save(TypeEtablissementDTO typeEtablissementDTO);

    /**
     * Updates a typeEtablissement.
     *
     * @param typeEtablissementDTO the entity to update.
     * @return the persisted entity.
     */
    TypeEtablissementDTO update(TypeEtablissementDTO typeEtablissementDTO);

    /**
     * Partially updates a typeEtablissement.
     *
     * @param typeEtablissementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeEtablissementDTO> partialUpdate(TypeEtablissementDTO typeEtablissementDTO);

    /**
     * Get all the typeEtablissements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeEtablissementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typeEtablissement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeEtablissementDTO> findOne(Long id);

    /**
     * Delete the "id" typeEtablissement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
