package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Reglement;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Reglement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReglementRepository extends JpaRepository<Reglement, Long> {
    @Query("SELECT reglement FROM Reglement reglement WHERE reglement.code= ?1")
    public Optional<Reglement> findByCode(String code);

    @Query("SELECT reglement FROM Reglement reglement WHERE reglement.libelle= ?1")
    public Optional<Reglement> findByLibelle(String libelle);
}
