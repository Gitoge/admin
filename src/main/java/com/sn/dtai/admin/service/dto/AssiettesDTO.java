package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Assiettes} entity.
 */
public class AssiettesDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private Integer userIdInsert;

    private LocalDate userdateInsert;

    private Set<PostesDTO> postes = new HashSet<>();

    private String operateur;

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

    public Set<PostesDTO> getPostes() {
        return postes;
    }

    public void setPostes(Set<PostesDTO> postes) {
        this.postes = postes;
    }

    
    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssiettesDTO)) {
            return false;
        }

        AssiettesDTO assiettesDTO = (AssiettesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, assiettesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssiettesDTO{" +
                "id=" + getId() +
                ", code='" + getCode() + "'" +
                ", libelle='" + getLibelle() + "'" +
                ", description='" + getDescription() + "'" +
                ", userIdInsert=" + getUserIdInsert() +
                ", userdateInsert='" + getUserdateInsert() + "'" +
                ", postes=" + getPostes() +
                "}";
    }
}
