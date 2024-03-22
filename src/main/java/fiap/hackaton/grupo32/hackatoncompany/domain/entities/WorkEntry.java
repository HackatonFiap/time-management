package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;

import java.time.LocalDateTime;

public class WorkEntry extends TimeEntry {

    public WorkEntry(Long employeeId, LocalDateTime startTime) {
        super(employeeId, startTime);
    }

    @Override
    public TimeEntriesTypeEnum getType() {
        return TimeEntriesTypeEnum.WORK;
    }
}