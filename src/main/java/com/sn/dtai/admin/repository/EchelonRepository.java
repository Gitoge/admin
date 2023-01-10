package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Echelon;

import java.util.List;
import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Echelon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EchelonRepository extends JpaRepository<Echelon, Long> {

    @Query("SELECT echelon FROM Echelon echelon")
    public List<Echelon> findAllEchelon();

    @Query("SELECT e FROM Echelon e where e.id=?1")
    public Optional<Echelon> findEchelonById(Long echelonId);

    @Query("SELECT echelon FROM Echelon echelon WHERE echelon.code= ?1")
    public Optional<Echelon> findByCode(String code);

    @Query("SELECT echelon FROM Echelon echelon WHERE echelon.libelle= ?1")
    public Optional<Echelon> findByLibelle(String libelle);

}
