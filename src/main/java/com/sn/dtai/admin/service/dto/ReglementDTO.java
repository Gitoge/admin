package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Reglement} entity.
 */
public class ReglementDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String libelle;

    private String complementinfos;

    private String commentaire;

    private TypeReglementDTO typereglement;

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

    public String getComplementinfos() {
        return complementinfos;
    }

    public void setComplementinfos(String complementinfos) {
        this.complementinfos = complementinfos;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public TypeReglementDTO getTypereglement() {
        return typereglement;
    }

    public void setTypereglement(TypeReglementDTO typereglement) {
        this.typereglement = typereglement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReglementDTO)) {
            return false;
        }

        ReglementDTO reglementDTO = (ReglementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reglementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReglementDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", complementinfos='" + getComplementinfos() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            ", typereglement=" + getTypereglement() +
            "}";
    }
}
