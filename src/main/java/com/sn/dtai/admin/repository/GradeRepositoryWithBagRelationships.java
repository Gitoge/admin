package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Grade;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface GradeRepositoryWithBagRelationships {
    Optional<Grade> fetchBagRelationships(Optional<Grade> grade);

    List<Grade> fetchBagRelationships(List<Grade> grades);

    Page<Grade> fetchBagRelationships(Page<Grade> grades);
}
