package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Modules;
import com.sn.dtai.admin.domain.Pages;
import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.service.dto.PagesDTO;
import com.sn.dtai.admin.service.dto.ActionsDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pages} and its DTO {@link PagesDTO}.
 */
@Mapper(componentModel = "spring")
public interface PagesMapper extends EntityMapper<PagesDTO, Pages> {

    @Mapping(target = "modules", source = "modules")
    @Mapping(target = "actions", source = "actions")
    PagesDTO toDto(Pages s);

    @Mapping(target = "removeActions", ignore = true)
    Pages toEntity(PagesDTO pagesDTO);

    @Named("actionsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ActionsDTO toDtoActionsId(Actions actions);

    @Named("actionsIdSet")
    default Set<ActionsDTO> toDtoActionsIdSet(Set<Actions> actions) {
        return actions.stream().map(this::toDtoActionsId).collect(Collectors.toSet());
    }
}
