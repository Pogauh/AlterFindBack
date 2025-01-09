package AlterFindBack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import AlterFindBack.controller.dto.LoginRequest;
import AlterFindBack.controller.dto.SignupRequest;
import AlterFindBack.controller.dto.SignupResponse;
import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.repositories.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public String doLogin(LoginRequest request) {
        Optional<UserDto> users = loginRepository.findByEmail(request.getEmail());

        if (users.isPresent()) {
            return "User details found";
        }
        return "User details not found";
    }



}