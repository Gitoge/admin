package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TypeActes;
import com.sn.dtai.admin.repository.TypeActesRepository;
import com.sn.dtai.admin.service.TypeActesService;
import com.sn.dtai.admin.service.dto.TypeActesDTO;
import com.sn.dtai.admin.service.mapper.TypeActesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TypeActes}.
 */
@Service
@Transactional
public class TypeActesServiceImpl implements TypeActesService {

    private final Logger log = LoggerFactory.getLogger(TypeActesServiceImpl.class);

    private final TypeActesRepository typeActesRepository;

    private final TypeActesMapper typeActesMapper;

    public TypeActesServiceImpl(TypeActesRepository typeActesRepository, TypeActesMapper typeActesMapper) {
        this.typeActesRepository = typeActesRepository;
        this.typeActesMapper = typeActesMapper;
    }

    @Override
    public TypeActesDTO save(TypeActesDTO typeActesDTO) {
        log.debug("Request to save TypeActes : {}", typeActesDTO);
        TypeActes typeActes = typeActesMapper.toEntity(typeActesDTO);
        typeActes = typeActesRepository.save(typeActes);
        return typeActesMapper.toDto(typeActes);
    }

    @Override
    public TypeActesDTO update(TypeActesDTO typeActesDTO) {
        log.debug("Request to save TypeActes : {}", typeActesDTO);
        TypeActes typeActes = typeActesMapper.toEntity(typeActesDTO);
        typeActes = typeActesRepository.save(typeActes);
        return typeActesMapper.toDto(typeActes);
    }

    @Override
    public Optional<TypeActesDTO> partialUpdate(TypeActesDTO typeActesDTO) {
        log.debug("Request to partially update TypeActes : {}", typeActesDTO);

        return typeActesRepository
            .findById(typeActesDTO.getId())
            .map(existingTypeActes -> {
                typeActesMapper.partialUpdate(existingTypeActes, typeActesDTO);

                return existingTypeActes;
            })
            .map(typeActesRepository::save)
            .map(typeActesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeActesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeActes");
        return typeActesRepository.findAll(pageable).map(typeActesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeActesDTO> findOne(Long id) {
        log.debug("Request to get TypeActes : {}", id);
        return typeActesRepository.findById(id).map(typeActesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeActes : {}", id);
        typeActesRepository.deleteById(id);
    }
}
