package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Profils;
import com.sn.dtai.admin.repository.ProfilsRepository;
import com.sn.dtai.admin.service.ProfilsService;
import com.sn.dtai.admin.service.dto.ProfilsDTO;
import com.sn.dtai.admin.service.mapper.ProfilsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Profils}.
 */
@Service
@Transactional
public class ProfilsServiceImpl implements ProfilsService {

    private final Logger log = LoggerFactory.getLogger(ProfilsServiceImpl.class);

    private final ProfilsRepository profilsRepository;

    private final ProfilsMapper profilsMapper;

    public ProfilsServiceImpl(ProfilsRepository profilsRepository, ProfilsMapper profilsMapper) {
        this.profilsRepository = profilsRepository;
        this.profilsMapper = profilsMapper;
    }

    @Override
    public ProfilsDTO save(ProfilsDTO profilsDTO) {
        log.debug("Request to save Profils : {}", profilsDTO);
        Profils profils = profilsMapper.toEntity(profilsDTO);
        profils = profilsRepository.save(profils);
        return profilsMapper.toDto(profils);
    }

    @Override
    public ProfilsDTO update(ProfilsDTO profilsDTO) {
        log.debug("Request to save Profils : {}", profilsDTO);
        Profils profils = profilsMapper.toEntity(profilsDTO);
        profils = profilsRepository.save(profils);
        return profilsMapper.toDto(profils);
    }

    @Override
    public Optional<ProfilsDTO> partialUpdate(ProfilsDTO profilsDTO) {
        log.debug("Request to partially update Profils : {}", profilsDTO);

        return profilsRepository
            .findById(profilsDTO.getId())
            .map(existingProfils -> {
                profilsMapper.partialUpdate(existingProfils, profilsDTO);

                return existingProfils;
            })
            .map(profilsRepository::save)
            .map(profilsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProfilsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Profils");
        return profilsRepository.findAll(pageable).map(profilsMapper::toDto);
    }

    public Page<ProfilsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return profilsRepository.findAllWithEagerRelationships(pageable).map(profilsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilsDTO> findOne(Long id) {
        log.debug("Request to get Profils : {}", id);
        return profilsRepository.findOneWithEagerRelationships(id).map(profilsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Profils : {}", id);
        profilsRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one profils by code.
     *
     * @param code the code of the profil.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Profils> findByCode(String code) {
        log.debug("Request to get Profils : {}", code);
        return profilsRepository.findByCode(code);    
            // .map(profilsMapper::toDto);
    }
 
    /**
      * Get one profils by Libelle.
      *
      * @param libelle the Libelle of the profils.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Profils> findByLibelle(String libelle) {
         log.debug("Request to get profils : {}", libelle);
         return profilsRepository.findByLibelle(libelle);
            // .map(profilsMapper::toDto);
     }
}
