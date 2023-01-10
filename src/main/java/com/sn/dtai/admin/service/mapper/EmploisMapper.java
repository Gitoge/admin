package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Emplois;
import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.service.dto.EmploisDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Emplois} and its DTO {@link EmploisDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmploisMapper extends EntityMapper<EmploisDTO, Emplois> {
    @Mapping(target = "postes", source = "postes", qualifiedByName = "postesIdSet")
    EmploisDTO toDto(Emplois s);

    @Mapping(target = "removePostes", ignore = true)
    Emplois toEntity(EmploisDTO emploisDTO);

    @Named("postesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PostesDTO toDtoPostesId(Postes postes);

    @Named("postesIdSet")
    default Set<PostesDTO> toDtoPostesIdSet(Set<Postes> postes) {
        return postes.stream().map(this::toDtoPostesId).collect(Collectors.toSet());
    }
}
