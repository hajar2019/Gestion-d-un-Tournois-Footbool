package doc.raf.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Joueur implements Serializable {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJoueur;
    private String nomJoueur;
    private String posteJoueur;

    @JsonBackReference(value = "joueur-equipe")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "equipe_id")
    private Equipe equipes;

    public Long getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Long idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getPosteJoueur() {
        return posteJoueur;
    }

    public void setPosteJoueur(String posteJoueur) {
        this.posteJoueur = posteJoueur;
    }

    public Equipe getEquipes() {
        return equipes;
    }

    public void setEquipes(Equipe equipes) {
        this.equipes = equipes;
    }

    public Joueur(Long idJoueur, String nomJoueur, String posteJoueur) {
    }

    public Joueur() {
        super();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idJoueur = " + idJoueur + ", " +
                "nomJoueur = " + nomJoueur + ", " +
                "posteJoueur = " + posteJoueur + ")";
    }
}