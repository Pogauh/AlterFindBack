package AlterFindBack.service;

import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.CandidateDetails;
import AlterFindBack.entities.Entreprise;
import AlterFindBack.entities.User;
import AlterFindBack.entities.Type;
import AlterFindBack.repositories.UserRepository;
import AlterFindBack.repositories.EntrepriseRepository;
import AlterFindBack.repositories.CandidateDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private CandidateDetailsRepository candidateDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Long registerNewUserAccount(UserDto userDto) {
        User user = new User();
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setType(userDto.getType());

        userRepository.save(user);

        if (userDto.getType() == Type.CANDIDATE) {
            CandidateDetails candidateDetails = new CandidateDetails();
            candidateDetails.setUser(user);
            candidateDetailsRepository.save(candidateDetails);
        } else if (userDto.getType() == Type.COMPANY) {
            Entreprise Entreprise = new Entreprise();
            Entreprise.setUser(user);
            entrepriseRepository.save(Entreprise);
        }
        return user.getId();
    }


//    public Long registerNewUserAccount(UserDto userDto) {
//        // Vérifier si l'email existe déjà
//        if (userRepository.existsByEmail(userDto.getEmail())) {
//            throw new EmailAlreadyExistsException("Un compte avec cet e-mail existe déjà : " + userDto.getEmail());
//        }
//
//        // Créer un nouvel utilisateur
//        User user = new User();
//        user.setNom(userDto.getNom());
//        user.setPrenom(userDto.getPrenom());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        // Sauvegarder l'utilisateur en base
//        userRepository.save(user);
//
//        return user.getId();
//    }
}



