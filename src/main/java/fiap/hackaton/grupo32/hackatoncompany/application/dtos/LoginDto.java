package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "CorporateId is required")
        String corporateId,
        @NotBlank(message = "Password is required")
        String password
) {
}
