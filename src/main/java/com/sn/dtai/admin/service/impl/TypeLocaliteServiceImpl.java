package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TypeLocalite;
import com.sn.dtai.admin.repository.TypeLocaliteRepository;
import com.sn.dtai.admin.service.TypeLocaliteService;
import com.sn.dtai.admin.service.dto.TypeLocaliteDTO;
import com.sn.dtai.admin.service.mapper.TypeLocaliteMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TypeLocalite}.
 */
@Service
@Transactional
public class TypeLocaliteServiceImpl implements TypeLocaliteService {

    private final Logger log = LoggerFactory.getLogger(TypeLocaliteServiceImpl.class);

    private final TypeLocaliteRepository typeLocaliteRepository;

    private final TypeLocaliteMapper typeLocaliteMapper;

    public TypeLocaliteServiceImpl(TypeLocaliteRepository typeLocaliteRepository, TypeLocaliteMapper typeLocaliteMapper) {
        this.typeLocaliteRepository = typeLocaliteRepository;
        this.typeLocaliteMapper = typeLocaliteMapper;
    }

    @Override
    public TypeLocaliteDTO save(TypeLocaliteDTO typeLocaliteDTO) {
        log.debug("Request to save TypeLocalite : {}", typeLocaliteDTO);
        TypeLocalite typeLocalite = typeLocaliteMapper.toEntity(typeLocaliteDTO);
        typeLocalite = typeLocaliteRepository.save(typeLocalite);
        return typeLocaliteMapper.toDto(typeLocalite);
    }

    @Override
    public TypeLocaliteDTO update(TypeLocaliteDTO typeLocaliteDTO) {
        log.debug("Request to save TypeLocalite : {}", typeLocaliteDTO);
        TypeLocalite typeLocalite = typeLocaliteMapper.toEntity(typeLocaliteDTO);
        typeLocalite = typeLocaliteRepository.save(typeLocalite);
        return typeLocaliteMapper.toDto(typeLocalite);
    }

    @Override
    public Optional<TypeLocaliteDTO> partialUpdate(TypeLocaliteDTO typeLocaliteDTO) {
        log.debug("Request to partially update TypeLocalite : {}", typeLocaliteDTO);

        return typeLocaliteRepository
            .findById(typeLocaliteDTO.getId())
            .map(existingTypeLocalite -> {
                typeLocaliteMapper.partialUpdate(existingTypeLocalite, typeLocaliteDTO);

                return existingTypeLocalite;
            })
            .map(typeLocaliteRepository::save)
            .map(typeLocaliteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeLocaliteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeLocalites");
        return typeLocaliteRepository.findAll(pageable).map(typeLocaliteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeLocaliteDTO> findOne(Long id) {
        log.debug("Request to get TypeLocalite : {}", id);
        return typeLocaliteRepository.findById(id).map(typeLocaliteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeLocalite : {}", id);
        typeLocaliteRepository.deleteById(id);
    }


    // Méthodes Ajoutées

    /**
     * Get one typeLocalite by structure and code.
     *
     * @param code the code of the typeLocalite.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeLocalite> findByCode(String code) {
        log.debug("Request to get typeLocalite : {}", code);
        return typeLocaliteRepository.findByCode( code);    
            // .map(applicationsMapper::toDto);
    }
 
    /**
      * Get one typeLocalite by Libelle.
      *
      * @param Nom the Nom of the applications.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<TypeLocalite> findByLibelle(String libelle) {
         log.debug("Request to get typeLocalite : {}", libelle);
         return typeLocaliteRepository.findByLibelle( libelle);
            // .map(applicationsMapper::toDto);
     }

}
