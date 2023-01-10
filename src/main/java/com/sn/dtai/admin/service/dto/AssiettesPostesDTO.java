package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.AssiettesPostes} entity.
 */
public class AssiettesPostesDTO implements Serializable {

    private Long id;

    private AssiettesDTO assiettes;

    private PostesDTO postes;

    private String operateur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public AssiettesDTO getAssiettes() {
        return assiettes;
    }

    public void setAssiettes(AssiettesDTO assiettes) {
        this.assiettes = assiettes;
    }

    public PostesDTO getPostes() {
        return postes;
    }

    public void setPostes(PostesDTO postes) {
        this.postes = postes;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssiettesPostesDTO)) {
            return false;
        }

        AssiettesPostesDTO assiettesPostesDTO = (AssiettesPostesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, assiettesPostesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssiettesPostesDTO{" +
            "id=" + getId() +
            ", assiettes=" + getAssiettes() +
            ", postes=" + getPostes() +
            "}";
    }


}
