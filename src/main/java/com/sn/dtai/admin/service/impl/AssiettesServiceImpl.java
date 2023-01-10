package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Assiettes;
import com.sn.dtai.admin.repository.AssiettesRepository;
import com.sn.dtai.admin.service.AssiettesService;
import com.sn.dtai.admin.service.dto.AssiettesDTO;
import com.sn.dtai.admin.service.mapper.AssiettesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Assiettes}.
 */
@Service
@Transactional
public class AssiettesServiceImpl implements AssiettesService {

    private final Logger log = LoggerFactory.getLogger(AssiettesServiceImpl.class);

    private final AssiettesRepository assiettesRepository;

    private final AssiettesMapper assiettesMapper;

    public AssiettesServiceImpl(AssiettesRepository assiettesRepository, AssiettesMapper assiettesMapper) {
        this.assiettesRepository = assiettesRepository;
        this.assiettesMapper = assiettesMapper;
    }

    @Override
    public AssiettesDTO save(AssiettesDTO assiettesDTO) {
        log.debug("Request to save Assiettes : {}", assiettesDTO);
        Assiettes assiettes = assiettesMapper.toEntity(assiettesDTO);
        assiettes = assiettesRepository.save(assiettes);
        return assiettesMapper.toDto(assiettes);
    }

    @Override
    public AssiettesDTO update(AssiettesDTO assiettesDTO) {
        log.debug("Request to save Assiettes : {}", assiettesDTO);
        Assiettes assiettes = assiettesMapper.toEntity(assiettesDTO);
        assiettes = assiettesRepository.save(assiettes);
        return assiettesMapper.toDto(assiettes);
    }

    @Override
    public Optional<AssiettesDTO> partialUpdate(AssiettesDTO assiettesDTO) {
        log.debug("Request to partially update Assiettes : {}", assiettesDTO);

        return assiettesRepository
            .findById(assiettesDTO.getId())
            .map(existingAssiettes -> {
                assiettesMapper.partialUpdate(existingAssiettes, assiettesDTO);

                return existingAssiettes;
            })
            .map(assiettesRepository::save)
            .map(assiettesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssiettesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Assiettes");
        return assiettesRepository.findAll(pageable).map(assiettesMapper::toDto);
    }

    public Page<AssiettesDTO> findAllWithEagerRelationships(Pageable pageable) {
        return assiettesRepository.findAllWithEagerRelationships(pageable).map(assiettesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssiettesDTO> findOne(Long id) {
        log.debug("Request to get Assiettes : {}", id);
        return assiettesRepository.findOneWithEagerRelationships(id).map(assiettesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Assiettes : {}", id);
        assiettesRepository.deleteById(id);
    }

     
    @Override
    @Transactional(readOnly = true)
    public Optional<AssiettesDTO> findOne(String code) {
        log.debug("Request to get Applications : {}", code);
        return assiettesRepository.findByCode(code).map(assiettesMapper::toDto);
    }
}
