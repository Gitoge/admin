package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

import com.sn.dtai.admin.domain.EtablissementBancaire;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Agence} entity.
 */
public class AgenceDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String libelle;

    private String telephone;

    private String adresse;

    private EtablissementBancaire etablissementBancaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public EtablissementBancaire getEtablissementBancaire() {
        return etablissementBancaire;
    }

    public void setEtablissementBancaire(EtablissementBancaire etablissementBancaire) {
        this.etablissementBancaire = etablissementBancaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgenceDTO)) {
            return false;
        }

        AgenceDTO etablissementBancaireDTO = (AgenceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, etablissementBancaireDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgenceDTO{" +
                "id=" + getId() +
                ", code='" + getCode() + "'" +
                ", libelle='" + getLibelle() + "'" +
                ", telephone='" + getTelephone() + "'" +
                ", adresse='" + getAdresse() + "'" +
                "}";
    }
}
