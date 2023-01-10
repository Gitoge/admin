package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Applications;
import com.sn.dtai.admin.repository.ApplicationsRepository;
import com.sn.dtai.admin.service.ApplicationsService;
import com.sn.dtai.admin.service.dto.ApplicationsDTO;
import com.sn.dtai.admin.service.mapper.ApplicationsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Applications}.
 */
@Service
@Transactional
public class ApplicationsServiceImpl implements ApplicationsService {

    private final Logger log = LoggerFactory.getLogger(ApplicationsServiceImpl.class);

    private final ApplicationsRepository applicationsRepository;

    private final ApplicationsMapper applicationsMapper;

    public ApplicationsServiceImpl(ApplicationsRepository applicationsRepository, ApplicationsMapper applicationsMapper) {
        this.applicationsRepository = applicationsRepository;
        this.applicationsMapper = applicationsMapper;
    }

    @Override
    public ApplicationsDTO save(ApplicationsDTO applicationsDTO) {
        log.debug("Request to save Applications : {}", applicationsDTO);
        Applications applications = applicationsMapper.toEntity(applicationsDTO);
        applications = applicationsRepository.save(applications);
        return applicationsMapper.toDto(applications);
    }

    @Override
    public ApplicationsDTO update(ApplicationsDTO applicationsDTO) {
        log.debug("Request to save Applications : {}", applicationsDTO);
        Applications applications = applicationsMapper.toEntity(applicationsDTO);
        applications = applicationsRepository.save(applications);
        return applicationsMapper.toDto(applications);
    }

    @Override
    public Optional<ApplicationsDTO> partialUpdate(ApplicationsDTO applicationsDTO) {
        log.debug("Request to partially update Applications : {}", applicationsDTO);

        return applicationsRepository
            .findById(applicationsDTO.getId())
            .map(existingApplications -> {
                applicationsMapper.partialUpdate(existingApplications, applicationsDTO);

                return existingApplications;
            })
            .map(applicationsRepository::save)
            .map(applicationsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApplicationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Applications");
        return applicationsRepository.findAll(pageable).map(applicationsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationsDTO> findOne(Long id) {
        log.debug("Request to get Applications : {}", id);
        return applicationsRepository.findById(id).map(applicationsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Applications : {}", id);
        applicationsRepository.deleteById(id);
    }

    // Méthodes Ajoutées

    /**
     * Get one applications by structure and code.
     *
     * @param code the code of the applications.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Applications> findByCode(String code) {
        log.debug("Request to get Applications : {}", code);
        return applicationsRepository.findByCode( code);    
            // .map(applicationsMapper::toDto);
    }
 
    /**
      * Get one applications by Libelle.
      *
      * @param Nom the Nom of the applications.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Applications> findByNom(String nom) {
         log.debug("Request to get applications : {}", nom);
         return applicationsRepository.findByNom( nom);
            // .map(applicationsMapper::toDto);
     }


}
