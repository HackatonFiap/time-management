package fiap.hackaton.grupo32.hackatoncompany.application.ports.out;

import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entities.TimeEntryEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TimeEntryRepositoryPort {
    TimeEntryEntity save(TimeEntryEntity timeEntry);
    List<TimeEntryEntity> findByDateAndUserId(LocalDateTime date, UUID userId);
    List<TimeEntryEntity> findOpenByDateAndUserIdAndType(LocalDateTime date, UUID userId, TimeEntriesTypeEnum type);
}
