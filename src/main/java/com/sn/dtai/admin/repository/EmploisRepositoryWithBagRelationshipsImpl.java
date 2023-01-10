package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Emplois;
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
public class EmploisRepositoryWithBagRelationshipsImpl implements EmploisRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Emplois> fetchBagRelationships(Optional<Emplois> emplois) {
        return emplois.map(this::fetchPostes);
    }

    @Override
    public Page<Emplois> fetchBagRelationships(Page<Emplois> emplois) {
        return new PageImpl<>(fetchBagRelationships(emplois.getContent()), emplois.getPageable(), emplois.getTotalElements());
    }

    @Override
    public List<Emplois> fetchBagRelationships(List<Emplois> emplois) {
        return Optional.of(emplois).map(this::fetchPostes).orElse(Collections.emptyList());
    }

    Emplois fetchPostes(Emplois result) {
        return entityManager
            .createQuery("select emplois from Emplois emplois left join fetch emplois.postes where emplois is :emplois", Emplois.class)
            .setParameter("emplois", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Emplois> fetchPostes(List<Emplois> emplois) {
        return entityManager
            .createQuery(
                "select distinct emplois from Emplois emplois left join fetch emplois.postes where emplois in :emplois",
                Emplois.class
            )
            .setParameter("emplois", emplois)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
