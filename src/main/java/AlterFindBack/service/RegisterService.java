package AlterFindBack.service;

import AlterFindBack.controller.dto.SignupResponse;
import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.EmailAlreadyExistsException;
import AlterFindBack.entities.User;
import AlterFindBack.repositories.UserRepository;
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
    private PasswordEncoder passwordEncoder;

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



