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
 * A Postes.
 */
@Entity
@Table(name = "postes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Postes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "sens")
    private Long sens;

    @Column(name = "user_id_insert")
    private Integer userIdInsert;

    @Column(name = "userdate_insert")
    private LocalDate userdateInsert;

    @Column(name = "categorie_elements_id")
    private Long categorieElementsId;

    @Column(name = "frequence_id")
    private Long frequenceId;

    @Column(name = "dans_assiette")
    private Boolean dansAssiette;

    @Column(name = "formule")
    private String formule;

    /* Elements Fixes - Elements Variables */
    @Column(name = "typePoste")
    private String typePoste;

    @Column(name = "etat")
    private Long etat;

    @ManyToMany(mappedBy = "postes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "postes" }, allowSetters = true)
    private Set<Grade> grades = new HashSet<>();

    @ManyToMany(mappedBy = "postes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "postes" }, allowSetters = true)
    private Set<Hierarchie> hierarchies = new HashSet<>();

    @ManyToMany(mappedBy = "postes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "postes" }, allowSetters = true)
    private Set<Corps> corps = new HashSet<>();

    @ManyToMany(mappedBy = "postes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "postes" }, allowSetters = true)
    private Set<Emplois> emplois = new HashSet<>();

    @ManyToMany(mappedBy = "postes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "postes" }, allowSetters = true)
    private Set<Assiettes> assiettes = new HashSet<>();

    @Column(name = "reference")
    private String reference;

    @Column(name = "montant", nullable = true)
    private Integer montant;


    @ManyToMany(mappedBy = "postes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "postes" }, allowSetters = true)
    private Set<Etablissement> etablissement = new HashSet<>();


    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Postes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Postes code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Postes libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Postes description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSens() {
        return sens;
    }

    public Postes sens(Long sens) {
        this.setSens(sens);
        return this;
    }

    public void setSens(Long sens) {
        this.sens = sens;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getCategorieElementsId() {
        return categorieElementsId;
    }

    public Postes categorieElementsId(Long categorieElementsId) {
        this.setCategorieElementsId(categorieElementsId);
        return this;
    }

    public void setCategorieElementsId(Long categorieElementsId) {
        this.categorieElementsId = categorieElementsId;
    }

    public Long getFrequenceId() {
        return frequenceId;
    }

    public Postes frequenceId(Long frequenceId) {
        this.setFrequenceId(frequenceId);
        return this;
    }

    public void setFrequenceId(Long frequenceId) {
        this.frequenceId = frequenceId;
    }

    public Boolean getDansAssiette() {
        return dansAssiette;
    }

    public Postes dansAssiette(Boolean dansAssiette) {
        this.setDansAssiette(dansAssiette);
        return this;
    }

    public void setDansAssiette(Boolean dansAssiette) {
        this.dansAssiette = dansAssiette;
    }

    public String getFormule() {
        return formule;
    }

    public Postes formule(String formule) {
        this.setFormule(formule);
        return this;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public String getTypePoste() {
        return typePoste;
    }

    public Postes typePoste(String typePoste) {
        this.setTypePoste(typePoste);
        return this;
    }

    public void setTypePoste(String typePoste) {
        this.typePoste = typePoste;
    }

    public Integer getUserIdInsert() {
        return this.userIdInsert;
    }

    public Postes userIdInsert(Integer userIdInsert) {
        this.setUserIdInsert(userIdInsert);
        return this;
    }

    public void setUserIdInsert(Integer userIdInsert) {
        this.userIdInsert = userIdInsert;
    }

    public LocalDate getUserdateInsert() {
        return this.userdateInsert;
    }

    public Postes userdateInsert(LocalDate userdateInsert) {
        this.setUserdateInsert(userdateInsert);
        return this;
    }

    public void setUserdateInsert(LocalDate userdateInsert) {
        this.userdateInsert = userdateInsert;
    }

    public Set<Grade> getGrades() {
        return this.grades;
    }

    public void setGrades(Set<Grade> grades) {
        if (this.grades != null) {
            this.grades.forEach(i -> i.removePostes(this));
        }
        if (grades != null) {
            grades.forEach(i -> i.addPostes(this));
        }
        this.grades = grades;
    }

    public Postes grades(Set<Grade> grades) {
        this.setGrades(grades);
        return this;
    }

    public Postes addGrade(Grade grade) {
        this.grades.add(grade);
        grade.getPostes().add(this);
        return this;
    }

    public Postes removeGrade(Grade grade) {
        this.grades.remove(grade);
        grade.getPostes().remove(this);
        return this;
    }

    public Set<Hierarchie> getHierarchies() {
        return this.hierarchies;
    }

    public void setHierarchies(Set<Hierarchie> hierarchies) {
        if (this.hierarchies != null) {
            this.hierarchies.forEach(i -> i.removePostes(this));
        }
        if (hierarchies != null) {
            hierarchies.forEach(i -> i.addPostes(this));
        }
        this.hierarchies = hierarchies;
    }

    public Postes hierarchies(Set<Hierarchie> hierarchies) {
        this.setHierarchies(hierarchies);
        return this;
    }

    public Postes addHierarchie(Hierarchie hierarchie) {
        this.hierarchies.add(hierarchie);
        hierarchie.getPostes().add(this);
        return this;
    }

    public Postes removeHierarchie(Hierarchie hierarchie) {
        this.hierarchies.remove(hierarchie);
        hierarchie.getPostes().remove(this);
        return this;
    }

    public Set<Corps> getCorps() {
        return this.corps;
    }

    public void setCorps(Set<Corps> corps) {
        if (this.corps != null) {
            this.corps.forEach(i -> i.removePostes(this));
        }
        if (corps != null) {
            corps.forEach(i -> i.addPostes(this));
        }
        this.corps = corps;
    }

    public Postes corps(Set<Corps> corps) {
        this.setCorps(corps);
        return this;
    }

    public Postes addCorps(Corps corps) {
        this.corps.add(corps);
        corps.getPostes().add(this);
        return this;
    }

    public Postes removeCorps(Corps corps) {
        this.corps.remove(corps);
        corps.getPostes().remove(this);
        return this;
    }

    public Set<Emplois> getEmplois() {
        return this.emplois;
    }

    public void setEmplois(Set<Emplois> emplois) {
        if (this.emplois != null) {
            this.emplois.forEach(i -> i.removePostes(this));
        }
        if (emplois != null) {
            emplois.forEach(i -> i.addPostes(this));
        }
        this.emplois = emplois;
    }

    public Postes emplois(Set<Emplois> emplois) {
        this.setEmplois(emplois);
        return this;
    }

    public Postes addEmplois(Emplois emplois) {
        this.emplois.add(emplois);
        emplois.getPostes().add(this);
        return this;
    }

    public Postes removeEmplois(Emplois emplois) {
        this.emplois.remove(emplois);
        emplois.getPostes().remove(this);
        return this;
    }

    public Set<Assiettes> getAssiettes() {
        return this.assiettes;
    }

    public void setAssiettes(Set<Assiettes> assiettes) {
        if (this.assiettes != null) {
            this.assiettes.forEach(i -> i.removePostes(this));
        }
        if (assiettes != null) {
            assiettes.forEach(i -> i.addPostes(this));
        }
        this.assiettes = assiettes;
    }

    public Postes assiettes(Set<Assiettes> assiettes) {
        this.setAssiettes(assiettes);
        return this;
    }

    public Postes addAssiettes(Assiettes assiettes) {
        this.assiettes.add(assiettes);
        assiettes.getPostes().add(this);
        return this;
    }

    public Postes removeAssiettes(Assiettes assiettes) {
        this.assiettes.remove(assiettes);
        assiettes.getPostes().remove(this);
        return this;
    }

    public String getReference() {
        return this.reference;
    }

    public Postes reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getMontant() {
        return this.montant;
    }

    public Postes montant(Integer montant) {
        this.setMontant(montant);
        return this;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Set<Etablissement> getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(Set<Etablissement> etablissement) {
        if (this.etablissement != null) {
            this.etablissement.forEach(i -> i.removePostes(this));
        }
        if (etablissement != null) {
            etablissement.forEach(i -> i.addPostes(this));
        }
        this.etablissement = etablissement;
    }

    public Postes etablissement(Set<Etablissement> etablissement) {
        this.setEtablissement(etablissement);
        return this;
    }

    public Postes addEtablissement(Etablissement etablissement) {
        this.etablissement.add(etablissement);
        etablissement.getPostes().add(this);
        return this;
    }

    public Postes removeEtablissement(Etablissement etablissement) {
        this.etablissement.remove(etablissement);
        etablissement.getPostes().remove(this);
        return this;
    }

    
    public Long getEtat() {
        return etat;
    }

    public Postes etat(Long etat) {
        this.setEtat(etat);
        return this;
    }

    public void setEtat(Long etat) {
        this.etat = etat;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Postes)) {
            return false;
        }
        return id != null && id.equals(((Postes) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Postes{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", formule='" + getFormule() + "'" +
            ", dansAssiette='" + getDansAssiette() + "'" +
            ", categorieElementId='" + getCategorieElementsId() +
            ", montant=" + getMontant() +
            ", frequenceId='" + getFrequenceId() +
            ", reference='" + getReference() + "'" +
            ", typePoste=" + getTypePoste() +
            ", sens=" + getSens() +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
