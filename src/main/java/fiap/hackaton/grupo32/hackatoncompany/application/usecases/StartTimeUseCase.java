package fiap.hackaton.grupo32.hackatoncompany.application.usecases;

import fiap.hackaton.grupo32.hackatoncompany.application.dtos.TimeEntryDto;
import fiap.hackaton.grupo32.hackatoncompany.application.ports.out.TimeEntryRepositoryPortOut;
import fiap.hackaton.grupo32.hackatoncompany.domain.entities.TimeEntry;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.mappers.GeneralObjectMapper;

import java.util.List;

public class StartTimeUseCase {

    private final TimeEntryRepositoryPortOut timeEntryRepositoryPortOut;

    private final GeneralObjectMapper mapper;

    public StartTimeUseCase(TimeEntryRepositoryPortOut timeEntryRepositoryPortOut, GeneralObjectMapper mapper) {
        this.timeEntryRepositoryPortOut = timeEntryRepositoryPortOut;
        this.mapper = mapper;
    }

    public TimeEntryDto execute(TimeEntryDto timeEntryDto) throws Exception {

        if (timeEntryDto.entryType().equals(TimeEntriesTypeEnum.WORK)) {
            List<TimeEntryDto> openWorkEntries = timeEntryRepositoryPortOut.findOpenByDateAndUserIdAndType(timeEntryDto.startTime(), timeEntryDto.employeeId(), TimeEntriesTypeEnum.WORK);
            if (!openWorkEntries.isEmpty()) {
                throw new TimeEntryConstraintException("There is already an open WORK time entry for this user on this date.");
            }
        } else {
            List<TimeEntryDto> openEntries = timeEntryRepositoryPortOut.findOpenByDateAndUserIdAndType(timeEntryDto.startTime(), timeEntryDto.employeeId(), timeEntryDto.entryType());
            if (!openEntries.isEmpty()) {
                throw new TimeEntryConstraintException("There is already an open " + timeEntryDto.entryType() + " time entry for this user on this date.");
            }
        }

        return timeEntryRepositoryPortOut.save(timeEntryDto);
    }
}
