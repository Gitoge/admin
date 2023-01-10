package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Positions;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Positions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PositionsRepository extends JpaRepository<Positions, Long> {

    @Query("SELECT positions FROM Positions positions")
    public List<Positions> findAllPositions();

    @Query("SELECT pos FROM Positions pos WHERE pos.libelle= ?1")
    public Optional<Positions> findByLibelle(String libelle);

    @Query("SELECT pos FROM Positions pos WHERE pos.code= ?1")
    public Optional<Positions> findByCode(String code);


    @Query("SELECT pos FROM Positions pos WHERE pos.code= '10'")
    public Optional<Positions> enActivite();
    
}
