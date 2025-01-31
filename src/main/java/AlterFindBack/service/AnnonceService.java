package AlterFindBack.service;

import AlterFindBack.entities.Annonce;
import AlterFindBack.entities.Entreprise;
import AlterFindBack.entities.User;
import AlterFindBack.repositories.AnnonceRepository;
import AlterFindBack.repositories.EntrepriseRepository;
import AlterFindBack.repositories.UserRepository;
import AlterFindBack.entities.Type;

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
    public EntrepriseRepository entrepriseRepository;

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
    public Annonce createAnnonce(Annonce annonce) {
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

}
