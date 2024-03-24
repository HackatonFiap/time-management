package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.RolesEnum;

import java.util.Set;

public class UserEntity {

    private String matricula;
    private String password;
    private RolesEnum role;

    public UserEntity(String matricula, String password, RolesEnum role) {
        this.matricula = matricula;
        this.password = password;
        this.role = role;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesEnum getRole() {
        return role;
    }

    public void setRole(RolesEnum role) {
        this.role = role;
    }
}
