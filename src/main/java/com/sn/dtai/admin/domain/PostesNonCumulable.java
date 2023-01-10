package com.sn.dtai.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PostesNonCumulable.
 */
@Entity
@Table(name = "postes_non_cumulable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PostesNonCumulable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code_poste_1")
    private String codePoste1;

    @Column(name = "code_poste_2")
    private String codePoste2;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PostesNonCumulable id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePoste1() {
        return this.codePoste1;
    }

    public PostesNonCumulable codePoste1(String codePoste1) {
        this.setCodePoste1(codePoste1);
        return this;
    }

    public void setCodePoste1(String codePoste1) {
        this.codePoste1 = codePoste1;
    }

    public String getCodePoste2() {
        return this.codePoste2;
    }

    public PostesNonCumulable codePoste2(String codePoste2) {
        this.setCodePoste2(codePoste2);
        return this;
    }

    public void setCodePoste2(String codePoste2) {
        this.codePoste2 = codePoste2;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostesNonCumulable)) {
            return false;
        }
        return id != null && id.equals(((PostesNonCumulable) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostesNonCumulable{" +
            "id=" + getId() +
            ", codePoste1='" + getCodePoste1() + "'" +
            ", codePoste2='" + getCodePoste2() + "'" +
            "}";
    }
}
