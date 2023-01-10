package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.PageAction} entity.
 */
public class PageActionDTO implements Serializable {

    private Long id;

    private PagesDTO pages;

    private ActionsDTO actions;

    private Boolean etat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public PagesDTO getPages() {
        return pages;
    }

    public void setPages(PagesDTO pages) {
        this.pages = pages;
    }

    public ActionsDTO getActions() {
        return actions;
    }

    public void setActions(ActionsDTO actions) {
        this.actions = actions;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PageActionDTO)) {
            return false;
        }

        PageActionDTO pageActionDTO = (PageActionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, pageActionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PageActionDTO{" +
            "id=" + getId() +
            ", pages=" + getPages() +
            ", actions=" + getActions() +
            "}";
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
