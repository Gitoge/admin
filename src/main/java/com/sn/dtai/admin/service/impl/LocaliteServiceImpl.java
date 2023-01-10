package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Localite;
import com.sn.dtai.admin.repository.LocaliteRepository;
import com.sn.dtai.admin.service.LocaliteService;
import com.sn.dtai.admin.service.dto.LocaliteDTO;
import com.sn.dtai.admin.service.mapper.LocaliteMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Localite}.
 */
@Service
@Transactional
public class LocaliteServiceImpl implements LocaliteService {

    private final Logger log = LoggerFactory.getLogger(LocaliteServiceImpl.class);

    private final LocaliteRepository localiteRepository;

    private final LocaliteMapper localiteMapper;

    public LocaliteServiceImpl(LocaliteRepository localiteRepository, LocaliteMapper localiteMapper) {
        this.localiteRepository = localiteRepository;
        this.localiteMapper = localiteMapper;
    }

    @Override
    public LocaliteDTO save(LocaliteDTO localiteDTO) {
        log.debug("Request to save Localite : {}", localiteDTO);
        Localite localite = localiteMapper.toEntity(localiteDTO);
        localite = localiteRepository.save(localite);
        return localiteMapper.toDto(localite);
    }

    @Override
    public LocaliteDTO update(LocaliteDTO localiteDTO) {
        log.debug("Request to save Localite : {}", localiteDTO);
        Localite localite = localiteMapper.toEntity(localiteDTO);
        localite = localiteRepository.save(localite);
        return localiteMapper.toDto(localite);
    }

    @Override
    public Optional<LocaliteDTO> partialUpdate(LocaliteDTO localiteDTO) {
        log.debug("Request to partially update Localite : {}", localiteDTO);

        return localiteRepository
            .findById(localiteDTO.getId())
            .map(existingLocalite -> {
                localiteMapper.partialUpdate(existingLocalite, localiteDTO);

                return existingLocalite;
            })
            .map(localiteRepository::save)
            .map(localiteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LocaliteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Localites");
        return localiteRepository.findAll(pageable).map(localiteMapper::toDto);
    }

    /**
     *  Get all the localites where Localite is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LocaliteDTO> findAllWhereLocaliteIsNull() {
        log.debug("Request to get all localites where Localite is null");
        return StreamSupport
            .stream(localiteRepository.findAll().spliterator(), false)
            .filter(localite -> localite.getLocalites() == null)
            .map(localiteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LocaliteDTO> findOne(Long id) {
        log.debug("Request to get Localite : {}", id);
        return localiteRepository.findById(id).map(localiteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Localite : {}", id);
        localiteRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one Localite by  code.
     *
     * @param code the code of the Localite.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Localite> findByCode(String code) {
        log.debug("Request to get Localite : {}", code);
        return localiteRepository.findByCode( code);    
            // .map(applicationsMapper::toDto);
    }
 
    /**
      * Get one typeLocalite by Libelle.
      *
      * @param Nom the Nom of the applications.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Localite> findByLibelle(String libelle) {
         log.debug("Request to get Localite : {}", libelle);
         return localiteRepository.findByLibelle( libelle);
            // .map(applicationsMapper::toDto);
     }
}
