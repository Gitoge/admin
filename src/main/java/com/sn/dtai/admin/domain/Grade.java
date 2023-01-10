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
 * A Grade.
 */
@Entity
@Table(name = "grade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Grade implements Serializable {

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

    @Column(name = "description")
    private String description;

    @Column(name = "anc_ech_classe")
    private String ancEchClasse;

    @Column(name = "nbre_echelon")
    private Integer nbreEchelon;

    @ManyToMany
    @JoinTable(
        name = "rel_grades__postes",
        joinColumns = @JoinColumn(name = "grade_id"),
        inverseJoinColumns = @JoinColumn(name = "postes_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "grades", "hierarchies", "corps", "emplois", "assiettes" }, allowSetters = true)
    private Set<Postes> postes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Grade id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Grade code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Grade libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Grade description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAncEchClasse() {
        return this.ancEchClasse;
    }

    public Grade ancEchClasse(String ancEchClasse) {
        this.setAncEchClasse(ancEchClasse);
        return this;
    }

    public void setAncEchClasse(String ancEchClasse) {
        this.ancEchClasse = ancEchClasse;
    }

    public Integer getNbreEchelon() {
        return this.nbreEchelon;
    }

    public Grade nbreEchelon(Integer nbreEchelon) {
        this.setNbreEchelon(nbreEchelon);
        return this;
    }

    public void setNbreEchelon(Integer nbreEchelon) {
        this.nbreEchelon = nbreEchelon;
    }

    public Set<Postes> getPostes() {
        return this.postes;
    }

    public void setPostes(Set<Postes> postes) {
        this.postes = postes;
    }

    public Grade postes(Set<Postes> postes) {
        this.setPostes(postes);
        return this;
    }

    public Grade addPostes(Postes postes) {
        this.postes.add(postes);
        postes.getGrades().add(this);
        return this;
    }

    public Grade removePostes(Postes postes) {
        this.postes.remove(postes);
        postes.getGrades().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Grade)) {
            return false;
        }
        return id != null && id.equals(((Grade) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Grade{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", ancEchClasse='" + getAncEchClasse() + "'" +
            ", nbreEchelon=" + getNbreEchelon() +
            "}";
    }
}
