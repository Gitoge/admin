package com.sn.dtai.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Hierarchie.
 */
@Entity
@Table(name = "hierarchie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Hierarchie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "borne_inferieure", nullable = false)
    private String borneInferieure;

    @NotNull
    @Column(name = "borne_superieure", nullable = false)
    private String borneSuperieure;

    @Column(name = "cod_echelon_indiciare")
    private String codEchelonIndiciare;

    @Column(name = "hcadre")
    private String hcadre;

    @ManyToMany
    @JoinTable(
        name = "rel_hierarchies__postes",
        joinColumns = @JoinColumn(name = "hierarchie_id"),
        inverseJoinColumns = @JoinColumn(name = "postes_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "grades", "hierarchies", "corps", "emplois", "assiettes" }, allowSetters = true)
    private Set<Postes> postes = new HashSet<>();

    @Column(name = "code_debut")
    private String codeDebut;

    @Column(name = "code_fin")
    private String codeFin;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Hierarchie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Hierarchie code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Hierarchie libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getBorneInferieure() {
        return this.borneInferieure;
    }

    public Hierarchie borneInferieure(String borneInferieure) {
        this.setBorneInferieure(borneInferieure);
        return this;
    }

    public void setBorneInferieure(String borneInferieure) {
        this.borneInferieure = borneInferieure;
    }

    public String getBorneSuperieure() {
        return this.borneSuperieure;
    }

    public Hierarchie borneSuperieure(String borneSuperieure) {
        this.setBorneSuperieure(borneSuperieure);
        return this;
    }

    public void setBorneSuperieure(String borneSuperieure) {
        this.borneSuperieure = borneSuperieure;
    }

    public String getCodEchelonIndiciare() {
        return this.codEchelonIndiciare;
    }

    public Hierarchie codEchelonIndiciare(String codEchelonIndiciare) {
        this.setCodEchelonIndiciare(codEchelonIndiciare);
        return this;
    }

    public void setCodEchelonIndiciare(String codEchelonIndiciare) {
        this.codEchelonIndiciare = codEchelonIndiciare;
    }

    public String getHcadre() {
        return this.hcadre;
    }

    public Hierarchie hcadre(String hcadre) {
        this.setHcadre(hcadre);
        return this;
    }

    public void setHcadre(String hcadre) {
        this.hcadre = hcadre;
    }

    public Set<Postes> getPostes() {
        return this.postes;
    }

    public void setPostes(Set<Postes> postes) {
        this.postes = postes;
    }

    public Hierarchie postes(Set<Postes> postes) {
        this.setPostes(postes);
        return this;
    }

    public Hierarchie addPostes(Postes postes) {
        this.postes.add(postes);
        postes.getHierarchies().add(this);
        return this;
    }

    public Hierarchie removePostes(Postes postes) {
        this.postes.remove(postes);
        postes.getHierarchies().remove(this);
        return this;
    }

    public String getCodeDebut() {
        return this.codeDebut;
    }

    public Hierarchie codeDebut(String codeDebut) {
        this.setCodeDebut(codeDebut);
        return this;
    }

    public void setCodeDebut(String codeDebut) {
        this.codeDebut = codeDebut;
    }

    public String getCodeFin() {
        return this.codeFin;
    }

    public Hierarchie codeFin(String codeFin) {
        this.setCodeFin(codeFin);
        return this;
    }

    public void setCodeFin(String codeFin) {
        this.codeFin = codeFin;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hierarchie)) {
            return false;
        }
        return id != null && id.equals(((Hierarchie) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hierarchie{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", borneInferieure='" + getBorneInferieure() + "'" +
            ", borneSuperieure='" + getBorneSuperieure() + "'" +
            ", codEchelonIndiciare='" + getCodEchelonIndiciare() + "'" +
            ", hcadre='" + getHcadre() + "'" +
            "}";
    }
}
