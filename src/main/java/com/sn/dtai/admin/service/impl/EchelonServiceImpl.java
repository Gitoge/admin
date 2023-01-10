package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Echelon;
import com.sn.dtai.admin.repository.EchelonRepository;
import com.sn.dtai.admin.service.EchelonService;
import com.sn.dtai.admin.service.dto.EchelonDTO;
import com.sn.dtai.admin.service.mapper.EchelonMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Echelon}.
 */
@Service
@Transactional
public class EchelonServiceImpl implements EchelonService {

    private final Logger log = LoggerFactory.getLogger(EchelonServiceImpl.class);

    private final EchelonRepository echelonRepository;

    private final EchelonMapper echelonMapper;

    public EchelonServiceImpl(EchelonRepository echelonRepository, EchelonMapper echelonMapper) {
        this.echelonRepository = echelonRepository;
        this.echelonMapper = echelonMapper;
    }

    @Override
    public EchelonDTO save(EchelonDTO echelonDTO) {
        log.debug("Request to save Echelon : {}", echelonDTO);
        Echelon echelon = echelonMapper.toEntity(echelonDTO);
        echelon = echelonRepository.save(echelon);
        return echelonMapper.toDto(echelon);
    }

    @Override
    public EchelonDTO update(EchelonDTO echelonDTO) {
        log.debug("Request to save Echelon : {}", echelonDTO);
        Echelon echelon = echelonMapper.toEntity(echelonDTO);
        echelon = echelonRepository.save(echelon);
        return echelonMapper.toDto(echelon);
    }

    @Override
    public Optional<EchelonDTO> partialUpdate(EchelonDTO echelonDTO) {
        log.debug("Request to partially update Echelon : {}", echelonDTO);

        return echelonRepository
            .findById(echelonDTO.getId())
            .map(existingEchelon -> {
                echelonMapper.partialUpdate(existingEchelon, echelonDTO);

                return existingEchelon;
            })
            .map(echelonRepository::save)
            .map(echelonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EchelonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Echelons");
        return echelonRepository.findAll(pageable).map(echelonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EchelonDTO> findOne(Long id) {
        log.debug("Request to get Echelon : {}", id);
        return echelonRepository.findById(id).map(echelonMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Echelon : {}", id);
        echelonRepository.deleteById(id);
    }

    /**
     * Get one Indices by  code.
     *
     * @param code the code of the Services.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EchelonDTO> findByCode(String code) {
        log.debug("Request to get Echelon : {}", code);
        return echelonRepository.findByCode( code).map(echelonMapper::toDto);

    }
}
