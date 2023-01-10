package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Hierarchie} entity.
 */
public class HierarchieDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    @NotNull
    private String borneInferieure;

    @NotNull
    private String borneSuperieure;

    private String codEchelonIndiciare;

    private String hcadre;

    private Set<PostesDTO> postes = new HashSet<>();

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

    public String getBorneInferieure() {
        return borneInferieure;
    }

    public void setBorneInferieure(String borneInferieure) {
        this.borneInferieure = borneInferieure;
    }

    public String getBorneSuperieure() {
        return borneSuperieure;
    }

    public void setBorneSuperieure(String borneSuperieure) {
        this.borneSuperieure = borneSuperieure;
    }

    public String getCodEchelonIndiciare() {
        return codEchelonIndiciare;
    }

    public void setCodEchelonIndiciare(String codEchelonIndiciare) {
        this.codEchelonIndiciare = codEchelonIndiciare;
    }

    public String getHcadre() {
        return hcadre;
    }

    public void setHcadre(String hcadre) {
        this.hcadre = hcadre;
    }

    public Set<PostesDTO> getPostes() {
        return postes;
    }

    public void setPostes(Set<PostesDTO> postes) {
        this.postes = postes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HierarchieDTO)) {
            return false;
        }

        HierarchieDTO hierarchieDTO = (HierarchieDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hierarchieDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HierarchieDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", borneInferieure='" + getBorneInferieure() + "'" +
            ", borneSuperieure='" + getBorneSuperieure() + "'" +
            ", codEchelonIndiciare='" + getCodEchelonIndiciare() + "'" +
            ", hcadre='" + getHcadre() + "'" +
            ", postes=" + getPostes() +
            "}";
    }
}
