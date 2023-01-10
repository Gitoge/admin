package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.ParamQuotiteCessible;
import com.sn.dtai.admin.repository.ParamQuotiteCessibleRepository;
import com.sn.dtai.admin.service.ParamQuotiteCessibleService;
import com.sn.dtai.admin.service.dto.ParamQuotiteCessibleDTO;
import com.sn.dtai.admin.service.mapper.ParamQuotiteCessibleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ParamQuotiteCessible}.
 */
@Service
@Transactional
public class ParamQuotiteCessibleServiceImpl implements ParamQuotiteCessibleService {

    private final Logger log = LoggerFactory.getLogger(ParamQuotiteCessibleServiceImpl.class);

    private final ParamQuotiteCessibleRepository paramQuotiteCessibleRepository;

    private final ParamQuotiteCessibleMapper paramQuotiteCessibleMapper;

    public ParamQuotiteCessibleServiceImpl(
        ParamQuotiteCessibleRepository paramQuotiteCessibleRepository,
        ParamQuotiteCessibleMapper paramQuotiteCessibleMapper
    ) {
        this.paramQuotiteCessibleRepository = paramQuotiteCessibleRepository;
        this.paramQuotiteCessibleMapper = paramQuotiteCessibleMapper;
    }

    @Override
    public ParamQuotiteCessibleDTO save(ParamQuotiteCessibleDTO paramQuotiteCessibleDTO) {
        log.debug("Request to save ParamQuotiteCessible : {}", paramQuotiteCessibleDTO);
        ParamQuotiteCessible paramQuotiteCessible = paramQuotiteCessibleMapper.toEntity(paramQuotiteCessibleDTO);
        paramQuotiteCessible = paramQuotiteCessibleRepository.save(paramQuotiteCessible);
        return paramQuotiteCessibleMapper.toDto(paramQuotiteCessible);
    }

    @Override
    public ParamQuotiteCessibleDTO update(ParamQuotiteCessibleDTO paramQuotiteCessibleDTO) {
        log.debug("Request to save ParamQuotiteCessible : {}", paramQuotiteCessibleDTO);
        ParamQuotiteCessible paramQuotiteCessible = paramQuotiteCessibleMapper.toEntity(paramQuotiteCessibleDTO);
        paramQuotiteCessible = paramQuotiteCessibleRepository.save(paramQuotiteCessible);
        return paramQuotiteCessibleMapper.toDto(paramQuotiteCessible);
    }

    @Override
    public Optional<ParamQuotiteCessibleDTO> partialUpdate(ParamQuotiteCessibleDTO paramQuotiteCessibleDTO) {
        log.debug("Request to partially update ParamQuotiteCessible : {}", paramQuotiteCessibleDTO);

        return paramQuotiteCessibleRepository
            .findById(paramQuotiteCessibleDTO.getId())
            .map(existingParamQuotiteCessible -> {
                paramQuotiteCessibleMapper.partialUpdate(existingParamQuotiteCessible, paramQuotiteCessibleDTO);

                return existingParamQuotiteCessible;
            })
            .map(paramQuotiteCessibleRepository::save)
            .map(paramQuotiteCessibleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParamQuotiteCessibleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParamQuotiteCessibles");
        return paramQuotiteCessibleRepository.findAll(pageable).map(paramQuotiteCessibleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParamQuotiteCessibleDTO> findOne(Long id) {
        log.debug("Request to get ParamQuotiteCessible : {}", id);
        return paramQuotiteCessibleRepository.findById(id).map(paramQuotiteCessibleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParamQuotiteCessible : {}", id);
        paramQuotiteCessibleRepository.deleteById(id);
    }
}
