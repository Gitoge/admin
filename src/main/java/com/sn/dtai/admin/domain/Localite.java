package com.sn.dtai.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Localite.
 */
@Entity
@Table(name = "localite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Localite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "est_par_defaut")
    private Boolean estParDefaut;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "niveau")
    private String niveau;

    @Column(name = "ordre")
    private String ordre;

    @Column(name = "version")
    private String version;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TableValeur pays;

    @Column(name = "insert_user_id")
    private Integer insertUserId;

    @Column(name = "insert_date")
    private LocalDate insertDate;

    @ManyToOne
    @JoinColumn(unique = false)
    private Arrondissement arrondissement;

    @ManyToOne
    @JoinColumn(unique = false)
    private Departement departement;

    //@Column(name = "localite_id")
    // @JsonBackReference
    @JsonIgnoreProperties(value = { "localite", "etabs", "rattachementId", "typeLocalite" }, allowSetters = true)
    @ManyToOne
    @JoinColumn(unique = false)
    private Localite localites;

    @OneToMany(mappedBy = "localite")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "directions", "typeEtab", "localite" }, allowSetters = true)
    private Set<Etablissement> etabs = new HashSet<>();

    // @JsonIgnoreProperties(value = { "localite", "etabs", "rattachementId", "typeLocalite" }, allowSetters = true)
    // @OneToMany(mappedBy = "localite")
    // private Localite rattachementId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "localites" }, allowSetters = true)
    private TypeLocalite typeLocalite;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Localite id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Localite code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEstParDefaut() {
        return this.estParDefaut;
    }

    public Localite estParDefaut(Boolean estParDefaut) {
        this.setEstParDefaut(estParDefaut);
        return this;
    }

    public void setEstParDefaut(Boolean estParDefaut) {
        this.estParDefaut = estParDefaut;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Localite libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNiveau() {
        return this.niveau;
    }

    public Localite niveau(String niveau) {
        this.setNiveau(niveau);
        return this;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getOrdre() {
        return this.ordre;
    }

    public Localite ordre(String ordre) {
        this.setOrdre(ordre);
        return this;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public String getVersion() {
        return this.version;
    }

    public Localite version(String version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public TableValeur getPays() {
        return pays;
    }

    public Localite pays(TableValeur tableValeur) {
        this.pays = tableValeur;
        return this;
    }

    public void setPays(TableValeur tableValeur) {
        this.pays = tableValeur;
    }

    public Integer getInsertUserId() {
        return this.insertUserId;
    }

    public Localite insertUserId(Integer insertUserId) {
        this.setInsertUserId(insertUserId);
        return this;
    }

    public void setInsertUserId(Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    public LocalDate getInsertDate() {
        return this.insertDate;
    }

    public Localite insertDate(LocalDate insertDate) {
        this.setInsertDate(insertDate);
        return this;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public Localite getLocalites() {
        return this.localites;
    }

    public void setLocalites(Localite localites) {
        this.localites = localites;
    }

    public Localite localites(Localite localites) {
        this.setLocalites(localites);
        return this;
    }

    public Set<Etablissement> getEtabs() {
        return this.etabs;
    }

    public void setEtabs(Set<Etablissement> etablissements) {
        if (this.etabs != null) {
            this.etabs.forEach(i -> i.setLocalite(null));
        }
        if (etablissements != null) {
            etablissements.forEach(i -> i.setLocalite(this));
        }
        this.etabs = etablissements;
    }

    public Localite etabs(Set<Etablissement> etablissements) {
        this.setEtabs(etablissements);
        return this;
    }

    public Localite addEtab(Etablissement etablissement) {
        this.etabs.add(etablissement);
        etablissement.setLocalite(this);
        return this;
    }

    public Localite removeEtab(Etablissement etablissement) {
        this.etabs.remove(etablissement);
        etablissement.setLocalite(null);
        return this;
    }

    public TypeLocalite getTypeLocalite() {
        return this.typeLocalite;
    }

    public void setTypeLocalite(TypeLocalite typeLocalite) {
        this.typeLocalite = typeLocalite;
    }

    public Localite typeLocalite(TypeLocalite typeLocalite) {
        this.setTypeLocalite(typeLocalite);
        return this;
    }

    public Arrondissement getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(Arrondissement arrondissement) {
        this.arrondissement = arrondissement;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Localite)) {
            return false;
        }
        return id != null && id.equals(((Localite) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Localite{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", estParDefaut='" + getEstParDefaut() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", niveau='" + getNiveau() + "'" +
            ", ordre='" + getOrdre() + "'" +
            ", version='" + getVersion() + "'" +
            ", pays=" + getPays() +
            ", insertUserId=" + getInsertUserId() +
            ", insertDate='" + getInsertDate() + "'" +
            "}";
    }
}
