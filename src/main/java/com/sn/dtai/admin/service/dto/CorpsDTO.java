package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Corps} entity.
 */
public class CorpsDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private String codHierarchie;

    @NotNull
    private Integer ageRetraite;

    private String classification;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodHierarchie() {
        return codHierarchie;
    }

    public void setCodHierarchie(String codHierarchie) {
        this.codHierarchie = codHierarchie;
    }

    public Integer getAgeRetraite() {
        return ageRetraite;
    }

    public void setAgeRetraite(Integer ageRetraite) {
        this.ageRetraite = ageRetraite;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
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
        if (!(o instanceof CorpsDTO)) {
            return false;
        }

        CorpsDTO corpsDTO = (CorpsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, corpsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CorpsDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", codHierarchie='" + getCodHierarchie() + "'" +
            ", ageRetraite=" + getAgeRetraite() +
            ", classification='" + getClassification() + "'" +
            ", postes=" + getPostes() +
            "}";
    }
}
