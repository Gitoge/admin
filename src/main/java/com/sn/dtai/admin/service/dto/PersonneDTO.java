package com.sn.dtai.admin.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.time.Instant;
import com.sn.dtai.admin.domain.enumeration.Sexe;

/**
 * A DTO for the {@link com.sn.dtai.admin.domain.Personne} entity.
 */
public class PersonneDTO implements Serializable {

    private Long id;

    private String login;

    private String password;

    private String prenom;

    private String nom;

    private LocalDate dateNaissance;

    private String lieuNaissance;

    private String telephone;

    private String typePiece;

    private String numeroPiece;

    private String email;

    private Sexe sexe;

    private Instant datePremiereConnexion;

    private Instant dateDerniereConnexion;

    // private String siege;

    private ProfilsDTO profils;

    // private RolesDTO roles;

    private EtablissementDTO etablissement;

    private Instant lastDateUpdate;

    private Long jhiUserId;

    private Long userInsertId;

    private List<PagesDTO> pages;

    private List<ModulesDTO> modules;


    private List<ActionsDTO> actions;

    private List<PageActionDTO> pagesActions;

    public List<ModulesDTO> getModules() {
        return modules;
    }

    public void setModules(List<ModulesDTO> modules) {
        this.modules = modules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Sexe getSexe() {
        return  sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getPrenom() {
            return prenom;
        }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public String getEmail() {
            return email;
        }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getDatePremiereConnexion() {
        return datePremiereConnexion;
    }

    public void setDatePremiereConnexion(Instant datePremiereConnexion) {
        this.datePremiereConnexion = datePremiereConnexion;
    }

    public Instant getDateDerniereConnexion() {
            return dateDerniereConnexion;
        }

    public void setDateDerniereConnexion(Instant dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

    /* public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    } */

    public ProfilsDTO getProfils() {
                return profils;
            }

    public EtablissementDTO getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(EtablissementDTO etablissement) {
        this.etablissement = etablissement;
    }

    public void setProfils(ProfilsDTO profils) {
        this.profils = profils;
    }

    /* public RolesDTO getRoles() {
        return roles;
    }

    public void setRoles(RolesDTO roles) {
        this.roles = roles;
    } */

    public Instant getLastDateUpdate() {
        return lastDateUpdate;
    }

    public void setLastDateUpdate(Instant lastDateUpdate) {
        this.lastDateUpdate = lastDateUpdate;
    }

    public Long getJhiUserId() {
        return jhiUserId;
    }

    public void setJhiUserId(Long jhiUserId) {
        this.jhiUserId = jhiUserId;
    }

    public Long getUserInsertId() {
        return userInsertId;
    }

    public void setUserInsertId(Long userInsertId) {
        this.userInsertId = userInsertId;
    }

    

@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonneDTO)) {
            return false;
        }

        PersonneDTO personneDTO = (PersonneDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, personneDTO.id);
    }

    public List<PagesDTO> getPages() {
        return pages;
    }

    public void setPages(List<PagesDTO> pages) {
        this.pages = pages;
    }

    public List<ActionsDTO> getActions() {
        return actions;
    }

    public void setActions(List<ActionsDTO> actions) {
        this.actions = actions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonneDTO{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", sexe='" + getSexe() + "'" +
            "}";
    }

    public List<PageActionDTO> getPagesActions() {
        return pagesActions;
    }

    public void setPagesActions(List<PageActionDTO> pagesActions) {
        this.pagesActions = pagesActions;
    }

}
