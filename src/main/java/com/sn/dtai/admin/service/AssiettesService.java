package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.AssiettesDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Assiettes}.
 */
public interface AssiettesService {
    /**
     * Save a assiettes.
     *
     * @param assiettesDTO the entity to save.
     * @return the persisted entity.
     */
    AssiettesDTO save(AssiettesDTO assiettesDTO);

    /**
     * Updates a assiettes.
     *
     * @param assiettesDTO the entity to update.
     * @return the persisted entity.
     */
    AssiettesDTO update(AssiettesDTO assiettesDTO);

    /**
     * Partially updates a assiettes.
     *
     * @param assiettesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AssiettesDTO> partialUpdate(AssiettesDTO assiettesDTO);

    /**
     * Get all the assiettes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssiettesDTO> findAll(Pageable pageable);

    /**
     * Get all the assiettes with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssiettesDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" assiettes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssiettesDTO> findOne(Long id);


     /**
     * Get the "code" assiettes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssiettesDTO> findOne(String code);
    

    /**
     * Delete the "id" assiettes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
