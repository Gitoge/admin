package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Cadre;
import com.sn.dtai.admin.service.dto.CadreDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cadre} and its DTO {@link CadreDTO}.
 */
@Mapper(componentModel = "spring")
public interface CadreMapper extends EntityMapper<CadreDTO, Cadre> {}
