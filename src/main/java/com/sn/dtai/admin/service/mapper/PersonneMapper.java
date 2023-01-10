package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.*;
import com.sn.dtai.admin.service.dto.PersonneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Personne} and its DTO {@link PersonneDTO}.
 */
@Mapper(componentModel = "spring",
    uses = { ProfilsMapper.class }

)
public interface PersonneMapper extends EntityMapper<PersonneDTO, Personne> {

@Mapping(target = "profils", source = "profils")
@Mapping(source = "etablissement", target = "etablissement")
PersonneDTO toDto(Personne p);

}
