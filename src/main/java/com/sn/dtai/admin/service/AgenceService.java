package com.sn.dtai.admin.service;

import com.sn.dtai.admin.domain.Agence;
import com.sn.dtai.admin.repository.AgenceRepository;
import com.sn.dtai.admin.service.dto.AgenceDTO;
import com.sn.dtai.admin.service.mapper.AgenceMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Agence}.
 */
@Service
@Transactional
public class AgenceService {

    private final Logger log = LoggerFactory.getLogger(AgenceService.class);

    private final AgenceRepository agenceRepository;

    private final AgenceMapper agenceMapper;

    public AgenceService(
        AgenceRepository agenceRepository,
        AgenceMapper agenceMapper
    ) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    /**
     * Save a agence.
     *
     * @param agenceDTO the entity to save.
     * @return the persisted entity.
     */
    public AgenceDTO save(AgenceDTO agenceDTO) {
        log.debug("Request to save Agence : {}", agenceDTO);
        Agence agence = agenceMapper.toEntity(agenceDTO);
        agence = agenceRepository.save(agence);
        return agenceMapper.toDto(agence);
    }

    /**
     * Partially update a agence.
     *
     * @param agenceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AgenceDTO> partialUpdate(AgenceDTO agenceDTO) {
        log.debug("Request to partially update Agence : {}", agenceDTO);

        return agenceRepository
            .findById(agenceDTO.getId())
            .map(existingAgence -> {
                agenceMapper.partialUpdate(existingAgence, agenceDTO);

                return existingAgence;
            })
            .map(agenceRepository::save)
            .map(agenceMapper::toDto);
    }

    /**
     * Get all the agences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AgenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Agences");
        return agenceRepository.findAll(pageable).map(agenceMapper::toDto);
    }

    /**
     * Get one agence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AgenceDTO> findOne(Long id) {
        log.debug("Request to get Agence : {}", id);
        return agenceRepository.findById(id).map(agenceMapper::toDto);
    }

    /**
     * Delete the agence by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Agence : {}", id);
        agenceRepository.deleteById(id);
    }

      /**
     * Get one Agence Bancaire by  code.
     *
     * @param code the code of the Agence.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AgenceDTO> findByCode(String code) {
        log.debug("Request to get Direction : {}", code);
        return agenceRepository.findByCode(code).map(agenceMapper::toDto); 
            // .map(applicationsMapper::toDto);
    }
}
