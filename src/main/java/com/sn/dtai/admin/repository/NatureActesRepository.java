package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.NatureActes;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NatureActes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NatureActesRepository extends JpaRepository<NatureActes, Long> {
    @Query("SELECT natureActes FROM NatureActes natureActes WHERE natureActes.code= ?1")
    public Optional<NatureActes> findByCode(String code);

    @Query("SELECT natureActes FROM NatureActes natureActes WHERE natureActes.libelle= ?1")
    public Optional<NatureActes> findByLibelle(String libelle);
}
