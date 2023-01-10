package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Direction;
import com.sn.dtai.admin.repository.DirectionRepository;
import com.sn.dtai.admin.service.DirectionService;
import com.sn.dtai.admin.service.dto.DirectionDTO;
import com.sn.dtai.admin.service.mapper.DirectionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Direction}.
 */
@Service
@Transactional
public class DirectionServiceImpl implements DirectionService {

    private final Logger log = LoggerFactory.getLogger(DirectionServiceImpl.class);

    private final DirectionRepository directionRepository;

    private final DirectionMapper directionMapper;

    public DirectionServiceImpl(DirectionRepository directionRepository, DirectionMapper directionMapper) {
        this.directionRepository = directionRepository;
        this.directionMapper = directionMapper;
    }

    @Override
    public DirectionDTO save(DirectionDTO directionDTO) {
        log.debug("Request to save Direction : {}", directionDTO);
        Direction direction = directionMapper.toEntity(directionDTO);
        direction = directionRepository.save(direction);
        return directionMapper.toDto(direction);
    }

    @Override
    public DirectionDTO update(DirectionDTO directionDTO) {
        log.debug("Request to save Direction : {}", directionDTO);
        Direction direction = directionMapper.toEntity(directionDTO);
        direction = directionRepository.save(direction);
        return directionMapper.toDto(direction);
    }

    @Override
    public Optional<DirectionDTO> partialUpdate(DirectionDTO directionDTO) {
        log.debug("Request to partially update Direction : {}", directionDTO);

        return directionRepository
            .findById(directionDTO.getId())
            .map(existingDirection -> {
                directionMapper.partialUpdate(existingDirection, directionDTO);

                return existingDirection;
            })
            .map(directionRepository::save)
            .map(directionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DirectionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Directions");
        return directionRepository.findAll(pageable).map(directionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DirectionDTO> findOne(Long id) {
        log.debug("Request to get Direction : {}", id);
        return directionRepository.findById(id).map(directionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Direction : {}", id);
        directionRepository.deleteById(id);
    }

    
    // Méthodes Ajoutées

    /**
     * Get one Direction by  code.
     *
     * @param code the code of the direction.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Direction> findByCode(String code) {
        log.debug("Request to get Direction : {}", code);
        return directionRepository.findByCode( code);    
            // .map(applicationsMapper::toDto);
    }
 
    /**
      * Get one Etablissement by Libelle.
      *
      * @param Libelle the Libelle of the etablissement.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Direction> findByLibelle(String libelle) {
         log.debug("Request to get Direction : {}", libelle);
         return directionRepository.findByLibelle( libelle);
            // .map(applicationsMapper::toDto);
     }
}
