package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Postes} entity.
 */
public class PostesDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    private Integer typePoste;

    private String formule;

    private Boolean dansAssiette;

    private Long categorieElementId;

    private Long frequenceId;

    private Integer userIdInsert;

    private LocalDate userdateInsert;

    private String reference;

    private Integer montant;

    private Long sens;
    
    private Long etat;


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

    public Integer getTypePoste() {
        return typePoste;
    }

    public void setTypePoste(Integer typePoste) {
        this.typePoste = typePoste;
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public Boolean getDansAssiette() {
        return dansAssiette;
    }

    public void setDansAssiette(Boolean dansAssiette) {
        this.dansAssiette = dansAssiette;
    }

    public Long getCategorieElementId() {
        return categorieElementId;
    }

    public void setCategorieElementId(Long categorieElementId) {
        this.categorieElementId = categorieElementId;
    }

    public Long getFrequenceId() {
        return frequenceId;
    }

    public void setFrequenceId(Long frequenceId) {
        this.frequenceId = frequenceId;
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

     public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

     public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Long getSens() {
        return sens;
    }

    public void setSens(Long sens) {
        this.sens = sens;
    }

    public Long getEtat() {
        return etat;
    }

    public void setEtat(Long etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostesDTO)) {
            return false;
        }

        PostesDTO postesDTO = (PostesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, postesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", formule='" + getFormule() + "'" +
            ", dansAssiette='" + getDansAssiette() + "'" +
            ", reference='" + getReference() + "'" +
            ", categorieElementId='" + getCategorieElementId() +
            ", frequenceId='" + getFrequenceId() +
            ", typePoste=" + getTypePoste() +
            ", montant=" + getMontant() +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
