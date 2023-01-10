package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.ParamBaremeMinimumFiscal;
import com.sn.dtai.admin.repository.ParamBaremeMinimumFiscalRepository;
import com.sn.dtai.admin.service.ParamBaremeMinimumFiscalService;
import com.sn.dtai.admin.service.dto.ParamBaremeMinimumFiscalDTO;
import com.sn.dtai.admin.service.mapper.ParamBaremeMinimumFiscalMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ParamBaremeMinimumFiscal}.
 */
@Service
@Transactional
public class ParamBaremeMinimumFiscalServiceImpl implements ParamBaremeMinimumFiscalService {

    private final Logger log = LoggerFactory.getLogger(ParamBaremeMinimumFiscalServiceImpl.class);

    private final ParamBaremeMinimumFiscalRepository paramBaremeMinimumFiscalRepository;

    private final ParamBaremeMinimumFiscalMapper paramBaremeMinimumFiscalMapper;

    public ParamBaremeMinimumFiscalServiceImpl(
        ParamBaremeMinimumFiscalRepository paramBaremeMinimumFiscalRepository,
        ParamBaremeMinimumFiscalMapper paramBaremeMinimumFiscalMapper
    ) {
        this.paramBaremeMinimumFiscalRepository = paramBaremeMinimumFiscalRepository;
        this.paramBaremeMinimumFiscalMapper = paramBaremeMinimumFiscalMapper;
    }

    @Override
    public ParamBaremeMinimumFiscalDTO save(ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO) {
        log.debug("Request to save ParamBaremeMinimumFiscal : {}", paramBaremeMinimumFiscalDTO);
        ParamBaremeMinimumFiscal paramBaremeMinimumFiscal = paramBaremeMinimumFiscalMapper.toEntity(paramBaremeMinimumFiscalDTO);
        paramBaremeMinimumFiscal = paramBaremeMinimumFiscalRepository.save(paramBaremeMinimumFiscal);
        return paramBaremeMinimumFiscalMapper.toDto(paramBaremeMinimumFiscal);
    }

    @Override
    public ParamBaremeMinimumFiscalDTO update(ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO) {
        log.debug("Request to save ParamBaremeMinimumFiscal : {}", paramBaremeMinimumFiscalDTO);
        ParamBaremeMinimumFiscal paramBaremeMinimumFiscal = paramBaremeMinimumFiscalMapper.toEntity(paramBaremeMinimumFiscalDTO);
        paramBaremeMinimumFiscal = paramBaremeMinimumFiscalRepository.save(paramBaremeMinimumFiscal);
        return paramBaremeMinimumFiscalMapper.toDto(paramBaremeMinimumFiscal);
    }

    @Override
    public Optional<ParamBaremeMinimumFiscalDTO> partialUpdate(ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO) {
        log.debug("Request to partially update ParamBaremeMinimumFiscal : {}", paramBaremeMinimumFiscalDTO);

        return paramBaremeMinimumFiscalRepository
            .findById(paramBaremeMinimumFiscalDTO.getId())
            .map(existingParamBaremeMinimumFiscal -> {
                paramBaremeMinimumFiscalMapper.partialUpdate(existingParamBaremeMinimumFiscal, paramBaremeMinimumFiscalDTO);

                return existingParamBaremeMinimumFiscal;
            })
            .map(paramBaremeMinimumFiscalRepository::save)
            .map(paramBaremeMinimumFiscalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParamBaremeMinimumFiscalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParamBaremeMinimumFiscals");
        return paramBaremeMinimumFiscalRepository.findAll(pageable).map(paramBaremeMinimumFiscalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParamBaremeMinimumFiscalDTO> findOne(Long id) {
        log.debug("Request to get ParamBaremeMinimumFiscal : {}", id);
        return paramBaremeMinimumFiscalRepository.findById(id).map(paramBaremeMinimumFiscalMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParamBaremeMinimumFiscal : {}", id);
        paramBaremeMinimumFiscalRepository.deleteById(id);
    }
}
