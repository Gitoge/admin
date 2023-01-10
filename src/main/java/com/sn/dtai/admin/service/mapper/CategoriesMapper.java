package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Categories;
import com.sn.dtai.admin.service.dto.CategoriesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categories} and its DTO {@link CategoriesDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoriesMapper extends EntityMapper<CategoriesDTO, Categories> {}
