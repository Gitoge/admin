package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.ParamBulletins;
import com.sn.dtai.admin.service.dto.ParamBulletinsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParamBulletins} and its DTO {@link ParamBulletinsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParamBulletinsMapper extends EntityMapper<ParamBulletinsDTO, ParamBulletins> {}
