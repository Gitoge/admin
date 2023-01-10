package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.CategorieActes;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CategorieActes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieActesRepository extends JpaRepository<CategorieActes, Long> {
    @Query("SELECT categorieActes FROM CategorieActes categorieActes WHERE categorieActes.code= ?1")
    public Optional<CategorieActes> findByCode(String code);

    @Query("SELECT categorieActes FROM CategorieActes categorieActes WHERE categorieActes.libelle= ?1")
    public Optional<CategorieActes> findByLibelle(String libelle);
}
