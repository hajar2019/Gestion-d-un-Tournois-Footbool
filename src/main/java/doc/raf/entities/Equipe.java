package doc.raf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Equipe implements Serializable {
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;
    private String nomEquipe;
    private String paysEquipe;
    @JsonManagedReference("joueur-equipe")
    @OneToMany(mappedBy = "equipes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Joueur> joueurs;

    @JsonIgnore
    @ManyToMany(mappedBy = "equipes",fetch = FetchType.LAZY)
    private List<Match> matches;

    public Equipe() {
        super();
    }

    public Equipe(Long idEquipe, String nomEquipe, String paysEquipe, List<Joueur> joueurs, List<Match> matches) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.paysEquipe = paysEquipe;
        this.joueurs = joueurs;
        this.matches = matches;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getPaysEquipe() {
        return paysEquipe;
    }

    public void setPaysEquipe(String paysEquipe) {
        this.paysEquipe = paysEquipe;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


}
