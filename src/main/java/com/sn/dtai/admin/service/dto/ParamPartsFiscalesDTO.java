package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.ParamPartsFiscales} entity.
 */
public class ParamPartsFiscalesDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String libelle;

    @NotNull
    private String composition;

    @NotNull
    private Double nombreParts;

    private String description;

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

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public Double getNombreParts() {
        return nombreParts;
    }

    public void setNombreParts(Double nombreParts) {
        this.nombreParts = nombreParts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof ParamPartsFiscalesDTO)) {
            return false;
        }

        ParamPartsFiscalesDTO paramPartsFiscalesDTO = (ParamPartsFiscalesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paramPartsFiscalesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamPartsFiscalesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", composition='" + getComposition() + "'" +
            ", nombreParts=" + getNombreParts() +
            ", description='" + getDescription() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
