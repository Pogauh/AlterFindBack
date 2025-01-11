package AlterFindBack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import AlterFindBack.controller.dto.LoginRequest;

import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.repositories.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long doLogin(LoginRequest request) {
        Optional<UserDto> userOptional = loginRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                // Authentification réussie, retourner l'ID de l'utilisateur
                return user.getId();
            }
        }
        return null;
    }

}