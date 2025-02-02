package AlterFindBack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidatures")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_candidate_details")
    private CandidateDetails candidat;

    @ManyToOne
    @JoinColumn(name = "id_annonce")
    private Annonce annonce;

    private String lettreMotivation;


}
