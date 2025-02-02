package AlterFindBack.service;

import AlterFindBack.controller.dto.AnnonceDto;
import AlterFindBack.controller.dto.CandidatureDto;
import AlterFindBack.entities.*;
import AlterFindBack.entities.Candidature;


import AlterFindBack.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    public AnnonceRepository annonceRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CandidaturesRepository candidaturesRepository;

    @Autowired
    public EntrepriseRepository entrepriseRepository;
    @Autowired
    private CandidateDetailsRepository candidateDetailsRepository;


    // Récupérer toutes les annonces
    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    // Récupérer une annonce par ID
    public Annonce getAnnonceById(Long id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce non trouvée avec l'id : " + id));
    }

    // Créer une annonce
    public Annonce createAnnonce(AnnonceDto annonceDto) {

        Entreprise entreprise = entrepriseRepository.findById(annonceDto.getCompany_id())
                .orElseThrow(() -> new IllegalArgumentException("Entreprise non trouvée avec l'ID : " + annonceDto.getCompany_id()));

        Annonce annonce = new Annonce();
        annonce.setTitre(annonceDto.getTitre());
        annonce.setDescription(annonceDto.getDescription());
        annonce.setLocalisation(annonceDto.getLocalisation());
        annonce.setSalaire(annonceDto.getSalaire());
        annonce.setEntreprise(entreprise);


        return annonceRepository.save(annonce);
    }


    // Mettre à jour une annonce
    public Annonce updateAnnonce(Long id, Annonce annonce) {
        if (!annonceRepository.existsById(id)) {
            throw new IllegalArgumentException("Annonce non trouvée avec l'id : " + id);
        }
        annonce.setId(id);
        return annonceRepository.save(annonce);
    }

    // Supprimer une annonce
    public void deleteAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }

    public List<Annonce> getAnnoncesByUserId(Long userId) {
        // Récupérer l'utilisateur
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getType() == Type.COMPANY) {
            // Récupérer l'entreprise liée à cet utilisateur
            Entreprise entreprise = entrepriseRepository.findByUserId(userId);

            // Récupérer toutes les annonces associées à cette entreprise
            return annonceRepository.findByEntrepriseId(entreprise.getId());
        } else {
            throw new RuntimeException("User is not a company");
        }
    }

    public void postulerOffre(CandidatureDto candidatureDto) {
        if (candidatureDto.getAnnonce_id() == null || candidatureDto.getCandidate_id() == null) {
            throw new IllegalArgumentException("L'ID de l'annonce et du candidat ne peuvent pas être null");
        }

        Candidature candidature = new Candidature();
        candidature.setLettreMotivation(candidatureDto.getLettreMotivation());

        Annonce annonce = annonceRepository.findById(candidatureDto.getAnnonce_id())
                .orElseThrow(() -> new RuntimeException("Annonce non trouvée"));

        CandidateDetails candidat = candidateDetailsRepository.findById(candidatureDto.getCandidate_id())
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        candidature.setAnnonce(annonce);
        candidature.setCandidat(candidat);

        candidaturesRepository.save(candidature);
    }



}
