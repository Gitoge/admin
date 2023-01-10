package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Indices;
import com.sn.dtai.admin.repository.IndicesRepository;
import com.sn.dtai.admin.service.IndicesService;
import com.sn.dtai.admin.service.dto.IndicesDTO;
import com.sn.dtai.admin.service.mapper.IndicesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Indices}.
 */
@Service
@Transactional
public class IndicesServiceImpl implements IndicesService {

    private final Logger log = LoggerFactory.getLogger(IndicesServiceImpl.class);

    private final IndicesRepository indicesRepository;

    private final IndicesMapper indicesMapper;

    public IndicesServiceImpl(IndicesRepository indicesRepository, IndicesMapper indicesMapper) {
        this.indicesRepository = indicesRepository;
        this.indicesMapper = indicesMapper;
    }

    @Override
    public IndicesDTO save(IndicesDTO indicesDTO) {
        log.debug("Request to save Indices : {}", indicesDTO);
        Indices indices = indicesMapper.toEntity(indicesDTO);
        indices = indicesRepository.save(indices);
        return indicesMapper.toDto(indices);
    }

    @Override
    public IndicesDTO update(IndicesDTO indicesDTO) {
        log.debug("Request to save Indices : {}", indicesDTO);
        Indices indices = indicesMapper.toEntity(indicesDTO);
        indices = indicesRepository.save(indices);
        return indicesMapper.toDto(indices);
    }

    @Override
    public Optional<IndicesDTO> partialUpdate(IndicesDTO indicesDTO) {
        log.debug("Request to partially update Indices : {}", indicesDTO);

        return indicesRepository
            .findById(indicesDTO.getId())
            .map(existingIndices -> {
                indicesMapper.partialUpdate(existingIndices, indicesDTO);

                return existingIndices;
            })
            .map(indicesRepository::save)
            .map(indicesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IndicesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Indices");
        return indicesRepository.findAll(pageable).map(indicesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IndicesDTO> findOne(Long id) {
        log.debug("Request to get Indices : {}", id);
        return indicesRepository.findById(id).map(indicesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Indices : {}", id);
        indicesRepository.deleteById(id);
    }

    /**
     * Get one Indices by  code.
     *
     * @param code the code of the Services.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IndicesDTO> findByCode(String code) {
        log.debug("Request to get Indice : {}", code);
        return indicesRepository.findByCode( code).map(indicesMapper::toDto);

    }
}
