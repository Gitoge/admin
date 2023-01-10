package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.DestinatairesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Destinataires}.
 */
public interface DestinatairesService {
    /**
     * Save a destinataires.
     *
     * @param destinatairesDTO the entity to save.
     * @return the persisted entity.
     */
    DestinatairesDTO save(DestinatairesDTO destinatairesDTO);

    /**
     * Updates a destinataires.
     *
     * @param destinatairesDTO the entity to update.
     * @return the persisted entity.
     */
    DestinatairesDTO update(DestinatairesDTO destinatairesDTO);

    /**
     * Partially updates a destinataires.
     *
     * @param destinatairesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DestinatairesDTO> partialUpdate(DestinatairesDTO destinatairesDTO);

    /**
     * Get all the destinataires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DestinatairesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" destinataires.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DestinatairesDTO> findOne(Long id);

    /**
     * Delete the "id" destinataires.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
