package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Etablissement;
import com.sn.dtai.admin.repository.EtablissementRepository;
import com.sn.dtai.admin.service.EtablissementService;
import com.sn.dtai.admin.service.dto.EtablissementDTO;
import com.sn.dtai.admin.service.mapper.EtablissementMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Etablissement}.
 */
@Service
@Transactional
public class EtablissementServiceImpl implements EtablissementService {

    private final Logger log = LoggerFactory.getLogger(EtablissementServiceImpl.class);

    private final EtablissementRepository etablissementRepository;

    private final EtablissementMapper etablissementMapper;

    public EtablissementServiceImpl(EtablissementRepository etablissementRepository, EtablissementMapper etablissementMapper) {
        this.etablissementRepository = etablissementRepository;
        this.etablissementMapper = etablissementMapper;
    }

    @Override
    public EtablissementDTO save(EtablissementDTO etablissementDTO) {
        log.debug("Request to save Etablissement : {}", etablissementDTO);
        Etablissement etablissement = etablissementMapper.toEntity(etablissementDTO);
        etablissement = etablissementRepository.save(etablissement);
        return etablissementMapper.toDto(etablissement);
    }

    @Override
    public EtablissementDTO update(EtablissementDTO etablissementDTO) {
        log.debug("Request to save Etablissement : {}", etablissementDTO);
        Etablissement etablissement = etablissementMapper.toEntity(etablissementDTO);
        etablissement = etablissementRepository.save(etablissement);
        return etablissementMapper.toDto(etablissement);
    }

    @Override
    public Optional<EtablissementDTO> partialUpdate(EtablissementDTO etablissementDTO) {
        log.debug("Request to partially update Etablissement : {}", etablissementDTO);

        return etablissementRepository
            .findById(etablissementDTO.getId())
            .map(existingEtablissement -> {
                etablissementMapper.partialUpdate(existingEtablissement, etablissementDTO);

                return existingEtablissement;
            })
            .map(etablissementRepository::save)
            .map(etablissementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EtablissementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Etablissements");
        return etablissementRepository.findAll(pageable).map(etablissementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EtablissementDTO> findOne(Long id) {
        log.debug("Request to get Etablissement : {}", id);
        return etablissementRepository.findById(id).map(etablissementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Etablissement : {}", id);
        etablissementRepository.deleteById(id);
    }

    // Méthodes Ajoutées

    /**
     * Get one Etablissement by  code.
     *
     * @param code the code of the typeLocalite.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Etablissement> findByCode(String code) {
        log.debug("Request to get typeEtablissement : {}", code);
        return etablissementRepository.findByCode( code);    
            // .map(applicationsMapper::toDto);
    }
 
    /**
      * Get one Etablissement by Libelle.
      *
      * @param Libelle the Libelle of the etablissement.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Etablissement> findByLibelle(String libelle) {
         log.debug("Request to get Etablissement : {}", libelle);
         return etablissementRepository.findByLibelle( libelle);
            // .map(applicationsMapper::toDto);
     }
}
