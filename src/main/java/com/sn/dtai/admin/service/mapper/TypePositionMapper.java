package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.TypePosition;
import com.sn.dtai.admin.service.dto.TypePositionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypePosition} and its DTO {@link TypePositionDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypePositionMapper extends EntityMapper<TypePositionDTO, TypePosition> {}
