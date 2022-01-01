package doc.raf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import doc.raf.dao.ProduitReposotory;
import doc.raf.entities.Produits;

import java.util.List;

@Controller
public class ProduitContoller {
    @Autowired
    ProduitReposotory prodRepo;
    @GetMapping(value = "/index")
    public String getAllProduits(Model model,@RequestParam(name = "page",defaultValue = "0") int page,
                                            @RequestParam(name = "motCle",defaultValue = "") String mc){
        Page<Produits> produits = prodRepo.findByDesignationProdContains(mc,PageRequest.of(page, 10));
        model.addAttribute("lesProduits", produits.getContent());
       //ListeProduit c'est ce qui fait le refferencement avec thymeleaf pour afficher les produits TOUT comme ce qui est dans addAttribute
        model.addAttribute("pages", new int[produits.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", mc);
        return "produit";
    }
    @GetMapping(value = "/delete")
    public String delete(Long id,int page,String motCle){
        prodRepo.deleteById(id);
        return "redirect:/index?page="+page+"&motCle="+motCle;
    }
}
