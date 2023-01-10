package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Applications;
import com.sn.dtai.admin.service.dto.ApplicationsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Applications} and its DTO {@link ApplicationsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApplicationsMapper extends EntityMapper<ApplicationsDTO, Applications> {}
