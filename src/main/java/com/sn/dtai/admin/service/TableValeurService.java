package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TableValeurDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TableValeur}.
 */
public interface TableValeurService {
    /**
     * Save a tableValeur.
     *
     * @param tableValeurDTO the entity to save.
     * @return the persisted entity.
     */
    TableValeurDTO save(TableValeurDTO tableValeurDTO);

    /**
     * Updates a tableValeur.
     *
     * @param tableValeurDTO the entity to update.
     * @return the persisted entity.
     */
    TableValeurDTO update(TableValeurDTO tableValeurDTO);

    /**
     * Partially updates a tableValeur.
     *
     * @param tableValeurDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TableValeurDTO> partialUpdate(TableValeurDTO tableValeurDTO);

    /**
     * Get all the tableValeurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TableValeurDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tableValeur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableValeurDTO> findOne(Long id);

    /**
     * Delete the "id" tableValeur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
