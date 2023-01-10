package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.PostesDTO;
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Postes}.
 */
public interface PostesService {
    /**
     * Save a postes.
     *
     * @param postesDTO the entity to save.
     * @return the persisted entity.
     */
    PostesDTO save(PostesDTO postesDTO);

    /**
     * Updates a postes.
     *
     * @param postesDTO the entity to update.
     * @return the persisted entity.
     */
    PostesDTO update(PostesDTO postesDTO);

    /**
     * Partially updates a postes.
     *
     * @param postesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PostesDTO> partialUpdate(PostesDTO postesDTO);

    /**
     * Get all the postes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PostesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" postes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PostesDTO> findOne(Long id);

    /**
     * Delete the "id" postes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

/**
 * Get the "code" postes.
 *
 * @param code the id of the entity.
 * @return the entity.
 */
Optional<PostesDTO> findByCodePostes(String code);



/**
 * Get the "list" postes.
 *
 * @param null
 * @return the entity.
 */
List<PostesDTO> listPostes();

/**
 * Get the "list" postes.
 *
 * @param null
 * @return the entity.
 */
List<PostesDTO> getPostesAssiettesByGrade(Long gradeId);


/**
 * Get the "list" postes by gradeId.
 *
 * @param gradeId
 * @return the entity.
 */
List<PostesDTO> getPostesByGrade(Long gradeId);

/**
 * Get the "list" postes by gradeId.
 *
 * @param gradeId
 * @return the entity.
 */
List<PostesDTO> getPostesInf600(Long gradeId);

}
