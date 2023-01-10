package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.Agence;
import com.sn.dtai.admin.domain.EtablissementBancaire;
import com.sn.dtai.admin.service.dto.AgenceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agence} and its DTO {@link AgenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgenceMapper extends EntityMapper<AgenceDTO, Agence> {
    @Mapping(target = "etablissementBancaire", source = "etablissementBancaire")
    AgenceDTO toDto(Agence s);

    @Named("etablissementBancaireId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EtablissementBancaire toDtoEtablissementBancaireId(EtablissementBancaire etablissementBancaire);
}
