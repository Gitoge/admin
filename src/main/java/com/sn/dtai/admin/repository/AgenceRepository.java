package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Agence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Agence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {
    
    @Query("SELECT agences FROM Agence agences ")
    public List<Agence> findAllAgences();

    @Query("SELECT agences FROM Agence agences WHERE agences.code= ?1")
    public Optional<Agence> findByCode(String code);
}
