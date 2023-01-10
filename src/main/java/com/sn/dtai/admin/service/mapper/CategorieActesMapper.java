package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.CategorieActes;
import com.sn.dtai.admin.service.dto.CategorieActesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieActes} and its DTO {@link CategorieActesDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategorieActesMapper extends EntityMapper<CategorieActesDTO, CategorieActes> {}
