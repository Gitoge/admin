package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Corps;
import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.service.dto.CorpsDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Corps} and its DTO {@link CorpsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CorpsMapper extends EntityMapper<CorpsDTO, Corps> {
    @Mapping(target = "postes", source = "postes", qualifiedByName = "postesIdSet")
    CorpsDTO toDto(Corps s);

    @Mapping(target = "removePostes", ignore = true)
    Corps toEntity(CorpsDTO corpsDTO);

    @Named("postesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PostesDTO toDtoPostesId(Postes postes);

    @Named("postesIdSet")
    default Set<PostesDTO> toDtoPostesIdSet(Set<Postes> postes) {
        return postes.stream().map(this::toDtoPostesId).collect(Collectors.toSet());
    }
}
