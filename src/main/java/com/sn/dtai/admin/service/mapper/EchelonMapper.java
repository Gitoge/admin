package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Echelon;
import com.sn.dtai.admin.service.dto.EchelonDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Echelon} and its DTO {@link EchelonDTO}.
 */
@Mapper(componentModel = "spring")
public interface EchelonMapper extends EntityMapper<EchelonDTO, Echelon> {}
