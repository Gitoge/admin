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
 * A Modules.
 */
@Entity
@Table(name = "modules")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Modules implements Serializable {

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

    /* @Column(name = "icon")
    private String icon; */

    @Column(name = "ordre")
    private Long ordre;

    /* @Column(name = "router_link")
    private String routerLink; */

    @ManyToOne
    private TableValeur icon;

    @ManyToOne
    private TableValeur routerLink;

    @Column(name = "type")
    private String type;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JsonIgnoreProperties(value = { "modules" }, allowSetters = true)
    private Applications applications;

    /* @ManyToMany(mappedBy = "modules")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "userProfil", "modules", "modules" }, allowSetters = true)
    private Set<Profils> profils = new HashSet<>(); */

    @OneToMany(mappedBy = "modules")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "modules", "roles","actions" }, allowSetters = true)
    private Set<Pages> pages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Modules id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Modules code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Modules libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Modules description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TableValeur getIcon() {
        return this.icon;
    }

    public Modules icon(TableValeur icon) {
        this.setIcon(icon);
        return this;
    }

    public void setIcon(TableValeur icon) {
        this.icon = icon;
    }

    public Long getOrdre() {
        return this.ordre;
    }

    public Modules ordre(Long ordre) {
        this.setOrdre(ordre);
        return this;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    public TableValeur getRouterLink() {
        return this.routerLink;
    }

    public Modules routerLink(TableValeur routerLink) {
        this.setRouterLink(routerLink);
        return this;
    }

    public void setRouterLink(TableValeur routerLink) {
        this.routerLink = routerLink;
    }

    public String getType() {
        return this.type;
    }

    public Modules type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Modules active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Applications getApplications() {
        return this.applications;
    }

    public void setApplications(Applications applications) {
        this.applications = applications;
    }

    public Modules applications(Applications applications) {
        this.setApplications(applications);
        return this;
    }

    /* public Set<Profils> getProfils() {
        return this.profils;
    }

    public void setProfils(Set<Profils> profils) {
        if (this.profils != null) {
            this.profils.forEach(i -> i.removeModules(this));
        }
        if (profils != null) {
            profils.forEach(i -> i.addModules(this));
        }
        this.profils = profils;
    }

    public Modules profils(Set<Profils> profils) {
        this.setProfils(profils);
        return this;
    }

    public Modules addProfils(Profils profils) {
        this.profils.add(profils);
        profils.getModules().add(this);
        return this;
    }

    public Modules removeProfils(Profils profils) {
        this.profils.remove(profils);
        profils.getModules().remove(this);
        return this;
    } */


    public Set<Pages> getPages() {
        return this.pages;
    }

    public void setPages(Set<Pages> pages) {
        /* if (this.pages != null) {
            this.pages.forEach(i -> i.setModules(null));
        }
        if (pages != null) {
            pages.forEach(i -> i.setModules(this));
        } */
        this.pages = pages;
    }

    public Modules pages(Set<Pages> pages) {
        this.setPages(pages);
        return this;
    }

    public Modules addPages(Pages pages) {
        this.pages.add(pages);
        //pages.setModules(this);
        return this;
    }

    public Modules removePages(Pages pages) {
        this.pages.remove(pages);
        //pages.setModules(null);
        return this;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modules)) {
            return false;
        }
        return id != null && id.equals(((Modules) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Modules{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", icon='" + getIcon() + "'" +
            ", ordre=" + getOrdre() +
            ", routerLink='" + getRouterLink() + "'" +
            ", type='" + getType() + "'" +
            ", active='" + getActive() + "'" +
            "}";
    }
}
