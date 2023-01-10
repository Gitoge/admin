package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Arrondissement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * Spring Data SQL repository for the Arrondissement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArrondissementRepository extends JpaRepository<Arrondissement, Long> {

@Query("SELECT arrondissement FROM Arrondissement arrondissement")
public List<Arrondissement> findAllArrondissement();
}
