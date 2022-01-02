package doc.raf.controllers;

import doc.raf.dao.ArbitreRepository;
import doc.raf.dao.EquipeRepository;
import doc.raf.dao.MatchRepository;
import doc.raf.dao.StadeRepository;
import doc.raf.entities.Arbitre;
import doc.raf.entities.Equipe;
import doc.raf.entities.Match;
import doc.raf.entities.Stade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ContMatch {
    @Autowired
    MatchRepository matchRepo;
    @Autowired
    ArbitreRepository arbRepo;
    @Autowired
    EquipeRepository equiRepo;
    @Autowired
    StadeRepository stadRepo;

    @PostMapping(path = "/matchs/stade/{idStade}/equipe1/{idEquipe1}/equipe2/{idEquipe2}/arbitre/{idArbitre}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Match saveMatch(@RequestBody Match match, @PathVariable Long idStade,
                           @PathVariable Long idEquipe1,@PathVariable Long idEquipe2,
                           @PathVariable Long idArbitre ){
        Stade stade = stadRepo.findById(idStade).orElseThrow(()->new RuntimeException("stade introuvable") );
        Equipe equipe1 = equiRepo.findById(idEquipe1).orElseThrow(()->new RuntimeException("equipe1 introuvable"));
        Equipe equipe2 = equiRepo.findById(idEquipe2).orElseThrow(()->new RuntimeException("equipe2 introuvable"));
        Arbitre arbitre = arbRepo.findById(idArbitre).orElseThrow(()->new RuntimeException("arbitre introuvable"));
        Match match1 = new Match(idArbitre, null, null, stade, arbitre, null);
        match1.setDateMatch(match.getDateMatch());
        match1.setHeureMatch(match.getHeureMatch());

        match1.setStade(stade);
        match1.setArbitre(arbitre);
        List<Equipe> equipeList= new ArrayList<>();
        equipeList.add(equipe1);
        equipeList.add(equipe2);
        match1.setEquipes(equipeList);

        return matchRepo.save(match1);
    }

    @GetMapping(value = "/home")
    public String getAllMatch(Model model){
        List<Match> matchs = matchRepo.findAll();
        model.addAttribute("lesMatchs",matchs);
        String match = matchs.toString();
        model.addAttribute("equipe",match);
        return "home";
    }

    @GetMapping("/matchs")
    public List<Match> getAllMatch() {
        return matchRepo.findAll();
    }

    @DeleteMapping("/match/{idMatch}")
    public void deletMatch(@PathVariable Long idMatch){
        matchRepo.deleteById(idMatch);
    }

    @PutMapping(path = "/matchs={idMatch}/stade={idStade}/arbitre={idArbitre}")
    public ResponseEntity<Match> updateMatch(@RequestBody Match match,@PathVariable Long idMatch,@PathVariable Long idStade,@PathVariable Long idArbitre){
        Match match1 = matchRepo.findById(idMatch).orElseThrow(()->new RuntimeException("le match n'existe pas"));
        Stade stade = stadRepo.findById(idStade).orElseThrow(()->new RuntimeException("Le stade n'est pas encore enregistrer"));
        Arbitre arbitre = arbRepo.findById(idArbitre).orElseThrow(()->new RuntimeException("Arbitre n'existe pas"));

        match1.setDateMatch(match.getDateMatch());
        match1.setHeureMatch(match.getHeureMatch());
        match1.setStade(stade);
        match1.setArbitre(arbitre);
        final Match updateMatch = matchRepo.save(match1);
        return ResponseEntity.ok(updateMatch);
    }

    @GetMapping("/matchs/date/{dateMatch}")
    public List<Match> getAllMathByDate(@PathVariable Date dateMatch){
        return matchRepo.getAllByDateMatchEquals(dateMatch);
    }

    @DeleteMapping("matchs/newdate/{newdate}")
    void deleteAllMatchsBefore(Date newdate){
        matchRepo.deleteMatchesByDateMatchBefore(newdate);
    }
}
