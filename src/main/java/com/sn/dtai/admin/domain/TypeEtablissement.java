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
 * A TypeEtablissement.
 */
@Entity
@Table(name = "type_etablissement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeEtablissement implements Serializable {

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

    @OneToMany(mappedBy = "typeEtab")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "directions", "typeEtab", "localite" }, allowSetters = true)
    private Set<Etablissement> etabs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TypeEtablissement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TypeEtablissement code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public TypeEtablissement libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public TypeEtablissement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Etablissement> getEtabs() {
        return this.etabs;
    }

    public void setEtabs(Set<Etablissement> etablissements) {
        if (this.etabs != null) {
            this.etabs.forEach(i -> i.setTypeEtab(null));
        }
        if (etablissements != null) {
            etablissements.forEach(i -> i.setTypeEtab(this));
        }
        this.etabs = etablissements;
    }

    public TypeEtablissement etabs(Set<Etablissement> etablissements) {
        this.setEtabs(etablissements);
        return this;
    }

    public TypeEtablissement addEtab(Etablissement etablissement) {
        this.etabs.add(etablissement);
        etablissement.setTypeEtab(this);
        return this;
    }

    public TypeEtablissement removeEtab(Etablissement etablissement) {
        this.etabs.remove(etablissement);
        etablissement.setTypeEtab(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeEtablissement)) {
            return false;
        }
        return id != null && id.equals(((TypeEtablissement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeEtablissement{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
