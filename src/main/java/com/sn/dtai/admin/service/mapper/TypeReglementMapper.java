package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.TypeReglement;
import com.sn.dtai.admin.service.dto.TypeReglementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeReglement} and its DTO {@link TypeReglementDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeReglementMapper extends EntityMapper<TypeReglementDTO, TypeReglement> {}
