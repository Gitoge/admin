package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Direction;
import com.sn.dtai.admin.domain.Etablissement;
import com.sn.dtai.admin.service.dto.DirectionDTO;
import com.sn.dtai.admin.service.dto.EtablissementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Direction} and its DTO {@link DirectionDTO}.
 */
@Mapper(componentModel = "spring")
public interface DirectionMapper extends EntityMapper<DirectionDTO, Direction> {
    @Mapping(target = "etab", source = "etab", qualifiedByName = "etablissementId")
    DirectionDTO toDto(Direction s);

    @Named("etablissementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EtablissementDTO toDtoEtablissementId(Etablissement etablissement);
}
