package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.StructureAdmin;
import com.sn.dtai.admin.service.dto.StructureAdminDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StructureAdmin} and its DTO {@link StructureAdminDTO}.
 */
@Mapper(componentModel = "spring")
public interface StructureAdminMapper extends EntityMapper<StructureAdminDTO, StructureAdmin> {}
