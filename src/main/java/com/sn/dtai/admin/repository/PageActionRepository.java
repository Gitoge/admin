package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.PageAction;
import com.sn.dtai.admin.service.dto.PagesActionsDto;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Spring Data JPA repository for the PageAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PageActionRepository extends JpaRepository<PageAction, Long> {
    @Modifying
    @Transactional
    @Query("update PageAction pageAction set pageAction.etat=false WHERE pageAction.pages.id= ?1")
    public void deleteAllByPage(Long pageId);

    /* @Query("SELECT pageAction FROM PageAction pageAction")
    public List<PageAction> findAllPageAction(); */

    @Query(value = "SELECT new com.sn.dtai.admin.service.dto.PagesActionsDto(pa.id, pa.pages.id, pa.actions.id, pa.pages.libelle, pa.actions.libelle) FROM PageAction pa where pa.etat = true")
    Page<PagesActionsDto> findAllPageAction(Pageable pageable);
}
