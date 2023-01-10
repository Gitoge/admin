package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TypeDestinataires;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypeDestinataires entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDestinatairesRepository extends JpaRepository<TypeDestinataires, Long> {

    @Query(value = "SELECT td FROM TypeDestinataires td INNER JOIN Destinataires d ON (td.id = d.typedestinataires)  WHERE td.id =:typeDestinataires")
	public List<TypeDestinataires> getDestinatairesByTypeDestinataires(@Param("typeDestinataires") Long typeDestinataires);
    
    @Query("SELECT td FROM TypeDestinataires td WHERE td.code= ?1")
    public Optional<TypeDestinataires> findByCode(String code);
}
