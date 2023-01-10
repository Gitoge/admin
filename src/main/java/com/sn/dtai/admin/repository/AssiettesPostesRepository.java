package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.AssiettesPostes;
import com.sn.dtai.admin.service.dto.AssiettesPostesDTO;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Spring Data JPA repository for the AssiettesPostes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssiettesPostesRepository extends JpaRepository<AssiettesPostes, Long> {
    @Query("SELECT assiettesPostes FROM AssiettesPostes assiettesPostes WHERE assiettesPostes.assiettes.id= ?1")
    public List<AssiettesPostes> findByAssiettes(Long assiettesId);

   
    
}
