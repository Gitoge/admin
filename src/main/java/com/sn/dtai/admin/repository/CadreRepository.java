package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Cadre;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Cadre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CadreRepository extends JpaRepository<Cadre, Long> {
    @Query("SELECT cadre FROM Cadre cadre WHERE cadre.code= ?1")
    public Optional<Cadre> findByCode(String code);

    @Query("SELECT cadre FROM Cadre cadre WHERE cadre.libelle= ?1")
    public Optional<Cadre> findByLibelle(String libelle);
}
