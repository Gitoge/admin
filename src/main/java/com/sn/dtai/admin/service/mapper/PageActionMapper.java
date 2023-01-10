package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.PageAction;
import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.domain.Pages;
import com.sn.dtai.admin.service.dto.PageActionDTO;
import com.sn.dtai.admin.service.dto.ActionsDTO;
import com.sn.dtai.admin.service.dto.PagesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PageAction} and its DTO {@link PageActionDTO}.
 */
@Mapper(componentModel = "spring")
public interface PageActionMapper extends EntityMapper<PageActionDTO, PageAction> {

    @Mapping(target = "actions", source = "actions")
    @Mapping(target = "pages", source = "pages")
    PageActionDTO toDto(PageAction s);

    @Named("actionsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ActionsDTO toDtoActionsId(Actions actions);

    @Named("pagesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PagesDTO toDtoPagesId(Pages pages);

    @Named("actionsIdSet")
    default Set<ActionsDTO> toDtoActionsIdSet(Set<Actions> actions) {
        return actions.stream().map(this::toDtoActionsId).collect(Collectors.toSet());
    }

    @Named("pagesIdSet")
    default Set<PagesDTO> toDtoPagesIdSet(Set<Pages> pages) {
        return pages.stream().map(this::toDtoPagesId).collect(Collectors.toSet());
    }
}
