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
 * A TypeLocalite.
 */
@Entity
@Table(name = "type_localite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeLocalite implements Serializable {

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

    @OneToMany(mappedBy = "typeLocalite")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "localite", "etabs", "rattachementId", "typeLocalite" }, allowSetters = true)
    private Set<Localite> localites = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TypeLocalite id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TypeLocalite code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public TypeLocalite libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public TypeLocalite description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Localite> getLocalites() {
        return this.localites;
    }

    public void setLocalites(Set<Localite> localites) {
        if (this.localites != null) {
            this.localites.forEach(i -> i.setTypeLocalite(null));
        }
        if (localites != null) {
            localites.forEach(i -> i.setTypeLocalite(this));
        }
        this.localites = localites;
    }

    public TypeLocalite localites(Set<Localite> localites) {
        this.setLocalites(localites);
        return this;
    }

    public TypeLocalite addLocalite(Localite localite) {
        this.localites.add(localite);
        localite.setTypeLocalite(this);
        return this;
    }

    public TypeLocalite removeLocalite(Localite localite) {
        this.localites.remove(localite);
        localite.setTypeLocalite(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeLocalite)) {
            return false;
        }
        return id != null && id.equals(((TypeLocalite) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeLocalite{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
