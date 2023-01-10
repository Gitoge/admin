package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TypeEtablissement;
import com.sn.dtai.admin.repository.TypeEtablissementRepository;
import com.sn.dtai.admin.service.TypeEtablissementService;
import com.sn.dtai.admin.service.dto.TypeEtablissementDTO;
import com.sn.dtai.admin.service.mapper.TypeEtablissementMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TypeEtablissement}.
 */
@Service
@Transactional
public class TypeEtablissementServiceImpl implements TypeEtablissementService {

    private final Logger log = LoggerFactory.getLogger(TypeEtablissementServiceImpl.class);

    private final TypeEtablissementRepository typeEtablissementRepository;

    private final TypeEtablissementMapper typeEtablissementMapper;

    public TypeEtablissementServiceImpl(
        TypeEtablissementRepository typeEtablissementRepository,
        TypeEtablissementMapper typeEtablissementMapper
    ) {
        this.typeEtablissementRepository = typeEtablissementRepository;
        this.typeEtablissementMapper = typeEtablissementMapper;
    }

    @Override
    public TypeEtablissementDTO save(TypeEtablissementDTO typeEtablissementDTO) {
        log.debug("Request to save TypeEtablissement : {}", typeEtablissementDTO);
        TypeEtablissement typeEtablissement = typeEtablissementMapper.toEntity(typeEtablissementDTO);
        typeEtablissement = typeEtablissementRepository.save(typeEtablissement);
        return typeEtablissementMapper.toDto(typeEtablissement);
    }

    @Override
    public TypeEtablissementDTO update(TypeEtablissementDTO typeEtablissementDTO) {
        log.debug("Request to save TypeEtablissement : {}", typeEtablissementDTO);
        TypeEtablissement typeEtablissement = typeEtablissementMapper.toEntity(typeEtablissementDTO);
        typeEtablissement = typeEtablissementRepository.save(typeEtablissement);
        return typeEtablissementMapper.toDto(typeEtablissement);
    }

    @Override
    public Optional<TypeEtablissementDTO> partialUpdate(TypeEtablissementDTO typeEtablissementDTO) {
        log.debug("Request to partially update TypeEtablissement : {}", typeEtablissementDTO);

        return typeEtablissementRepository
            .findById(typeEtablissementDTO.getId())
            .map(existingTypeEtablissement -> {
                typeEtablissementMapper.partialUpdate(existingTypeEtablissement, typeEtablissementDTO);

                return existingTypeEtablissement;
            })
            .map(typeEtablissementRepository::save)
            .map(typeEtablissementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeEtablissementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeEtablissements");
        return typeEtablissementRepository.findAll(pageable).map(typeEtablissementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeEtablissementDTO> findOne(Long id) {
        log.debug("Request to get TypeEtablissement : {}", id);
        return typeEtablissementRepository.findById(id).map(typeEtablissementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeEtablissement : {}", id);
        typeEtablissementRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one typeEtablissement by structure and code.
     *
     * @param code the code of the typeLocalite.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeEtablissement> findByCode(String code) {
        log.debug("Request to get typeEtablissement : {}", code);
        return typeEtablissementRepository.findByCode( code);    
            // .map(applicationsMapper::toDto);
    }
 
    /**
      * Get one typeEtablissement by Libelle.
      *
      * @param Nom the Nom of the applications.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<TypeEtablissement> findByLibelle(String libelle) {
         log.debug("Request to get typeEtablissement : {}", libelle);
         return typeEtablissementRepository.findByLibelle( libelle);
            // .map(applicationsMapper::toDto);
     }

}
