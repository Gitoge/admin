package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.*;
import com.sn.dtai.admin.service.dto.ArrondissementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Arrondissement} and its DTO {@link ArrondissementDTO}.
 */
@Mapper(componentModel = "spring", uses = { DepartementMapper.class })
public interface ArrondissementMapper extends EntityMapper<ArrondissementDTO, Arrondissement> {
    @Mapping(target = "departement", source = "departement")
    ArrondissementDTO toDto(Arrondissement s);
}
