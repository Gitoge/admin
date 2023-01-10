package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Emplois} entity.
 */
public class EmploisDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private Integer tauxAt;

    private String primeLieEmploi;

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

    public Integer getTauxAt() {
        return tauxAt;
    }

    public void setTauxAt(Integer tauxAt) {
        this.tauxAt = tauxAt;
    }

    public String getPrimeLieEmploi() {
        return primeLieEmploi;
    }

    public void setPrimeLieEmploi(String primeLieEmploi) {
        this.primeLieEmploi = primeLieEmploi;
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
        if (!(o instanceof EmploisDTO)) {
            return false;
        }

        EmploisDTO emploisDTO = (EmploisDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, emploisDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmploisDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", tauxAt=" + getTauxAt() +
            ", primeLieEmploi='" + getPrimeLieEmploi() + "'" +
            ", postes=" + getPostes() +
            "}";
    }
}
