package com.sn.dtai.admin.service;

import com.sn.dtai.admin.domain.PostesReferenceActes;
import com.sn.dtai.admin.repository.PostesReferenceActesRepository;
import com.sn.dtai.admin.service.dto.PostesReferenceActesDTO;
import com.sn.dtai.admin.service.mapper.PostesReferenceActesMapper;
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
 * Service Implementation for managing {@link PostesReferenceActes}.
 */
@Service
@Transactional
public class PostesReferenceActesService {

    private final Logger log = LoggerFactory.getLogger(PostesReferenceActesService.class);

    private final PostesReferenceActesRepository postesReferenceActesRepository;

    private final PostesReferenceActesMapper postesReferenceActesMapper;

    public PostesReferenceActesService(
        PostesReferenceActesRepository postesReferenceActesRepository,
        PostesReferenceActesMapper postesReferenceActesMapper
    ) {
        this.postesReferenceActesRepository = postesReferenceActesRepository;
        this.postesReferenceActesMapper = postesReferenceActesMapper;
    }

    /**
     * Save a postesReferenceActes.
     *
     * @param postesReferenceActesDTO the entity to save.
     * @return the persisted entity.
     */
    public PostesReferenceActesDTO save(PostesReferenceActesDTO postesReferenceActesDTO) {
        log.debug("Request to save PostesReferenceActes : {}", postesReferenceActesDTO);
        PostesReferenceActes postesReferenceActes = postesReferenceActesMapper.toEntity(postesReferenceActesDTO);
        postesReferenceActes = postesReferenceActesRepository.save(postesReferenceActes);
        return postesReferenceActesMapper.toDto(postesReferenceActes);
    }

    /**
     * Partially update a postesReferenceActes.
     *
     * @param postesReferenceActesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PostesReferenceActesDTO> partialUpdate(PostesReferenceActesDTO postesReferenceActesDTO) {
        log.debug("Request to partially update PostesReferenceActes : {}", postesReferenceActesDTO);

        return postesReferenceActesRepository
            .findById(postesReferenceActesDTO.getId())
            .map(existingPostesReferenceActes -> {
                postesReferenceActesMapper.partialUpdate(existingPostesReferenceActes, postesReferenceActesDTO);

                return existingPostesReferenceActes;
            })
            .map(postesReferenceActesRepository::save)
            .map(postesReferenceActesMapper::toDto);
    }

    /**
     * Get all the postesReferenceActes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PostesReferenceActesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PostesReferenceActes");
        return postesReferenceActesRepository.findAll(pageable).map(postesReferenceActesMapper::toDto);
    }

    /**
     * Get one postesReferenceActes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PostesReferenceActesDTO> findOne(Long id) {
        log.debug("Request to get PostesReferenceActes : {}", id);
        return postesReferenceActesRepository.findById(id).map(postesReferenceActesMapper::toDto);
    }

    /**
     * Get one postesReferenceActes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PostesReferenceActesDTO> findByPostesId(Long postesId) {
        log.debug("Request to get PostesReferenceActes : {}", postesId);
        return postesReferenceActesRepository.findByPostesId(postesId).map(postesReferenceActesMapper::toDto);
    }

/**
     * Delete the postesReferenceActes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PostesReferenceActes : {}", id);
        postesReferenceActesRepository.deleteById(id);
    }

    /**
     * Get one postesReferenceActes by code.
     *
     * @param code the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<PostesReferenceActesDTO> findByCodePostes(String code) {
        log.debug("Request to get PostesReferenceActes : {}", code);
        return postesReferenceActesRepository
            .findByCodePostes(code)
            .stream()
            .map(postesReferenceActesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
