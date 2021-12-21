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


    public Produits(Long idProd, String designationProd, double prixProd, int quantiteProd) {
        this.idProd = idProd;
        DesignationProd = designationProd;
        this.prixProd = prixProd;
        this.quantiteProd = quantiteProd;
    }

    public Produits() {
        super();
    }

    @Override
    public String toString() {
        return "Produits{" +
                "idProd=" + idProd +
                ", DesignationProd='" + DesignationProd + '\'' +
                ", prixProd=" + prixProd +
                ", quantiteProd=" + quantiteProd +
                '}';
    }



    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getDesignationProd() {
        return DesignationProd;
    }

    public void setDesignationProd(String designationProd) {
        DesignationProd = designationProd;
    }

    public double getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(double prixProd) {
        this.prixProd = prixProd;
    }

    public int getQuantiteProd() {
        return quantiteProd;
    }

    public void setQuantiteProd(int quantiteProd) {
        this.quantiteProd = quantiteProd;
    }
}

