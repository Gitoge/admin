package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Grade;
import com.sn.dtai.admin.domain.Postes;
import com.sn.dtai.admin.service.dto.GradeDTO;
import com.sn.dtai.admin.service.dto.PostesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Grade} and its DTO {@link GradeDTO}.
 */
@Mapper(componentModel = "spring")
public interface GradeMapper extends EntityMapper<GradeDTO, Grade> {
    @Mapping(target = "postes", source = "postes", qualifiedByName = "postesIdSet")
    GradeDTO toDto(Grade s);

    @Mapping(target = "removePostes", ignore = true)
    Grade toEntity(GradeDTO gradeDTO);

    @Named("postesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PostesDTO toDtoPostesId(Postes postes);

    @Named("postesIdSet")
    default Set<PostesDTO> toDtoPostesIdSet(Set<Postes> postes) {
        return postes.stream().map(this::toDtoPostesId).collect(Collectors.toSet());
    }
}
