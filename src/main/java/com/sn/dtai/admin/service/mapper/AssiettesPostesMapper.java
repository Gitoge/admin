package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.AssiettesPostes;
import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.domain.Assiettes;
import com.sn.dtai.admin.service.dto.AssiettesPostesDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import com.sn.dtai.admin.service.dto.AssiettesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AssiettesPostes} and its DTO {@link AssiettesPostesDTO}.
 */
@Mapper(componentModel = "spring")
public interface AssiettesPostesMapper extends EntityMapper<AssiettesPostesDTO, AssiettesPostes> {

    @Mapping(target = "postes", source = "postes")
    @Mapping(target = "assiettes", source = "assiettes")
    AssiettesPostesDTO toDto(AssiettesPostes s);

    @Named("postesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PostesDTO toDtoPostesId(Postes postes);

    @Named("assiettesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AssiettesDTO toDtoAssiettesId(Assiettes assiettes);

    @Named("postesIdSet")
    default Set<PostesDTO> toDtoPostesIdSet(Set<Postes> postes) {
        return postes.stream().map(this::toDtoPostesId).collect(Collectors.toSet());
    }

    @Named("assiettesIdSet")
    default Set<AssiettesDTO> toDtoAssiettesIdSet(Set<Assiettes> assiettes) {
        return assiettes.stream().map(this::toDtoAssiettesId).collect(Collectors.toSet());
    }
}
