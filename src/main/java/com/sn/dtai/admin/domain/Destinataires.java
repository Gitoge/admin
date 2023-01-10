package com.sn.dtai.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Destinataires.
 */
@Entity
@Table(name = "destinataires")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Destinataires implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false,unique = true)
    private String code;

    @Column(name = "libelle",unique = true)
    private String libelle;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "comptebancaire")
    private String comptebancaire;

    @ManyToOne
    @JsonIgnoreProperties(value = { "destinataires" }, allowSetters = true)
    private TypeDestinataires typedestinataires;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Destinataires id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Destinataires code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Destinataires libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Destinataires prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public Destinataires nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Destinataires adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getComptebancaire() {
        return this.comptebancaire;
    }

    public Destinataires comptebancaire(String comptebancaire) {
        this.setComptebancaire(comptebancaire);
        return this;
    }

    public void setComptebancaire(String comptebancaire) {
        this.comptebancaire = comptebancaire;
    }

    public TypeDestinataires getTypedestinataires() {
        return this.typedestinataires;
    }

    public void setTypedestinataires(TypeDestinataires typeDestinataires) {
        this.typedestinataires = typeDestinataires;
    }

    public Destinataires typedestinataires(TypeDestinataires typeDestinataires) {
        this.setTypedestinataires(typeDestinataires);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Destinataires)) {
            return false;
        }
        return id != null && id.equals(((Destinataires) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Destinataires{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", comptebancaire='" + getComptebancaire() + "'" +
            "}";
    }
}
