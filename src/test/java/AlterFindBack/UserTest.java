package AlterFindBack;

import AlterFindBack.entities.Type;
import AlterFindBack.repositories.UserRepository;
import AlterFindBack.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import AlterFindBack.entities.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService();
        userService.userRepository = userRepository;
    }

    @Test
    public void shouldReturnUser_whenIdIsFound() {
        // Arrange
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setEmail("Test@gmail.com");
        mockUser.setNom("Dupres");
        mockUser.setPrenom("Michel");
        mockUser.setUserType(Type.CANDIDATE);


        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals("Test@gmail.com", result.getEmail());
        assertEquals("Dupres", result.getNom());
        assertEquals("Michel", result.getPrenom());
        assertEquals(Type.CANDIDATE, result.getUserType()); // Comparaison correcte


        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void shouldThrowException_whenIdIsNotFound() {
        // Arrange
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserById(userId);
        });
        assertEquals("Utilisateur non trouvé avec l'id : " + userId, exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void shouldReturnAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setEmail("test1@gmail.com");
        user1.setNom("Dupres");
        user1.setPrenom("Michel");
        user1.setUserType(Type.CANDIDATE);
        //------------------------------//
        User user2 = new User();
        user2.setEmail("test2@gmail.com");
        user2.setNom("Dujardin");
        user2.setPrenom("Jean");
        user2.setUserType(Type.COMPANY);

        List<User> mockUsers = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Act
        List<User> result = userService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("test1@gmail.com", result.get(0).getEmail());
        assertEquals("test2@gmail.com", result.get(1).getEmail());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldDeleteUser_whenIdExists() {
        // Arrange
        Long userId = 3L;
        when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
        }

    @Test
    public void shouldThrowException_whenDeletingNonExistentUser() {
        // Arrange
        Long userId = 4L;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(userId);
        });
        assertEquals("Utilisateur non trouvé avec l'id : " + userId, exception.getMessage());
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, never()).deleteById(userId);
    }
}


