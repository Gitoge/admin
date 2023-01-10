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
 * A TypeDestinataires.
 */
@Entity
@Table(name = "type_destinataires")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeDestinataires implements Serializable {

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

    @OneToMany(mappedBy = "typedestinataires")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "typedestinataires" }, allowSetters = true)
    private Set<Destinataires> destinataires = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TypeDestinataires id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TypeDestinataires code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public TypeDestinataires libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public TypeDestinataires description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Destinataires> getDestinataires() {
        return this.destinataires;
    }

    public void setDestinataires(Set<Destinataires> destinataires) {
        if (this.destinataires != null) {
            this.destinataires.forEach(i -> i.setTypedestinataires(null));
        }
        if (destinataires != null) {
            destinataires.forEach(i -> i.setTypedestinataires(this));
        }
        this.destinataires = destinataires;
    }

    public TypeDestinataires destinataires(Set<Destinataires> destinataires) {
        this.setDestinataires(destinataires);
        return this;
    }

    public TypeDestinataires addDestinataires(Destinataires destinataires) {
        this.destinataires.add(destinataires);
        destinataires.setTypedestinataires(this);
        return this;
    }

    public TypeDestinataires removeDestinataires(Destinataires destinataires) {
        this.destinataires.remove(destinataires);
        destinataires.setTypedestinataires(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeDestinataires)) {
            return false;
        }
        return id != null && id.equals(((TypeDestinataires) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeDestinataires{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
