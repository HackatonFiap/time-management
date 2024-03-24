package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.RolesEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequest(
        @NotBlank(message = "Matricula is required")
        String matricula,
        @NotBlank(message = "Password is required")
        String password,
        @NotNull(message = "Role is required")
        @Enumerated(EnumType.STRING)
        RolesEnum role
) {
}
