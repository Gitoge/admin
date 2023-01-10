package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.PageAction;
import com.sn.dtai.admin.domain.Roles;
import com.sn.dtai.admin.domain.Profils;
import com.sn.dtai.admin.service.dto.PageActionDTO;
import com.sn.dtai.admin.service.dto.ProfilsDTO;
import com.sn.dtai.admin.service.dto.RolesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roles} and its DTO {@link RolesDTO}.
 */
@Mapper(componentModel = "spring")
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {

    @Mapping(target = "pagesActions", source = "pagesActions", qualifiedByName = "pagesActionsIdSet")
    RolesDTO toDto(Roles s);

    @Mapping(target = "removePagesActions", ignore = true)
    Roles toEntity(RolesDTO rolesDTO);

    @Named("pagesActionsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PageActionDTO toDtoPagesActionsId(PageAction pagesActions);

    @Named("pagesActionsIdSet")
    default Set<PageActionDTO> toDtoPagesActionsIdSet(Set<PageAction> pagesActions) {
        return pagesActions.stream().map(this::toDtoPagesActionsId).collect(Collectors.toSet());
    }
}
