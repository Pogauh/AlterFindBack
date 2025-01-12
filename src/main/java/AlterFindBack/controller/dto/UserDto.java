package AlterFindBack.controller.dto;

import AlterFindBack.entities.CandidateDetails;
import AlterFindBack.entities.Entreprise;
import AlterFindBack.entities.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String prenom;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CandidateDetails candidateDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Entreprise entreprise;

}
