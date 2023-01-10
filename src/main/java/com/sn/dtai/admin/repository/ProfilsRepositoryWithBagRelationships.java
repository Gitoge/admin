package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Profils;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ProfilsRepositoryWithBagRelationships {
    Optional<Profils> fetchBagRelationships(Optional<Profils> profils);

    List<Profils> fetchBagRelationships(List<Profils> profils);

    Page<Profils> fetchBagRelationships(Page<Profils> profils);
}
