package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TypeEtablissement;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypeEtablissement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeEtablissementRepository extends JpaRepository<TypeEtablissement, Long> {
    @Query("SELECT typeEtablissement FROM TypeEtablissement typeEtablissement WHERE typeEtablissement.code= ?1")
    public Optional<TypeEtablissement> findByCode(String code);

    @Query("SELECT typeEtablissement FROM TypeEtablissement typeEtablissement WHERE typeEtablissement.libelle= ?1")
    public Optional<TypeEtablissement> findByLibelle(String libelle);

    @Query("SELECT typeEtablissement FROM TypeEtablissement typeEtablissement ORDER BY typeEtablissement.libelle")
    public Page<TypeEtablissement> findAllTypeEtablissement(Pageable pageable);

    @Query(
        "SELECT typeEtablissement FROM TypeEtablissement as typeEtablissement WHERE typeEtablissement.libelle like %?1% ORDER BY typeEtablissement.libelle"
    )
    public Page<TypeEtablissement> rechercheTypeEtablissementByMotsCles(String motCles, Pageable pageable);
   
    @Query(value = "SELECT tl FROM TypeEtablissement tl INNER JOIN Etablissement e ON (tl.id = e.typeEtab)  WHERE tl.id =:typeEtablissement")
	public List<TypeEtablissement> getEtablissementsByTypeEtablissement(@Param("typeEtablissement") Long typeEtablissement);
}
