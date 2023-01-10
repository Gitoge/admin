package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.ParamPartsFiscales;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ParamPartsFiscales entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamPartsFiscalesRepository extends JpaRepository<ParamPartsFiscales, Long> {
    @Query("SELECT ppf FROM ParamPartsFiscales ppf WHERE ppf.code= ?1")
    public Optional<ParamPartsFiscales> findByCode(String code);

    @Query(value="SELECT ppf.nombreParts FROM ParamPartsFiscales ppf WHERE ppf.code= ?1")
	Optional<Double> findNombrePartsAgent(@Param("code") String code);

}
