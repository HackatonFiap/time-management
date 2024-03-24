package fiap.hackaton.grupo32.hackatoncompany.application.usecases;

import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPort;
import fiap.hackaton.grupo32.hackatoncompany.domain.dto.TimeEntryDto;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entities.TimeEntryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartTimeUseCase {

    private final TimeEntryRepositoryPort timeEntryRepository;

    public StartTimeUseCase(TimeEntryRepositoryPort timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public TimeEntryDto execute(TimeEntryDto timeEntryDto) {

        TimeEntry timeEntry = convertDtoToTimeEntry(timeEntryDto);

        if (timeEntry.getType().equals(TimeEntriesTypeEnum.WORK)) {
            List<TimeEntryEntity> openWorkEntries = timeEntryRepository.findOpenByDateAndUserIdAndType(timeEntry.getStartTime(), timeEntry.getEmployeeId(), TimeEntriesTypeEnum.WORK);
            if (!openWorkEntries.isEmpty()) {
                throw new TimeEntryConstraintException("There is already an open WORK time entry for this user on this date.");
            }
        } else {
            List<TimeEntryEntity> openEntries = timeEntryRepository.findOpenByDateAndUserIdAndType(timeEntry.getStartTime(), timeEntry.getEmployeeId(), timeEntry.getType());
            if (!openEntries.isEmpty()) {
                throw new TimeEntryConstraintException("There is already an open " + timeEntry.getType() + " time entry for this user on this date.");
            }
        }

        TimeEntryEntity timeEntries = timeEntryRepository.save(convertToTimeEntryEntity(timeEntry));
        return convertEntityToDto(timeEntries);
    }

    public TimeEntry convertToTimeEntry(TimeEntryEntity timeEntryEntity) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setId(timeEntryEntity.getId());
        timeEntry.setStartTime(timeEntryEntity.getStartTime());
        timeEntry.setEndTime(timeEntryEntity.getEndTime());
        timeEntry.setType(timeEntryEntity.getType());
        timeEntry.setEmployeeId(timeEntryEntity.getEmployeeId());
        return timeEntry;
    }

    public TimeEntryEntity convertToTimeEntryEntity(TimeEntry timeEntry) {
        TimeEntryEntity timeEntryEntity = new TimeEntryEntity();
        timeEntryEntity.setId(timeEntry.getId());
        timeEntryEntity.setStartTime(timeEntry.getStartTime());
        timeEntryEntity.setEndTime(timeEntry.getEndTime());
        timeEntryEntity.setType(timeEntry.getType());
        timeEntryEntity.setEmployeeId(timeEntry.getEmployeeId());
        return timeEntryEntity;
    }

    public TimeEntry convertDtoToTimeEntry(TimeEntryDto timeEntryDto) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setEmployeeId(timeEntryDto.employeeId());
        timeEntry.setStartTime(timeEntryDto.startTime());
        timeEntry.setEndTime(timeEntryDto.endTime());
        timeEntry.setType(timeEntryDto.entryType());
        return timeEntry;
    }
    public TimeEntryDto convertTimeEntryToDto(TimeEntryEntity timeEntry) {
        return new TimeEntryDto(
                timeEntry.getEmployeeId(),
                timeEntry.getStartTime(),
                timeEntry.getEndTime(),
                timeEntry.getType()
        );
    }
    public TimeEntryDto convertEntityToDto(TimeEntryEntity timeEntryEntity) {
        return new TimeEntryDto(
                timeEntryEntity.getEmployeeId(),
                timeEntryEntity.getStartTime(),
                timeEntryEntity.getEndTime(),
                timeEntryEntity.getType()
        );
    }
}
