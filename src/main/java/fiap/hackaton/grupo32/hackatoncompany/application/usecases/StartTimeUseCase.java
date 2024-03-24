package fiap.hackaton.grupo32.hackatoncompany.application.usecases;

import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPort;
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

    public TimeEntry execute(TimeEntry timeEntry) throws Exception {

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
        return convertToTimeEntry(timeEntries);
    }

    public TimeEntry convertToTimeEntry(TimeEntryEntity timeEntryEntity) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setId(timeEntryEntity.getId());
        timeEntry.setStartTime(timeEntryEntity.getStartTime());
        timeEntry.setEndTime(timeEntryEntity.getEndTime());
        timeEntry.setEntryType(timeEntryEntity.getTimeEntriesTypeEnum());
        timeEntry.setEmployeeId(timeEntryEntity.getEmployeeId());
        return timeEntry;
    }

    public TimeEntryEntity convertToTimeEntryEntity(TimeEntry timeEntry) {
        TimeEntryEntity timeEntryEntity = new TimeEntryEntity();
        timeEntryEntity.setId(timeEntry.getId());
        timeEntryEntity.setStartTime(timeEntry.getStartTime());
        timeEntryEntity.setEndTime(timeEntry.getEndTime());
        timeEntryEntity.setTimeEntriesTypeEnum(timeEntry.getType());
        timeEntryEntity.setEmployeeId(timeEntry.getEmployeeId());
        return timeEntryEntity;
    }
}
