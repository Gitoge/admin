package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Profils;
import com.sn.dtai.admin.domain.Roles;
import com.sn.dtai.admin.service.dto.ProfilsDTO;
import com.sn.dtai.admin.service.dto.RolesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Profils} and its DTO {@link ProfilsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfilsMapper extends EntityMapper<ProfilsDTO, Profils> {
    @Mapping(target = "roles", source = "roles")
    ProfilsDTO toDto(Profils s);

    @Mapping(target = "removeRoles", ignore = true)
    Profils toEntity(ProfilsDTO profilsDTO);

    @Named("rolesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RolesDTO toDtoRolesId(Roles roles);

    @Named("rolesIdSet")
    default Set<RolesDTO> toDtoRolesIdSet(Set<Roles> roles) {
        return roles.stream().map(this::toDtoRolesId).collect(Collectors.toSet());
    }
}
