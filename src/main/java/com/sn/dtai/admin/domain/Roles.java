package com.sn.dtai.admin.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Roles.
 */
@Entity
@Table(name = "roles")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Roles implements Serializable {

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

    /*  @ManyToMany(mappedBy = "roles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "roles", "profils" }, allowSetters = true)
    private Set<Pages> pages = new HashSet<>(); */

    @ManyToMany
    @JoinTable(name = "rel_roles__page_action", joinColumns = @JoinColumn(name = "roles_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PageAction> pagesActions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Roles id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Roles code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Roles libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Roles description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Set<Pages> getPages() {
        return this.pages;
    }

     public void setPages(Set<Pages> pages) {
        if (this.pages != null) {
            this.pages.forEach(i -> i.removePages(this));
        }
        if (pages != null) {
            pages.forEach(i -> i.addPages(this));
        }
        this.pages = pages;
    }

    public Roles pages(Set<Pages> pages) {
        this.setPages(pages);
        return this;
    }

    public Roles addPages(Pages pages) {
        this.pages.add(pages);
        // pages.getPages().add(this);
        return this;
    }

    public Roles removePages(Pages pages) {
        this.pages.remove(pages);
        //pages.getPages().remove(this);
        return this;
    } */

    public Set<PageAction> getPagesActions() {
        return this.pagesActions;
    }

    public void setPagesActions(Set<PageAction> pagesActions) {
        this.pagesActions = pagesActions;
    }

    public Roles pagesActions(Set<PageAction> pagesActions) {
        this.setPagesActions(pagesActions);
        return this;
    }

    public Roles addPagesAction(PageAction pagesActions) {
        this.pagesActions.add(pagesActions);
        //actions.getRoles().add(this);
        return this;
    }

    public Roles removePagesActions(PageAction pagesActions) {
        this.pagesActions.remove(pagesActions);
        //actions.getRoles().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Roles)) {
            return false;
        }
        return id != null && id.equals(((Roles) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Roles{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
