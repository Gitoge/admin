package com.sn.dtai.admin.repository;

import com.sn.dtai.admin.domain.UserExtended;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UserExtended entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtendedRepository extends JpaRepository<UserExtended, Long> {}
