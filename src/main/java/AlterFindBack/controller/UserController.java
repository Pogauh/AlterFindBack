package AlterFindBack.controller;


import AlterFindBack.controller.dto.LoginRequest;
import AlterFindBack.controller.dto.LoginResponse;
import AlterFindBack.controller.dto.SignupResponse;
import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.EmailAlreadyExistsException;
import AlterFindBack.entities.User;
import AlterFindBack.service.LoginService;
import AlterFindBack.service.RegisterService;
import AlterFindBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService RegisterService;

    @Autowired
    private LoginService LoginService;


    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Utilisateur supprimé avec succès.");
    }

    @PostMapping("/register")
    public ResponseEntity<SignupResponse> registerUser(@RequestBody UserDto userDto) {
        try {
            Long userId = RegisterService.registerNewUserAccount(userDto);
            SignupResponse response = new SignupResponse();
            response.setUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EmailAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest request) {
        Long userId = LoginService.doLogin(request);

        if (userId != null) {
            // Authentification réussie, créer la réponse avec l'ID utilisateur
            LoginResponse response = new LoginResponse();
            response.setId(userId.toString());
            response.setToken("Token_details"); // Remplacez par la génération réelle du token
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Authentification échouée, retourner une erreur
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


}

