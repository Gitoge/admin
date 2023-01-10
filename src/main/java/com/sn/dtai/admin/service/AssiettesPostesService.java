package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.AssiettesPostesDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.AssiettesPostes}.
 */
public interface AssiettesPostesService {
    /**
     * Save a assiettesPostes.
     *
     * @param assiettesPostesDTO the entity to save.
     * @return the persisted entity.
     */
    AssiettesPostesDTO save(AssiettesPostesDTO assiettesPostesDTO);

    /**
     * Updates a assiettesPostes.
     *
     * @param assiettesPostesDTO the entity to update.
     * @return the persisted entity.
     */
    AssiettesPostesDTO update(AssiettesPostesDTO assiettesPostesDTO);

    /**
     * Partially updates a assiettesPostes.
     *
     * @param assiettesPostesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AssiettesPostesDTO> partialUpdate(AssiettesPostesDTO assiettesPostesDTO);

    /**
     * Get all the assiettesPostes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssiettesPostesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" assiettesPostes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    List<AssiettesPostesDTO> findByAssiettes(Long assiettesId);


        /**
     * Get the "id" assiettesPostes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssiettesPostesDTO> findOne(Long id);

    /**
     * Delete the "id" assiettesPostes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void deleteAllByPage(Long assiettesId);

    Page<AssiettesPostesDTO> findAllAssiettesPostes(Pageable pageable);
}
