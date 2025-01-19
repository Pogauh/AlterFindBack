package AlterFindBack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "candidate_details")
public class CandidateDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonBackReference // Évite la sérialisation infinie sur le côté "back" de la relation
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
