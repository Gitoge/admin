package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.carriere.domain.Billeteur} entity.
 */
public class BilleteurDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String prenom;

    private String nom;

    private String matricule;

    private String telephone;

    private EtablissementDTO etablissement;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public EtablissementDTO getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(EtablissementDTO etablissement) {
        this.etablissement = etablissement;
    }

@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BilleteurDTO)) {
            return false;
        }

        BilleteurDTO billeteurDTO = (BilleteurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, billeteurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BilleteurDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", matricule='" + getMatricule() + "'" +
            ", telephone='" + getTelephone() + "'" +
            "}";
    }
}
