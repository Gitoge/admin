package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Grade;
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
public class GradeRepositoryWithBagRelationshipsImpl implements GradeRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Grade> fetchBagRelationships(Optional<Grade> grade) {
        return grade.map(this::fetchPostes);
    }

    @Override
    public Page<Grade> fetchBagRelationships(Page<Grade> grades) {
        return new PageImpl<>(fetchBagRelationships(grades.getContent()), grades.getPageable(), grades.getTotalElements());
    }

    @Override
    public List<Grade> fetchBagRelationships(List<Grade> grades) {
        return Optional.of(grades).map(this::fetchPostes).orElse(Collections.emptyList());
    }

    Grade fetchPostes(Grade result) {
        return entityManager
            .createQuery("select grade from Grade grade left join fetch grade.postes where grade is :grade", Grade.class)
            .setParameter("grade", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Grade> fetchPostes(List<Grade> grades) {
        return entityManager
            .createQuery("select distinct grade from Grade grade left join fetch grade.postes where grade in :grades", Grade.class)
            .setParameter("grades", grades)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
