package doc.raf.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import doc.raf.dao.ArbitreRepository;
import doc.raf.entities.Arbitre;

import java.util.List;

@Controller
public class ContArbitre {
    @Autowired
    ArbitreRepository arbiRepory;

    @PostMapping("/arbitres")
    public Arbitre saveArbitre(@RequestBody Arbitre arbitre){
        return arbiRepory.save(arbitre);
    }

    @GetMapping("/arbitre")
    public String getAllArbitre(Model model){
        List<Arbitre> arbitres = arbiRepory.findAll();
        model.addAttribute("lesArbitres",arbitres);
        return "arbitre";
    }

    @PutMapping("/arbitres/{idArbitre}")
    public ResponseEntity<Arbitre> updateArbitre(@RequestBody Arbitre arbitre, @PathVariable Long idArbitre){
        Arbitre arbitre1 = arbiRepory.findById(idArbitre).orElseThrow(()->new RuntimeException("Arbitre inexistant"));
        arbitre1.setNomArbitre(arbitre.getNomArbitre());
        arbitre1.setNationaliteArbitre(arbitre.getNationaliteArbitre());
        final Arbitre updateArbitre = arbiRepory.save(arbitre1);
        return ResponseEntity.ok(updateArbitre);
    }

    @DeleteMapping("/arbitre/delete/{idArbitre}")
    public void deletEquipe(@PathVariable Long idArbitre){
        arbiRepory.deleteById(idArbitre);
    }
}
