package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.StructureAdmin;
import com.sn.dtai.admin.repository.StructureAdminRepository;
import com.sn.dtai.admin.service.StructureAdminService;
import com.sn.dtai.admin.service.dto.StructureAdminDTO;
import com.sn.dtai.admin.service.mapper.StructureAdminMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link StructureAdmin}.
 */
@Service
@Transactional
public class StructureAdminServiceImpl implements StructureAdminService {

    private final Logger log = LoggerFactory.getLogger(StructureAdminServiceImpl.class);

    private final StructureAdminRepository structureAdminRepository;

    private final StructureAdminMapper structureAdminMapper;

    public StructureAdminServiceImpl(StructureAdminRepository structureAdminRepository, StructureAdminMapper structureAdminMapper) {
        this.structureAdminRepository = structureAdminRepository;
        this.structureAdminMapper = structureAdminMapper;
    }

    @Override
    public StructureAdminDTO save(StructureAdminDTO structureAdminDTO) {
        log.debug("Request to save StructureAdmin : {}", structureAdminDTO);
        StructureAdmin structureAdmin = structureAdminMapper.toEntity(structureAdminDTO);
        structureAdmin = structureAdminRepository.save(structureAdmin);
        return structureAdminMapper.toDto(structureAdmin);
    }

    @Override
    public StructureAdminDTO update(StructureAdminDTO structureAdminDTO) {
        log.debug("Request to save StructureAdmin : {}", structureAdminDTO);
        StructureAdmin structureAdmin = structureAdminMapper.toEntity(structureAdminDTO);
        structureAdmin = structureAdminRepository.save(structureAdmin);
        return structureAdminMapper.toDto(structureAdmin);
    }

    @Override
    public Optional<StructureAdminDTO> partialUpdate(StructureAdminDTO structureAdminDTO) {
        log.debug("Request to partially update StructureAdmin : {}", structureAdminDTO);

        return structureAdminRepository
            .findById(structureAdminDTO.getId())
            .map(existingStructureAdmin -> {
                structureAdminMapper.partialUpdate(existingStructureAdmin, structureAdminDTO);

                return existingStructureAdmin;
            })
            .map(structureAdminRepository::save)
            .map(structureAdminMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StructureAdminDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StructureAdmins");
        return structureAdminRepository.findAll(pageable).map(structureAdminMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StructureAdminDTO> findOne(Long id) {
        log.debug("Request to get StructureAdmin : {}", id);
        return structureAdminRepository.findById(id).map(structureAdminMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StructureAdmin : {}", id);
        structureAdminRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one Sttructure by code.
     *
     * @param code the code of the strucure.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StructureAdmin> findByCode(String code) {
        log.debug("Request to get Structure : {}", code);
        return structureAdminRepository.findByCode(code);    
            // .map(structureAdminMapper::toDto);
    }
 
    /**
      * Get one structure by Libelle.
      *
      * @param libelle the Libelle of the structures.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<StructureAdmin> findByLibelle(String libelle) {
         log.debug("Request to get structure : {}", libelle);
         return structureAdminRepository.findByLibelle(libelle);
            // .map(structurAdminMapper::toDto);
     }
}
