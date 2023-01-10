package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Positions;
import com.sn.dtai.admin.domain.TypePosition;
import com.sn.dtai.admin.service.dto.PositionsDTO;
import com.sn.dtai.admin.service.dto.TypePositionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Positions} and its DTO {@link PositionsDTO}.
 */
@Mapper(componentModel = "spring")
public interface PositionsMapper extends EntityMapper<PositionsDTO, Positions> {
    @Mapping(target = "typeposition", source = "typeposition", qualifiedByName = "typePositionId")
    PositionsDTO toDto(Positions s);

    @Named("typePositionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypePositionDTO toDtoTypePositionId(TypePosition typePosition);
}
