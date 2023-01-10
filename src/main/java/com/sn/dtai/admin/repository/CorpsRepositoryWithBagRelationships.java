package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Corps;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface CorpsRepositoryWithBagRelationships {
    Optional<Corps> fetchBagRelationships(Optional<Corps> corps);

    List<Corps> fetchBagRelationships(List<Corps> corps);

    Page<Corps> fetchBagRelationships(Page<Corps> corps);
}
