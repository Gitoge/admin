package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.TableValeur} entity.
 */
public class TableValeurDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    private String description;

    @NotNull
    private TableTypeValeurDTO tabletypevaleur;

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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TableTypeValeurDTO getTabletypevaleur() {
        return tabletypevaleur;
    }

    public void setTabletypevaleur(TableTypeValeurDTO tabletypevaleur) {
        this.tabletypevaleur = tabletypevaleur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableValeurDTO)) {
            return false;
        }

        TableValeurDTO tableValeurDTO = (TableValeurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tableValeurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TableValeurDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", tabletypevaleur=" + getTabletypevaleur() +
            "}";
    }
}
