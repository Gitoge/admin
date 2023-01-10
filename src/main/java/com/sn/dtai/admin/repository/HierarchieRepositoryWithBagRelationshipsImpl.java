package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Hierarchie;
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
public class HierarchieRepositoryWithBagRelationshipsImpl implements HierarchieRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Hierarchie> fetchBagRelationships(Optional<Hierarchie> hierarchie) {
        return hierarchie.map(this::fetchPostes);
    }

    @Override
    public Page<Hierarchie> fetchBagRelationships(Page<Hierarchie> hierarchies) {
        return new PageImpl<>(fetchBagRelationships(hierarchies.getContent()), hierarchies.getPageable(), hierarchies.getTotalElements());
    }

    @Override
    public List<Hierarchie> fetchBagRelationships(List<Hierarchie> hierarchies) {
        return Optional.of(hierarchies).map(this::fetchPostes).orElse(Collections.emptyList());
    }

    Hierarchie fetchPostes(Hierarchie result) {
        return entityManager
            .createQuery(
                "select hierarchie from Hierarchie hierarchie left join fetch hierarchie.postes where hierarchie is :hierarchie",
                Hierarchie.class
            )
            .setParameter("hierarchie", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Hierarchie> fetchPostes(List<Hierarchie> hierarchies) {
        return entityManager
            .createQuery(
                "select distinct hierarchie from Hierarchie hierarchie left join fetch hierarchie.postes where hierarchie in :hierarchies",
                Hierarchie.class
            )
            .setParameter("hierarchies", hierarchies)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
