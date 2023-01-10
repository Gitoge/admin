package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Services;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Service entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    @Query("SELECT services FROM Services services WHERE services.code= ?1 ORDER by services.libelle")
    public Optional<Services> findByCode(String code);

    @Query("SELECT services FROM Services services WHERE services.libelle= ?1 ORDER by services.libelle")
    public Optional<Services> findByLibelle(String libelle);

    @Query(
        "SELECT services FROM Services services JOIN Direction as direction on ( direction = services.direction )  JOIN Etablissement as etablissement on ( etablissement = direction.etab  ) WHERE etablissement.id= ?1"
    )
    public List<Services> findByEtablissement(Long etablissementId);
    

    @Query("SELECT services FROM Services services ORDER by services.libelle")
    public List<Services> findAllServices();

    @Query(
        "SELECT services FROM Services as services WHERE ((services.libelle like %?1%) OR (services.code like %?1%) OR (services.direction.libelle like %?1%)) ORDER BY services.libelle"
    )
    public Page<Services> rechercheServicesByMotsCles(String motCles, Pageable pageable);


    @Query(
        "SELECT service FROM Services service WHERE service IN  (SELECT services FROM Services services JOIN Direction as direction on ( direction = services.direction )  JOIN Etablissement as etablissement on ( etablissement = direction.etab  ) WHERE etablissement.id= ?1)  and service.code =?2"
    )
    public Optional<Services> findBOneServiceyEtablissement(Long etablissementId, String code);
}
