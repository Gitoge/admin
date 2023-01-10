package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Pages;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Pages entity.
 */
@Repository
public interface PagesRepository extends PagesRepositoryWithBagRelationships, JpaRepository<Pages, Long> {
    default Optional<Pages> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Pages> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Pages> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    List<Pages> findByModulesIdOrderByOrdre(Long id);

    @Query("SELECT pages FROM Pages pages WHERE pages.code= ?1")
    public Optional<Pages> findByCode(String code);

    @Query("SELECT pages FROM Pages pages WHERE pages.libelle= ?1")
    public Optional<Pages> findByLibelle(String libelle);

    //AREVOIR @Query("SELECT DISTINCT pages FROM Pages pages LEFT JOIN FETCH pages.roles role   WHERE role.id IN ?1 ")
    @Query("SELECT DISTINCT pages FROM Pages pages ")
    public List<Pages> findByRoles(List<Long> rolesIds);

    @Query("SELECT pages FROM Pages as pages WHERE ((pages.libelle like %?1%) OR (pages.modules.libelle like %?1%)) ORDER BY pages.libelle")
    public Page<Pages> recherchePagesByMotsCles(String motCles, Pageable pageable);
}
