package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ParamBaremeMinimumFiscalDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.ParamBaremeMinimumFiscal}.
 */
public interface ParamBaremeMinimumFiscalService {
    /**
     * Save a paramBaremeMinimumFiscal.
     *
     * @param paramBaremeMinimumFiscalDTO the entity to save.
     * @return the persisted entity.
     */
    ParamBaremeMinimumFiscalDTO save(ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO);

    /**
     * Updates a paramBaremeMinimumFiscal.
     *
     * @param paramBaremeMinimumFiscalDTO the entity to update.
     * @return the persisted entity.
     */
    ParamBaremeMinimumFiscalDTO update(ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO);

    /**
     * Partially updates a paramBaremeMinimumFiscal.
     *
     * @param paramBaremeMinimumFiscalDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ParamBaremeMinimumFiscalDTO> partialUpdate(ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO);

    /**
     * Get all the paramBaremeMinimumFiscals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ParamBaremeMinimumFiscalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" paramBaremeMinimumFiscal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParamBaremeMinimumFiscalDTO> findOne(Long id);

    /**
     * Delete the "id" paramBaremeMinimumFiscal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
