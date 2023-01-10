package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.PostesNonCumulable} entity.
 */
public class PostesNonCumulableDTO implements Serializable {

    private Long id;

    private String codePoste1;

    private String codePoste2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePoste1() {
        return codePoste1;
    }

    public void setCodePoste1(String codePoste1) {
        this.codePoste1 = codePoste1;
    }

    public String getCodePoste2() {
        return codePoste2;
    }

    public void setCodePoste2(String codePoste2) {
        this.codePoste2 = codePoste2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostesNonCumulableDTO)) {
            return false;
        }

        PostesNonCumulableDTO postesNonCumulableDTO = (PostesNonCumulableDTO) o;
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
        return "PostesNonCumulableDTO{" +
            "id=" + getId() +
            ", codePoste1='" + getCodePoste1() + "'" +
            ", codePoste2='" + getCodePoste2() + "'" +
            "}";
    }
}
