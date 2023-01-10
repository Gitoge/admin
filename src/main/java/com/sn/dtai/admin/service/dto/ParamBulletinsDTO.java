package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.ParamBulletins} entity.
 */
public class ParamBulletinsDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String entete;

    private String signature;

    private String arrierePlan;

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

    public String getEntete() {
        return entete;
    }

    public void setEntete(String entete) {
        this.entete = entete;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getArrierePlan() {
        return arrierePlan;
    }

    public void setArrierePlan(String arrierePlan) {
        this.arrierePlan = arrierePlan;
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
        if (!(o instanceof ParamBulletinsDTO)) {
            return false;
        }

        ParamBulletinsDTO paramBulletinsDTO = (ParamBulletinsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paramBulletinsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamBulletinsDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", entete='" + getEntete() + "'" +
            ", signature='" + getSignature() + "'" +
            ", arrierePlan='" + getArrierePlan() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
