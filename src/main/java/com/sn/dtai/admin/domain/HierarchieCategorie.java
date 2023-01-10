package com.sn.dtai.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HierarchieCategorie.
 */
@Entity
@Table(name = "hierarchie_categorie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HierarchieCategorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Hierarchie hierarchie;

    @ManyToOne
    private Categories categories;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HierarchieCategorie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hierarchie getHierarchie() {
        return this.hierarchie;
    }

    public HierarchieCategorie hierarchie(Hierarchie hierarchie) {
        this.setHierarchie(hierarchie);
        return this;
    }

    public void setHierarchie(Hierarchie hierarchie) {
        this.hierarchie = hierarchie;
    }

    public Categories getCategories() {
        return this.categories;
    }

    public HierarchieCategorie categories(Categories categories) {
        this.setCategories(categories);
        return this;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HierarchieCategorie)) {
            return false;
        }
        return id != null && id.equals(((HierarchieCategorie) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HierarchieCategorie{" +
            "id=" + getId() +
            ", hierarchie=" + getHierarchie() +
            ", categories=" + getCategories() +
            "}";
    }
}
