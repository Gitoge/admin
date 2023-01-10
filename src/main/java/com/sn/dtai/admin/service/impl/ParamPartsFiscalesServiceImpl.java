package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.ParamPartsFiscales;
import com.sn.dtai.admin.repository.ParamPartsFiscalesRepository;
import com.sn.dtai.admin.service.ParamPartsFiscalesService;
import com.sn.dtai.admin.service.dto.ParamPartsFiscalesDTO;
import com.sn.dtai.admin.service.mapper.ParamPartsFiscalesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ParamPartsFiscales}.
 */
@Service
@Transactional
public class ParamPartsFiscalesServiceImpl implements ParamPartsFiscalesService {

    private final Logger log = LoggerFactory.getLogger(ParamPartsFiscalesServiceImpl.class);

    private final ParamPartsFiscalesRepository paramPartsFiscalesRepository;

    private final ParamPartsFiscalesMapper paramPartsFiscalesMapper;

    public ParamPartsFiscalesServiceImpl(
        ParamPartsFiscalesRepository paramPartsFiscalesRepository,
        ParamPartsFiscalesMapper paramPartsFiscalesMapper
    ) {
        this.paramPartsFiscalesRepository = paramPartsFiscalesRepository;
        this.paramPartsFiscalesMapper = paramPartsFiscalesMapper;
    }

    @Override
    public ParamPartsFiscalesDTO save(ParamPartsFiscalesDTO paramPartsFiscalesDTO) {
        log.debug("Request to save ParamPartsFiscales : {}", paramPartsFiscalesDTO);
        ParamPartsFiscales paramPartsFiscales = paramPartsFiscalesMapper.toEntity(paramPartsFiscalesDTO);
        paramPartsFiscales = paramPartsFiscalesRepository.save(paramPartsFiscales);
        return paramPartsFiscalesMapper.toDto(paramPartsFiscales);
    }

    @Override
    public ParamPartsFiscalesDTO update(ParamPartsFiscalesDTO paramPartsFiscalesDTO) {
        log.debug("Request to save ParamPartsFiscales : {}", paramPartsFiscalesDTO);
        ParamPartsFiscales paramPartsFiscales = paramPartsFiscalesMapper.toEntity(paramPartsFiscalesDTO);
        paramPartsFiscales = paramPartsFiscalesRepository.save(paramPartsFiscales);
        return paramPartsFiscalesMapper.toDto(paramPartsFiscales);
    }

    @Override
    public Optional<ParamPartsFiscalesDTO> partialUpdate(ParamPartsFiscalesDTO paramPartsFiscalesDTO) {
        log.debug("Request to partially update ParamPartsFiscales : {}", paramPartsFiscalesDTO);

        return paramPartsFiscalesRepository
            .findById(paramPartsFiscalesDTO.getId())
            .map(existingParamPartsFiscales -> {
                paramPartsFiscalesMapper.partialUpdate(existingParamPartsFiscales, paramPartsFiscalesDTO);

                return existingParamPartsFiscales;
            })
            .map(paramPartsFiscalesRepository::save)
            .map(paramPartsFiscalesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParamPartsFiscalesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParamPartsFiscales");
        return paramPartsFiscalesRepository.findAll(pageable).map(paramPartsFiscalesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParamPartsFiscalesDTO> findOne(Long id) {
        log.debug("Request to get ParamPartsFiscales : {}", id);
        return paramPartsFiscalesRepository.findById(id).map(paramPartsFiscalesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParamPartsFiscales : {}", id);
        paramPartsFiscalesRepository.deleteById(id);
    }

    
    /**
     * Get one Services by  code.
     *
     * @param code the code of the Services.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ParamPartsFiscalesDTO> findByCode(String code) {
        log.debug("Request to get ParamPartsFiscales : {}", code);
        return paramPartsFiscalesRepository.findByCode( code).map(paramPartsFiscalesMapper::toDto);
       
    }
 
}
