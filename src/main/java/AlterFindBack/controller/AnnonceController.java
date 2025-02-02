package AlterFindBack.controller;


import AlterFindBack.controller.dto.AnnonceDto;
import AlterFindBack.controller.dto.LoginRequest;
import AlterFindBack.controller.dto.LoginResponse;
import AlterFindBack.entities.Annonce;
import AlterFindBack.service.AnnonceService;
import AlterFindBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private UserService userService;

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

    @PostMapping()
    public ResponseEntity<String> CreateAnnonce(@RequestBody AnnonceDto annonceDto) {


        Logger logger = LoggerFactory.getLogger(getClass());

        // Afficher ce qui est reçu
        logger.info("Annonce reçue : {}", annonceDto);
        logger.info("Company ID reçu : {}", annonceDto.getCompany_id());



        if (annonceDto == null ||
                annonceDto.getTitre() == null || annonceDto.getTitre().isEmpty() ||
                annonceDto.getDescription() == null || annonceDto.getDescription().isEmpty() ||
                annonceDto.getLocalisation() == null || annonceDto.getLocalisation().isEmpty() ||
                annonceDto.getSalaire() == null) {
            return ResponseEntity.badRequest().body("Tous les champs sont obligatoires !");
        }
                Long entrepriseID = userService.getEntrepriseIdByUserId(annonceDto.getCompany_id());
                annonceDto.setCompany_id(entrepriseID);


                annonceService.createAnnonce(annonceDto);
        return new ResponseEntity<>(HttpStatus.OK);

        }
}
