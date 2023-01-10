package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.CategorieActes;
import com.sn.dtai.admin.domain.TypeActes;
import com.sn.dtai.admin.service.dto.CategorieActesDTO;
import com.sn.dtai.admin.service.dto.TypeActesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeActes} and its DTO {@link TypeActesDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeActesMapper extends EntityMapper<TypeActesDTO, TypeActes> {
    @Mapping(target = "categorieactes", source = "categorieactes", qualifiedByName = "categorieActesId")
    TypeActesDTO toDto(TypeActes s);

    @Named("categorieActesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategorieActesDTO toDtoCategorieActesId(CategorieActes categorieActes);
}
