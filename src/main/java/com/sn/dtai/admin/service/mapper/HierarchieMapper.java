package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Hierarchie;
import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.service.dto.HierarchieDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hierarchie} and its DTO {@link HierarchieDTO}.
 */
@Mapper(componentModel = "spring")
public interface HierarchieMapper extends EntityMapper<HierarchieDTO, Hierarchie> {
    @Mapping(target = "postes", source = "postes", qualifiedByName = "postesIdSet")
    HierarchieDTO toDto(Hierarchie s);

    @Mapping(target = "removePostes", ignore = true)
    Hierarchie toEntity(HierarchieDTO hierarchieDTO);

    @Named("postesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PostesDTO toDtoPostesId(Postes postes);

    @Named("postesIdSet")
    default Set<PostesDTO> toDtoPostesIdSet(Set<Postes> postes) {
        return postes.stream().map(this::toDtoPostesId).collect(Collectors.toSet());
    }
}
