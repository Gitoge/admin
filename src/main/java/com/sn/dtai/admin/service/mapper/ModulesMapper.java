package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Applications;
import com.sn.dtai.admin.domain.Modules;
import com.sn.dtai.admin.service.dto.ApplicationsDTO;
import com.sn.dtai.admin.service.dto.ModulesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Modules} and its DTO {@link ModulesDTO}.
 */
@Mapper(componentModel = "spring")
public interface ModulesMapper extends EntityMapper<ModulesDTO, Modules> {
    @Mapping(target = "applications", source = "applications")
    ModulesDTO toDto(Modules s);

    @Named("applicationsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationsDTO toDtoApplicationsId(Applications applications);
}
