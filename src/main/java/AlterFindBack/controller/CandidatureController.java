package AlterFindBack.controller;

import AlterFindBack.controller.dto.CandidatureDto;
import AlterFindBack.entities.CandidateDetails;
import AlterFindBack.entities.Candidature;
import AlterFindBack.entities.EmailAlreadyExistsException;
import AlterFindBack.entities.Entreprise;
import AlterFindBack.repositories.CandidateDetailsRepository;
import AlterFindBack.service.AnnonceService;
import AlterFindBack.service.CandidatureService;
import AlterFindBack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CandidatureController {

    @Autowired
    private UserService userService;
    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private CandidatureService candidatureService;

    @GetMapping("/candidat/{candidatId}")
    public ResponseEntity<List<Candidature>> getCandidaturesByCandidatId(@PathVariable Long candidatId) {

        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("candidatId: {}", candidatId);

        List<Candidature> candidatures = candidatureService.getCandidaturesByCandidatId(candidatId);
        return ResponseEntity.ok(candidatures);
    }

    @PostMapping("/postuler")
    public ResponseEntity postuler(@RequestBody CandidatureDto candidatureDto) {

        Logger logger = LoggerFactory.getLogger(getClass());

        if(candidatureDto!=null) {

            Long candidateID = userService.getCandidatIdByUserId(candidatureDto.getCandidate_id());
            candidatureDto.setCandidate_id(candidateID);

            logger.info("Annonce ID : {}", candidatureDto.getAnnonce_id());
            logger.info("Candidat ID reçu : {}", candidatureDto.getCandidate_id());
            logger.info("Candidat LM : {}", candidatureDto.getLettreMotivation());

            annonceService.postulerOffre(candidatureDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la candidature");
        }
    }

}
