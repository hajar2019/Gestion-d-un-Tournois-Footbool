package doc.raf.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Data
@Entity
public class Produits implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;
    private String DesignationProd;
    private double prixProd;
    private int quantiteProd;

    public Produits(Object o, String pc_lenovo, int i, int i1) {
    }

    public Produits() {
        super();
    }
}

