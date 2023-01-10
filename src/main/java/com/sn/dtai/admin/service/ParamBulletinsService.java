package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.ParamBulletinsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.ParamBulletins}.
 */
public interface ParamBulletinsService {
    /**
     * Save a paramBulletins.
     *
     * @param paramBulletinsDTO the entity to save.
     * @return the persisted entity.
     */
    ParamBulletinsDTO save(ParamBulletinsDTO paramBulletinsDTO);

    /**
     * Updates a paramBulletins.
     *
     * @param paramBulletinsDTO the entity to update.
     * @return the persisted entity.
     */
    ParamBulletinsDTO update(ParamBulletinsDTO paramBulletinsDTO);

    /**
     * Partially updates a paramBulletins.
     *
     * @param paramBulletinsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ParamBulletinsDTO> partialUpdate(ParamBulletinsDTO paramBulletinsDTO);

    /**
     * Get all the paramBulletins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ParamBulletinsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" paramBulletins.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParamBulletinsDTO> findOne(Long id);

    /**
     * Delete the "id" paramBulletins.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
