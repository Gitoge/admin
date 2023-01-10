package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Pages} entity.
 */
public class PagesDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;

    private String description;

    private Long ordre;

    private Boolean active;

    private TableValeurDTO routerLink;


    private ModulesDTO modules;

    private Set<ActionsDTO> actions = new HashSet<>();

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

    public Long getOrdre() {
        return ordre;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
        
    public ModulesDTO getModules() {
		return modules;
	}

	public void setModules(ModulesDTO module) {
		this.modules = module;
	}

    public Set<ActionsDTO> getActions() {
        return actions;
    }

    public void setActions(Set<ActionsDTO> actions) {
        this.actions = actions;
    }

    public TableValeurDTO getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(TableValeurDTO routerLink) {
        this.routerLink = routerLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PagesDTO)) {
            return false;
        }

        PagesDTO pagesDTO = (PagesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pagesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PagesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", ordre=" + getOrdre() +
            ", modules=" + getModules() +
            ", actions=" + getActions() +
            ", active='" + getActive() + "'" +
            "}";
    }


}
