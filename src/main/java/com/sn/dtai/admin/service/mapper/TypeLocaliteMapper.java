package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.TypeLocalite;
import com.sn.dtai.admin.service.dto.TypeLocaliteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeLocalite} and its DTO {@link TypeLocaliteDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeLocaliteMapper extends EntityMapper<TypeLocaliteDTO, TypeLocalite> {}
