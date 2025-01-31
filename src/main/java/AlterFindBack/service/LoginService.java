package AlterFindBack.service;

import java.util.Optional;

import AlterFindBack.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import AlterFindBack.controller.dto.LoginRequest;

import AlterFindBack.repositories.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long doLogin(LoginRequest request) {
        Optional<User> userOptional = loginRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                // Authentification réussie, retourner l'ID de l'utilisateur
                return user.getId();
            }
        }
        return null;
    }

}