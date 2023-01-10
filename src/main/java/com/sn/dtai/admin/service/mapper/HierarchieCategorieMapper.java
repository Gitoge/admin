package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.HierarchieCategorie;
import com.sn.dtai.admin.domain.Categories;
import com.sn.dtai.admin.domain.Hierarchie;
import com.sn.dtai.admin.service.dto.HierarchieCategorieDTO;
import com.sn.dtai.admin.service.dto.CategoriesDTO;
import com.sn.dtai.admin.service.dto.HierarchieDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HierarchieCategorie} and its DTO {@link HierarchieCategorieDTO}.
 */
@Mapper(componentModel = "spring")
public interface HierarchieCategorieMapper extends EntityMapper<HierarchieCategorieDTO, HierarchieCategorie> {

    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "hierarchie", source = "hierarchie")
    HierarchieCategorieDTO toDto(HierarchieCategorie s);

    @Named("categoriesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoriesDTO toDtoCategoriesId(Categories categories);

    @Named("hierarchieId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HierarchieDTO toDtoHierarchieId(Hierarchie hierarchie);

    @Named("categoriesIdSet")
    default Set<CategoriesDTO> toDtoCategoriesIdSet(Set<Categories> categories) {
        return categories.stream().map(this::toDtoCategoriesId).collect(Collectors.toSet());
    }

    @Named("hierarchieIdSet")
    default Set<HierarchieDTO> toDtoHierarchieIdSet(Set<Hierarchie> hierarchie) {
        return hierarchie.stream().map(this::toDtoHierarchieId).collect(Collectors.toSet());
    }
}
