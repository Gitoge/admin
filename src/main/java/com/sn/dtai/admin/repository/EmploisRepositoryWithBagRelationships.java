package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Emplois;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface EmploisRepositoryWithBagRelationships {
    Optional<Emplois> fetchBagRelationships(Optional<Emplois> emplois);

    List<Emplois> fetchBagRelationships(List<Emplois> emplois);

    Page<Emplois> fetchBagRelationships(Page<Emplois> emplois);
}
