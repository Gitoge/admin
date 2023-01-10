package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Assiettes;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class AssiettesRepositoryWithBagRelationshipsImpl implements AssiettesRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Assiettes> fetchBagRelationships(Optional<Assiettes> assiettes) {
        return assiettes.map(this::fetchPostes);
    }

    @Override
    public Page<Assiettes> fetchBagRelationships(Page<Assiettes> assiettes) {
        return new PageImpl<>(fetchBagRelationships(assiettes.getContent()), assiettes.getPageable(), assiettes.getTotalElements());
    }

    @Override
    public List<Assiettes> fetchBagRelationships(List<Assiettes> assiettes) {
        return Optional.of(assiettes).map(this::fetchPostes).orElse(Collections.emptyList());
    }

    Assiettes fetchPostes(Assiettes result) {
        return entityManager
            .createQuery(
                "select assiettes from Assiettes assiettes left join fetch assiettes.postes where assiettes is :assiettes",
                Assiettes.class
            )
            .setParameter("assiettes", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Assiettes> fetchPostes(List<Assiettes> assiettes) {
        return entityManager
            .createQuery(
                "select distinct assiettes from Assiettes assiettes left join fetch assiettes.postes where assiettes in :assiettes",
                Assiettes.class
            )
            .setParameter("assiettes", assiettes)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
