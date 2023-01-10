package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Localite} entity.
 */
public class LocaliteDTO implements Serializable {

    private Long id;

    private String code;

    private String estParDefaut;

    private String libelle;

    private String niveau;

    private String ordre;

    private String version;

    private Integer paysId;

    private Integer insertUserId;

    private LocalDate insertDate;

    private LocaliteDTO localites;

    private LocaliteDTO localite;

    private ArrondissementDTO arrondissement;

    private DepartementDTO departement;

    private TypeLocaliteDTO typeLocalite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEstParDefaut() {
        return estParDefaut;
    }

    public void setEstParDefaut(String estParDefaut) {
        this.estParDefaut = estParDefaut;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPaysId() {
        return paysId;
    }

    public void setPaysId(Integer paysId) {
        this.paysId = paysId;
    }

    public Integer getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public LocaliteDTO getLocalites() {
        return localites;
    }

    public void setLocalites(LocaliteDTO localites) {
        this.localites = localites;
    }

    // Utiliser uniquement DANS ETABLISSEMENT
    public LocaliteDTO getLocalite() {
        return localite;
    }

    public void setLocalite(LocaliteDTO localite) {
        this.localite = localite;
    }

    public TypeLocaliteDTO getTypeLocalite() {
        return typeLocalite;
    }

    public void setTypeLocalite(TypeLocaliteDTO typeLocalite) {
        this.typeLocalite = typeLocalite;
    }

    public ArrondissementDTO getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(ArrondissementDTO arrondissement) {
        this.arrondissement = arrondissement;
    }

    public DepartementDTO getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementDTO departement) {
        this.departement = departement;
    }

@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocaliteDTO)) {
            return false;
        }

        LocaliteDTO localiteDTO = (LocaliteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, localiteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocaliteDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", estParDefaut='" + getEstParDefaut() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", niveau='" + getNiveau() + "'" +
            ", ordre='" + getOrdre() + "'" +
            ", version='" + getVersion() + "'" +
            ", paysId=" + getPaysId() +
            ", insertUserId=" + getInsertUserId() +
            ", insertDate='" + getInsertDate() + "'" +
            ", localites=" + getLocalites() +
            ", typeLocalite=" + getTypeLocalite() +
            "}";
    }
}
