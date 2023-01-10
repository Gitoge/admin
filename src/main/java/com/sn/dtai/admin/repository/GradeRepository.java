package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Grade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Grade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GradeRepository extends GradeRepositoryWithBagRelationships, JpaRepository<Grade, Long> {
    default Optional<Grade> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Grade> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Grade> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT grade FROM Grade grade WHERE grade.code= ?1")
    public Optional<Grade> findByCode(String code);

    @Query("SELECT grade FROM Grade grade WHERE grade.libelle= ?1")
    public Optional<Grade> findByLibelle(String libelle);

    @Query("SELECT grade FROM Grade grade ORDER BY grade.libelle")
    public List<Grade> findAllGrades();

    @Query("SELECT grade FROM Grade grade WHERE (?1 = '' OR grade.libelle like %?1%  OR grade.code like %?1%)")
    public Page<Grade> rechercheGrades(String motCle,Pageable pageable);
}
