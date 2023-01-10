package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.*;
import com.sn.dtai.admin.service.dto.BilleteurDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Billeteur} and its DTO {@link BilleteurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BilleteurMapper extends EntityMapper<BilleteurDTO, Billeteur> {

@Mapping(target = "etablissement", source = "etablissement")
BilleteurDTO toDto(Billeteur s);

}
