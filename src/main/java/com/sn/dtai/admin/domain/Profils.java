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
 * A Profils.
 */
@Entity
@Table(name = "profils")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Profils implements Serializable {

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

    @ManyToOne
    private UserExtended userProfil;

    @ManyToMany
    @JoinTable(
        name = "rel_profils__roles",
        joinColumns = @JoinColumn(name = "profils_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "pages", "profils" }, allowSetters = true)
    private Set<Roles> roles = new HashSet<>();

    /* @ManyToMany(mappedBy = "profils")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "profils", "modules" }, allowSetters = true)
    private Set<Roles> roles = new HashSet<>(); */

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Profils id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Profils code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Profils libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Profils description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserExtended getUserProfil() {
        return this.userProfil;
    }

    public void setUserProfil(UserExtended userExtended) {
        this.userProfil = userExtended;
    }

    public Profils userProfil(UserExtended userExtended) {
        this.setUserProfil(userExtended);
        return this;
    }

    public Set<Roles> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Profils roles(Set<Roles> roles) {
        this.setRoles(roles);
        return this;
    }

    public Profils addRoles(Roles roles) {
        this.roles.add(roles);
        //roles.getProfils().add(this);
        return this;
    }

    public Profils removeRoles(Roles roles) {
        this.roles.remove(roles);
        //roles.getProfils().remove(this);
        return this;
    }

    //////////////////////////////////////////////
    /* public Set<Roles> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Roles> roles) {
        if (this.roles != null) {
            this.roles.forEach(i -> i.removeProfils(this));
        }
        if (roles != null) {
            roles.forEach(i -> i.addProfils(this));
        }
        this.roles = roles;
    }

    public Profils roles(Set<Roles> roles) {
        this.setRoles(roles);
        return this;
    }

    public Profils addRoles(Roles roles) {
        this.roles.add(roles);
        roles.getProfils().add(this);
        return this;
    }

    public Profils removeRoles(Roles roles) {
        this.roles.remove(roles);
        roles.getProfils().remove(this);
        return this;
    } */
    ////////////////////////////////////////////////////////

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profils)) {
            return false;
        }
        return id != null && id.equals(((Profils) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Profils{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
