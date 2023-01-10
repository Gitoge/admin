package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.ParamBaremeImposable;
import com.sn.dtai.admin.service.dto.ParamBaremeImposableDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParamBaremeImposable} and its DTO {@link ParamBaremeImposableDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParamBaremeImposableMapper extends EntityMapper<ParamBaremeImposableDTO, ParamBaremeImposable> {}
