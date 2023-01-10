package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.HierarchieCategorie;
import com.sn.dtai.admin.service.dto.HierarchieCategorieDTO;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Spring Data JPA repository for the HierarchieCategorie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HierarchieCategorieRepository extends JpaRepository<HierarchieCategorie, Long> {
    // @Modifying
    // @Transactional
    // @Query("update HierarchieCategorie hierarchiePoste set hierarchiePoste.etat=false WHERE hierarchiePoste.hierarchie.id= ?1")
    // public void deleteAllByHierarchie(Long hierarchieId);

    @Query("SELECT hierarchieCategorie FROM HierarchieCategorie hierarchieCategorie where hierarchieCategorie.categories.id =?1 ")
    public Optional<HierarchieCategorie> findHierarchieByCategorie(Long categorieId);
}
