package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Pages;
import com.sn.dtai.admin.repository.PagesRepository;
import com.sn.dtai.admin.service.PagesService;
import com.sn.dtai.admin.service.dto.PagesDTO;
import com.sn.dtai.admin.service.mapper.PagesMapper;

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
 * Service Implementation for managing {@link Pages}.
 */
@Service
@Transactional
public class PagesServiceImpl implements PagesService {

    private final Logger log = LoggerFactory.getLogger(PagesServiceImpl.class);

    private final PagesRepository pagesRepository;

    private final PagesMapper pagesMapper;

    public PagesServiceImpl(PagesRepository pagesRepository, PagesMapper pagesMapper) {
        this.pagesRepository = pagesRepository;
        this.pagesMapper = pagesMapper;
    }

    @Override
    public PagesDTO save(PagesDTO pagesDTO) {
        log.debug("Request to save Pages : {}", pagesDTO);
        Pages pages = pagesMapper.toEntity(pagesDTO);
        pages = pagesRepository.save(pages);
        return pagesMapper.toDto(pages);
    }

    @Override
    public PagesDTO update(PagesDTO pagesDTO) {
        log.debug("Request to save Pages : {}", pagesDTO);
        Pages pages = pagesMapper.toEntity(pagesDTO);
        pages = pagesRepository.save(pages);
        return pagesMapper.toDto(pages);
    }

    @Override
    public Optional<PagesDTO> partialUpdate(PagesDTO pagesDTO) {
        log.debug("Request to partially update Pages : {}", pagesDTO);

        return pagesRepository
            .findById(pagesDTO.getId())
            .map(existingPages -> {
                pagesMapper.partialUpdate(existingPages, pagesDTO);

                return existingPages;
            })
            .map(pagesRepository::save)
            .map(pagesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PagesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pages");
        return pagesRepository.findAll(pageable).map(pagesMapper::toDto);
    }

    public Page<PagesDTO> findAllWithEagerRelationships(Pageable pageable) {
        return pagesRepository.findAllWithEagerRelationships(pageable).map(pagesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PagesDTO> findOne(Long id) {
        log.debug("Request to get Pages : {}", id);
        return pagesRepository.findOneWithEagerRelationships(id).map(pagesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pages : {}", id);
        pagesRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one pages by code.
     *
     * @param code the code of the page.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Pages> findByCode(String code) {
        log.debug("Request to get Pages : {}", code);
        return pagesRepository.findByCode(code);    
            // .map(pagesMapper::toDto);
    }
 
    /**
      * Get one pages by Libelle.
      *
      * @param libelle the Libelle of the pages.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Pages> findByLibelle(String libelle) {
         log.debug("Request to get pages : {}", libelle);
         return pagesRepository.findByLibelle(libelle);
            // .map(pagesMapper::toDto);
     }

     @Override
     @Transactional(readOnly = true)
     public List<PagesDTO> findByRoles(List<Long> rolesIds) {
         log.debug("Request to get Roles By Profil : {}",rolesIds);
         return pagesRepository.findByRoles(rolesIds).stream()
         .map(pagesMapper::toDto)
         .collect(Collectors.toCollection(LinkedList::new));
     }
}
