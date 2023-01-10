package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Indices} entity.
 */
public class IndicesDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String description;

    private Integer mntSFTenf01;

    private Integer mntSFTenf02;

    private Integer soldeIndiciaire;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMntSFTenf01() {
        return mntSFTenf01;
    }

    public void setMntSFTenf01(Integer mntSFTenf01) {
        this.mntSFTenf01 = mntSFTenf01;
    }

    public Integer getMntSFTenf02() {
        return mntSFTenf02;
    }

    public void setMntSFTenf02(Integer mntSFTenf02) {
        this.mntSFTenf02 = mntSFTenf02;
    }

    public Integer getSoldeIndiciaire() {
        return soldeIndiciaire;
    }

    public void setSoldeIndiciaire(Integer soldeIndiciaire) {
        this.soldeIndiciaire = soldeIndiciaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IndicesDTO)) {
            return false;
        }

        IndicesDTO indicesDTO = (IndicesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, indicesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IndicesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", mntSFTenf01=" + getMntSFTenf01() +
            ", mntSFTenf02=" + getMntSFTenf02() +
            ", soldeIndiciaire=" + getSoldeIndiciaire() +
            "}";
    }
}
