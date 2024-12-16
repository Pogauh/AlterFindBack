package AlterFindBack.controller;


import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.User;
import AlterFindBack.repositories.UserRepository;
import AlterFindBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*##GET##*/
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    /*##GET-ID##*/
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }
    /*##DELETE##*/
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
    }
    /*##POST##*/
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        System.out.println("Nom: " + userDto.getNom());
        System.out.println("Prénom: " + userDto.getPrenom());
        System.out.println("Email: " + userDto.getEmail());
        return ResponseEntity.ok("Utilisateur enregistré !");
        
    }


    /*##PATCH##*/



}
