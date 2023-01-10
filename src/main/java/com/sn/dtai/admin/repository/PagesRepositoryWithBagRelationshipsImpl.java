package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Pages;
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
public class PagesRepositoryWithBagRelationshipsImpl implements PagesRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Pages> fetchBagRelationships(Optional<Pages> pages) {
        return pages.map(this::fetchRoles);
    }

    @Override
    public Page<Pages> fetchBagRelationships(Page<Pages> pages) {
        return new PageImpl<>(fetchBagRelationships(pages.getContent()), pages.getPageable(), pages.getTotalElements());
    }

    @Override
    public List<Pages> fetchBagRelationships(List<Pages> pages) {
        return Optional.of(pages).map(this::fetchRoles).orElse(Collections.emptyList());
    }

    Pages fetchRoles(Pages result) {
        return entityManager
            .createQuery("select pages from Pages pages left join fetch pages.actions where pages is :pages", Pages.class)
            .setParameter("pages", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Pages> fetchRoles(List<Pages> pages) {
        return entityManager
            .createQuery("select distinct pages from Pages pages left join fetch pages.actions where pages in :pages", Pages.class)
            .setParameter("pages", pages)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
