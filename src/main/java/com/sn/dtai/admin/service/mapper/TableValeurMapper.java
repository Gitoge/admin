package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.TableTypeValeur;
import com.sn.dtai.admin.domain.TableValeur;
import com.sn.dtai.admin.service.dto.TableTypeValeurDTO;
import com.sn.dtai.admin.service.dto.TableValeurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TableValeur} and its DTO {@link TableValeurDTO}.
 */
@Mapper(componentModel = "spring")
public interface TableValeurMapper extends EntityMapper<TableValeurDTO, TableValeur> {
    @Mapping(target = "tabletypevaleur", source = "tabletypevaleur", qualifiedByName = "tableTypeValeurId")
    TableValeurDTO toDto(TableValeur s);

    @Named("tableTypeValeurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TableTypeValeurDTO toDtoTableTypeValeurId(TableTypeValeur tableTypeValeur);
}
