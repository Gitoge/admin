package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Modules;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ModulesRepositoryWithBagRelationships {
    Optional<Modules> fetchBagRelationships(Optional<Modules> modules);

    List<Modules> fetchBagRelationships(List<Modules> modules);

    Page<Modules> fetchBagRelationships(Page<Modules> modules);
}
