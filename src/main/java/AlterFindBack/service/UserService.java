package AlterFindBack.service;

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

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        User user = new User();
        user.setPrenom(user.getPrenom());
        user.setNom(user.getNom());
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());
        return UserRepository.save(user);
    }

    private boolean emailExists(String email) {
        return UserRepository.findByEmail(email) != null;
    }

    public User getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        return UserRepository.getReferenceById(id);
    }

    public List<User> findAll(){
        return UserRepository.findAll();
    }
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        return UserRepository.save(user);

    }
    public void deleteUser(Long id) {
        UserRepository.deleteById(id);
    }
}


