package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.repository.ActionsRepository;
import com.sn.dtai.admin.service.ActionsService;
import com.sn.dtai.admin.service.dto.ActionsDTO;
import com.sn.dtai.admin.service.mapper.ActionsMapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service Implementation for managing {@link Actions}.
 */
@Service
@Transactional
public class ActionsServiceImpl implements ActionsService {

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger log = LoggerFactory.getLogger(ActionsServiceImpl.class);

    private final ActionsRepository actionsRepository;

    private final ActionsMapper actionsMapper;

    public ActionsServiceImpl(ActionsRepository actionsRepository, ActionsMapper actionsMapper) {
        this.actionsRepository = actionsRepository;
        this.actionsMapper = actionsMapper;
    }

    @Override
    public ActionsDTO save(ActionsDTO actionsDTO) {
        log.debug("Request to save Actions : {}", actionsDTO);
        Actions actions = actionsMapper.toEntity(actionsDTO);
        actions = actionsRepository.save(actions);
        return actionsMapper.toDto(actions);
    }

    @Override
    public ActionsDTO update(ActionsDTO actionsDTO) {
        log.debug("Request to save Actions : {}", actionsDTO);
        Actions actions = actionsMapper.toEntity(actionsDTO);
        actions = actionsRepository.save(actions);
        return actionsMapper.toDto(actions);
    }

    @Override
    public Optional<ActionsDTO> partialUpdate(ActionsDTO actionsDTO) {
        log.debug("Request to partially update Actions : {}", actionsDTO);

        return actionsRepository
            .findById(actionsDTO.getId())
            .map(existingActions -> {
                actionsMapper.partialUpdate(existingActions, actionsDTO);

                return existingActions;
            })
            .map(actionsRepository::save)
            .map(actionsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ActionsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actions");
        return actionsRepository.findAll(pageable).map(actionsMapper::toDto);
    }

    public Page<ActionsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return actionsRepository.findAllWithEagerRelationships(pageable).map(actionsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActionsDTO> findOne(Long id) {
        log.debug("Request to get Actions : {}", id);
        return actionsRepository.findOneWithEagerRelationships(id).map(actionsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Actions : {}", id);
        actionsRepository.deleteById(id);
    }

    // Méthodes Ajoutées

    /**
     * Get one actions by code.
     *
     * @param code the code of the action.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Actions> findByCode(String code) {
        log.debug("Request to get Modules : {}", code);
        return actionsRepository.findByCode(code);
            // .map(actionsMapper::toDto);
    }

    /**
      * Get one actions by Libelle.
      *
      * @param libelle the Libelle of the actions.
      * @return the entity.
      */
     @Transactional(readOnly = true)
     public Optional<Actions> findByLibelle(String libelle) {
         log.debug("Request to get actions : {}", libelle);
         return actionsRepository.findByLibelle(libelle);
            // .map(actionsMapper::toDto);
     }

    @Override
    public Set<Actions> findByPagesId(Long id) {
        return new HashSet<Actions>(entityManager
            .createQuery("SELECT a FROM Actions a "+
                " LEFT JOIN FETCH a.pages p" +
               " WHERE p.id = :page_id " , Actions.class)
            .setParameter("page_id", id)
            .getResultList());
    }

    @Override
     @Transactional(readOnly = true)
     public List<ActionsDTO> findByPages(List<Long> pagesIds) {
         log.debug("Request to get Actions By Pages : {}",pagesIds);
         return actionsRepository.findByPages(pagesIds).stream()
         .map(actionsMapper::toDto)
         .collect(Collectors.toCollection(LinkedList::new));
    }



}

