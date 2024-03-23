package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;

import java.time.LocalDateTime;
import java.util.UUID;

public class TimeEntry {

    private Long id;
    private UUID employeeId;
    LocalDateTime startTime;
    private LocalDateTime endTime;
    private TimeEntriesTypeEnum timeEntriesTypeEnum;
    private Boolean isToday;

    protected TimeEntry(UUID employeeId, LocalDateTime startTime) {
        this.employeeId = employeeId;
        this.startTime = startTime;
        if (startTime != null) {
            this.isToday = startTime.toLocalDate().isEqual(LocalDateTime.now().toLocalDate());
        }
    }

    public  TimeEntriesTypeEnum getType(){
        return this.timeEntriesTypeEnum;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

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
