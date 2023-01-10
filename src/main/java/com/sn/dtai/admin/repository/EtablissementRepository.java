package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Etablissement;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Etablissement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
    @Query("SELECT etablissement FROM Etablissement etablissement WHERE etablissement.id= ?1")
    public Optional<Etablissement> findByIdEtablissement(Long idEtablissement);

    @Query("SELECT etablissement FROM Etablissement etablissement WHERE etablissement.code= ?1")
    public Optional<Etablissement> findByCode(String code);

    @Query("SELECT etablissement FROM Etablissement etablissement WHERE etablissement.libelleCourt= ?1")
    public Optional<Etablissement> findByLibelle(String libelle);

    @Query(value = "SELECT etablissement FROM Etablissement etablissement WHERE etablissement.localite.id=:localiteId")
    List<Etablissement> findByLocalite(@Param("localiteId") Long localiteId);

    @Query(
        "SELECT etablissement FROM Etablissement as etablissement WHERE ((etablissement.sigle like %?1%) OR (etablissement.code like %?1%) OR (etablissement.email like %?1%) OR (etablissement.libelleLong like %?1%) OR (etablissement.typeEtab.libelle like %?1%) OR (etablissement.numTelephone like %?1%)  ) ORDER BY etablissement.libelleCourt"
    )
    public Page<Etablissement> recherchesEtablissement(String motCles, Pageable pageable);

    @Query("SELECT etablissement FROM Etablissement etablissement ORDER BY etablissement.libelleLong")
    public List<Etablissement> findAllEtablissement();

    @Query("SELECT etablissement FROM Etablissement etablissement ORDER BY etablissement.libelleLong")
    public Page<Etablissement> findAllEtablissementPage(Pageable pageable);

    @Query(value = "SELECT etablissement FROM Etablissement etablissement WHERE etablissement.id=?1")
    List<Etablissement> findByEtablissementId(Long etablissementId);
}
