package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.TableValeur;
import com.sn.dtai.admin.repository.TableValeurRepository;
import com.sn.dtai.admin.service.TableValeurService;
import com.sn.dtai.admin.service.dto.TableValeurDTO;
import com.sn.dtai.admin.service.mapper.TableValeurMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TableValeur}.
 */
@Service
@Transactional
public class TableValeurServiceImpl implements TableValeurService {

    private final Logger log = LoggerFactory.getLogger(TableValeurServiceImpl.class);

    private final TableValeurRepository tableValeurRepository;

    private final TableValeurMapper tableValeurMapper;

    public TableValeurServiceImpl(TableValeurRepository tableValeurRepository, TableValeurMapper tableValeurMapper) {
        this.tableValeurRepository = tableValeurRepository;
        this.tableValeurMapper = tableValeurMapper;
    }

    @Override
    public TableValeurDTO save(TableValeurDTO tableValeurDTO) {
        log.debug("Request to save TableValeur : {}", tableValeurDTO);
        TableValeur tableValeur = tableValeurMapper.toEntity(tableValeurDTO);
        tableValeur = tableValeurRepository.save(tableValeur);
        return tableValeurMapper.toDto(tableValeur);
    }

    @Override
    public TableValeurDTO update(TableValeurDTO tableValeurDTO) {
        log.debug("Request to save TableValeur : {}", tableValeurDTO);
        TableValeur tableValeur = tableValeurMapper.toEntity(tableValeurDTO);
        tableValeur = tableValeurRepository.save(tableValeur);
        return tableValeurMapper.toDto(tableValeur);
    }

    @Override
    public Optional<TableValeurDTO> partialUpdate(TableValeurDTO tableValeurDTO) {
        log.debug("Request to partially update TableValeur : {}", tableValeurDTO);

        return tableValeurRepository
            .findById(tableValeurDTO.getId())
            .map(existingTableValeur -> {
                tableValeurMapper.partialUpdate(existingTableValeur, tableValeurDTO);

                return existingTableValeur;
            })
            .map(tableValeurRepository::save)
            .map(tableValeurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TableValeurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TableValeurs");
        return tableValeurRepository.findAll(pageable).map(tableValeurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TableValeurDTO> findOne(Long id) {
        log.debug("Request to get TableValeur : {}", id);
        return tableValeurRepository.findById(id).map(tableValeurMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableValeur : {}", id);
        tableValeurRepository.deleteById(id);
    }
}
