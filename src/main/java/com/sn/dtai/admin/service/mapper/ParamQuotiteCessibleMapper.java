package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.ParamQuotiteCessible;
import com.sn.dtai.admin.service.dto.ParamQuotiteCessibleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParamQuotiteCessible} and its DTO {@link ParamQuotiteCessibleDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParamQuotiteCessibleMapper extends EntityMapper<ParamQuotiteCessibleDTO, ParamQuotiteCessible> {}
