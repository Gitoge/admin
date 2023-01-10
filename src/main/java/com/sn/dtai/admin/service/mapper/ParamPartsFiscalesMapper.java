package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.ParamPartsFiscales;
import com.sn.dtai.admin.service.dto.ParamPartsFiscalesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParamPartsFiscales} and its DTO {@link ParamPartsFiscalesDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParamPartsFiscalesMapper extends EntityMapper<ParamPartsFiscalesDTO, ParamPartsFiscales> {}
