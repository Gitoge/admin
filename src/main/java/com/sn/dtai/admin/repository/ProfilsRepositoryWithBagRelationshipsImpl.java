package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Profils;
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
public class ProfilsRepositoryWithBagRelationshipsImpl implements ProfilsRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Profils> fetchBagRelationships(Optional<Profils> profils) {
        return profils.map(this::fetchModules);
    }

    @Override
    public Page<Profils> fetchBagRelationships(Page<Profils> profils) {
        return new PageImpl<>(fetchBagRelationships(profils.getContent()), profils.getPageable(), profils.getTotalElements());
    }

    @Override
    public List<Profils> fetchBagRelationships(List<Profils> profils) {
        return Optional.of(profils).map(this::fetchModules).orElse(Collections.emptyList());
    }

    Profils fetchModules(Profils result) {
        return entityManager
            .createQuery("select profils from Profils profils left join fetch profils.roles where profils is :profils", Profils.class)
            .setParameter("profils", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Profils> fetchModules(List<Profils> profils) {
        return entityManager
            .createQuery(
                "select distinct profils from Profils profils left join fetch profils.roles where profils in :profils",
                Profils.class
            )
            .setParameter("profils", profils)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
