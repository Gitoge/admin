package com.sn.dtai.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Actions.
 */
@Entity
@Table(name = "actions") //* a changé */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Actions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "libelle", unique = true)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "action_link")
    private String actionLink;

    /* @ManyToMany
    @JoinTable(
        name = "rel_actions__pages",
        joinColumns = @JoinColumn(name = "actions_id"),
        inverseJoinColumns = @JoinColumn(name = "pages_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "actions" }, allowSetters = true)
    private Set<Pages> pages = new HashSet<>(); */

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Actions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Actions code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Actions libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Actions description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionLink() {
        return this.actionLink;
    }

    public Actions actionLink(String actionLink) {
        this.setActionLink(actionLink);
        return this;
    }

    public void setActionLink(String actionLink) {
        this.actionLink = actionLink;
    }

    /* public Set<Pages> getPages() {
        return this.pages;
    }

    public void setPages(Set<Pages> pages) {
        this.pages = pages;
    }

    public Actions pages(Set<Pages> pages)  {
        this.setPages(pages);
        return this;
    }

    public Actions addPages(Pages pages) { 
        this.pages.add(pages);
        pages.getActions().add(this); 
        return this;
    }

    public Actions removePages(Pages pages) { 
        this.pages.remove(pages);
        pages.getActions().remove(this); 
        return this;
    } */

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Actions)) {
            return false;
        }
        return id != null && id.equals(((Actions) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Actions{" + 
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", actionLink='" + getActionLink() + "'" +
            "}";
    }
}
