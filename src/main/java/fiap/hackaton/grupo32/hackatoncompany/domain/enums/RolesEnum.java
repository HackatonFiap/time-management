package fiap.hackaton.grupo32.hackatoncompany.domain.enums;

public enum RolesEnum {

    ADMIN(1L),
    USER(2L);

    Long id;

    RolesEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
