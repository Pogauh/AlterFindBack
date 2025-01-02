package AlterFindBack.service;

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
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerNewUserAccount(UserDto userDto) {
        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Un compte avec cet e-mail existe déjà : " + userDto.getEmail());
        }

        // Créer un nouvel utilisateur
        User user = new User();
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Sauvegarder l'utilisateur en base
        userRepository.save(user);
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
}



