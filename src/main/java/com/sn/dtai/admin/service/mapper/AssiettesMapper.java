package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Assiettes;
import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.service.dto.AssiettesDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Assiettes} and its DTO {@link AssiettesDTO}.
 */
@Mapper(componentModel = "spring")
public interface AssiettesMapper extends EntityMapper<AssiettesDTO, Assiettes> {
    @Mapping(target = "postes", source = "postes", qualifiedByName = "postesIdSet")
    AssiettesDTO toDto(Assiettes s);

    @Mapping(target = "removePostes", ignore = true)
    Assiettes toEntity(AssiettesDTO assiettesDTO);

    @Named("postesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PostesDTO toDtoPostesId(Postes postes);

    @Named("postesIdSet")
    default Set<PostesDTO> toDtoPostesIdSet(Set<Postes> postes) {
        return postes.stream().map(this::toDtoPostesId).collect(Collectors.toSet());
    }
}
