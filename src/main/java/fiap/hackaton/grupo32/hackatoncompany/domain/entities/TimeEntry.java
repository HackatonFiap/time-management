package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;

import java.time.LocalDateTime;

public abstract class TimeEntry {

    private Long id;
    private Long employeeId;
    LocalDateTime startTime;
    private LocalDateTime endTime;

    protected TimeEntry(Long employeeId, LocalDateTime startTime) {
        this.employeeId = employeeId;
        this.startTime = startTime;
    }

    public abstract TimeEntriesTypeEnum getType();

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    // Additional methods for calculating duration based on startTime and endTime.

    public Long getDurationInMinutes() {
        if (endTime == null) {
            throw new TimeEntryConstraintException("Time entry end time is not set");
        }
        return java.time.Duration.between(startTime, endTime).toMinutes();
    }

    public Long getDurationsinMillis() {
        if (endTime == null) {
            throw new TimeEntryConstraintException("Time entry end time is not set");
        }
        return java.time.Duration.between(startTime, endTime).toMillis();
    }
}
