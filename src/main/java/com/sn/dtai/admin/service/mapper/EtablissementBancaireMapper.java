package com.sn.dtai.admin.service.mapper;

import com.sn.dtai.admin.domain.EtablissementBancaire;
import com.sn.dtai.admin.service.dto.EtablissementBancaireDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtablissementBancaire} and its DTO {@link EtablissementBancaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtablissementBancaireMapper extends EntityMapper<EtablissementBancaireDTO, EtablissementBancaire> {}
