package AlterFindBack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Annonces")  // Vous pouvez spécifier le nom de la table si vous le souhaitez
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

//    @ManyToOne
//    @JoinColumn(name = "entreprise_id")
//    private Entreprise entreprise;  // Clé étrangère vers l'entreprise qui a posté l'annonce

    @Column(nullable = false)
    private boolean statut = true;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Entreprise getEntreprise() {
//        return entreprise;
//    }
//
//    public void setEntreprise(Entreprise entreprise) {
//        this.entreprise = entreprise;
//    }
}
