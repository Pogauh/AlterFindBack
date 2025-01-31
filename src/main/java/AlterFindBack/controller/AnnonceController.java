package AlterFindBack.controller;


import AlterFindBack.entities.Annonce;
import AlterFindBack.entities.User;
import AlterFindBack.service.AnnonceService;
import AlterFindBack.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;


    @GetMapping
    public List<Annonce> getAll() {
        return annonceService.getAllAnnonces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Annonce>> getByAllAnnoncesByUserId(@PathVariable("id") Long id) {
        List<Annonce> annonces = annonceService.getAnnoncesByUserId(id);
        return ResponseEntity.ok(annonces);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteAnnonces(@PathVariable("id") Long id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }


}
