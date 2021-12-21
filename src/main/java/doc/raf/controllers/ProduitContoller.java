package doc.raf.controllers;

import doc.raf.dao.ProduitReposotory;
import doc.raf.entities.Produits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProduitContoller {
    @Autowired
    ProduitReposotory prodRepo;
    @GetMapping(value = "/index")
    public String getAllProduits(Model model){
        List<Produits> produits = prodRepo.findAll();
        model.addAttribute("ListeProduit",produits); // ListeProduit c'est ce qui fait le refferencent avec thymeleaf pour afficher les produits
        return "produit";
    }
}
