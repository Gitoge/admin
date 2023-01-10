package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Modules} entity.
 */
public class ModulesDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;

    private String description;

    private TableValeurDTO icon;

    private Long ordre;

    private TableValeurDTO routerLink;

    private String type;

    private Boolean active;

    private ApplicationsDTO applications;

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

    public TableValeurDTO getIcon() {
        return icon;
    }

    public void setIcon(TableValeurDTO icon) {
        this.icon = icon;
    }

    public Long getOrdre() {
        return ordre;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    public TableValeurDTO getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(TableValeurDTO routerLink) {
        this.routerLink = routerLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ApplicationsDTO getApplications() {
        return applications;
    }

    public void setApplications(ApplicationsDTO applications) {
        this.applications = applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModulesDTO)) {
            return false;
        }

        ModulesDTO modulesDTO = (ModulesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, modulesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModulesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", icon='" + getIcon() + "'" +
            ", ordre=" + getOrdre() +
            ", routerLink='" + getRouterLink() + "'" +
            ", type='" + getType() + "'" +
            ", active='" + getActive() + "'" +
            ", applications=" + getApplications() +
            "}";
    }
}
