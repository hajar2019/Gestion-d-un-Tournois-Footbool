package doc.raf.dao;

import doc.raf.entities.Produits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitReposotory extends JpaRepository<Produits,Long> {

}
