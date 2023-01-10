package com.sn.dtai.admin.service;

import com.sn.dtai.admin.domain.PostesNonCumulable;
import com.sn.dtai.admin.repository.PostesNonCumulableRepository;
import com.sn.dtai.admin.service.dto.PostesNonCumulableDTO;
import com.sn.dtai.admin.service.mapper.PostesNonCumulableMapper;

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
 * Service Implementation for managing {@link PostesNonCumulable}.
 */
@Service
@Transactional
public class PostesNonCumulableService {

    private final Logger log = LoggerFactory.getLogger(PostesNonCumulableService.class);

    private final PostesNonCumulableRepository postesNonCumulableRepository;

    private final PostesNonCumulableMapper postesNonCumulableMapper;

    public PostesNonCumulableService(
            PostesNonCumulableRepository postesNonCumulableRepository,
            PostesNonCumulableMapper postesNonCumulableMapper) {
        this.postesNonCumulableRepository = postesNonCumulableRepository;
        this.postesNonCumulableMapper = postesNonCumulableMapper;
    }

    /**
     * Save a postesNonCumulable.
     *
     * @param postesNonCumulableDTO the entity to save.
     * @return the persisted entity.
     */
    public PostesNonCumulableDTO save(PostesNonCumulableDTO postesNonCumulableDTO) {
        log.debug("Request to save PostesNonCumulable : {}", postesNonCumulableDTO);
        PostesNonCumulable postesNonCumulable = postesNonCumulableMapper.toEntity(postesNonCumulableDTO);
        postesNonCumulable = postesNonCumulableRepository.save(postesNonCumulable);
        return postesNonCumulableMapper.toDto(postesNonCumulable);
    }

    /**
     * Partially update a postesNonCumulable.
     *
     * @param postesNonCumulableDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PostesNonCumulableDTO> partialUpdate(PostesNonCumulableDTO postesNonCumulableDTO) {
        log.debug("Request to partially update PostesNonCumulable : {}", postesNonCumulableDTO);

        return postesNonCumulableRepository
                .findById(postesNonCumulableDTO.getId())
                .map(existingPostesNonCumulable -> {
                    postesNonCumulableMapper.partialUpdate(existingPostesNonCumulable, postesNonCumulableDTO);

                    return existingPostesNonCumulable;
                })
                .map(postesNonCumulableRepository::save)
                .map(postesNonCumulableMapper::toDto);
    }

    /**
     * Get all the postesNonCumulables.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PostesNonCumulableDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PostesNonCumulables");
        return postesNonCumulableRepository.findAll(pageable).map(postesNonCumulableMapper::toDto);
    }

    /**
     * Get one postesNonCumulable by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PostesNonCumulableDTO> findOne(Long id) {
        log.debug("Request to get PostesNonCumulable : {}", id);
        return postesNonCumulableRepository.findById(id).map(postesNonCumulableMapper::toDto);
    }

    /**
     * Delete the postesNonCumulable by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PostesNonCumulable : {}", id);
        postesNonCumulableRepository.deleteById(id);
    }

    // GET POSTE NON CUMULABLES
    /**
     * Get one postesNonCumulable by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<PostesNonCumulableDTO> findPosteNonCumulable(String codePoste) {
        log.debug("Request to get PostesNonCumulable : {}", codePoste);
        return postesNonCumulableRepository.findByCodePoste1(codePoste).stream().map(postesNonCumulableMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
    
}
