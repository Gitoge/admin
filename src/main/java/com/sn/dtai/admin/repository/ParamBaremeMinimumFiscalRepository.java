package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.ParamBaremeMinimumFiscal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ParamBaremeMinimumFiscal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamBaremeMinimumFiscalRepository extends JpaRepository<ParamBaremeMinimumFiscal, Long> {}
