package AlterFindBack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entreprises")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom_entreprise;
    private String secteur_activite;
    private String description;
    private String adresse;
    private String ville;
    private String code_postal;
    private String pays;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
