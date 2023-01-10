package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Destinataires;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Destinataires entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DestinatairesRepository extends JpaRepository<Destinataires, Long> {

    @Query("SELECT td FROM Destinataires td WHERE td.code= ?1")
    public Optional<Destinataires> findByCode(String code);
}
