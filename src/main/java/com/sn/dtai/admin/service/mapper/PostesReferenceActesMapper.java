package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.*;
import com.sn.dtai.admin.service.dto.PostesReferenceActesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PostesReferenceActes} and its DTO {@link PostesReferenceActesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PostesReferenceActesMapper extends EntityMapper<PostesReferenceActesDTO, PostesReferenceActes> {}
