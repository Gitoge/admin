package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.ParamBaremeImposable;

import com.sn.dtai.admin.service.dto.SommeProgressiveDTO;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ParamBaremeImposable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamBaremeImposableRepository extends JpaRepository<ParamBaremeImposable, Long> {

    @Query(value = "SELECT pbi.montant FROM ParamBaremeImposable pbi WHERE (pbi.code =?1 and ?2 BETWEEN pbi.salaireDebut and pbi.salaireFin )")
    public Optional<Integer> findBySommeandPoste(String codePoste, Integer somme);

    @Query(value = "SELECT pbi.montant FROM ParamBaremeImposable pbi WHERE (pbi.code =?1 and ?1 = '623' and ?2 BETWEEN pbi.salaireDebut and pbi.salaireFin )")
    public Optional<Integer> findBySommeAndPoste(String codePoste, Integer somme);

    @Query(value = "SELECT SUM(pbi.montant) FROM ParamBaremeImposable pbi WHERE (pbi.code ='623'and ?1 ='623' and  pbi.salaireFin < ?2)")
    public Optional<Integer> sommeProgressive(String codePoste, Integer somme);

    // @Query("SELECT new com.sn.dtai.admin.service.dto.SommeProgressiveDTO(400, 600) FROM ParamBaremeImposable param ")
    // public Optional<Integer> sommeProgressive(String codePoste, Integer somme);
}
