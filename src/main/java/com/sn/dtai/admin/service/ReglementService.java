package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ReglementDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Reglement}.
 */
public interface ReglementService {
    /**
     * Save a reglement.
     *
     * @param reglementDTO the entity to save.
     * @return the persisted entity.
     */
    ReglementDTO save(ReglementDTO reglementDTO);

    /**
     * Updates a reglement.
     *
     * @param reglementDTO the entity to update.
     * @return the persisted entity.
     */
    ReglementDTO update(ReglementDTO reglementDTO);

    /**
     * Partially updates a reglement.
     *
     * @param reglementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ReglementDTO> partialUpdate(ReglementDTO reglementDTO);

    /**
     * Get all the reglements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReglementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" reglement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReglementDTO> findOne(Long id);

    /**
     * Delete the "id" reglement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
