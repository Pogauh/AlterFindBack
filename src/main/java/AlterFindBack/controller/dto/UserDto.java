package AlterFindBack.controller.dto;

import AlterFindBack.entities.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String password;

    private Type type;


}
