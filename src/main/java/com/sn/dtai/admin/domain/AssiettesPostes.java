package com.sn.dtai.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AssiettesPostes.
 */
@Entity
@Table(name = "assiettes_postes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AssiettesPostes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Assiettes assiettes;

    @ManyToOne
    private Postes postes;

    private String operateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AssiettesPostes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assiettes getAssiettes() {
        return this.assiettes;
    }

    public AssiettesPostes assiettes(Assiettes assiettes) {
        this.setAssiettes(assiettes);
        return this;
    }

    public void setAssiettes(Assiettes assiettes) {
        this.assiettes = assiettes;
    }

    public Postes getPostes() {
        return this.postes;
    }

    public AssiettesPostes postes(Postes postes) {
        this.setPostes(postes);
        return this;
    }

    public void setPostes(Postes postes) {
        this.postes = postes;
    }


    public AssiettesPostes operateur(String operateur) {
        this.setOperateur(operateur);
        return this;
    }
    

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssiettesPostes)) {
            return false;
        }
        return id != null && id.equals(((AssiettesPostes) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssiettesPostes{" +
            "id=" + getId() +
            ", assiettes=" + getAssiettes() +
            ", postes=" + getPostes() +
            "}";
    }
}
