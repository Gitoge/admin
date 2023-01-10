package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Corps;
import com.sn.dtai.admin.repository.CorpsRepository;
import com.sn.dtai.admin.service.CorpsService;
import com.sn.dtai.admin.service.dto.CorpsDTO;
import com.sn.dtai.admin.service.mapper.CorpsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Corps}.
 */
@Service
@Transactional
public class CorpsServiceImpl implements CorpsService {

    private final Logger log = LoggerFactory.getLogger(CorpsServiceImpl.class);

    private final CorpsRepository corpsRepository;

    private final CorpsMapper corpsMapper;

    public CorpsServiceImpl(CorpsRepository corpsRepository, CorpsMapper corpsMapper) {
        this.corpsRepository = corpsRepository;
        this.corpsMapper = corpsMapper;
    }

    @Override
    public CorpsDTO save(CorpsDTO corpsDTO) {
        log.debug("Request to save Corps : {}", corpsDTO);
        Corps corps = corpsMapper.toEntity(corpsDTO);
        corps = corpsRepository.save(corps);
        return corpsMapper.toDto(corps);
    }

    @Override
    public CorpsDTO update(CorpsDTO corpsDTO) {
        log.debug("Request to save Corps : {}", corpsDTO);
        Corps corps = corpsMapper.toEntity(corpsDTO);
        corps = corpsRepository.save(corps);
        return corpsMapper.toDto(corps);
    }

    @Override
    public Optional<CorpsDTO> partialUpdate(CorpsDTO corpsDTO) {
        log.debug("Request to partially update Corps : {}", corpsDTO);

        return corpsRepository
            .findById(corpsDTO.getId())
            .map(existingCorps -> {
                corpsMapper.partialUpdate(existingCorps, corpsDTO);

                return existingCorps;
            })
            .map(corpsRepository::save)
            .map(corpsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CorpsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Corps");
        return corpsRepository.findAll(pageable).map(corpsMapper::toDto);
    }

    public Page<CorpsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return corpsRepository.findAllWithEagerRelationships(pageable).map(corpsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CorpsDTO> findOne(Long id) {
        log.debug("Request to get Corps : {}", id);
        return corpsRepository.findOneWithEagerRelationships(id).map(corpsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Corps : {}", id);
        corpsRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one Corps by  code.
     *
     * @param code the code of the Corps.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Corps> findByCode(String code) {
        log.debug("Request to get Corps : {}", code);
        return corpsRepository.findByCode( code);    
            // .map(applicationsMapper::toDto);
    }
 
    /**
      * Get one Corps by Libelle.
      *
      * @param Libelle the Libelle of the coprs.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Corps> findByLibelle(String libelle) {
         log.debug("Request to get Corps : {}", libelle);
         return corpsRepository.findByLibelle( libelle);
            // .map(applicationsMapper::toDto);
     }
}
