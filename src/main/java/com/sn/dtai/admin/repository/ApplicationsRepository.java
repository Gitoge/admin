package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Applications;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Applications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationsRepository extends JpaRepository<Applications, Long> {
    @Query("SELECT applications FROM Applications applications WHERE applications.code= ?1")
    public Optional<Applications> findByCode(String code);

    @Query("SELECT applications FROM Applications applications WHERE applications.nom= ?1")
    public Optional<Applications> findByNom(String nom);
}
