package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.ParamBaremeImposable;
import com.sn.dtai.admin.repository.ParamBaremeImposableRepository;
import com.sn.dtai.admin.service.ParamBaremeImposableService;
import com.sn.dtai.admin.service.dto.ParamBaremeImposableDTO;
import com.sn.dtai.admin.service.mapper.ParamBaremeImposableMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ParamBaremeImposable}.
 */
@Service
@Transactional
public class ParamBaremeImposableServiceImpl implements ParamBaremeImposableService {

    private final Logger log = LoggerFactory.getLogger(ParamBaremeImposableServiceImpl.class);

    private final ParamBaremeImposableRepository paramBaremeImposableRepository;

    private final ParamBaremeImposableMapper paramBaremeImposableMapper;

    public ParamBaremeImposableServiceImpl(
        ParamBaremeImposableRepository paramBaremeImposableRepository,
        ParamBaremeImposableMapper paramBaremeImposableMapper
    ) {
        this.paramBaremeImposableRepository = paramBaremeImposableRepository;
        this.paramBaremeImposableMapper = paramBaremeImposableMapper;
    }

    @Override
    public ParamBaremeImposableDTO save(ParamBaremeImposableDTO paramBaremeImposableDTO) {
        log.debug("Request to save ParamBaremeImposable : {}", paramBaremeImposableDTO);
        ParamBaremeImposable paramBaremeImposable = paramBaremeImposableMapper.toEntity(paramBaremeImposableDTO);
        paramBaremeImposable = paramBaremeImposableRepository.save(paramBaremeImposable);
        return paramBaremeImposableMapper.toDto(paramBaremeImposable);
    }

    @Override
    public ParamBaremeImposableDTO update(ParamBaremeImposableDTO paramBaremeImposableDTO) {
        log.debug("Request to save ParamBaremeImposable : {}", paramBaremeImposableDTO);
        ParamBaremeImposable paramBaremeImposable = paramBaremeImposableMapper.toEntity(paramBaremeImposableDTO);
        paramBaremeImposable = paramBaremeImposableRepository.save(paramBaremeImposable);
        return paramBaremeImposableMapper.toDto(paramBaremeImposable);
    }

    @Override
    public Optional<ParamBaremeImposableDTO> partialUpdate(ParamBaremeImposableDTO paramBaremeImposableDTO) {
        log.debug("Request to partially update ParamBaremeImposable : {}", paramBaremeImposableDTO);

        return paramBaremeImposableRepository
            .findById(paramBaremeImposableDTO.getId())
            .map(existingParamBaremeImposable -> {
                paramBaremeImposableMapper.partialUpdate(existingParamBaremeImposable, paramBaremeImposableDTO);

                return existingParamBaremeImposable;
            })
            .map(paramBaremeImposableRepository::save)
            .map(paramBaremeImposableMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParamBaremeImposableDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParamBaremeImposables");
        return paramBaremeImposableRepository.findAll(pageable).map(paramBaremeImposableMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParamBaremeImposableDTO> findOne(Long id) {
        log.debug("Request to get ParamBaremeImposable : {}", id);
        return paramBaremeImposableRepository.findById(id).map(paramBaremeImposableMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParamBaremeImposable : {}", id);
        paramBaremeImposableRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Integer> findBySommeandPoste(String codePoste, Integer somme) {
        log.debug("Request to get ParamBaremeImposable : {}", somme);
        return paramBaremeImposableRepository.findBySommeandPoste( codePoste, somme);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Integer> findBySommeAndPoste(String codePoste, Integer somme) {
        log.debug("Request to get ParamBaremeImposable : {}", somme);
        return paramBaremeImposableRepository.findBySommeAndPoste( codePoste, somme);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Integer> sommeProgressive(String codePoste, Integer somme) {
        log.debug("Request to get ParamBaremeImposable : {}", somme);
        return paramBaremeImposableRepository.sommeProgressive( codePoste, somme);
    }
}
