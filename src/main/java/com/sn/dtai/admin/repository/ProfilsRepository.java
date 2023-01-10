package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Profils;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Profils entity.
 */
@Repository
public interface ProfilsRepository extends ProfilsRepositoryWithBagRelationships, JpaRepository<Profils, Long> {
    default Optional<Profils> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Profils> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Profils> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT profils FROM Profils profils WHERE profils.code= ?1")
    public Optional<Profils> findByCode(String code);

    @Query("SELECT profils FROM Profils profils WHERE profils.libelle= ?1")
    public Optional<Profils> findByLibelle(String libelle);

    @Query("SELECT profils FROM Profils as profils WHERE profils.libelle like %?1% ORDER BY profils.libelle")
    public Page<Profils> rechercheProfilsByMotsCles(String motCles, Pageable pageable);
}
