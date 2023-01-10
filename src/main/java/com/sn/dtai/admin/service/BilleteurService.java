package com.sn.dtai.admin.service;

import com.sn.dtai.admin.domain.Billeteur;
import com.sn.dtai.admin.repository.BilleteurRepository;
import com.sn.dtai.admin.service.dto.BilleteurDTO;
import com.sn.dtai.admin.service.mapper.BilleteurMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Billeteur}.
 */
@Service
@Transactional
public class BilleteurService {

    private final Logger log = LoggerFactory.getLogger(BilleteurService.class);

    private final BilleteurRepository billeteurRepository;

    private final BilleteurMapper billeteurMapper;

    public BilleteurService(BilleteurRepository billeteurRepository, BilleteurMapper billeteurMapper) {
        this.billeteurRepository = billeteurRepository;
        this.billeteurMapper = billeteurMapper;
    }

    /**
     * Save a billeteur.
     *
     * @param billeteurDTO the entity to save.
     * @return the persisted entity.
     */
    public BilleteurDTO save(BilleteurDTO billeteurDTO) {
        log.debug("Request to save Billeteur : {}", billeteurDTO);
        Billeteur billeteur = billeteurMapper.toEntity(billeteurDTO);
        billeteur = billeteurRepository.save(billeteur);
        return billeteurMapper.toDto(billeteur);
    }

    /**
     * Partially update a billeteur.
     *
     * @param billeteurDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BilleteurDTO> partialUpdate(BilleteurDTO billeteurDTO) {
        log.debug("Request to partially update Billeteur : {}", billeteurDTO);

        return billeteurRepository
            .findById(billeteurDTO.getId())
            .map(existingBilleteur -> {
                billeteurMapper.partialUpdate(existingBilleteur, billeteurDTO);

                return existingBilleteur;
            })
            .map(billeteurRepository::save)
            .map(billeteurMapper::toDto);
    }

    /**
     * Get all the billeteurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BilleteurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Billeteurs");
        return billeteurRepository.findAll(pageable).map(billeteurMapper::toDto);
    }

    /**
     * Get one billeteur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BilleteurDTO> findOne(Long id) {
        log.debug("Request to get Billeteur : {}", id);
        return billeteurRepository.findById(id).map(billeteurMapper::toDto);
    }

    /**
     * Delete the billeteur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Billeteur : {}", id);
        billeteurRepository.deleteById(id);
    }

        /**
     * Get one Etablissement Bancaire by  code.
     *
     * @param code the code of the Etablissement.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BilleteurDTO> findByCode(String code) {
        log.debug("Request to get Direction : {}", code);
        return billeteurRepository.findByCode(code).map(billeteurMapper::toDto); 
            // .map(applicationsMapper::toDto);
    }
}
