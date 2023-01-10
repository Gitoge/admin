package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Diplomes;
import com.sn.dtai.admin.service.dto.DiplomesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Diplomes} and its DTO {@link DiplomesDTO}.
 */
@Mapper(componentModel = "spring")
public interface DiplomesMapper extends EntityMapper<DiplomesDTO, Diplomes> {}
