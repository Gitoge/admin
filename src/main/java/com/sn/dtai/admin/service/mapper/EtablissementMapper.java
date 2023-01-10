package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Etablissement;
import com.sn.dtai.admin.domain.Localite;
import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.domain.TypeEtablissement;
import com.sn.dtai.admin.service.dto.EtablissementDTO;
import com.sn.dtai.admin.service.dto.LocaliteDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import com.sn.dtai.admin.service.dto.TypeEtablissementDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Etablissement} and its DTO {@link EtablissementDTO}.
 */
@Mapper(componentModel = "spring")
public interface EtablissementMapper extends EntityMapper<EtablissementDTO, Etablissement> {
    @Mapping(target = "typeEtab", source = "typeEtab", qualifiedByName = "typeEtablissementId")
    @Mapping(target = "localite", source = "localite", qualifiedByName = "localiteId")
    @Mapping(target = "postes", source = "postes")
    EtablissementDTO toDto(Etablissement s);

    @Named("typeEtablissementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeEtablissementDTO toDtoTypeEtablissementId(TypeEtablissement typeEtablissement);

    @Named("localiteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LocaliteDTO toDtoLocaliteId(Localite localite);


    @Mapping(target = "removePostes", ignore = true)
    Etablissement toEntity(EtablissementDTO etablissementDTO);

    @Named("postesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PostesDTO toDtoPostesId(Postes postes);

    @Named("postesIdSet")
    default Set<PostesDTO> toDtoPostesIdSet(Set<Postes> postes) {
        return postes.stream().map(this::toDtoPostesId).collect(Collectors.toSet());
    }
}
