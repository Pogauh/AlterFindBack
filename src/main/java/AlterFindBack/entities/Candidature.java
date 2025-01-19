package AlterFindBack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidature")  // Vous pouvez spécifier le nom de la table si vous le souhaitez
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "candidat_id")  // Clé étrangère vers l'entité Candidat
//    private CandidateDetails candidat;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "annonce_id")  // Clé étrangère vers l'entité Annonce
//    private Annonce annonce;

    @Column(name = "date_postulation")
    private String datePostulation; // Date de postulation, vous pouvez aussi utiliser un type `Date` ou `LocalDate`

    // Autres attributs (par exemple, statut de la candidature, message du candidat, etc.)

}
