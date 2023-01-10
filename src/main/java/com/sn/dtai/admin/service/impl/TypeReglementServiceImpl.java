package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TypeReglement;
import com.sn.dtai.admin.repository.TypeReglementRepository;
import com.sn.dtai.admin.service.TypeReglementService;
import com.sn.dtai.admin.service.dto.TypeReglementDTO;
import com.sn.dtai.admin.service.mapper.TypeReglementMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TypeReglement}.
 */
@Service
@Transactional
public class TypeReglementServiceImpl implements TypeReglementService {

    private final Logger log = LoggerFactory.getLogger(TypeReglementServiceImpl.class);

    private final TypeReglementRepository typeReglementRepository;

    private final TypeReglementMapper typeReglementMapper;

    public TypeReglementServiceImpl(TypeReglementRepository typeReglementRepository, TypeReglementMapper typeReglementMapper) {
        this.typeReglementRepository = typeReglementRepository;
        this.typeReglementMapper = typeReglementMapper;
    }

    @Override
    public TypeReglementDTO save(TypeReglementDTO typeReglementDTO) {
        log.debug("Request to save TypeReglement : {}", typeReglementDTO);
        TypeReglement typeReglement = typeReglementMapper.toEntity(typeReglementDTO);
        typeReglement = typeReglementRepository.save(typeReglement);
        return typeReglementMapper.toDto(typeReglement);
    }

    @Override
    public TypeReglementDTO update(TypeReglementDTO typeReglementDTO) {
        log.debug("Request to save TypeReglement : {}", typeReglementDTO);
        TypeReglement typeReglement = typeReglementMapper.toEntity(typeReglementDTO);
        typeReglement = typeReglementRepository.save(typeReglement);
        return typeReglementMapper.toDto(typeReglement);
    }

    @Override
    public Optional<TypeReglementDTO> partialUpdate(TypeReglementDTO typeReglementDTO) {
        log.debug("Request to partially update TypeReglement : {}", typeReglementDTO);

        return typeReglementRepository
            .findById(typeReglementDTO.getId())
            .map(existingTypeReglement -> {
                typeReglementMapper.partialUpdate(existingTypeReglement, typeReglementDTO);

                return existingTypeReglement;
            })
            .map(typeReglementRepository::save)
            .map(typeReglementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TypeReglementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeReglements");
        return typeReglementRepository.findAll(pageable).map(typeReglementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeReglementDTO> findOne(Long id) {
        log.debug("Request to get TypeReglement : {}", id);
        return typeReglementRepository.findById(id).map(typeReglementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeReglement : {}", id);
        typeReglementRepository.deleteById(id);
    }
}
