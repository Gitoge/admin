package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TableValeur;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TableValeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableValeurRepository extends JpaRepository<TableValeur, Long> 
{
    @Query(value="SELECT t FROM TableValeur t WHERE t.tabletypevaleur.code =:code ORDER BY t.libelle ASC")
	List<TableValeur> findTableValeurByType(@Param("code") String code);

	@Query(value="SELECT t FROM TableValeur t WHERE t.tabletypevaleur.code =:code")
	List<TableValeur> findChampIntervention(@Param("code") String code);

	Optional<TableValeur> findByCode(String code);
	Optional<TableValeur> findByLibelle(String libelle);
}
