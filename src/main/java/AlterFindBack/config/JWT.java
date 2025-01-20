package AlterFindBack.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // Clé secrète
    private final Key cleSecrete = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Création du token avec le nom, l'id et le rôle
    public String genererToken(String pseudo, Long id, String role) {
        // Durée de validité du token (1 heure)
        long dureeExpiration = 3600000;
        return Jwts.builder()
                // Nom d'utilisateur, id et rôle
                .setSubject(pseudo)
                .claim("id", id)
                .claim("role", role)
                // Date de création du token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + dureeExpiration))
                .signWith(cleSecrete)
                .compact();
    }

    // Extraire le nom d'utilisateur du token
    public String extrairePseudo(String token) {
        return Jwts.parserBuilder()
                // Vérifie la signature du token
                .setSigningKey(cleSecrete)
                .build()
                .parseClaimsJws(token)
                .getBody()
                // Retourne le nom d'utilisateur
                .getSubject();
    }

    // Extraire l'id du token
    public Long extraireId(String token) {
        return Long.parseLong(Jwts.parserBuilder()
                .setSigningKey(cleSecrete)
                .build()
                .parseClaimsJws(token)
                .getBody()
                // Retourne l'id de l'utilisateur
                .get("id", String.class));
    }

    // Extraire le rôle de l'utilisateur du token
    public String extraireRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(cleSecrete)
                .build()
                .parseClaimsJws(token)
                .getBody()
                // Extraire le rôle de l'utilisateur
                .get("role", String.class);
    }

    // Valider le token JWT
    public boolean validerToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(cleSecrete).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // S'il y a une exeption, alors le token n'est pas valide
            return false;
        }
    }
}