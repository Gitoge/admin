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
 * A Etablissement.
 */
@Entity
@Table(name = "etablissement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Etablissement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false,unique = true)
    private String code;

    @NotNull
    @Column(name = "sigle", nullable = false,unique = true)
    private String sigle;

    @NotNull
    @Column(name = "libelleLong", nullable = false,unique = true)
    private String libelleLong;

    @NotNull
    @Column(name = "libelleCourt", nullable = false,unique = true)
    private String libelleCourt;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "num_telephone")
    private String numTelephone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "bp")
    private String bp;

    @Column(name = "conventionId")
    private Long conventionId;

    @OneToMany(mappedBy = "etab")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "services", "etab" }, allowSetters = true)
    private Set<Direction> directions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "etabs" }, allowSetters = true)
    private TypeEtablissement typeEtab;

    @ManyToOne
    @JsonIgnoreProperties(value = { "localite", "etabs", "rattachementId", "typeLocalite" }, allowSetters = true)
    private Localite localite;

    @Column(name = "libelle_convention")
    private String libelleConvention;

    @ManyToMany
    @JoinTable(
        name = "rel_etablissement__postes",
        joinColumns = @JoinColumn(name = "etablissement_id"),
        inverseJoinColumns = @JoinColumn(name = "postes_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "grades", "hierarchies", "corps", "etablissement", "assiettes" }, allowSetters = true)
    private Set<Postes> postes = new HashSet<>();

    @Column(name = "nombre_heure_convention")
    private Integer nombreHeure;


    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Etablissement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Etablissement code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSigle() {
        return this.sigle;
    }

    public Etablissement sigle(String sigle) {
        this.setSigle(sigle);
        return this;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getLibelleLong() {
        return this.libelleLong;
    }

    public Etablissement libelleLong(String libelleLong) {
        this.setLibelleLong(libelleLong);
        return this;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }


    public String getLibelleCourt() {
        return this.libelleCourt;
    }

    public Etablissement libelleCourt(String libelleCourt) {
        this.setLibelleCourt(libelleCourt);
        return this;
    }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Etablissement adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTelephone() {
        return this.numTelephone;
    }

    public Etablissement numTelephone(String numTelephone) {
        this.setNumTelephone(numTelephone);
        return this;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getFax() {
        return this.fax;
    }

    public Etablissement fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public Etablissement email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBp() {
        return this.bp;
    }

    public Etablissement bp(String bp) {
        this.setBp(bp);
        return this;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Long getConventionId() {
        return this.conventionId;
    }

    public Etablissement conventionId(Long conventionId) {
        this.setConventionId(conventionId);
        return this;
    }

    public void setConventionId(Long conventionId) {
        this.conventionId = conventionId;
    }

    public Set<Direction> getDirections() {
        return this.directions;
    }

    public void setDirections(Set<Direction> directions) {
        if (this.directions != null) {
            this.directions.forEach(i -> i.setEtab(null));
        }
        if (directions != null) {
            directions.forEach(i -> i.setEtab(this));
        }
        this.directions = directions;
    }

    public Etablissement directions(Set<Direction> directions) {
        this.setDirections(directions);
        return this;
    }

    public Etablissement addDirection(Direction direction) {
        this.directions.add(direction);
        direction.setEtab(this);
        return this;
    }

    public Etablissement removeDirection(Direction direction) {
        this.directions.remove(direction);
        direction.setEtab(null);
        return this;
    }

    public TypeEtablissement getTypeEtab() {
        return this.typeEtab;
    }

    public void setTypeEtab(TypeEtablissement typeEtablissement) {
        this.typeEtab = typeEtablissement;
    }

    public Etablissement typeEtab(TypeEtablissement typeEtablissement) {
        this.setTypeEtab(typeEtablissement);
        return this;
    }

    public Localite getLocalite() {
        return this.localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public Etablissement localite(Localite localite) {
        this.setLocalite(localite);
        return this;
    }

    public String getLibelleConvention() {
        return this.libelleConvention;
    }

    public Etablissement libelleConvention(String libelleConvention) {
        this.setLibelleConvention(libelleConvention);
        return this;
    }

    public void setLibelleConvention(String libelleConvention) {
        this.libelleConvention = libelleConvention;
    }

    public Set<Postes> getPostes() {
        return this.postes;
    }

    public void setPostes(Set<Postes> postes) {
        this.postes = postes;
    }

    public Etablissement postes(Set<Postes> postes) {
        this.setPostes(postes);
        return this;
    }

    public Etablissement addPostes(Postes postes) {
        this.postes.add(postes);
        postes.getEtablissement().add(this);
        return this;
    }

    public Etablissement removePostes(Postes postes) {
        this.postes.remove(postes);
        postes.getEmplois().remove(this);
        return this;
    }

    public Integer getNombreHeure() {
        return this.nombreHeure;
    }

    public Etablissement nombreHeure(Integer nombreHeure) {
        this.setNombreHeure(nombreHeure);
        return this;
    }

    public void setNombreHeure(Integer nombreHeure) {
        this.nombreHeure = nombreHeure;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Etablissement)) {
            return false;
        }
        return id != null && id.equals(((Etablissement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Etablissement{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", sigle='" + getSigle() + "'" +
            ", libelleLong='" + getLibelleLong() + "'" +
            ", libelleCourt='" + getLibelleCourt() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", numTelephone='" + getNumTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", bp='" + getBp() + "'" +
            ", conventionId='" + getConventionId() + "'" +
            "}";
    }
}
