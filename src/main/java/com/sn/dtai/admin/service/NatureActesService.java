package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.NatureActesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.NatureActes}.
 */
public interface NatureActesService {
    /**
     * Save a natureActes.
     *
     * @param natureActesDTO the entity to save.
     * @return the persisted entity.
     */
    NatureActesDTO save(NatureActesDTO natureActesDTO);

    /**
     * Updates a natureActes.
     *
     * @param natureActesDTO the entity to update.
     * @return the persisted entity.
     */
    NatureActesDTO update(NatureActesDTO natureActesDTO);

    /**
     * Partially updates a natureActes.
     *
     * @param natureActesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NatureActesDTO> partialUpdate(NatureActesDTO natureActesDTO);

    /**
     * Get all the natureActes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NatureActesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" natureActes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NatureActesDTO> findOne(Long id);

    /**
     * Delete the "id" natureActes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
