package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Assiettes;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface AssiettesRepositoryWithBagRelationships {
    Optional<Assiettes> fetchBagRelationships(Optional<Assiettes> assiettes);

    List<Assiettes> fetchBagRelationships(List<Assiettes> assiettes);

    Page<Assiettes> fetchBagRelationships(Page<Assiettes> assiettes);
}
