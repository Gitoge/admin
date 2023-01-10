package com.sn.dtai.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PageAction.
 */
@Entity
@Table(name = "page_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PageAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Pages pages;

    @ManyToOne
    private Actions actions;

    private Boolean etat;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PageAction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pages getPages() {
        return this.pages;
    }

    public PageAction pages(Pages pages) {
        this.setPages(pages);
        return this;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public Actions getActions() {
        return this.actions;
    }

    public PageAction actions(Actions actions) {
        this.setActions(actions);
        return this;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PageAction)) {
            return false;
        }
        return id != null && id.equals(((PageAction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PageAction{" +
            "id=" + getId() +
            ", pages=" + getPages() +
            ", actions=" + getActions() +
            "}";
    }
}
