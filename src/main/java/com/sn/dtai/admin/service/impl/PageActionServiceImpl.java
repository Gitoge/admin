package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.PageAction;
import com.sn.dtai.admin.repository.PageActionRepository;
import com.sn.dtai.admin.service.PageActionService;
import com.sn.dtai.admin.service.dto.PageActionDTO;
import com.sn.dtai.admin.service.dto.PagesActionsDto;
import com.sn.dtai.admin.service.mapper.PageActionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PageAction}.
 */
@Service
@Transactional
public class PageActionServiceImpl implements PageActionService {

    private final Logger log = LoggerFactory.getLogger(PageActionServiceImpl.class);

    private final PageActionRepository pageActionRepository;

    private final PageActionMapper pageActionMapper;

    public PageActionServiceImpl(PageActionRepository pageActionRepository, PageActionMapper pageActionMapper) {
        this.pageActionRepository = pageActionRepository;
        this.pageActionMapper = pageActionMapper;
    }

    @Override
    public PageActionDTO save(PageActionDTO pageActionDTO) {
        log.debug("Request to save PageAction : {}", pageActionDTO);
        PageAction pageAction = pageActionMapper.toEntity(pageActionDTO);
        pageAction = pageActionRepository.save(pageAction);
        return pageActionMapper.toDto(pageAction);
    }

    @Override
    public PageActionDTO update(PageActionDTO pageActionDTO) {
        log.debug("Request to save PageAction : {}", pageActionDTO);
        PageAction pageAction = pageActionMapper.toEntity(pageActionDTO);
        pageAction = pageActionRepository.save(pageAction);
        return pageActionMapper.toDto(pageAction);
    }

    @Override
    public Optional<PageActionDTO> partialUpdate(PageActionDTO pageActionDTO) {
        log.debug("Request to partially update PageAction : {}", pageActionDTO);

        return pageActionRepository
            .findById(pageActionDTO.getId())
            .map(existingPageAction -> {
                pageActionMapper.partialUpdate(existingPageAction, pageActionDTO);

                return existingPageAction;
            })
            .map(pageActionRepository::save)
            .map(pageActionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PageActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PageActions");
        return pageActionRepository.findAll(pageable).map(pageActionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PageActionDTO> findOne(Long id) {
        log.debug("Request to get PageAction : {}", id);
        return pageActionRepository.findById(id).map(pageActionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PageAction : {}", id);
        pageActionRepository.deleteById(id);
    }

    @Override
    public void deleteAllByPage(Long pageId) {
        log.debug("Request to delete All By Page : {}", pageId);
        pageActionRepository.deleteAllByPage(pageId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PagesActionsDto> findAllPageAction(Pageable pageable) {
        log.debug("Request to get all PageActions");
        return pageActionRepository.findAllPageAction(pageable);
    }
}
