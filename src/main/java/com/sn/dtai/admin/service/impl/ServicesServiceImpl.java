package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Services;
import com.sn.dtai.admin.repository.ServicesRepository;
import com.sn.dtai.admin.service.ServicesService;
import com.sn.dtai.admin.service.dto.ServicesDTO;
import com.sn.dtai.admin.service.mapper.ServicesMapper;

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
 * Service Implementation for managing {@link Service}.
 */
@Service
@Transactional
public class ServicesServiceImpl implements ServicesService {

    private final Logger log = LoggerFactory.getLogger(ServicesServiceImpl.class);

    private final ServicesRepository serviceRepository;

    private final ServicesMapper serviceMapper;

    public ServicesServiceImpl(ServicesRepository serviceRepository, ServicesMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public ServicesDTO save(ServicesDTO serviceDTO) {
        log.debug("Request to save Service : {}", serviceDTO);
        Services service = serviceMapper.toEntity(serviceDTO);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    @Override
    public ServicesDTO update(ServicesDTO serviceDTO) {
        log.debug("Request to save Service : {}", serviceDTO);
        Services service = serviceMapper.toEntity(serviceDTO);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    @Override
    public Optional<ServicesDTO> partialUpdate(ServicesDTO serviceDTO) {
        log.debug("Request to partially update Service : {}", serviceDTO);

        return serviceRepository
            .findById(serviceDTO.getId())
            .map(existingService -> {
                serviceMapper.partialUpdate(existingService, serviceDTO);

                return existingService;
            })
            .map(serviceRepository::save)
            .map(serviceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServicesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Services");
        return serviceRepository.findAll(pageable).map(serviceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicesDTO> findOne(Long id) {
        log.debug("Request to get Service : {}", id);
        return serviceRepository.findById(id).map(serviceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Service : {}", id);
        serviceRepository.deleteById(id);
    }

    /**
     * Get one Services by  code.
     *
     * @param code the code of the Services.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Services> findByCode(String code) {
        log.debug("Request to get Services : {}", code);
        return serviceRepository.findByCode(code);        
    }
    /**
      * Get one Etablissement by Libelle.
      *
      * @param Libelle the Libelle of the etablissement.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Services> findByLibelle(String libelle) {
         log.debug("Request to get Services : {}", libelle);
         return serviceRepository.findByLibelle( libelle);
     }


      /**
      * Get List of Services by Etablissement by libelleCourt.
      *
      * @param libelleCourt the libelleCourt of the etablissement.
      * @return the entity.
      */
     @Transactional
     public List<ServicesDTO> findByEtablissement(Long etablissementId) {
        return serviceRepository.findByEtablissement( etablissementId).stream().map(serviceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
     }

     
      /**
      * Get List of Services by Etablissement by libelleCourt.
      *
      * @param libelleCourt the libelleCourt of the etablissement.
      * @return the entity.
      */
      @Transactional
      public Optional<ServicesDTO> findBOneServiceyEtablissement(Long etablissementId, String code) {
         return serviceRepository.findBOneServiceyEtablissement( etablissementId, code).map(serviceMapper::toDto);
      }
 
}
