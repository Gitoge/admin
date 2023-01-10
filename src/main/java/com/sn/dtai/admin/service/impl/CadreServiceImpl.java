package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Cadre;
import com.sn.dtai.admin.repository.CadreRepository;
import com.sn.dtai.admin.service.CadreService;
import com.sn.dtai.admin.service.dto.CadreDTO;
import com.sn.dtai.admin.service.mapper.CadreMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Cadre}.
 */
@Service
@Transactional
public class CadreServiceImpl implements CadreService {

    private final Logger log = LoggerFactory.getLogger(CadreServiceImpl.class);

    private final CadreRepository cadreRepository;

    private final CadreMapper cadreMapper;

    public CadreServiceImpl(CadreRepository cadreRepository, CadreMapper cadreMapper) {
        this.cadreRepository = cadreRepository;
        this.cadreMapper = cadreMapper;
    }

    @Override
    public CadreDTO save(CadreDTO cadreDTO) {
        log.debug("Request to save Cadre : {}", cadreDTO);
        Cadre cadre = cadreMapper.toEntity(cadreDTO);
        cadre = cadreRepository.save(cadre);
        return cadreMapper.toDto(cadre);
    }

    @Override
    public CadreDTO update(CadreDTO cadreDTO) {
        log.debug("Request to save Cadre : {}", cadreDTO);
        Cadre cadre = cadreMapper.toEntity(cadreDTO);
        cadre = cadreRepository.save(cadre);
        return cadreMapper.toDto(cadre);
    }

    @Override
    public Optional<CadreDTO> partialUpdate(CadreDTO cadreDTO) {
        log.debug("Request to partially update Cadre : {}", cadreDTO);

        return cadreRepository
            .findById(cadreDTO.getId())
            .map(existingCadre -> {
                cadreMapper.partialUpdate(existingCadre, cadreDTO);

                return existingCadre;
            })
            .map(cadreRepository::save)
            .map(cadreMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CadreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cadres");
        return cadreRepository.findAll(pageable).map(cadreMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CadreDTO> findOne(Long id) {
        log.debug("Request to get Cadre : {}", id);
        return cadreRepository.findById(id).map(cadreMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cadre : {}", id);
        cadreRepository.deleteById(id);
    }
}
