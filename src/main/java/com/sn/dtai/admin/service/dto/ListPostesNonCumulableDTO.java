package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.PostesNonCumulable} entity.
 */
public class ListPostesNonCumulableDTO implements Serializable {

    private Long id;

    private List<PostesNonCumulableDTO> postesNonCumulableDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   

    public List<PostesNonCumulableDTO> getPostesNonCumulable() {
        return postesNonCumulableDTO;
    }

    public void setPostesNonCumulable(List<PostesNonCumulableDTO> postesNonCumulableDTO) {
        this.postesNonCumulableDTO = postesNonCumulableDTO;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListPostesNonCumulableDTO)) {
            return false;
        }

        ListPostesNonCumulableDTO postesNonCumulableDTO = (ListPostesNonCumulableDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, postesNonCumulableDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostesNonCumulableListDTO{" +
            ", postesNonCumulableDTO=" + getPostesNonCumulable() +
            "}";
    }

}
