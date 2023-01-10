package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.StructureAdminDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.StructureAdmin}.
 */
public interface StructureAdminService {
    /**
     * Save a structureAdmin.
     *
     * @param structureAdminDTO the entity to save.
     * @return the persisted entity.
     */
    StructureAdminDTO save(StructureAdminDTO structureAdminDTO);

    /**
     * Updates a structureAdmin.
     *
     * @param structureAdminDTO the entity to update.
     * @return the persisted entity.
     */
    StructureAdminDTO update(StructureAdminDTO structureAdminDTO);

    /**
     * Partially updates a structureAdmin.
     *
     * @param structureAdminDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StructureAdminDTO> partialUpdate(StructureAdminDTO structureAdminDTO);

    /**
     * Get all the structureAdmins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<StructureAdminDTO> findAll(Pageable pageable);

    /**
     * Get the "id" structureAdmin.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StructureAdminDTO> findOne(Long id);

    /**
     * Delete the "id" structureAdmin.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
