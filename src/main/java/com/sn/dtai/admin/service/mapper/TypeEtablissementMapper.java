package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.TypeEtablissement;
import com.sn.dtai.admin.service.dto.TypeEtablissementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeEtablissement} and its DTO {@link TypeEtablissementDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeEtablissementMapper extends EntityMapper<TypeEtablissementDTO, TypeEtablissement> {}
