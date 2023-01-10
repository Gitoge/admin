package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Positions;
import com.sn.dtai.admin.repository.PositionsRepository;
import com.sn.dtai.admin.service.PositionsService;
import com.sn.dtai.admin.service.dto.PositionsDTO;
import com.sn.dtai.admin.service.mapper.PositionsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Positions}.
 */
@Service
@Transactional
public class PositionsServiceImpl implements PositionsService {

    private final Logger log = LoggerFactory.getLogger(PositionsServiceImpl.class);

    private final PositionsRepository positionsRepository;

    private final PositionsMapper positionsMapper;

    public PositionsServiceImpl(PositionsRepository positionsRepository, PositionsMapper positionsMapper) {
        this.positionsRepository = positionsRepository;
        this.positionsMapper = positionsMapper;
    }

    @Override
    public PositionsDTO save(PositionsDTO positionsDTO) {
        log.debug("Request to save Positions : {}", positionsDTO);
        Positions positions = positionsMapper.toEntity(positionsDTO);
        positions = positionsRepository.save(positions);
        return positionsMapper.toDto(positions);
    }

    @Override
    public PositionsDTO update(PositionsDTO positionsDTO) {
        log.debug("Request to save Positions : {}", positionsDTO);
        Positions positions = positionsMapper.toEntity(positionsDTO);
        positions = positionsRepository.save(positions);
        return positionsMapper.toDto(positions);
    }

    @Override
    public Optional<PositionsDTO> partialUpdate(PositionsDTO positionsDTO) {
        log.debug("Request to partially update Positions : {}", positionsDTO);

        return positionsRepository
            .findById(positionsDTO.getId())
            .map(existingPositions -> {
                positionsMapper.partialUpdate(existingPositions, positionsDTO);

                return existingPositions;
            })
            .map(positionsRepository::save)
            .map(positionsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PositionsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Positions");
        return positionsRepository.findAll(pageable).map(positionsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PositionsDTO> findOne(Long id) {
        log.debug("Request to get Positions : {}", id);
        return positionsRepository.findById(id).map(positionsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Positions : {}", id);
        positionsRepository.deleteById(id);
    }
}
