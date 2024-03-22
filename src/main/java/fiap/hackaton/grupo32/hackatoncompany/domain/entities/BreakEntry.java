package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;

import java.time.LocalDateTime;

public class BreakEntry extends TimeEntry {

    public BreakEntry(Long employeeId, LocalDateTime startTime) {
        super(employeeId, startTime);
    }

    @Override
    public TimeEntriesTypeEnum getType() {
        return TimeEntriesTypeEnum.BREAK;
    }

    @Override
    public void setEndTime(LocalDateTime endTime) {
        // Validate if break end time is after break start time
        if (endTime.isBefore(this.startTime)) {
            throw new IllegalArgumentException("Break end time cannot be before break start time");
        }
        super.setEndTime(endTime);
    }
}