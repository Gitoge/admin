package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.AssiettesPostes;
import com.sn.dtai.admin.repository.AssiettesPostesRepository;
import com.sn.dtai.admin.service.AssiettesPostesService;
import com.sn.dtai.admin.service.dto.AssiettesPostesDTO;
import com.sn.dtai.admin.service.mapper.AssiettesPostesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AssiettesPostes}.
 */
@Service
@Transactional
public class AssiettesPostesServiceImpl implements AssiettesPostesService {

    private final Logger log = LoggerFactory.getLogger(AssiettesPostesServiceImpl.class);

    private final AssiettesPostesRepository assiettesPostesRepository;

    private final AssiettesPostesMapper assiettesPostesMapper;

    public AssiettesPostesServiceImpl(AssiettesPostesRepository assiettesPostesRepository, AssiettesPostesMapper assiettesPostesMapper) {
        this.assiettesPostesRepository = assiettesPostesRepository;
        this.assiettesPostesMapper = assiettesPostesMapper;
    }

    @Override
    public AssiettesPostesDTO save(AssiettesPostesDTO assiettesPostesDTO) {
        log.debug("Request to save AssiettesPostes : {}", assiettesPostesDTO);
        AssiettesPostes assiettesPostes = assiettesPostesMapper.toEntity(assiettesPostesDTO);
        assiettesPostes = assiettesPostesRepository.save(assiettesPostes);
        return assiettesPostesMapper.toDto(assiettesPostes);
    }

    @Override
    public AssiettesPostesDTO update(AssiettesPostesDTO assiettesPostesDTO) {
        log.debug("Request to save AssiettesPostes : {}", assiettesPostesDTO);
        AssiettesPostes assiettesPostes = assiettesPostesMapper.toEntity(assiettesPostesDTO);
        assiettesPostes = assiettesPostesRepository.save(assiettesPostes);
        return assiettesPostesMapper.toDto(assiettesPostes);
    }

    @Override
    public Optional<AssiettesPostesDTO> partialUpdate(AssiettesPostesDTO assiettesPostesDTO) {
        log.debug("Request to partially update AssiettesPostes : {}", assiettesPostesDTO);

        return assiettesPostesRepository
            .findById(assiettesPostesDTO.getId())
            .map(existingAssiettesPostes -> {
                assiettesPostesMapper.partialUpdate(existingAssiettesPostes, assiettesPostesDTO);

                return existingAssiettesPostes;
            })
            .map(assiettesPostesRepository::save)
            .map(assiettesPostesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssiettesPostesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AssiettesPostess");
        return assiettesPostesRepository.findAll(pageable).map(assiettesPostesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssiettesPostesDTO> findOne(Long id) {
        log.debug("Request to get AssiettesPostes : {}", id);
        return assiettesPostesRepository.findById(id).map(assiettesPostesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AssiettesPostes : {}", id);
        assiettesPostesRepository.deleteById(id);
    }

    @Override
    public void deleteAllByPage(Long assiettesId) {
        // TODO Auto-generated method stub

    }

    @Override
    public Page<AssiettesPostesDTO> findAllAssiettesPostes(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AssiettesPostesDTO> findByAssiettes(Long assiettesId) {
        // TODO Auto-generated method stub
        return assiettesPostesRepository
            .findByAssiettes(assiettesId)
            .stream()
            .map(assiettesPostesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
