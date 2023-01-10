package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.CadreDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Cadre}.
 */
public interface CadreService {
    /**
     * Save a cadre.
     *
     * @param cadreDTO the entity to save.
     * @return the persisted entity.
     */
    CadreDTO save(CadreDTO cadreDTO);

    /**
     * Updates a cadre.
     *
     * @param cadreDTO the entity to update.
     * @return the persisted entity.
     */
    CadreDTO update(CadreDTO cadreDTO);

    /**
     * Partially updates a cadre.
     *
     * @param cadreDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CadreDTO> partialUpdate(CadreDTO cadreDTO);

    /**
     * Get all the cadres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CadreDTO> findAll(Pageable pageable);

    /**
     * Get the "id" cadre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CadreDTO> findOne(Long id);

    /**
     * Delete the "id" cadre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
