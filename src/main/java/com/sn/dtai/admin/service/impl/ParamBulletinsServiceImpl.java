package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.ParamBulletins;
import com.sn.dtai.admin.repository.ParamBulletinsRepository;
import com.sn.dtai.admin.service.ParamBulletinsService;
import com.sn.dtai.admin.service.dto.ParamBulletinsDTO;
import com.sn.dtai.admin.service.mapper.ParamBulletinsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ParamBulletins}.
 */
@Service
@Transactional
public class ParamBulletinsServiceImpl implements ParamBulletinsService {

    private final Logger log = LoggerFactory.getLogger(ParamBulletinsServiceImpl.class);

    private final ParamBulletinsRepository paramBulletinsRepository;

    private final ParamBulletinsMapper paramBulletinsMapper;

    public ParamBulletinsServiceImpl(ParamBulletinsRepository paramBulletinsRepository, ParamBulletinsMapper paramBulletinsMapper) {
        this.paramBulletinsRepository = paramBulletinsRepository;
        this.paramBulletinsMapper = paramBulletinsMapper;
    }

    @Override
    public ParamBulletinsDTO save(ParamBulletinsDTO paramBulletinsDTO) {
        log.debug("Request to save ParamBulletins : {}", paramBulletinsDTO);
        ParamBulletins paramBulletins = paramBulletinsMapper.toEntity(paramBulletinsDTO);
        paramBulletins = paramBulletinsRepository.save(paramBulletins);
        return paramBulletinsMapper.toDto(paramBulletins);
    }

    @Override
    public ParamBulletinsDTO update(ParamBulletinsDTO paramBulletinsDTO) {
        log.debug("Request to save ParamBulletins : {}", paramBulletinsDTO);
        ParamBulletins paramBulletins = paramBulletinsMapper.toEntity(paramBulletinsDTO);
        paramBulletins = paramBulletinsRepository.save(paramBulletins);
        return paramBulletinsMapper.toDto(paramBulletins);
    }

    @Override
    public Optional<ParamBulletinsDTO> partialUpdate(ParamBulletinsDTO paramBulletinsDTO) {
        log.debug("Request to partially update ParamBulletins : {}", paramBulletinsDTO);

        return paramBulletinsRepository
            .findById(paramBulletinsDTO.getId())
            .map(existingParamBulletins -> {
                paramBulletinsMapper.partialUpdate(existingParamBulletins, paramBulletinsDTO);

                return existingParamBulletins;
            })
            .map(paramBulletinsRepository::save)
            .map(paramBulletinsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParamBulletinsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParamBulletins");
        return paramBulletinsRepository.findAll(pageable).map(paramBulletinsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParamBulletinsDTO> findOne(Long id) {
        log.debug("Request to get ParamBulletins : {}", id);
        return paramBulletinsRepository.findById(id).map(paramBulletinsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParamBulletins : {}", id);
        paramBulletinsRepository.deleteById(id);
    }
}
