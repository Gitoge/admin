package com.sn.dtai.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PostesReferenceActes.
 */
@Entity
@Table(name = "postes_reference_actes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PostesReferenceActes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JsonIgnoreProperties(value = { "postes" }, allowSetters = true)
    private Postes postes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PostesReferenceActes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Postes getPostes() {
        return this.postes;
    }

    public PostesReferenceActes postes(Postes postes) {
        this.setPostes(postes);
        return this;
    }

    public void setPostes(Postes postes) {
        this.postes = postes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostesReferenceActes)) {
            return false;
        }
        return id != null && id.equals(((PostesReferenceActes) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostesReferenceActes{" +
            "id=" + getId() +
            ", postes=" + getPostes() +
            "}";
    }
}
