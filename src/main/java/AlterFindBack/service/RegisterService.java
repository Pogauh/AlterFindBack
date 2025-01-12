package AlterFindBack.service;

import AlterFindBack.controller.dto.SignupResponse;
import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.*;
import AlterFindBack.repositories.EntrepriseRepository;
import AlterFindBack.repositories.UserRepository;
import AlterFindBack.repositories.CandidateDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntrepriseRepository EntrepriseRepository;

    @Autowired
    private CandidateDetailsRepository CandidateDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    public Long registerNewUserAccount(UserDto userDto) {
        User user = new User();
        user.setNom(userDto.getNom());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserType(userDto.getUserType());

        userRepository.save(user);

        if (userDto.getUserType() == UserType.CANDIDATE) {
            CandidateDetails candidateDetails = new CandidateDetails();
            candidateDetails.setUser(user);
            CandidateDetailsRepository.save(candidateDetails);
        } else if (userDto.getUserType() == UserType.COMPANY) {
            Entreprise Entreprise = new Entreprise();
            Entreprise.setUser(user);
            EntrepriseRepository.save(Entreprise);
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



