package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Emplois;
import com.sn.dtai.admin.domain.Postes;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Emplois entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmploisRepository extends EmploisRepositoryWithBagRelationships, JpaRepository<Emplois, Long> {
    default Optional<Emplois> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Emplois> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Emplois> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
    Optional<Emplois> findEmploisByCode(String code);


    @Query("SELECT emplois FROM Emplois emplois order by libelle")
    public List<Emplois> findEmplois();

    @Query("SELECT emplois FROM Emplois emplois WHERE (?1 = '' OR emplois.libelle like %?1%  OR emplois.code like %?1%)")
    public Page<Emplois> rechercheEmplois(String motCle,Pageable pageable);


    @Query("SELECT emplois FROM Emplois emplois WHERE emplois.code= ?1")
    public Optional<Emplois> findByCode(String code);


    @Query("SELECT postes FROM Emplois emplois INNER JOIN emplois.postes postes where emplois.id = ?1")
    public List<Postes> getPostesByEmplois(Long emplois);

}
