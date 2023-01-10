package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.TypePosition;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypePosition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypePositionRepository extends JpaRepository<TypePosition, Long> {

    @Query(value = "SELECT tp FROM TypePosition tp INNER JOIN Positions p ON (tp.id = p.typeposition)  WHERE tp.id =:typePosition")
    public List<TypePosition> getPositionsByTypePosition(@Param("typePosition") Long typePosition);

    @Query("SELECT conv FROM TypePosition conv WHERE conv.libelle= ?1")
    public Optional<TypePosition> findByLibelle(String libelle);

    @Query("SELECT conv FROM TypePosition conv WHERE conv.code= ?1")
    public Optional<TypePosition> findByCode(String code);
}
