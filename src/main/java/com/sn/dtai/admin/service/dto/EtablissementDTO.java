package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Etablissement} entity.
 */
public class EtablissementDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String sigle;

    @NotNull
    private String libelleLong;

    @NotNull
    private String libelleCourt;

    private String adresse;

    private String numTelephone;

    private String fax;

    private String email;

    private String bp;

    private Long conventionId ;

    private TypeEtablissementDTO typeEtab;

    private LocaliteDTO localite;

    private String libelleConvention;

    private Set<PostesDTO> postes = new HashSet<>();

    private Integer nombreHeure;


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

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getLibelleLong() {
        return libelleLong;
    }

    public void setLibelleLong(String libelle) {
        this.libelleLong = libelle;
    }

    
    public String getLibelleCourt() {
        return libelleCourt;
    }

    public void setLibelleCourt(String libelle) {
        this.libelleCourt = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Long getConvention() {
        return conventionId;
    }

    public void setConvention(Long conventionId) {
        this.conventionId = conventionId;
    }

    public TypeEtablissementDTO getTypeEtab() {
        return typeEtab;
    }

    public void setTypeEtab(TypeEtablissementDTO typeEtab) {
        this.typeEtab = typeEtab;
    }

    public LocaliteDTO getLocalite() {
        return localite;
    }

    public void setLocalite(LocaliteDTO localite) {
        this.localite = localite;
    }


    
    public String getLibelleConvention() {
        return libelleConvention;
    }

    public void setLibelleConvention(String libelleConvention) {
        this.libelleConvention = libelleConvention;
    }


    
    public Set<PostesDTO> getPostes() {
        return postes;
    }

    public void setPostes(Set<PostesDTO> postes) {
        this.postes = postes;
    }

    public Integer getNombreHeure() {
        return this.nombreHeure;
    }


    public void setNombreHeure(Integer nombreHeure) {
        this.nombreHeure = nombreHeure;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtablissementDTO)) {
            return false;
        }

        EtablissementDTO etablissementDTO = (EtablissementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, etablissementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtablissementDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", sigle='" + getSigle() + "'" +
            ", libelleCourt='" + getLibelleCourt() + "'" +
            ", libelleLong='" + getLibelleLong() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", numTelephone='" + getNumTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", bp='" + getBp() + "'" +
            ", ConventionId='" + getConvention() + "'" +
            ", LibelleConvention='" + getLibelleConvention() + "'" +
            ", typeEtab=" + getTypeEtab() +
            ", localite=" + getLocalite() +
            ", postes=" + getPostes() +
            "}";
    }
}
