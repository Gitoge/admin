package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.EtablissementDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Etablissement}.
 */
public interface EtablissementService {
    /**
     * Save a etablissement.
     *
     * @param etablissementDTO the entity to save.
     * @return the persisted entity.
     */
    EtablissementDTO save(EtablissementDTO etablissementDTO);

    /**
     * Updates a etablissement.
     *
     * @param etablissementDTO the entity to update.
     * @return the persisted entity.
     */
    EtablissementDTO update(EtablissementDTO etablissementDTO);

    /**
     * Partially updates a etablissement.
     *
     * @param etablissementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EtablissementDTO> partialUpdate(EtablissementDTO etablissementDTO);

    /**
     * Get all the etablissements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EtablissementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" etablissement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtablissementDTO> findOne(Long id);

    /**
     * Delete the "id" etablissement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
