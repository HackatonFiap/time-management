package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.RolesEnum;

import java.util.Set;

public class User {

    private String matricula;
    private String password;

    private RolesEnum role;

    public User(String matricula, String password, RolesEnum role) {
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
