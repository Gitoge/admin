package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Corps;
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
public class CorpsRepositoryWithBagRelationshipsImpl implements CorpsRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Corps> fetchBagRelationships(Optional<Corps> corps) {
        return corps.map(this::fetchPostes);
    }

    @Override
    public Page<Corps> fetchBagRelationships(Page<Corps> corps) {
        return new PageImpl<>(fetchBagRelationships(corps.getContent()), corps.getPageable(), corps.getTotalElements());
    }

    @Override
    public List<Corps> fetchBagRelationships(List<Corps> corps) {
        return Optional.of(corps).map(this::fetchPostes).orElse(Collections.emptyList());
    }

    Corps fetchPostes(Corps result) {
        return entityManager
            .createQuery("select corps from Corps corps left join fetch corps.postes where corps is :corps", Corps.class)
            .setParameter("corps", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Corps> fetchPostes(List<Corps> corps) {
        return entityManager
            .createQuery("select distinct corps from Corps corps left join fetch corps.postes where corps in :corps", Corps.class)
            .setParameter("corps", corps)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
