package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Postes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Postes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostesRepository extends JpaRepository<Postes, Long> {

@Query("SELECT postes FROM Postes postes WHERE postes.code = ?1 ")
public Optional<Postes> findByCodePostes(String code);

// lister le nombre de postes sans pagination
@Query("SELECT p FROM Postes p order by code")
public List<Postes> listPostes();

// // lister les postes à assiettes liés à un grade donné

 @Query("SELECT postes FROM Grade grade INNER JOIN grade.postes postes where grade.id = ?1 and postes.reference ='ASSIETTE' and postes.etat =1 ")
  public List<Postes> getPostesAssiettesByGrade(long gradeId);

    // Lister l'ensemble des postes liés au grade

    @Query("SELECT postes FROM Grade grade INNER JOIN grade.postes postes where grade.id = ?1")
    public List<Postes> getPostesByGrade(Long gradeId);


    // Lister l'ensemble des postes de gains liés au grade

    @Query("SELECT postes FROM Grade grade INNER JOIN grade.postes postes where grade.id = ?1 and postes.code < '600' and postes.etat =1")
    public List<Postes> getPostesGainByGrade(Long gradeId);


    // Lister l'ensemble des postes liés au grade ET qui sont inférieurs à 600

    @Query("SELECT postes FROM Postes postes WHERE postes.id = ?1 and postes.code < '600' and postes.etat =1")
    public Optional<Postes> getPostesInf600(Long id);

   // Lister l'ensemble des postes liés au grade

   @Query("SELECT postes FROM Assiettes assiettes INNER JOIN assiettes.postes postes where assiettes.id = ?1")
   public List<Postes> getPostesByAssiette(Long assietteId);

    // Lister l'ensemble des postes liés à l'emplois

    @Query("SELECT postes FROM Emplois emplois INNER JOIN emplois.postes postes where emplois.id = ?1")
    public List<Postes> getPostesByEmplois(Long emploisId);

    
    @Query(
        "SELECT postes FROM Postes as postes WHERE ((postes.libelle like %?1%) OR (postes.code like %?1%)  ) ORDER BY postes.code"
    )
    public Page<Postes> recherchesPostesByMotsCles(String motCles, Pageable pageable);


}
