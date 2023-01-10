package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.Indices;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data SQL repository for the Indices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicesRepository extends JpaRepository<Indices, Long> {
    @Query("SELECT indice FROM Indices indice WHERE indice.code= ?1")
    public Optional<Indices> findByCode(String code);
}
