package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.ParamBaremeMinimumFiscal} entity.
 */
public class ParamBaremeMinimumFiscalDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    @NotNull
    private Integer montantPlafond;

    @NotNull
    private Integer montantAPrelever;

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

    public Integer getMontantPlafond() {
        return montantPlafond;
    }

    public void setMontantPlafond(Integer montantPlafond) {
        this.montantPlafond = montantPlafond;
    }

    public Integer getMontantAPrelever() {
        return montantAPrelever;
    }

    public void setMontantAPrelever(Integer montantAPrelever) {
        this.montantAPrelever = montantAPrelever;
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
        if (!(o instanceof ParamBaremeMinimumFiscalDTO)) {
            return false;
        }

        ParamBaremeMinimumFiscalDTO paramBaremeMinimumFiscalDTO = (ParamBaremeMinimumFiscalDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paramBaremeMinimumFiscalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamBaremeMinimumFiscalDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", montantPlafond=" + getMontantPlafond() +
            ", montantAPrelever=" + getMontantAPrelever() +
            ", dateImpact='" + getDateImpact() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
