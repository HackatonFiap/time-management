package fiap.hackaton.grupo32.hackatoncompany.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.UUID;

public record TimeEntryDto(

        UUID id,
        UUID employeeId,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime startTime,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime endTime,
        @Enumerated(EnumType.STRING)
        TimeEntriesTypeEnum entryType
) {
}
