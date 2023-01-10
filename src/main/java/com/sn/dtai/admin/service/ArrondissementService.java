package com.sn.dtai.admin.service;

import com.sn.dtai.admin.domain.Arrondissement;
import com.sn.dtai.admin.repository.ArrondissementRepository;
import com.sn.dtai.admin.service.dto.ArrondissementDTO;
import com.sn.dtai.admin.service.mapper.ArrondissementMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Arrondissement}.
 */
@Service
@Transactional
public class ArrondissementService {

    private final Logger log = LoggerFactory.getLogger(ArrondissementService.class);

    private final ArrondissementRepository arrondissementRepository;

    private final ArrondissementMapper arrondissementMapper;

    public ArrondissementService(ArrondissementRepository arrondissementRepository, ArrondissementMapper arrondissementMapper) {
        this.arrondissementRepository = arrondissementRepository;
        this.arrondissementMapper = arrondissementMapper;
    }

    /**
     * Save a arrondissement.
     *
     * @param arrondissementDTO the entity to save.
     * @return the persisted entity.
     */
    public ArrondissementDTO save(ArrondissementDTO arrondissementDTO) {
        log.debug("Request to save Arrondissement : {}", arrondissementDTO);
        Arrondissement arrondissement = arrondissementMapper.toEntity(arrondissementDTO);
        arrondissement = arrondissementRepository.save(arrondissement);
        return arrondissementMapper.toDto(arrondissement);
    }

    /**
     * Partially update a arrondissement.
     *
     * @param arrondissementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ArrondissementDTO> partialUpdate(ArrondissementDTO arrondissementDTO) {
        log.debug("Request to partially update Arrondissement : {}", arrondissementDTO);

        return arrondissementRepository
            .findById(arrondissementDTO.getId())
            .map(existingArrondissement -> {
                arrondissementMapper.partialUpdate(existingArrondissement, arrondissementDTO);

                return existingArrondissement;
            })
            .map(arrondissementRepository::save)
            .map(arrondissementMapper::toDto);
    }

    /**
     * Get all the arrondissements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ArrondissementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Arrondissements");
        return arrondissementRepository.findAll(pageable).map(arrondissementMapper::toDto);
    }

    /**
     * Get one arrondissement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ArrondissementDTO> findOne(Long id) {
        log.debug("Request to get Arrondissement : {}", id);
        return arrondissementRepository.findById(id).map(arrondissementMapper::toDto);
    }

    /**
     * Delete the arrondissement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Arrondissement : {}", id);
        arrondissementRepository.deleteById(id);
    }

    /**
     * Get all Arrondissement
     *
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<ArrondissementDTO> findAllArrondissement() {
        return arrondissementRepository.findAllArrondissement().stream().map(arrondissementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
