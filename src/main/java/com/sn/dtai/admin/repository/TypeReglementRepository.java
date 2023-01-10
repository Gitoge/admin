package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TypeReglement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypeReglement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeReglementRepository extends JpaRepository<TypeReglement, Long> {
    @Query(value = "SELECT tr FROM TypeReglement tr INNER JOIN Reglement r ON (tr.id = r.typereglement)  WHERE tr.id =:typeReglement")
	public List<TypeReglement> getReglementsByTypeReglement(@Param("typeReglement") Long typeReglement);

    @Query("SELECT td FROM TypeReglement td WHERE td.code= ?1")
    public Optional<TypeReglement> findByCode(String code);
}
