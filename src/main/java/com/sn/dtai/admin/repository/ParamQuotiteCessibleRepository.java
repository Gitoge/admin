package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.ParamQuotiteCessible;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ParamQuotiteCessible entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamQuotiteCessibleRepository extends JpaRepository<ParamQuotiteCessible, Long> {}
