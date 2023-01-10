package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.paie.domain.PostesReferenceActes} entity.
 */
public class PostesReferenceActesDTO implements Serializable {

    private Long id;

    private PostesDTO postes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostesDTO getPostes() {
        return postes;
    }

    public void setPostes(PostesDTO postes) {
        this.postes = postes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostesReferenceActesDTO)) {
            return false;
        }

        PostesReferenceActesDTO postesReferenceActesDTO = (PostesReferenceActesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, postesReferenceActesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostesReferenceActesDTO{" +
            "id=" + getId() +
            ", postes=" + getPostes() +
            "}";
    }
}
