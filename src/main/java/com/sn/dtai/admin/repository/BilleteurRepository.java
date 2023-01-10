package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Billeteur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Billeteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BilleteurRepository extends JpaRepository<Billeteur, Long> {

    @Query("SELECT billeteur FROM Billeteur billeteur")
    public List<Billeteur> findAllBilleteurs();

    @Query("SELECT billeteur FROM Billeteur billeteur WHERE billeteur.code= ?1")
    public Optional<Billeteur> findByCode(String code);
}
