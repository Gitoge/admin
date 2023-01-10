package com.sn.dtai.admin.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ParamBulletins.
 */
@Entity
@Table(name = "param_bulletins")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamBulletins implements Serializable {

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

    @Column(name = "entete")
    private String entete;

    @Column(name = "signature")
    private String signature;

    @Column(name = "arriere_plan")
    private String arrierePlan;

    @Column(name = "user_id_insert")
    private Integer userIdInsert;

    @Column(name = "userdate_insert")
    private LocalDate userdateInsert;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ParamBulletins id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public ParamBulletins code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public ParamBulletins libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEntete() {
        return this.entete;
    }

    public ParamBulletins entete(String entete) {
        this.setEntete(entete);
        return this;
    }

    public void setEntete(String entete) {
        this.entete = entete;
    }

    public String getSignature() {
        return this.signature;
    }

    public ParamBulletins signature(String signature) {
        this.setSignature(signature);
        return this;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getArrierePlan() {
        return this.arrierePlan;
    }

    public ParamBulletins arrierePlan(String arrierePlan) {
        this.setArrierePlan(arrierePlan);
        return this;
    }

    public void setArrierePlan(String arrierePlan) {
        this.arrierePlan = arrierePlan;
    }

    public Integer getUserIdInsert() {
        return this.userIdInsert;
    }

    public ParamBulletins userIdInsert(Integer userIdInsert) {
        this.setUserIdInsert(userIdInsert);
        return this;
    }

    public void setUserIdInsert(Integer userIdInsert) {
        this.userIdInsert = userIdInsert;
    }

    public LocalDate getUserdateInsert() {
        return this.userdateInsert;
    }

    public ParamBulletins userdateInsert(LocalDate userdateInsert) {
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
        if (!(o instanceof ParamBulletins)) {
            return false;
        }
        return id != null && id.equals(((ParamBulletins) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamBulletins{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", entete='" + getEntete() + "'" +
            ", signature='" + getSignature() + "'" +
            ", arrierePlan='" + getArrierePlan() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
