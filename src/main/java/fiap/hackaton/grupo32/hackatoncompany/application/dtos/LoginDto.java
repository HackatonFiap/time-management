package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "Matricula is required")
        String matricula,
        @NotBlank(message = "Password is required")
        String password
) {
}
