package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.AssiettesPostes} entity.
 */
public class ListPostesDTO implements Serializable {

    private Long id;

    private List<PostesDTO> postesDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<PostesDTO> getPostes() {
        return postesDTO;
    }

    public void setPostes(List<PostesDTO> postesDTO) {
        this.postesDTO = postesDTO;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListPostesDTO)) {
            return false;
        }

        ListPostesDTO assiettesPostesDTO = (ListPostesDTO) o;
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
        return "ListPostesDTO{" +
            ", postes=" + getPostes() +
            "}";
    }

}
