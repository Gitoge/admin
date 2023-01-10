package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Region;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;
/**
 * Spring Data SQL repository for the Region entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

@Query("SELECT reg FROM Region reg")
public List<Region> findAllRegion();

}
