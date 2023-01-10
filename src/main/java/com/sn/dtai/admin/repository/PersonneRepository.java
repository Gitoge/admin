package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Personne;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data SQL repository for the Personne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
Optional<Personne> findPersonneByLogin(String login);
Optional<Personne> findByJhiUserId(Long id);

// Afficher seulement l'utilisateur simple connecté
@Query("SELECT p FROM Personne as p where ((p.numeroPiece like %?1%) OR (p.email like %?1%) OR (p.profils.libelle like %?1%) OR (p.typePiece like %?1%) OR (p.prenom like %?1%) OR (p.nom like %?1%)) and p.id=?2 and upper(p.profils.libelle)='UTILISATEUR'")
public Page<Personne> recherchesPersonne(
    String motCles,
    Long id,
    Pageable pageable
);

// Administrateur
@Query("SELECT p FROM Personne as p where ((p.numeroPiece like %?1%) OR (p.email like %?1%) OR (p.profils.libelle like %?1%) OR (p.typePiece like %?1%) OR (p.prenom like %?1%) OR (p.nom like %?1%))  and (p.etablissement.id=?2) and upper(p.profils.libelle)<>'ADMINISTRATEUR'")
public Page<Personne> recherchesPersonneAdmin(
    String motCles,
    Long etablissementId,
    Pageable pageable
);

// Super Administrateur
@Query("SELECT p FROM Personne as p where ((p.numeroPiece like %?1%) OR (p.email like %?1%) OR (p.profils.libelle like %?1%) OR (p.typePiece like %?1%) OR (p.prenom like %?1%) OR (p.nom like %?1%))")
public Page<Personne> superAdmin(
    String motCles,
    Pageable pageable
);



@Query(
    "SELECT p FROM Personne p " +
    " LEFT JOIN FETCH p.profils profil" +
    //" LEFT JOIN FETCH profil.modules " +
    //" LEFT JOIN FETCH profil.roles " +
    " WHERE p.jhiUserId = :userId "
    // " ORDER BY ordre ASC "
)
public Optional<Personne> getInfosPersonneByJhiUser(@Param("userId") Long userId);

/*@Query("SELECT p FROM Personne as p WHERE  p.userInsertId = ?1  AND ((p.numeroPiece like %?2%) OR (p.email like %?2%) OR (p.profils.libelle like %?2%) OR (p.typePiece like %?2%) OR (p.prenom like %?2%) OR (p.nom like %?2%))")
public Page<Personne> recherchesPersonne(
    Long userInsertId,
    String motCles,
    Pageable pageable
);*/


}
