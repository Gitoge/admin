package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Diplomes;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Diplomes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiplomesRepository extends JpaRepository<Diplomes, Long> {
    @Query("SELECT diplomes FROM Diplomes diplomes WHERE diplomes.code= ?1")
    public Optional<Diplomes> findByCode(String code);

    @Query("SELECT diplomes FROM Diplomes diplomes WHERE diplomes.libelle= ?1")
    public Optional<Diplomes> findByLibelle(String libelle);
}
