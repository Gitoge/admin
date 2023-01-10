package com.sn.dtai.admin.service;

import com.sn.dtai.admin.service.dto.PersonneDTO;
import com.sn.dtai.admin.domain.Personne;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sn.dtai.admin.domain.Personne}.
 */
public interface PersonneService {
    /**
     * Save a personne.
     *
     * @param personneDTO the entity to save.
     * @return the persisted entity.
     */
    PersonneDTO save(PersonneDTO personneDTO);

    /**
     * Updates a personne.
     *
     * @param personneDTO the entity to update.
     * @return the persisted entity.
     */
    PersonneDTO update(PersonneDTO personneDTO);

    /**
     * Partially updates a personne.
     *
     * @param personneDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PersonneDTO> partialUpdate(PersonneDTO personneDTO);

    /**
     * Get all the personnes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PersonneDTO> findAll(Pageable pageable);

    /**
     * Get the "id" personne.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonneDTO> findOne(Long id);

    /**
     * Delete the "id" personne.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "login" personne.
     *
     * @param login the id of the entity.
     * @return the entity.
     */
    Optional<Personne> findPersonneByLogin(String login);

    Personne findPersonneProfilsModulesPagesActionsByJhiUser(Long id);

    Optional<PersonneDTO> getInfosPersonneByJhiUser(Long userId);
}
