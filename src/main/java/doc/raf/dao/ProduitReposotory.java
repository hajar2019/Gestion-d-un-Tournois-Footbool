package doc.raf.dao;

import doc.raf.entities.Produits;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitReposotory extends JpaRepository<Produits,Long> {
    public Page<Produits> findByDesignationProdContains(String mc,Pageable pageable);
}
