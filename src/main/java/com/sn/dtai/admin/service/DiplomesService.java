package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.DiplomesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Diplomes}.
 */
public interface DiplomesService {
    /**
     * Save a diplomes.
     *
     * @param diplomesDTO the entity to save.
     * @return the persisted entity.
     */
    DiplomesDTO save(DiplomesDTO diplomesDTO);

    /**
     * Updates a diplomes.
     *
     * @param diplomesDTO the entity to update.
     * @return the persisted entity.
     */
    DiplomesDTO update(DiplomesDTO diplomesDTO);

    /**
     * Partially updates a diplomes.
     *
     * @param diplomesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DiplomesDTO> partialUpdate(DiplomesDTO diplomesDTO);

    /**
     * Get all the diplomes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DiplomesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" diplomes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DiplomesDTO> findOne(Long id);

    /**
     * Delete the "id" diplomes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
