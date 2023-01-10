package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.PageActionDTO;
import com.sn.dtai.admin.service.dto.PagesActionsDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.PageAction}.
 */
public interface PageActionService {
    /**
     * Save a pageAction.
     *
     * @param pageActionDTO the entity to save.
     * @return the persisted entity.
     */
    PageActionDTO save(PageActionDTO pageActionDTO);

    /**
     * Updates a pageAction.
     *
     * @param pageActionDTO the entity to update.
     * @return the persisted entity.
     */
    PageActionDTO update(PageActionDTO pageActionDTO);

    /**
     * Partially updates a pageAction.
     *
     * @param pageActionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PageActionDTO> partialUpdate(PageActionDTO pageActionDTO);

    /**
     * Get all the pageActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PageActionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" pageAction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PageActionDTO> findOne(Long id);

    /**
     * Delete the "id" pageAction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void deleteAllByPage(Long pageId);

    Page<PagesActionsDto> findAllPageAction(Pageable pageable);
}
