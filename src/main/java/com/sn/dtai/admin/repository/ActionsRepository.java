package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Actions;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Actions entity.
 */
@Repository
public interface ActionsRepository extends ActionsRepositoryWithBagRelationships, JpaRepository<Actions, Long> {
    default Optional<Actions> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Actions> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Actions> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT actions FROM Actions actions WHERE actions.code= ?1")
    public Optional<Actions> findByCode(String code);

    @Query("SELECT actions FROM Actions actions WHERE actions.libelle= ?1")
    public Optional<Actions> findByLibelle(String libelle);

    //AREVOIR @Query("SELECT actions FROM Actions actions LEFT JOIN FETCH actions.pages page WHERE page.id IN ?1 ")
    @Query("SELECT actions FROM Actions actions ")
    public List<Actions> findByPages(List<Long> pagesIds);

    @Query("SELECT actions FROM Actions as actions WHERE actions.libelle like %?1% ORDER BY actions.libelle")
    public Page<Actions> rechercheActionsByMotsCles(String motCles, Pageable pageable);
}
