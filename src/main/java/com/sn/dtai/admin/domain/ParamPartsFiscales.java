package com.sn.dtai.admin.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ParamPartsFiscales.
 */
@Entity
@Table(name = "param_parts_fiscales")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParamPartsFiscales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    
    @Column(name = "libelle")
    private String libelle;

    @NotNull
    @Column(name = "composition", nullable = false)
    private String composition;

    @NotNull
    @Column(name = "nombre_parts", nullable = false)
    private Double nombreParts;

    @Column(name = "description")
    private String description;

    @Column(name = "user_id_insert")
    private Integer userIdInsert;

    @Column(name = "userdate_insert")
    private LocalDate userdateInsert;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ParamPartsFiscales id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public ParamPartsFiscales code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    public String getLibelle() {
        return this.libelle;
    }

    public ParamPartsFiscales libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getComposition() {
        return this.composition;
    }

    public ParamPartsFiscales composition(String composition) {
        this.setComposition(composition);
        return this;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public Double getNombreParts() {
        return this.nombreParts;
    }

    public ParamPartsFiscales nombreParts(Double nombreParts) {
        this.setNombreParts(nombreParts);
        return this;
    }

    public void setNombreParts(Double nombreParts) {
        this.nombreParts = nombreParts;
    }

    public String getDescription() {
        return this.description;
    }

    public ParamPartsFiscales description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserIdInsert() {
        return this.userIdInsert;
    }

    public ParamPartsFiscales userIdInsert(Integer userIdInsert) {
        this.setUserIdInsert(userIdInsert);
        return this;
    }

    public void setUserIdInsert(Integer userIdInsert) {
        this.userIdInsert = userIdInsert;
    }

    public LocalDate getUserdateInsert() {
        return this.userdateInsert;
    }

    public ParamPartsFiscales userdateInsert(LocalDate userdateInsert) {
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
        if (!(o instanceof ParamPartsFiscales)) {
            return false;
        }
        return id != null && id.equals(((ParamPartsFiscales) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParamPartsFiscales{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", composition='" + getComposition() + "'" +
            ", nombreParts=" + getNombreParts() +
            ", description='" + getDescription() + "'" +
            ", userIdInsert=" + getUserIdInsert() +
            ", userdateInsert='" + getUserdateInsert() + "'" +
            "}";
    }
}
