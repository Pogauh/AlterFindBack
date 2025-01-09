package AlterFindBack.entities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class Jwt {

    @Value("${jwt.secret.key}")
    private String secret;
    private final long expiration = 86400000;

    // Générer un token avec l'id utilisateur
    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Récupérer l'id utilisateur à partir du token
    public Long extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    // Vérifier si le token est valide
    public boolean isTokenValid(String token) {
        try {
            extractUserId(token); // Vérifie que le token peut être parsé
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
