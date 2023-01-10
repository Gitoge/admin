package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ParamPartsFiscalesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.ParamPartsFiscales}.
 */
public interface ParamPartsFiscalesService {
    /**
     * Save a paramPartsFiscales.
     *
     * @param paramPartsFiscalesDTO the entity to save.
     * @return the persisted entity.
     */
    ParamPartsFiscalesDTO save(ParamPartsFiscalesDTO paramPartsFiscalesDTO);

    /**
     * Updates a paramPartsFiscales.
     *
     * @param paramPartsFiscalesDTO the entity to update.
     * @return the persisted entity.
     */
    ParamPartsFiscalesDTO update(ParamPartsFiscalesDTO paramPartsFiscalesDTO);

    /**
     * Partially updates a paramPartsFiscales.
     *
     * @param paramPartsFiscalesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ParamPartsFiscalesDTO> partialUpdate(ParamPartsFiscalesDTO paramPartsFiscalesDTO);

    /**
     * Get all the paramPartsFiscales.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ParamPartsFiscalesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" paramPartsFiscales.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParamPartsFiscalesDTO> findOne(Long id);

    /**
     * Delete the "id" paramPartsFiscales.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
