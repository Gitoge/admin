package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Actions;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ActionsRepositoryWithBagRelationships {
    Optional<Actions> fetchBagRelationships(Optional<Actions> actions);

    List<Actions> fetchBagRelationships(List<Actions> actions);

    Page<Actions> fetchBagRelationships(Page<Actions> actions);
}
