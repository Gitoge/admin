package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;


/**
 * A DTO for the {@link com.sn.dtai.carriere.domain.SituationFamiliale} entity.
 */
public class SituationFamilialeDTO implements Serializable {

    private Long id;

    @NotNull
    private String situationMatrimoniale;

    private Integer nombreEpouse;

    private Boolean conjointSalarie;

    private Integer nombreEnfant;

    private Double nombrePart;

    private Integer nombreEnfantImposable;

    private Integer nombreEnfantMajeur;

    private Integer nombreEnfantPlus10;

    private Double nombreMinimumFiscal;

    private Integer nombreEnfantDecede;

    private Instant dateModification;

    private String status;

    private Integer userInsertId;

    private ActesDTO actes;

    private AgentDTO agent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSituationMatrimoniale() {
        return situationMatrimoniale;
    }

    public void setSituationMatrimoniale(String situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public Integer getNombreEpouse() {
        return nombreEpouse;
    }

    public void setNombreEpouse(Integer nombreEpouse) {
        this.nombreEpouse = nombreEpouse;
    }

    public Boolean getConjointSalarie() {
        return conjointSalarie;
    }

    public void setConjointSalarie(Boolean conjointSalarie) {
        this.conjointSalarie = conjointSalarie;
    }

    public Integer getNombreEnfant() {
        return nombreEnfant;
    }

    public void setNombreEnfant(Integer nombreEnfant) {
        this.nombreEnfant = nombreEnfant;
    }

    public Double getNombrePart() {
        return nombrePart;
    }

    public void setNombrePart(Double nombrePart) {
        this.nombrePart = nombrePart;
    }

    public Integer getNombreEnfantImposable() {
        return nombreEnfantImposable;
    }

    public void setNombreEnfantImposable(Integer nombreEnfantImposable) {
        this.nombreEnfantImposable = nombreEnfantImposable;
    }

    public Integer getNombreEnfantMajeur() {
        return nombreEnfantMajeur;
    }

    public void setNombreEnfantMajeur(Integer nombreEnfantMajeur) {
        this.nombreEnfantMajeur = nombreEnfantMajeur;
    }

    public Double getNombreMinimumFiscal() {
        return nombreMinimumFiscal;
    }

    public void setNombreMinimumFiscal(Double nombreMinimumFiscal) {
        this.nombreMinimumFiscal = nombreMinimumFiscal;
    }

    public Integer getNombreEnfantDecede() {
        return nombreEnfantDecede;
    }

    public void setNombreEnfantDecede(Integer nombreEnfantDecede) {
        this.nombreEnfantDecede = nombreEnfantDecede;
    }

    public Instant getDateModification() {
        return dateModification;
    }

    public void setDateModification(Instant dateModification) {
        this.dateModification = dateModification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserInsertId() {
        return userInsertId;
    }

    public void setUserInsertId(Integer userInsertId) {
        this.userInsertId = userInsertId;
    }

    public ActesDTO getActes() {
        return actes;
    }

    public void setActes(ActesDTO actes) {
        this.actes = actes;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    public Integer getNombreEnfantPlus10() {
        return nombreEnfantPlus10;
    }

    public void setNombreEnfantPlus10(Integer nombreEnfantPlus10) {
        this.nombreEnfantPlus10 = nombreEnfantPlus10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SituationFamilialeDTO)) {
            return false;
        }

        SituationFamilialeDTO situationFamilialeDTO = (SituationFamilialeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, situationFamilialeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SituationFamilialeDTO{" +
            "id=" + getId() +
            ", situationMatrimoniale='" + getSituationMatrimoniale() + "'" +
            ", nombreEpouse=" + getNombreEpouse() +
            ", conjointSalarie=" + getConjointSalarie() +
            ", nombreEnfant=" + getNombreEnfant() +
            ", nombrePart=" + getNombrePart() +
            ", nombreEnfantImposable=" + getNombreEnfantImposable() +
            ", nombreEnfantMajeur=" + getNombreEnfantMajeur() +
            ", nombreEnfant+10=" + getNombreEnfantPlus10() +
            ", nombreMinimumFiscal=" + getNombreMinimumFiscal() +
            ", nombreEnfantDecede=" + getNombreEnfantDecede() +
            ", dateModification='" + getDateModification() + "'" +
            ", status='" + getStatus() + "'" +
            ", userInsertId=" + getUserInsertId() +
            ", actes=" + getActes() +
            ", agent=" + getAgent() +
            "}";
    }
}
