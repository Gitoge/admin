package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Diplomes;
import com.sn.dtai.admin.repository.DiplomesRepository;
import com.sn.dtai.admin.service.DiplomesService;
import com.sn.dtai.admin.service.dto.DiplomesDTO;
import com.sn.dtai.admin.service.mapper.DiplomesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Diplomes}.
 */
@Service
@Transactional
public class DiplomesServiceImpl implements DiplomesService {

    private final Logger log = LoggerFactory.getLogger(DiplomesServiceImpl.class);

    private final DiplomesRepository diplomesRepository;

    private final DiplomesMapper diplomesMapper;

    public DiplomesServiceImpl(DiplomesRepository diplomesRepository, DiplomesMapper diplomesMapper) {
        this.diplomesRepository = diplomesRepository;
        this.diplomesMapper = diplomesMapper;
    }

    @Override
    public DiplomesDTO save(DiplomesDTO diplomesDTO) {
        log.debug("Request to save Diplomes : {}", diplomesDTO);
        Diplomes diplomes = diplomesMapper.toEntity(diplomesDTO);
        diplomes = diplomesRepository.save(diplomes);
        return diplomesMapper.toDto(diplomes);
    }

    @Override
    public DiplomesDTO update(DiplomesDTO diplomesDTO) {
        log.debug("Request to save Diplomes : {}", diplomesDTO);
        Diplomes diplomes = diplomesMapper.toEntity(diplomesDTO);
        diplomes = diplomesRepository.save(diplomes);
        return diplomesMapper.toDto(diplomes);
    }

    @Override
    public Optional<DiplomesDTO> partialUpdate(DiplomesDTO diplomesDTO) {
        log.debug("Request to partially update Diplomes : {}", diplomesDTO);

        return diplomesRepository
            .findById(diplomesDTO.getId())
            .map(existingDiplomes -> {
                diplomesMapper.partialUpdate(existingDiplomes, diplomesDTO);

                return existingDiplomes;
            })
            .map(diplomesRepository::save)
            .map(diplomesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DiplomesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Diplomes");
        return diplomesRepository.findAll(pageable).map(diplomesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DiplomesDTO> findOne(Long id) {
        log.debug("Request to get Diplomes : {}", id);
        return diplomesRepository.findById(id).map(diplomesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Diplomes : {}", id);
        diplomesRepository.deleteById(id);
    }
}
