package AlterFindBack.service;

import AlterFindBack.controller.dto.SignupResponse;
import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.EmailAlreadyExistsException;
import AlterFindBack.entities.User;
import AlterFindBack.repositories.LoginRepository;
import AlterFindBack.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Long validateCredentials(String email, String password) {
        // Rechercher l'utilisateur par email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'email : " + email));

        // Comparer le mot de passe fourni avec le mot de passe stocké
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Mot de passe incorrect.");
        }

        // Retourner l'ID de l'utilisateur si les informations sont valides
        return user.getId();
    }

    public SignupResponse registerNewUserAccount(UserDto userDto) {
        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Un compte avec cet e-mail existe déjà : " + userDto.getEmail());
        }
        SignupResponse response = new SignupResponse();


        // Créer un nouvel utilisateur
        User user = new User();
        user.setNom(userDto.getNom());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Sauvegarder l'utilisateur en base
        userRepository.save(user);
        response.setResponse("User created with id " + user.getId());

        return response;
    }

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





    /*
    v0.1
    public SignupResponse doRegister(SignupRequest request) {
        Optional<UserDto> users = loginRepository.findByEmail(request.getUsername());

        SignupResponse response = new SignupResponse();

        if (users.isPresent()) {
            response.setResponse("User details Already found");
            return response;
        }

        UserDto user = new UserDto();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setUsername(request.getUsername());
        user.setEmail(request.getAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        loginRepository.save(user);

        response.setResponse("User created with id " + user.getId());

        return response;
    }
    */
}



