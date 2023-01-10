package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Departement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;
/**
 * Spring Data SQL repository for the Departement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

@Query("SELECT dep FROM Departement dep")
public List<Departement> findAllDepartement();
}
