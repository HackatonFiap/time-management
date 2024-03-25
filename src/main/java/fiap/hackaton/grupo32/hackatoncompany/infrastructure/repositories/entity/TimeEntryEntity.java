package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class TimeEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private TimeEntriesTypeEnum entryType;

    public TimeEntryEntity() {
    }

    protected TimeEntryEntity(UUID employeeId, LocalDateTime startTime) {
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
