package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TypePosition;
import com.sn.dtai.admin.repository.TypePositionRepository;
import com.sn.dtai.admin.service.TypePositionService;
import com.sn.dtai.admin.service.dto.TypePositionDTO;
import com.sn.dtai.admin.service.mapper.TypePositionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TypePosition}.
 */
@Service
@Transactional
public class TypePositionServiceImpl implements TypePositionService {

    private final Logger log = LoggerFactory.getLogger(TypePositionServiceImpl.class);

    private final TypePositionRepository typePositionRepository;

    private final TypePositionMapper typePositionMapper;

    public TypePositionServiceImpl(TypePositionRepository typePositionRepository, TypePositionMapper typePositionMapper) {
        this.typePositionRepository = typePositionRepository;
        this.typePositionMapper = typePositionMapper;
    }

    @Override
    public TypePositionDTO save(TypePositionDTO typePositionDTO) {
        log.debug("Request to save TypePosition : {}", typePositionDTO);
        TypePosition typePosition = typePositionMapper.toEntity(typePositionDTO);
        typePosition = typePositionRepository.save(typePosition);
        return typePositionMapper.toDto(typePosition);
    }

    @Override
    public TypePositionDTO update(TypePositionDTO typePositionDTO) {
        log.debug("Request to save TypePosition : {}", typePositionDTO);
        TypePosition typePosition = typePositionMapper.toEntity(typePositionDTO);
        typePosition = typePositionRepository.save(typePosition);
        return typePositionMapper.toDto(typePosition);
    }

    @Override
    public Optional<TypePositionDTO> partialUpdate(TypePositionDTO typePositionDTO) {
        log.debug("Request to partially update TypePosition : {}", typePositionDTO);

        return typePositionRepository
            .findById(typePositionDTO.getId())
            .map(existingTypePosition -> {
                typePositionMapper.partialUpdate(existingTypePosition, typePositionDTO);

                return existingTypePosition;
            })
            .map(typePositionRepository::save)
            .map(typePositionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypePositionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypePositions");
        return typePositionRepository.findAll(pageable).map(typePositionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypePositionDTO> findOne(Long id) {
        log.debug("Request to get TypePosition : {}", id);
        return typePositionRepository.findById(id).map(typePositionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypePosition : {}", id);
        typePositionRepository.deleteById(id);
    }
}
