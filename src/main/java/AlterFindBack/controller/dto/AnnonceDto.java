package AlterFindBack.controller.dto;

import AlterFindBack.entities.Entreprise;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnonceDto {

    private Long id;

    private String titre;
    private String description;
    private String localisation;
    private String salaire;
    private Long company_id;
}
