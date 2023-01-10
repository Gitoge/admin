package com.sn.dtai.admin.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ParamBaremeImposable.
 */
@Entity
@Table(name = "param_bareme_imposable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamBaremeImposable implements Serializable {

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

    @NotNull
    @Column(name = "salaire_debut", nullable = false)
    private Integer salaireDebut;

    @NotNull
    @Column(name = "salaire_fin", nullable = false)
    private Integer salaireFin;

    @Column(name = "taux_tranche")
    private Integer tauxTranche;

    @Column(name = "taux_cumule")
    private Integer tauxCumule;

    @Column(name = "montant")
    private Integer montant;

    @NotNull
    @Column(name = "date_impact", nullable = false)
    private LocalDate dateImpact;

    @Column(name = "user_id_insert")
    private Integer userIdInsert;

    @Column(name = "userdate_insert")
    private LocalDate userdateInsert;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ParamBaremeImposable id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public ParamBaremeImposable code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public ParamBaremeImposable libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getSalaireDebut() {
        return this.salaireDebut;
    }

    public ParamBaremeImposable salaireDebut(Integer salaireDebut) {
        this.setSalaireDebut(salaireDebut);
        return this;
    }

    public void setSalaireDebut(Integer salaireDebut) {
        this.salaireDebut = salaireDebut;
    }

    public Integer getSalaireFin() {
        return this.salaireFin;
    }

    public ParamBaremeImposable salaireFin(Integer salaireFin) {
        this.setSalaireFin(salaireFin);
        return this;
    }

    public void setSalaireFin(Integer salaireFin) {
        this.salaireFin = salaireFin;
    }

    public Integer getTauxTranche() {
        return this.tauxTranche;
    }

    public ParamBaremeImposable tauxTranche(Integer tauxTranche) {
        this.setTauxTranche(tauxTranche);
        return this;
    }

    public void setTauxTranche(Integer tauxTranche) {
        this.tauxTranche = tauxTranche;
    }

    public Integer getTauxCumule() {
        return this.tauxCumule;
    }

    public ParamBaremeImposable tauxCumule(Integer tauxCumule) {
        this.setTauxCumule(tauxCumule);
        return this;
    }

    public void setTauxCumule(Integer tauxCumule) {
        this.tauxCumule = tauxCumule;
    }

    public Integer getMontant() {
        return this.montant;
    }

    public ParamBaremeImposable montant(Integer montant) {
        this.setMontant(montant);
        return this;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public LocalDate getDateImpact() {
        return this.dateImpact;
    }

    public ParamBaremeImposable dateImpact(LocalDate dateImpact) {
        this.setDateImpact(dateImpact);
        return this;
    }

    public void setDateImpact(LocalDate dateImpact) {
        this.dateImpact = dateImpact;
    }

    public Integer getUserIdInsert() {
        return this.userIdInsert;
    }

    public ParamBaremeImposable userIdInsert(Integer userIdInsert) {
        this.setUserIdInsert(userIdInsert);
        return this;
    }

    public void setUserIdInsert(Integer userIdInsert) {
        this.userIdInsert = userIdInsert;
    }

    public LocalDate getUserdateInsert() {
        return this.userdateInsert;
    }

    public ParamBaremeImposable userdateInsert(LocalDate userdateInsert) {
        this.setUserdateInsert(userdateInsert);
        return this;
    }

    public void setUserdateInsert(LocalDate userdateInsert) {
        this.userdateInsert = userdateInsert;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParamBaremeImposable)) {
            return false;
        }
        return id != null && id.equals(((ParamBaremeImposable) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamBaremeImposable{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", salaireDebut=" + getSalaireDebut() +
            ", salaireFin=" + getSalaireFin() +
            ", tauxTranche=" + getTauxTranche() +
            ", tauxCumule=" + getTauxCumule() +
            ", montant=" + getMontant() +
            ", dateImpact='" + getDateImpact() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
