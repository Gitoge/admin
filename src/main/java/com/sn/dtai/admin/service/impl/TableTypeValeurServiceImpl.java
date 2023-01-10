package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TableTypeValeur;
import com.sn.dtai.admin.repository.TableTypeValeurRepository;
import com.sn.dtai.admin.service.TableTypeValeurService;
import com.sn.dtai.admin.service.dto.TableTypeValeurDTO;
import com.sn.dtai.admin.service.mapper.TableTypeValeurMapper;

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
 * Service Implementation for managing {@link TableTypeValeur}.
 */
@Service
@Transactional
public class TableTypeValeurServiceImpl implements TableTypeValeurService {

    private final Logger log = LoggerFactory.getLogger(TableTypeValeurServiceImpl.class);

    private final TableTypeValeurRepository tableTypeValeurRepository;

    private final TableTypeValeurMapper tableTypeValeurMapper;

    public TableTypeValeurServiceImpl(TableTypeValeurRepository tableTypeValeurRepository, TableTypeValeurMapper tableTypeValeurMapper) {
        this.tableTypeValeurRepository = tableTypeValeurRepository;
        this.tableTypeValeurMapper = tableTypeValeurMapper;
    }

    @Override
    public TableTypeValeurDTO save(TableTypeValeurDTO tableTypeValeurDTO) {
        log.debug("Request to save TableTypeValeur : {}", tableTypeValeurDTO);
        TableTypeValeur tableTypeValeur = tableTypeValeurMapper.toEntity(tableTypeValeurDTO);
        tableTypeValeur = tableTypeValeurRepository.save(tableTypeValeur);
        return tableTypeValeurMapper.toDto(tableTypeValeur);
    }

    @Override
    public TableTypeValeurDTO update(TableTypeValeurDTO tableTypeValeurDTO) {
        log.debug("Request to save TableTypeValeur : {}", tableTypeValeurDTO);
        TableTypeValeur tableTypeValeur = tableTypeValeurMapper.toEntity(tableTypeValeurDTO);
        tableTypeValeur = tableTypeValeurRepository.save(tableTypeValeur);
        return tableTypeValeurMapper.toDto(tableTypeValeur);
    }

    @Override
    public Optional<TableTypeValeurDTO> partialUpdate(TableTypeValeurDTO tableTypeValeurDTO) {
        log.debug("Request to partially update TableTypeValeur : {}", tableTypeValeurDTO);

        return tableTypeValeurRepository
            .findById(tableTypeValeurDTO.getId())
            .map(existingTableTypeValeur -> {
                tableTypeValeurMapper.partialUpdate(existingTableTypeValeur, tableTypeValeurDTO);

                return existingTableTypeValeur;
            })
            .map(tableTypeValeurRepository::save)
            .map(tableTypeValeurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TableTypeValeurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TableTypeValeurs");
        return tableTypeValeurRepository.findAll(pageable).map(tableTypeValeurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TableTypeValeurDTO> findOne(Long id) {
        log.debug("Request to get TableTypeValeur : {}", id);
        return tableTypeValeurRepository.findById(id).map(tableTypeValeurMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableTypeValeur : {}", id);
        tableTypeValeurRepository.deleteById(id);
    }

     /**
     * Get all the tableTypeValeurs.
     *
     * 
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TableTypeValeurDTO> findListTableTypeValeur() {
        log.debug("Request to get all TableTypeValeurs");
        return tableTypeValeurRepository.findAll().stream()
            .map(tableTypeValeurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
