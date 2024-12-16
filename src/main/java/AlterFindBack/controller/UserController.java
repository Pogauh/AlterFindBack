package AlterFindBack.controller;


import AlterFindBack.entities.User;
import AlterFindBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }
    @GetMapping(value = "/{id}")
    public User getById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        this.userService.saveUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        this.userService.deleteUser(id);
    }

}
