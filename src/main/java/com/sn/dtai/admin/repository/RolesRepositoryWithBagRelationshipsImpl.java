package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Roles;
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
public class RolesRepositoryWithBagRelationshipsImpl implements RolesRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Roles> fetchBagRelationships(Optional<Roles> roles) {
        return roles.map(this::fetchProfils);
    }

    @Override
    public Page<Roles> fetchBagRelationships(Page<Roles> roles) {
        return new PageImpl<>(fetchBagRelationships(roles.getContent()), roles.getPageable(), roles.getTotalElements());
    }

    @Override
    public List<Roles> fetchBagRelationships(List<Roles> roles) {
        return Optional.of(roles).map(this::fetchProfils).orElse(Collections.emptyList());
    }

    Roles fetchProfils(Roles result) {
        return entityManager
            .createQuery("select roles from Roles roles left join fetch roles.pagesActions where roles is :roles", Roles.class)
            .setParameter("roles", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Roles> fetchProfils(List<Roles> roles) {
        return entityManager
            .createQuery("select distinct roles from Roles roles left join fetch roles.pagesActions where roles in :roles", Roles.class)
            .setParameter("roles", roles)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
