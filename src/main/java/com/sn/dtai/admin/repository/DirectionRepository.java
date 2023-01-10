package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Direction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Direction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Query("SELECT direction FROM Direction direction WHERE direction.code= ?1")
    public Optional<Direction> findByCode(String code);

    @Query("SELECT direction FROM Direction direction WHERE direction.libelle= ?1")
    public Optional<Direction> findByLibelle(String libelle);

    @Query("SELECT direction FROM Direction direction")
    public List<Direction> findAllDirections();

    @Query(
        "SELECT direction FROM Direction as direction WHERE ((direction.libelle like %?1%) OR (direction.etab.libelleLong like %?1%)) ORDER BY direction.libelle"
    )
    public Page<Direction> rechercheDirectionByMotsCles(String motCles, Pageable pageable);

    @Query(value = "SELECT dirc FROM Direction dirc INNER JOIN Services serv ON (dirc.id = serv.direction)  WHERE dirc.id =:direction")
	public List<Direction> getDirectionsByService(@Param("direction") Long direction);
}
