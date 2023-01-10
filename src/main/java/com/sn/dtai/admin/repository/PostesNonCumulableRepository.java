package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.PostesNonCumulable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PostesNonCumulable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostesNonCumulableRepository extends JpaRepository<PostesNonCumulable, Long> {

    @Query("SELECT posnc FROM PostesNonCumulable posnc WHERE ((?1 = posnc.codePoste1 ) OR (?1 = posnc.codePoste2))")
    public List<PostesNonCumulable> findByCodePoste1(String codePoste);
    
}
