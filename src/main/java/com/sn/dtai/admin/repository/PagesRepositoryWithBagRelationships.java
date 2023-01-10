package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Pages;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface PagesRepositoryWithBagRelationships {
    Optional<Pages> fetchBagRelationships(Optional<Pages> pages);

    List<Pages> fetchBagRelationships(List<Pages> pages);

    Page<Pages> fetchBagRelationships(Page<Pages> pages);
}
