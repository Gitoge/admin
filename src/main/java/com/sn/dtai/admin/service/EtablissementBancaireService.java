package com.sn.dtai.admin.service;

import com.sn.dtai.admin.domain.EtablissementBancaire;
import com.sn.dtai.admin.repository.EtablissementBancaireRepository;
import com.sn.dtai.admin.service.dto.EtablissementBancaireDTO;
import com.sn.dtai.admin.service.mapper.EtablissementBancaireMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EtablissementBancaire}.
 */
@Service
@Transactional
public class EtablissementBancaireService {

    private final Logger log = LoggerFactory.getLogger(EtablissementBancaireService.class);

    private final EtablissementBancaireRepository etablissementBancaireRepository;

    private final EtablissementBancaireMapper etablissementBancaireMapper;

    public EtablissementBancaireService(
        EtablissementBancaireRepository etablissementBancaireRepository,
        EtablissementBancaireMapper etablissementBancaireMapper
    ) {
        this.etablissementBancaireRepository = etablissementBancaireRepository;
        this.etablissementBancaireMapper = etablissementBancaireMapper;
    }

    /**
     * Save a etablissementBancaire.
     *
     * @param etablissementBancaireDTO the entity to save.
     * @return the persisted entity.
     */
    public EtablissementBancaireDTO save(EtablissementBancaireDTO etablissementBancaireDTO) {
        log.debug("Request to save EtablissementBancaire : {}", etablissementBancaireDTO);
        EtablissementBancaire etablissementBancaire = etablissementBancaireMapper.toEntity(etablissementBancaireDTO);
        etablissementBancaire = etablissementBancaireRepository.save(etablissementBancaire);
        return etablissementBancaireMapper.toDto(etablissementBancaire);
    }

    /**
     * Partially update a etablissementBancaire.
     *
     * @param etablissementBancaireDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EtablissementBancaireDTO> partialUpdate(EtablissementBancaireDTO etablissementBancaireDTO) {
        log.debug("Request to partially update EtablissementBancaire : {}", etablissementBancaireDTO);

        return etablissementBancaireRepository
            .findById(etablissementBancaireDTO.getId())
            .map(existingEtablissementBancaire -> {
                etablissementBancaireMapper.partialUpdate(existingEtablissementBancaire, etablissementBancaireDTO);

                return existingEtablissementBancaire;
            })
            .map(etablissementBancaireRepository::save)
            .map(etablissementBancaireMapper::toDto);
    }

    /**
     * Get all the etablissementBancaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EtablissementBancaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtablissementBancaires");
        return etablissementBancaireRepository.findAll(pageable).map(etablissementBancaireMapper::toDto);
    }

    /**
     * Get one etablissementBancaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtablissementBancaireDTO> findOne(Long id) {
        log.debug("Request to get EtablissementBancaire : {}", id);
        return etablissementBancaireRepository.findById(id).map(etablissementBancaireMapper::toDto);
    }

    /**
     * Delete the etablissementBancaire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EtablissementBancaire : {}", id);
        etablissementBancaireRepository.deleteById(id);
    }

    /**
     * Get one Etablissement Bancaire by  code.
     *
     * @param code the code of the Etablissement.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtablissementBancaireDTO> findByCode(String code) {
        log.debug("Request to get Direction : {}", code);
        return etablissementBancaireRepository.findByCode(code).map(etablissementBancaireMapper::toDto); 
            // .map(applicationsMapper::toDto);
    }
}
