package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.EtablissementBancaire;
import com.sn.dtai.admin.domain.Localite;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EtablissementBancaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtablissementBancaireRepository extends JpaRepository<EtablissementBancaire, Long> {
    
    @Query("SELECT etablissementBancaire FROM EtablissementBancaire etablissementBancaire ORDER BY etablissementBancaire.sigle")
    public List<EtablissementBancaire> findAllEtablissementBancaires();

    @Query("SELECT etablissementBancaire FROM EtablissementBancaire etablissementBancaire WHERE etablissementBancaire.code= ?1")
    public Optional<EtablissementBancaire> findByCode(String code);

    
    @Query(value = "SELECT etablissementBancaire FROM EtablissementBancaire etablissementBancaire INNER JOIN Agence agence ON (etablissementBancaire.id = agence.etablissementBancaire)  WHERE etablissementBancaire.id =:etablissementBancaireId")
	public List<EtablissementBancaire> getEtablissementByAgence(@Param("etablissementBancaireId") Long etablissementBancaireId);
}
