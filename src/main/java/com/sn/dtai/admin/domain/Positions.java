package com.sn.dtai.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Positions.
 */
@Entity
@Table(name = "positions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Positions implements Serializable {

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

    @Column(name = "type_agent")
    private String typeAgent;

    @Column(name = "impact_solde")
    private Integer impactSolde;

    @Column(name = "statut_position")
    private Boolean statutPosition;

    @ManyToOne
    @JsonIgnoreProperties(value = { "positions" }, allowSetters = true)
    private TypePosition typeposition;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Positions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Positions code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Positions libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public Positions description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeAgent() {
        return this.typeAgent;
    }

    public Positions typeAgent(String typeAgent) {
        this.setTypeAgent(typeAgent);
        return this;
    }

    public void setTypeAgent(String typeAgent) {
        this.typeAgent = typeAgent;
    }

    public Integer getImpactSolde() {
        return this.impactSolde;
    }

    public Positions impactSolde(Integer impactSolde) {
        this.setImpactSolde(impactSolde);
        return this;
    }

    public void setImpactSolde(Integer impactSolde) {
        this.impactSolde = impactSolde;
    }

    public Boolean getStatutPosition() {
        return this.statutPosition;
    }

    public Positions statutPosition(Boolean statutPosition) {
        this.setStatutPosition(statutPosition);
        return this;
    }

    public void setStatutPosition(Boolean statutPosition) {
        this.statutPosition = statutPosition;
    }

    public TypePosition getTypeposition() {
        return this.typeposition;
    }

    public void setTypeposition(TypePosition typeposition) {
        this.typeposition = typeposition;
    }

    public Positions typeposition(TypePosition typeposition) {
        this.setTypeposition(typeposition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Positions)) {
            return false;
        }
        return id != null && id.equals(((Positions) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Positions{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", typeAgent='" + getTypeAgent() + "'" +
            ", impactSolde=" + getImpactSolde() +
            ", statutPosition='" + getStatutPosition() + "'" +
            "}";
    }
}
