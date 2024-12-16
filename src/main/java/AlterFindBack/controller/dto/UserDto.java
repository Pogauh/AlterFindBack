package AlterFindBack.controller.dto;

import lombok.Getter;

public class UserDto {
    // Getters et setters
    @Getter
    private String nom;
    @Getter
    private String prenom;
    private String email;
    private String password;

    // Constructeur par défaut
    public UserDto() {}

    public String getEmail() {
        return email;
    }



}
