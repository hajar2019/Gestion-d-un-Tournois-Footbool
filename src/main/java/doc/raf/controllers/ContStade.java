package doc.raf.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import doc.raf.dao.StadeRepository;
import doc.raf.entities.Stade;

import java.util.List;

@RestController
public class ContStade {
    @Autowired
    StadeRepository stadRepo;

    // @PostMapping("stades")
    // public Stade saveStade(@RequestBody State stade){
    //     return stadRepo.save(stade);
    // }

    @GetMapping("/stades")
    public List<Stade> getAllEquipe(){
        return stadRepo.findAll();
    }

    @DeleteMapping("/stades/{idStade}")
    public void deletMatch(@PathVariable Long idStade){
        stadRepo.deleteById(idStade);
    }

    @PutMapping("/stades/{idStade}")
    public ResponseEntity<Stade> updateStade(@RequestBody Stade stade,@PathVariable Long idStade){
        Stade stade1 = stadRepo.findById(idStade).orElseThrow(()->new RuntimeException("Le stade n'existe pas"));
        stade1.setNomStade(stade.getNomStade());
        stade1.setVilleStade(stade.getVilleStade());
        final Stade updateStade = stadRepo.save(stade1);
        return ResponseEntity.ok(updateStade);
    }

    @GetMapping("/stades/match/{idMatch}")
    public Stade getStadeByIdMatch(@PathVariable Long idMatch){
        return stadRepo.getByMatchId(idMatch);
    }
}
