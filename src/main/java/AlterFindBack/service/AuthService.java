package AlterFindBack.service;

import AlterFindBack.entities.Jwt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final Jwt jwt = new Jwt();

    public String authenticateUser(Long userId) {
        // Générer un token JWT avec l'id utilisateur
        return jwt.generateToken(userId);
    }

    public Long validateToken(String token) {
        if (jwt.isTokenValid(token)) {
            return jwt.extractUserId(token);
        } else {
            throw new RuntimeException("Token invalide ou expiré");
        }
    }
}
