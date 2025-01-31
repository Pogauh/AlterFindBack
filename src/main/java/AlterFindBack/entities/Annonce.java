package AlterFindBack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Annonce")  // Vous pouvez spécifier le nom de la table si vous le souhaitez
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String localisation;
    private String salaire;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Entreprise entreprise;

}
