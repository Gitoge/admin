package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.carriere.domain.Actes} entity.
 */
public class ActesDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer numeroActe;

    private LocalDate dateActe;

    private LocalDate dateEffet;

    private Long origineId;

    private Integer userInsertId;

    private NatureActesDTO natureActes;

    private TypeActesDTO typeActes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroActe() {
        return numeroActe;
    }

    public void setNumeroActe(Integer numeroActe) {
        this.numeroActe = numeroActe;
    }

    public LocalDate getDateActe() {
        return dateActe;
    }

    public void setDateActe(LocalDate dateActe) {
        this.dateActe = dateActe;
    }

    public LocalDate getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(LocalDate dateEffet) {
        this.dateEffet = dateEffet;
    }

    public Long getOrigineId() {
        return origineId;
    }

    public void setOrigineId(Long origineId) {
        this.origineId = origineId;
    }

    public Integer getUserInsertId() {
        return userInsertId;
    }

    public void setUserInsertId(Integer userInsertId) {
        this.userInsertId = userInsertId;
    }

    public NatureActesDTO getNatureActes() {
        return natureActes;
    }

    public void setNatureActes(NatureActesDTO natureActes) {
        this.natureActes = natureActes;
    }

    public TypeActesDTO getTypeActes() {
        return typeActes;
    }

    public void setTypeActes(TypeActesDTO typeActes) {
        this.typeActes = typeActes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActesDTO)) {
            return false;
        }

        ActesDTO actesDTO = (ActesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, actesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActesDTO{" +
            "id=" + getId() +
            ", numeroActe=" + getNumeroActe() +
            ", dateActe='" + getDateActe() + "'" +
            ", dateEffet='" + getDateEffet() + "'" +
            ", origineId=" + getOrigineId() +
            ", userInsertId=" + getUserInsertId() +
            ", natureActes=" + getNatureActes() +
            ", typeActes=" + getTypeActes() +
            "}";
    }
}
