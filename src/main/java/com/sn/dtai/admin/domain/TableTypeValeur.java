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
 * A TableTypeValeur.
 */
@Entity
@Table(name = "table_type_valeur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TableTypeValeur implements Serializable {

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

    @OneToMany(mappedBy = "tabletypevaleur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tabletypevaleur" }, allowSetters = true)
    private Set<TableValeur> tablevaleurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TableTypeValeur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TableTypeValeur code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public TableTypeValeur libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public TableTypeValeur description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TableValeur> getTablevaleurs() {
        return this.tablevaleurs;
    }

    public void setTablevaleurs(Set<TableValeur> tableValeurs) {
        if (this.tablevaleurs != null) {
            this.tablevaleurs.forEach(i -> i.setTabletypevaleur(null));
        }
        if (tableValeurs != null) {
            tableValeurs.forEach(i -> i.setTabletypevaleur(this));
        }
        this.tablevaleurs = tableValeurs;
    }

    public TableTypeValeur tablevaleurs(Set<TableValeur> tableValeurs) {
        this.setTablevaleurs(tableValeurs);
        return this;
    }

    public TableTypeValeur addTablevaleur(TableValeur tableValeur) {
        this.tablevaleurs.add(tableValeur);
        tableValeur.setTabletypevaleur(this);
        return this;
    }

    public TableTypeValeur removeTablevaleur(TableValeur tableValeur) {
        this.tablevaleurs.remove(tableValeur);
        tableValeur.setTabletypevaleur(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableTypeValeur)) {
            return false;
        }
        return id != null && id.equals(((TableTypeValeur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TableTypeValeur{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
