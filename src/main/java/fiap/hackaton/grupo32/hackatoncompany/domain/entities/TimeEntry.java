package fiap.hackaton.grupo32.hackatoncompany.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeEntry {

    private UUID id;
    private UUID employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TimeEntriesTypeEnum entryType;


    public TimeEntry() {
    }

    public TimeEntry(UUID employeeId, LocalDateTime startTime) {
        this.employeeId = employeeId;
        this.startTime = startTime;
    }

    public  TimeEntriesTypeEnum getType(){
        return this.entryType;
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
