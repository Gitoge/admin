package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Modules;
import com.sn.dtai.admin.repository.ModulesRepository;
import com.sn.dtai.admin.service.ModulesService;
import com.sn.dtai.admin.service.dto.ModulesDTO;
import com.sn.dtai.admin.service.mapper.ModulesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Modules}.
 */
@Service
@Transactional
public class ModulesServiceImpl implements ModulesService {

    private final Logger log = LoggerFactory.getLogger(ModulesServiceImpl.class);

    private final ModulesRepository modulesRepository;

    private final ModulesMapper modulesMapper;

    public ModulesServiceImpl(ModulesRepository modulesRepository, ModulesMapper modulesMapper) {
        this.modulesRepository = modulesRepository;
        this.modulesMapper = modulesMapper;
    }

    @Override
    public ModulesDTO save(ModulesDTO modulesDTO) {
        log.debug("Request to save Modules : {}", modulesDTO);
        Modules modules = modulesMapper.toEntity(modulesDTO);
        modules = modulesRepository.save(modules);
        return modulesMapper.toDto(modules);
    }

    @Override
    public ModulesDTO update(ModulesDTO modulesDTO) {
        log.debug("Request to save Modules : {}", modulesDTO);
        Modules modules = modulesMapper.toEntity(modulesDTO);
        modules = modulesRepository.save(modules);
        return modulesMapper.toDto(modules);
    }

    @Override
    public Optional<ModulesDTO> partialUpdate(ModulesDTO modulesDTO) {
        log.debug("Request to partially update Modules : {}", modulesDTO);

        return modulesRepository
            .findById(modulesDTO.getId())
            .map(existingModules -> {
                modulesMapper.partialUpdate(existingModules, modulesDTO);

                return existingModules;
            })
            .map(modulesRepository::save)
            .map(modulesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ModulesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Modules");
        return modulesRepository.findAll(pageable).map(modulesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ModulesDTO> findOne(Long id) {
        log.debug("Request to get Modules : {}", id);
        return modulesRepository.findById(id).map(modulesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modules : {}", id);
        modulesRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one modules by code.
     *
     * @param code the code of the module.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Modules> findByCode(String code) {
        log.debug("Request to get Modules : {}", code);
        return modulesRepository.findByCode(code);    
            // .map(modulesMapper::toDto);
    }
 
    /**
      * Get one modules by Libelle.
      *
      * @param libelle the Libelle of the modules.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Modules> findByLibelle(String libelle) {
         log.debug("Request to get modules : {}", libelle);
         return modulesRepository.findByLibelle(libelle);
            // .map(applicationsMapper::toDto);
     }
}
