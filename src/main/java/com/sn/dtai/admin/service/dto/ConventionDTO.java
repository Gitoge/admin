package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.carriere.domain.Convention} entity.
 */
public class ConventionDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private Integer userInsertId;

    private Integer userUpdateId;

    private Instant dateInsert;

    private Instant dateUpdate;

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

    public Integer getUserInsertId() {
        return userInsertId;
    }

    public void setUserInsertId(Integer userInsertId) {
        this.userInsertId = userInsertId;
    }

    public Integer getUserUpdateId() {
        return userUpdateId;
    }

    public void setUserUpdateId(Integer userUpdateId) {
        this.userUpdateId = userUpdateId;
    }

    public Instant getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Instant dateInsert) {
        this.dateInsert = dateInsert;
    }

    public Instant getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConventionDTO)) {
            return false;
        }

        ConventionDTO conventionDTO = (ConventionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, conventionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConventionDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", userInsertId=" + getUserInsertId() +
            ", userUpdateId=" + getUserUpdateId() +
            ", dateInsert='" + getDateInsert() + "'" +
            ", dateUpdate='" + getDateUpdate() + "'" +
            "}";
    }
}
