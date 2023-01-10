package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.TableTypeValeur;
import com.sn.dtai.admin.service.dto.TableTypeValeurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TableTypeValeur} and its DTO {@link TableTypeValeurDTO}.
 */
@Mapper(componentModel = "spring")
public interface TableTypeValeurMapper extends EntityMapper<TableTypeValeurDTO, TableTypeValeur> {}
