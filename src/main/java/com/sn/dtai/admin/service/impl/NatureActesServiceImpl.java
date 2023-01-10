package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.NatureActes;
import com.sn.dtai.admin.repository.NatureActesRepository;
import com.sn.dtai.admin.service.NatureActesService;
import com.sn.dtai.admin.service.dto.NatureActesDTO;
import com.sn.dtai.admin.service.mapper.NatureActesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NatureActes}.
 */
@Service
@Transactional
public class NatureActesServiceImpl implements NatureActesService {

    private final Logger log = LoggerFactory.getLogger(NatureActesServiceImpl.class);

    private final NatureActesRepository natureActesRepository;

    private final NatureActesMapper natureActesMapper;

    public NatureActesServiceImpl(NatureActesRepository natureActesRepository, NatureActesMapper natureActesMapper) {
        this.natureActesRepository = natureActesRepository;
        this.natureActesMapper = natureActesMapper;
    }

    @Override
    public NatureActesDTO save(NatureActesDTO natureActesDTO) {
        log.debug("Request to save NatureActes : {}", natureActesDTO);
        NatureActes natureActes = natureActesMapper.toEntity(natureActesDTO);
        natureActes = natureActesRepository.save(natureActes);
        return natureActesMapper.toDto(natureActes);
    }

    @Override
    public NatureActesDTO update(NatureActesDTO natureActesDTO) {
        log.debug("Request to save NatureActes : {}", natureActesDTO);
        NatureActes natureActes = natureActesMapper.toEntity(natureActesDTO);
        natureActes = natureActesRepository.save(natureActes);
        return natureActesMapper.toDto(natureActes);
    }

    @Override
    public Optional<NatureActesDTO> partialUpdate(NatureActesDTO natureActesDTO) {
        log.debug("Request to partially update NatureActes : {}", natureActesDTO);

        return natureActesRepository
            .findById(natureActesDTO.getId())
            .map(existingNatureActes -> {
                natureActesMapper.partialUpdate(existingNatureActes, natureActesDTO);

                return existingNatureActes;
            })
            .map(natureActesRepository::save)
            .map(natureActesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NatureActesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NatureActes");
        return natureActesRepository.findAll(pageable).map(natureActesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NatureActesDTO> findOne(Long id) {
        log.debug("Request to get NatureActes : {}", id);
        return natureActesRepository.findById(id).map(natureActesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NatureActes : {}", id);
        natureActesRepository.deleteById(id);
    }
}
