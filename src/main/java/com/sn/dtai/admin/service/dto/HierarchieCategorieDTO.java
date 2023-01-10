package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.HierarchieCategorie} entity.
 */
public class HierarchieCategorieDTO implements Serializable {

    private Long id;

    private HierarchieDTO hierarchie;

    private CategoriesDTO categories;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public HierarchieDTO getHierarchie() {
        return hierarchie;
    }

    public void setHierarchie(HierarchieDTO hierarchie) {
        this.hierarchie = hierarchie;
    }

    public CategoriesDTO getCategories() {
        return categories;
    }

    public void setCategories(CategoriesDTO categories) {
        this.categories = categories;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HierarchieCategorieDTO)) {
            return false;
        }

        HierarchieCategorieDTO hierarchieCategoriesDTO = (HierarchieCategorieDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hierarchieCategoriesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HierarchieCategorieDTO{" +
            "id=" + getId() +
            ", hierarchie=" + getHierarchie() +
            ", categorie=" + getCategories() +
            "}";
    }

}
