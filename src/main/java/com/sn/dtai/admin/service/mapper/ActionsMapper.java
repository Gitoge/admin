package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Pages;
import com.sn.dtai.admin.domain.Actions;
import com.sn.dtai.admin.service.dto.PagesDTO;
import com.sn.dtai.admin.service.dto.ActionsDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Actions} and its DTO {@link ActionsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ActionsMapper extends EntityMapper<ActionsDTO, Actions> {
   /*  @Mapping(target = "pages", source = "pages", qualifiedByName = "pagesIdSet")
    ActionsDTO toDto(Actions s);

    @Mapping(target = "removePages", ignore = true)
    Actions toEntity(ActionsDTO actionsDTO);//

    @Named("pagesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PagesDTO toDtoPagesId(Pages pages);  

    @Named("pagesIdSet")
    default Set<PagesDTO> toDtoPagesIdSet(Set<Pages> pages) {  
        return pages.stream().map(this::toDtoPagesId).collect(Collectors.toSet()); 
    }*/
}
