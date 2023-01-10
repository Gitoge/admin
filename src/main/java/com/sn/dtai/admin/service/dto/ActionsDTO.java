package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Actions} entity.
 */
public class ActionsDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;

    private String description;

    private String actionLink;

    // private Set<PagesDTO> pages = new HashSet<>();

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

    public String getActionLink() {
        return actionLink;
    }

    public void setActionLink(String actionLink) {
        this.actionLink = actionLink;
    }

    /* public Set<PagesDTO> getPages() {
        return pages;
    }

    public void setPages(Set<PagesDTO> pages) {
        this.pages = pages;
    }
 */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionsDTO)) {
            return false;
        }

        ActionsDTO actionsDTO = (ActionsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, actionsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActionsDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", actionLink='" + getActionLink() + "'" +
            // ", pages=" + getPages() +
            "}";
    }
}
