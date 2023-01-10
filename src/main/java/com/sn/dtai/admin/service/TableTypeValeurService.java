package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.TableTypeValeurDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.TableTypeValeur}.
 */
public interface TableTypeValeurService {
    /**
     * Save a tableTypeValeur.
     *
     * @param tableTypeValeurDTO the entity to save.
     * @return the persisted entity.
     */
    TableTypeValeurDTO save(TableTypeValeurDTO tableTypeValeurDTO);

    /**
     * Updates a tableTypeValeur.
     *
     * @param tableTypeValeurDTO the entity to update.
     * @return the persisted entity.
     */
    TableTypeValeurDTO update(TableTypeValeurDTO tableTypeValeurDTO);

    /**
     * Partially updates a tableTypeValeur.
     *
     * @param tableTypeValeurDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TableTypeValeurDTO> partialUpdate(TableTypeValeurDTO tableTypeValeurDTO);

    /**
     * Get all the tableTypeValeurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TableTypeValeurDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tableTypeValeur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableTypeValeurDTO> findOne(Long id);

    /**
     * Delete the "id" tableTypeValeur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
