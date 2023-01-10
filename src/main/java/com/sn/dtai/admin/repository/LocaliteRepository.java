package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Localite;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Localite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocaliteRepository extends JpaRepository<Localite, Long> {
    @Query(
        value = "SELECT l FROM Localite l INNER JOIN TypeLocalite tl ON (l.typeLocalite.id = tl.id) WHERE tl.code=:codeTypeLocalite ORDER BY l.libelle ASC"
    )
    List<Localite> findAllByTypeLocalite(@Param("codeTypeLocalite") String codeTypeLocalite);

    @Query(value = "SELECT l FROM Localite l WHERE l.localites.id=:localite")
    List<Localite> findAllByRattachement(@Param("localite") Long localite);

    @Query("SELECT localite FROM Localite localite WHERE localite.code= ?1")
    public Optional<Localite> findByCode(String code);

    @Query("SELECT localite FROM Localite localite WHERE localite.libelle= ?1")
    public Optional<Localite> findByLibelle(String libelle);

    //  OR (localite.localites.libelle like %?1%) OR (localite.typeLocalite.libelle like %?1%) OR (localite.pays.libelle like %?1%)  )
    @Query(
        "SELECT localite FROM Localite as localite WHERE ((localite.code like %?1%) OR (localite.libelle like %?1%)) OR (localite.typeLocalite.libelle like %?1%) ORDER BY localite.libelle"
    )
    public Page<Localite> rechercheLocaliteByMotsCles(String motCles, Pageable pageable);

    @Query(value = "SELECT L FROM Localite L INNER JOIN Etablissement e ON (L.id = e.localite)  WHERE L.id =:localiteId")
	public List<Localite> getLocalitesByEtEtablissement(@Param("localiteId") Long localiteId);

    @Query(value = "SELECT L FROM Localite L INNER JOIN Localite l ON (L.id = l.localites)  WHERE L.id =:localiteId")
	public List<Localite> getLocalitesByLocalite(@Param("localiteId") Long localiteId);
}
