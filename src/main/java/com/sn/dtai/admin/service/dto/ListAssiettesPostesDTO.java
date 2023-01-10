package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.AssiettesPostes} entity.
 */
public class ListAssiettesPostesDTO implements Serializable {

    private Long id;

    private List<AssiettesPostesDTO> assiettesPostesDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   

    public List<AssiettesPostesDTO> getAssiettesPostes() {
        return assiettesPostesDTO;
    }

    public void setAssiettesPostes(List<AssiettesPostesDTO> assiettesPostesDTO) {
        this.assiettesPostesDTO = assiettesPostesDTO;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListAssiettesPostesDTO)) {
            return false;
        }

        ListAssiettesPostesDTO assiettesPostesDTO = (ListAssiettesPostesDTO) o;
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
        return "AssiettesPostesListDTO{" +
            ", assiettesPostesDTO=" + getAssiettesPostes() +
            "}";
    }

}
