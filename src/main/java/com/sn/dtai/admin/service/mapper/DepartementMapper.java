package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.*;
import com.sn.dtai.admin.service.dto.DepartementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Departement} and its DTO {@link DepartementDTO}.
 */
@Mapper(componentModel = "spring", uses = { RegionMapper.class })
public interface DepartementMapper extends EntityMapper<DepartementDTO, Departement> {
    @Mapping(target = "region", source = "region")
    DepartementDTO toDto(Departement s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartementDTO toDtoId(Departement departement);
}
