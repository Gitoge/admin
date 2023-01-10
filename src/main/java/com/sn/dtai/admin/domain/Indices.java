package com.sn.dtai.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Indices.
 */
@Entity
@Table(name = "indices")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Indices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "mnt_sf_tenf_01")
    private Integer mntSFTenf01;

    @Column(name = "mnt_sf_tenf_02")
    private Integer mntSFTenf02;

    @Column(name = "solde_indiciaire")
    private Integer soldeIndiciaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Indices id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Indices code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public Indices description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMntSFTenf01() {
        return this.mntSFTenf01;
    }

    public Indices mntSFTenf01(Integer mntSFTenf01) {
        this.setMntSFTenf01(mntSFTenf01);
        return this;
    }

    public void setMntSFTenf01(Integer mntSFTenf01) {
        this.mntSFTenf01 = mntSFTenf01;
    }

    public Integer getMntSFTenf02() {
        return this.mntSFTenf02;
    }

    public Indices mntSFTenf02(Integer mntSFTenf02) {
        this.setMntSFTenf02(mntSFTenf02);
        return this;
    }

    public void setMntSFTenf02(Integer mntSFTenf02) {
        this.mntSFTenf02 = mntSFTenf02;
    }

    public Integer getSoldeIndiciaire() {
        return this.soldeIndiciaire;
    }

    public Indices soldeIndiciaire(Integer soldeIndiciaire) {
        this.setSoldeIndiciaire(soldeIndiciaire);
        return this;
    }

    public void setSoldeIndiciaire(Integer soldeIndiciaire) {
        this.soldeIndiciaire = soldeIndiciaire;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Indices)) {
            return false;
        }
        return id != null && id.equals(((Indices) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Indices{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", mntSFTenf01=" + getMntSFTenf01() +
            ", mntSFTenf02=" + getMntSFTenf02() +
            ", soldeIndiciaire=" + getSoldeIndiciaire() +
            "}";
    }
}
