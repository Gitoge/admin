package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Reglement;
import com.sn.dtai.admin.repository.ReglementRepository;
import com.sn.dtai.admin.service.ReglementService;
import com.sn.dtai.admin.service.dto.ReglementDTO;
import com.sn.dtai.admin.service.mapper.ReglementMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Reglement}.
 */
@Service
@Transactional
public class ReglementServiceImpl implements ReglementService {

    private final Logger log = LoggerFactory.getLogger(ReglementServiceImpl.class);

    private final ReglementRepository reglementRepository;

    private final ReglementMapper reglementMapper;

    public ReglementServiceImpl(ReglementRepository reglementRepository, ReglementMapper reglementMapper) {
        this.reglementRepository = reglementRepository;
        this.reglementMapper = reglementMapper;
    }

    @Override
    public ReglementDTO save(ReglementDTO reglementDTO) {
        log.debug("Request to save Reglement : {}", reglementDTO);
        Reglement reglement = reglementMapper.toEntity(reglementDTO);
        reglement = reglementRepository.save(reglement);
        return reglementMapper.toDto(reglement);
    }

    @Override
    public ReglementDTO update(ReglementDTO reglementDTO) {
        log.debug("Request to save Reglement : {}", reglementDTO);
        Reglement reglement = reglementMapper.toEntity(reglementDTO);
        reglement = reglementRepository.save(reglement);
        return reglementMapper.toDto(reglement);
    }

    @Override
    public Optional<ReglementDTO> partialUpdate(ReglementDTO reglementDTO) {
        log.debug("Request to partially update Reglement : {}", reglementDTO);

        return reglementRepository
            .findById(reglementDTO.getId())
            .map(existingReglement -> {
                reglementMapper.partialUpdate(existingReglement, reglementDTO);

                return existingReglement;
            })
            .map(reglementRepository::save)
            .map(reglementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReglementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reglements");
        return reglementRepository.findAll(pageable).map(reglementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReglementDTO> findOne(Long id) {
        log.debug("Request to get Reglement : {}", id);
        return reglementRepository.findById(id).map(reglementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reglement : {}", id);
        reglementRepository.deleteById(id);
    }
}
