package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Destinataires;
import com.sn.dtai.admin.domain.TypeDestinataires;
import com.sn.dtai.admin.service.dto.DestinatairesDTO;
import com.sn.dtai.admin.service.dto.TypeDestinatairesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Destinataires} and its DTO {@link DestinatairesDTO}.
 */
@Mapper(componentModel = "spring")
public interface DestinatairesMapper extends EntityMapper<DestinatairesDTO, Destinataires> {
    @Mapping(target = "typedestinataires", source = "typedestinataires", qualifiedByName = "typeDestinatairesId")
    DestinatairesDTO toDto(Destinataires s);

    @Named("typeDestinatairesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeDestinatairesDTO toDtoTypeDestinatairesId(TypeDestinataires typeDestinataires);
}
