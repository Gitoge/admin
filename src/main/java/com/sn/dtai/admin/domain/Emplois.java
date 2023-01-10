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
 * A Emplois.
 */
@Entity
@Table(name = "emplois")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Emplois implements Serializable {

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

    @Column(name = "taux_at")
    private Integer tauxAt;

    @Column(name = "prime_lie_emploi")
    private String primeLieEmploi;

    @ManyToMany
    @JoinTable(
        name = "rel_emplois__postes",
        joinColumns = @JoinColumn(name = "emplois_id"),
        inverseJoinColumns = @JoinColumn(name = "postes_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "grades", "hierarchies", "corps", "emplois", "assiettes" }, allowSetters = true)
    private Set<Postes> postes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Emplois id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Emplois code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Emplois libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Emplois description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTauxAt() {
        return this.tauxAt;
    }

    public Emplois tauxAt(Integer tauxAt) {
        this.setTauxAt(tauxAt);
        return this;
    }

    public void setTauxAt(Integer tauxAt) {
        this.tauxAt = tauxAt;
    }

    public String getPrimeLieEmploi() {
        return this.primeLieEmploi;
    }

    public Emplois primeLieEmploi(String primeLieEmploi) {
        this.setPrimeLieEmploi(primeLieEmploi);
        return this;
    }

    public void setPrimeLieEmploi(String primeLieEmploi) {
        this.primeLieEmploi = primeLieEmploi;
    }

    public Set<Postes> getPostes() {
        return this.postes;
    }

    public void setPostes(Set<Postes> postes) {
        this.postes = postes;
    }

    public Emplois postes(Set<Postes> postes) {
        this.setPostes(postes);
        return this;
    }

    public Emplois addPostes(Postes postes) {
        this.postes.add(postes);
        postes.getEmplois().add(this);
        return this;
    }

    public Emplois removePostes(Postes postes) {
        this.postes.remove(postes);
        postes.getEmplois().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Emplois)) {
            return false;
        }
        return id != null && id.equals(((Emplois) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Emplois{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", tauxAt=" + getTauxAt() +
            ", primeLieEmploi='" + getPrimeLieEmploi() + "'" +
            "}";
    }
}
