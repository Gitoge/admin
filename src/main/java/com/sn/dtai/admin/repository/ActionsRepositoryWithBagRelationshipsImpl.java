package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Actions;
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
public class ActionsRepositoryWithBagRelationshipsImpl implements ActionsRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Actions> fetchBagRelationships(Optional<Actions> actions) {
        return actions.map(this::fetchPages);
    }

    @Override
    public Page<Actions> fetchBagRelationships(Page<Actions> actions) {
        return new PageImpl<>(fetchBagRelationships(actions.getContent()), actions.getPageable(), actions.getTotalElements());
    }

    @Override
    public List<Actions> fetchBagRelationships(List<Actions> actions) {
        return Optional.of(actions).map(this::fetchPages).orElse(Collections.emptyList());
    }

    Actions fetchPages(Actions result) {
        return entityManager
            .createQuery("select actions from Actions actions where actions is :actions", Actions.class)
            .setParameter("actions", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Actions> fetchPages(List<Actions> actions) {
        return entityManager
            .createQuery("select distinct actions from Actions actions where actions in :actions", Actions.class)
            .setParameter("actions", actions)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
