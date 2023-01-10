package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Grade;
import com.sn.dtai.admin.repository.GradeRepository;
import com.sn.dtai.admin.service.GradeService;
import com.sn.dtai.admin.service.dto.GradeDTO;
import com.sn.dtai.admin.service.mapper.GradeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Grade}.
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    private final Logger log = LoggerFactory.getLogger(GradeServiceImpl.class);

    private final GradeRepository gradeRepository;

    private final GradeMapper gradeMapper;

    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public GradeDTO save(GradeDTO gradeDTO) {
        log.debug("Request to save Grade : {}", gradeDTO);
        Grade grade = gradeMapper.toEntity(gradeDTO);
        grade = gradeRepository.save(grade);
        return gradeMapper.toDto(grade);
    }

    @Override
    public GradeDTO update(GradeDTO gradeDTO) {
        log.debug("Request to save Grade : {}", gradeDTO);
        Grade grade = gradeMapper.toEntity(gradeDTO);
        grade = gradeRepository.save(grade);
        return gradeMapper.toDto(grade);
    }

    @Override
    public Optional<GradeDTO> partialUpdate(GradeDTO gradeDTO) {
        log.debug("Request to partially update Grade : {}", gradeDTO);

        return gradeRepository
            .findById(gradeDTO.getId())
            .map(existingGrade -> {
                gradeMapper.partialUpdate(existingGrade, gradeDTO);

                return existingGrade;
            })
            .map(gradeRepository::save)
            .map(gradeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Grades");
        return gradeRepository.findAll(pageable).map(gradeMapper::toDto);
    }

    public Page<GradeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return gradeRepository.findAllWithEagerRelationships(pageable).map(gradeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GradeDTO> findOne(Long id) {
        log.debug("Request to get Grade : {}", id);
        return gradeRepository.findOneWithEagerRelationships(id).map(gradeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Grade : {}", id);
        gradeRepository.deleteById(id);
    }

     // Méthodes Ajoutées

    /**
     * Get one Grade by  code.
     *
     * @param code the code of the Services.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Grade> findByCode(String code) {
        log.debug("Request to get Grade : {}", code);
        return gradeRepository.findByCode( code);

    }

    /**
      * Get one Grade by Libelle.
      *
      * @param Libelle the Libelle of the grade.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Grade> findByLibelle(String libelle) {
         log.debug("Request to get Grade : {}", libelle);
         return gradeRepository.findByLibelle( libelle);
     }

    @Transactional(readOnly = true)
    public Page<Grade> findGrades(String motCle,Pageable pageable) {
        log.debug("Request to get Grade by motCle : {}", motCle);
        return gradeRepository.rechercheGrades(motCle,pageable);
    }
}
