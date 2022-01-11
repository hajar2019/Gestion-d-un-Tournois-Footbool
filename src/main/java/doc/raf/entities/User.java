package doc.raf.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Entity
@Data
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotEmpty(message = "Le champ Nom est vide!")
    private String nom;
    @NotEmpty(message = "Le champ Prenom est vide!")
    private String prenom;
    @Email(message = "Le champ Email est vide!")
    private String email;
    @NotEmpty(message = "Le champ Password est vide!")
    private String password;
    private String role;
    private boolean active;
    
    public User() {
    }

    public User(Long idUser, String nom, String prenom, String email, String password, String role, boolean active) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    @Override
    public String toString() {
        return "User [active=" + active + ", email=" + email + ", nom=" + nom + ", password=" + password + ", prenom="
                + prenom + ", role=" + role + "]";
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

  




    
    
    
}
