package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.carriere.domain.Agent} entity.
 */
public class AgentDTO implements Serializable {

    private Long id;

    private String matricule;

    @NotNull
    private String typePiece;

    @NotNull
    private String numeroPiece;

    @NotNull
    private String prenom;

    @NotNull
    private String nom;

    @NotNull
    private LocalDate dateNaissance;

    @NotNull
    private String lieuNaissance;

    @NotNull
    private String sexe;

    private String nomMari;

    private String telephone;

    private String email;

    private String adresse;

    private Boolean femmeMariee;

    private Instant datePriseEnCharge;

    private LocalDate dateSortie;

    private String status;

    private String titre;

    private Integer userInsertId;

    private Integer userUpdateId;

    private Instant dateInsert;

    private Instant dateUpdate;

    // private NationaliteDTO nationalite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNomMari() {
        return nomMari;
    }

    public void setNomMari(String nomMari) {
        this.nomMari = nomMari;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Boolean getFemmeMariee() {
        return femmeMariee;
    }

    public void setFemmeMariee(Boolean femmeMariee) {
        this.femmeMariee = femmeMariee;
    }

    public Instant getDatePriseEnCharge() {
        return datePriseEnCharge;
    }

    public void setDatePriseEnCharge(Instant datePriseEnCharge) {
        this.datePriseEnCharge = datePriseEnCharge;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitreId(String titre) {
        this.titre = titre;
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

    // public NationaliteDTO getNationalite() {
    //     return nationalite;
    // }

    // public void setNationalite(NationaliteDTO nationalite) {
    //     this.nationalite = nationalite;
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgentDTO)) {
            return false;
        }

        AgentDTO agentDTO = (AgentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, agentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgentDTO{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", nomMari='" + getNomMari() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", femmeMariee='" + getFemmeMariee() + "'" +
            ", datePriseEnCharge='" + getDatePriseEnCharge() + "'" +
            ", dateSortie='" + getDateSortie() + "'" +
            ", status='" + getStatus() + "'" +
            ", titre=" + getTitre() +
            ", userInsertId=" + getUserInsertId() +
            ", userUpdateId=" + getUserUpdateId() +
            ", dateInsert='" + getDateInsert() + "'" +
            ", dateUpdate='" + getDateUpdate() + "'" +
            // ", nationalite=" + getNationalite() +
            "}";
    }
}
