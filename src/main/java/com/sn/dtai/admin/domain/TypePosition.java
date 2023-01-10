package com.sn.dtai.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TypePosition.
 */
@Entity
@Table(name = "type_position")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypePosition implements Serializable {

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

    @Column(name = "user_id_insert")
    private Integer userIdInsert;

    @Column(name = "userdate_insert")
    private LocalDate userdateInsert;

    @OneToMany(mappedBy = "typeposition")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "typeposition" }, allowSetters = true)
    private Set<Positions> positions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TypePosition id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TypePosition code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public TypePosition libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public TypePosition description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserIdInsert() {
        return this.userIdInsert;
    }

    public TypePosition userIdInsert(Integer userIdInsert) {
        this.setUserIdInsert(userIdInsert);
        return this;
    }

    public void setUserIdInsert(Integer userIdInsert) {
        this.userIdInsert = userIdInsert;
    }

    public LocalDate getUserdateInsert() {
        return this.userdateInsert;
    }

    public TypePosition userdateInsert(LocalDate userdateInsert) {
        this.setUserdateInsert(userdateInsert);
        return this;
    }

    public void setUserdateInsert(LocalDate userdateInsert) {
        this.userdateInsert = userdateInsert;
    }

    public Set<Positions> getPositions() {
        return this.positions;
    }

    public void setPositions(Set<Positions> positions) {
        if (this.positions != null) {
            this.positions.forEach(i -> i.setTypeposition(null));
        }
        if (positions != null) {
            positions.forEach(i -> i.setTypeposition(this));
        }
        this.positions = positions;
    }

    public TypePosition positions(Set<Positions> positions) {
        this.setPositions(positions);
        return this;
    }

    public TypePosition addPositions(Positions positions) {
        this.positions.add(positions);
        positions.setTypeposition(this);
        return this;
    }

    public TypePosition removePositions(Positions positions) {
        this.positions.remove(positions);
        positions.setTypeposition(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypePosition)) {
            return false;
        }
        return id != null && id.equals(((TypePosition) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypePosition{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
