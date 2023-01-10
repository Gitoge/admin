package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.ParamBulletins;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ParamBulletins entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamBulletinsRepository extends JpaRepository<ParamBulletins, Long> {}
