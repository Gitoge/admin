package com.sn.dtai.admin.service.impl;

import com.sn.dtai.admin.domain.UserExtended;
import com.sn.dtai.admin.repository.UserExtendedRepository;
import com.sn.dtai.admin.service.UserExtendedService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserExtended}.
 */
@Service
@Transactional
public class UserExtendedServiceImpl implements UserExtendedService {

    private final Logger log = LoggerFactory.getLogger(UserExtendedServiceImpl.class);

    private final UserExtendedRepository userExtendedRepository;

    public UserExtendedServiceImpl(UserExtendedRepository userExtendedRepository) {
        this.userExtendedRepository = userExtendedRepository;
    }

    @Override
    public UserExtended save(UserExtended userExtended) {
        log.debug("Request to save UserExtended : {}", userExtended);
        return userExtendedRepository.save(userExtended);
    }

    @Override
    public UserExtended update(UserExtended userExtended) {
        log.debug("Request to save UserExtended : {}", userExtended);
        // no save call needed as we have no fields that can be updated
        return userExtended;
    }

    @Override
    public Optional<UserExtended> partialUpdate(UserExtended userExtended) {
        log.debug("Request to partially update UserExtended : {}", userExtended);

        return userExtendedRepository
            .findById(userExtended.getId())
            .map(existingUserExtended -> {
                return existingUserExtended;
            }); // .map(userExtendedRepository::save)
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserExtended> findAll() {
        log.debug("Request to get all UserExtendeds");
        return userExtendedRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserExtended> findOne(Long id) {
        log.debug("Request to get UserExtended : {}", id);
        return userExtendedRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserExtended : {}", id);
        userExtendedRepository.deleteById(id);
    }
}
