package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.RolesEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.TimeEntryEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record EmployeeDto(

        UUID id,
        String corporateId,
        String password,
        @Enumerated(EnumType.STRING)
        RolesEnum role,
        List<TimeEntryEntity> timeEntries
) {
}
