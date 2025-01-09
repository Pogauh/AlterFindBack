package AlterFindBack.controller;


import AlterFindBack.controller.dto.LoginRequest;
import AlterFindBack.controller.dto.LoginResponse;
import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.EmailAlreadyExistsException;
import AlterFindBack.entities.User;
import AlterFindBack.service.UserService;
import AlterFindBack.service.AuthService;
import AlterFindBack.service.LoginService;
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
    private LoginService LoginService;

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        // Supposons que vous avez validé les credentials et obtenu l'id utilisateur
        Long userId = userService.validateCredentials(request.getEmail(), request.getPassword());

        // Générer le token JWT
        String token = authService.authenticateUser(userId);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate")
    public ResponseEntity<Long> validateToken(@RequestParam String token) {
        Long userId = authService.validateToken(token);
        return ResponseEntity.ok(userId);
    }

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
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.registerNewUserAccount(userDto);
            return ResponseEntity.ok("Utilisateur enregistré avec succès !");
        } catch (EmailAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    /*
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setToken("Token_details");

        String result = LoginService.doLogin(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    */


}

