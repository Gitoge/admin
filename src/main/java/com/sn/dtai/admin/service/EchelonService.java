package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.EchelonDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Echelon}.
 */
public interface EchelonService {
    /**
     * Save a echelon.
     *
     * @param echelonDTO the entity to save.
     * @return the persisted entity.
     */
    EchelonDTO save(EchelonDTO echelonDTO);

    /**
     * Updates a echelon.
     *
     * @param echelonDTO the entity to update.
     * @return the persisted entity.
     */
    EchelonDTO update(EchelonDTO echelonDTO);

    /**
     * Partially updates a echelon.
     *
     * @param echelonDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EchelonDTO> partialUpdate(EchelonDTO echelonDTO);

    /**
     * Get all the echelons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EchelonDTO> findAll(Pageable pageable);

    /**
     * Get the "id" echelon.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EchelonDTO> findOne(Long id);

    /**
     * Delete the "id" echelon.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
