package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.TypeDestinataires;
import com.sn.dtai.admin.service.dto.TypeDestinatairesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeDestinataires} and its DTO {@link TypeDestinatairesDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeDestinatairesMapper extends EntityMapper<TypeDestinatairesDTO, TypeDestinataires> {}
