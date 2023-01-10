package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TypeActes;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypeActes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeActesRepository extends JpaRepository<TypeActes, Long> {
    // @Query(value = "SELECT td FROM TypeActes td INNER JOIN Actes d ON (td.id = d.typedestinataires)  WHERE td.id =:typeActes")
	// public List<TypeActes> getActesByTypeActes(@Param("typeActes") Long typeActes);

    @Query("SELECT td FROM TypeActes td WHERE td.code= ?1")
    public Optional<TypeActes> findByCode(String code);

}
