package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Reglement;
import com.sn.dtai.admin.domain.TypeReglement;
import com.sn.dtai.admin.service.dto.ReglementDTO;
import com.sn.dtai.admin.service.dto.TypeReglementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reglement} and its DTO {@link ReglementDTO}.
 */
@Mapper(componentModel = "spring")
public interface ReglementMapper extends EntityMapper<ReglementDTO, Reglement> {
    @Mapping(target = "typereglement", source = "typereglement", qualifiedByName = "typeReglementId")
    ReglementDTO toDto(Reglement s);

    @Named("typeReglementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeReglementDTO toDtoTypeReglementId(TypeReglement typeReglement);
}
