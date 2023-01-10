package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Direction;
import com.sn.dtai.admin.domain.Services;
import com.sn.dtai.admin.service.dto.DirectionDTO;
import com.sn.dtai.admin.service.dto.ServicesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Services} and its DTO {@link ServicesDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServicesMapper extends EntityMapper<ServicesDTO, Services> {
    @Mapping(target = "direction", source = "direction", qualifiedByName = "directionId")
    ServicesDTO toDto(Services s);

    @Named("directionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DirectionDTO toDtoDirectionId(Direction direction);
}
