package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Corps;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Corps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CorpsRepository extends CorpsRepositoryWithBagRelationships, JpaRepository<Corps, Long> {
    default Optional<Corps> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Corps> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Corps> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT corps FROM Corps corps WHERE corps.code= ?1")
    public Optional<Corps> findByCode(String code);

    @Query("SELECT corps FROM Corps corps WHERE corps.libelle= ?1")
    public Optional<Corps> findByLibelle(String libelle);

    @Query("SELECT corps FROM Corps corps ORDER BY corps.libelle")
    public List<Corps> findAllCorps();
}
