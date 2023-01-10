package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.ParamBaremeMinimumFiscal;
import com.sn.dtai.admin.service.dto.ParamBaremeMinimumFiscalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParamBaremeMinimumFiscal} and its DTO {@link ParamBaremeMinimumFiscalDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParamBaremeMinimumFiscalMapper extends EntityMapper<ParamBaremeMinimumFiscalDTO, ParamBaremeMinimumFiscal> {}
