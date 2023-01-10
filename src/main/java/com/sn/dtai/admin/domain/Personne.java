package com.sn.dtai.admin.domain;

import com.sn.dtai.admin.domain.enumeration.Sexe;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Personne.
 */
@Entity
@Table(name = "personne", uniqueConstraints = @UniqueConstraint(columnNames = { "login", "email" }))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "email")
    private String email;

    @Column(name = "fonction")
    private String fonction;

    @Column(name = "lieu_naissance")
    private String lieuNaissance;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "sexe")
    private Sexe sexe;

    @Column(name = "jhi_user_id")
    private Long jhiUserId;

    @Column(name = "user_insert_id")
    private Long userInsertId;

    @Column(name = "lastDateUpdate")
    private Instant lastDateUpdate;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "type_piece")
    private String typePiece;

    @Column(name = "numero_piece")
    private String numeroPiece;

    @Column(name = "datePremiereConnexion")
    private Instant datePremiereConnexion;

    @Column(name = "dateDerniereConnexion")
    private Instant dateDerniereConnexion;

    /* @Column(name = "siege")
    private String siege; */

    @ManyToOne
    private Profils profils;

    /* @ManyToOne
    private Roles roles; */

    @OneToOne
    private Etablissement etablissement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Personne id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public Personne dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return this.email;
    }

    public Personne email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFonction() {
        return this.fonction;
    }

    public Personne fonction(String fonction) {
        this.setFonction(fonction);
        return this;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getLieuNaissance() {
        return this.lieuNaissance;
    }

    public Personne lieuNaissance(String lieuNaissance) {
        this.setLieuNaissance(lieuNaissance);
        return this;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getMatricule() {
        return this.matricule;
    }

    public Personne matricule(String matricule) {
        this.setMatricule(matricule);
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return this.nom;
    }

    public Personne nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Personne prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
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

    public String getTelephone() {
        return telephone;
    }

    public Instant getLastDateUpdate() {
        return lastDateUpdate;
    }

    public void setLastDateUpdate(Instant lastDateUpdate) {
        this.lastDateUpdate = lastDateUpdate;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Profils getProfils() {
        return profils;
    }

    public void setProfils(Profils profils) {
        this.profils = profils;
    }

    /* public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    } */

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personne)) {
            return false;
        }
        return id != null && id.equals(((Personne) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personne{" +
            "id=" + getId() +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", email='" + getEmail() + "'" +
            ", fonction='" + getFonction() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", matricule='" + getMatricule() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", jhiUserId='" + getJhiUserId() + "'" +
            "}";
    }
}
