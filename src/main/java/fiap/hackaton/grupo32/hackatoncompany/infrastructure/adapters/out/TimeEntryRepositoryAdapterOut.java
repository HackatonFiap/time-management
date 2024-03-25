package fiap.hackaton.grupo32.hackatoncompany.infrastructure.adapters.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import fiap.hackaton.grupo32.hackatoncompany.application.dtos.TimeEntryDto;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.TimeEntryPostgreSqlRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class TimeEntryRepositoryAdapterOut implements TimeEntryRepositoryPortOut {

    private final TimeEntryPostgreSqlRepository repository;
    private final GeneralObjectMapper mapper;

    public TimeEntryRepositoryAdapterOut(TimeEntryPostgreSqlRepository repository, GeneralObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TimeEntryDto save(TimeEntryDto timeEntry) {
        var entity = repository.save(mapper.timeEntryDtoToTimeEntryEntity(timeEntry));
        return mapper.timeEntryEntityToTimeEntryDto(entity);
    }

    @Override
    public TimeEntryDto update(TimeEntryDto timeEntryDto) {
        var entity = repository.save(mapper.timeEntryDtoToTimeEntryEntity(timeEntryDto));
        return mapper.timeEntryEntityToTimeEntryDto(entity);
    }

    @Override
    public List<TimeEntryDto> reportMonth(LocalDateTime startTime, LocalDateTime endTime, UUID userId) {
        var entities = repository.reportMonth(startTime, endTime, userId);
        return entities.stream().map(mapper::timeEntryEntityToTimeEntryDto).toList();
    }

    @Override
    public List<TimeEntryDto> findByDateAndUserId(LocalDateTime date, UUID userId) {
        var entities = repository.findByDateAndEmployeeId(date, userId);
        return entities.stream().map(mapper::timeEntryEntityToTimeEntryDto).toList();
    }

    @Override
    public List<TimeEntryDto> findOpenByDateAndUserIdAndType(LocalDateTime date, UUID userId, TimeEntriesTypeEnum type) {
        var entities = repository.findOpenByDateAndEmployeeIdAndType(date, userId, type);
        return entities.stream().map(mapper::timeEntryEntityToTimeEntryDto).toList();
    }

    @Override
    public List<TimeEntryDto> findClosedByDateAndUserIdAndType(LocalDateTime date, UUID userId, TimeEntriesTypeEnum type) {
        var entities = repository.findOpenByDateAndEmployeeIdAndType(date, userId, type);
        return entities.stream().map(mapper::timeEntryEntityToTimeEntryDto).toList();
    }
}
