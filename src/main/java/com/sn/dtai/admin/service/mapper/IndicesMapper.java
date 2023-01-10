package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Indices;
import com.sn.dtai.admin.service.dto.IndicesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Indices} and its DTO {@link IndicesDTO}.
 */
@Mapper(componentModel = "spring")
public interface IndicesMapper extends EntityMapper<IndicesDTO, Indices> {}
