package fiap.hackaton.grupo32.hackatoncompany.infrastructure.repositories.entities;

import fiap.hackaton.grupo32.hackatoncompany.domain.enums.TimeEntriesTypeEnum;
import fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions.TimeEntryConstraintException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TimeEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TimeEntriesTypeEnum timeEntriesTypeEnum;
    private Boolean isToday;

    public TimeEntryEntity() {
        if (startTime != null) {
            this.isToday = startTime.toLocalDate().isEqual(LocalDateTime.now().toLocalDate());
        }
    }

    protected TimeEntryEntity(UUID employeeId, LocalDateTime startTime) {
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
