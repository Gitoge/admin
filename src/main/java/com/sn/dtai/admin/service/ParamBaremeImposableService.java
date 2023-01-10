package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ParamBaremeImposableDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.ParamBaremeImposable}.
 */
public interface ParamBaremeImposableService {
    /**
     * Save a paramBaremeImposable.
     *
     * @param paramBaremeImposableDTO the entity to save.
     * @return the persisted entity.
     */
    ParamBaremeImposableDTO save(ParamBaremeImposableDTO paramBaremeImposableDTO);

    /**
     * Updates a paramBaremeImposable.
     *
     * @param paramBaremeImposableDTO the entity to update.
     * @return the persisted entity.
     */
    ParamBaremeImposableDTO update(ParamBaremeImposableDTO paramBaremeImposableDTO);

    /**
     * Partially updates a paramBaremeImposable.
     *
     * @param paramBaremeImposableDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ParamBaremeImposableDTO> partialUpdate(ParamBaremeImposableDTO paramBaremeImposableDTO);

    /**
     * Get all the paramBaremeImposables.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ParamBaremeImposableDTO> findAll(Pageable pageable);

    /**
     * Get the "id" paramBaremeImposable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParamBaremeImposableDTO> findOne(Long id);

    /**
     * Delete the "id" paramBaremeImposable.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "id" paramBaremeImposable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Integer> findBySommeandPoste(String codePoste, Integer somme);
    Optional<Integer> findBySommeAndPoste(String codePoste, Integer somme);
    Optional<Integer> sommeProgressive(String codePoste, Integer somme);
}
