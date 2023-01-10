package com.sn.dtai.admin.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A StructureAdmin.
 */
@Entity
@Table(name = "structure_admin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StructureAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code",unique = true)
    private String code;

    @Column(name = "libelle",unique = true)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "direction")
    private String direction;

    @Column(name = "departement_service")
    private String departementService;

    @Column(name = "adresse")
    private String adresse;

    @OneToOne
    @JoinColumn(unique = true)
    private UserExtended userAdminStructure;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public StructureAdmin id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public StructureAdmin code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public StructureAdmin libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public StructureAdmin description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirection() {
        return this.direction;
    }

    public StructureAdmin direction(String direction) {
        this.setDirection(direction);
        return this;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDepartementService() {
        return this.departementService;
    }

    public StructureAdmin departementService(String departementService) {
        this.setDepartementService(departementService);
        return this;
    }

    public void setDepartementService(String departementService) {
        this.departementService = departementService;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public StructureAdmin adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public UserExtended getUserAdminStructure() {
        return this.userAdminStructure;
    }

    public void setUserAdminStructure(UserExtended userExtended) {
        this.userAdminStructure = userExtended;
    }

    public StructureAdmin userAdminStructure(UserExtended userExtended) {
        this.setUserAdminStructure(userExtended);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StructureAdmin)) {
            return false;
        }
        return id != null && id.equals(((StructureAdmin) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StructureAdmin{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", direction='" + getDirection() + "'" +
            ", departementService='" + getDepartementService() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
}
