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
 * A Corps.
 */
@Entity
@Table(name = "corps")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Corps implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false,unique = true)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false,unique = true)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "cod_hierarchie")
    private String codHierarchie;

    @NotNull
    @Column(name = "age_retraite", nullable = false)
    private Integer ageRetraite;

    @Column(name = "classification")
    private String classification;

    @ManyToMany
    @JoinTable(
        name = "rel_corps__postes",
        joinColumns = @JoinColumn(name = "corps_id"),
        inverseJoinColumns = @JoinColumn(name = "postes_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "grades", "hierarchies", "corps", "emplois", "assiettes" }, allowSetters = true)
    private Set<Postes> postes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Corps id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Corps code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Corps libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Corps description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodHierarchie() {
        return this.codHierarchie;
    }

    public Corps codHierarchie(String codHierarchie) {
        this.setCodHierarchie(codHierarchie);
        return this;
    }

    public void setCodHierarchie(String codHierarchie) {
        this.codHierarchie = codHierarchie;
    }

    public Integer getAgeRetraite() {
        return this.ageRetraite;
    }

    public Corps ageRetraite(Integer ageRetraite) {
        this.setAgeRetraite(ageRetraite);
        return this;
    }

    public void setAgeRetraite(Integer ageRetraite) {
        this.ageRetraite = ageRetraite;
    }

    public String getClassification() {
        return this.classification;
    }

    public Corps classification(String classification) {
        this.setClassification(classification);
        return this;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Set<Postes> getPostes() {
        return this.postes;
    }

    public void setPostes(Set<Postes> postes) {
        this.postes = postes;
    }

    public Corps postes(Set<Postes> postes) {
        this.setPostes(postes);
        return this;
    }

    public Corps addPostes(Postes postes) {
        this.postes.add(postes);
        postes.getCorps().add(this);
        return this;
    }

    public Corps removePostes(Postes postes) {
        this.postes.remove(postes);
        postes.getCorps().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Corps)) {
            return false;
        }
        return id != null && id.equals(((Corps) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Corps{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", codHierarchie='" + getCodHierarchie() + "'" +
            ", ageRetraite=" + getAgeRetraite() +
            ", classification='" + getClassification() + "'" +
            "}";
    }
}
