package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TypeLocalite;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypeLocalite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeLocaliteRepository extends JpaRepository<TypeLocalite, Long> {
    @Query("SELECT typeLocalite FROM TypeLocalite typeLocalite WHERE typeLocalite.code= ?1")
    public Optional<TypeLocalite> findByCode(String code);

    @Query("SELECT typeLocalite FROM TypeLocalite typeLocalite WHERE typeLocalite.libelle= ?1")
    public Optional<TypeLocalite> findByLibelle(String libelle);

    @Query("SELECT typeLocalite FROM TypeLocalite typeLocalite ORDER BY typeLocalite.libelle")
    public Page<TypeLocalite> findAllTypeLocalite(Pageable pageable);

    @Query("SELECT typeLocalite FROM TypeLocalite as typeLocalite WHERE typeLocalite.libelle like %?1% ORDER BY typeLocalite.libelle")
    public Page<TypeLocalite> rechercheTypeLocaliteByMotsCles(String motCles, Pageable pageable);

    @Query(value = "SELECT tl FROM TypeLocalite tl INNER JOIN Localite l ON (tl.id = l.typeLocalite)  WHERE tl.id =:typeLocalite")
	public List<TypeLocalite> getLocalitesByTypeLocalite(@Param("typeLocalite") Long typeLocalite);
}
