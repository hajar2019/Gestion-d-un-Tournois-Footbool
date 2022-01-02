package doc.raf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import doc.raf.dao.EquipeRepository;
import doc.raf.dao.JoueurRepository;
import doc.raf.entities.Equipe;
import doc.raf.entities.Joueur;

import java.util.List;

@Controller
public class ContJoueur {
    @Autowired
    JoueurRepository joueRepo;
    @Autowired
    EquipeRepository equiRepo;

    //ENREGISTRER UN JOUEUR DANS UN EQUIPE

    @PostMapping("/joueurs/equipe/{idEquipe}")
    public Joueur saveJoueur(@RequestBody Joueur joueur,@PathVariable Long idEquipe){
        Equipe equipe = equiRepo.findById(idEquipe).orElseThrow(()->new RuntimeException("Equipe introuvable"));
        Joueur joueur1 = new Joueur();
        joueur1.setNomJoueur(joueur.getNomJoueur());
        joueur1.setPosteJoueur(joueur.getPosteJoueur());
        joueur1.setEquipes(equipe);

        return joueRepo.save(joueur1);
    }
        //MODIFIER LES INFORMATIONS D'UN JOUEUR

    @PutMapping("/joueurs/{idJoueur}/equipe/{idEquipe}")
    public ResponseEntity<Joueur> updateJoueur(@RequestBody Joueur joueur,@PathVariable Long idJoueur,@PathVariable Long idEquipe){
        Joueur joueur1 = joueRepo.findById(idJoueur).orElseThrow(()->new RuntimeException("Joueur n'existe pas:"));
        Equipe equipe = equiRepo.findById(idEquipe).orElseThrow(()->new RuntimeException("L'equipe n'existe pas!"));

        joueur1.setNomJoueur(joueur.getNomJoueur());
        joueur1.setPosteJoueur(joueur.getPosteJoueur());
        joueur1.setEquipes(equipe);
        final Joueur updateJoueur = joueRepo.save(joueur1);
        return ResponseEntity.ok(updateJoueur);
    }
        /// RECUPERER LES JOUEUR 0

    @GetMapping("/joueur")
    public String AllJoueur(Model model,@RequestParam(name = "page",defaultValue = "0") int page,
                                        @RequestParam(name = "motCle",defaultValue = "") String mc){
        Page<Joueur> pagesJoueurs = joueRepo.findByNomJoueurContains(mc,PageRequest.of(page,6));
        model.addAttribute("lesJoueurs",pagesJoueurs.getContent());
        model.addAttribute("pages",new int[pagesJoueurs.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("motCle",mc);

        return "joueur";
    }
    ////////// SUPPRIMER UN JOUR PAR SON ID 0

    @GetMapping(value = "/delete")
    public String delete(Long id, int page, String motCle) {
        joueRepo.deleteById(id);
        return "redirect:/joueur?page=" + page + "&motCle=" + motCle;
    }
    /// Ajouter un joueur

    @GetMapping(value = "/newjoueur")
    public String showFormJoueur(Model model){
        model.addAttribute("joueur",new Joueur());
        List<Equipe> equipeList = equiRepo.findAll();
        model.addAttribute("listEquipe",equipeList);
        return "addJoueur";
    }

    @PostMapping(value = "/regiterjoueur")
    public String saveJoueur( Joueur joueur){
        joueRepo.save(joueur);
        return "redirect:/joueur";
    }
////Modifier un joueur

    @GetMapping(value = "/editJoueur")
    public String showEditJoueur(Model model, Long id ){
        Joueur joueur = joueRepo.findById(id).get();
        model.addAttribute("joueur",joueur);

        List<Equipe> equipeList = equiRepo.findAll();
        model.addAttribute("lesEquipes",equipeList);
        return "joueurEdit";
    }


    @GetMapping("/joueurs")
    public List<Joueur> getAllJoueur(){
        return joueRepo.findAll();
    }
    ////////// SUPPRIMER UN JOUR PAR SON ID

    @DeleteMapping("/joueurs/delete/{id}")
    public void deletJoueur(@PathVariable Long id){
        joueRepo.deleteById(id);
    }

    @GetMapping("/joueurs/nomequipe/{equipe}")
    public List<Joueur> getAllJoueurEquipe(@PathVariable String equipe){
        return joueRepo.getJoueurByEquipes(equipe);
    }

    @GetMapping("/joueurs/poste/{poste}/equipe/{equipe}")
    public List<Joueur> getAllJoueurEquipe(@PathVariable String poste,@PathVariable String equipe){
        return joueRepo.getJoueurByEquipesAndPoste(poste,equipe);
    }
}

