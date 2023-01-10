package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.PostesReferenceActes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Spring Data SQL repository for the PostesReferenceActes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostesReferenceActesRepository extends JpaRepository<PostesReferenceActes, Long> {

Optional<PostesReferenceActes> findByPostesId(Long postesId);

@Query("SELECT post FROM PostesReferenceActes post WHERE post.postes.code = ?1 ")
public List<PostesReferenceActes> findByCodePostes(String code);

}
