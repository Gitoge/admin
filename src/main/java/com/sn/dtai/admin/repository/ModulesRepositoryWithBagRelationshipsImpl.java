package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Modules;
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
public class ModulesRepositoryWithBagRelationshipsImpl implements ModulesRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Modules> fetchBagRelationships(Optional<Modules> modules) {
        return modules.map(this::fetchProfils);
    }

    @Override
    public Page<Modules> fetchBagRelationships(Page<Modules> modules) {
        return new PageImpl<>(fetchBagRelationships(modules.getContent()), modules.getPageable(), modules.getTotalElements());
    }

    @Override
    public List<Modules> fetchBagRelationships(List<Modules> modules) {
        return Optional.of(modules).map(this::fetchProfils).orElse(Collections.emptyList());
    }

    Modules fetchProfils(Modules result) {
        return entityManager
            .createQuery("select modules from Modules modules left join fetch modules.profils where modules is :modules", Modules.class)
            .setParameter("modules", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Modules> fetchProfils(List<Modules> modules) {
        return entityManager
            .createQuery(
                "select distinct modules from Modules modules left join fetch modules.profils where modules in :modules",
                Modules.class
            )
            .setParameter("modules", modules)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
