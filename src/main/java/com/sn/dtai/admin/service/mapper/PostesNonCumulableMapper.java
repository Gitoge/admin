package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.*;
import com.sn.dtai.admin.service.dto.PostesNonCumulableDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PostesNonCumulable} and its DTO {@link PostesNonCumulableDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PostesNonCumulableMapper extends EntityMapper<PostesNonCumulableDTO, PostesNonCumulable> {}
