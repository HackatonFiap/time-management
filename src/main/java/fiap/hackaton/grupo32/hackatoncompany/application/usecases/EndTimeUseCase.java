package fiap.hackaton.grupo32.hackatoncompany.application.usecases;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.TimeEntryDto;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EndTimeUseCase {

    private final TimeEntryRepositoryPortOut timeEntryRepositoryPortOut;

    private final GeneralObjectMapper mapper;

    public EndTimeUseCase(TimeEntryRepositoryPortOut timeEntryRepositoryPortOut, GeneralObjectMapper mapper) {
        this.timeEntryRepositoryPortOut = timeEntryRepositoryPortOut;
        this.mapper = mapper;
    }

    public TimeEntryDto execute(TimeEntryDto timeEntryDto) throws Exception {

        List<TimeEntryDto> openEntries = timeEntryRepositoryPortOut.findClosedByDateAndUserIdAndType(LocalDateTime.now(), timeEntryDto.employeeId(), timeEntryDto.entryType());
        if (!openEntries.isEmpty()) {
            throw new TimeEntryConstraintException("There is already an Closed " + timeEntryDto.entryType() + " time entry for this user on this date.");
        }

        List<TimeEntryDto> timeEntries = timeEntryRepositoryPortOut.findByUserAndType(timeEntryDto.employeeId(), timeEntryDto.entryType());
        var timeEntryEntity = mapper.timeEntryDtoToTimeEntryEntity(timeEntries.getFirst());
        timeEntryEntity.setEndTime(LocalDateTime.now());
        timeEntryRepositoryPortOut.update(timeEntryEntity.getId(), timeEntryEntity.getEndTime());
        return timeEntryDto;
    }
}
