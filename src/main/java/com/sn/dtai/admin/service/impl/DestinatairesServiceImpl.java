package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Destinataires;
import com.sn.dtai.admin.repository.DestinatairesRepository;
import com.sn.dtai.admin.service.DestinatairesService;
import com.sn.dtai.admin.service.dto.DestinatairesDTO;
import com.sn.dtai.admin.service.mapper.DestinatairesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Destinataires}.
 */
@Service
@Transactional
public class DestinatairesServiceImpl implements DestinatairesService {

    private final Logger log = LoggerFactory.getLogger(DestinatairesServiceImpl.class);

    private final DestinatairesRepository destinatairesRepository;

    private final DestinatairesMapper destinatairesMapper;

    public DestinatairesServiceImpl(DestinatairesRepository destinatairesRepository, DestinatairesMapper destinatairesMapper) {
        this.destinatairesRepository = destinatairesRepository;
        this.destinatairesMapper = destinatairesMapper;
    }

    @Override
    public DestinatairesDTO save(DestinatairesDTO destinatairesDTO) {
        log.debug("Request to save Destinataires : {}", destinatairesDTO);
        Destinataires destinataires = destinatairesMapper.toEntity(destinatairesDTO);
        destinataires = destinatairesRepository.save(destinataires);
        return destinatairesMapper.toDto(destinataires);
    }

    @Override
    public DestinatairesDTO update(DestinatairesDTO destinatairesDTO) {
        log.debug("Request to save Destinataires : {}", destinatairesDTO);
        Destinataires destinataires = destinatairesMapper.toEntity(destinatairesDTO);
        destinataires = destinatairesRepository.save(destinataires);
        return destinatairesMapper.toDto(destinataires);
    }

    @Override
    public Optional<DestinatairesDTO> partialUpdate(DestinatairesDTO destinatairesDTO) {
        log.debug("Request to partially update Destinataires : {}", destinatairesDTO);

        return destinatairesRepository
            .findById(destinatairesDTO.getId())
            .map(existingDestinataires -> {
                destinatairesMapper.partialUpdate(existingDestinataires, destinatairesDTO);

                return existingDestinataires;
            })
            .map(destinatairesRepository::save)
            .map(destinatairesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DestinatairesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Destinataires");
        return destinatairesRepository.findAll(pageable).map(destinatairesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DestinatairesDTO> findOne(Long id) {
        log.debug("Request to get Destinataires : {}", id);
        return destinatairesRepository.findById(id).map(destinatairesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Destinataires : {}", id);
        destinatairesRepository.deleteById(id);
    }
}
