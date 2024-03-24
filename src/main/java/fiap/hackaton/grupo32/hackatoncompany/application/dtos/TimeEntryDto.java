package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record TimeEntryDto(
        UUID employeeId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        TimeEntriesTypeEnum entryType
) {
}
