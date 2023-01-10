package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Assiettes;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Assiettes entity.
 */
@Repository
public interface AssiettesRepository extends AssiettesRepositoryWithBagRelationships, JpaRepository<Assiettes, Long> {
    default Optional<Assiettes> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Assiettes> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Assiettes> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
    
    @Query("SELECT assiettes FROM Assiettes assiettes WHERE assiettes.code= ?1")
    public Optional<Assiettes> findByCodePoste(String code);

    Optional<Assiettes> findByCode(String code);

}
