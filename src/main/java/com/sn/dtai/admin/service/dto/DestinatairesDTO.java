package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Destinataires} entity.
 */
public class DestinatairesDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String libelle;

    private String prenom;

    private String nom;

    private String adresse;

    private String comptebancaire;

    private TypeDestinatairesDTO typedestinataires;

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getComptebancaire() {
        return comptebancaire;
    }

    public void setComptebancaire(String comptebancaire) {
        this.comptebancaire = comptebancaire;
    }

    public TypeDestinatairesDTO getTypedestinataires() {
        return typedestinataires;
    }

    public void setTypedestinataires(TypeDestinatairesDTO typedestinataires) {
        this.typedestinataires = typedestinataires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DestinatairesDTO)) {
            return false;
        }

        DestinatairesDTO destinatairesDTO = (DestinatairesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, destinatairesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DestinatairesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", comptebancaire='" + getComptebancaire() + "'" +
            ", typedestinataires=" + getTypedestinataires() +
            "}";
    }
}
