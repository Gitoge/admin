package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.StructureAdmin;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the StructureAdmin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StructureAdminRepository extends JpaRepository<StructureAdmin, Long> {
    @Query("SELECT structure FROM StructureAdmin structure WHERE structure.code= ?1")
    public Optional<StructureAdmin> findByCode(String code);

    @Query("SELECT structure FROM StructureAdmin structure WHERE structure.libelle= ?1")
    public Optional<StructureAdmin> findByLibelle(String libelle);
}
