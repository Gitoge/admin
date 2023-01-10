package com.sn.dtai.admin.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A EtablissementBancaire.
 */
@Entity
@Table(name = "etablissement_bancaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtablissementBancaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "sigle")
    private String sigle;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "fax")
    private String fax;

    @Column(name = "swift")
    private String swift;


    @OneToMany(mappedBy = "etablissementBancaire")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"etablissementBancaire"}, allowSetters = true)
    private Set<Agence> agence = new HashSet<>();

    
    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EtablissementBancaire id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public EtablissementBancaire code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public EtablissementBancaire libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getSigle() {
        return this.sigle;
    }

    public EtablissementBancaire sigle(String sigle) {
        this.setSigle(sigle);
        return this;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public EtablissementBancaire telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public EtablissementBancaire email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public EtablissementBancaire adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getFax() {
        return this.fax;
    }

    public EtablissementBancaire fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    
    public Set<Agence> getAgence() {
        return this.agence;
    }

    public void setAgence(Set<Agence> agence) {
        if (this.agence != null) {
            this.agence.forEach(i -> i.setEtablissementBancaire(null));
        }
        if (agence != null) {
            agence.forEach(i -> i.setEtablissementBancaire(this));
        }
        this.agence = agence;
    }

    public EtablissementBancaire agence(Set<Agence> agence) {
        this.setAgence(agence);
        return this;
    }

    public EtablissementBancaire addAgence(Agence agence) {
        this.agence.add(agence);
        agence.setEtablissementBancaire(this);
        return this;
    }

    public EtablissementBancaire removeAgence(Agence agence) {
        this.agence.remove(agence);
        agence.setEtablissementBancaire(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtablissementBancaire)) {
            return false;
        }
        return id != null && id.equals(((EtablissementBancaire) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtablissementBancaire{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", sigle='" + getSigle() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", fax='" + getFax() + "'" +
            ", swift='" + getSwift() + "'" +
            "}";
    }
}
