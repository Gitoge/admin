package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Classe;
import com.sn.dtai.admin.service.dto.ClasseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classe} and its DTO {@link ClasseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClasseMapper extends EntityMapper<ClasseDTO, Classe> {}
