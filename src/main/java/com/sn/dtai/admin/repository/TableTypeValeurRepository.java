package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TableTypeValeur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TableTypeValeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableTypeValeurRepository extends JpaRepository<TableTypeValeur, Long> {}
