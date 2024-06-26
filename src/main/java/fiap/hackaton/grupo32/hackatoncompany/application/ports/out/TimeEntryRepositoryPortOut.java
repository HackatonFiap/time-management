package fiap.hackaton.grupo32.hackatoncompany.application.ports.out;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.TimeEntryDto;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity.TimeEntryEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TimeEntryRepositoryPortOut {
    TimeEntryDto save(TimeEntryDto timeEntryDto);
    TimeEntryDto update(TimeEntryDto timeEntryDto);
    List<TimeEntryDto> findByDateAndUserId(LocalDateTime date, UUID userId);
    List<TimeEntryDto> findOpenByDateAndUserIdAndType(LocalDateTime date, UUID userId, TimeEntriesTypeEnum type);
    List<TimeEntryDto> findClosedByDateAndUserIdAndType(LocalDateTime date, UUID userId, TimeEntriesTypeEnum type);
    List<TimeEntryDto> reportMonth(LocalDateTime startTime, LocalDateTime endTime, UUID userId);

    List<TimeEntryDto> findByUserAndType(UUID userId, TimeEntriesTypeEnum type);

    void update(UUID id, LocalDateTime endTime);
}
