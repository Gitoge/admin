package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.Personne;
import com.sn.dtai.admin.repository.PersonneRepository;
import com.sn.dtai.admin.service.PersonneService;
import com.sn.dtai.admin.service.dto.PersonneDTO;
import com.sn.dtai.admin.service.mapper.PersonneMapper;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Personne}.
 */
@Service
@Transactional
public class PersonneServiceImpl implements PersonneService {

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger log = LoggerFactory.getLogger(PersonneServiceImpl.class);

    private final PersonneRepository personneRepository;

    private final PersonneMapper personneMapper;

    public PersonneServiceImpl(PersonneRepository personneRepository, PersonneMapper personneMapper) {
        this.personneRepository = personneRepository;
        this.personneMapper = personneMapper;
    }

    @Override
    public PersonneDTO save(PersonneDTO personneDTO) {
        log.debug("Request to save Personne : {}", personneDTO);
        Personne personne = personneMapper.toEntity(personneDTO);
        personne = personneRepository.save(personne);
        return personneMapper.toDto(personne);
    }

    @Override
    public PersonneDTO update(PersonneDTO personneDTO) {
        log.debug("Request to save Personne : {}", personneDTO);
        Personne personne = personneMapper.toEntity(personneDTO);
        personne = personneRepository.save(personne);
        return personneMapper.toDto(personne);
    }

    @Override
    public Optional<PersonneDTO> partialUpdate(PersonneDTO personneDTO) {
        log.debug("Request to partially update Personne : {}", personneDTO);

        return personneRepository
            .findById(personneDTO.getId())
            .map(existingPersonne -> {
                personneMapper.partialUpdate(existingPersonne, personneDTO);

                return existingPersonne;
            })
            .map(personneRepository::save)
            .map(personneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Personnes");
        return personneRepository.findAll(pageable).map(personneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonneDTO> findOne(Long id) {
        log.debug("Request to get Personne : {}", id);
        return personneRepository.findById(id).map(personneMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Personne : {}", id);
        personneRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Personne> findPersonneByLogin(String login) {
        log.debug("Request to get Personne : {}", login);
        return personneRepository.findPersonneByLogin(login);
    }

    @Override
    public Personne findPersonneProfilsModulesPagesActionsByJhiUser(Long id) {
        return entityManager
            .createQuery(
                "SELECT p FROM Personne p " +
                " LEFT JOIN FETCH p.profils profil" +
                " LEFT JOIN FETCH profil.modules " +
                " LEFT JOIN FETCH profil.roles " +
                " WHERE p.jhiUserId = :user_id " +
                " ORDER BY ordre ASC ",
                Personne.class
            )
            .setParameter("user_id", id)
            .getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonneDTO> getInfosPersonneByJhiUser(Long userId) {
        log.debug("Request to get Personne : {}", userId);
        return personneRepository.getInfosPersonneByJhiUser(userId).map(personneMapper::toDto);
    }
}
