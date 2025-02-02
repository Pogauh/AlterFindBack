package AlterFindBack.service;

import AlterFindBack.controller.dto.SignupResponse;
import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.CandidateDetails;
import AlterFindBack.entities.EmailAlreadyExistsException;
import AlterFindBack.entities.Entreprise;
import AlterFindBack.entities.User;
import AlterFindBack.repositories.CandidateDetailsRepository;
import AlterFindBack.repositories.EntrepriseRepository;
import AlterFindBack.repositories.LoginRepository;
import AlterFindBack.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private CandidateDetailsRepository candidateDetailsRepository;


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'id : " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'id : " + id);
        }
        userRepository.deleteById(id);
    }

    public Long getEntrepriseIdByUserId(Long userId) {
        Entreprise entreprise = entrepriseRepository.findByUserId(userId);
        return (entreprise != null) ? entreprise.getId() : null;
    }

    public Long getCandidatIdByUserId(Long userId) {
        CandidateDetails candidateDetails = candidateDetailsRepository.findByUserId(userId);
        return (candidateDetails != null) ? candidateDetails.getId() : null;
    }



}



