package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Hierarchie;
import com.sn.dtai.admin.service.dto.HierarchieDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Hierarchie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HierarchieRepository
        extends HierarchieRepositoryWithBagRelationships, JpaRepository<Hierarchie, Long> {
    default Optional<Hierarchie> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Hierarchie> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Hierarchie> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT hierarchie FROM Hierarchie hierarchie WHERE hierarchie.code= ?1")
    public Optional<Hierarchie> findByCode(String code);

    @Query("SELECT hierarchie FROM Hierarchie hierarchie WHERE hierarchie.libelle= ?1")
    public Optional<Hierarchie> findByLibelle(String libelle);

    @Query("SELECT hierarchie FROM Hierarchie hierarchie  order by code")
    public List<Hierarchie> findAllHierarchies();

    @Query("SELECT hierarchie FROM Hierarchie hierarchie order by code")
    public Page<Hierarchie> findAllHierarchies(@Param("pageable") Pageable pageable);


    @Query("SELECT hierarchie FROM Hierarchie hierarchie where ?1 BETWEEN hierarchie.codeDebut and hierarchie.codeFin")
    public Optional<Hierarchie> findHierarchieByBornes(String valeur);
}
