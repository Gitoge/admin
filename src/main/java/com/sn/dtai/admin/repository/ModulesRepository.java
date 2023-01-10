package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Modules;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Modules entity.
 */
@Repository
public interface ModulesRepository extends ModulesRepositoryWithBagRelationships, JpaRepository<Modules, Long> {
    default Optional<Modules> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Modules> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Modules> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT modules FROM Modules modules WHERE modules.code= ?1")
    public Optional<Modules> findByCode(String code);

    @Query("SELECT modules FROM Modules modules WHERE modules.libelle= ?1")
    public Optional<Modules> findByLibelle(String libelle);

    @Query("SELECT modules FROM Modules modules WHERE modules.active= true")
    public Page<Modules> findAllActifs(Pageable pageable);

    @Query("SELECT modules FROM Modules as modules WHERE modules.libelle like %?1% ORDER BY modules.libelle")
    public Page<Modules> rechercheModulesByMotsCles(String motCles, Pageable pageable);
}
