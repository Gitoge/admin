package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.service.dto.PostesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Postes} and its DTO {@link PostesDTO}.
 */
@Mapper(componentModel = "spring")
public interface PostesMapper extends EntityMapper<PostesDTO, Postes> {}
