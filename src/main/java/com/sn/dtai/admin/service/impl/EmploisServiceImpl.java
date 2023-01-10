package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Emplois;
import com.sn.dtai.admin.repository.EmploisRepository;
import com.sn.dtai.admin.service.EmploisService;
import com.sn.dtai.admin.service.dto.EmploisDTO;
import com.sn.dtai.admin.service.mapper.EmploisMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Emplois}.
 */
@Service
@Transactional
public class EmploisServiceImpl implements EmploisService {

    private final Logger log = LoggerFactory.getLogger(EmploisServiceImpl.class);

    private final EmploisRepository emploisRepository;

    private final EmploisMapper emploisMapper;

    public EmploisServiceImpl(EmploisRepository emploisRepository, EmploisMapper emploisMapper) {
        this.emploisRepository = emploisRepository;
        this.emploisMapper = emploisMapper;
    }

    @Override
    public EmploisDTO save(EmploisDTO emploisDTO) {
        log.debug("Request to save Emplois : {}", emploisDTO);
        Emplois emplois = emploisMapper.toEntity(emploisDTO);
        emplois = emploisRepository.save(emplois);
        return emploisMapper.toDto(emplois);
    }

    @Override
    public EmploisDTO update(EmploisDTO emploisDTO) {
        log.debug("Request to save Emplois : {}", emploisDTO);
        Emplois emplois = emploisMapper.toEntity(emploisDTO);
        emplois = emploisRepository.save(emplois);
        return emploisMapper.toDto(emplois);
    }

    @Override
    public Optional<EmploisDTO> partialUpdate(EmploisDTO emploisDTO) {
        log.debug("Request to partially update Emplois : {}", emploisDTO);

        return emploisRepository
            .findById(emploisDTO.getId())
            .map(existingEmplois -> {
                emploisMapper.partialUpdate(existingEmplois, emploisDTO);

                return existingEmplois;
            })
            .map(emploisRepository::save)
            .map(emploisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmploisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Emplois");
        return emploisRepository.findAll(pageable).map(emploisMapper::toDto);
    }

    public Page<EmploisDTO> findAllWithEagerRelationships(Pageable pageable) {
        return emploisRepository.findAllWithEagerRelationships(pageable).map(emploisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmploisDTO> findOne(Long id) {
        log.debug("Request to get Emplois : {}", id);
        return emploisRepository.findOneWithEagerRelationships(id).map(emploisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Emplois : {}", id);
        emploisRepository.deleteById(id);
    }

     /**
     * Get one Direction by  code.
     *
     * @param code the code of the direction.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmploisDTO> findByCode(String code) {
        log.debug("Request to get Direction : {}", code);
        return emploisRepository.findByCode(code).map(emploisMapper::toDto); 
    }
}
