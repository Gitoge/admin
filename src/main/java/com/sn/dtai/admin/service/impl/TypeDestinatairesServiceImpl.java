package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TypeDestinataires;
import com.sn.dtai.admin.repository.TypeDestinatairesRepository;
import com.sn.dtai.admin.service.TypeDestinatairesService;
import com.sn.dtai.admin.service.dto.TypeDestinatairesDTO;
import com.sn.dtai.admin.service.mapper.TypeDestinatairesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TypeDestinataires}.
 */
@Service
@Transactional
public class TypeDestinatairesServiceImpl implements TypeDestinatairesService {

    private final Logger log = LoggerFactory.getLogger(TypeDestinatairesServiceImpl.class);

    private final TypeDestinatairesRepository typeDestinatairesRepository;

    private final TypeDestinatairesMapper typeDestinatairesMapper;

    public TypeDestinatairesServiceImpl(
        TypeDestinatairesRepository typeDestinatairesRepository,
        TypeDestinatairesMapper typeDestinatairesMapper
    ) {
        this.typeDestinatairesRepository = typeDestinatairesRepository;
        this.typeDestinatairesMapper = typeDestinatairesMapper;
    }

    @Override
    public TypeDestinatairesDTO save(TypeDestinatairesDTO typeDestinatairesDTO) {
        log.debug("Request to save TypeDestinataires : {}", typeDestinatairesDTO);
        TypeDestinataires typeDestinataires = typeDestinatairesMapper.toEntity(typeDestinatairesDTO);
        typeDestinataires = typeDestinatairesRepository.save(typeDestinataires);
        return typeDestinatairesMapper.toDto(typeDestinataires);
    }

    @Override
    public TypeDestinatairesDTO update(TypeDestinatairesDTO typeDestinatairesDTO) {
        log.debug("Request to save TypeDestinataires : {}", typeDestinatairesDTO);
        TypeDestinataires typeDestinataires = typeDestinatairesMapper.toEntity(typeDestinatairesDTO);
        typeDestinataires = typeDestinatairesRepository.save(typeDestinataires);
        return typeDestinatairesMapper.toDto(typeDestinataires);
    }

    @Override
    public Optional<TypeDestinatairesDTO> partialUpdate(TypeDestinatairesDTO typeDestinatairesDTO) {
        log.debug("Request to partially update TypeDestinataires : {}", typeDestinatairesDTO);

        return typeDestinatairesRepository
            .findById(typeDestinatairesDTO.getId())
            .map(existingTypeDestinataires -> {
                typeDestinatairesMapper.partialUpdate(existingTypeDestinataires, typeDestinatairesDTO);

                return existingTypeDestinataires;
            })
            .map(typeDestinatairesRepository::save)
            .map(typeDestinatairesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeDestinatairesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeDestinataires");
        return typeDestinatairesRepository.findAll(pageable).map(typeDestinatairesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeDestinatairesDTO> findOne(Long id) {
        log.debug("Request to get TypeDestinataires : {}", id);
        return typeDestinatairesRepository.findById(id).map(typeDestinatairesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeDestinataires : {}", id);
        typeDestinatairesRepository.deleteById(id);
    }
}
