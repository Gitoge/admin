package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Positions} entity.
 */
public class PositionsDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private String typeAgent;

    private Integer impactSolde;

    private Boolean statutPosition;

    private TypePositionDTO typeposition;

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

    public String getTypeAgent() {
        return typeAgent;
    }

    public void setTypeAgent(String typeAgent) {
        this.typeAgent = typeAgent;
    }

    public Integer getImpactSolde() {
        return impactSolde;
    }

    public void setImpactSolde(Integer impactSolde) {
        this.impactSolde = impactSolde;
    }

    public Boolean getStatutPosition() {
        return statutPosition;
    }

    public void setStatutPosition(Boolean statutPosition) {
        this.statutPosition = statutPosition;
    }

    public TypePositionDTO getTypeposition() {
        return typeposition;
    }

    public void setTypeposition(TypePositionDTO typeposition) {
        this.typeposition = typeposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PositionsDTO)) {
            return false;
        }

        PositionsDTO positionsDTO = (PositionsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, positionsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PositionsDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", typeAgent='" + getTypeAgent() + "'" +
            ", impactSolde=" + getImpactSolde() +
            ", statutPosition='" + getStatutPosition() + "'" +
            ", typeposition=" + getTypeposition() +
            "}";
    }
}
