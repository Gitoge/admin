package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Roles;
import com.sn.dtai.admin.repository.RolesRepository;
import com.sn.dtai.admin.service.RolesService;
import com.sn.dtai.admin.service.dto.RolesDTO;
import com.sn.dtai.admin.service.mapper.RolesMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Roles}.
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService {

    private final Logger log = LoggerFactory.getLogger(RolesServiceImpl.class);

    private final RolesRepository rolesRepository;

    private final RolesMapper rolesMapper;

    public RolesServiceImpl(RolesRepository rolesRepository, RolesMapper rolesMapper) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
    }

    @Override
    public RolesDTO save(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    @Override
    public RolesDTO update(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    @Override
    public Optional<RolesDTO> partialUpdate(RolesDTO rolesDTO) {
        log.debug("Request to partially update Roles : {}", rolesDTO);

        return rolesRepository
            .findById(rolesDTO.getId())
            .map(existingRoles -> {
                rolesMapper.partialUpdate(existingRoles, rolesDTO);

                return existingRoles;
            })
            .map(rolesRepository::save)
            .map(rolesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RolesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Roles");
        return rolesRepository.findAll(pageable).map(rolesMapper::toDto);
    }

    public Page<RolesDTO> findAllWithEagerRelationships(Pageable pageable) {
        return rolesRepository.findAllWithEagerRelationships(pageable).map(rolesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findOne(Long id) {
        log.debug("Request to get Roles : {}", id);
        return rolesRepository.findOneWithEagerRelationships(id).map(rolesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Roles : {}", id);
        rolesRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one roles by code.
     *
     * @param code the code of the role.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Roles> findByCode(String code) {
        log.debug("Request to get Roles : {}", code);
        return rolesRepository.findByCode(code);    
            // .map(rolesMapper::toDto);
    }
 
    /**
      * Get one roles by Libelle.
      *
      * @param libelle the Libelle of the roles.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Roles> findByLibelle(String libelle) {
         log.debug("Request to get Roles : {}", libelle);
         return rolesRepository.findByLibelle(libelle);
            // .map(rolesMapper::toDto);
     }

     @Override
     @Transactional(readOnly = true)
     public List<RolesDTO> findByProfil(Long profilId) {
         log.debug("Request to get Roles By Profil : {}",profilId);
         return rolesRepository.findByProfil(profilId).stream()
         .map(rolesMapper::toDto)
         .collect(Collectors.toCollection(LinkedList::new));
     }
}
