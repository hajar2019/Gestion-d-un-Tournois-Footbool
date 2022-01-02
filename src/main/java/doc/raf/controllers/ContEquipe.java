package doc.raf.controllers;


import doc.raf.entities.Joueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import doc.raf.dao.EquipeRepository;
import doc.raf.entities.Equipe;

import java.util.List;

@Controller
public class ContEquipe {
    @Autowired
    EquipeRepository equiRepo;

    ///INSERER UN EQUIPE OU AVEC CES JOUEURS DANS LA BASE DES DONNES 0

    @PostMapping("/registerequipe")
    public String saveEquipe( Equipe equipe){
        equiRepo.save(equipe);
        return "redirect:/equipe";
    }

    /// ajouter equipe 0


    @GetMapping(value = "/addequipe")
    public String showFormEquipe(Model model){
        model.addAttribute("equipe",new Equipe());
        return "addEquipe";
    }
    /// RECUPER TOUS LES EQUIPES 0

    @GetMapping(value = "/equipe")
    public String gettAllEquipe(Model model){
        List<Equipe> equipeList = equiRepo.findAll();
        model.addAttribute("lesEquipes",equipeList);
        return "equipe";
    }
        ///######## SUPPRIMER UN EQUIPE PAR SON ID 0

    @GetMapping(value = "/deleteEquipe")
    public String deleteById(Long id) {
        equiRepo.deleteById(id);
        return "redirect:/equipe";
    }

        ///######## Edit UN EQUIPE PAR SON ID 0

    @GetMapping(value = "/editEquipe")
    public String showEditEquipe(Model model, Long id ){
        Equipe equipe = equiRepo.findById(id).get();
        model.addAttribute("equipe",equipe);
        return "equipeEdit";
    }

        ///######## SUPPRIMER UN EQUIPE PAR SON ID



    @DeleteMapping("/equipes/delete/{idEquipe}")
    public void deletEquipe(@PathVariable Long idEquipe){
        equiRepo.deleteById(idEquipe);
    }

    ///MODIFIER UN EQUIPE PAR SON ID

    @PutMapping("/equipes/{idEquipe}")
    public ResponseEntity<Equipe> updateEquipe(@RequestBody Equipe equipe,@PathVariable Long idEquipe){
        Equipe equipe1 = equiRepo.findById(idEquipe).orElseThrow(()->new RuntimeException("L'equipe n'existe pas"));
        equipe1.setNomEquipe(equipe.getNomEquipe());
        equipe1.setPaysEquipe(equipe.getPaysEquipe());
        final Equipe updateEquipe = equiRepo.save(equipe1);
        return ResponseEntity.ok(updateEquipe);
    }

    @GetMapping("/equipe/{pays}")
    public List<Equipe> getEquipeByPays(@PathVariable String pays){
        return equiRepo.findByPaysEquipe(pays);
    }

    @GetMapping("/equipesby/idmatch/{idMatch}")
    public List<Equipe> getAllEquipesByMathId(@PathVariable Long idMatch){
        return equiRepo.getAllEquipeByIdMatch(idMatch);
    }

}
