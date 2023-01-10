package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.CategorieActes;
import com.sn.dtai.admin.repository.CategorieActesRepository;
import com.sn.dtai.admin.service.CategorieActesService;
import com.sn.dtai.admin.service.dto.CategorieActesDTO;
import com.sn.dtai.admin.service.mapper.CategorieActesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CategorieActes}.
 */
@Service
@Transactional
public class CategorieActesServiceImpl implements CategorieActesService {

    private final Logger log = LoggerFactory.getLogger(CategorieActesServiceImpl.class);

    private final CategorieActesRepository categorieActesRepository;

    private final CategorieActesMapper categorieActesMapper;

    public CategorieActesServiceImpl(CategorieActesRepository categorieActesRepository, CategorieActesMapper categorieActesMapper) {
        this.categorieActesRepository = categorieActesRepository;
        this.categorieActesMapper = categorieActesMapper;
    }

    @Override
    public CategorieActesDTO save(CategorieActesDTO categorieActesDTO) {
        log.debug("Request to save CategorieActes : {}", categorieActesDTO);
        CategorieActes categorieActes = categorieActesMapper.toEntity(categorieActesDTO);
        categorieActes = categorieActesRepository.save(categorieActes);
        return categorieActesMapper.toDto(categorieActes);
    }

    @Override
    public CategorieActesDTO update(CategorieActesDTO categorieActesDTO) {
        log.debug("Request to save CategorieActes : {}", categorieActesDTO);
        CategorieActes categorieActes = categorieActesMapper.toEntity(categorieActesDTO);
        categorieActes = categorieActesRepository.save(categorieActes);
        return categorieActesMapper.toDto(categorieActes);
    }

    @Override
    public Optional<CategorieActesDTO> partialUpdate(CategorieActesDTO categorieActesDTO) {
        log.debug("Request to partially update CategorieActes : {}", categorieActesDTO);

        return categorieActesRepository
            .findById(categorieActesDTO.getId())
            .map(existingCategorieActes -> {
                categorieActesMapper.partialUpdate(existingCategorieActes, categorieActesDTO);

                return existingCategorieActes;
            })
            .map(categorieActesRepository::save)
            .map(categorieActesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategorieActesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategorieActes");
        return categorieActesRepository.findAll(pageable).map(categorieActesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieActesDTO> findOne(Long id) {
        log.debug("Request to get CategorieActes : {}", id);
        return categorieActesRepository.findById(id).map(categorieActesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategorieActes : {}", id);
        categorieActesRepository.deleteById(id);
    }
}
