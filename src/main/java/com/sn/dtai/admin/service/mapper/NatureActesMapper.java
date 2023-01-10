package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.NatureActes;
import com.sn.dtai.admin.service.dto.NatureActesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NatureActes} and its DTO {@link NatureActesDTO}.
 */
@Mapper(componentModel = "spring")
public interface NatureActesMapper extends EntityMapper<NatureActesDTO, NatureActes> {}
