package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Hierarchie;
import com.sn.dtai.admin.repository.HierarchieRepository;
import com.sn.dtai.admin.service.HierarchieService;
import com.sn.dtai.admin.service.dto.HierarchieDTO;
import com.sn.dtai.admin.service.mapper.HierarchieMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Hierarchie}.
 */
@Service
@Transactional
public class HierarchieServiceImpl implements HierarchieService {

    private final Logger log = LoggerFactory.getLogger(HierarchieServiceImpl.class);

    private final HierarchieRepository hierarchieRepository;

    private final HierarchieMapper hierarchieMapper;

    public HierarchieServiceImpl(HierarchieRepository hierarchieRepository, HierarchieMapper hierarchieMapper) {
        this.hierarchieRepository = hierarchieRepository;
        this.hierarchieMapper = hierarchieMapper;
    }

    @Override
    public HierarchieDTO save(HierarchieDTO hierarchieDTO) {
        log.debug("Request to save Hierarchie : {}", hierarchieDTO);
        Hierarchie hierarchie = hierarchieMapper.toEntity(hierarchieDTO);
        hierarchie = hierarchieRepository.save(hierarchie);
        return hierarchieMapper.toDto(hierarchie);
    }

    @Override
    public HierarchieDTO update(HierarchieDTO hierarchieDTO) {
        log.debug("Request to save Hierarchie : {}", hierarchieDTO);
        Hierarchie hierarchie = hierarchieMapper.toEntity(hierarchieDTO);
        hierarchie = hierarchieRepository.save(hierarchie);
        return hierarchieMapper.toDto(hierarchie);
    }

    @Override
    public Optional<HierarchieDTO> partialUpdate(HierarchieDTO hierarchieDTO) {
        log.debug("Request to partially update Hierarchie : {}", hierarchieDTO);

        return hierarchieRepository
            .findById(hierarchieDTO.getId())
            .map(existingHierarchie -> {
                hierarchieMapper.partialUpdate(existingHierarchie, hierarchieDTO);

                return existingHierarchie;
            })
            .map(hierarchieRepository::save)
            .map(hierarchieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HierarchieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Hierarchies");
        return hierarchieRepository.findAll(pageable).map(hierarchieMapper::toDto);
    }

    public Page<HierarchieDTO> findAllWithEagerRelationships(Pageable pageable) {
        return hierarchieRepository.findAllWithEagerRelationships(pageable).map(hierarchieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HierarchieDTO> findOne(Long id) {
        log.debug("Request to get Hierarchie : {}", id);
        return hierarchieRepository.findOneWithEagerRelationships(id).map(hierarchieMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hierarchie : {}", id);
        hierarchieRepository.deleteById(id);
    }

    /**
     * Get one Services by  code.
     *
     * @param code the code of the Services.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Hierarchie> findByCode(String code) {
        log.debug("Request to get Hierarachie : {}", code);
        return hierarchieRepository.findByCode( code);    
       
    }
 
    /**
      * Get one Hierarchie by Libelle.
      *
      * @param Libelle the Libelle of the hierarchie.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Hierarchie> findByLibelle(String libelle) {
         log.debug("Request to get Hierarchie : {}", libelle);
         return hierarchieRepository.findByLibelle( libelle);
            
     }

     
    /**
      * Get one Hierarchie by Libelle.
      *
      * @param Libelle the Libelle of the hierarchie.
      * @return the entity.
      */
      @Transactional(readOnly = true)
      public Optional<HierarchieDTO> findHierarchieByBornes(String valeur) {
          log.debug("Request to get Hierarchie : {}", valeur);
          return hierarchieRepository.findHierarchieByBornes( valeur).map(hierarchieMapper::toDto);
             
      }
}
