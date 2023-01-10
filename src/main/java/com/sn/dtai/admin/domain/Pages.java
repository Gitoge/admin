package com.sn.dtai.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Pages.
 */
@Entity
@Table(name = "pages")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pages implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    /* @Column(name = "router_link")
    private String routerLink; */

    private TableValeur routerLink;

    @Column(name = "ordre")
    private Long ordre;

    @Column(name = "active")
    private Boolean active;

    /* @ManyToMany(mappedBy = "pages")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "pages", "roles" }, allowSetters = true)
    private Set<Actions> actions = new HashSet<>(); */

    @ManyToMany
    @JoinTable(name = "rel_pages__actions", joinColumns = @JoinColumn(name = "pages_id"), inverseJoinColumns = @JoinColumn(name = "actions_id"))
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "actions", "roles" }, allowSetters = true)
    private Set<Actions> actions = new HashSet<>();
/* */
    @ManyToOne
    @JsonIgnoreProperties(value = { "pages" }, allowSetters = true)
    private Modules modules; 

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Pages id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Pages code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Pages libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Pages description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TableValeur getRouterLink() {
        return this.routerLink;
    }

    public Pages routerLink(TableValeur routerLink) {
        this.setRouterLink(routerLink);
        return this;
    }

    public void setRouterLink(TableValeur routerLink) {
        this.routerLink = routerLink;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Pages active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getOrdre() {
        return this.ordre;
    }

    public Pages ordre(Long ordre) {
        this.setOrdre(ordre);
        return this;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }


/*     public Set<Actions> getActions() {
        return this.actions;
    }

    public void setActions(Set<Actions> actions) {
        this.actions = actions;
    }

    public Pages actions(Set<Actions> actions) {
        this.setActions(actions);
        return this;
    }

    public Pages addActions(Actions actions) {
        this.actions.add(actions);
        actions.getPages().add(this);
        return this;
    }

    public Pages removeActions(Actions actions) {
        this.actions.remove(actions);
        actions.getPages().remove(this);
        return this;
    } */

    public Set<Actions> getActions() {
        return this.actions;
    }

    public void setActions(Set<Actions> actions) {
        this.actions = actions;
    }

    public Pages actions(Set<Actions> actions) {
        this.setActions(actions);
        return this;
    }

    public Pages addActions(Actions actions) {
        this.actions.add(actions);
        // actions.getPages().add(this);
        return this;
    }

    public Pages removeActions(Actions actions) {
        this.actions.remove(actions);
        //actions.getPages().remove(this);
        return this;
    }

    public Modules getModules() {
        return this.modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    public Pages modules(Modules modules) {
        this.setModules(modules);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pages)) {
            return false;
        }
        return id != null && id.equals(((Pages) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pages{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", ordre=" + getOrdre() +
            ", active='" + getActive() + "'" +

            "}";
    }
}
