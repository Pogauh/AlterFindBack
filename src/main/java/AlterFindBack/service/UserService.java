package AlterFindBack.service;

import AlterFindBack.controller.dto.UserDto;
import AlterFindBack.entities.User;
import AlterFindBack.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private UserRepository userRepository;

    /*##GET##*/
    public List<User> findAll(){
        return UserRepository.findAll();
    }

    /*##GET-ID##*/
    public User getUserById(Long id) {
        return UserRepository.getUserById(id);
    }

    /*##DELETE##*/
    public void deleteUser(Long id) {
        UserRepository.deleteById(id);
    }

    /*##POST##*/
    public void saveUser(UserDto userDto) {
        // Vérification si l'e-mail existe déjà
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Un compte avec cet e-mail existe déjà : " + userDto.getEmail());
        }
    }


    /*##PATCH##*/



}


