package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.HierarchieCategorie;
import com.sn.dtai.admin.repository.HierarchieCategorieRepository;
import com.sn.dtai.admin.service.HierarchieCategorieService;
import com.sn.dtai.admin.service.dto.HierarchieCategorieDTO;
import com.sn.dtai.admin.service.mapper.HierarchieCategorieMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HierarchieCategorie}.
 */
@Service
@Transactional
public class HierarchieCategorieServiceImpl implements HierarchieCategorieService {

    private final Logger log = LoggerFactory.getLogger(HierarchieCategorieServiceImpl.class);

    private final HierarchieCategorieRepository hierarchieCategorieRepository;

    private final HierarchieCategorieMapper hierarchieCategorieMapper;

    public HierarchieCategorieServiceImpl(
        HierarchieCategorieRepository hierarchieCategorieRepository,
        HierarchieCategorieMapper hierarchieCategorieMapper
    ) {
        this.hierarchieCategorieRepository = hierarchieCategorieRepository;
        this.hierarchieCategorieMapper = hierarchieCategorieMapper;
    }

    @Override
    public HierarchieCategorieDTO save(HierarchieCategorieDTO hierarchieCategorieDTO) {
        log.debug("Request to save HierarchieCategorie : {}", hierarchieCategorieDTO);
        HierarchieCategorie hierarchieCategorie = hierarchieCategorieMapper.toEntity(hierarchieCategorieDTO);
        hierarchieCategorie = hierarchieCategorieRepository.save(hierarchieCategorie);
        return hierarchieCategorieMapper.toDto(hierarchieCategorie);
    }

    @Override
    public HierarchieCategorieDTO update(HierarchieCategorieDTO hierarchieCategorieDTO) {
        log.debug("Request to save HierarchieCategorie : {}", hierarchieCategorieDTO);
        HierarchieCategorie hierarchieCategorie = hierarchieCategorieMapper.toEntity(hierarchieCategorieDTO);
        hierarchieCategorie = hierarchieCategorieRepository.save(hierarchieCategorie);
        return hierarchieCategorieMapper.toDto(hierarchieCategorie);
    }

    @Override
    public Optional<HierarchieCategorieDTO> partialUpdate(HierarchieCategorieDTO hierarchieCategorieDTO) {
        log.debug("Request to partially update HierarchieCategorie : {}", hierarchieCategorieDTO);

        return hierarchieCategorieRepository
            .findById(hierarchieCategorieDTO.getId())
            .map(existingHierarchieCategorie -> {
                hierarchieCategorieMapper.partialUpdate(existingHierarchieCategorie, hierarchieCategorieDTO);

                return existingHierarchieCategorie;
            })
            .map(hierarchieCategorieRepository::save)
            .map(hierarchieCategorieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HierarchieCategorieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HierarchieCategories");
        return hierarchieCategorieRepository.findAll(pageable).map(hierarchieCategorieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HierarchieCategorieDTO> findOne(Long id) {
        log.debug("Request to get HierarchieCategorie : {}", id);
        return hierarchieCategorieRepository.findById(id).map(hierarchieCategorieMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HierarchieCategorie : {}", id);
        hierarchieCategorieRepository.deleteById(id);
    }

    @Override
    public void deleteAllByPage(Long hierarchieId) {
        // TODO Auto-generated method stub

    }

    @Override
    public Page<HierarchieCategorieDTO> findAllHierarchieCategorie(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<HierarchieCategorieDTO> findHierarchieByCategorie(Long categorieId) {
        return hierarchieCategorieRepository.findHierarchieByCategorie(categorieId).map(hierarchieCategorieMapper::toDto);
    }
}
