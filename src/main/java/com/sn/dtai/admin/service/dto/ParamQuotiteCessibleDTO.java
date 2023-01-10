package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.ParamQuotiteCessible} entity.
 */
public class ParamQuotiteCessibleDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    @NotNull
    private Integer salaireDebut;

    @NotNull
    private Integer salaireFin;

    @NotNull
    private Integer tauxTranche;

    @NotNull
    private LocalDate dateImpact;

    private Integer userIdInsert;

    private LocalDate userdateInsert;

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

    public Integer getSalaireDebut() {
        return salaireDebut;
    }

    public void setSalaireDebut(Integer salaireDebut) {
        this.salaireDebut = salaireDebut;
    }

    public Integer getSalaireFin() {
        return salaireFin;
    }

    public void setSalaireFin(Integer salaireFin) {
        this.salaireFin = salaireFin;
    }

    public Integer getTauxTranche() {
        return tauxTranche;
    }

    public void setTauxTranche(Integer tauxTranche) {
        this.tauxTranche = tauxTranche;
    }

    public LocalDate getDateImpact() {
        return dateImpact;
    }

    public void setDateImpact(LocalDate dateImpact) {
        this.dateImpact = dateImpact;
    }

    public Integer getUserIdInsert() {
        return userIdInsert;
    }

    public void setUserIdInsert(Integer userIdInsert) {
        this.userIdInsert = userIdInsert;
    }

    public LocalDate getUserdateInsert() {
        return userdateInsert;
    }

    public void setUserdateInsert(LocalDate userdateInsert) {
        this.userdateInsert = userdateInsert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParamQuotiteCessibleDTO)) {
            return false;
        }

        ParamQuotiteCessibleDTO paramQuotiteCessibleDTO = (ParamQuotiteCessibleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paramQuotiteCessibleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamQuotiteCessibleDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", salaireDebut=" + getSalaireDebut() +
            ", salaireFin=" + getSalaireFin() +
            ", tauxTranche=" + getTauxTranche() +
            ", dateImpact='" + getDateImpact() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
