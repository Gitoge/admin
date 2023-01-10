package com.sn.dtai.admin.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ParamBaremeMinimumFiscal.
 */
@Entity
@Table(name = "param_bareme_minimum_fiscal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamBaremeMinimumFiscal implements Serializable {

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
    @Column(name = "montant_plafond", nullable = false)
    private Integer montantPlafond;

    @NotNull
    @Column(name = "montant_a_prelever", nullable = false)
    private Integer montantAPrelever;

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

    public ParamBaremeMinimumFiscal id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public ParamBaremeMinimumFiscal code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public ParamBaremeMinimumFiscal libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getMontantPlafond() {
        return this.montantPlafond;
    }

    public ParamBaremeMinimumFiscal montantPlafond(Integer montantPlafond) {
        this.setMontantPlafond(montantPlafond);
        return this;
    }

    public void setMontantPlafond(Integer montantPlafond) {
        this.montantPlafond = montantPlafond;
    }

    public Integer getMontantAPrelever() {
        return this.montantAPrelever;
    }

    public ParamBaremeMinimumFiscal montantAPrelever(Integer montantAPrelever) {
        this.setMontantAPrelever(montantAPrelever);
        return this;
    }

    public void setMontantAPrelever(Integer montantAPrelever) {
        this.montantAPrelever = montantAPrelever;
    }

    public LocalDate getDateImpact() {
        return this.dateImpact;
    }

    public ParamBaremeMinimumFiscal dateImpact(LocalDate dateImpact) {
        this.setDateImpact(dateImpact);
        return this;
    }

    public void setDateImpact(LocalDate dateImpact) {
        this.dateImpact = dateImpact;
    }

    public Integer getUserIdInsert() {
        return this.userIdInsert;
    }

    public ParamBaremeMinimumFiscal userIdInsert(Integer userIdInsert) {
        this.setUserIdInsert(userIdInsert);
        return this;
    }

    public void setUserIdInsert(Integer userIdInsert) {
        this.userIdInsert = userIdInsert;
    }

    public LocalDate getUserdateInsert() {
        return this.userdateInsert;
    }

    public ParamBaremeMinimumFiscal userdateInsert(LocalDate userdateInsert) {
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
        if (!(o instanceof ParamBaremeMinimumFiscal)) {
            return false;
        }
        return id != null && id.equals(((ParamBaremeMinimumFiscal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamBaremeMinimumFiscal{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", montantPlafond=" + getMontantPlafond() +
            ", montantAPrelever=" + getMontantAPrelever() +
            ", dateImpact='" + getDateImpact() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
