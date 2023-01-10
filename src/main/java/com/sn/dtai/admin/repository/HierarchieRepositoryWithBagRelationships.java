package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Hierarchie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface HierarchieRepositoryWithBagRelationships {
    Optional<Hierarchie> fetchBagRelationships(Optional<Hierarchie> hierarchie);

    List<Hierarchie> fetchBagRelationships(List<Hierarchie> hierarchies);

    Page<Hierarchie> fetchBagRelationships(Page<Hierarchie> hierarchies);
}
